package com.common.retrofit.base;

import com.common.bean.resultImpl.BaseRespBean;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * @desc:         请求数据处理类
 * @author:       Leo
 * @date:         2016/12/29
 */
public class RxHelper
{
    /**
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<BaseRespBean<T>, T> handleResult(final LifeCycleEvent event, final PublishSubject<LifeCycleEvent> lifecycleSubject) {
        return new Observable.Transformer<BaseRespBean<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseRespBean<T>> tObservable) {
                Observable<LifeCycleEvent> compareLifecycleObservable =
                        lifecycleSubject.takeFirst(new Func1<LifeCycleEvent, Boolean>() {
                            @Override
                            public Boolean call(LifeCycleEvent LifeCycleEvent) {
                                return LifeCycleEvent.equals(event);
                            }
                        });
                return tObservable.flatMap(new Func1<BaseRespBean<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(BaseRespBean<T> result) {
                        if (result.getResponseCode() != 0) {
                            return createData(result.getExtInfo());
                        } else {
                            return Observable.error(new HttpResultException(result.getResponseCode()));
                        }
                    }
                }) .takeUntil(compareLifecycleObservable)
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 创建成功的数据
     * @param data    请求响应数据
     * @param <T>     数据体格式
     * @return
     */
    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
