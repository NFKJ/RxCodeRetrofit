package com.downloader.manager.retrofit;

import android.content.Context;

import com.downloader.DownLoadService;
import com.downloader.config.FastJsonConverterFactory;
import com.downloader.config.CommonInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * @desc: 网络下载管理类
 * @author: Leo
 * @date: 2016/12/6
 */
public class RetrofitManager
{
    private static RetrofitManager instance = null;
    public Context context;
    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;
    private DownLoadService mDownLoadService;

    public static RetrofitManager getInstance() {
        if (instance == null) {
            synchronized (RetrofitManager.class) {
                if (instance == null) {
                    instance = new RetrofitManager();
                }
            }
        }
        return instance;
    }

    public RetrofitManager init(Context context, String baseUrl) {
        this.context = context;

        synchronized (RetrofitManager.this) {
            mOkHttpClient = new OkHttpClient().newBuilder()
                    .cache(new Cache(new File(context.getExternalCacheDir(), "http_cache"), 1024 * 1024 * 100))
                    .readTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .addInterceptor(new CommonInterceptor())
                    .build();

            mRetrofit = new Retrofit.Builder()
                    .addConverterFactory(FastJsonConverterFactory.create())
                    .baseUrl(baseUrl)
                    .client(mOkHttpClient)
                    .build();

            mDownLoadService = mRetrofit.create(DownLoadService.class);
        }

        return this;
    }

    public DownLoadService getmDownLoadService() {
        return mDownLoadService;
    }
}
