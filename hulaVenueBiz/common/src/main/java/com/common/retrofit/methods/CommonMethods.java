package com.common.retrofit.methods;

import com.common.base.Constants;
import com.common.retrofit.entity.params.CheckParams;
import com.common.retrofit.base.BaseMethods;
import com.common.retrofit.entity.result.VersionBean;
import com.common.retrofit.entity.resultImpl.HttpRespBean;
import com.common.retrofit.service.CommonService;
import com.common.utils.AppUtils;

import rx.Observable;
import rx.Subscriber;

public class CommonMethods extends BaseMethods {

    private static CommonMethods m_ins = null;

    public static CommonMethods getInstance() {
        if (m_ins == null) {
            synchronized (CommonMethods.class) {
                if (m_ins == null) {
                    m_ins = new CommonMethods();
                }
            }
        }
        return m_ins;
    }

    @Override
    protected String getHttpUrl() {
        return "common/";
    }

    private CommonService initService() {
        return getRetrofit().create(CommonService.class);
    }

    public void checkVersion(Subscriber<VersionBean> subscriber){
        Observable<HttpRespBean<VersionBean>> observable = initService().checkVersion(
                new CheckParams(AppUtils.getVersionName(), AppUtils.getPacketChannelName(), Constants.ANDROID));
        toSubscribe(observable, subscriber);
    }
}
