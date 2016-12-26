package com.hulabusiness.base.mvp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * 底部弹窗Fragment
 */
public abstract class BaseDialogFragment<P extends BasePresenter>
        extends DialogFragment implements BaseView {

    protected P presenter;
    protected Dialog dialog;
    protected Context context;

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();

        presenter = createPresenterInstance();

    }

    protected abstract P createPresenterInstance();

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        dialog = setDialog();
        initView(dialog);
        setView();
        setListener();
        return dialog;
    }

    /**
     * 设置自定义对话框
     */
    protected abstract Dialog setDialog();

    protected abstract void setListener();

    protected abstract void setView();

    protected abstract void initView(Dialog dialog);

    @Override
    public Activity visitActivity() {
        return getActivity();
    }

    @Override
    public void showToastMsg(String msg) {
//        ToastManager.shortShow(msg);
    }

    @Override
    public void showToastMsg(int resId) {
//        ToastManager.shortShow(resId);
    }

    @Override
    public void showProgressingDialog() {

    }

    @Override
    public void dismissProgressDialog() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        if (presenter != null) {
//            presenter.detachView();
        }
        super.onDestroy();
    }
}
