package com.downloader.manager;

import android.util.Log;

import com.downloader.entity.DownLoadEntity;
import com.downloader.listener.DownCallBackListener;
import com.downloader.listener.DownLoadBackListener;
import com.downloader.listener.DownLoadTaskListener;
import com.downloader.manager.database.DownLoadDatabase;
import com.downloader.manager.download.MainThreadImpl;
import com.downloader.utils.CpuUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @desc:
 * @author: Leo
 * @date: 2016/12/06
 */
public class DownLoadRequest
{
    private MainThreadImpl mainThread = MainThreadImpl.getInstance();

    //下载线程池
    private ExecutorService mDownLoadService;

    //多线程下载文件最低大小10MB
    private long mMultiLine = 10 * 1024 * 1024;

    private final long NEW_DOWN_BEGIN = 0;

    //下载结果回调
    private DownLoadBackListener mCallBackListener;

    //数据库
    private DownLoadDatabase mDownLoadDatabase;

    //URL下载Task
    private Map<String, Map<Integer, Future>> mUrlTaskMap = new ConcurrentHashMap<>();

    //下载的任务
    private List<DownLoadEntity> loadEntityList;

    //总回调
    private DownCallBackListener mDownCallBackListener;

    private DownLoadHandle mDownLoadHandle;

    public DownLoadRequest(DownLoadDatabase mDownLoadDatabase, DownLoadBackListener mCallBackListener, List<DownLoadEntity> loadEntityList, long mMultiLine) {
        this.mDownLoadDatabase = mDownLoadDatabase;
        this.mCallBackListener = mCallBackListener;
        this.loadEntityList = loadEntityList;
        this.mMultiLine = mMultiLine;
        mDownLoadHandle = new DownLoadHandle(mDownLoadDatabase);
        mDownLoadService = Executors.newFixedThreadPool(CpuUtils.getNumCores() + 1);
    }

    public void start() {
        //下面是一个耗时操作
        List<DownLoadEntity> queryList = mDownLoadHandle.queryDownLoadData(loadEntityList);
        Iterator iterator = queryList.iterator();
        long totalFileSize = 0;
        long hasDownSize = 0;
        while (iterator.hasNext()) {
            final DownLoadEntity downLoadEntity = (DownLoadEntity) iterator.next();
            hasDownSize += downLoadEntity.getDowned();
            if (downLoadEntity.getTotal() == 0) {
                mainThread.post(new Runnable() {
                    @Override
                    public void run() {
                        mCallBackListener.onError(downLoadEntity, new Throwable("文件读取失败"));
                    }
                });
                return;
            } else {
                totalFileSize += downLoadEntity.getTotal();
            }
        }

        if (hasDownSize >= totalFileSize) {   // 下载完成
            mainThread.post(new Runnable() {
                @Override
                public void run() {
                    mCallBackListener.onCompleted();
                }
            });
            return;
        }

        mDownCallBackListener = new DownCallBackListener(mCallBackListener, totalFileSize, hasDownSize);
        mDownCallBackListener.onStart();

        for (DownLoadEntity entity : loadEntityList) {
            if (entity.getDowned() == entity.getTotal()) {
                continue;
            }
            addDownLoadTask(entity);
        }
    }

    private void addDownLoadTask(DownLoadEntity entity) {
        Map<Integer, Future> downLoadTaskMap = new ConcurrentHashMap<>();
        MutiDownLoaderListener mutiDownLoaderListener = new MutiDownLoaderListener(mDownCallBackListener);
        if (entity.getMultiList() != null && entity.getMultiList().size() != 0) {
            int count = entity.getMultiList().size();
            for (int i = 0; i < count; i++) {
                DownLoadEntity loadEntity = entity.getMultiList().get(i);
                // 当前分支是否完成下载
                if (loadEntity.getDowned() + loadEntity.getStart() > loadEntity.getEnd()) {
                    continue;
                }
                DownLoadTask downLoadTask = new DownLoadTask.Builder()
                        .downLoadModel(loadEntity)
                        .downLoadTaskListener(mutiDownLoaderListener)
                        .build();
                executeNetWork(loadEntity, downLoadTask, downLoadTaskMap);
            }
        } else {
            // 文件不存在 直接下载
            createDownLoadTask(entity, NEW_DOWN_BEGIN, downLoadTaskMap, mutiDownLoaderListener);
        }
    }

    private void createDownLoadTask(DownLoadEntity entity, long beginSize, Map<Integer, Future> downLoadTaskMap, DownLoadTaskListener downLoadTaskListener) {
        long startSize, endSize;

        DownLoadTask downLoadTask;

        if (mMultiLine != 0 && entity.getTotal() > mMultiLine && entity.isSupportMulti()) {
            // 多线程下载
            int threadNum = (int) ((entity.getTotal() % mMultiLine == 0) ?
                    entity.getTotal() / mMultiLine : entity.getTotal() / mMultiLine + 1);

            for (int i = 0; i < threadNum; i++) {
                startSize = beginSize + i * mMultiLine;
                endSize = startSize + mMultiLine - 1;

                if (i == threadNum - 1) {
                    if (endSize > entity.getTotal()) {
                        endSize = entity.getTotal() - 1;
                    }
                }

                DownLoadEntity loadEntity = mDownLoadDatabase.insert(
                        entity.getUrl(), (int) startSize, (int) endSize,
                        (int) entity.getTotal(), entity.getSaveName(), entity.getLastModify());

                Log.i("DownLoadRequest === entity-M", loadEntity.toString());

                downLoadTask = new DownLoadTask.Builder().downLoadModel(loadEntity)
                        .downLoadTaskListener(downLoadTaskListener).build();

                executeNetWork(loadEntity, downLoadTask, downLoadTaskMap);
            }
        } else {
            // 单线程下载

            DownLoadEntity loadEntity = mDownLoadDatabase.insert(
                    entity.getUrl(), 0, (int) entity.getTotal() - 1,
                    (int) entity.getTotal(), entity.getSaveName(), entity.getLastModify());

            Log.i("DownLoadRequest === entity-S", loadEntity.toString());

            downLoadTask = new DownLoadTask.Builder().downLoadModel(loadEntity)
                    .downLoadTaskListener(downLoadTaskListener).build();

            executeNetWork(loadEntity, downLoadTask, downLoadTaskMap);
        }
    }

    private void executeNetWork(DownLoadEntity loadEntity, DownLoadTask downLoadTask, Map<Integer, Future> downLoadTaskMap) {
        downLoadTaskMap.put(loadEntity.getDataId(), mDownLoadService.submit(downLoadTask));
        mUrlTaskMap.put(loadEntity.getUrl(), downLoadTaskMap);
    }

    private class MutiDownLoaderListener implements DownLoadTaskListener
    {
        private DownLoadTaskListener downLoadTaskListener;
        //重复次数
        private int repeatCount = 10;

        public MutiDownLoaderListener(DownLoadTaskListener downLoadTaskListener) {
            this.downLoadTaskListener = downLoadTaskListener;
        }

        @Override
        public synchronized void onStart() {
            downLoadTaskListener.onStart();
        }

        @Override
        public synchronized void onCancel(DownLoadEntity downLoadEntity) {
            downLoadTaskListener.onCancel(downLoadEntity);
        }

        @Override
        public synchronized void onDownLoading(long downSize) {
            downLoadTaskListener.onDownLoading(downSize);
        }

        @Override
        public synchronized void onCompleted(DownLoadEntity downLoadEntity) {
            mDownLoadDatabase.update(downLoadEntity);
            if (!isRepeatExecute(downLoadEntity, repeatCount, this)) {
                if (removeTask(downLoadEntity)) {
                    downLoadTaskListener.onCompleted(downLoadEntity);
                }
            } else {
                repeatCount--;
            }
        }

        @Override
        public synchronized void onError(DownLoadEntity downLoadEntity, Throwable throwable) {
            mDownLoadDatabase.update(downLoadEntity);
            if (!isRepeatExecute(downLoadEntity, repeatCount, this)) {
                if (repeatCount <= 0) {
                    downLoadTaskListener.onError(downLoadEntity, throwable);
                }
            } else {
                repeatCount--;
            }
        }
    }

    private boolean removeTask(DownLoadEntity downLoadEntity) {
        Map<Integer, Future> map = mUrlTaskMap.get(downLoadEntity.getUrl());
        if (map.isEmpty()) {
            return true;
        }

        if (map.containsKey(downLoadEntity.getDataId())) {
            map.remove(downLoadEntity.getDataId());
        }

        if (map.size() == 0) {
            mUrlTaskMap.remove(downLoadEntity.getUrl());
        }

        return mUrlTaskMap.size() == 0;
    }

    private void cancel(String url) {
        Map<Integer, Future> downLoadMap = mUrlTaskMap.get(url);

        if (downLoadMap != null && downLoadMap.size() > 0) {
            Iterator iterator = downLoadMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Future> map = (Map.Entry<Integer, Future>) iterator.next();
                Future future = map.getValue();
                future.cancel(true);
                iterator.remove();
            }
            mUrlTaskMap.remove(url);
        }
    }

    //取消所有任务
    public void stop() {
        if (mUrlTaskMap.size() != 0) {
            Iterator iterator = mUrlTaskMap.keySet().iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                cancel(key);
                iterator.remove();
            }
        }
    }

    private boolean isRepeatExecute(DownLoadEntity downLoadEntity, int repeatCount,
                                    DownLoadTaskListener downLoadTaskListener) {
        if ((downLoadEntity.getDowned() + downLoadEntity.getStart() <= downLoadEntity.getEnd())
                && repeatCount > 0) {
            // 没下载完
            DownLoadTask downLoadTask = new DownLoadTask.Builder().downLoadModel(downLoadEntity)
                    .downLoadTaskListener(downLoadTaskListener).build();
            executeNetWork(downLoadEntity, downLoadTask, mUrlTaskMap.get(downLoadEntity.getUrl()));
            return true;
        }
        return false;
    }
}
