package com.hulaVenueBiz.base.mvp;

 /**
  * @desc:         基类BaseView
  * @author:       Leo
  * @date:         2017/1/11
  */
public interface BaseView {

	void showToastMsg(String msg);

	void showProgressingDialog();

	void dismissProgressDialog();
}
