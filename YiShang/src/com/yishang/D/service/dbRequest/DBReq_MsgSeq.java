package com.yishang.D.service.dbRequest;

import java.util.List;

import com.format.utils.DataValidate;
import com.lidroid.xutils.exception.DbException;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;
import com.yishang.A.global.Enum.push.Enum_PushSource;
import com.yishang.C.dao.daoImpl.Dao_Msg;
import com.yishang.C.dao.daoImpl.Dao_MsgSeq;
import com.yishang.C.dao.daoModel.T_MsgSeq;

/**
 * 商机信息的数据请求服务
 * @author MM_Zerui
 *
 */
public class DBReq_MsgSeq {
	private CallBack_Msg callBack;
	private Enum_PushSource sEnum;
	private boolean ifInit=false;
	
	public boolean isIfInit() {
		return ifInit;
	}
	
	public DBReq_MsgSeq(Enum_PushSource sEnum,CallBack_Msg call){
		this.callBack=call;
		this.sEnum=sEnum;
	}
	public DBReq_MsgSeq setSourceType(Enum_PushSource sEnum){
		this.sEnum=sEnum;
		return this;
	}
	public void onInit(){
		callBack.index=0;
		ifInit=true;
		getMethod();
	}
	public void onLoad(){
		ifInit=false;
		getMethod();
	}
	/**
	 * 数据读取方法
	 */
	public void getMethod(){
		new RunnableService(new runCallBack() {
			
			@Override
			public void start() {
				// TODO Auto-generated method stub
				try {
					List<T_MsgSeq> list=Dao_MsgSeq.getListDatas(sEnum, callBack.index++);
					if(DataValidate.checkDataValid(list)){
						callBack.onSuccess(list);
						return;
					}
					callBack.onFail();
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					callBack.onFail();
				}
			}
			
			@Override
			public void end() {
				// TODO Auto-generated method stub
				callBack.onFinally();
			}
		}, true);
	}
	public static abstract class CallBack_Msg{
		public int index = 0;
		public abstract void onSuccess(List<T_MsgSeq> datas);
		public abstract void onLoad(List<T_MsgSeq> datas);
		public abstract void onFail();
		public abstract void onFinally();
	}
}
