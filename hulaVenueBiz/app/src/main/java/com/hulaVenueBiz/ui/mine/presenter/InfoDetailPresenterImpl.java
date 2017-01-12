package com.hulaVenueBiz.ui.mine.presenter;

import android.view.View;

import com.common.okhttp.beans.HVVenueInfoBean;
import com.common.okhttp.services.IUserService;
import com.common.okhttp.zmoumall.http.ObjectCallback;
import com.common.utils.EmptyUtils;
import com.common.widget.loadingView.LoadingLayout;
import com.hulaVenueBiz.ui.mine.contract.InfoDetailContract;

/**
 * @desc:         获取场馆详情接口实现
 * @author:       Leo
 * @date:         2017/1/10
 */
public class InfoDetailPresenterImpl extends InfoDetailContract.Presenter {

    @Override
    public void getVenueInfo() {
        mView.statusLoading();

        IUserService.getVenueInfo(context, new ObjectCallback<HVVenueInfoBean>() {
            @Override
            public void onSuccess(HVVenueInfoBean response, String message)
            {
                if (EmptyUtils.isEmpty(response)) {
                    mView.statusEmpty();
                    return;
                }

                mView.statusContent();
                mView.setViewData(response);
            }

            @Override
            public void onErrorT(int code, HVVenueInfoBean response, String errorMessage) {
                if (code == ObjectCallback.NO_NET_WORK) {
                    mView.statusNoNetwork();
                } else {
                    mView.statusError();
                }

                mView.statusReTry(listener);

                mView.showToastMsg(errorMessage);
            }
        });
    }

    private LoadingLayout.OnReloadListener listener = new LoadingLayout.OnReloadListener() {
        @Override
        public void onReload(View v) {
            getVenueInfo();
        }
    };
}