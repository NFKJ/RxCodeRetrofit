package com.hulaVenueBiz.ui.mine.contract;

import com.common.okhttp.beans.HVVenueInfoBean;
import com.hulaVenueBiz.base.mvp.BasePresenter;
import com.hulaVenueBiz.base.mvp.BaseMView;

/**
  * @desc:         获取场馆详情
  * @author:       Leo
  * @date:         2017/1/10
  */
public interface InfoDetailContract
{
    interface View extends BaseMView {
        void setViewData(HVVenueInfoBean bean);
    }

    abstract class Presenter extends BasePresenter<InfoDetailContract.View>
    {
        /**
         * 获取场馆详情
         */
        public abstract void getVenueInfo();
    }
}
