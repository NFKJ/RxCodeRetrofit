package com.hulabusiness.base.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.common.widget.RevealLayout;
import com.common.widget.navigation.NavigationBar;
import com.hulabusiness.R;
import com.hulabusiness.base.listener.OnOnceClickListener;

/**
 * @desc:         MVP模式
 * @author:       Leo
 * @date:         2016/10/26
 * @param <P>     扩展Presenter
 */
public abstract class BaseMvpActivity<P extends BasePresenter>
        extends FragmentActivity implements BaseView {

    protected Context context;
    protected P presenter;

    protected LayoutInflater m_inflater;
    protected FrameLayout m_contentView;
    protected RevealLayout m_root;
    private NavigationBar m_navigationBar;

    public NavigationBar getNavigationBar() {
        return m_navigationBar;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        context = this;
        super.setContentView(R.layout.activity_base);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initRootView();
        setNavigation();
        presenter = createPresenterInstance();
        if (null != presenter) {
            presenter.attachView(this);
        }

        View view = m_inflater.inflate(getLayoutId(), m_root, false);
        m_contentView.addView(view);

        onViewCreated();
        doLogicFunc();
    }

    protected void setNavigation() {
        m_navigationBar.setVisibility(View.GONE);
    }

    private void initRootView() {
        m_inflater = LayoutInflater.from(this);
        m_root = (RevealLayout) this.findViewById(R.id.root);
        m_contentView = (FrameLayout) this.findViewById(R.id.appContent);
        m_navigationBar = (NavigationBar) this.findViewById(R.id.navigationBar);
    }

    //初始化Presenter在setContentView之前
    protected abstract P createPresenterInstance();

    protected abstract int getLayoutId();
    // 初始化界面控件
    protected abstract void onViewCreated();
    // 执行逻辑操作
    protected abstract void doLogicFunc();

    @Override
    public Activity visitActivity() {
        return getActivity();
    }

    protected Activity getActivity() {
        return this;
    }

    public void startActivity(Class<?> cls)
    {
        startActivity(cls, null);
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
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
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

    /**
     * Invoke the method after you have implemented method {@link BaseMvpActivity#onViewClicked(View, int)}
     *
     * @param id id of View
     */
    protected void attachClickListener(int id) {
        View view = findViewById(id);
        if (view != null) {
            view.setOnClickListener(clickListener);
        }
    }

    private OnOnceClickListener clickListener = new OnOnceClickListener() {
        @Override
        public void onOnceClick(View v) {
            onViewClicked(v, v.getId());
        }
    };

    /**
     * Clicked views' implementation
     *
     * @param view which view has clicked
     * @param id   id of View
     */
    protected void onViewClicked(View view, int id) {

    }

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.detachView();
        }
        super.onDestroy();
    }
}