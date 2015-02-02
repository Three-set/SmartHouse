package com.example.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import android.R.integer;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.android.demon.util.AppConstants;
import com.radiusnetworks.ibeacon.IBeacon;
import com.radiusnetworks.ibeacon.IBeaconConsumer;
import com.radiusnetworks.ibeacon.IBeaconManager;
import com.radiusnetworks.ibeacon.RangeNotifier;
import com.radiusnetworks.ibeacon.Region;

public class LocationService extends Service implements IBeaconConsumer {

	private IBeaconManager ibeaconmanager = IBeaconManager.getInstanceForApplication(this);
	private Region region = new Region("mymonitor", "d26d197e-4a1c-44ae-b504-dd7768870564", null, null);
	private LocationFilter ibeaconLocationThread;
	private Handler handlerIBeaconThread;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private Handler handlerService = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case AppConstants.IBEACON_LOCATION_DATA:
				Intent intent = new Intent();
				intent.setAction(AppConstants.ACTION_BROADCAST_BLE_LOCATION);
				intent.putExtra("major", msg.arg1);
				intent.putExtra("minor", msg.arg2);
				sendBroadcast(intent);
				break;
			default:
				break;
			}
		}

	};

	@Override
	public void onCreate() {
		super.onCreate();
		ibeaconmanager.bind(this);
		ibeaconLocationThread = new LocationFilter(getApplicationContext(), handlerService);
		
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		try {
			ibeaconmanager.stopRangingBeaconsInRegion(region);
		} catch (RemoteException e) {
			Toast.makeText(getApplicationContext(), "扫描信号错误", Toast.LENGTH_SHORT);
			e.printStackTrace();
		}
		ibeaconmanager.unBind(this);
		ibeaconLocationThread.getHandlerIbeaconThread().obtainMessage(AppConstants.IBEACON_LOCATION_EXIT).sendToTarget();;
		
	}

	@Override
	public void onIBeaconServiceConnect() {
		ibeaconmanager.setRangeNotifier(new RangeNotifier() {

			@Override
			public void didRangeBeaconsInRegion(Collection<IBeacon> arg0, Region arg1) {
				if(handlerIBeaconThread==null)
					handlerIBeaconThread=ibeaconLocationThread.getHandlerIbeaconThread();
				else
					handlerIBeaconThread.obtainMessage(AppConstants.IBEACON_SCANDATA_UPDATE, arg0).sendToTarget();;
			}
		});
		try {
			ibeaconmanager.startRangingBeaconsInRegion(region);
		} catch (RemoteException e) {
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), "扫描信号错误", Toast.LENGTH_SHORT);
		}
		ibeaconLocationThread.start();
	}
}
