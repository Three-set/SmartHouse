package com.example.map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.crypto.EncryptedPrivateKeyInfo;

import com.android.demon.R;

import android.R.integer;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.SensorEvent;
import android.hardware.SensorListener;
import android.os.Handler;
import android.preference.PreferenceActivity.Header;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class ARView extends View {

	private final static int AR_BITMAP_HEIGHT = 100;
	private final static int AR_BITMAP_WITH = 3700;

	private final static String TAG = "ARView";
	private SensorEvent event;
	private Context context;
	private Paint paint;
	private Info currentinfo;
	private ArrayList<Info> infos;
	private Bitmap bitmapmark;
	private Handler handler;

	public ARView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	public ARView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public ARView(Context context) {
		super(context);
		init(context);

	}

	private void init(Context context) {
		this.context = context;
		paint = new Paint();
		paint.setColor(Color.BLUE);
		paint.setTextSize(50);
		infos = InfoFactory.getInfoslist();
		handler = new Handler();
		bitmapmark = BitmapFactory.decodeResource(getResources(), R.drawable.markback);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (event == null)
			return;
		// (0 to 359).0=North,90=East,180=South,270=West
		int azimuth = new Float(event.values[0]).intValue();
		azimuth-=135;
		if(azimuth<0)
			azimuth+=360;
		int pitch = new Float(event.values[1]).intValue(); // (-180 to180)
		for (Info infotemp : infos) {
	
			if (infotemp.getMinor() == 1 && infotemp.getOrientation() != 0.0) {
				
				if (Math.abs(infotemp.getOrientation() - azimuth) < 30||Math.abs(infotemp.getOrientation()-azimuth)>330) {
				//	BUG.D(TAG, "azimuth"+azimuth+" oritent:"+infotemp.getOrientation());
					drawImageinScreen(canvas, infotemp, azimuth, pitch);
				} else {
				}
			}
		}
	}

	private void drawImageinScreen(Canvas canvas, Info infotemp, int azimuth, int pitch) {
		double range= azimuth-infotemp.getOrientation();
		
		if(azimuth>340&&azimuth<360){
			range=azimuth-360-infotemp.getOrientation();
		}
	
		double temp = -range/ 28;
		float x = this.getWidth()/2+(float) (temp * this.getWidth()/2 ) - bitmapmark.getWidth() / 2;
		float y = 0 - bitmapmark.getHeight() / 2 + this.getHeight() / 2;
		canvas.drawBitmap(bitmapmark, x, y, paint);
	}

	public void updatelocation(Info info) {
		currentinfo = info;
		infos = InfoFactory.getInfoslist();
		calculateOrientation(info, infos);
	}

	public void updateOrientation(SensorEvent event) {
		this.event = event;
		this.invalidate();
	}

	void calculateOrientation(Info info, ArrayList<Info> infolist) {
		//BUG.E(TAG,"minor:"+info.getMinor()+ "x:"+info.getX()+" y:"+info.getY());
		for (Info infotemp : infolist) {
			infotemp.setX(infotemp.getX() - info.getX());
			infotemp.setY(infotemp.getY() - info.getY());
			//BUG.E(TAG,"x:"+infotemp.getX()+ "Y:"+infotemp.getY());
			double orientation = Math.atan2(infotemp.getY(), infotemp.getX());
			if (orientation < 0) {
				orientation += 2 * Math.PI;
			}
			infotemp.setOrientation(Math.toDegrees(orientation));
			
		}
	}
}
