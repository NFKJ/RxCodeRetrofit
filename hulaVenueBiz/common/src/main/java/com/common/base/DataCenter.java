package com.common.base;

import android.content.Context;

import com.common.okhttp.Cons;
import com.common.okhttp.beans.HVLoginBean;
import com.common.okhttp.beans.HVVenueInfoBean;
import com.common.utils.SPUtils;
import com.common.utils.StringUtils;
import com.google.gson.Gson;

/**
 * @desc:         数据保存
 * @author:       Leo
 * @date:         2017/1/6
 */
public class DataCenter
{
    private static DataCenter instance = null;
    public static final String TOKEN = "TOKEN";                    // token保存key
    public static final String USERNAME = "USERNAME";              // 用户名保存key
    public static final String ISFIRST = "ISFIRST";                // 是否首次启动保存key
    public static final String VENUEINFO = "VENUEINFO";            // 场馆详细信息保存key

    private String Token = "";
    private String userName = "";
    private HVVenueInfoBean venueInfoBean;

    private DataCenter(){
        instance = this;
    }

    public static void initDataCenter() {
        if (null == instance) {
            new DataCenter();
        }
    }

    public static DataCenter getInstance()
    {
        if (instance == null) {
            synchronized (DataCenter.class) {
                if (instance == null) {
                    instance = new DataCenter() ;
                }
            }
        }
        return instance ;
    }

    /**
     * 保存登录信息
     * @param context      con
     * @param response     登录返回信息
     */
    public static void saveLoginDataInfo(Context context, HVLoginBean response)
    {
        DataCenter.getInstance().setToken(response.getToken());
        SPUtils.setShareString(context, DataCenter.TOKEN, response.getToken());
        DataCenter.getInstance().setUserName(response.getToken());
        SPUtils.setShareString(context, DataCenter.USERNAME, response.getToken());
        DataCenter.getInstance().setVenueInfoBean(response.getVenueInfo());
        SPUtils.setShareJson(context, DataCenter.VENUEINFO, new Gson().toJson(response.getVenueInfo()));
    }

    /**
     * 清空登录信息
     * @param context      con
     */
    public static void deleteLoginDataInfo(Context context)
    {
        DataCenter.getInstance().setToken("");
        SPUtils.setShareString(context, DataCenter.TOKEN, "");
        DataCenter.getInstance().setUserName("");
        SPUtils.setShareString(context, DataCenter.USERNAME, "");
        DataCenter.getInstance().setVenueInfoBean(null);
        SPUtils.setShareJson(context, DataCenter.VENUEINFO, "");
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Cons.mToken = StringUtils.nullToStr(token);
        Token = StringUtils.nullToStr(token);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public HVVenueInfoBean getVenueInfoBean() {
        return venueInfoBean;
    }

    public void setVenueInfoBean(HVVenueInfoBean venueInfoBean) {
        this.venueInfoBean = venueInfoBean;
    }
}
