package com.hulaVenueBiz.ui.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.rxbus.RxBus;
import com.common.rxbus.postevent.KeyEvent;
import com.common.utils.EmptyUtils;
import com.hulaVenueBiz.R;
import com.hulaVenueBiz.base.listener.OnItemClickLitener;
import com.hulaVenueBiz.base.mvp.BaseAdapter;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * @desc:         优惠信息分类适配器
 * @author:       Leo
 * @date:         2017/1/11
 */
public class DiscountTypeAdapter extends BaseAdapter<DiscountTypeAdapter.ViewHolder>
{
    private LayoutInflater mInflater;
    private List<TypeItemBean> listBean = new ArrayList<>();

    private boolean mIsCheck = false;

    private String[] datas = new String[]{"无", "惠", "折", "秒", "减"};

    public DiscountTypeAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        setData();
    }

    private void setData() {
        for (int i = 0; i < datas.length; i++) {
            listBean.add(new TypeItemBean(datas[i], false));
        }
    }

    private OnItemClickLitener mOnItemClickLitener;//Item的点击监听

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_discount_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {
        if (EmptyUtils.isEmpty(listBean)) {
            return;
        }

        TypeItemBean bean = listBean.get(position);

        // 位置为0，且从未选中，则默认第一个选中
        if (position == 0 && !mIsCheck) {
            bean.setChecked(true);
        }

        initData(holder, bean);
    }

    private void clearStyle() {
        if (EmptyUtils.isNotEmpty(listBean)) {
            listBean.clear();
        }
        setData();
        notifyDataSetChanged();
    }

    private void initData(ViewHolder holder, TypeItemBean bean)
    {
        holder.tvType.setText(bean.getText());

        if (bean.isChecked()) {
            holder.tvType.setBackgroundResource(R.drawable.shape_discount_type_press);
        } else {
            holder.tvType.setBackgroundResource(R.drawable.shape_discount_type_normal);
        }
    }

    @Override
    public int getItemCount() {
        return listBean != null ? listBean.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView tvType;

        ViewHolder(View view) {
            super(view);
            tvType = (TextView) view.findViewById(R.id.tv_type);
            tvType.setOnClickListener(this);
            AutoUtils.autoSize(view);
        }

        @Override
        public void onClick(View view) {
            clearStyle();

            mIsCheck = true;
            TypeItemBean bean = listBean.get(getLayoutPosition());
            bean.setChecked(true);

            if (bean.isChecked()) {
                tvType.setBackgroundResource(R.drawable.shape_discount_type_press);
            } else {
                tvType.setBackgroundResource(R.drawable.shape_discount_type_normal);
            }

            RxBus.getDefault().post(new KeyEvent(KeyEvent.DISCOUNT_POSITION, getLayoutPosition()));
        }
    }

    private class TypeItemBean
    {
        private String text;                // 文本
        private boolean isChecked;          // 是否选中

        public TypeItemBean(String text, boolean isChecked) {
            this.text = text;
            this.isChecked = isChecked;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }
    }
}
