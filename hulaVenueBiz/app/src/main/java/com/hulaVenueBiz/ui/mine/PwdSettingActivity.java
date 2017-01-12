package com.hulaVenueBiz.ui.mine;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.common.widget.dialog.CommonDialog;
import com.common.widget.navigation.WidgeButton;
import com.hulaVenueBiz.R;
import com.hulaVenueBiz.base.listener.OnOnceClickListener;
import com.hulaVenueBiz.base.mvp.BaseMvpActivity;
import com.hulaVenueBiz.ui.mine.contract.PwdSettingContract;
import com.hulaVenueBiz.ui.mine.presenter.PwdSettingPresenterImpl;

/**
 * @desc:         密码修改界面
 * @author:       Leo
 * @date:         2017/1/9
 */
public class PwdSettingActivity extends BaseMvpActivity<PwdSettingPresenterImpl> implements PwdSettingContract.View
{
    private EditText evPwdOld;
    private EditText evPwdNew;
    private EditText evPwdConfirm;
    private Button btnConfirm;
    private WidgeButton btnBack;

    @Override
    protected PwdSettingPresenterImpl createPresenterInstance() {
        return new PwdSettingPresenterImpl();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting_pwd;
    }

    @Override
    protected void setNavigation() {
        setNavigationBack(R.string.string_pwd_title);
    }

    @Override
    protected void onViewCreated() {
        btnConfirm = (Button) findViewById(R.id.btn_confirm);
        evPwdOld = (EditText) findViewById(R.id.ev_oldpwd);
        evPwdNew = (EditText) findViewById(R.id.ev_newpwd);
        evPwdConfirm = (EditText) findViewById(R.id.ev_pwdconfirm);
    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(btnConfirm);
        attachClickListener(btnBack);
    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == btnConfirm.getId()) {
            presenter.goToCheckPwd(getEditTextStr(evPwdOld), getEditTextStr(evPwdNew), getEditTextStr(evPwdConfirm));
        } else {
            onBackPressed();
        }
    }

    @Override
    public void changeSuccess() {
        CommonDialog commonDialog = newCommonDialog(getString(R.string.string_pwd_success));
        commonDialog.setSubmit(new OnOnceClickListener() {
            @Override
            public void onOnceClick(View v) {
                onBackPressed();
            }
        });
    }
}