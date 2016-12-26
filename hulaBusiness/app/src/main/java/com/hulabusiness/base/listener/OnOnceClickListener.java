package com.hulabusiness.base.listener;

import android.view.View;
import android.view.View.OnClickListener;

 /**
  * @desc:         防止点击打开多个界面
  * @author:       Leo
  * @date:         2016/12/23
  */
public abstract class OnOnceClickListener implements OnClickListener {

    private long lastClickTime = 0L;

    private final int MIN_CLICK_DELAY_TIME = 500;// ms

    @Override
    public void onClick(View v) {
        long currentClickTime = System.currentTimeMillis();
        if (currentClickTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            onOnceClick(v);
        }
        lastClickTime = currentClickTime;
    }

    public abstract void onOnceClick(View v);

}
