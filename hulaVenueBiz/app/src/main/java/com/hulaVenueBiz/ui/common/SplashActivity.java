package com.hulaVenueBiz.ui.common;

import android.view.View;

import com.common.utils.AppUtils;
import com.common.widget.dialog.CommonDialog;
import com.hulaVenueBiz.R;
import com.hulaVenueBiz.base.listener.OnOnceClickListener;
import com.hulaVenueBiz.base.mvp.BaseMvpActivity;
import com.hulaVenueBiz.ui.common.contract.SplashContract;
import com.hulaVenueBiz.ui.common.presenter.SplashPresenterImpl;

 /**
  * @desc:         启动页界面
  * @author:       Leo
  * @date:         2016/12/23
  */
public class SplashActivity extends BaseMvpActivity<SplashPresenterImpl> implements SplashContract.View
{
    @Override
    protected SplashPresenterImpl createPresenterInstance() {
        return new SplashPresenterImpl();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void setNavigation() {
        super.setNavigation();
        m_statusBar.setVisibility(View.GONE);
    }

    @Override
    protected void onViewCreated() {
    }

    @Override
    protected void doLogicFunc() {
        presenter.checkVersion();
    }

    @Override
    public void readyToMain() {
        startActivity(LoginActivity.class);
        finish();
    }

    @Override
    public void readyToLogin() {
        startActivity(LoginActivity.class);
        finish();
    }

    @Override
    public void readyToGuide() {
//        startActivity(GuideActivity.class);
        finish();
    }

    @Override
    public void updataForce(final String url) {
        final CommonDialog dialog = newCommonDialog(getString(R.string.string_update_title),
                getString(R.string.string_update_content), false, true);

        dialog.setSubmit(new OnOnceClickListener() {
            @Override
            public void onOnceClick(View v) {
                dialog.dismiss();
                startActivity(AppUtils.getOpenWebClientIntent(url));
            }
        });
    }

    @Override
    public void updataRecommend(final String url) {
        final CommonDialog dialog = newCommonDialog(getString(R.string.string_update_title),
                getString(R.string.string_update_content), false, false);

        dialog.setCancel(new OnOnceClickListener() {
            @Override
            public void onOnceClick(View v) {
                dialog.dismiss();
                presenter.checkIsFirstIn();
            }
        });

        dialog.setSubmit(new OnOnceClickListener() {
            @Override
            public void onOnceClick(View v) {
                dialog.dismiss();
                presenter.checkIsFirstIn();
                startActivity(AppUtils.getOpenWebClientIntent(url));
            }
        });
    }
}
