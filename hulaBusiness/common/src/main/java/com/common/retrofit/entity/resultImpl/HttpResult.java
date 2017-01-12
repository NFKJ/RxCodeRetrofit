package com.common.retrofit.entity.resultImpl;

import com.common.retrofit.entity.result.BaseRespBean;

public class HttpResult extends BaseRespBean {


    private String ExtInfo;

    public String getExtInfo() {
        return ExtInfo;
    }

    public void setExtInfo(String extInfo) {
        ExtInfo = extInfo;
    }
}
