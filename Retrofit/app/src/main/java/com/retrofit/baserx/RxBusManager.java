package com.retrofit.baserx;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 管理 RxBus 和 Rxjava 提供了标签管理和方便的使用接口
 * Created by chenanze on 16/6/28.
 */
public class RxBusManager {

    //管理Observables 和 Subscribers订阅
    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public void add(Subscription m) {
        mCompositeSubscription.add(m);
    }

    public void clear() {
        if (!mCompositeSubscription.isUnsubscribed())
            mCompositeSubscription.unsubscribe();// 取消订阅
    }
}