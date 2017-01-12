package com.hulaVenueBiz.ui.mine.presenter;

import com.common.okhttp.services.IUserService;
import com.common.okhttp.zmoumall.http.ObjectCallback;
import com.common.utils.StringUtils;
import com.hulaVenueBiz.ui.mine.contract.NoticeSettingContract;

/**
  * @desc:         公告编辑逻辑实现
  * @author:       Leo
  * @date:         2017/1/10
  */
public class NoticeSettingPresenterImpl extends NoticeSettingContract.Presenter
{
    @Override
    public void goToChangeNotice(String noticeContent) {
        mView.showProgressingDialog();

        IUserService.modifyVenueNotice(context, StringUtils.nullToStr(noticeContent), new ObjectCallback<Object>() {
            @Override
            public void onSuccess(Object response, String message) {
                mView.dismissProgressDialog();
                mView.changeSuccess();
            }

            @Override
            public void onErrorT(int code, Object response, String errorMessage) {
                mView.dismissProgressDialog();
                mView.showToastMsg(errorMessage);
            }
        });
    }
}