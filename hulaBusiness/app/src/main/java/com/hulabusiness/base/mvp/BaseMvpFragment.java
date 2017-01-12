package com.hulabusiness.base.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.common.widget.loadingView.LoadingLayout;
import com.common.widget.navigation.NavigationBar;
import com.hulabusiness.R;
import com.hulabusiness.base.listener.OnOnceClickListener;

/**
 * @desc:         MVP模式
 * @author:       Leo
 * @date:         2016/10/26
 * @param <P>     扩展Presenter
 */
public abstract class BaseMvpFragment<P extends BasePresenter>
        extends Fragment implements BaseView {

    protected P presenter;
    protected Context context;

    private NavigationBar m_navigationBar;
    private LoadingLayout m_contentView;
    private LinearLayout m_root;

    /**
     * 缓存Fragment view
     * 避免多个Fragment切换时UI重绘
     */
    protected View contentView = null;

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getActivity();

        presenter = createPresenterInstance();
        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        context = getActivity();

        if (contentView == null) {
            contentView = inflater.inflate(R.layout.activity_base, container, false);
            initRootView();
            setNavigation();

            View view = inflater.inflate(getLayoutId(), container, false);
            m_contentView.addView(view);

            onViewCreated(view);
            doLogicFunc();
        }

        // 缓存的mView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个mView已经有parent的错误。
        ViewGroup parent = (ViewGroup) contentView.getParent();
        if (parent != null)
        {
            parent.removeView(contentView);
        }
        return contentView;
    }

    protected void setNavigation() {
        m_navigationBar.setVisibility(View.GONE);
    }

    private void initRootView() {
        m_root = (LinearLayout) contentView.findViewById(R.id.root);
        m_contentView = (LoadingLayout) contentView.findViewById(R.id.appContent);
        m_navigationBar = (NavigationBar) contentView.findViewById(R.id.navigationBar);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    protected abstract P createPresenterInstance();
    protected abstract int getLayoutId();
    // 初始化界面控件
    protected abstract void onViewCreated(View view);
    // 执行逻辑操作
    protected abstract void doLogicFunc();

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
        intent.setClass(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void attachClickListener(int id) {
        if (contentView != null) {
            View view = contentView.findViewById(id);
            if (view != null) {
                view.setOnClickListener(clickListener);
            }
        }
    }

    protected void attachClickListener(View view) {
        if (contentView != null) {
            if (view != null) {
                view.setOnClickListener(vclickListener);
            }
        }
    }

    private OnOnceClickListener vclickListener = new OnOnceClickListener() {
        @Override
        public void onOnceClick(View v) {
            onViewClicked(v);
        }
    };

    protected void onViewClicked(View view) {
    }

    private OnOnceClickListener clickListener = new OnOnceClickListener() {
        @Override
        public void onOnceClick(View v) {
            onViewClicked(v, v.getId());
        }
    };

    protected void onViewClicked(View view, int id) {
    }

    public NavigationBar getNavigationBar() {
        return m_navigationBar;
    }

    @Override
    public void onDestroy() {
        if (presenter != null) {
            presenter.detachView();
        }
        super.onDestroy();
    }
}
