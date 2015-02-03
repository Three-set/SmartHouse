package com.example.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import android.R.integer;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.android.demon.util.AppConstants;
import com.example.map.Info;
import com.example.map.InfoFactory;
import com.radiusnetworks.ibeacon.IBeacon;

public class LocationFilter extends Thread {

	private Context myContext;
	private Handler handlerService, handlerIbeaconThread;
	private Looper looper;
	private int[] minors = new int[2];
	private int major;
	private int minor , lastminor;
	private ArrayList<Info> lists;
	public LocationFilter(Context context, Handler handlerService) {
		myContext = context;
		this.handlerService = handlerService;
		lists=InfoFactory.getInfoslist();
	}

	@Override
	public void run() {
		Looper.prepare();

		handlerIbeaconThread = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case AppConstants.IBEACON_SCANDATA_UPDATE:
					Collection<IBeacon> iBeacons = (Collection<IBeacon>) msg.obj;
					IBeacon iBeacon = getwellIbeacon(iBeacons);
					int minor = getCurrenMinor(iBeacon);
					if (minor != 10086&&minor!=lastminor) {
						Message message = handlerService.obtainMessage(AppConstants.IBEACON_LOCATION_DATA,major,minor);
						message.sendToTarget();
						lastminor=minor;
					}
					break;
				case AppConstants.IBEACON_LOCATION_EXIT:
					Looper.myLooper().quit();
					break;
				default:
					break;
				}
			}
		};
		Looper.loop();

	}

	public Handler getHandlerIbeaconThread() {
		return handlerIbeaconThread;
	}

	private IBeacon getwellIbeacon(Collection<IBeacon> arg0) {
		// TODO Auto-generated method stub
		List<IBeacon> iBeacons = new LinkedList<IBeacon>();
		for (IBeacon iBeacon : arg0) {
			int rssi_min = InfoFactory.getminRssi(lists, iBeacon.getMinor());
			if (iBeacon.getRssi() < -10 && iBeacon.getRssi() > rssi_min)
				iBeacons.add(iBeacon);
		}

		Collections.sort(iBeacons, new Comparator<IBeacon>() {

			@Override
			public int compare(IBeacon lhs, IBeacon rhs) {
				// TODO Auto-generated method stub
				if (lhs.getRssi() > rhs.getRssi()) {
					return -1;
				} else {
					return 1;
				}
			}
		});
		if (iBeacons.size() != 0) {
			return iBeacons.get(0);
		} else {
			return null;
		}
	}

	private int getCurrenMinor(IBeacon iBeacon) {
		// TODO Auto-generated method stub
		minors[0] = minors[1];
		if (iBeacon != null) {
			minors[1] = iBeacon.getMinor();
		} else {
			minors[1] = 10086;
		}
		if (minors[0] == minors[1])
			return minors[0];
		else {
			return 10086;
		}
	}


}
