package com.hulaVenueBiz.ui.mine;

import android.view.View;
import android.widget.TextView;

import com.common.widget.dialog.CommonDialog;
import com.common.widget.editview.CountEditText;
import com.common.widget.navigation.WidgeButton;
import com.hulaVenueBiz.R;
import com.hulaVenueBiz.base.listener.OnOnceClickListener;
import com.hulaVenueBiz.base.mvp.BaseMvpActivity;
import com.hulaVenueBiz.ui.mine.contract.NoticeSettingContract;
import com.hulaVenueBiz.ui.mine.presenter.NoticeSettingPresenterImpl;

/**
 * @desc:         公告编辑界面
 * @author:       Leo
 * @date:         2017/1/9
 */
public class NoticeSettingActivity extends BaseMvpActivity<NoticeSettingPresenterImpl> implements NoticeSettingContract.View
{
    private WidgeButton btnSave;
    private CountEditText evNotice;
    private TextView tvCount;

    // 输入最大字数
    private final int MAXCOUNT = 140;

    @Override
    protected NoticeSettingPresenterImpl createPresenterInstance() {
        return new NoticeSettingPresenterImpl();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting_notice;
    }

    @Override
    protected void setNavigation() {
        setNavigationBack(R.string.string_notice_title);

        btnSave = new WidgeButton(context, R.string.app_save);
        getNavigationBar().setRightMenu(btnSave);
    }

    @Override
    protected void onViewCreated() {
        evNotice = (CountEditText) findViewById(R.id.ev_notice);
        tvCount = (TextView) findViewById(R.id.tv_count);
    }

    @Override
    protected void doLogicFunc() {
        evNotice.setCountView(tvCount, MAXCOUNT);
        attachClickListener(btnSave);
    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == btnSave.getId()) {
            presenter.goToChangeNotice(getEditTextStr(evNotice));
        }
    }

    @Override
    public void changeSuccess() {
        CommonDialog commonDialog = newCommonDialog(getString(R.string.string_notice_success));
        commonDialog.setSubmit(new OnOnceClickListener() {
            @Override
            public void onOnceClick(View v) {
                onBackPressed();
            }
        });
    }
}