package com.hulaVenueBiz.base.listener;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public abstract class OnOnceItemClickListener implements OnItemClickListener {

    private long lastClickTime = 0L;

    private final int MIN_CLICK_DELAY_TIME = 500;// ms

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        long currentClickTime = System.currentTimeMillis();
        if (currentClickTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            onOnceItemClick(parent, view, position, id);
        }
        lastClickTime = currentClickTime;
    }

    public abstract void onOnceItemClick(AdapterView<?> parent, View view, int position, long id);

}
