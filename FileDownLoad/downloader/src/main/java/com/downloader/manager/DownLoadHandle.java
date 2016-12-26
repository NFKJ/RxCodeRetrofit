package com.downloader.manager;

import android.text.TextUtils;

import com.downloader.entity.DownLoadEntity;
import com.downloader.listener.GetFileCountListener;
import com.downloader.manager.database.DownLoadDatabase;
import com.downloader.manager.retrofit.RetrofitManager;
import com.downloader.utils.CpuUtils;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * @desc:
 * @author: Leo
 * @date: 2016/12/06
 */
public class DownLoadHandle
{
    private DownLoadDatabase mDownLoadDatabase;

    private ExecutorService mGetFileService = Executors.newFixedThreadPool(CpuUtils.getNumCores() + 1);

    private int mDownLoadCount;

    public DownLoadHandle(DownLoadDatabase mDownLoadDatabase) {
        this.mDownLoadDatabase = mDownLoadDatabase;
    }

    List<DownLoadEntity> queryDownLoadData(List<DownLoadEntity> list) {
        final Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            DownLoadEntity downLoadEntity = (DownLoadEntity) iterator.next();
            downLoadEntity.setDowned(0);
            Call<ResponseBody> mResponseCall = null;
            List<DownLoadEntity> dataList = mDownLoadDatabase.query(downLoadEntity.getUrl());

            if (dataList.size() > 0) {        //数据库中存在未下载完成的文件
                downLoadEntity.setMultiList(dataList);

                // 根据lastModify判断服务端文件是否改变
                if (!TextUtils.isEmpty(dataList.get(0).getLastModify())) {
                    mResponseCall = RetrofitManager.getInstance().getmDownLoadService()
                            .getHttpHeaderWithIfRange(downLoadEntity.getUrl(),
                                    dataList.get(0).getLastModify(),
                                    "bytes" + 0 + "-" + 0);
                }
            } else {
                mResponseCall = RetrofitManager.getInstance().getmDownLoadService()
                        .getHttpHeader(downLoadEntity.getUrl(), "bytes" + 0 + "-" + 0);
            }
            executeGetFileWork(mResponseCall, new GetFileCount(downLoadEntity, mResponseCall));
        }

        return list;
    }

    private void executeGetFileWork(Call<ResponseBody> mResponseCall, GetFileCountListener listener) {
        GetFileCountTask getFileCountTask = new GetFileCountTask(mResponseCall, listener);
        mGetFileService.submit(getFileCountTask);
    }

    private class GetFileCount implements GetFileCountListener {

        private DownLoadEntity mDownLoadEntity;
        private Call<ResponseBody> mResponseCall;

        int reCount = 3;

        public GetFileCount(DownLoadEntity mDownLoadEntity, Call<ResponseBody> mResponseCall) {
            this.mDownLoadEntity = mDownLoadEntity;
            this.mResponseCall = mResponseCall;
        }

        @Override
        public void success(boolean isSupportMulti, boolean isNew, String modified, Long fileSize) {
            mDownLoadEntity.setTotal(fileSize);
            mDownLoadEntity.setLastModify(modified);
            mDownLoadEntity.setSupportMulti(isSupportMulti);

            if (!isNew) {
                //未更换资源
                if (mDownLoadEntity.getMultiList() != null) {   // 说明下载过
                    File file = new File(mDownLoadEntity.getSaveName());
                    if (file.exists()) {
                        // 文件存在 下载剩余
                        Iterator dataIterator = mDownLoadEntity.getMultiList().iterator();
                        while (dataIterator.hasNext()) {
                            DownLoadEntity dataEntity = (DownLoadEntity) dataIterator.next();
                            mDownLoadEntity.setDowned(mDownLoadEntity.getDowned() + dataEntity.getDowned());
                        }
                    } else {
                        // 文件不存在 删除数据库 重新下载
                        mDownLoadDatabase.deleteAllByUrl(mDownLoadEntity.getUrl());
                    }
                }
            } else {
                // 更换资源 重新下载
                mDownLoadDatabase.deleteAllByUrl(mDownLoadEntity.getUrl());
            }
            setCount();
        }

        @Override
        public void failed() {
            if (reCount <= 0) {
                setCount();
                if (!mGetFileService.isShutdown()) {
                    mGetFileService.shutdownNow();
                }
            } else {
                reCount--;
                executeGetFileWork(mResponseCall, this);
            }
        }
    }

    private synchronized void setCount() {
        mDownLoadCount++;
    }

    private synchronized int getCount() {
        return mDownLoadCount;
    }
}
