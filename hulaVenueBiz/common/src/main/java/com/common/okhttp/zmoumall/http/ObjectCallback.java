package com.common.okhttp.zmoumall.http;

import android.content.Context;

import com.common.okhttp.base.OkHttpUtils;
import com.common.okhttp.base.callback.StringCallback;
import com.common.utils.LogUtils;
import com.common.utils.NetworkUtils;
import com.common.widget.toast.ToastManager;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * 通用对象解析
 * <pre>使用Gson进行解析</pre>
 * ltx
 */
public abstract class ObjectCallback<T> extends StringCallback {

    private static final int SUCCESS_CODE = 0;
    private static final int LOGIN_OTHER_CODE = 201;
    public static final int NO_NET_WORK = -100;//无网络的情况
    private static final String S_NO_NET_WORK = "当前无网络，请重试";//无网络的情况
    private static final String KEY_CODE = "ErrorCode";
    private static final String KEY_OBJECT = "Data";
    private static final String KEY_ERROR = "Message";
    private static final String TIME_OUT = "timeout";
    private static final String SERVICE_ERRER = "服务器请求失败";
    private static final String TIME_OUT_STR = "请求超时，稍后重试";
    private Type type;//gson 初始化
    private Context mContext;

    public void setCallBackBean(Type t) {
        type = t;
    }

    /**
     * 设置上下文
     *
     * @param mContext 上下文
     */
    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onSuccess(String json) {
        try {
            if (null == json || "".equals(json) || json.length() == 0 || json.equals("null")) {
                onError(OkHttpUtils.DEFAULT_ERROR_CODE, "");
                LogUtils.e("Error", "数据为空");
                return;
            }
            final int code = new JSONObject(json).getInt(KEY_CODE);
            if (code == SUCCESS_CODE) {
                T obj = null;
                final String message = new JSONObject(json).getString(KEY_ERROR);

                if (!json.contains(KEY_OBJECT)) {
                    onSuccess(null, message);
                } else {
                     String content = new JSONObject(json).getString(KEY_OBJECT);
                    //如果是String 就直接返回不需要解析
                    if(type ==String.class){
                        obj= (T) content;
                    }else{
                     obj = new Gson().fromJson(content, type);
                    }
                    onSuccess(obj, message);

                    LogUtils.i("OnSuccess", content);
                }

            } else if (code == LOGIN_OTHER_CODE) {
                final String content = new JSONObject(json).getString(KEY_ERROR);
               /* CarApplication.otherLogin(content);*/
                LogUtils.i("OnError", content);
            } else {
                String content = new JSONObject(json).getString(KEY_ERROR);
                onError(code, content);
                LogUtils.i("OnError", content);
            }
        } catch (Exception e) {
            onError(OkHttpUtils.DEFAULT_ERROR_CODE, e.toString());
            e.printStackTrace();
        }
    }

    public abstract void onSuccess(T response, String message);

    public abstract void onErrorT(int code, T response, String errorMessage);

    @Override
    public void onHttpErrorCall(int statusCode) {
        onErrorT(statusCode, null, SERVICE_ERRER);
        LogUtils.e(statusCode);
    }

    @Override
    public void onError(int code, String errorMessage) {
        super.onError(code, errorMessage);
        LogUtils.e(errorMessage);
        if (!NetworkUtils.isWorked(mContext)) {
            ToastManager.showShortToast(S_NO_NET_WORK);
            onErrorT(NO_NET_WORK, null, S_NO_NET_WORK);
        } else
            onErrorT(code, null, errorMessage);
        if(TIME_OUT.equals(errorMessage)){//网络超时
            ToastManager.showShortToast(TIME_OUT_STR);
        }
    }
}
