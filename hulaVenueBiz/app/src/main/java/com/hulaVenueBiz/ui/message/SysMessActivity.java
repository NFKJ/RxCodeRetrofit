package com.hulaVenueBiz.ui.message;

import android.support.v7.widget.LinearLayoutManager;

import com.common.widget.pulltorefresh.XRecyclerView;
import com.hulaVenueBiz.R;
import com.hulaVenueBiz.base.mvp.BaseMvpActivity;
import com.hulaVenueBiz.ui.message.adapter.SysMessAdapter;
import com.hulaVenueBiz.ui.message.contract.SysMessContract;
import com.hulaVenueBiz.ui.message.presenter.SysMessPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统消息 yjl
 */
public class SysMessActivity extends BaseMvpActivity<SysMessPresenterImpl> implements SysMessContract.View {
    private XRecyclerView pulltoRefreshView;
    private SysMessAdapter mAdapter;

    @Override
    protected SysMessPresenterImpl createPresenterInstance() {
        return new SysMessPresenterImpl();
    }

    @Override
    protected void setNavigation() {
        getNavigationBar().setAppWidgeTitle(getResources().getString(R.string.string_message_sys));
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
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        pulltoRefreshView.setLayoutManager(linearLayoutManager);
        mAdapter = new SysMessAdapter(context, list);
        pulltoRefreshView.setAdapter(mAdapter);
    }

    @Override
    public void initView() {

    }
}
