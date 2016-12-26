package com.filedownload;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.downloader.entity.DownLoadEntity;
import com.downloader.listener.DownLoadBackListener;
import com.downloader.manager.download.FileDownLoadManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView fileName;
    private TextView fileSize;
    private ProgressBar progressbar;
    private AppCompatCheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
        setListener();
    }

    private void initView() {
        fileName = (TextView) findViewById(R.id.file_name);
        fileSize = (TextView) findViewById(R.id.file_size);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        checkBox = (AppCompatCheckBox) findViewById(R.id.checkbox);
    }

    private void setListener() {
        checkBox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Log.i(" -- CHECK", b + "");
                if (b) {
                    start();
                } else {
                    stop();
                }
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.i(" -- CHECK", b + "");
                if (b) {
                    start();
                } else {
                    stop();
                }
            }
        });
    }

    private void start() {
        List<DownLoadEntity> list = new ArrayList<>();
        DownLoadEntity downLoadEntity = new DownLoadEntity();
        downLoadEntity.setUrl("http://dlsw.baidu.com/sw-search-sp/soft/80/10547/QQMusicForYQQ_12.57.3805.413_setup.1460617481.exe");
        downLoadEntity.setSaveName(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "QQ.exe");
        list.add(downLoadEntity);

        FileDownLoadManager.getInstance().download(list, "MainActivity", new DownLoadBackListener() {
            @Override
            public void onStart(double percent) {
                progressbar.setProgress((int) percent);
                Log.i("MainActivity--start", percent + "");
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onDownLoading(double percent) {
                progressbar.setProgress((int) percent);
                Log.i("MainActivity--ing", percent + "");
            }

            @Override
            public void onCompleted() {
                Log.i("MainActivity", "onCompleted");
            }

            @Override
            public void onError(DownLoadEntity downLoadEntity, Throwable throwable) {

            }
        });
    }

    private void stop() {
        FileDownLoadManager.getInstance().cancel("MainActivity");
    }
}
