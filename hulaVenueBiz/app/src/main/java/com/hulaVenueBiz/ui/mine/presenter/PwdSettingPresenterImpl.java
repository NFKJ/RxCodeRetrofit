package com.hulaVenueBiz.ui.mine.presenter;

import com.common.okhttp.services.IUserService;
import com.common.okhttp.zmoumall.http.ObjectCallback;
import com.common.utils.EmptyUtils;
import com.common.utils.StringUtils;
import com.hulaVenueBiz.ui.mine.contract.PwdSettingContract;

/**
  * @desc:         密码修改逻辑
  * @author:       Leo
  * @date:         2017/1/10
  */
public class PwdSettingPresenterImpl extends PwdSettingContract.Presenter {

    private void goToSetPwd(String oldpwd, String newpwd) {
        mView.showProgressingDialog();

        IUserService.changePassword(context, oldpwd, newpwd, new ObjectCallback<Object>() {
            @Override
            public void onSuccess(Object response, String message) {
                mView.dismissProgressDialog();
                mView.showToastMsg("密码修改成功");
                mView.changeSuccess();
            }

            @Override
            public void onErrorT(int code, Object response, String errorMessage) {
                mView.dismissProgressDialog();
                mView.showToastMsg(errorMessage);
            }
        });
    }

    @Override
    public void goToCheckPwd(String oldpwd, String newpwd, String compwd)
    {
        if (EmptyUtils.isEmpty(oldpwd)) {
            mView.showToastMsg("原密码不可为空");
            return;
        } else if (EmptyUtils.isEmpty(newpwd)) {
            mView.showToastMsg("新密码不可为空");
            return;
        } else if (EmptyUtils.isEmpty(compwd)) {
            mView.showToastMsg("确认密码不可为空");
            return;
        } else if (!StringUtils.isNotNullAndEqual(newpwd, compwd)) {
            mView.showToastMsg("新密码与确认密码不一致");
            return;
        } else {
            goToSetPwd(oldpwd, newpwd);
        }
    }
}