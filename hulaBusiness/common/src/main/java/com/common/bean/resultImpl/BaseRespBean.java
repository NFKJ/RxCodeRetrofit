package com.common.bean.resultImpl;

public class BaseRespBean<T>
{
    private Boolean IsSuccess;
    private Boolean NextPage;
    private String ErrorMsg;
    private Long ResponseCode;
    private T ExtInfo;

    public T getExtInfo() {
        return ExtInfo;
    }

    public void setExtInfo(T extInfo) {
        ExtInfo = extInfo;
    }

    public Boolean getNextPage() {
        return NextPage;
    }

    public void setNextPage(Boolean nextPage) {
        NextPage = nextPage;
    }

    public Boolean getIsSuccess() {
        return IsSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        IsSuccess = isSuccess;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }

    public Long getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(Long responseCode) {
        ResponseCode = responseCode;
    }
}
