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

import com.common.okhttp.beans.HVOrderInfoBean;
import com.common.okhttp.beans.HVCheckTicketBean;


import com.common.okhttp.enums.Enum;

public class IOrderService
{

	private static String _getFullURL(String method)
	{
		return new StringBuffer( Cons.GATEWAY_URL ).append("/").append(method).toString();
	}

	//获取订单详情
	public static void getOrderInfo(Context activity, String ticketsId, ObjectCallback<HVOrderInfoBean> callback)
	{
		callback.setCallBackBean(new TypeToken<HVOrderInfoBean>(){}.getType());
		callback.setContext(activity);

		Map<String, Object> params = new HashMap<>();
		params.put("ticketsId", ticketsId);

		Gson gson=new Gson();
		String json=gson.toJson(params);

		String url = _getFullURL("getOrderInfo");
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
		LogUtils.e("api=getOrderInfo	gson="+json);
	}

	//验票
	public static void checkTicket(Context activity, ObjectCallback<HVCheckTicketBean> callback)
	{
		callback.setCallBackBean(new TypeToken<HVCheckTicketBean>(){}.getType());
		callback.setContext(activity);

		Map<String, Object> params = new HashMap<>();

		Gson gson=new Gson();
		String json=gson.toJson(params);

		String url = _getFullURL("checkTicket");
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
		LogUtils.e("api=checkTicket	gson="+json);
	}
}
