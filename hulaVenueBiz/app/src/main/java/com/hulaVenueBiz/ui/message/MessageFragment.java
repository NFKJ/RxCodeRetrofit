package com.hulaVenueBiz.ui.message;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hulaVenueBiz.R;
import com.hulaVenueBiz.base.mvp.BaseMvpFragment;
import com.hulaVenueBiz.base.mvp.BasePresenter;
import com.hulaVenueBiz.ui.check.PlaceOrdeActivity;

/**
 * @desc: 消息模块主页
 * @author: Leo
 * @date: 2017/1/10
 */
public class MessageFragment extends BaseMvpFragment {
    private LinearLayout llayoutSys,llayout_ding;
    private ImageView ivRedSys;
    private LinearLayout llayoutOrd;
    private ImageView ivRedOrd;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void setNavigation() {
        getNavigationBar().setAppWidgeTitle(getResources().getString(R.string.string_course));
    }

    @Override
    protected void onViewCreated(View view) {
        llayout_ding= (LinearLayout) view.findViewById(R.id.llayout_ding);
        llayoutSys = (LinearLayout) view.findViewById(R.id.llayout_sys);
        ivRedSys = (ImageView) view.findViewById(R.id.iv_red_sys);
        llayoutOrd = (LinearLayout) view.findViewById(R.id.llayout_ord);
        ivRedOrd = (ImageView) view.findViewById(R.id.iv_red_ord);
    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(llayoutSys);
        attachClickListener(llayoutOrd);
        attachClickListener(llayout_ding);
    }

    @Override
    protected void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llayout_sys:
                startActivity(SysMessActivity.class);
                break;
            case R.id.llayout_ord:
                startActivity(OrdMessActivity.class);
                break;
            case R.id.llayout_ding:
                startActivity(PlaceOrdeActivity.class);
                break;
        }
    }

}
