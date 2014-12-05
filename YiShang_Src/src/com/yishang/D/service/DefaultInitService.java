package com.yishang.D.service;

import com.lidroid.xutils.exception.DbException;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;
import com.yishang.A.global.Enum.com.Enum_ComType;
import com.yishang.A.global.Enum.push.Enum_PushSource;
import com.yishang.A.global.Enum.push.Enum_PushType;
import com.yishang.A.global.writting.W_Msg;
import com.yishang.C.dao.daoImpl.Dao_Company;
import com.yishang.C.dao.daoImpl.Dao_Msg;
import com.yishang.C.dao.daoImpl.Dao_Relationship;
import com.yishang.C.dao.daoImpl.Dao_Resource;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.C.dao.daoModel.T_Company;
import com.yishang.C.dao.daoModel.T_Msg;
import com.yishang.C.dao.daoModel.T_Relationships;
import com.yishang.C.dao.daoModel.T_Resource;
import com.yishang.Z.utils.FormatUtils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Ĭ�����ݵĳ�ʼ������
 * @author MM_Zerui
 *
 */
public class DefaultInitService extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		new RunnableService(new runCallBack() {
			
			@Override
			public void start() {
				// TODO Auto-generated method stub
				method();
			}
		

			@Override
			public void end() {
				// TODO Auto-generated method stub
				
			}
		}, true);
	}
	
	/**
	 * ִ��  @�̻� @���� @��Դ @��ҵ 
	 * �ĳ�ʼ��
	 */
	private void method() {
		//�̻�
		T_Msg msgBean=new T_Msg();
		msgBean.setMsg_source(Enum_PushSource.SYSTEM.value());
		msgBean.setMsg_type(Enum_PushType.SYS_INFORM.value());
		msgBean.setMsg_content(W_Msg.SYSDefault);
		msgBean.setMsg_sendName("����С����");
		msgBean.setMsg_time(FormatUtils.getCurrentDateValue());
		msgBean.setSelf_id(Dao_Self.getInstance().getUser_id());
		//Ĭ�����ݵ�IDΪ-1
		msgBean.setMsg_id(-1);
		try {
			Dao_Msg.addPushRecord(msgBean);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//����
		T_Relationships relaBean=new T_Relationships();
		relaBean.setRela_name("����С����");
		relaBean.setRela_type(0);
		relaBean.setRela_rank("����С����Ϊ���ṩ����");
		relaBean.setRela_id("-1");
		relaBean.setRela_phone("18676903277");
		Dao_Relationship.addSysContact(relaBean);
		
		//��Դ
		T_Resource resBean=new T_Resource();
		resBean.setBook_id("-1");
		resBean.setCom_name("�������");
		resBean.setBook_name("����-��ҵ����");
		resBean.setSender_name("С����");
		resBean.setBook_creater_id(Dao_Self.getInstance().getUser_id());
		resBean.setBook_url("http://es.wisdomeng.com/book_001");
		Dao_Resource.addResRecord(resBean);
		
		//��ҵ
		T_Company comBean=new T_Company();
		comBean.setCom_id("8a211dcc4a0f5c30014a0f5c3f510001");
		comBean.setCom_name("�������");
		comBean.setCom_icon("http://es.wisdomeng.com:80//file/head/141767158120265VkYcn.jpg");
		comBean.setCom_abb("�������");
		comBean.setCom_remark("��ݸ�����������Ϣ�������޹�˾");
		comBean.setCom_relate(Enum_ComType.COM_SUPPLIER.value());//Ĭ��Ϊ��Ӧ�̣���ע��
		try {
			Dao_Company.addComRecord(comBean);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stopSelf();
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}


	

}
