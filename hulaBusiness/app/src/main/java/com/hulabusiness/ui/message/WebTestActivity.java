package com.hulabusiness.ui.message;

import com.hulabusiness.R;
import com.hulabusiness.base.mvp.BaseH5Activity;

public class WebTestActivity extends BaseH5Activity
{
    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_test;
    }

    @Override
    protected void doLogicFunc() {
        super.doLogicFunc();
        webView.loadUrl("http://www.baidu.com");
    }
}
