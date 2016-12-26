package com.downloader.manager.download;

import android.os.Looper;
import android.os.Handler;

/**
 * @desc: 主线程方法实现类
 * @author: Leo
 * @date: 2016/12/6
 */
public class MainThreadImpl implements IMainThread
{
    private Handler mHandler;

    private static MainThreadImpl instance ;

    public static MainThreadImpl getInstance(){
        if (instance == null) {
            synchronized (MainThreadImpl.class){
                if (instance == null) {
                    instance = new MainThreadImpl() ;
                }
            }
        }
        return instance ;
    }

    private MainThreadImpl() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable runnable) {
        mHandler.post(runnable);
    }
}
