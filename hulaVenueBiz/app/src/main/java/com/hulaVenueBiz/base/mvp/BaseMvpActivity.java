package com.hulaVenueBiz.base.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.image.ImageLoaderUtils;
import com.common.okhttp.base.OkHttpUtils;
import com.common.rxbus.RxManager;
import com.common.utils.ActivityStack;
import com.common.utils.EmptyUtils;
import com.common.utils.KeyBoardUtils;
import com.common.utils.ScreenUtils;
import com.common.utils.StringUtils;
import com.common.utils.ViewUtils;
import com.common.widget.dialog.CommonDialog;
import com.common.widget.loadingView.LoadingLayout;
import com.common.widget.navigation.NavigationBar;
import com.common.widget.navigation.WidgeButton;
import com.common.widget.progress.LoadDialog;
import com.common.widget.toast.ToastManager;
import com.hulaVenueBiz.R;
import com.hulaVenueBiz.base.listener.OnOnceClickListener;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * @desc:         MVP模式
 * @author:       Leo
 * @date:         2016/10/26
 * @param <P>     扩展Presenter
 */
public abstract class BaseMvpActivity<P extends BasePresenter>
        extends AutoLayoutActivity implements BaseMView {

    protected RxManager rxManager;
    protected Context context;
    protected P presenter;

    protected LayoutInflater m_inflater;
    protected LoadingLayout m_contentView;
    protected LinearLayout m_root;
    private NavigationBar m_navigationBar;
    protected View m_statusBar;                  // 状态栏

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

        // 隐藏状态栏
        ScreenUtils.translateStatusBar(this);
        setTheme(R.style.TranslucentTheme);

        initRootView();
        setStatusBar();
        setNavigation();
        presenter = createPresenterInstance();
        if (null != presenter) {
            presenter.attachView(this, context);
        }

        View view = m_inflater.inflate(getLayoutId(), m_root, false);
        m_contentView.addView(view);

        // 初始化对话框
        CommonDialog.newInstance(context);
        ActivityStack.getInstance().addActivity(this);
        onViewCreated();
        doLogicFunc();
    }

    /**  设置状态栏高度  */
    protected void setStatusBar() {
        m_statusBar.setVisibility(View.VISIBLE);
        ViewGroup.LayoutParams layoutParams = m_statusBar.getLayoutParams();
        layoutParams.height = ScreenUtils.getStatusHeight(context);
        m_statusBar.setLayoutParams(layoutParams);
        m_statusBar.setBackgroundResource(R.color.navigation_background_color);
    }

    /**  设置titleBar */
    protected void setNavigation() {
        m_navigationBar.setVisibility(View.GONE);
    }

    /**  设置返回键  */
    protected void setNavigationBack() {
        m_navigationBar.setVisibility(View.VISIBLE);
        WidgeButton btnBack = new WidgeButton(context);
        btnBack.setBackgroundResource(R.mipmap.icon_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        getNavigationBar().setLeftMenu(btnBack);
    }

    /**
     * 设置返回键
     * @param titleRes title名
     */
    protected void setNavigationBack(int titleRes) {
        setNavigationBack();
        m_navigationBar.setAppWidgeTitle(getResources().getString(titleRes));
    }

    private void initRootView() {
        m_inflater = LayoutInflater.from(this);
        m_root = (LinearLayout) this.findViewById(R.id.root);
        m_contentView = (LoadingLayout) this.findViewById(R.id.appContent);
        m_navigationBar = (NavigationBar) this.findViewById(R.id.navigationBar);
        m_statusBar = this.findViewById(R.id.m_statusBar);
    }

    /**  初始化Presenter在setContentView之前  */
    protected abstract P createPresenterInstance();

    protected abstract int getLayoutId();

    /**  初始化界面控件  */
    protected abstract void onViewCreated();

    /**  执行逻辑操作  */
    protected abstract void doLogicFunc();

    protected Activity getActivity() {
        return this;
    }

    public void startActivity(Class<?> cls)
    {
        if (null == cls) {  return;  }

        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

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

    /**
     * 创建通用对话框
     * @param title             标题
     * @param text              提示文本
     * @param outSideCancel     点击窗外是否取消<默认<true>
     * @param isCancelGone      是否隐藏取消按钮<默认false>
     * @return
     */
    protected CommonDialog newCommonDialog(String title, String text, Boolean outSideCancel, Boolean isCancelGone)
    {
        CommonDialog dialog = CommonDialog.newInstance(context);
        dialog.setTitle(title);
        dialog.setText(text);
        dialog.setCanceledOnTouchOutside(outSideCancel);
        if(isCancelGone){
            dialog.setCancelGone();
        }
        dialog.show(getSupportFragmentManager(), "CommonDialog");
        return dialog;
    }

    protected CommonDialog newCommonDialog(String text) {
        return newCommonDialog(getString(R.string.string_dialog_title), text, true, false);
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

    @Override
    public void statusReTry(LoadingLayout.OnReloadListener listener)
    {
        if (EmptyUtils.isNull(listener)) {   return;   }

        m_contentView.setOnReloadListener(listener);
    }

    /**
     * 界面返回时主动释放内存
     */
    @Override
    public void onBackPressed() {
        KeyBoardUtils.hideKeyBoard(m_root);
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        clearMemory();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void clearMemory()
    {
        if (presenter != null) {
            presenter.detachView();
        }

        if (null != rxManager) {
            rxManager.clear();
        }

        // 清除图片缓存
        ImageLoaderUtils.cleanMemory(context);

        // 清除栈
        ActivityStack.getInstance().finishActivity(this);
        // 结束网络请求线程
        OkHttpUtils.getInstance().cancelTag(context);

        // 清除界面所有的view
        ViewUtils.clearAllChildViews(this);
    }

    /**
     * 获取编辑框文本
     * @param editText
     * @return
     */
    protected String getEditTextStr(EditText editText) {
        if (EmptyUtils.isNotNull(editText)) {
            return editText.getText().toString();
        }
        return "";
    }

    /**
     * 为控件添加单次点击事件
     * @param view
     */
    protected void attachClickListener(View view) {
        if (view != null) {
            view.setOnClickListener(clickListener);
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
    protected void onDestroy() {
        clearMemory();
        super.onDestroy();
    }

    /**
     * 点击空白区域隐藏键盘
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (KeyBoardUtils.isShouldHideKeyboard(v, ev)) {
                KeyBoardUtils.hideKeyBoard(v);
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}