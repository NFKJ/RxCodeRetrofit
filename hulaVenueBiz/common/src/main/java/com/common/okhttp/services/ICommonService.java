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

import com.common.okhttp.beans.HVVersionBean;


import com.common.okhttp.enums.Enum;

public class ICommonService
{

	private static String _getFullURL(String method)
	{
		return new StringBuffer( Cons.GATEWAY_URL ).append("common/").append(method).toString();
	}

	//检查版本更新
	public static void checkVersion(Context activity, String version, String channel, Enum.EnumPlatform platform, ObjectCallback<HVVersionBean> callback)
	{
		callback.setCallBackBean(new TypeToken<HVVersionBean>(){}.getType());
		callback.setContext(activity);

		Map<String, Object> params = new HashMap<>();
		params.put("version", version);
		params.put("channel", channel);
		params.put("platform", platform.value);

		Gson gson=new Gson();
		String json=gson.toJson(params);

		String url = _getFullURL("checkVersion");
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
		LogUtils.e("api=checkVersion	gson="+json);
	}
}
