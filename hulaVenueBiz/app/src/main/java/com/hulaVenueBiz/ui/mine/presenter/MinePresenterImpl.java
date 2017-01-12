package com.hulaVenueBiz.ui.mine.presenter;

import com.common.base.DataCenter;
import com.common.okhttp.services.IUserService;
import com.common.okhttp.zmoumall.http.ObjectCallback;
import com.common.utils.ActivityStack;
import com.hulaVenueBiz.ui.mine.contract.MineContract;

/**
  * @desc:         退出登录逻辑实现
  * @author:       Leo
  * @date:         2017/1/10
  */
public class MinePresenterImpl extends MineContract.Presenter {

    @Override
    public void goToLogout()
    {
        mView.showProgressingDialog();

        IUserService.logout(context, new ObjectCallback<Object>() {
            @Override
            public void onSuccess(Object response, String message) {
                mView.dismissProgressDialog();

                // 清除用户登录信息
                DataCenter.deleteLoginDataInfo(context);
                ActivityStack.getInstance().finishAllActivity();
                mView.logoutSuccess();
            }

            @Override
            public void onErrorT(int code, Object response, String errorMessage) {
                mView.dismissProgressDialog();
                mView.showToastMsg(errorMessage);
            }
        });
    }
}