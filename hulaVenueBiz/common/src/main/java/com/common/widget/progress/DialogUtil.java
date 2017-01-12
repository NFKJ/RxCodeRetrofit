package com.common.widget.progress;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.R;

public class DialogUtil {

	/**
	 * 得到自定义的progressDialog
	 * 
	 * @param context
	 * @param msg
	 *            文字显示
	 * @return
	 */
	public static Dialog createLoadingDialog(Context context, String msg, Boolean canceledOnTouchOutside) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.loadingview_layout, null);// 得到加载view
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_loading_view);// 加载布局
		TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
		tipTextView.setText(msg);// 设置加载信息

		final JumpingBeans jumpingBeans1 = JumpingBeans.with(tipTextView).appendJumpingDots().build();

		Dialog loadingDialog = new Dialog(context, R.style.MyLoadingDialogStyle);// 创建自定义样式dialog
		loadingDialog.setCancelable(false); // 是否可以按“返回键”消失
		loadingDialog.setCanceledOnTouchOutside(canceledOnTouchOutside); // 点击加载框以外的区域
		loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
		// loadingDialog.show();

		loadingDialog.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss(DialogInterface dialog) {
				jumpingBeans1.stopJumping();
			}
		});

		return loadingDialog;
	}

	public static Dialog createOnlyLoadingDialog(Context context, Boolean canceledOnTouchOutside) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.loadingview_layout, null);// 得到加载view
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_loading_view);// 加载布局
		TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
		tipTextView.setVisibility(View.GONE);

		final JumpingBeans jumpingBeans1 = JumpingBeans.with(tipTextView).appendJumpingDots().build();

		Dialog loadingDialog = new Dialog(context, R.style.MyLoadingDialogStyle);// 创建自定义样式dialog
		loadingDialog.setCancelable(false); // 是否可以按“返回键”消失
		loadingDialog.setCanceledOnTouchOutside(canceledOnTouchOutside); // 点击加载框以外的区域
		loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
		// loadingDialog.show();

		loadingDialog.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss(DialogInterface dialog) {
				jumpingBeans1.stopJumping();
			}
		});

		return loadingDialog;
	}

}
