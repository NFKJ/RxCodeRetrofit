package com.hulaVenueBiz.base.mvp;

import android.content.Context;

import com.common.rxbus.RxManager;
import com.common.utils.EmptyUtils;

import java.lang.ref.SoftReference;

/**
 * @desc:         基类Presenter
 * @author:       Leo
 * @date:         2016/10/26
 */
public abstract class BasePresenter<V extends BaseView> {

    private SoftReference<V> mViewRef;
    protected RxManager rxManager;
    protected Context context;
    protected V mView;

    public void attachView(V view, Context con) {
        if (EmptyUtils.isNull(view)) {
            throw new NullPointerException("BasePresenter#attechView view can not be null");
        }

        context = con;
        mViewRef = new SoftReference<>(view);
        rxManager = new RxManager();
        mView = view;
    }

    protected boolean isViewAttached() {
        return EmptyUtils.isNotEmpty(mViewRef) && EmptyUtils.isNotEmpty(mViewRef.get());
    }

    protected V getView() {
        return mViewRef.get();
    }

    public void detachView() {
        if (isViewAttached()) {
            mViewRef.clear();
            mViewRef = null;
            rxManager.clear();
        }
    }
}
