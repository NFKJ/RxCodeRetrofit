package com.hulaVenueBiz.ui.check;

import android.app.Dialog;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.retrofit.entity.result.VenueDetailBean;
import com.common.utils.KeyBoardUtils;
import com.common.utils.ScreenUtils;
import com.common.widget.editview.DeleteEditText;
import com.common.widget.navigation.NavigationBar;
import com.common.widget.navigation.WidgeButton;
import com.hulaVenueBiz.R;
import com.hulaVenueBiz.base.mvp.BaseDialogFragment;
import com.hulaVenueBiz.ui.check.contract.QRCodeContract;
import com.hulaVenueBiz.ui.check.presenter.QRCodePresenterImpl;

/**
  * @desc:         实现条码输入弹窗
  * @author:       Leo
  * @date:         2017/1/6
  */
public class INCodeDialogFragment extends BaseDialogFragment<QRCodePresenterImpl> implements QRCodeContract.View
{
    private RelativeLayout rvLayout;
    private DeleteEditText etCode;
    private WidgeButton btnClose;
    private WidgeButton btnQRCode;

    private NavigationBar m_navigationBar;
    private View m_statusBar;                  // 状态栏

    @Override
    protected QRCodePresenterImpl createPresenterInstance() {
        return new QRCodePresenterImpl();
    }

    @Override
    protected Dialog setDialog() {

        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(context, R.style.DialogFragment);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.dialog_inputcode);
        dialog.setCanceledOnTouchOutside(false); // 外部点击取消

        final Window window = dialog.getWindow();
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;  // 居中
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);

        return dialog;
    }

    @Override
    protected void setListener() {
        attachClickListener(btnClose);
    }

    @Override
    protected void initView(Dialog dialog)
    {
        rvLayout = (RelativeLayout) dialog.findViewById(R.id.rv_layout);
        etCode = (DeleteEditText) dialog.findViewById(R.id.et_code);
        m_navigationBar = (NavigationBar) dialog.findViewById(R.id.navigationBar);
        m_statusBar = dialog.findViewById(R.id.m_statusBar);
    }

    @Override
    protected void setView()
    {
        etCode.setFocusable(true);
        setStatusBar();
        setNavigation();

        attachClickListener(btnClose);
        attachClickListener(btnQRCode);
        attachClickListener(rvLayout);

        etCode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent)
            {
                if (i == EditorInfo.IME_ACTION_SEARCH)
                {
                    // 隐藏键盘
                    KeyBoardUtils.hideKeyboard(etCode, context);
                    // 请求条形码
                    presenter.reqCodeList(etCode.getText().toString());
                    return true;
                }
                return false;
            }
        });
    }

    // 设置状态栏高度
    private void setStatusBar() {
        m_statusBar.setVisibility(View.VISIBLE);
        ViewGroup.LayoutParams layoutParams = m_statusBar.getLayoutParams();
        layoutParams.height = ScreenUtils.getStatusHeight(context);
        m_statusBar.setLayoutParams(layoutParams);
        m_statusBar.setBackgroundResource(R.color.navigation_background_color);
    }

    private void setNavigation()
    {
        m_navigationBar.setAppWidgeTitle(getString(R.string.string_codein_title));
        btnClose = new WidgeButton(context);
        btnClose.setBackgroundResource(R.mipmap.icon_close);
        m_navigationBar.setLeftMenu(btnClose);

        btnQRCode = new WidgeButton(context);
        btnQRCode.setBackgroundResource(R.mipmap.icon_scan);
        m_navigationBar.setRightMenu(btnQRCode);
    }

    @Override
    protected void onViewClicked(View view)
    {
        if (view.getId() == btnQRCode.getId()) {
            dismissParent();
            presenter.startQR();
        } else if (view.getId() == rvLayout.getId()) {
            KeyBoardUtils.hideKeyboard(etCode, context);
        } else {
            dismissParent();
        }
    }

    @Override
    public void codeListData(VenueDetailBean bean) {
//        LogUtils.e(bean.getIsRecommend());
    }
}
