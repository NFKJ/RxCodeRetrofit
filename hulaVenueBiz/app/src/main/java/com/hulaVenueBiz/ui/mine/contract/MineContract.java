package com.hulaVenueBiz.ui.mine.contract;

import com.hulaVenueBiz.base.mvp.BasePresenter;
import com.hulaVenueBiz.base.mvp.BaseMView;

/**
 * @desc:         实现我的模块主页逻辑
 * @author:       Leo
 * @date:         2017/1/10
 */
public interface MineContract
{
    interface View extends BaseMView {
        void logoutSuccess();
    }

    abstract class Presenter extends BasePresenter<MineContract.View>
    {
        // 退出登录
        public abstract void goToLogout();
    }
}
