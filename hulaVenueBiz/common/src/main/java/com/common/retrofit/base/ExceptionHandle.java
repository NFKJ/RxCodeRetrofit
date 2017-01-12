package com.common.retrofit.base;

import android.net.ParseException;

import com.common.retrofit.jsoncoverter.ApiException;
import com.common.utils.ContextUtils;
import com.common.utils.NetworkUtils;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;

import retrofit2.adapter.rxjava.HttpException;

public class ExceptionHandle {

    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ApiException handleException(Throwable e)
    {
        ApiException ex;

        if (e instanceof HttpException) {                     // HTTP错误
            HttpException httpException = (HttpException) e;
            ex = new ApiException(e, httpException.code());
            switch(httpException.code()){
                case UNAUTHORIZED:
                case FORBIDDEN:
                    break;
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex = new ApiException(e, ApiException.NETWORD_ERROR);
                    ex.setDisplayMessage("网络错误");          // 均视为网络错误
                    break;
            }
        } else if (e instanceof ConnectException
                || e instanceof java.net.SocketTimeoutException
                || !NetworkUtils.isWorked(ContextUtils.getAppContext()) ) {
            ex = new ApiException(e, ApiException.REQUEST_FILED);
            ex.setDisplayMessage("连接失败");                  // 服务器连接失败
        } else if (e instanceof ApiException){                // 服务器返回的错误
            ApiException resultException = (ApiException) e;
            ex = new ApiException(e, resultException.getCode());
            ex.setDisplayMessage(resultException.getMessage());
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex = new ApiException(e, ApiException.PARSE_ERROR);
            ex.setDisplayMessage("解析错误");                  // 均视为解析错误
        } else {
            ex = new ApiException(e, ApiException.UNKNOWN);
            ex.setDisplayMessage("未知错误");                  // 未知错误
        }

        return ex;
    }
}