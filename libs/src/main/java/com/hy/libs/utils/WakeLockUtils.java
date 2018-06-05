package com.hy.libs.utils;

import java.util.HashMap;
import java.util.Map;

import android.app.Service;
import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

/**
 * @ClassName: WakeLockUtils
 * @Description: 手机休眠锁管理
 * @author 毛华磊
 * @date 2016年6月10日 下午3:26:23
 * 
 */
public class WakeLockUtils {

	private static WakeLockUtils instance;

	private static Map<String, WakeLock> mapSw = new HashMap<String, WakeLock>();

	public synchronized static WakeLockUtils getInstance() {

		if (instance == null) {
			instance = new WakeLockUtils();
		}
		return instance;
	}

	/**
	 * 申请 WakeLock
	 * 
	 * @param context
	 * @param service
	 * @param tag
	 */
	public void acquire(Context context, Service service, String tag) {

		PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
		WakeLock wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, service
				.getClass().getName());
		mapSw.put(tag, wakeLock);
		wakeLock.acquire();
	}

	/**
	 * 释放WakeLock
	 * 
	 * @param tag
	 */
	public void release(String tag) {

		if (mapSw.get(tag) != null) {
			mapSw.get(tag).release();
		}
	}
}
