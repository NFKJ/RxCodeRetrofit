package com.downloader.listener;

import com.downloader.entity.DownLoadEntity;

public interface DownLoadTaskListener
{
    void onStart();

    void onCancel(DownLoadEntity downLoadEntity);

    void onDownLoading(long downSize);

    void onCompleted(DownLoadEntity downLoadEntity);

    void onError(DownLoadEntity downLoadEntity, Throwable throwable);
}
