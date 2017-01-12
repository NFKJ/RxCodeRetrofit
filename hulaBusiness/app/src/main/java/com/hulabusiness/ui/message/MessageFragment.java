package com.hulabusiness.ui.message;

import android.view.View;
import android.widget.TextView;

import com.hulabusiness.R;
import com.hulabusiness.base.mvp.BaseMvpFragment;
import com.hulabusiness.base.mvp.BasePresenter;

/**
 * @desc:
 * @author: Leo
 * @date: 2016/12/23
 */
public class MessageFragment extends BaseMvpFragment
{
    private TextView tvTest;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_next;
    }

    @Override
    protected void setNavigation() {
        getNavigationBar().setAppWidgeTitle("课程");
    }

    @Override
    protected void onViewCreated(View view) {
        tvTest = (TextView) view.findViewById(R.id.tv_test);
    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(tvTest);
    }

    @Override
    protected void onViewClicked(View view, int id) {
        switch (id) {
            case R.id.tv_test:
                startActivity(WebTestActivity.class);
                break;
        }
    }
}
