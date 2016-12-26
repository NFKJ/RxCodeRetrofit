package com.retrofit.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * @desc:   App相关工具类
 * @author: Leo
 * @date:   2016/09/26
 */
public class AppUtils {

    private AppUtils() {
        throw new UnsupportedOperationException("error...");
    }

    /**
     * 获取app版本名
     * @param context 上下文
     * @return  版本名
     */
    public static String getVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }
}
