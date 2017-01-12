package com.common.widget.progress;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

public class LoadDialog {
    private Dialog load = null;
    private String text;
    private Context context;
    private boolean cancelable;

    /**
     * 改变提示文字
     * @param context
     * @param text
     * @param cancelable
     */
    public LoadDialog(Context context,String text, boolean cancelable) {
        this.text=text;
        this.context=context;
        this.cancelable = cancelable;
        create(text,cancelable);
    }

    private void create(String text, Boolean cancelable){
        createLoadingView(text, cancelable);
    }

    public void show(){
        if (load != null&&!load.isShowing()&&context!=null) {
            load.show();
        }
    }

    public void dismiss() {
        if (load != null&&load.isShowing()&&!((Activity) context).isFinishing()) {
            load.dismiss();
            load = null;
        }
    }

    /**
     * 自定义等待框提示
     *
     * @param text
     */
    private Dialog createLoadingView(String text, Boolean canceledOnTouchOutside) {
        load = DialogUtil.createLoadingDialog(context, text, canceledOnTouchOutside);
        return load;
    }
}
