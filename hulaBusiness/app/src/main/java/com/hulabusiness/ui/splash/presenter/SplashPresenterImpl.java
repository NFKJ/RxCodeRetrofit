package com.hulabusiness.ui.splash.presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.common.utils.SPUtils;
import com.hulabusiness.ui.splash.contract.SplashContract;

/**
 * @desc:         启动页逻辑吹
 * @author:       Leo
 * @date:         2016/12/23
 */
public class SplashPresenterImpl extends SplashContract.Presenter {

    private void checkShare(Context context)
    {
        boolean isFirstIn;

        isFirstIn = SPUtils.getShareBoolean(context, "isFirstIn");
        if (isFirstIn) {
            SPUtils.setShareBoolean(context, "isFirstIn", false);
            mView.readyToGuide();
        } else
            mView.readyToMain();

        Log.e("isFirstIn", isFirstIn + "");
    }

    @Override
    public void checkIsFirstIn(final Context context, View view)
    {
        if (null == context || null == view) {
            return;
        }

        ScaleAnimation scale = new ScaleAnimation(1.1f,1.1f,1f,1f);
        TranslateAnimation translate = new TranslateAnimation(-100,0,0,0);

        AnimationSet animSet = new AnimationSet(true);
        animSet.addAnimation(scale);
        animSet.addAnimation(translate);

        animSet.setDuration(2*1000);
        translate.setRepeatCount(1);
        translate.setRepeatMode(Animation.REVERSE);
        view.startAnimation(animSet);

        animSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                checkShare(context);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
}