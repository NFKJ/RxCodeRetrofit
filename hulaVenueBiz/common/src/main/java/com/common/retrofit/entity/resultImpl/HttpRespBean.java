package com.common.retrofit.entity.resultImpl;

public class HttpRespBean<T>
{
    private int ErrorCode;
    private String Message;
    private T Data;

    public void setErrorCode(int ErrorCode) {
        this.ErrorCode = ErrorCode;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public void setData(T Data) {
        this.Data = Data;
    }

    public int getErrorCode() {
        return ErrorCode;
    }

    public String getMessage() {
        return Message;
    }

    public T getData() {
        return Data;
    }
}
