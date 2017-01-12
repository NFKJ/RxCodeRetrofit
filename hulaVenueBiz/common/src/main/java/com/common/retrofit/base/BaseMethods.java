package com.common.retrofit.base;

import com.common.base.Constants;
import com.common.retrofit.entity.resultImpl.HttpRespBean;
import com.common.retrofit.jsoncoverter.CustomGsonConverterFactory;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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

    // 统一正常返回处理
    public class HttpResultFunc<T> implements Func1<HttpRespBean<T>, T> {
        @Override
        public T call(HttpRespBean<T> resultFunc) {
            return resultFunc.getData();
        }
    }

    // 统一异常处理
    public class HttpResultErrorFunc<T> implements Func1<Throwable, Observable<T>> {
        @Override public Observable<T> call(Throwable t) {
            return Observable.error(ExceptionHandle.handleException(t));
        }
    }

    // 统一添加线程切换
    public <T> void toSubscribe(Observable<HttpRespBean<T>> observable,Subscriber<T> subscribe){
        observable.map(new HttpResultFunc<T>()).onErrorResumeNext(new HttpResultErrorFunc<T>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscribe);
    }
}
