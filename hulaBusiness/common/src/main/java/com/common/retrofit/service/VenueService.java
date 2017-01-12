package com.common.retrofit.service;

import com.common.retrofit.entity.params.ActivityBean;
import com.common.retrofit.entity.result.VenueDetail;
import com.common.retrofit.entity.resultImpl.HttpRespBean;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface VenueService {

    @POST("getVenueInfo")
    Observable<HttpRespBean<VenueDetail>> getVenue(@Body ActivityBean params);
}
