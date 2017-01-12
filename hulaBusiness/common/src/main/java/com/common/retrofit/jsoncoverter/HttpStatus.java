package com.common.retrofit.jsoncoverter;

import com.common.Constants;

public class HttpStatus {

    private int ErrorCode;
    private String Message;

    /**
     * API是否请求失败
     * @return 失败返回true, 成功返回false
     */
    public boolean isCodeInvalid() {
        return ErrorCode != Constants.SERVER_RESP_SUCCESS_CODE;
    }

    public void setErrorCode(int ErrorCode) {
        this.ErrorCode = ErrorCode;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public int getErrorCode() {
        return ErrorCode;
    }

    public String getMessage() {
        return Message;
    }
}