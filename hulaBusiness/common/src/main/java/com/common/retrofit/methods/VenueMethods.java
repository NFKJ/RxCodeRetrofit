package com.common.retrofit.methods;

import com.common.retrofit.base.BaseMethods;
import com.common.retrofit.entity.params.ActivityBean;
import com.common.retrofit.entity.result.VenueDetail;
import com.common.retrofit.service.VenueService;

import rx.Observable;
import rx.Subscriber;

public class VenueMethods extends BaseMethods {

    private static VenueMethods m_ins = null;

    public static VenueMethods getInstance() {
        if (m_ins == null) {
            synchronized (VenueMethods.class) {
                if (m_ins == null) {
                    m_ins = new VenueMethods();
                }
            }
        }
        return m_ins;
    }

    @Override
    protected String getHttpUrl() {
        return "venue/";
    }

    private VenueService initService() {
        return getRetrofit().create(VenueService.class);
    }

    public void getVenueInfo(String params, Subscriber<VenueDetail> subscriber){

        Observable<VenueDetail> observable = initService().getVenue(new ActivityBean(params))
                .map(new VHttpResultFunc<VenueDetail>()).onErrorResumeNext(new HttpResultErrorFunc<VenueDetail>());
        toSubscribe(observable, subscriber);
    }
}
