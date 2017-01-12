package com.hulaVenueBiz.ui.mine.contract;

import com.hulaVenueBiz.base.mvp.BasePresenter;
import com.hulaVenueBiz.base.mvp.BaseMView;

/**
  * @desc:         密码修改逻辑
  * @author:       Leo
  * @date:         2017/1/10
  */
public interface PwdSettingContract
{
    interface View extends BaseMView {
        void changeSuccess();
    }

    abstract class Presenter extends BasePresenter<PwdSettingContract.View>
    {
        /**
         * 检验输入有效性
         * @param oldpwd     旧密码
         * @param newpwd     新密码
         * @param compwd     确认密码
         */
        public abstract void goToCheckPwd(String oldpwd, String newpwd, String compwd);
    }
}
