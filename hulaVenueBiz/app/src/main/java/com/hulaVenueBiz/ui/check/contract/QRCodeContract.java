package com.hulaVenueBiz.ui.check.contract;

import com.common.retrofit.entity.result.VenueDetailBean;
import com.hulaVenueBiz.base.mvp.BasePresenter;
import com.hulaVenueBiz.base.mvp.BaseView;

/**
  * @desc:         实现输入条形码和扫描条形码功能
  * @author:       Leo
  * @date:         2016/12/30
  */
public interface QRCodeContract {

    interface View extends BaseView {
        void codeListData(VenueDetailBean bean);
    }

    abstract class Presenter extends BasePresenter<QRCodeContract.View>
    {
        // 根据code请求订单
        public abstract void reqCodeList(String code);

        // 开启扫描模式
        public abstract void startQR();

        // 开启输入模式
        public abstract void startIN();
    }
}