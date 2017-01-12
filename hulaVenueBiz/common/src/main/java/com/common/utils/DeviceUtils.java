package com.common.utils;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.UUID;

/**
 * 设备相关工具类
 * Created by Leo on 2016/9/19.
 */
public class DeviceUtils {

    private DeviceUtils() {
        throw new UnsupportedOperationException("error...");
    }

    /**
     * 获取设备MAC地址
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>}</p>
     *
     * @param context 上下文
     * @return MAC地址
     */
    public static String getMacAddress(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        if (info != null) {
            String macAddress = info.getMacAddress();
            if (macAddress != null) {
                return macAddress.replace(":", "");
            }
        }
        return null;
    }

    /**
     * 获取设备厂商，如Xiaomi
     *
     * @return 设备厂商
     */
    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    /**
     * 获取设备型号，如MI2SC
     *
     * @return 设备型号
     */
    public static String getModel() {
        String model = Build.MODEL;
        if (model != null) {
            model = model.trim().replaceAll("\\s*", "");
        } else {
            model = "";
        }
        return model;
    }

    /**
     * 判断SDCard是否可用
     */
    public static boolean existSDCard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取设备详细信息
     * @return 设备详细信息
     */
    public static String getDeviceInfoDetail()
    {
        Context context = ContextUtils.getAppContext();
        try {
            org.json.JSONObject json = new org.json.JSONObject();
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);

            String device_id = tm.getDeviceId();

            android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);

            String mac = wifi.getConnectionInfo().getMacAddress();
            json.put("mac", mac);

            if (TextUtils.isEmpty(device_id)) {
                device_id = mac;
            }

            if (TextUtils.isEmpty(device_id)) {
                device_id = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
            }

            json.put("device_id", device_id);

            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取UUID
     * @return UUID
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     * 获取设备信息
     * @return <String> 设备信息
     */
    public static String getDeviceInfo() {
        return android.os.Build.MANUFACTURER + "_" + android.os.Build.MODEL + "_"
                + android.os.Build.VERSION.SDK + "_"
                + android.os.Build.VERSION.RELEASE + getDeviceId();
    }

    /**
     * 获取设备id
     * @return 设备ID
     */
    public static String getDeviceId() {
        TelephonyManager TelephonyMgr = (TelephonyManager) ContextUtils.getAppContext().getSystemService(Activity.TELEPHONY_SERVICE);
        return TelephonyMgr.getDeviceId();
    }
}
