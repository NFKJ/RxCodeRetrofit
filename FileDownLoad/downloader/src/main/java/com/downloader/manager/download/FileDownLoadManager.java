package com.downloader.manager.download;

import com.downloader.entity.DownLoadEntity;
import com.downloader.listener.DownLoadBackListener;
import com.downloader.manager.DownLoadRequest;
import com.downloader.manager.database.DownLoadDatabase;
import com.downloader.manager.retrofit.RetrofitManager;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @desc:         文件下载核心类
 * @author:       Leo
 * @date:         2016/12/6
 */
public class FileDownLoadManager {
    private DownLoadDatabase mDownLoadDatabase = new DownLoadDatabase(RetrofitManager.getInstance().context);
    private ExecutorService mExecutorService = Executors.newCachedThreadPool();

    //多线程下载文件最低大小10MB
    private final long MULTI_LINE = 10 * 1024 * 1024;

    //所有下载的Task
    private Map<String, DownLoadRequest> mDownLoadRequestMap = new ConcurrentHashMap<>();

    private static FileDownLoadManager instance ;

    private FileDownLoadManager(){
    }

    public static FileDownLoadManager getInstance(){
        if (instance == null) {
            synchronized (FileDownLoadManager.class){
                if (instance == null) {
                    instance = new FileDownLoadManager() ;
                }
            }
        }
        return instance ;
    }

    public void download(final List<DownLoadEntity> list, final String tag,
                         final DownLoadBackListener downLoadBackListener, final long multiline) {
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                DownLoadRequest downLoadRequest = new DownLoadRequest(mDownLoadDatabase, downLoadBackListener, list, multiline);
                downLoadRequest.start();
                mDownLoadRequestMap.put(tag, downLoadRequest);
            }
        });
    }

    //默认支持多线程下载
    public void download(final List<DownLoadEntity> list, final String tag, final DownLoadBackListener downLoadTaskListener) {
        download(list, tag, downLoadTaskListener, this.MULTI_LINE);
    }

    public void cancel(String tag) {
        if (!mDownLoadRequestMap.isEmpty()) {
            if (mDownLoadRequestMap.containsKey(tag)) {
                mDownLoadRequestMap.get(tag).stop();
                mDownLoadRequestMap.remove(tag);
            }
        }
    }
}