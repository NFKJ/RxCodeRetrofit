package com.hulaVenueBiz.base.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.image.ImageLoaderUtils;
import com.common.rxbus.RxManager;
import com.common.utils.EmptyUtils;
import com.common.utils.StringUtils;
import com.common.utils.ViewUtils;
import com.common.widget.dialog.CommonDialog;
import com.common.widget.loadingView.LoadingLayout;
import com.common.widget.navigation.NavigationBar;
import com.common.widget.progress.LoadDialog;
import com.common.widget.toast.ToastManager;
import com.hulaVenueBiz.R;
import com.hulaVenueBiz.base.listener.OnOnceClickListener;

/**
 * @desc:         MVP模式
 * @author:       Leo
 * @date:         2016/10/26
 * @param <P>     扩展Presenter
 */
public abstract class BaseMvpFragment<P extends BasePresenter>
        extends Fragment implements BaseMView {

    protected P presenter;
    protected Context context;
    protected RxManager rxManager;

    private NavigationBar m_navigationBar;
    private LoadingLayout m_contentView;
    private LinearLayout m_root;
    private View m_statusBar;                  // 状态栏

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
        rxManager = new RxManager();

        presenter = createPresenterInstance();
        if (presenter != null) {
            presenter.attachView(this, context);
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

            // 初始化对话框
            CommonDialog.newInstance(context);

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
        m_statusBar = contentView.findViewById(R.id.m_statusBar);
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
    public void showToastMsg(String msg) {
        ToastManager.showShortToast(msg);
    }

    protected LoadDialog loadDialog;
    protected String loadText;//加载中提示文字
    protected boolean loadCancelable=true;

    @Override
    public void showProgressingDialog() {
        loadText=getResources().getString(R.string.string_loadText);
        loadDialog = new LoadDialog(context,loadText, loadCancelable);
        loadDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        loadDialog.dismiss();
    }

    @Override
    public void statusError() {
        m_contentView.setStatus(LoadingLayout.Status.Error);
    }

    @Override
    public void statusLoading() {
        m_contentView.setStatus(LoadingLayout.Status.Loading);
    }

    @Override
    public void statusNoNetwork() {
        m_contentView.setStatus(LoadingLayout.Status.No_Network);
    }

    @Override
    public void statusEmpty() {
        m_contentView.setStatus(LoadingLayout.Status.Empty);
    }

    @Override
    public void statusContent() {
        m_contentView.setStatus(LoadingLayout.Status.Success);
    }

    @Override
    public void statusReTry(LoadingLayout.OnReloadListener listener)
    {
        if (EmptyUtils.isNull(listener)) {   return;   }

        m_contentView.setOnReloadListener(listener);
    }

    /**
     * 填充文本兼容空对象
     * @param textView    textview
     * @param content     content
     */
    public void setTextView(TextView textView, String content)
    {
        if (EmptyUtils.isNull(textView)) {  return; }

        textView.setText(StringUtils.nullToStr(content));
    }

    /**
     * 填充图片兼容空对象
     * @param imageview    imageview
     * @param url          content
     */
    public void setImageView(ImageView imageview, String url)
    {
        if (EmptyUtils.isNull(imageview)) {  return; }

        ImageLoaderUtils.display(imageview, StringUtils.nullToStr(url));
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

    protected void attachClickListener(View view) {
        if (contentView != null) {
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

    public NavigationBar getNavigationBar() {
        return m_navigationBar;
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

        ViewUtils.clearAllChildViews(getActivity());
    }

    @Override
    public void onDestroy() {
        clearMemory();
        super.onDestroy();
    }
}
