package com.yishang.D.service.sync;

import java.util.ArrayList;

import com.exception.utils.P;
import com.lidroid.xutils.exception.DbException;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;
import com.yishang.A.global.Enum.Enum_ReceiverAction;
import com.yishang.B.module.b.ContactsEntity.Recv_contacts;
import com.yishang.C.dao.daoImpl.Dao_Relationship;
import com.yishang.D.service.httpRequest.HttpReq_GetRelationship;
import com.yishang.D.service.httpRequest.HttpReq_GetRelationship.CallBack_Rela;
import com.yishang.Z.utils.BroadcastUtil;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * ͬ��������Ϣ�ķ���
 * @note �������˵�������Ϣ�б�
 * @note ���ڻ�ȡ����ÿ�������б���Ϣ�����Ա��ص�������Ϣ���и���
 * @note ���¹����У������ȡ��ĳ�û���Ϣ�����ڣ�����뵽�����У������ȡ��ĳ�û���Ϣ���ڣ�����¸��û���Ϣ��
 * @note ������Ϻ�֪ͨ��ͼҳ����и���
 * @author MM_Zerui
 *
 */
public class SYNCRelationshipService extends Service{
	private HttpReq_GetRelationship httpReq;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	
	}
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		httpReq=new HttpReq_GetRelationship();
		new RunnableService(new runCallBack(){

			@Override
			public void start() {
				// TODO Auto-generated method stub
				httpMethod();
			}

			@Override
			public void end() {
				// TODO Auto-generated method stub
				
			}
			
		}, true);
	}
	public void httpMethod(){
	httpReq.setCallBack(new CallBack_Rela() {
			
			@Override
			public void onSuccess(ArrayList<Recv_contacts> list) {
				// TODO Auto-generated method stub
				try {
					P.v("�����б��С:"+list.size());
					Dao_Relationship.addReqContact(list);
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					P.v("�������ݿ����:"+e.getMessage());
				}
				httpReq.onLoad();
			}
			
			@Override
			public void onLoad(ArrayList<Recv_contacts> list) {
				// TODO Auto-generated method stub
				try {
					Dao_Relationship.addReqContact(list);
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				httpReq.onLoad();
			}
			
			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				P.v("ͬ����̨������Ϣ����");
//				onDestroy();
				stopSelf();
			}

			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
				
			}
		}).onInit();
	}
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		//֪ͨ����ҳ����и���
		BroadcastUtil.sendBroadCast(this, Enum_ReceiverAction.CONTACTS_PAGE.name());
//		stopSelf();
		super.onDestroy();
	}

}
