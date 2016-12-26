package com.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.retrofit.fileload.UpdateManager;

public class MainActivity extends AppCompatActivity
{
    private TextView download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        download = (TextView) findViewById(R.id.tv_download);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UpdateManager(MainActivity.this).checkUpdate(true);
            }
        });
    }
}
