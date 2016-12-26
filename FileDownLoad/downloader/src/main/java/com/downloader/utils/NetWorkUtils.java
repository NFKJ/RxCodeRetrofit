package com.downloader.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 1.显示连接已保存，但标题栏没有，即没有实质连上
 *          输出为：not connect, available
 * 2.显示连接已保存，标题栏也有已连接上的图标
 *          输出为：connect, available
 * 3.选择不保存后
 *          输出为：not connect, available
 * 4.选择连接，正在获取IP地址时
 *          输出为：not connect, not available
 * 5.连接上后
 *          输出为：connect, available
 * @desc:
 * @author:       Leo
 * @date:         2016/12/6
 */
public class NetWorkUtils {

    public static final int NETTYPE_WIFI = 1;
    public static final int NETTYPE_MOBILE = 2;

    /**
     * 向外暴露接口网络是否可用
     *
     * @param context
     *         上下文
     *
     * @return
     */
    public static boolean isCanBeUsed(Context context) {
        return isAvailable(context) && isConnected(context);
    }

    /**
     * 网络是否可用
     *
     * @param context
     *         上下文
     *
     * @return
     */
    private static boolean isAvailable(Context context) {
        NetworkInfo info = getActiveNetworkInfo(context);
        return info != null && info.isAvailable();
    }

    /**
     * 网络是否连接
     *
     * @param context
     *         上下文
     *
     * @return
     */
    private static boolean isConnected(Context context) {
        NetworkInfo info = getActiveNetworkInfo(context);
        return info != null && info.isConnected();
    }

    /**
     * 获取活动网络信息
     *
     * @param context
     *         上下文
     *
     * @return Networkinfo
     */
    private static NetworkInfo getActiveNetworkInfo(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo();
    }
}
