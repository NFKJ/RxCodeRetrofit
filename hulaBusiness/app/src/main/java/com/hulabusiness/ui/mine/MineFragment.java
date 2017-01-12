package com.hulabusiness.ui.mine;

import android.view.View;

import com.hulabusiness.R;
import com.hulabusiness.base.mvp.BaseMvpFragment;
import com.hulabusiness.base.mvp.BasePresenter;

/**
 * @desc:
 * @author: Leo
 * @date: 2016/12/23
 */
public class MineFragment extends BaseMvpFragment
{
    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_third;
    }

    @Override
    protected void setNavigation() {
        getNavigationBar().setAppWidgeTitle("我的");
    }

    @Override
    protected void onViewCreated(View view) {

    }

    @Override
    protected void doLogicFunc() {

    }
}
