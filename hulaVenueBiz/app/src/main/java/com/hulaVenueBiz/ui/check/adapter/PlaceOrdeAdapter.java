package com.hulaVenueBiz.ui.check.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.utils.DateUtil;
import com.common.utils.StringUtils;
import com.common.widget.toast.ToastManager;
import com.hulaVenueBiz.R;
import com.hulaVenueBiz.base.listener.OnItemClickLitener;
import com.hulaVenueBiz.base.mvp.BaseAdapter;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * 订单消息的适配器 yjl
 */
public class PlaceOrdeAdapter extends BaseAdapter<PlaceOrdeAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    // 场馆图片
    private List<String> venuePic;
    private Context mContext;
    private SparseBooleanArray sparseBooleanArray;//维护CheckBox
    private int limitNum = 2;

    public SparseBooleanArray getSparseBooleanArray() {
        return sparseBooleanArray;
    }

    public PlaceOrdeAdapter(Context context, List<String> venuePic) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.venuePic = venuePic;
        sparseBooleanArray = new SparseBooleanArray(getItemCount());
    }

    public void setData(List<String> venuePic) {
        this.venuePic.addAll(venuePic);
        sparseBooleanArray.clear();
        sparseBooleanArray = null;
        sparseBooleanArray = new SparseBooleanArray(getItemCount());
        notifyDataSetChanged();
    }

    private OnItemClickLitener mOnItemClickLitener;//Item的点击监听

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_place_orde, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        String bean = venuePic.get(position);
        if (!StringUtils.isNull(bean)) {
            initData(holder, bean, position);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickLitener != null)
                    mOnItemClickLitener.onItemClick(holder.itemView, position);
            }
        });
    }

    private void initData(ViewHolder mViewHolder, String mHVVenueBean, int position) {
        //重置
        mViewHolder.ivCheckBox.setVisibility(View.VISIBLE);
        mViewHolder.ivPlace.setVisibility(View.GONE);
        if (position == 0) {
            mViewHolder.ivOrderState.setText("退款成功");
            mViewHolder.ivCheckBox.setVisibility(View.GONE);
        } else {
            mViewHolder.ivOrderState.setText("已付款");
        }
        if (position == 1) {
            mViewHolder.ivStateLayout.setVisibility(View.GONE);
            mViewHolder.ivPlace.setVisibility(View.VISIBLE);
        } else {
            mViewHolder.ivStateLayout.setVisibility(View.VISIBLE);
        }
        mViewHolder.ivOrderTime.setText(StringUtils.nullToStr(DateUtil.getSimpleDate(System.currentTimeMillis())));
//        mViewHolder.tvTitle.setText(StringUtils.nullToStr(mHVVenueBean));
//        mViewHolder.tvCorName.setText(""+mHVVenueBean.getProductName());
//        mViewHolder.tvPrise.setText("￥"+ DecimalUtils.doubleToIntOrD(mHVVenueBean.getDiscountPrice()));
//        BigDecimal nowPrise = new BigDecimal(mHVVenueBean.getDiscountPrice());
//        BigDecimal oldPrise = new BigDecimal(mHVVenueBean.getOriginalPrice());
//        if(nowPrise.compareTo(oldPrise)==0){
//            mViewHolder.tvOldPrise.setVisibility(View.GONE);
//        }else {
//            mViewHolder.tvOldPrise.setText("￥" + DecimalUtils.doubleToIntOrD(mHVVenueBean.getOriginalPrice()));
//        }
//        ImageLoaderUtils.display(mContext,mViewHolder.ivCorImg,mHVVenueBean.getProductUrl());
        sparseBooleanArray.put(position, mViewHolder.ivCheckBox.isChecked());
        mViewHolder.ivCheckBox.setTag(R.id.iv_check_box, position);
        mViewHolder.ivCheckBox.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            CheckBox checkBox = (CheckBox) view;
            if (isOutOfSize() && checkBox.isChecked()) {
                checkBox.setChecked(false);
                ToastManager.showShortToast("超过最大可选择数:" + limitNum);
                return;
            }
            int position = (int) view.getTag(R.id.iv_check_box);
            sparseBooleanArray.put(position, checkBox.isChecked());
            ToastManager.showShortToast(position + "->" + checkBox.isChecked());
        }
    };

    @Override
    public int getItemCount() {
        return null == venuePic ? 0 : venuePic.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView ivAddressName;
        private TextView ivOrderTime;
        private ImageView ivPlace;
        private LinearLayout ivStateLayout;
        private TextView ivOrderState;
        private CheckBox ivCheckBox;

        ViewHolder(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            ivAddressName = (TextView) view.findViewById(R.id.iv_address_name);
            ivOrderTime = (TextView) view.findViewById(R.id.iv_order_time);
            ivPlace = (ImageView) view.findViewById(R.id.iv_place);
            ivStateLayout = (LinearLayout) view.findViewById(R.id.iv_state_layout);
            ivOrderState = (TextView) view.findViewById(R.id.iv_order_state);
            ivCheckBox = (CheckBox) view.findViewById(R.id.iv_check_box);
            AutoUtils.autoSize(view);
        }
    }

    /**
     * 判断true的个数是否大于限制的个数
     *
     * @return
     */
    private boolean isOutOfSize() {
        int count = 0;
        for (int i = 0; i < sparseBooleanArray.size(); i++) {
            if (sparseBooleanArray.get(i)) {
                count += 1;
            }
        }
        return count >= limitNum;
    }
}
