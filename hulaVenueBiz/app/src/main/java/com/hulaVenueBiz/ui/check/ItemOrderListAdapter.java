package com.hulaVenueBiz.ui.check;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hulaVenueBiz.R;

import java.util.ArrayList;
import java.util.List;

public class ItemOrderListAdapter extends BaseAdapter {

    private List<Object> objects = new ArrayList<Object>();

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemOrderListAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_order_list, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((Object)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(Object object, ViewHolder holder) {
        //TODO implement
    }

    protected class ViewHolder {
        private TextView tvId;
        private TextView tvName;
        private TextView tvTime;
        private TextView tvStatus;
        private ImageView ivCheck;
        private ImageView ivSelect;

        public ViewHolder(View view) {
            tvId = (TextView) view.findViewById(R.id.tv_id);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvTime = (TextView) view.findViewById(R.id.tv_time);
            tvStatus = (TextView) view.findViewById(R.id.tv_status);
            ivCheck = (ImageView) view.findViewById(R.id.iv_check);
            ivSelect = (ImageView) view.findViewById(R.id.iv_select);
        }
    }
}