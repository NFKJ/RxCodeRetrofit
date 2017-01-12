package com.hulabusiness.base.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.common.rxbus.RxManager;
import com.common.utils.ActivityStack;
import com.common.utils.KeyBoardUtils;
import com.common.utils.ViewUtils;
import com.common.widget.loadingView.LoadingLayout;
import com.common.widget.navigation.NavigationBar;
import com.common.widget.toast.ToastManager;
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

    protected RxManager rxManager;
    protected Context context;
    protected P presenter;

    protected LayoutInflater m_inflater;
    protected LoadingLayout m_contentView;
    protected LinearLayout m_root;
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
        rxManager = new RxManager();

        initRootView();
        setNavigation();
        presenter = createPresenterInstance();
        if (null != presenter) {
            presenter.attachView(this);
        }

        View view = m_inflater.inflate(getLayoutId(), m_root, false);
        m_contentView.addView(view);

        ActivityStack.getInstance().addActivity(this);
        onViewCreated();
        doLogicFunc();
    }

    protected void setNavigation() {
        m_navigationBar.setVisibility(View.GONE);
    }

    private void initRootView() {
        m_inflater = LayoutInflater.from(this);
        m_root = (LinearLayout) this.findViewById(R.id.root);
        m_contentView = (LoadingLayout) this.findViewById(R.id.appContent);
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
        ToastManager.showShortToast(msg);
    }

    @Override
    public void showToastMsg(int resId) {
        ToastManager.showShortToast(resId);
    }

    @Override
    public void showProgressingDialog() {
    }

    @Override
    public void dismissProgressDialog() {
    }

    /**
     * 界面返回时主动释放内存
     */
    @Override
    public void onBackPressed() {
        KeyBoardUtils.hideKeyBoard(m_root);
        clearMemory();
        super.onBackPressed();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        onBackPressed();
        return super.onKeyDown(keyCode, event);
    }

    private void clearMemory() {
        if (presenter != null) {
            presenter.detachView();
        }

        if (null != rxManager) {
            rxManager.clear();
        }
        ActivityStack.getInstance().finishActivity(this);
        ViewUtils.clearAllChildViews(this);
    }

    protected void attachClickListener(int id) {
        View view = findViewById(id);
        if (view != null) {
            view.setOnClickListener(clickListener);
        }
    }

    protected void attachClickListener(View view) {
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

    protected void onViewClicked(View view, int id) {
    }

    @Override
    protected void onDestroy() {
        clearMemory();
        super.onDestroy();
    }
}