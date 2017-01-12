package com.hulaVenueBiz.ui.check;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.common.widget.pulltorefresh.XRecyclerView;
import com.common.widget.toast.ToastManager;
import com.hulaVenueBiz.R;
import com.hulaVenueBiz.base.mvp.BaseMvpActivity;
import com.hulaVenueBiz.ui.check.adapter.PlaceOrdeAdapter;
import com.hulaVenueBiz.ui.check.contract.PlaceOrdeContract;
import com.hulaVenueBiz.ui.check.presenter.PlaceOrdePresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * 场地预订信息
 */
public class PlaceOrdeActivity extends BaseMvpActivity<PlaceOrdePresenterImpl> implements PlaceOrdeContract.View {
    private XRecyclerView pulltoRefreshView;
    private PlaceOrdeAdapter mAdapter;
    private Button mButton;
    @Override
    protected PlaceOrdePresenterImpl createPresenterInstance() {
        return new PlaceOrdePresenterImpl();
    }

    @Override
    protected void setNavigation() {
        getNavigationBar().setAppWidgeTitle(getResources().getString(R.string.string_place_order));
        setNavigationBack();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_place_orde;
    }

    @Override
    protected void onViewCreated() {
        pulltoRefreshView = (XRecyclerView) findViewById(R.id.pulltoRefreshView);
        mButton= (Button) findViewById(R.id.btn_yanzhen);
    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(mButton);
        List<String> list=new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
            list.add(""+i);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        pulltoRefreshView.setLayoutManager(linearLayoutManager);
        mAdapter=new PlaceOrdeAdapter(context,list);
        //添加头部
        View header = LayoutInflater.from(this).inflate(R.layout.head_place_orde, (ViewGroup) findViewById(android.R.id.content), false);
        pulltoRefreshView.addHeaderView(header);
        pulltoRefreshView.setAdapter(mAdapter);

    }
    @Override
    protected void onViewClicked(View view) {
        ToastManager.showShortToast(mAdapter.getSparseBooleanArray().toString());
    }
    @Override
    public void initView() {

    }
}
