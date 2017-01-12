package com.common.retrofit.base;

/**
 * 用于捕获服务器约定的错误类型
 */
public class ResultException extends RuntimeException {
    private int errCode = 0;

    public ResultException(int errCode, String msg) {
        super(msg);
        this.errCode = errCode;
    }

    public int getErrCode() {
        return errCode;
    }
}