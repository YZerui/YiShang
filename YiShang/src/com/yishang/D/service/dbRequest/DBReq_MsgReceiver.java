package com.yishang.D.service.dbRequest;

import java.util.List;

import com.exception.utils.P;
import com.format.utils.DataValidate;
import com.lidroid.xutils.exception.DbException;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;
import com.yishang.C.dao.daoImpl.Dao_Msg;
import com.yishang.C.dao.daoModel.T_Msg;

/**
 * ��ȡ��ҵ�����Ϣ��ģ��
 * @author MM_Zerui
 *
 */
public class DBReq_MsgReceiver {
	private CallBack_MsgRecv callBack;
	private String uID;
	public DBReq_MsgReceiver(String uID,CallBack_MsgRecv callBack){
		this.callBack=callBack;
		this.uID=uID;
	}
	public void onInit(){
		callBack.index=0;
		getMethod(true);
	}
	public void onLoad(){
		getMethod(false);
	}
	private void getMethod(final boolean ifInit) {
		new RunnableService(new runCallBack() {
			
			@Override
			public void start() {
				// TODO Auto-generated method stub
				try {
					List<T_Msg> list=Dao_Msg.getAllRecord_USER(uID,callBack.index++);
					if(DataValidate.checkDataValid(list)){
						callBack.callInit(list);
						return;
					}
					if(ifInit){
						callBack.onFail();
						return;
					}
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					P.v(e.getMessage());
				}
			}
			
			@Override
			public void end() {
				// TODO Auto-generated method stub
				callBack.onFinally();
			}
		}, true);
	}
	public static abstract class CallBack_MsgRecv{
		public int index=0;
		public abstract void callInit(List<T_Msg> datas);
		public abstract void callLoad(List<T_Msg> datas);
		public abstract void onFail();
		public abstract void onFinally();
	}
}
