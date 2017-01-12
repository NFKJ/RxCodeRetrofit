package com.common.retrofit.jsoncoverter;

import com.common.Constants;

public class ApiException extends RuntimeException
{
    private int mErrorCode;
    private String displayMessage;

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.mErrorCode = code;
    }

    public ApiException(int errorCode, String errorMessage) {
        super(errorMessage);
        mErrorCode = errorCode;
    }

    public int getCode() {
        return mErrorCode;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String msg) {
        this.displayMessage = msg + "(code:" + mErrorCode + ")";
    }

    /**
     * 判断是否是token失效
     * @return 失效返回true, 否则返回false;
     */
    public boolean isTokenExpried() {
        return mErrorCode == Constants.SERVER_RESP_TOKEN_EXPRIED;
    }

    // 未知错误
    public static final int UNKNOWN = 1000;

    // 解析错误
    public static final int PARSE_ERROR = 1001;

    // 网络错误
    public static final int NETWORD_ERROR = 1002;

    // 协议出错
    public static final int HTTP_ERROR = 1003;

    // 证书出错
    public static final int SSL_ERROR = 1005;

    // 连接超时与连接失败统一为连接失败
    public static final int REQUEST_FILED = 1006;
}