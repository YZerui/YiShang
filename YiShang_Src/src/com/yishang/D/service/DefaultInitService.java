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
 * 默认内容的初始化服务
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
	 * 执行  @商机 @人脉 @资源 @企业 
	 * 的初始化
	 */
	private void method() {
		//商机
		T_Msg msgBean=new T_Msg();
		msgBean.setMsg_source(Enum_PushSource.SYSTEM.value());
		msgBean.setMsg_type(Enum_PushType.SYS_INFORM.value());
		msgBean.setMsg_content(W_Msg.SYSDefault);
		msgBean.setMsg_sendName("易商小秘书");
		msgBean.setMsg_time(FormatUtils.getCurrentDateValue());
		msgBean.setSelf_id(Dao_Self.getInstance().getUser_id());
		//默认内容的ID为-1
		msgBean.setMsg_id(-1);
		try {
			Dao_Msg.addPushRecord(msgBean);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//人脉
		T_Relationships relaBean=new T_Relationships();
		relaBean.setRela_name("易商小秘书");
		relaBean.setRela_type(0);
		relaBean.setRela_rank("易商小秘书为你提供帮助");
		relaBean.setRela_id("-1");
		relaBean.setRela_phone("18676903277");
		Dao_Relationship.addSysContact(relaBean);
		
		//资源
		T_Resource resBean=new T_Resource();
		resBean.setBook_id("-1");
		resBean.setCom_name("锐峰智盟");
		resBean.setBook_name("易商-让业绩飞");
		resBean.setSender_name("小秘书");
		resBean.setBook_creater_id(Dao_Self.getInstance().getUser_id());
		resBean.setBook_url("http://es.wisdomeng.com/book_001");
		Dao_Resource.addResRecord(resBean);
		
		//企业
		T_Company comBean=new T_Company();
		comBean.setCom_id("8a211dcc4a0f5c30014a0f5c3f510001");
		comBean.setCom_name("锐峰智盟");
		comBean.setCom_icon("http://es.wisdomeng.com:80//file/head/141767158120265VkYcn.jpg");
		comBean.setCom_abb("锐峰智盟");
		comBean.setCom_remark("东莞市锐峰智盟信息技术有限公司");
		comBean.setCom_relate(Enum_ComType.COM_SUPPLIER.value());//默认为供应商（关注）
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
