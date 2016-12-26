package com.downloader.listener;

import com.downloader.entity.DownLoadEntity;
import com.downloader.manager.download.MainThreadImpl;

/**
  * @desc:         下载结果回调
  * @author:       Leo
  * @date:         2016/12/6
  */
public class DownCallBackListener implements DownLoadTaskListener
{
    private MainThreadImpl mainThread = MainThreadImpl.getInstance();

    private DownLoadBackListener mBackListener;

    long mTotalSize;
    long mHasDownSize;

    private boolean isRetureStart;
    private boolean isRetureErr;
    private boolean isRetureCancel;

    public DownCallBackListener(DownLoadBackListener mBackListener, long mTotalSize, long mHasDownSize) {
        this.mBackListener = mBackListener;
        this.mTotalSize = mTotalSize;
        this.mHasDownSize = mHasDownSize;
    }

    @Override
    public synchronized void onStart() {
        if (!isRetureStart) {
            mainThread.post(new Runnable() {
                @Override
                public void run() {
                    mBackListener.onStart((double) mHasDownSize / mTotalSize);
                }
            });
        }
        isRetureStart = true;
    }

    @Override
    public synchronized void onCancel(DownLoadEntity downLoadEntity) {
        if (!isRetureCancel) {
            mainThread.post(new Runnable() {
                @Override
                public void run() {
                    mBackListener.onCancel();
                }
            });
        }
        isRetureCancel = true;
    }

    @Override
    public synchronized void onDownLoading(long downSize) {
        mHasDownSize += downSize;
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                mBackListener.onDownLoading((double) mHasDownSize / mTotalSize);
            }
        });
    }

    @Override
    public synchronized void onCompleted(DownLoadEntity downLoadEntity) {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                mBackListener.onCompleted();
            }
        });
    }

    @Override
    public synchronized void onError(final DownLoadEntity downLoadEntity, final Throwable throwable) {
        if (!isRetureErr) {
            mainThread.post(new Runnable() {
                @Override
                public void run() {
                    mBackListener.onError(downLoadEntity, throwable);
                }
            });
        }
        isRetureErr = true;
    }
}
