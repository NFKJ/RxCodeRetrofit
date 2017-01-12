package com.hulaVenueBiz.ui.common;

import com.hulaVenueBiz.R;
import com.hulaVenueBiz.base.mvp.BaseH5Activity;

public class RegisterActivity extends BaseH5Activity
{
    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void setNavigation() {
        super.setNavigation();
        getNavigationBar().setAppWidgeTitle(getString(R.string.string_regoster_title));
    }

    @Override
    protected void doLogicFunc() {
        super.doLogicFunc();
        webView.loadUrl("http://www.baidu.com");
    }
}
