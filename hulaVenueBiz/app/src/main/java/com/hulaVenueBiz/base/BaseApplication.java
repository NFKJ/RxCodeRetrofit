package com.hulaVenueBiz.base;

import com.common.base.DataCenter;
import com.common.utils.ContextUtils;
import com.common.utils.EmptyUtils;
import com.common.widget.loadingView.LoadingLayout;
import com.common.widget.toast.ToastManager;
import com.hulaVenueBiz.R;
import com.hulaVenueBiz.push.GeTuiIntentService;
import com.hulaVenueBiz.push.GeTuiService;
import com.igexin.sdk.PushManager;

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

        // com.getui.demo.DemoPushService 为第三方自定义推送服务
        PushManager.getInstance().initialize(getAppContext(), GeTuiService.class);
        PushManager.getInstance().registerPushIntentService(getAppContext(), GeTuiIntentService.class);
        initLoadingView();
        initDataCenter();
    }

    // 初始化本地数据存储
    private void initDataCenter() {
        if (EmptyUtils.isNull(DataCenter.getInstance())) {
            DataCenter.initDataCenter();
        }
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
