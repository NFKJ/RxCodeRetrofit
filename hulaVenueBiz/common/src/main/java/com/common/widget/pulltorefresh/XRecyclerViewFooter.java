package com.common.widget.pulltorefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.common.R;


/**
 * 下拉刷新底部
 */
public class XRecyclerViewFooter extends LinearLayout {
    private LinearLayout mContainer;//布局指向
//    private Context mContext;//上下文引用

    public final static int STATE_LOADING = 0; //正在加载
    public final static int STATE_COMPLETE = 1;  //加载完成
    public final static int STATE_LAST = 2;  //加载到底了
    public final static int STATE_NONET = 3;  //上拉时无网络

    private ProgressBar mProgressBar;    // 正在刷新的图标
    private TextView makeText;

    //    // 均匀旋转动画
//    private RotateAnimation refreshingAnimation;
    public XRecyclerViewFooter(Context context) {
        super(context);
        initView(context);
    }

    public XRecyclerViewFooter(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    public XRecyclerViewFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    //初始化
    private void initView(Context context) {
//        mContext = context;
        mContainer = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.pullto_xrecycleview_footer, null);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 0);
        this.setLayoutParams(lp);
        this.setPadding(0, 0, 0, 0);
        addView(mContainer, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        setGravity(Gravity.CENTER);

        mProgressBar = (ProgressBar) findViewById(R.id.pull_to_load_footer_progressbar);
        makeText = (TextView) findViewById(R.id.pull_to_load_footer_hint_textview);

//        ///添加匀速转动动画
//        refreshingAnimation = (RotateAnimation) AnimationUtils.loadAnimation(
//                context, R.anim.rotating);
//        LinearInterpolator lir = new LinearInterpolator();
//        refreshingAnimation.setInterpolator(lir);

        measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public void  setState(int state) {
        switch (state){
            case STATE_LOADING:
                this.setVisibility(VISIBLE);
                mProgressBar.setVisibility(VISIBLE);
                makeText.setText("加载中...");
                break;
            case STATE_COMPLETE:
//                mProgressBar.clearAnimation();
                this.setVisibility(GONE);
                break;
            case STATE_LAST:
                this.setVisibility(VISIBLE);
//                mProgressBar.clearAnimation();
                makeText.setVisibility(VISIBLE);
                makeText.setText("加载到底了");
                mProgressBar.setVisibility(GONE);
                break;
            case STATE_NONET:
                this.setVisibility(VISIBLE);
//                mProgressBar.clearAnimation();
                makeText.setVisibility(VISIBLE);
                makeText.setText("没有网络了");
                mProgressBar.setVisibility(GONE);
                break;
            default:
                break;
        }
    }
}
