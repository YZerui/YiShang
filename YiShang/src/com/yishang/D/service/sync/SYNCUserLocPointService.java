package com.yishang.D.service.sync;

import com.yishang.D.service.httpRequest.HttpReq_SYNCLocPoint;
import com.yishang.D.service.httpRequest.HttpReq_SYNCLocPoint.CallBack_SYNCLOC;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * 同步用户经纬度的服务
 * 
 * @author MM_Zerui
 * 
 */
public class SYNCUserLocPointService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		syncMethod();
	}

	private void syncMethod() {
		// TODO Auto-generated method stub
		new HttpReq_SYNCLocPoint(new CallBack_SYNCLOC() {

			@Override
			public void onSuccess() {
				
			}

			@Override
			public void onFail() {

			}

			@Override
			public void onFinally() {
//				onDestroy();
				stopSelf();
			}
		});
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
	}
}
