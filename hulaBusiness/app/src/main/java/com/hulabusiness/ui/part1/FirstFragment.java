package com.hulabusiness.ui.part1;

import android.content.Intent;
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
public class FirstFragment extends BaseMvpFragment
{
    private TextView tvTest;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_first;
    }

    @Override
    protected void setNavigation() {
        getNavigationBar().setAppWidgeTitle("场馆");
    }

    @Override
    protected void onViewCreated(View view) {
        tvTest = (TextView) view.findViewById(R.id.tv_test);
    }

    @Override
    protected void doLogicFunc() {
        setListener();
        tvTest.setText("测试");
    }

    private void setListener() {
        tvTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, FirstTestActivity.class));
            }
        });
    }
}
