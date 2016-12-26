package com.retrofit.fileload;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.retrofit.utils.AppUtils;

/**
 * Created by zs on 2016/7/7.
 */
public class UpdateManager {

    private Context mContext;

    public UpdateManager(Context context) {
        this.mContext = context;
    }

    /**
     * 检测软件更新
     */
    public void checkUpdate(final boolean isToast) {
        /**
         * 在这里请求后台接口，获取更新的内容和最新的版本号
         */
        // 版本的更新信息
        String version_info = "更新内容\n" + "    1. 添加新东西\n" + "    2. 修复老bug\n" + "    ";
        String mVersion_code = AppUtils.getVersionName(mContext);// 当前的版本号
        String nVersion_code = "1.1.0";

        showNoticeDialog(version_info);

//        if (!mVersion_code.equals(nVersion_code)) {
//            // 显示提示对话
//            showNoticeDialog(version_info);
//        } else {
//            if (isToast) {
//                Toast.makeText(mContext, "已经是最新版本", Toast.LENGTH_SHORT).show();
//            }
//        }
    }

    /**
     * 显示更新对话框
     *
     * @param version_info
     */
    private void showNoticeDialog(String version_info) {
        // 构造对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("更新提示");
        builder.setMessage(version_info);
        // 更新
        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                mContext.startService(new Intent(mContext, DownLoadService.class));
            }
        });
        // 稍后更新
        builder.setNegativeButton("以后更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog noticeDialog = builder.create();
        noticeDialog.show();
    }
}
