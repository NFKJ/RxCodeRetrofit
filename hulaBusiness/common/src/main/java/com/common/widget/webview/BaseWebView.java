package com.common.widget.webview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.common.R;

/**
 * 添加Progress的Webview
 * @author Leo
 * Created at 2016/9/26
 */
public class BaseWebView extends WebViewManager
{
    private OnLoadCompleteListener onLoadCompleteListener;      //url加载完成监听
    private ProgressBar progressBar;                            //加载进度条

    public BaseWebView(Context context) {
        this(context, null);
    }

    public BaseWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        createProsserBar(context);
        addView(progressBar);
        setWebChromeClient(new WebClient());
    }

    /**
     * 创建水平进度条
     * @param context 上下文
     */
    private void createProsserBar(Context context)
    {
        progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressBar.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.base_progress_horizonbar));
        progressBar.setMax(100);
        progressBar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 5, 0, 0));
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt)
    {
        LayoutParams lp = (LayoutParams) progressBar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressBar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public OnLoadCompleteListener getOnLoadCompleteListener() {
        return onLoadCompleteListener;
    }

    public void setOnLoadCompleteListener(OnLoadCompleteListener onLoadCompleteListener) {
        this.onLoadCompleteListener = onLoadCompleteListener;
    }

    public class WebClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressBar.setVisibility(GONE);

                if (onLoadCompleteListener != null) {
                    int contentHeight = getContentHeight();
                    int viewHeight = getHeight();
                    onLoadCompleteListener.onComplete(contentHeight, viewHeight);
                }

                if(!getSettings().getLoadsImagesAutomatically()) {
                    getSettings().setLoadsImagesAutomatically(true);
                }
            } else {
                if (progressBar.getVisibility() == GONE)
                    progressBar.setVisibility(VISIBLE);
                progressBar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }

    }

    public interface OnLoadCompleteListener {
        void onComplete(int contentHeight, int viewHeight);
    }
}