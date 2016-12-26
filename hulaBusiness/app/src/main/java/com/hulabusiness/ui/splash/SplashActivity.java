package com.hulabusiness.ui.splash;

import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hulabusiness.R;
import com.hulabusiness.base.mvp.BaseMvpActivity;
import com.hulabusiness.ui.MainActivity;
import com.hulabusiness.ui.splash.contract.SplashContract;
import com.hulabusiness.ui.splash.presenter.SplashPresenterImpl;

 /**
  * @desc:         启动页界面
  * @author:       Leo
  * @date:         2016/12/23
  */
public class SplashActivity extends BaseMvpActivity<SplashPresenterImpl> implements SplashContract.View
{
    private ImageView ivLogo;
    private ImageView ivBg;
    private LinearLayout tvName;

    @Override
    protected SplashPresenterImpl createPresenterInstance() {
        return new SplashPresenterImpl();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onViewCreated() {
        ivLogo = (ImageView) findViewById(R.id.iv_logo);
        ivBg = (ImageView) findViewById(R.id.iv_bg);
        tvName = (LinearLayout) findViewById(R.id.tv_name);
    }

    @Override
    protected void doLogicFunc() {
        presenter.checkIsFirstIn(context, ivBg);
    }

    @Override
    public void readyToMain() {
        startActivity(MainActivity.class);
        finish();
    }

    @Override
    public void readyToGuide() {
//        startActivity(GuideActivity.class);
        finish();
    }
}
