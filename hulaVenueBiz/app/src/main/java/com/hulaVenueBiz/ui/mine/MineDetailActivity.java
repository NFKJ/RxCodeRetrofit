package com.hulaVenueBiz.ui.mine;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.common.okhttp.beans.HVVenueCategoryBean;
import com.common.okhttp.beans.HVVenueInfoBean;
import com.common.okhttp.beans.HVVenueServiceBean;
import com.common.utils.SizeUtils;
import com.common.widget.imageview.XCFlowLayout;
import com.hulaVenueBiz.R;
import com.hulaVenueBiz.base.mvp.BaseMvpActivity;
import com.hulaVenueBiz.ui.mine.contract.InfoDetailContract;
import com.hulaVenueBiz.ui.mine.presenter.InfoDetailPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
  * @desc:         场馆资料详情
  * @author:       Leo
  * @date:         2017/1/10
  */
public class MineDetailActivity extends BaseMvpActivity<InfoDetailPresenterImpl> implements InfoDetailContract.View
{
    private ImageView ivLogo;
    private TextView tvTitle;
    private TextView tvAddress;
    private TextView tvTime;
    private TextView tvPhone;
    private TextView tvObject;
    private TextView tvIntroduce;
    private XCFlowLayout flImage;

    private int mImgPos = 0;                           //根据tab的网络真实尺寸加载的postion

    @Override
    protected InfoDetailPresenterImpl createPresenterInstance() {
        return new InfoDetailPresenterImpl();
    }

    @Override
    protected void setNavigation() {
        setNavigationBack(R.string.string_detailinfo_title);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_info_detail;
    }

    @Override
    protected void onViewCreated()
    {
        ivLogo = (ImageView) findViewById(R.id.iv_logo);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvAddress = (TextView) findViewById(R.id.tv_address);
        tvTime = (TextView) findViewById(R.id.tv_time);
        tvPhone = (TextView) findViewById(R.id.tv_phone);
        flImage = (XCFlowLayout) findViewById(R.id.fl_image);
        tvObject = (TextView) findViewById(R.id.tv_object);
        tvIntroduce = (TextView) findViewById(R.id.tv_introduce);
    }

    @Override
    protected void doLogicFunc() {
        showLable(null);
        setTextView(tvObject, setVenueCategoryList(null));
//        presenter.getVenueInfo();
    }

    @Override
    public void setViewData(HVVenueInfoBean bean)
    {
        setTextView(tvTitle, bean.getVenueName());
        setTextView(tvAddress, bean.getAddress());
        setTextView(tvTime, bean.getOpenTime());
        setTextView(tvPhone, bean.getPhone());
        setTextView(tvIntroduce, bean.getIntroduce());
        setImageView(ivLogo, bean.getThumbnailUrl());
        showLable(bean);
        setTextView(tvObject, setVenueCategoryList(bean));
    }

    private String setVenueCategoryList(HVVenueInfoBean infoBean)
    {
        List<HVVenueCategoryBean> serviceBeen = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            HVVenueCategoryBean serviceBean = new HVVenueCategoryBean();
            serviceBean.setCategoryName("羽毛球");
            serviceBeen.add(serviceBean);
        }

        String categorys = "";

        for (HVVenueCategoryBean bean : serviceBeen) {
            categorys += bean.getCategoryName() + "、";
        }

        categorys = categorys.endsWith("、")
                ? categorys.substring(0, categorys.length() - 1) : categorys;

        return categorys;
    }

    /**
     * 根据具体的网络尺寸加载
     * @param serviceBeen 标签的数据集
     */
    private void doShowUrlImg(final List<HVVenueServiceBean> serviceBeen)
    {
        if (mImgPos >= serviceBeen.size()) {//当pos超出return
            return;
        }

        final ImageView mIv = new ImageView(context);
        Glide.with(context)
                .load(serviceBeen.get(mImgPos).getServiceIconUrl())
                .asBitmap()
                .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {

                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                        // Do something with bitmap here.
                        int h = bitmap.getHeight(); //获取bitmap信息，可赋值给外部变量操作，也可在此时行操作。
                        int w = bitmap.getWidth();
                        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(SizeUtils.dp2px(context, w / 2), SizeUtils.dp2px(context, h / 2));
//                        if (mImgPos != 0) {//就第一个不margin left 5dp
                            ll.setMargins(0, SizeUtils.dp2px(context, 5), SizeUtils.dp2px(context, 5), 0);
//                        } else {
//                            ll.setMargins(0, SizeUtils.dp2px(context, 5), 0, 0);
//                        }
                        mIv.setLayoutParams(ll);
                        mIv.setImageBitmap(bitmap);
                        flImage.addView(mIv);
                        mImgPos++;
                        doShowUrlImg(serviceBeen);
                    }
                });

    }

    /**
     * 显示标签
     *
     * @param bean 标签的类
     */
    private void showLable(HVVenueInfoBean bean) {
        List<HVVenueServiceBean> serviceBeen = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            HVVenueServiceBean serviceBean = new HVVenueServiceBean();
            serviceBean.setServiceIconSize(54);
            serviceBean.setServiceIconUrl("https://pic.hulasports.com/7ee120ea00614855a73b33e1b78f7873");
            serviceBeen.add(serviceBean);
        }
//        if (EmptyUtils.isEmpty(bean.getViewVenueServiceList())) {
//            flImage.setVisibility(View.GONE);
//        } else {
//            doShowUrlImg(bean.getViewVenueServiceList());
//        }
        doShowUrlImg(serviceBeen);
    }
}
