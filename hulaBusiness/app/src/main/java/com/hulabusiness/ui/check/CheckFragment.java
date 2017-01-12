package com.hulabusiness.ui.check;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.common.widget.editview.DeleteEditText;
import com.common.widget.navigation.WidgeButton;
import com.hulabusiness.R;
import com.hulabusiness.base.mvp.BaseMvpFragment;
import com.hulabusiness.base.mvp.BasePresenter;

/**
 * @desc:
 * @author: Leo
 * @date: 2016/12/23
 */
public class CheckFragment extends BaseMvpFragment
{
    private DeleteEditText etCode;
    private TextView tvTest;
    private WidgeButton btnQRCode;

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
        getNavigationBar().setAppWidgeTitle("验票");
        btnQRCode = new WidgeButton(context);
        btnQRCode.setBackgroundResource(R.mipmap.ic_launcher);
        getNavigationBar().setRightMenu(btnQRCode);
    }

    @Override
    protected void onViewCreated(View view) {
        tvTest = (TextView) view.findViewById(R.id.tv_test);
        etCode = (DeleteEditText) view.findViewById(R.id.et_code);
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

        attachClickListener(btnQRCode);
    }

    @Override
    protected void onViewClicked(View view) {

    }
}
