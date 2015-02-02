package com.example.zhihuijiaju;

import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

	private SurfaceHolder surfaceHolder;
	private Camera camera;
	private boolean flag;

	public CameraPreview(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public CameraPreview(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public CameraPreview(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		surfaceHolder = getHolder();
		surfaceHolder.addCallback(this);
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		flag = false;
	}

	public void startCamera() {
		camera.startPreview();
		flag = true;
	}

	public void stopCamera() {
		if (camera != null) {
			camera.stopPreview();
			flag = false;
		}
	}

	public boolean isCamera() {
		return flag;
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {

		int cameranum = Camera.getNumberOfCameras();
		if (camera == null)
			camera = Camera.open();
		camera.setDisplayOrientation(90);
		try {
			camera.setPreviewDisplay(surfaceHolder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		camera.startPreview();
		flag = true;
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d("SurfaceHolder.Callback", "surfaceDestroyed");
		camera.stopPreview();
		camera.release();

	}

}
