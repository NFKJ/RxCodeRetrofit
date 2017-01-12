package com.hulaVenueBiz.ui.mine;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.base.DataCenter;
import com.common.utils.AppUtils;
import com.hulaVenueBiz.R;
import com.hulaVenueBiz.base.mvp.BaseMvpFragment;
import com.hulaVenueBiz.ui.common.LoginActivity;
import com.hulaVenueBiz.ui.mine.contract.MineContract;
import com.hulaVenueBiz.ui.mine.presenter.MinePresenterImpl;

/**
  * @desc:         我的模块主页
  * @author:       Leo
  * @date:         2017/1/10
  */
public class MineFragment extends BaseMvpFragment<MinePresenterImpl> implements MineContract.View
{
    private TextView tvName;
    private ImageView ivLogo;
    private TextView tvSetpwd;
    private TextView tvInfo;
    private TextView tvDescount;
    private TextView tvNotice;
    private TextView tvCall;
    private TextView tvLogout;

    @Override
    protected MinePresenterImpl createPresenterInstance() {
        return new MinePresenterImpl();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void onViewCreated(View view) {
        tvName = (TextView) view.findViewById(R.id.tv_name);
        ivLogo = (ImageView) view.findViewById(R.id.iv_logo);
        tvSetpwd = (TextView) view.findViewById(R.id.tv_setpwd);
        tvInfo = (TextView) view.findViewById(R.id.tv_info);
        tvDescount = (TextView) view.findViewById(R.id.tv_discount);
        tvNotice = (TextView) view.findViewById(R.id.tv_notice);
        tvCall = (TextView) view.findViewById(R.id.tv_call);
        tvLogout = (TextView) view.findViewById(R.id.tv_logout);
    }

    @Override
    protected void doLogicFunc()
    {
        attachClickListener(tvSetpwd);
        attachClickListener(tvInfo);
        attachClickListener(tvDescount);
        attachClickListener(tvNotice);
        attachClickListener(tvCall);
        attachClickListener(tvLogout);

        setTextView(tvName, DataCenter.getInstance().getUserName());
    }

    @Override
    protected void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_setpwd:
                startActivity(PwdSettingActivity.class);
                break;
            case R.id.tv_info:
                startActivity(MineDetailActivity.class);
                break;
            case R.id.tv_discount:
                startActivity(DiscountSettingActivity.class);
                break;
            case R.id.tv_notice:
                startActivity(NoticeSettingActivity.class);
                break;
            case R.id.tv_call:
                startActivity(AppUtils.gettakeCallIntent());
                break;
            case R.id.tv_logout:
                presenter.goToLogout();
                break;
        }
    }

    @Override
    public void logoutSuccess() {
        startActivity(LoginActivity.class);
    }
}
