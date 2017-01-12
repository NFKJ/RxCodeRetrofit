package com.hulaVenueBiz.ui.common;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.hulaVenueBiz.R;
import com.hulaVenueBiz.base.mvp.BaseMvpActivity;
import com.hulaVenueBiz.ui.common.contract.LoginContract;
import com.hulaVenueBiz.ui.common.presenter.LoginPresenterImpl;

 /**
  * @desc:         登录界面
  * @author:       Leo
  * @date:         2017/1/6
  */
public class LoginActivity extends BaseMvpActivity<LoginPresenterImpl> implements LoginContract.View
{
    private Button btnLogin;
    private RelativeLayout btnRegister;
    private EditText evAccount;
    private EditText evPwd;

    @Override
    protected LoginPresenterImpl createPresenterInstance() {
        return new LoginPresenterImpl();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void setNavigation() {
        super.setNavigation();
        m_statusBar.setVisibility(View.GONE);
    }

    @Override
    protected void onViewCreated() {
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRegister = (RelativeLayout) findViewById(R.id.btn_register);
        evAccount = (EditText) findViewById(R.id.ev_account);
        evPwd = (EditText) findViewById(R.id.ev_passward);
    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(btnLogin);
        attachClickListener(btnRegister);
    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == btnLogin.getId()) {
            loginSuccess();
//            presenter.goToLogin(getEditTextStr(evAccount), getEditTextStr(evPwd));
//            presenter.goToLogin("13000000000", "123456");
        } else if (view.getId() == btnRegister.getId()) {
            startActivity(RegisterActivity.class);
        }
    }

    @Override
    public void loginSuccess() {
        startActivity(MainActivity.class);
        finish();
    }
}
