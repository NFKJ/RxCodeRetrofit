package com.downloader.config;

import com.downloader.manager.retrofit.RetrofitManager;
import com.downloader.utils.NetWorkUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @desc:
 * @author: Leo
 * @date: 2016/12/06
 */
public class CommonInterceptor implements Interceptor
{
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder newBuidler = originalRequest.newBuilder();
        Request compressedRequest;

        //网络不可用时，从缓存中读取
        if (!NetWorkUtils.isCanBeUsed(RetrofitManager.getInstance().context)) {
            newBuidler.cacheControl(CacheControl.FORCE_CACHE);
        } else {
            newBuidler.cacheControl(CacheControl.FORCE_NETWORK);
        }

        newBuidler.header("User-Agent", "HLSyc_Android");

        compressedRequest = newBuidler.build();

        Response response = chain.proceed(compressedRequest);

        if (NetWorkUtils.isCanBeUsed(RetrofitManager.getInstance().context)) {
            //有网络时，设置缓存超时时间一小时
            int maxAge = 60 * 60;
            response = response.newBuilder()
                    .removeHeader("Pragma")
                    //清除头信息，因为如果服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .header("Cache-Control", "public, max=age" + maxAge)
                    .build();
        } else {
            //无网络时，设置超时为5分钟
            int maxStale = 60 * 5;
            response = response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
        return response;
    }
}
