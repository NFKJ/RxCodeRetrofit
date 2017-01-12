package com.common.retrofit.service;

import com.common.retrofit.entity.params.LoginParams;
import com.common.retrofit.entity.resultImpl.HttpRespBean;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface UserService
{
    @POST("login")
    Observable<HttpRespBean<String>> goToLogin(@Body LoginParams params);
}
