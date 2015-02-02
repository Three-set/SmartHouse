package com.android.demon;

import android.app.ActionBar.LayoutParams;
import android.app.Dialog;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class BrightnessDialog extends Dialog {

	/*
	 * private final Runnable mDismissDialogRunnable = new Runnable() { public
	 * void run() { if (BrightnessDialog.this.isShowing()) {
	 * BrightnessDialog.this.dismiss(); } }; };
	 */
	private Context mContext;
	private WindowManager mWindowManager;
	private View mView;

	public BrightnessDialog(Context context) {
		super(context);
		mContext = context;
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		final WindowManager.LayoutParams params = new WindowManager.LayoutParams();
		int flags = WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
		// Window window = getWindow();
		params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
		params.flags = flags;
		params.format = PixelFormat.TRANSLUCENT;
		params.width = LayoutParams.MATCH_PARENT;
		params.height = LayoutParams.MATCH_PARENT;
		params.gravity = Gravity.CENTER;
		//mView=getLayoutInflater().inflate(R.layout.activity_sample_light_control, null);
		mView = LayoutInflater.from(mContext).inflate(R.layout.activity_sample_light_control, null);
		//mWindowManager.addView(mView, params);
		// window.setType(WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG);
		// window.getAttributes().privateFlags |=
		// WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
		Window window = getWindow();
		window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		window.requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(mView);
		// setContentView(R.layout.activity_sample_light_control);
		setCanceledOnTouchOutside(true);
	}
}
