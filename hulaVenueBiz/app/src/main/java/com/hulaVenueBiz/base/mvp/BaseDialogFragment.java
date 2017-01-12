package com.hulaVenueBiz.base.mvp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;

import com.common.image.ImageLoaderUtils;
import com.common.okhttp.base.OkHttpUtils;
import com.common.rxbus.RxManager;
import com.common.widget.toast.ToastManager;
import com.hulaVenueBiz.base.listener.OnOnceClickListener;

/**
 * 底部弹窗Fragment
 */
public abstract class BaseDialogFragment<P extends BasePresenter>
        extends DialogFragment implements BaseView {

    protected P presenter;
    protected Dialog dialog;
    protected Context context;
    protected RxManager rxManager;

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        rxManager = new RxManager();

        presenter = createPresenterInstance();
        if (null != presenter) {
            presenter.attachView(this, context);
        }
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
    public void showToastMsg(String msg) {
        ToastManager.showShortToast(msg);
    }

    @Override
    public void showProgressingDialog() {
    }

    @Override
    public void dismissProgressDialog() {
    }

    public void startActivity(Class<?> cls)
    {
        startActivity(cls, null);
    }

    public void dismissParent() {
        OkHttpUtils.getInstance().cancelTag(context);
        dismiss();
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle)
    {
        if (null == cls) {
            return;
        }

        Intent intent = new Intent();
        intent.setClass(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void attachClickListener(View view) {
        if (dialog != null) {
            if (view != null) {
                view.setOnClickListener(clickListener);
            }
        }
    }

    private OnOnceClickListener clickListener = new OnOnceClickListener() {
        @Override
        public void onOnceClick(View v) {
            onViewClicked(v);
        }
    };

    protected void onViewClicked(View view) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void clearMemory() {
        if (presenter != null) {
            presenter.detachView();
        }

        if (null != rxManager) {
            rxManager.clear();
        }

        // 清除图片缓存
        ImageLoaderUtils.cleanMemory(context);

        dismissParent();
    }

    @Override
    public void onDestroy() {
        clearMemory();
        super.onDestroy();
    }
}
