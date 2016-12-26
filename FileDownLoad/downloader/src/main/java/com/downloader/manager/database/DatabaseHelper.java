package com.downloader.manager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
  * @desc:         数据缓存（如：查询历史数据）
  * @author:       Leo
  * @date:         2016/12/6
  */
public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "app.db";

    //数据库升级，放弃老数据
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DownLoadDatabase.DownLoad.CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DownLoadDatabase.DownLoad.DROP_SQL);
        onCreate(sqLiteDatabase);
    }

}
