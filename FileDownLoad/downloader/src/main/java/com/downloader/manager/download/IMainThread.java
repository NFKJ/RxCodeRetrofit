package com.downloader.manager.download;

 /**
  * @desc:         主线程接口类
  * @author:       Leo
  * @date:         2016/12/6
  */
public interface IMainThread {
    void post(Runnable runnable);
}
