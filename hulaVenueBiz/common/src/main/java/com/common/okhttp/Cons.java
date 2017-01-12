package com.common.okhttp;

import com.common.utils.AppUtils;
import com.common.utils.DeviceUtils;
import com.common.utils.LogUtils;
import com.common.utils.MD5Utils;

/**
 * ltx on 2016/11/7 16:27
 * 网络请求相关的常量
 */
public class Cons {
    public final static String GATEWAY_URL ="http://s5.hulanet.cc:8080/hlcg/";//内网
//    public final static String GATEWAY_URL ="http://114.55.9.234:8080/hlcg/";//外网
//    public final static String GATEWAY_URL ="https://c.service.hulasports.com/hlcg/";//外网
    public final static String hettpNet_URL ="http://service.hlcg.hulanet.cc/hlcg/";//外网接口

    public final static String URL_DOWNLOAD="https://pic.hulasports.com//f66f6352b26c4bc19189e5ac1c34981c.html";  //下载页面

    public static String mToken = "";
    public static String mDeviceId = DeviceUtils.getDeviceId();
    public static String mVersion = AppUtils.getVersionName();//版本号
    public static String mValue = "diiaoiiai";//固定值

    public static final String mDeviceType = "2"; //类型 android 为2

    /**
     * 获取拼接字符串
     * @param json json
     * @return
     */
    public static String getData(String json)
    {
        String mData;
        String mDataStr= mValue + "?DeviceType=" + mDeviceType + "&DeviceId=" +
                mDeviceId + "&Token=" + mToken + "&Content=" + json;
        mData= MD5Utils.MD5Encode(mDataStr);
        LogUtils.e("mDataStr="+mDataStr);
        LogUtils.e("mDataStrMD5="+mData);
        return mData;
    }
}
