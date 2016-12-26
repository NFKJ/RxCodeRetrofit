package com.downloader.utils;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

/**
  * @desc:         cpu信息工具类
  * @author:       Leo
  * @date:         2016/12/6
  */
public class CpuUtils
{
    /**
     * 获取cpu的核心数
     * @return
     */
    public static int getNumCores() {
        class CpuFilter implements FileFilter {

            @Override
            public boolean accept(File file) {
                if (Pattern.matches("cpu[0-9]", file.getName())) {
                    return true;
                }
                return false;
            }
        }

        try {
            // 获取cpu信息文件
            File dir = new File("sys/devices/system/cpu/");
            // 筛选出需要的文件
            File[] files = dir.listFiles(new CpuFilter());
            // 返回cpu的核心数
            return files.length;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
}
