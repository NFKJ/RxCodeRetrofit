package com.hulabusiness.base.mvp;

import android.view.KeyEvent;
import android.view.View;

import com.common.widget.navigation.WidgeButton;
import com.common.widget.webview.BaseWebView;
import com.hulabusiness.R;

 /**
  * @desc:         BaseH5Activity
  * @author:       Leo
  * @date:         2016/12/26
  */
public abstract class BaseH5Activity extends BaseMvpActivity
{
    private WidgeButton btnBack;
    private WidgeButton btnClose;
    public BaseWebView webView;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected void setNavigation()
    {
        btnBack = new WidgeButton(context);
        btnBack.setBackgroundResource(R.mipmap.icon_back_normal);
        btnClose = new WidgeButton(context);
        btnClose.setBackgroundResource(R.mipmap.icon_close_normal);
        getNavigationBar().setLeftMenus(new WidgeButton[]{btnBack, btnClose});
    }

    @Override
    protected void onViewCreated() {
        webView = (BaseWebView) findViewById(R.id.web_view);
    }

    @Override
    protected void doLogicFunc() {
        setNavigationListener();
    }

    private void setNavigationListener()
    {
        btnBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (null == webView) { return; }

                //添加网页的返回事件
                if (webView.canGoBack()) {
                    webView.goBack();//返回上一页面
                } else {
                    onBackPressed();
                }
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && null != webView && webView.canGoBack()) {
            webView.goBack();//返回上一页面
            return false;
        } else {
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }
}
