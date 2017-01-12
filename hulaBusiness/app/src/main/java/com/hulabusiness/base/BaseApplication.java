package com.hulabusiness.base;

import com.common.utils.ContextUtils;
import com.common.widget.loadingView.LoadingLayout;
import com.common.widget.toast.ToastManager;
import com.hulabusiness.R;

/**
  * @desc:         BaseApplication
  * @author:       Leo
  * @date:         2016/12/26
  */
public class BaseApplication extends ContextUtils
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        initApp();
    }

    private void initApp() {
        ToastManager.init(getAppContext());

        initLoadingView();
    }

    // 初始化页面预加载View
    private void initLoadingView() {
        LoadingLayout.getConfig()
                .setErrorText("出错啦~请稍后重试！")
                .setEmptyText("抱歉，暂无数据")
                .setNoNetworkText("无网络连接，请检查您的网络···")
                .setErrorImage(R.mipmap.define_error)
                .setEmptyImage(R.mipmap.define_empty)
                .setNoNetworkImage(R.mipmap.define_nonetwork)
                .setAllTipTextColor(R.color.gray)
                .setAllTipTextSize(14)
                .setReloadButtonText("点我重试哦")
                .setReloadButtonTextSize(14)
                .setReloadButtonTextColor(R.color.gray)
                .setReloadButtonWidthAndHeight(150,40)
                .setAllPageBackgroundColor(R.color.view_background_color);
//        .setLoadingPageLayout(R.layout.define_loading_page)
    }
}
