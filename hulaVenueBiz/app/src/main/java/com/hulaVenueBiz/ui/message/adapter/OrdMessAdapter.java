package com.hulaVenueBiz.ui.message.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.utils.DateUtil;
import com.common.utils.StringUtils;
import com.hulaVenueBiz.R;
import com.hulaVenueBiz.base.listener.OnItemClickLitener;
import com.hulaVenueBiz.base.mvp.BaseAdapter;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 *订单消息的适配器 yjl
 */
public class OrdMessAdapter extends BaseAdapter<OrdMessAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    // 场馆图片
    private List<String> venuePic;
    private Context mContext;

    public OrdMessAdapter(Context context, List<String> venuePic) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.venuePic = venuePic;
    }

    public void setData(List<String> venuePic) {
        this.venuePic.addAll(venuePic);
        notifyDataSetChanged();
    }

    private OnItemClickLitener mOnItemClickLitener;//Item的点击监听

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_ord_mess, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        String bean = venuePic.get(position);
        if (!StringUtils.isNull(bean)) {
            initData(holder, bean);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickLitener != null)
                    mOnItemClickLitener.onItemClick(holder.itemView, position);
            }
        });
    }

    private void initData(ViewHolder mViewHolder, String mHVVenueBean) {
        mViewHolder.tvTime.setText(StringUtils.nullToStr(DateUtil.getSimpleDate(System.currentTimeMillis())));
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
    }

    @Override
    public int getItemCount() {
        return null == venuePic ? 0 : venuePic.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvTime;
        private TextView tvOrdId;
        private TextView tvOrdTel;
        private TextView tvOrdPrise;

        ViewHolder(View view) {
            super(view);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            tvTime = (TextView) view.findViewById(R.id.tv_time);
            tvOrdId = (TextView) view.findViewById(R.id.tv_ord_id);
            tvOrdTel = (TextView) view.findViewById(R.id.tv_ord_tel);
            tvOrdPrise = (TextView) view.findViewById(R.id.tv_ord_prise);
            AutoUtils.autoSize(view);
        }
    }

}
