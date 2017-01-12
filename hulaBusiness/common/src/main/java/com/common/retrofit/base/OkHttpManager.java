package com.common.retrofit.base;

import com.common.Constants;
import com.common.utils.MD5Utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpManager {

    private static OkHttpManager m_ins = null;

    public static OkHttpManager getClient() {
        if (m_ins == null) {
            synchronized (OkHttpManager.class) {
                if (m_ins == null) {
                    m_ins = new OkHttpManager();
                }
            }
        }
        return m_ins;
    }

    public OkHttpClient getOkHttp(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.retryOnConnectionFailure(true);
        builder.connectTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(Constants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Content-Type", "application/json;charset=UTF-8")
                        .header("Accept-Encoding", "gzip,deflate")
                        .header("Charset", "UTF-8")
                        .header("Accept", "application/json")
                        .header("token", "")
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });
        return builder.build();
    }

    /**
     * 获取拼接字符串
     * @param json json
     * @return
     */
    public static String getData(String json){
        String mData="";
        String mDataStr=new StringBuffer("diiaoiiai").append("?DeviceType=").append("2").append("&DeviceId=").append("").append("&Token=").append("").append("&Content=").append(json).toString();
        mData= MD5Utils.MD5Encode(mDataStr);
        return mData;
    }
}
