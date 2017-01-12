package com.common.utils;

import android.app.Application;
import android.content.Context;

/**
 * @desc:         用于获取Context
 * @author:       Leo
 * @date:         2016/12/29
 */
public class ContextUtils extends Application
{
    private static Context context;

    public static Context getAppContext()
    {
        return context;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = getApplicationContext();
    }
}
