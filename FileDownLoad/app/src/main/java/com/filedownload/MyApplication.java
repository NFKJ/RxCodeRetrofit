package com.filedownload;

import android.app.Application;

import com.downloader.manager.retrofit.RetrofitManager;

public class MyApplication extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitManager.getInstance().init(this, "http://test.kuaikuaikeji.com/kas/");
    }
}
