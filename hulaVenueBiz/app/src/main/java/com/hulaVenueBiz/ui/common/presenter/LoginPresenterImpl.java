package com.hulaVenueBiz.ui.common.presenter;

import com.common.base.DataCenter;
import com.common.okhttp.beans.HVLoginBean;
import com.common.okhttp.services.IUserService;
import com.common.okhttp.zmoumall.http.ObjectCallback;
import com.common.utils.EmptyUtils;
import com.hulaVenueBiz.R;
import com.hulaVenueBiz.ui.common.contract.LoginContract;

/**
 * @desc:         登录逻辑
 * @author:       Leo
 * @date:         2017/1/6
 */
public class LoginPresenterImpl extends LoginContract.Presenter
{
    @Override
    public void goToLogin(String account, String password) {

        if (EmptyUtils.isEmpty(account)) {
            mView.showToastMsg(context.getString(R.string.string_login_t_input));
            return;
        } else if (EmptyUtils.isEmpty(password)) {
            mView.showToastMsg(context.getString(R.string.string_login_t_pwd));
            return;
        }

        mView.showProgressingDialog();

        IUserService.login(context, account, password, new ObjectCallback<HVLoginBean>() {
            @Override
            public void onSuccess(HVLoginBean response, String message) {
                mView.dismissProgressDialog();

                if (EmptyUtils.isEmpty(response)) {
                    mView.showToastMsg(context.getString(R.string.string_login_t_failed));
                    return;
                }

                mView.showToastMsg(context.getString(R.string.string_login_t_success));

                // 保存信息
                DataCenter.saveLoginDataInfo(context, response);

                mView.loginSuccess();
            }

            @Override
            public void onErrorT(int code, HVLoginBean response, String errorMessage) {
                mView.dismissProgressDialog();
                mView.showToastMsg(errorMessage);
            }
        });
    }
}