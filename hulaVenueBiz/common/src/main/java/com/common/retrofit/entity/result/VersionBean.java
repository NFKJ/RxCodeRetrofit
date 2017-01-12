package com.common.retrofit.entity.result;


import com.common.retrofit.entity.resultImpl.Enum;

/**
 * @desc:         版本更新
 * @author:       Leo
 * @date:         2017/1/9
 */
public class VersionBean
{
    //版本类型枚举
    private int action;

    //跳转地址
    private String url;

    public Enum.EnumVersion getAction() {
        return Enum.EnumVersion.valueOf(action);
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
