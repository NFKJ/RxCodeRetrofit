package com.downloader.manager;

import android.text.TextUtils;

import com.downloader.listener.GetFileCountListener;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @desc:
 * @author: Leo
 * @date: 2016/12/06
 */
public class GetFileCountTask implements Runnable
{
    private Call<ResponseBody> mResponseCall;
    private GetFileCountListener mGetFileCountListener;

    public GetFileCountTask(Call<ResponseBody> mResponseCall, GetFileCountListener mGetFileCountListener) {
        this.mResponseCall = mResponseCall;
        this.mGetFileCountListener = mGetFileCountListener;
    }

    @Override
    public void run() {
        Response response = null;

        try {
            response = mResponseCall.execute();

            if (response.isSuccessful()) {
                if (mGetFileCountListener != null) {

                    boolean isSupportMulti = !TextUtils.isEmpty(response.headers().get("Content-Range"))
                            && !TextUtils.isEmpty(response.headers().get("Content-Length"));
                    boolean isNew = response.code() != 206;
                    String modified = response.headers().get("Last-Modified");
                    Long fileSize = Long.parseLong(response.headers().get("Content-Range").split("/")[1]);
                    mGetFileCountListener.success(isSupportMulti, isNew, modified, fileSize);
                }
            } else {
                if (mGetFileCountListener != null) {
                    mGetFileCountListener.failed();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            if (mGetFileCountListener != null) {
                mGetFileCountListener.failed();
            }
        } finally {
            if (response.body() != null) {
                ((ResponseBody) response.body()).close();
            }
        }
    }
}
