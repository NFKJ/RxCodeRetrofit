package com.hulabusiness.ui.part1;

import com.common.widget.navigation.NavigationBar;
import com.hulabusiness.R;
import com.hulabusiness.base.mvp.BaseMvpActivity;
import com.hulabusiness.base.mvp.BasePresenter;

public class FirstTestActivity extends BaseMvpActivity {

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_first_test;
    }

    @Override
    protected void setNavigation() {
        getNavigationBar().setTitleType(NavigationBar.TitleType.Minor);
    }

    @Override
    protected void onViewCreated() {

    }

    @Override
    protected void doLogicFunc() {

    }
}
