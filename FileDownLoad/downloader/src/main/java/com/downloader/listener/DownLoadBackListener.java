package com.downloader.listener;

import com.downloader.entity.DownLoadEntity;

/**
 * @desc:         下载结果回调
 * @author:       Leo
 * @date:         2016/12/6
 */
public interface DownLoadBackListener
{
    void onStart(double percent);

    void onCancel();

    void onDownLoading(double percent);

    void onCompleted();

    void onError(DownLoadEntity downLoadEntity, Throwable throwable);
}