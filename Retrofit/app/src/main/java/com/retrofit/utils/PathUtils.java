package com.retrofit.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * @desc:   view工具类
 * @author: Leo
 * @date:   2016/09/28
 */
public class PathUtils
{
    Context context;

    private static PathUtils sharedInstance = null;

    public static synchronized PathUtils get()
    {
        if (sharedInstance == null)
        {
            sharedInstance = new PathUtils();
        }
        return sharedInstance;
    }

    public PathUtils() { }

    public final static String ApplicationBasePath = Environment.getExternalStorageDirectory().getPath()
            + "/Android/data/" + ContextUtils.getContext().getPackageName() + "/cache";

    private final static String ApplicationImagePath = "/thumbs";
    public final static String ApplicationTempPath = "/temp/";
    private final static String ApplicationHttpCachPath = "/http";
    public static final String ApplicationLogPath = "/debuglog";
    public static final String ApplicationCrashLogPath = "/log";
    private static final String ApplicationLogUploadPath = "/debuglog/upload";
    private static final String ApplicationPhotoPath = "/local";
    private static final String ApplicationZipPath = "/zip";
    public final static String ApplicationUpdatePath = "/update";

    public void init(Context context) {
        if (this.context == null) {
            this.context = context;
        }
    }

    private void CheckOrCreateFolder(String fileName)
    {

        File audioFile = new File(fileName.substring(0, fileName.lastIndexOf("/")));
        if (!audioFile.exists())
        {
            audioFile.mkdirs();
        }
    }

    public String getImageSaveFolderPath()
    {
        String path = ApplicationBasePath + ApplicationImagePath + "/";
        this.CheckOrCreateFolder(path);
        return path;
    }

    public String getDcimFolderPath()
    {
        return Environment.getExternalStorageDirectory().getPath() + "/" + Environment.DIRECTORY_DCIM;
    }
}
