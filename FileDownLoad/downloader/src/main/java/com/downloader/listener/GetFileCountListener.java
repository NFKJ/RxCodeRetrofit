package com.downloader.listener;

/**
 * @desc:
 * @author: Leo
 * @date: 2016/12/06
 */
public interface GetFileCountListener {

    void success(boolean isSupportMulti, boolean isNew, String modified, Long fileSize);

    void failed();
}
