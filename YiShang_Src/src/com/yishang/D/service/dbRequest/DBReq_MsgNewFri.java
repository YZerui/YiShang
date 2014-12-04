package com.yishang.D.service.dbRequest;

import java.util.List;

import com.format.utils.DataValidate;
import com.lidroid.xutils.exception.DbException;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;
import com.yishang.C.dao.daoImpl.Dao_MsgNewFri;
import com.yishang.C.dao.daoModel.T_MsgNewFir;

/**
 * –¬≈Û”—¡–±Ì
 * @author MM_Zerui
 *
 */
public class DBReq_MsgNewFri {
	private CallBack_NewFri callBack;
	
	public DBReq_MsgNewFri(CallBack_NewFri callBack) {
		this.callBack=callBack;
	}
	public DBReq_MsgNewFri onInit(){
		callBack.index=0;
		getMethod(true);
		return this;
	}
	public DBReq_MsgNewFri onLoad(){
		getMethod(false);
		return this;
	}
	public void getMethod(final boolean ifInit){
		new RunnableService(new runCallBack(){

			@Override
			public void start() {
				// TODO Auto-generated method stub
				try {
					List<T_MsgNewFir> listDatas=Dao_MsgNewFri.getAllRecord(callBack.index++);
					if(DataValidate.checkDataValid(listDatas)){
						if(ifInit){
							callBack.onSuccess(listDatas);
							return;
						}
						callBack.onLoad(listDatas);
						return;
					}
					if(ifInit){
						callBack.onFail();
					}
					
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void end() {
				// TODO Auto-generated method stub
				callBack.onFinally();
			}
			
		}, true);
	}
	public static abstract class CallBack_NewFri{
		public int index = 0;
		public abstract void onSuccess(List<T_MsgNewFir> datas);
		public abstract void onLoad(List<T_MsgNewFir> datas);
		public abstract void onFail();
		public abstract void onFinally();
	}
}
