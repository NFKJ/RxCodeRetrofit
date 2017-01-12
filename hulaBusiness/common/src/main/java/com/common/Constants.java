package com.common;

 /**
  * @desc:         项目基础常量
  * @author:       Leo
  * @date:         2016/12/26
  */
public class Constants
 {
     public static final int SERVER_RESP_SUCCESS_CODE = 0;     // 服务端接口返回成功
     public static final int SERVER_RESP_TOKEN_EXPRIED = 4;    // 服务端接口Token失效

     public static final int DEFAULT_TIMEOUT = 10;    // retrofit超时时间
//     public static final String BaseUrl = "http://service.hulanet.cc/v3/";
     public final static String BaseUrl ="http://s5.hulanet.cc:8080/hlcg/";//内网
//     public final static String BaseUrl ="https://c.service.hulasports.com/hlcg/";//外网

     // AES加密
     public static String AES = "(.6tw+P0e67tgf87";
     public static String AESKEY = "7L26ZYCYRNQF43A3";
 }
