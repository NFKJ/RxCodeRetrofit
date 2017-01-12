package com.hulaVenueBiz.ui.message.contract;

import com.hulaVenueBiz.base.mvp.BasePresenter;
import com.hulaVenueBiz.base.mvp.BaseMView;

public interface OrdMessContract
{
    interface View extends BaseMView {
        /***
         * 更新页面数据
         */
        void initView();
    }

    abstract class Presenter extends BasePresenter<View>
    {
        /**
         * 请求列表数据
         */
        public abstract void reqData();
    }
}