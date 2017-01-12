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

import com.common.okhttp.beans.HVLoginBean;
import com.common.okhttp.beans.HVVenueInfoBean;


import com.common.okhttp.enums.Enum;

public class IUserService
{

	private static String _getFullURL(String method)
	{
		return new StringBuffer( Cons.GATEWAY_URL ).append("user/").append(method).toString();
	}

	//登录
	public static void login(Context activity, String username, String password, ObjectCallback<HVLoginBean> callback)
	{
		callback.setCallBackBean(new TypeToken<HVLoginBean>(){}.getType());
		callback.setContext(activity);

		Map<String, Object> params = new HashMap<>();
		params.put("username", username);
		params.put("password", password);

		Gson gson=new Gson();
		String json=gson.toJson(params);

		String url = _getFullURL("login");
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
		LogUtils.e("api=login	gson="+json);
	}

	//退出登录
	public static void logout(Context activity, ObjectCallback<Object> callback)
	{
		callback.setCallBackBean(new TypeToken<Object>(){}.getType());
		callback.setContext(activity);

		Map<String, Object> params = new HashMap<>();

		Gson gson=new Gson();
		String json=gson.toJson(params);

		String url = _getFullURL("logout");
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
		LogUtils.e("api=logout	gson="+json);
	}

	//修改密码
	public static void changePassword(Context activity, String oldPassword, String replacePassword, ObjectCallback<Object> callback)
	{
		callback.setCallBackBean(new TypeToken<Object>(){}.getType());
		callback.setContext(activity);

		Map<String, Object> params = new HashMap<>();
		params.put("oldPassword", oldPassword);
		params.put("replacePassword", replacePassword);

		Gson gson=new Gson();
		String json=gson.toJson(params);

		String url = _getFullURL("changePassword");
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
		LogUtils.e("api=changePassword	gson="+json);
	}

	//场馆基本资料
	public static void getVenueInfo(Context activity, ObjectCallback<HVVenueInfoBean> callback)
	{
		callback.setCallBackBean(new TypeToken<HVVenueInfoBean>(){}.getType());
		callback.setContext(activity);

		Map<String, Object> params = new HashMap<>();

		Gson gson=new Gson();
		String json=gson.toJson(params);

		String url = _getFullURL("getVenueInfo");
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
		LogUtils.e("api=getVenueInfo	gson="+json);
	}

	//修改场馆公告
	public static void modifyVenueNotice(Context activity, String noticeContent, ObjectCallback<Object> callback)
	{
		callback.setCallBackBean(new TypeToken<Object>(){}.getType());
		callback.setContext(activity);

		Map<String, Object> params = new HashMap<>();
		params.put("noticeContent", noticeContent);

		Gson gson=new Gson();
		String json=gson.toJson(params);

		String url = _getFullURL("modifyVenueNotice");
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
		LogUtils.e("api=modifyVenueNotice	gson="+json);
	}
}
