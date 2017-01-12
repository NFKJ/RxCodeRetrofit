package com.hulaVenueBiz.ui.message;

import android.support.v7.widget.LinearLayoutManager;

import com.common.widget.pulltorefresh.XRecyclerView;
import com.hulaVenueBiz.R;
import com.hulaVenueBiz.base.mvp.BaseMvpActivity;
import com.hulaVenueBiz.ui.message.adapter.OrdMessAdapter;
import com.hulaVenueBiz.ui.message.contract.OrdMessContract;
import com.hulaVenueBiz.ui.message.presenter.OrdMessPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单消息 yjl
 */
public class OrdMessActivity extends BaseMvpActivity<OrdMessPresenterImpl> implements OrdMessContract.View {

    private XRecyclerView pulltoRefreshView;
    private OrdMessAdapter mAdapter;
    @Override
    protected OrdMessPresenterImpl createPresenterInstance() {
        return new OrdMessPresenterImpl();
    }
    @Override
    protected void setNavigation() {
        getNavigationBar().setAppWidgeTitle(getResources().getString(R.string.string_message_ord));
        setNavigationBack();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_comm_pullto;
    }

    @Override
    protected void onViewCreated() {
        pulltoRefreshView = (XRecyclerView) findViewById(R.id.pulltoRefreshView);
    }

    @Override
    protected void doLogicFunc() {
        List<String> list=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            list.add(""+i);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        pulltoRefreshView.setLayoutManager(linearLayoutManager);
        mAdapter=new OrdMessAdapter(context,list);
        pulltoRefreshView.setAdapter(mAdapter);
    }

    @Override
    public void initView() {

    }
}
