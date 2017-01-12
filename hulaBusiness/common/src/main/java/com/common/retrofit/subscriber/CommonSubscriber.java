package com.common.retrofit.subscriber;

import com.common.retrofit.jsoncoverter.ApiException;
import com.common.utils.LogUtils;

import rx.Subscriber;

public class CommonSubscriber<T> extends Subscriber<T> implements CancelSubscriberListener {

    private SubscriberListener mSubscriberListener;

    public CommonSubscriber(SubscriberListener mSubscriberListener) {
        this.mSubscriberListener = mSubscriberListener;
    }

    @Override
    public void onCompleted() {
    }

    /**
     * 对错误进行统一处理
     * @param e Throwable
     */
    @Override
    public void onError(Throwable e)
    {
        e.printStackTrace();

        ApiException exception = (ApiException) e;
        if (exception.isTokenExpried()) {
            //处理token失效对应的逻辑    强制下线，，，
        } else {
            LogUtils.e(e.getMessage());
        }

        if (mSubscriberListener != null) {
            mSubscriberListener.onError(e.toString(), exception.getCode());
        }
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (mSubscriberListener != null) {
            mSubscriberListener.onNext(t);
        }
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelSubscriber() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }
}