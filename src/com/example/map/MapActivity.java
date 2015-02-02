package com.example.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.demon.R;
import com.android.demon.util.AppConstants;
import com.radiusnetworks.ibeacon.IBeacon;
import com.radiusnetworks.ibeacon.IBeaconConsumer;
import com.radiusnetworks.ibeacon.IBeaconManager;
import com.radiusnetworks.ibeacon.RangeNotifier;
import com.radiusnetworks.ibeacon.Region;

/**
 * @author kelly
 *
 */
public class MapActivity extends Activity {

	private final static String TAG = "MainActivity";
	private IBeaconManager ibeaconMagager = IBeaconManager.getInstanceForApplication(this);
	private static ArrayList<Info> infos;
	private TextView textView;
	private ImageView imageView;
	private int num = 0;
	private Bitmap mapBitmap;
	private Bitmap tagBitmap;
	private Bitmap endBitmap;
	private Canvas canvas;
	private SensorManager sensorManager;
	private Sensor orientationSensor;
	private OrientationListener orientationListener;
	private Info info_1;
	private SensorEvent event_1;
	private static final int REQUEST_ENABLE_BT = 0;
	private CameraPreview cameraPreview;
	private ARView arView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		imageView = (ImageView) findViewById(R.id.imageview);
		textView = (TextView) findViewById(R.id.textview);
		cameraPreview = (CameraPreview) findViewById(R.id.camerapreview);
		arView = (ARView) findViewById(R.id.arview);
		infos = InfoFactory.getInfoslist();

		orientationListener = new OrientationListener();

		mapBitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.map));
		tagBitmap = BitmapFactory.decodeStream(getResources().openRawResource(R.drawable.tag));
		tagBitmap = bitmapScale(tagBitmap, 0.1f, 0.1f);
		endBitmap = Bitmap.createBitmap(mapBitmap.getWidth(), mapBitmap.getHeight(), mapBitmap.getConfig());
		canvas = new Canvas(endBitmap);

		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		orientationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		sensorManager.registerListener(orientationListener, orientationSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}

	// 閸掓稑缂撴穱鈩冧紖閺佺増宓�
	@Override
	protected void onDestroy() {
		super.onDestroy();
		sensorManager.unregisterListener(orientationListener);
	};

	private Bitmap createBitmap(Bitmap map, Bitmap tag, Info info, SensorEvent event) {

		Paint paint = new Paint();
		canvas.drawBitmap(map, 0, 0, paint);
		Matrix tagMatrix = new Matrix();
		if (event != null) {
			float rotate = event.values[0] - 50;

			if (rotate < 0)
				rotate = 360 + rotate;
			BUG.E(TAG, "" + rotate);
			tagMatrix.postRotate(rotate, tag.getWidth() / 2, tag.getHeight() / 2);
		}
		if (info != null)
			tagMatrix.postTranslate(info.getX() - tag.getWidth() / 2, info.getY() - tag.getHeight() / 2);
		else {
			tagMatrix.postTranslate(-50, -50);
		}
		canvas.drawBitmap(tag, tagMatrix, paint);
		return endBitmap;
	}

	private void SetMap(final Bitmap bitmap, final Info info) {

		imageView.post(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				// Bitmap bitmap2 = bitmap.copy(bitmap.getConfig(), true);
				imageView.setImageBitmap(bitmap);
				if (info != null)
					textView.setText(info.getText());
			}
		});
	}

	private Bitmap bitmapScale(Bitmap bitmap, float x, float y) {
		Matrix matrix = new Matrix();
		matrix.postScale(x, y); // 闂�鍨嫲鐎硅姤鏂佹径褏缂夌亸蹇曟畱濮ｆ柧绶�
		Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		return resizeBmp;
	}

	class OrientationListener implements SensorEventListener {

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSensorChanged(SensorEvent event) {
			// TODO Auto-generated method stub
			event_1 = event;

			int pitch = new Float(event.values[1]).intValue(); // (-180 to 180)
			if (pitch < 45 && pitch > -45) {
				if (arView.isShown())
					arView.setVisibility(View.INVISIBLE);
				if (!imageView.isShown())
					imageView.setVisibility(View.VISIBLE);
				if (cameraPreview.isCamera()) {
					cameraPreview.stopCamera();
				}
				Bitmap endBitmap = createBitmap(mapBitmap, tagBitmap, info_1, event_1);
				SetMap(endBitmap, info_1);
			} else {
				if (!arView.isShown())
					arView.setVisibility(View.VISIBLE);
				if (imageView.isShown())
					imageView.setVisibility(View.INVISIBLE);
				if (!cameraPreview.isCamera())
					cameraPreview.startCamera();
				arView.updateOrientation(event);
				arView.invalidate();
			}
		}
	}

	class PostionBroadcastReciver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if (intent.getAction().equals(AppConstants.ACTION_BROADCAST_BLE_LOCATION)) {
				int major = intent.getIntExtra("major", 10086);
				int minor = intent.getIntExtra("minor", 10086);
				info_1 = InfoFactory.getinfo(infos, minor);
				if (info_1 != null) {
					Bitmap endBitmap = createBitmap(mapBitmap, tagBitmap, info_1, event_1);
					SetMap(endBitmap, info_1);
				}
			}
		}
	}
}
