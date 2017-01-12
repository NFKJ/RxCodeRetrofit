package com.common.utils;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc:   view工具类
 * @author: Leo
 * @date:   2016/09/28
 */
public class ViewUtils
{
    public static void switchViewVisibility(View... targetViews) {
        for (View v : targetViews) {
            if (v != null) {
                v.setVisibility(v.getVisibility() == View.VISIBLE ? View.GONE
                        : View.VISIBLE);
            }
        }
    }

    /**
     * 获取目标界面所有view
     * @param activity  目标界面
     * @return  界面子view列表
     */
    public static List<View> getAllChildViews(Activity activity) {

        View view = activity.getWindow().getDecorView();

        return getAllChildViews(view);
    }

    public static void clearAllChildViews(Activity activity)
    {
        View view = activity.getWindow().getDecorView();

        if (view instanceof ViewGroup) {
            ((ViewGroup) view).removeAllViews();
        }
    }

    /**
     * 获取目标View的所有子view
     * @param view   目标view
     * @return   子view列表
     */
    private static List<View> getAllChildViews(View view) {

        List<View> allchildren = new ArrayList<View>();

        if (view instanceof ViewGroup) {

            ViewGroup vp = (ViewGroup) view;

            for (int i = 0; i < vp.getChildCount(); i++) {

                View viewchild = vp.getChildAt(i);

                allchildren.add(viewchild);

                allchildren.addAll(getAllChildViews(viewchild));
            }
        }

        return allchildren;
    }

    public static void setViewShow(View... targetViews) {
        for (View v : targetViews) {
            if (v != null) {
                v.setVisibility(View.VISIBLE);
            }
        }
    }

    public static void setViewHide(View... targetViews) {
        for (View v : targetViews) {
            if (v != null) {
                v.setVisibility(View.GONE);
            }
        }
    }

    public static void setViewEnabled(boolean enable, View... targetViews) {
        for (View v : targetViews) {
            if (v != null) {
                v.setEnabled(enable);
            }
        }
    }

    public static void setViewVisibility(boolean show, View... targetViews) {
        for (View v : targetViews) {
            if (v != null) {
                v.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        }
    }

    public static void setBackgroundResource(int resid, View... targetViews) {
        for (View v : targetViews) {
            if (v != null) {
                v.setBackgroundResource(resid);
            }
        }
    }

//    public static void setImageDrawable(final ImageView imageView,
//                                        int drawableInt, String url, final int dimenSize) {
//        if (drawableInt > 0) {
//            Bitmap bitmapOrg = BitmapFactory
//                    .decodeResource(imageView.getResources(), drawableInt);
//            imageView.setImageBitmap(Bitmap.createScaledBitmap(bitmapOrg,
//                    dimenSize, dimenSize, false));
//        } else if (StringUtils.isEmpty(url)) {
//            // supplement volley add internet image
//
//        }
//    }

    private static int checkVilidity(float f) {
        if (f < 0) {
            return 0;
        }
        return (int) f;
    }

    private static int getVilidity(int actual, int expect) {
        if (actual < expect) {
            return actual;
        }
        return expect;
    }
}
