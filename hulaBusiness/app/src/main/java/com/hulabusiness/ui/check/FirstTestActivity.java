package com.hulabusiness.ui.check;

import com.common.retrofit.entity.result.VenueDetail;
import com.common.retrofit.jsoncoverter.ApiException;
import com.common.retrofit.methods.VenueMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.utils.LogUtils;
import com.common.widget.loadingView.LoadingLayout;
import com.common.widget.navigation.NavigationBar;
import com.common.widget.navigation.WidgeButton;
import com.hulabusiness.R;
import com.hulabusiness.base.mvp.BaseMvpActivity;
import com.hulabusiness.base.mvp.BasePresenter;

import rx.Subscriber;

public class FirstTestActivity extends BaseMvpActivity
{
    private WidgeButton btnBack;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_first_test;
    }

    @Override
    protected void setNavigation() {
        getNavigationBar().setTitleType(NavigationBar.TitleType.Minor);
        btnBack = new WidgeButton(context, R.string.app_back);
        getNavigationBar().setLeftMenu(btnBack);
    }

    @Override
    protected void onViewCreated() {
        m_contentView.setStatus(LoadingLayout.Loading);
    }

    @Override
    protected void doLogicFunc() {

        Subscriber<VenueDetail> subscriberlogin = new CommonSubscriber<>(new SubscriberListener<VenueDetail>() {
            @Override
            public void onNext(VenueDetail bean)
            {
                if (bean != null) {
                    m_contentView.setStatus(LoadingLayout.Success);
                    LogUtils.e(bean.getAddress());
                } else {
                    m_contentView.setStatus(LoadingLayout.Empty);
                }
            }

            @Override
            public void onError(String e, int code) {
                if (code == ApiException.REQUEST_FILED) {
                    m_contentView.setStatus(LoadingLayout.No_Network);
                } else {
                    m_contentView.setStatus(LoadingLayout.Error);
                }
            }
        });

        VenueMethods.getInstance().getVenueInfo("", subscriberlogin);
        rxManager.add(subscriberlogin);
    }
}
