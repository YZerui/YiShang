package com.yishang.D.service.sync;

import com.yishang.B.module.f.LoginEntity.Recv_userIfo;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.D.service.httpRequest.HttpReq_SYNCUserIfo;
import com.yishang.D.service.httpRequest.HttpReq_SYNCUserIfo.CallBack_UserIfo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * 同步用户信息的服务
 * @author MM_Zerui
 *
 */
public class SYNCUserIfoService extends Service{
	
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
//		initResource();
		httpUserIfo();
	}
	private void initResource() {
		
	}

	/**
	 * 获取用户信息完成初始化
	 */
	private void httpUserIfo() {
		new HttpReq_SYNCUserIfo(Dao_Self.getInstance().getUser_id(),new CallBack_UserIfo() {
			
			@Override
			public void onSuccess(Recv_userIfo result) {
				// TODO Auto-generated method stub
				Dao_Self.save(result);
			}
			
			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				
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
