package com.common.widget.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Leo on 2016/8/18.
 */

/**
 * 设置Webview基础参数
 * @author Leo
 * Created at 2016/9/26
 */
public class WebViewManager extends WebView {

    public WebViewManager(Context context) {
        super(context);
        initSetting();
    }

    public WebViewManager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initSetting();
    }

    public WebViewManager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSetting();
    }

    public void initSetting()
    {
//        getSettings().setSupportMultipleWindows(true);
        setWebViewClient(new WebViewClient());
        setWebChromeClient(new WebChromeClient());

        getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        getSettings().setCacheMode(WebSettings.LOAD_DEFAULT); // 设置
        // 缓存模式
        // 开启 DOM storage API 功能
        getSettings().setDomStorageEnabled(true);
        // 开启 database storage API 功能
        getSettings().setDatabaseEnabled(true);
        getSettings().setAppCacheEnabled(true);

        getSettings().setJavaScriptEnabled(true);
        getSettings().setUseWideViewPort(true);
        getSettings().setDomStorageEnabled(true);//支持domstorage 若不加会加载不出某些html
        getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        getSettings().setLoadWithOverviewMode(true);
        // 每次加载url时
        getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        getSettings().setDisplayZoomControls(true);
        getSettings().setBuiltInZoomControls(false); // 设置显示缩放按钮
        getSettings().setAllowFileAccess(true); // 允许访问文件
        getSettings().setSupportZoom(true); // 支持缩放
        getSettings().setAppCacheEnabled(true);
        getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        clearCache(true);
    }
}
