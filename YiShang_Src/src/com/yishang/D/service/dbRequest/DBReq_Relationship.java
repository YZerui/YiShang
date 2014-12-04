package com.yishang.D.service.dbRequest;

import java.util.List;

import com.format.utils.DataValidate;
import com.lidroid.xutils.exception.DbException;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;
import com.yishang.A.global.Enum.Enum_ListLimit;
import com.yishang.A.global.Enum.db.Enum_RelaType;
import com.yishang.C.dao.daoImpl.Dao_Relationship;
import com.yishang.C.dao.daoModel.T_Relationships;

/**
 * 人脉信息的本地获取
 * 
 * @author MM_Zerui
 * 
 */
public class DBReq_Relationship {
	private CallBack_Rela callBack;
	private Enum_RelaType Enum;
	private boolean ifInit;
	public DBReq_Relationship setCallBack(CallBack_Rela call, Enum_RelaType Enum) {
		this.callBack = call;
		this.Enum = Enum;
		return this;
	}

	public void callInit() {
		callBack.index = 0;
		setIfInit(true);
		getMethod(true);
	}

	public void callLoad() {
		setIfInit(false);
		getMethod(false);
	}

	public CallBack_Rela getCallBack() {
		return callBack;
	}

	public void setCallBack(CallBack_Rela callBack) {
		this.callBack = callBack;
	}

	public Enum_RelaType getEnum() {
		return Enum;
	}

	public DBReq_Relationship setEnum(Enum_RelaType enum1) {
		Enum = enum1;
		return this;
	}

	public boolean isIfInit() {
		return ifInit;
	}

	public void setIfInit(boolean ifInit) {
		this.ifInit = ifInit;
	}
	public DBReq_Relationship getAll(){
		callBack.index=Enum_ListLimit.DEFAULT.value();
		return this;
	}

	public void getMethod(final boolean ifInit) {
		new RunnableService(new runCallBack() {

			@Override
			public void start() {
				try {
					List<T_Relationships> list = Dao_Relationship.getRecord_Client(
							Enum, callBack.index++);
					if (DataValidate.checkDataValid(list)) {
						if (ifInit) {
							callBack.onInit(list);
						} else {
							callBack.onLoad(list);
						}

						return;
					}
					callBack.onFail();
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void end() {
				callBack.onFinally();
			}

		}, true);
	}

	public static abstract class CallBack_Rela {
		public int index = 0;

		public abstract void onInit(List<T_Relationships> list);

		public abstract void onLoad(List<T_Relationships> list);

		public abstract void onFail();
		public abstract void onFinally();
	}
}
