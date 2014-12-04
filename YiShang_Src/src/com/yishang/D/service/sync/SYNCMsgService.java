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
 * 同步商机的服务
 * @author MM_Zerui
 * 1. 如果为初始登录（条件：商机表T_Msg相应信息为空）则同步一周内的所有商机到T_MsgBuffer
   2. 如果不为空，获取最近一条商机的信息，并请求此ID后的商机信息到T_MsgBuffer 	
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
				//开启前销毁缓冲
				Dao_MsgBuffer.clearRecord();
				//如果存在商机记录
				httpReq=new HttpReq_GetMsg();
				httpReq.setMsgSource(Enum_ReqMsgSource.ALL);
				T_Msg msgBean=Dao_Msg.getNewstMsg();
				if(DataValidate.checkDataValid(msgBean)){
					//这里加一,表示获取该商机之后的数据
					httpReq.setMsgID(msgBean.getMsg_id()+1);
				}else {
					//如果为初始登录
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
				//当数据获取完毕时开始转入T_MsgBuffer和T_Msg表单 更新操作
				P.v("销毁商机信息下载服务");
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
		P.v("开启同步本地商机服务");
		ViewSwitchUtils.startService(getApplicationContext(), SYNCDbMsgService.class);
		
		
//		super.onDestroy();
	}
}
