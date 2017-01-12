package com.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * @desc:         SP相关工具类
 * @author:       Leo
 * @date:         2016/12/23
 */
public class SPUtils {

    /**
     * 存储bool
     * @param context   con
     * @param key       key
     * @param value     数值
     */
    public static void setShareBoolean(Context context, String key, boolean value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * 取出bool
     * @param context   con
     * @param key       key
     * @return bool
     */
    public static boolean getShareBoolean(Context context, String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(key, false);
    }

    /**
     * 存储String
     * @param context   con
     * @param key       key
     * @param value     数值
     */
    public static void setShareString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, StringUtils.nullToStr(value));
        editor.apply();
    }

    /**
     * 取出String
     * @param context   con
     * @param key       key
     * @return String
     */
    public static String getShareString(Context context, String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key, null);
    }

    /**
     * 存储json
     * @param context   con
     * @param key       key
     * @param value     数值
     */
    public static void setShareJson(Context context, String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * 取出json
     * @param context   con
     * @param key       key
     * @return String
     */
    public static String getShareJson(Context context, String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key, null);
    }
}