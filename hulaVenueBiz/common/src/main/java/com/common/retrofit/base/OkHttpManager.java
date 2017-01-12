package com.common.retrofit.base;

import com.common.base.Constants;
import com.common.utils.DeviceUtils;

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
                        .header("Token", "")
                        .header("DeviceType", Constants.ANDROID)
                        .header("DeviceID", DeviceUtils.getDeviceInfo())
//                        .header("Version", Constants.mVersion)
//                        .header("Data", Constants.getData(json))
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });
        return builder.build();
    }
}
