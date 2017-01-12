package com.common.okhttp.base.callback;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

public abstract class Callback {
    /**
     * 请求之前执行此方法
     * <p>UI线程</p>
     */
    public void onBefore(Request request) {
    }

    /**
     * 请求结束后执行此方法
     * <p>UI线程</p>
     */
    public void onAfter() {
    }

    /**
     * 下载进度
     * <p>UI线程</p>
     *
     * @param totalBytes 文件总大小
     * @param currentBytes 已下载大小
     */
    public void onProgress(long totalBytes, long currentBytes) {
    }

    /**
     * 请求完成执行此方法
     * <p>子线程</p>
     */
    public abstract Object onResult(Response response) throws IOException;

    /**
     * <p>UI线程</p>
     */
    public void onError(int code, String errorMessage) {
    }
    //public void onError(Request request, Exception e){}

    /**
     * <p>UI线程</p>
     */
    public void onHttpError(int statusCode) {
    }
    //public void onHttpError(Response response, Exception e){}

    /**
     * <p>UI线程</p>
     */
    public abstract void onResponse(Object response);

    public static Callback CALLBACK_DEFAULT = new Callback() {
        @Override
        public Object onResult(Response response) throws IOException {
            return null;
        }

        @Override
        public void onResponse(Object response) {

        }
    };
}