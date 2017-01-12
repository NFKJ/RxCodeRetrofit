package com.common.retrofit.base;

import com.common.Constants;
import com.common.bean.resultImpl.BaseRespBean;
import com.common.retrofit.entity.resultImpl.HttpRespBean;
import com.common.retrofit.jsoncoverter.CustomGsonConverterFactory;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.utils.EmptyUtils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * @desc:         接口调用基类
 * @author:       Leo
 * @date:         2016/12/29
 */
public abstract class BaseMethods {

    protected abstract String getHttpUrl();

    public Retrofit getRetrofit()
    {
        return new Retrofit.Builder()
                .client(OkHttpManager.getClient().getOkHttp())
                .addConverterFactory(CustomGsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constants.BaseUrl + getHttpUrl())
                .build();
    }

    public class VHttpResultFunc<T> implements Func1<HttpRespBean<T>, T> {

        @Override
        public T call(HttpRespBean<T> resultFunc) {
            return resultFunc.getData();
        }
    }

    public class HttpResultErrorFunc<T> implements Func1<Throwable, Observable<T>> {
        @Override public Observable<T> call(Throwable t) {
            return Observable.error(ExceptionHandle.handleException(t));
        }
    }

    /**
     *
     * @param ob    ob
     * @param subscriber    sub
     * @param event   Activity 生命周期
     * @param lifecycleSubject  Activity 生命周期
     */
    public void toSubscribe(Observable ob, final CommonSubscriber subscriber, final LifeCycleEvent event,
                            final PublishSubject<LifeCycleEvent> lifecycleSubject) {
        //数据预处理
        Observable.Transformer<BaseRespBean<Object>, Object> result = RxHelper.handleResult(event,lifecycleSubject);
        Observable observable = ob.compose(result)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        //显示Dialog和一些其他操作
                    }
                });
    }

    public <T> void toSubscribe(Observable<T> observable,Subscriber<T> subscribe){
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscribe);
    }

    public <T> Observable.Transformer<HttpRespBean<T>, T> sTransformer() {
        return new Observable.Transformer<HttpRespBean<T>, T>() {
            @Override
            public Observable<T> call(Observable<HttpRespBean<T>> httpRespBeanObservable) {
                return httpRespBeanObservable.map(new Func1<HttpRespBean<T>, T>() {
                    @Override
                    public T call(HttpRespBean<T> tHttpRespBean) {

                        if (EmptyUtils.isEmpty(tHttpRespBean.getMessage()) || EmptyUtils.isNull(tHttpRespBean.getData())) {

                        }
                        return tHttpRespBean.getData();
                    }
                }).onErrorResumeNext(new HttpResultErrorFunc<T>());
            }
        };
    }

//    public static <T> Observable.Transformer<T, T> switchSchedulers() {
//        return new Observable<>()
//        return observable -> observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }

    private static class HttpResponseFunc<T> implements Func1<Throwable, Observable<T>> {
        @Override public Observable<T> call(Throwable throwable) {
            //ExceptionEngine为处理异常的驱动器
            return Observable.error(new Throwable(throwable));
        }
    }
}
