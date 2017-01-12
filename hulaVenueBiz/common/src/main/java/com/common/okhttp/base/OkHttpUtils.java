package com.common.okhttp.base;

import android.os.Handler;
import android.os.Looper;

import com.common.okhttp.base.builder.GetBuilder;
import com.common.okhttp.base.builder.PostFileBuilder;
import com.common.okhttp.base.builder.PostFormBuilder;
import com.common.okhttp.base.builder.PostStringBuilder;
import com.common.okhttp.base.callback.Callback;
import com.common.okhttp.base.cookie.CookieJarImpl;
import com.common.okhttp.base.cookie.store.CookieStore;
import com.common.okhttp.base.cookie.store.HasCookieStore;
import com.common.okhttp.base.cookie.store.MemoryCookieStore;
import com.common.okhttp.base.request.RequestCall;
import com.common.okhttp.base.utils.Exceptions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by zhy on 15/8/17.
 */
public class OkHttpUtils {
    /**
     * 默认超时时间
     */
    public static final long DEFAULT_MILLISECONDS = 15_000;
    public static final int DEFAULT_ERROR_CODE = -1;
    private static OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mDelivery;

//    private OkHttpUtils() {
//        mDelivery = new Handler(Looper.getMainLooper());
//        mOkHttpClient = new OkHttpClient.Builder().build();
//    }

    public OkHttpUtils(OkHttpClient okHttpClient) {
        mDelivery = new Handler(Looper.getMainLooper());
        if (okHttpClient == null) {
            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
            //cookie enabled
            okHttpClientBuilder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));
            okHttpClientBuilder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            mOkHttpClient = okHttpClientBuilder.build();
        } else {
            mOkHttpClient = okHttpClient;
        }
    }

    public static OkHttpUtils getInstance() {
        if (mInstance == null) {
            synchronized (OkHttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpUtils(null);
                }
            }
        }
        return mInstance;
    }

    public static OkHttpUtils getInstance(OkHttpClient okHttpClient) {
        if (mInstance == null) {
            synchronized (OkHttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpUtils(okHttpClient);
                }
            }
        }
        return mInstance;
    }

    public Handler getDelivery() {
        return mDelivery;
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public static GetBuilder get() {
        return new GetBuilder();
    }

    public static PostStringBuilder postString() {
        return new PostStringBuilder();
    }

    public static PostFileBuilder postFile() {
        return new PostFileBuilder();
    }

    public static PostFormBuilder post() {
        return new PostFormBuilder();
    }

    public void execute(final RequestCall requestCall, Callback callback) {
        if (callback == null) callback = Callback.CALLBACK_DEFAULT;
        final Callback finalCallback = callback;

        requestCall.getCall().enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailResultCallback(DEFAULT_ERROR_CODE, e, finalCallback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200) {
                    Object o = finalCallback.onResult(response);
                    sendSuccessResultCallback(o, finalCallback);
                    return;
                } else {
                    sendHttpFailResultCallback(response.code(), finalCallback);
                }
            }
        });
    }

    public CookieStore getCookieStore() {
        final CookieJar cookieJar = mOkHttpClient.cookieJar();
        if (cookieJar == null) {
            Exceptions.illegalArgument("you should invoked okHttpClientBuilder.cookieJar() to set a cookieJar.");
        }
        if (cookieJar instanceof HasCookieStore) {
            return ((HasCookieStore) cookieJar).getCookieStore();
        } else {
            return null;
        }
    }

    public void sendFailResultCallback(final int code, final Exception e, final Callback callback) {
        if (callback == null) return;

        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                callback.onError(code, null == e ? "" : e.getMessage());
                callback.onAfter();
            }
        });
    }

    public void sendHttpFailResultCallback(final int statusCode, final Callback callback) {
        if (callback == null) return;

        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                callback.onHttpError(statusCode);
                callback.onAfter();
            }
        });
    }

    public void sendSuccessResultCallback(final Object object, final Callback callback) {
        if (callback == null) return;
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                callback.onResponse(object);
                callback.onAfter();
            }
        });
    }

    /**
     * 根据tag取消请求
     */
    public void cancelTag(Object tag) {
        for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }

    public void setConnectTimeout(int timeout, TimeUnit units) {
        mOkHttpClient = getOkHttpClient().newBuilder().connectTimeout(timeout, units).build();
    }
}

