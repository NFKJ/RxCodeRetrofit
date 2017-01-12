package com.common.retrofit.service;

import com.common.retrofit.entity.params.CheckParams;
import com.common.retrofit.entity.result.VersionBean;
import com.common.retrofit.entity.resultImpl.HttpRespBean;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface CommonService
{
    @POST("checkVersion")
    Observable<HttpRespBean<VersionBean>> checkVersion(@Body CheckParams params);
}
