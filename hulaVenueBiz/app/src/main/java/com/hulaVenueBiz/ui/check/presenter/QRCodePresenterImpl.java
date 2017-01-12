package com.hulaVenueBiz.ui.check.presenter;
import com.common.retrofit.entity.result.VenueDetailBean;
import com.common.retrofit.methods.VenueMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.rxbus.RxBus;
import com.common.rxbus.postevent.KeyEvent;
import com.hulaVenueBiz.ui.check.contract.QRCodeContract;

/**
* Created by MVPHelper on 2016/12/30
*/
public class QRCodePresenterImpl extends QRCodeContract.Presenter {

    @Override
    public void reqCodeList(String code)
    {
        mView.showProgressingDialog();

        CommonSubscriber<VenueDetailBean> subscriber = new CommonSubscriber<>(new SubscriberListener<VenueDetailBean>() {
            @Override
            public void onNext(VenueDetailBean o) {
                mView.codeListData(o);
            }

            @Override
            public void onError(String e, int code) {
                mView.showToastMsg(e);
            }
        });

        VenueMethods.getInstance().getVenueInfo(code, subscriber);

        rxManager.add(subscriber);
    }

    /**
     * 使用RxBus通知主界面开启两种条形码录入方式
     */
    @Override
    public void startQR() {
        RxBus.getDefault().post(new KeyEvent(KeyEvent.CODE_START_MODE, KeyEvent.CODE_START_QR));
    }

    @Override
    public void startIN() {
        RxBus.getDefault().post(new KeyEvent(KeyEvent.CODE_START_MODE, KeyEvent.CODE_START_IN));
    }
}