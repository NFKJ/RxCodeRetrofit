package com.hulaVenueBiz.ui.common.presenter;

import com.common.base.DataCenter;
import com.common.okhttp.beans.HVVersionBean;
import com.common.okhttp.enums.Enum;
import com.common.okhttp.services.ICommonService;
import com.common.okhttp.zmoumall.http.ObjectCallback;
import com.common.utils.AppUtils;
import com.common.utils.EmptyUtils;
import com.common.utils.LogUtils;
import com.common.utils.SPUtils;
import com.hulaVenueBiz.ui.common.contract.SplashContract;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * @desc:         启动页逻辑
 * @author:       Leo
 * @date:         2016/12/23
 */
public class SplashPresenterImpl extends SplashContract.Presenter
{
    private void checkShare()
    {
        boolean isFirstIn;

        isFirstIn = SPUtils.getShareBoolean(context, DataCenter.ISFIRST);
        if (isFirstIn) {
            SPUtils.setShareBoolean(context, DataCenter.ISFIRST, false);
            mView.readyToGuide();
        } else {
            if (EmptyUtils.isEmpty(SPUtils.getShareString(context, DataCenter.TOKEN))) {
                mView.readyToLogin();
            } else {
                mView.readyToMain();
            }
        }

        LogUtils.e(isFirstIn);
        LogUtils.e(SPUtils.getShareString(context, DataCenter.TOKEN));
    }

    @Override
    public void checkIsFirstIn()
    {
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>()
                {

                    @Override
                    public void call(Long aLong)
                    {
                        checkShare();
                    }
                });
    }

    @Override
    public void checkVersion()
    {
        ICommonService.checkVersion(context, AppUtils.getVersionName(), AppUtils.getPacketChannelName(), Enum.EnumPlatform.Android, new ObjectCallback<HVVersionBean>() {
            @Override
            public void onSuccess(final HVVersionBean response, String message) {
                if(EmptyUtils.isNull(response)||response.getAction() == Enum.EnumVersion.None||response.getAction()==Enum.EnumVersion.Newest){
                    return;
                }
                switch (response.getAction()) {
                    case Force:// 强制升级
                        mView.updataForce(response.getUrl());
                        break;
                    case Prompt://提示升级
                        mView.updataRecommend(response.getUrl());
                        break;
                }
            }

            @Override
            public void onErrorT(int code, HVVersionBean response, String errorMessage) {
                mView.showToastMsg(errorMessage);
            }
        });
    }
}