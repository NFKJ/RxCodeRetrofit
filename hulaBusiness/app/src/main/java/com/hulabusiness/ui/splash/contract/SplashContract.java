package com.hulabusiness.ui.splash.contract;

import android.content.Context;

import com.hulabusiness.base.mvp.BasePresenter;
import com.hulabusiness.base.mvp.BaseView;

/**
 * @desc:
 * @author: Leo
 * @date:   2016/10/10
 */
public interface SplashContract
{
    interface View extends BaseView {
        void readyToMain();
        void readyToGuide();
    }

    abstract class Presenter extends BasePresenter<View>
    {
        /**
         * 背景浮动动画
         * @param context con
         * @param view 背景控件
         */
        public abstract void checkIsFirstIn(Context context, android.view.View view);
    }
}