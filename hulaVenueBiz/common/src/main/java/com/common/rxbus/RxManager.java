package com.common.rxbus;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

 /**
  * @desc:         用于管理单个presenter的RxBus的事件和Rxjava相关代码的生命周期处理
  * @author:       Leo
  * @date:         2016/12/29
  */
public class RxManager
{
    /*管理Observables 和 Subscribers订阅*/
    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    /**
     * 单纯的Observables 和 Subscribers管理
     * @param m
     */
    public void add(Subscription m) {
        /*订阅管理*/
        mCompositeSubscription.add(m);
    }
    
    /**
     * 单个presenter生命周期结束，取消订阅和所有rxbus观察
     */
    public void clear() {
        if (!mCompositeSubscription.isUnsubscribed())
            mCompositeSubscription.unsubscribe();// 取消所有订阅
    }
}
