package com.hulaVenueBiz.ui.message.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.utils.DateUtil;
import com.common.utils.StringUtils;
import com.common.widget.textview.ExpandableTextView;
import com.hulaVenueBiz.R;
import com.hulaVenueBiz.base.listener.OnItemClickLitener;
import com.hulaVenueBiz.base.mvp.BaseAdapter;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 *系统消息的适配器 yjl
 */
public class SysMessAdapter extends BaseAdapter<SysMessAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    // 场馆图片
    private List<String> venuePic;
    private Context mContext;

    public SysMessAdapter(Context context, List<String> venuePic) {
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
        View view = mInflater.inflate(R.layout.item_sys_mess, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        String bean = venuePic.get(position);
        if (!StringUtils.isNull(bean)) {
            initData(holder, bean,position);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickLitener != null)
                    mOnItemClickLitener.onItemClick(holder.itemView, position);
            }
        });
    }

    private void initData(ViewHolder mViewHolder, String mHVVenueBean,int position) {
        mViewHolder.tvTime.setText(StringUtils.nullToStr(DateUtil.getSimpleDate(System.currentTimeMillis())));
        mViewHolder.tvMessage.setText("说的话是的分公司的是的鬼地方个大厦的发生过的分公司的是的鬼地方个大厦的发生的分公司的是的鬼地方个大厦的发生的分公司的是的鬼地方个大厦的发生的分公司的是的鬼地方个大厦的发生的分公司的是的鬼地方个大厦的发生合适的话的非官方的是",mConvertTextCollapsedStatus,position);
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
    private SparseBooleanArray mConvertTextCollapsedStatus = new SparseBooleanArray();

    @Override
    public int getItemCount() {
        return null == venuePic ? 0 : venuePic.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTime;
        private ExpandableTextView tvMessage;

        ViewHolder(View view) {
            super(view);
            tvTime = (TextView) view.findViewById(R.id.tv_time);
            tvMessage = (ExpandableTextView) view.findViewById(R.id.tv_message);
            AutoUtils.autoSize(view);
        }
    }

}
