package com.yishang.D.service.sync;

import java.util.List;

import com.exception.utils.P;
import com.format.utils.DataValidate;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;
import com.yishang.A.global.Enum.constant.Enum_Timestamp;
import com.yishang.A.global.Enum.http.Enum_ReqMsgSource;
import com.yishang.C.dao.daoImpl.Dao_Msg;
import com.yishang.C.dao.daoImpl.Dao_MsgBuffer;
import com.yishang.C.dao.daoImpl.Dao_MsgSeq;
import com.yishang.C.dao.daoModel.T_Msg;
import com.yishang.C.dao.daoModel.T_MsgBuffer;
import com.yishang.D.service.httpRequest.HttpReq_GetMsg;
import com.yishang.D.service.httpRequest.HttpReq_GetMsg.CallBack;
import com.yishang.Z.utils.FormatUtils;
import com.yishang.Z.utils.ViewSwitchUtils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * ͬ���̻��ķ���
 * @author MM_Zerui
 * 1. ���Ϊ��ʼ��¼���������̻���T_Msg��Ӧ��ϢΪ�գ���ͬ��һ���ڵ������̻���T_MsgBuffer
   2. �����Ϊ�գ���ȡ���һ���̻�����Ϣ���������ID����̻���Ϣ��T_MsgBuffer 	
 *
 */
public class SYNCMsgService extends Service{
	private HttpReq_GetMsg httpReq;
	private String getWeekBeforeTimeStamp(){
		return String.valueOf(FormatUtils.getCurrentDateValue_long()-Enum_Timestamp.DAY.value()*7);
	}
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		
		new RunnableService(new runCallBack() {
			
			@Override
			public void start() {
				// TODO Auto-generated method stub
				//����ǰ���ٻ���
				Dao_MsgBuffer.clearRecord();
				//��������̻���¼
				httpReq=new HttpReq_GetMsg();
				httpReq.setMsgSource(Enum_ReqMsgSource.ALL);
				T_Msg msgBean=Dao_Msg.getNewstMsg();
				if(DataValidate.checkDataValid(msgBean)){
					//�����һ,��ʾ��ȡ���̻�֮�������
					httpReq.setMsgID(msgBean.getMsg_id()+1);
				}else {
					//���Ϊ��ʼ��¼
					httpReq.setTime(getWeekBeforeTimeStamp());
				}
				httpMethod();
			}
			
			@Override
			public void end() {
				// TODO Auto-generated method stub
				
			}
		}, true);
	
	
	}
	private void httpMethod(){
		
		httpReq.setCallBack(new CallBack() {
			
			@Override
			public void onInit(List<T_MsgBuffer> list) {
				// TODO Auto-generated method stub
				
				Dao_MsgBuffer.addRecord(list);
				httpReq.onLoad();
			}
			
			@Override
			public void onLoad(List<T_MsgBuffer> list) {
				// TODO Auto-generated method stub
				Dao_MsgBuffer.addRecord(list);
				httpReq.onLoad();
			}
		
			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
			
			}
			
			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				//�����ݻ�ȡ���ʱ��ʼת��T_MsgBuffer��T_Msg�� ���²���
				P.v("�����̻���Ϣ���ط���");
//				Dao_MsgBuffer.clearRecord();
				stopSelf();
			
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
		P.v("����ͬ�������̻�����");
		ViewSwitchUtils.startService(getApplicationContext(), SYNCDbMsgService.class);
		
		
//		super.onDestroy();
	}
}
