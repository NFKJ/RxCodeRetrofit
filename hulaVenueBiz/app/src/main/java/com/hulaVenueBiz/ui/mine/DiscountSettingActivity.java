package com.hulaVenueBiz.ui.mine;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.common.rxbus.RxBus;
import com.common.rxbus.postevent.KeyEvent;
import com.common.utils.StringUtils;
import com.common.widget.editview.CountEditText;
import com.common.widget.navigation.WidgeButton;
import com.hulaVenueBiz.R;
import com.hulaVenueBiz.base.mvp.BaseMvpActivity;
import com.hulaVenueBiz.base.mvp.BasePresenter;
import com.hulaVenueBiz.ui.mine.adapter.DiscountTypeAdapter;

import rx.functions.Action1;

/**
 * @desc:         场馆优惠信息界面
 * @author:       Leo
 * @date:         2017/1/11
 */
public class DiscountSettingActivity extends BaseMvpActivity
{
    private RecyclerView rvIcon;
    private CountEditText evDiscount;
    private TextView tvCount;
    private WidgeButton btnSave;

    // 输入最大字数
    private final int MAXCOUNT = 10;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected void setNavigation() {
        setNavigationBack(R.string.string_discount_title);

        btnSave = new WidgeButton(context, R.string.app_save);
        getNavigationBar().setRightMenu(btnSave);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting_discount;
    }

    @Override
    protected void onViewCreated() {
        rvIcon = (RecyclerView) findViewById(R.id.rv_icon);
        evDiscount = (CountEditText) findViewById(R.id.ev_discount);
        tvCount = (TextView) findViewById(R.id.tv_count);
    }

    @Override
    protected void doLogicFunc() {
        setRecyclerView();
        evDiscount.setCountView(tvCount, MAXCOUNT);
        attachClickListener(btnSave);
        getRxBus();
    }

    // 设置优惠信息
    private void setRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvIcon.setLayoutManager(layoutManager);

        rvIcon.setAdapter(new DiscountTypeAdapter(context));
    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == btnSave.getId()) {
            showToastMsg("保存成功");
        }
    }

    private void getRxBus() {
        rxManager.add(RxBus.getDefault().toObservable(KeyEvent.class).subscribe(new Action1<KeyEvent>() {
            @Override
            public void call(KeyEvent keyEvent) {
                if (StringUtils.isNotNullAndEqual(keyEvent.getKey(), KeyEvent.DISCOUNT_POSITION)) {
                    int po = (int) keyEvent.getValue();
                    showToastMsg(po + "");
                }
            }
        }));
    }
}