package com.yishang.D.service.sync;

import java.util.ArrayList;

import com.lidroid.xutils.exception.DbException;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;
import com.yishang.A.global.Enum.Enum_ReceiverAction;
import com.yishang.A.global.Enum.com.Enum_ComRela;
import com.yishang.B.module.d.BusinessEntity.Recv_business;
import com.yishang.C.dao.daoImpl.Dao_Company;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.D.service.httpRequest.HttpReq_GetRelateCompany;
import com.yishang.D.service.httpRequest.HttpReq_GetRelateCompany.CallBack_RelaCom;
import com.yishang.Z.utils.BroadcastUtil;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * 同步关联企业信息的服务
 * 
 * @author MM_Zerui
 * 
 */
public class SYNCRelateCompanyService extends Service {
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
		new RunnableService(new runCallBack()
		{

			@Override
			public void start() {
				// TODO Auto-generated method stub
				initResource();
				httpMethod();
			}

			@Override
			public void end() {
				// TODO Auto-generated method stub
				
			}}, true);
	
	}

	private void initResource() {

	}

	/**
	 * 获取关联企业信息
	 */
	private void httpMethod() {
		new HttpReq_GetRelateCompany(Dao_Self.getInstance().getUser_id())
				.setType(Enum_ComRela.DEFAULT).setCallBack(new CallBack_RelaCom() {
					
					@Override
					public void onRefresh(ArrayList<Recv_business> list) {
						// TODO Auto-generated method stub
						try {
							Dao_Company.addRecvComRecord(list);
						} catch (DbException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					@Override
					public void onLoad(ArrayList<Recv_business> list) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onFinally() {
						// TODO Auto-generated method stub
//						onDestroy();
						stopSelf();
					}
					
					@Override
					public void onFail() {
						// TODO Auto-generated method stub
						
					}
				}).onInit();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//发送广播通知企业列表进行更新
//		BroadcastUtil.sendBroadCast(this, Enum_ReceiverAction.COMPANY.name());
		
	}
}
