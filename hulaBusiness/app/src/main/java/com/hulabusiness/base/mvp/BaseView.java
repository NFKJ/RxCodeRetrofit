package com.hulabusiness.base.mvp;

import android.app.Activity;


/**
 * @desc:         基类BaseView
 * @author:       Leo
 * @date:         2016/10/26
 */
public interface BaseView {

	Activity visitActivity();

	void showToastMsg(String msg);

	void showToastMsg(int resId);

	void showProgressingDialog();

	void dismissProgressDialog();
}
