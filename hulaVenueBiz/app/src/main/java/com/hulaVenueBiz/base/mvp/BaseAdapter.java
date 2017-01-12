package com.hulaVenueBiz.base.mvp;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


/**
 * Created by 小俞 on 2017/1/12 0012.
 */

public abstract class BaseAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(T holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
