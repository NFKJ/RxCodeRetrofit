package com.hulaVenueBiz.ui.common.contract;

import com.hulaVenueBiz.base.mvp.BaseMView;
import com.hulaVenueBiz.base.mvp.BasePresenter;

public interface LoginContract
{
    interface View extends BaseMView
    {
        void loginSuccess();
    }

    abstract class Presenter extends BasePresenter<View>
    {
        /**
         * 用户登录
         * @param account    账号
         * @param password   密码
         */
        public abstract void goToLogin(String account, String password);
    }
}