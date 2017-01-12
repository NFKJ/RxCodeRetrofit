package com.common.widget.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.R;

/**
 * 通用对话框 yjl
 */
public class CommonDialog extends DialogFragment {
    private TextView mCancel, mSubmit, mText, mTitle;
    private LinearLayout mTitlelayout;
    private View rootView;
    private boolean outSidedismiss = true;

    public static CommonDialog newInstance(Context context) {
        CommonDialog adf = new CommonDialog();
        adf.rootView = LayoutInflater.from(context).inflate(R.layout.layout_base_dialog, null);
        adf.init(adf.rootView);
        return adf;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.dialog_contact);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setCanceledOnTouchOutside(outSidedismiss);
        if(!outSidedismiss){
            getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        return true;
                    }
                    return false;
                }
            });
        }
        return rootView;
    }

    /**  对话框初始化 */
    public void init(View rootView) {
        mCancel = (TextView) rootView.findViewById(R.id.dialog_contact_cancel);
        mSubmit = (TextView) rootView.findViewById(R.id.dialog_contact_submit);
        mText = (TextView) rootView.findViewById(R.id.dialog_contact_text);
        mTitle = (TextView) rootView.findViewById(R.id.dialog_contact_title);
        mTitlelayout = (LinearLayout) rootView.findViewById(R.id.dialog_contact_titlelayout);

        if (null != mCancel) {
            mCancel.setOnClickListener(cancelClick);
        }
    }

    /**
     * diaog标题
     * @param title    title
     */
    public void setTitle(String title) {
        if (null != mTitle) {
            mTitle.setText(title);
            mTitlelayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * diaog提示语
     * @param text    text
     */
    public void setText(String text) {
        if (null != mText) {
            mText.setText(text);
        }
    }

    /**
     * dialog提示语
     * @param text  text
     * @param size 字体大小
     */
    public void setText(String text, int size) {
        if (null != mText) {
            mText.setText(text);
            mText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
        }
    }

    /**
     * 确认监听事件
     * @param submit     submit
     */
    public void setSubmit(View.OnClickListener submit) {
        if (null == submit) {
            if (null != mSubmit) {
                mSubmit.setOnClickListener(cancelClick);
            }
        } else if (null != mSubmit) {
            mSubmit.setOnClickListener(submit);
        }
    }

    public void setSubmitText(String str) {
        mSubmit.setText(str);
    }

    public void setCancelText(String str) {
        mCancel.setText(str);
    }

    /**
     * 取消监听事件
     * @param cancel     cancel
     */
    public void setCancel(View.OnClickListener cancel) {
        if (null == cancel) {
            if (null != mCancel) {
                mCancel.setOnClickListener(cancelClick);
            }
        } else if (null != mCancel) {
            mCancel.setOnClickListener(cancel);
        }
    }

    /**
     * 去除取消按钮
     */
    public void setCancelGone() {
        if (null != mCancel) {
            mCancel.setVisibility(View.GONE);
            mSubmit.setBackgroundResource(R.drawable.btn_all_bg);
        }
    }

    /**
     * 设置弹框外消失
     * @param bool    bool
     */
    public void setCanceledOnTouchOutside(Boolean bool) {
        outSidedismiss = bool;
    }

    private View.OnClickListener cancelClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CommonDialog.this.dismiss();
        }
    };

    @Override
    public void show(FragmentManager manager, String tag) {
        //这里直接调用show方法会报java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commitAllowingStateLoss();
    }
}
