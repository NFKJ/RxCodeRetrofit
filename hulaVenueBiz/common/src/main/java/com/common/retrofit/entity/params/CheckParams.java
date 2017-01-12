package com.common.retrofit.entity.params;

 /**
  * @desc:         版本检查更新
  * @author:       Leo
  * @date:         2017/1/9
  */
public class CheckParams
 {
     private String version;          // 版本号
     private String channel;          // 渠道号
     private String platform;         // 0 none, 1 ios, 2 android

     public CheckParams(String version, String channel, String platform) {
         this.version = version;
         this.channel = channel;
         this.platform = platform;
     }
 }
