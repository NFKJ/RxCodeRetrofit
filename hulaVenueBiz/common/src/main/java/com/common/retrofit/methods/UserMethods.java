package com.common.retrofit.methods;

import com.common.retrofit.base.BaseMethods;
import com.common.retrofit.entity.params.LoginParams;
import com.common.retrofit.entity.resultImpl.HttpRespBean;
import com.common.retrofit.service.UserService;

import rx.Observable;
import rx.Subscriber;

public class UserMethods extends BaseMethods {

    private static UserMethods m_ins = null;

    public static UserMethods getInstance() {
        if (m_ins == null) {
            synchronized (UserMethods.class) {
                if (m_ins == null) {
                    m_ins = new UserMethods();
                }
            }
        }
        return m_ins;
    }

    @Override
    protected String getHttpUrl() {
        return "user/";
    }

    private UserService initService() {
        return getRetrofit().create(UserService.class);
    }

    public void goToLogin(String phone, String pwd, Subscriber<String> subscriber){
        Observable<HttpRespBean<String>> observable = initService().goToLogin(new LoginParams(phone, pwd));
        toSubscribe(observable, subscriber);
    }
}
