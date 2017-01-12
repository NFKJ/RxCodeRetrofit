package com.hulaVenueBiz.ui.common.contract;

import com.hulaVenueBiz.base.mvp.BaseMView;
import com.hulaVenueBiz.base.mvp.BasePresenter;

/**
 * @desc:
 * @author: Leo
 * @date:   2016/10/10
 */
public interface SplashContract
{
    /**
     * 首次启动进入引导页
     * 非首次且已经登录进入主页
     * 非首次且未登录进入登录页
     */
    interface View extends BaseMView {
        void readyToMain();
        void readyToLogin();
        void readyToGuide();

        void updataForce(String url);          // 强制更新
        void updataRecommend(String url);      // 推荐更新
    }

    abstract class Presenter extends BasePresenter<View>
    {
        /**  检查 是否首次启动  */
        public abstract void checkIsFirstIn();

        /**  版本检查更新  */
        public abstract void checkVersion();
    }
}