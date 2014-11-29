package com.yishang.D.service.sync;

import com.yishang.D.service.httpRequest.HttpReq_SYNCAvosIfo;
import com.yishang.D.service.httpRequest.HttpReq_SYNCLocPoint;
import com.yishang.D.service.httpRequest.HttpReq_SYNCAvosIfo.CallBack_AVOS;
import com.yishang.D.service.httpRequest.HttpReq_SYNCLocPoint.CallBack_SYNCLOC;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * 同步AVOS信息的服务
 * 
 * @author MM_Zerui
 * 
 */
public class SYNCAvosIfoService extends Service {

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
		new HttpReq_SYNCAvosIfo(new CallBack_AVOS() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
//				onDestroy();
				stopSelf();
			}
			
			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
	}
}
