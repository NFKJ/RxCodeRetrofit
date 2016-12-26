package com.downloader.manager;

import com.downloader.entity.DownLoadEntity;
import com.downloader.listener.DownLoadTaskListener;
import com.downloader.manager.retrofit.RetrofitManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.RandomAccessFile;
import java.net.SocketTimeoutException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @desc:
 * @author: Leo
 * @date: 2016/12/06
 */
public class DownLoadTask implements Runnable {
    private String mSaveFileName;

    private DownLoadTaskListener downLoadTaskListener;

    private Call<ResponseBody> responseCall;

    private long fileSizeDownLoaded;

    private DownLoadEntity downLoadEntity;

    private long mNeedDownSize;

    private final long CALL_BACK_LENGTH = 1024 * 1024;

    public DownLoadTask(DownLoadEntity downLoadEntity, DownLoadTaskListener downLoadTaskListener) {
        this.downLoadEntity = downLoadEntity;
        this.downLoadTaskListener = downLoadTaskListener;
        this.mSaveFileName = downLoadEntity.getSaveName();
        this.mNeedDownSize = downLoadEntity.getEnd() - (downLoadEntity.getStart() + downLoadEntity.getDowned());
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        long start = 0;
        long end = downLoadEntity.getEnd();
        String url = downLoadEntity.getUrl();

        if (downLoadEntity.getDowned() != 0) {
            start = downLoadEntity.getStart() + downLoadEntity.getDowned();
        } else {
            start = downLoadEntity.getStart();
        }
        responseCall = RetrofitManager.getInstance().getmDownLoadService()
                .downloadFile(url, "bytes=" + start + "-" + end);

        ResponseBody result = null;

        try {
            Response response = responseCall.execute();
            // onStart
            result = (ResponseBody) response.body();
            if (response.isSuccessful()) {
                if (writeToFile(result, downLoadEntity.getStart(), downLoadEntity.getDowned())) {
                    onCompleted();
                }
            } else {
                onError(new Throwable(response.message()));
            }
        } catch (IOException e) {
            onError(new Throwable(e.getMessage()));
        } finally {
            if (result != null) {
                result.close();
            }
        }
    }

    private boolean writeToFile(ResponseBody result, long start, long downed) {

        try {
            File downLoadFile = new File(mSaveFileName);

            if (!downLoadFile.exists()) {
                downLoadFile.createNewFile();
            }

            // 跳过已写入的文件部分
            RandomAccessFile saveFile = new RandomAccessFile(downLoadFile, "rw");
            saveFile.seek(start + downed);

            InputStream inputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                inputStream = result.byteStream();

                while (fileSizeDownLoaded < mNeedDownSize) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    saveFile.write(fileReader, 0, read);

                    fileSizeDownLoaded += read;

                    if (fileSizeDownLoaded >= CALL_BACK_LENGTH) {
                        onDownLoading(fileSizeDownLoaded);
                        mNeedDownSize -= fileSizeDownLoaded;
                        fileSizeDownLoaded = 0;
                    } else {
                        if (mNeedDownSize < CALL_BACK_LENGTH) {
                            if (fileSizeDownLoaded - 1 == mNeedDownSize) {
                                onDownLoading(fileSizeDownLoaded);
                                break;
                            }
                        }
                    }
                }
                return true;
            } finally {
                saveFile.close();

                if (inputStream != null) {
                    inputStream.close();
                }
            }
        } catch (IOException e) {
            if (e instanceof InterruptedIOException && !(e instanceof SocketTimeoutException)) {
                onCancel();
            } else {
                onError(e);
            }
        }
        return false;
    }

    private void onError(Throwable e) {
        downLoadTaskListener.onError(downLoadEntity, e);
    }

    private void onDownLoading(long fileSizeDownLoaded) {
        downLoadTaskListener.onDownLoading(fileSizeDownLoaded);
        downLoadEntity.setDowned(downLoadEntity.getDowned() + fileSizeDownLoaded);
    }

    private void onCancel() {
        responseCall.cancel();
        responseCall = null;
        downLoadTaskListener.onCancel(downLoadEntity);
    }

    private void onCompleted() {
        responseCall = null;
        downLoadTaskListener.onCompleted(downLoadEntity);
    }

    public static final class Builder {
        private DownLoadEntity model;

        private DownLoadTaskListener downLoadTaskListener;

        public Builder downLoadModel(DownLoadEntity downLoadEntity) {
            model = downLoadEntity;
            return this;
        }

        public Builder downLoadTaskListener(DownLoadTaskListener downLoadTaskListener) {
            this.downLoadTaskListener = downLoadTaskListener;
            return this;
        }

        public DownLoadTask build() {
            if (model.getUrl().isEmpty()) {
                throw new IllegalStateException("DownLoad URL required");
            }

            if (downLoadTaskListener == null) {
                throw new IllegalStateException("DownLoadTaskListener required");
            }

            if (model.getEnd() == 0) {
                throw new IllegalStateException("End reuqired");
            }

            return new DownLoadTask(model, downLoadTaskListener);
        }
    }
}
