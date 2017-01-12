package com.hulaVenueBiz.ui.check;

import android.app.Dialog;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;

import com.common.retrofit.entity.result.VenueDetailBean;
import com.common.utils.LogUtils;
import com.common.utils.ScreenUtils;
import com.hulaVenueBiz.R;
import com.hulaVenueBiz.base.mvp.BaseDialogFragment;
import com.hulaVenueBiz.ui.check.contract.QRCodeContract;
import com.hulaVenueBiz.ui.check.presenter.QRCodePresenterImpl;
import com.hulaVenueBiz.ui.common.LoginActivity;
import com.qrcode.core.QRCodeView;
import com.qrcode.core.ZXingView;

/**
  * @desc:         实现扫码弹窗
  * @author:       Leo
  * @date:         2017/1/3
  */
public class QRCodeDialogFragment extends BaseDialogFragment<QRCodePresenterImpl>
         implements QRCodeContract.View, QRCodeView.Delegate
{
    private ZXingView zvScan;
    private ImageView btnClose;
    private TextView tvInput;
    private Space mStatus;

    @Override
    protected QRCodePresenterImpl createPresenterInstance() {
        return new QRCodePresenterImpl();
    }

    @Override
    protected Dialog setDialog() {

        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(context, R.style.DialogFragment);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.dialog_qrcode);

        dialog.setCanceledOnTouchOutside(true); // 外部点击取消

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
        zvScan.setDelegate(this);
        zvScan.changeToScanBarcodeStyle();
        zvScan.startSpot();

        attachClickListener(btnClose);
        attachClickListener(tvInput);
    }

    @Override
    protected void setView() {
        setStatusBar();
    }

    // 设置状态栏高度
    protected void setStatusBar() {
        mStatus.setVisibility(View.VISIBLE);
        ViewGroup.LayoutParams layoutParams = mStatus.getLayoutParams();
        layoutParams.height = ScreenUtils.getStatusHeight(context);
        mStatus.setLayoutParams(layoutParams);
    }

    @Override
    protected void initView(Dialog dialog) {
        zvScan = (ZXingView) dialog.findViewById(R.id.zv_scan);
        btnClose = (ImageView) dialog.findViewById(R.id.iv_back);
        tvInput = (TextView) dialog.findViewById(R.id.tv_input);
        mStatus = (Space) dialog.findViewById(R.id.m_statusBar);
    }

    @Override
    protected void onViewClicked(View view)
    {
        dismiss();

        if (view.getId() == tvInput.getId()) {
            presenter.startIN();
        }
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        LogUtils.i("result:" + result);
        presenter.reqCodeList(result);
        dismiss();
        startActivity(LoginActivity.class);
        vibrate();
        zvScan.stopSpot();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        LogUtils.i("打开相机出错");
    }

    @Override
    public void onStart() {
        super.onStart();
        zvScan.startCamera();
        zvScan.showScanRect();
    }

    @Override
    public void onStop() {
        zvScan.stopCamera();
        super.onStop();
    }

    @Override
    public void dismiss() {
        zvScan.stopSpot();
        zvScan.onDestroy();
        super.dismiss();
    }

    @Override
    public void onDestroy() {
        dismiss();
        super.onDestroy();
    }

    // 震动
    private void vibrate() {
        Vibrator vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void codeListData(VenueDetailBean bean) {
        zvScan.startSpot();
        startActivity(LoginActivity.class);
    }
}
