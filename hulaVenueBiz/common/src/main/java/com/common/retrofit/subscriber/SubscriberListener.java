package com.common.retrofit.subscriber;

public interface SubscriberListener<T> {
    void onNext(T t);

    void onError(String e, int code);
}
