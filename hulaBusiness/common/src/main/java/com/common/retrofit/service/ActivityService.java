package com.common.retrofit.service;

import com.common.retrofit.entity.params.ActivityBean;
import com.common.retrofit.entity.resultImpl.ResponseResult;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface ActivityService
{
    //查询最新活动
    @POST("SelectNewestActivity")
    Observable<ResponseResult> selectNewestActivity(@Body ActivityBean params);
}
