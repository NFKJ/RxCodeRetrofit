package com.hulabusiness.base.mvp;

import com.common.utils.EmptyUtils;

import java.lang.ref.SoftReference;

/**
 * @desc:         基类Presenter
 * @author:       Leo
 * @date:         2016/10/26
 */
public abstract class BasePresenter<V extends BaseView> {

    private SoftReference<V> mViewRef;
//    protected RxBusManager rxManager;
    protected V mView;

    public void attachView(V view) {
        if (EmptyUtils.isNull(view)) {
            throw new NullPointerException("BasePresenter#attechView view can not be null");
        }
        mViewRef = new SoftReference<>(view);
//        rxManager = new RxBusManager();
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
//            rxManager.clear();
        }
    }
}
