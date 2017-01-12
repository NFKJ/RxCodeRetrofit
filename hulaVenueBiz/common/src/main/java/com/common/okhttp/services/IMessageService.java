package com.common.okhttp.services;

import com.common.okhttp.Cons;
import com.common.okhttp.base.OkHttpUtils;
import com.common.utils.LogUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.common.okhttp.zmoumall.http.ObjectCallback;
import okhttp3.MediaType;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import android.content.Context;

import com.common.okhttp.beans.HVMessageBean;


import com.common.okhttp.enums.Enum;

public class IMessageService
{

	private static String _getFullURL(String method)
	{
		return new StringBuffer( Cons.GATEWAY_URL ).append("message/").append(method).toString();
	}

	//最新消息
	public static void getLatestMessage(Context activity, ObjectCallback<ArrayList<HVMessageBean>> callback)
	{
		callback.setCallBackBean(new TypeToken<ArrayList<HVMessageBean>>(){}.getType());
		callback.setContext(activity);

		Map<String, Object> params = new HashMap<>();

		Gson gson=new Gson();
		String json=gson.toJson(params);

		String url = _getFullURL("getLatestMessage");
		OkHttpUtils.postString()
                .mediaType(MediaType.parse("application/json;charset=utf-8"))
               	.url(url)
                .content(json)
                .addHeader("Token",Cons.mToken)
                .addHeader("DeviceID",Cons.mDeviceId)
				.addHeader("DeviceType",Cons.mDeviceType)
				.addHeader("Version",Cons.mVersion)
				.addHeader("Data",Cons.getData(json))
                .tag(activity)
                .build()
                .execute(callback);
		LogUtils.e("api=getLatestMessage	gson="+json);
	}

	//消息列表
	public static void getMessageList(Context activity, Enum.EnumMessageType messageType, int pageIndex, int pageSize, ObjectCallback<ArrayList<HVMessageBean>> callback)
	{
		callback.setCallBackBean(new TypeToken<ArrayList<HVMessageBean>>(){}.getType());
		callback.setContext(activity);

		Map<String, Object> params = new HashMap<>();
		params.put("messageType", messageType.value);
		params.put("pageIndex", pageIndex);
		params.put("pageSize", pageSize);

		Gson gson=new Gson();
		String json=gson.toJson(params);

		String url = _getFullURL("getMessageList");
		OkHttpUtils.postString()
                .mediaType(MediaType.parse("application/json;charset=utf-8"))
               	.url(url)
                .content(json)
                .addHeader("Token",Cons.mToken)
                .addHeader("DeviceID",Cons.mDeviceId)
				.addHeader("DeviceType",Cons.mDeviceType)
				.addHeader("Version",Cons.mVersion)
				.addHeader("Data",Cons.getData(json))
                .tag(activity)
                .build()
                .execute(callback);
		LogUtils.e("api=getMessageList	gson="+json);
	}
}
