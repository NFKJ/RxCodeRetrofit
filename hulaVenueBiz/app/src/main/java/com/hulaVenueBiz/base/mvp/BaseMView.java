package com.hulaVenueBiz.base.mvp;

import com.common.widget.loadingView.LoadingLayout;

/**
 * @desc:         基类BaseView
 * @author:       Leo
 * @date:         2016/10/26
 */
public interface BaseMView extends BaseView {

	void statusLoading();

	void statusNoNetwork();

	void statusEmpty();

	void statusError();

	void statusContent();

	void statusReTry(LoadingLayout.OnReloadListener listener);
}
