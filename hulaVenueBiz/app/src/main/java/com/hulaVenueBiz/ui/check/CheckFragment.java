package com.hulaVenueBiz.ui.check;

import android.Manifest;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.common.base.Constants;
import com.common.permission.AfterPermissionGranted;
import com.common.permission.EasyPermissions;
import com.common.rxbus.RxBus;
import com.common.rxbus.postevent.KeyEvent;
import com.common.utils.StringUtils;
import com.common.widget.navigation.NavigationBar;
import com.hulaVenueBiz.R;
import com.hulaVenueBiz.base.mvp.BaseMvpActivity;
import com.hulaVenueBiz.base.mvp.BaseMvpFragment;
import com.hulaVenueBiz.ui.check.presenter.QRCodePresenterImpl;

import java.util.List;

import rx.functions.Action1;

 /**
  * @desc:         验票模块主页
  * @author:       Leo
  * @date:         2017/1/10
  */
public class CheckFragment extends BaseMvpFragment<QRCodePresenterImpl> implements EasyPermissions.PermissionCallbacks
{
    private Button btnQRCode;
    private TextView tvINCode;

    @Override
    protected QRCodePresenterImpl createPresenterInstance() {
        return new QRCodePresenterImpl();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_check;
    }

    @Override
    protected void setNavigation() {
        getNavigationBar().setAppWidgeTitle("验票");
    }

    @Override
    protected void onViewCreated(View view) {
        btnQRCode = (Button) view.findViewById(R.id.btn_qrcode);
        tvINCode = (TextView) view.findViewById(R.id.tv_incode);
    }

    @Override
    protected void doLogicFunc() {
        setListener();
        getRxBus();
    }

    private void getRxBus() {
        rxManager.add(RxBus.getDefault().toObservable(KeyEvent.class)
                .subscribe(new Action1<KeyEvent>() {
                    @Override
                    public void call(KeyEvent keyEvent) {
                        if (StringUtils.isNotNullAndEqual(keyEvent.getKey(), KeyEvent.CODE_START_MODE)) {
                            if (StringUtils.isNotNullAndEqual(keyEvent.getValue().toString(), KeyEvent.CODE_START_QR)) {
                                startQRCode();
                            } else if (StringUtils.isNotNullAndEqual(keyEvent.getValue().toString(), KeyEvent.CODE_START_IN)) {
                                startINCode();
                            }
                        }
                    }
                }));
    }

    private void setListener() {
        attachClickListener(btnQRCode);
        attachClickListener(tvINCode);
    }

    // 开启条形码扫描
    private void startQRCode() {
        QRCodeDialogFragment qrCodeFragment = new QRCodeDialogFragment();
        qrCodeFragment.show(((BaseMvpActivity) context).getSupportFragmentManager(),"QRCodeDialogFragment");
    }

    // 手动输入条形码
    private void startINCode() {
        INCodeDialogFragment inCodeFragment = new INCodeDialogFragment();
        inCodeFragment.show(((BaseMvpActivity) context).getSupportFragmentManager(),"INCodeDialogFragment");
    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == btnQRCode.getId()) {
            requestQRCodePermission();
        } else if (view.getId() == tvINCode.getId()){
            startINCode();
        }
    }

    @Override
    public void onPermissionsGranted(List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(List<String> perms) {
        EasyPermissions.startAppSettings(context);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(Constants.REQUECT_CODE_CAMERA)
    private void requestQRCodePermission() {
        if (!EasyPermissions.hasPermissions(context, Manifest.permission.CAMERA)) {
            // Ask for one permission
            EasyPermissions.requestPermissions(this, "扫描二维码需要打开相机和闪光灯的权限",
                    Constants.REQUECT_CODE_CAMERA, Manifest.permission.CAMERA);
        } else {
            startQRCode();
        }
    }
}