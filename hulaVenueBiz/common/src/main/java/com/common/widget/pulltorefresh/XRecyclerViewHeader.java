package com.common.widget.pulltorefresh;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.common.R;


/**
 * 下拉刷新头部
 */
public class XRecyclerViewHeader extends LinearLayout {
    private LinearLayout mContainer;//布局指向
    private Context mContext;//上下文引用

    public final static int STATE_NORMAL = 0;// 初始状态
    public final static int STATE_RELEASE_TO_REFRESH = 1;    // 释放刷新
    public final static int STATE_REFRESHING = 2;    // 正在刷新

    private ImageView mArrowImageView;//箭头呀
    private ProgressBar mProgressBar;    // 正在刷新的图标
    private TextView mStatusTextView;//状态文字
    private int mState = STATE_NORMAL;  // 当前状态（临时保存用）
//    private TextView mHeaderTimeView;//刷新时间文本

    //以下是箭头图标动画
    private Animation mRotateUpAnim;
    private Animation mRotateDownAnim;

    private final int ROTATE_ANIM_DURATION = 180;//旋转角度
    public int mMeasuredHeight;//布局的原始高度，用于做状态改变的标志
    // 均匀旋转动画
//    private RotateAnimation refreshingAnimation;
//    private AnimationDrawable refreshingAnimation;//帧动画

    public XRecyclerViewHeader(Context context) {
        super(context);
        initView(context);
    }

    public XRecyclerViewHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public XRecyclerViewHeader(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    //初始化
    private void initView(Context context) {
        mContext = context;
        // 初始情况，设置下拉刷新view高度为0
        mContainer = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.pullto_xrecycleview_header, null);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 0);
        this.setLayoutParams(lp);
        this.setPadding(0, 0, 0, 0);

        addView(mContainer, new LayoutParams(LayoutParams.MATCH_PARENT, 0));
        setGravity(Gravity.BOTTOM);

        mArrowImageView = (ImageView) findViewById(R.id.iv_normal_refresh_header_arrow);
        mStatusTextView = (TextView) findViewById(R.id.tv_normal_refresh_header_status);
        mProgressBar = (ProgressBar) findViewById(R.id.pull_to_load_header_progressbar);
//        mHeaderTimeView = (TextView) findViewById(R.id.last_refresh_time);

        //箭头改变动画
        mRotateUpAnim = new RotateAnimation(0.0f, -180.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        mRotateUpAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateUpAnim.setFillAfter(true);
        mRotateDownAnim = new RotateAnimation(-180.0f, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        mRotateDownAnim.setDuration(ROTATE_ANIM_DURATION);
        mRotateDownAnim.setFillAfter(true);
//        ///添加匀速转动动画
//        refreshingAnimation = (RotateAnimation) AnimationUtils.loadAnimation(
//                context, R.anim.update_loading_progressbar_anim);
//        LinearInterpolator lir = new LinearInterpolator();
//        refreshingAnimation.setInterpolator(lir);

//        AnimationDrawable mAnimation = new AnimationDrawable();
//        mAnimation.addFrame(getContext().getResources().getDrawable(R.drawable.bg_refresh_ed_0), 50);
//        mAnimation.addFrame(getContext().getResources().getDrawable(R.drawable.bg_refresh_ed_1), 50);
//        mAnimation.addFrame(getContext().getResources().getDrawable(R.drawable.bg_refresh_ed_2), 50);
//        mAnimation.setOneShot(false);
//        mProgressBar.setBackground(mAnimation);
//        refreshingAnimation = (AnimationDrawable) mProgressBar.getBackground();

        measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mMeasuredHeight = getMeasuredHeight();

    }

    //设置状态
    public void setState(int state) {
        if (state == mState) {
            return;
        }
        //判断显示
        switch (state) {
            case STATE_NORMAL:
            case STATE_RELEASE_TO_REFRESH:// 释放刷新
                mProgressBar.clearAnimation();
                mArrowImageView.setVisibility(VISIBLE);
                mProgressBar.setVisibility(INVISIBLE);
                mStatusTextView.setVisibility(VISIBLE);
                break;
            case STATE_REFRESHING://正在刷新
                mArrowImageView.clearAnimation();
                mArrowImageView.setVisibility(View.INVISIBLE);
                mProgressBar.setVisibility(View.VISIBLE);
                mStatusTextView.setVisibility(VISIBLE);
                break;
            default:
                break;
        }
        //判断动画的添加
        switch (state) {
            case STATE_NORMAL:
                if (mState == STATE_RELEASE_TO_REFRESH) {
                    mArrowImageView.startAnimation(mRotateDownAnim);

                }
                if (mState == STATE_REFRESHING) {
                    mArrowImageView.clearAnimation();
                }
                mStatusTextView.setText("下拉刷新");
                break;
            case STATE_RELEASE_TO_REFRESH:// 释放刷新
                if (mState != STATE_RELEASE_TO_REFRESH) {
                    mArrowImageView.clearAnimation();
                    mArrowImageView.startAnimation(mRotateUpAnim);
                    mStatusTextView.setText("释放更新");
                }
                break;
            case STATE_REFRESHING:// 正在刷新
//                refreshingAnimation.start();
//                mProgressBar.startAnimation(refreshingAnimation);
                mStatusTextView.setText("正在刷新");
                break;
            default:
//                refreshingAnimation.stop();
                break;
        }
        mState = state;
    }

    //返回当前状态
    public int getState() {
        return mState;
    }

    //完成刷新
    public void refreshComplate() {
//        mHeaderTimeView.setText(friendlyTime(new Date()));
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String date = format.format(new Date());
//        mHeaderTimeView.setText(date);
        smoothScrollTo(0);
        setState(STATE_NORMAL);
    }

    /**
     * 刷新头滑动改变
     */
    public void onMove(float dalta) {
        if (getVisiableHeight() > 0 || dalta > 0) {
            setVisiableHeight((int) dalta + getVisiableHeight());
            if (mState <= STATE_RELEASE_TO_REFRESH) {// 未处于刷新状态，更新箭头
                if (getVisiableHeight() > mMeasuredHeight) {
                    setState(STATE_RELEASE_TO_REFRESH);
                } else {
                    setState(STATE_NORMAL);
                }
            }
        }
    }

    /**
     * 释放刷新头的时候，是否满足刷新的要求
     */
    public boolean releaseAction() {
        boolean isOnRefresh = false;
        int height = getVisiableHeight();
        if (height == 0) {
            isOnRefresh = false;
        }

        //刷新时改变状态
        if (getVisiableHeight() > mMeasuredHeight && mState < STATE_REFRESHING) {
            setState(STATE_REFRESHING);
            isOnRefresh = true;
        }
        //刷新时回滚到原始高度
        int destHeight = 0;
        if (mState == STATE_REFRESHING) {
            destHeight = mMeasuredHeight;
        }
        smoothScrollTo(destHeight);
        return isOnRefresh;
    }

    //回滚到顶部
    private void smoothScrollTo(int destHeight) {
        ValueAnimator animator = ValueAnimator.ofInt(getVisiableHeight(), destHeight);
        animator.setDuration(300).start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setVisiableHeight((Integer) animation.getAnimatedValue());
            }
        });
        animator.start();
    }

    //设置刷新头可见的高度
    public void setVisiableHeight(int height) {
        if (height < 0)
            height = 0;
        LayoutParams lp = (LayoutParams) mContainer
                .getLayoutParams();
        lp.height = height;
        mContainer.setLayoutParams(lp);
    }

    //获得刷新头可见的高度
    public int getVisiableHeight() {
        int height = 0;
        LayoutParams lp = (LayoutParams) mContainer
                .getLayoutParams();
        height = lp.height;
        return height;
    }

}
