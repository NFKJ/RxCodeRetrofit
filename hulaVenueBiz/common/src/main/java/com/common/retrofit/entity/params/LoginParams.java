package com.common.retrofit.entity.params;

/**
 * @desc:         登录参数
 * @author:       Leo
 * @date:         2017/1/6
 */
public class LoginParams
{
    private String phone;
    private String pwd;

    public LoginParams(String phone, String pwd) {
        this.phone = phone;
        this.pwd = pwd;
    }
}
