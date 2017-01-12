package com.common.base;

import android.Manifest;

/**
  * @desc:         项目基础常量
  * @author:       Leo
  * @date:         2016/12/26
  */
public class Constants
 {
     public static final String ANDROID = "2";                                     // 安卓来源标识
     public static final String OFFICIAL_PHONE = "4005165454";                     // 官方电话

     public static final int REQUECT_CODE_CAMERA = 5001;                           // 相机权限
     public static final int REQUECT_CODE_PERMISSION = 5002;                       // 所有权限
     public static final String[] REQUECT_NEEDS = new String[]
             {Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE};         // 所有权限

     public static final int SERVER_RESP_SUCCESS_CODE = 0;        // 服务端接口返回成功
     public static final int SERVER_RESP_TOKEN_EXPRIED = 4;       // 服务端接口Token失效

     public static final int DEFAULT_TIMEOUT = 10;    // retrofit超时时间
//     public static final String BaseUrl = "http://service.hulanet.cc/v3/";
     public final static String BaseUrl ="http://s5.hulanet.cc:8080/hlcg/";//内网
//     public final static String BaseUrl ="https://c.service.hulasports.com/hlcg/";//外网

 }
