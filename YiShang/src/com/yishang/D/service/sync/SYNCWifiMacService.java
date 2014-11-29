package com.yishang.D.service.sync;

import com.exception.utils.P;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;
import com.yishang.D.service.httpRequest.HttpReq_SYNCAvosIfo;
import com.yishang.D.service.httpRequest.HttpReq_SYNCLocPoint;
import com.yishang.D.service.httpRequest.HttpReq_SYNCWifi;
import com.yishang.D.service.httpRequest.HttpReq_SYNCAvosIfo.CallBack_AVOS;
import com.yishang.D.service.httpRequest.HttpReq_SYNCLocPoint.CallBack_SYNCLOC;
import com.yishang.D.service.httpRequest.HttpReq_SYNCWifi.CallBack_Wifi;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * 同步WifiMac信息的服务
 * 
 * @author MM_Zerui
 * 
 */
public class SYNCWifiMacService extends Service {

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
		new RunnableService(new runCallBack() {
			
			@Override
			public void start() {
				// TODO Auto-generated method stub
				syncMethod();
			}
			
			@Override
			public void end() {
				// TODO Auto-generated method stub
				
			}
		}, true);
		
	}

	private void syncMethod() {
		// TODO Auto-generated method stub
		new HttpReq_SYNCWifi(new CallBack_Wifi(){

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				P.v("同步wifiMac成功");
			}

			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				P.v("同步wifiMac失败");
			}

			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
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
