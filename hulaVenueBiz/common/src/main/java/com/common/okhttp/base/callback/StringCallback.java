package com.common.okhttp.base.callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by zhy on 15/12/14.
 */
public abstract class StringCallback extends Callback {
    @Override
    public String onResult(Response response) throws IOException {
        return response.body().string();
    }

    @Override
    public void onResponse(Object response) {
        this.onSuccess(response.toString());
    }

    @Override
    public void onHttpError(int statusCode) {
       this.onHttpErrorCall(statusCode);
    }

    public abstract void onSuccess(String response);
    public abstract void onHttpErrorCall(int statusCode);
}
