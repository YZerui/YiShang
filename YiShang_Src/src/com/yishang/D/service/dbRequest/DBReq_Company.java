package com.yishang.D.service.dbRequest;

import java.util.List;

import com.format.utils.DataValidate;
import com.lidroid.xutils.exception.DbException;
import com.yishang.A.global.Enum.Enum_ListLimit;
import com.yishang.A.global.Enum.com.Enum_ComType;
import com.yishang.C.dao.daoImpl.Dao_Company;
import com.yishang.C.dao.daoModel.T_Company;
import com.yishang.C.dao.daoModel.T_Resource;

/**
 * ��������ҵ���ݿ��ȡ���ݵķ���
 * 
 * @��ע ����Ϊ��������ҵ���ͻ���ҵ����Ӧ����ҵ��ÿ�������ڲ���������ϵ��ʱ������
 * @author MM_Zerui
 * 
 */
public class DBReq_Company {
	private CallBack callBack;
	private Enum_ComType enumType;
	private boolean ifInit=false;
	
	public boolean isIfInit() {
		return ifInit;
	}

	public DBReq_Company setCallBack(CallBack callBack) {
		this.callBack = callBack;
		return this;
	}

	public DBReq_Company setComType(Enum_ComType enumType) {
		this.enumType = enumType;
		return this;
	}

	public DBReq_Company onInit() {
		ifInit=true;
		callBack.index=0;
		getMethod(true);
		callBack.onFinally();
		return this;
	}

	public DBReq_Company onLoad() {
		ifInit=false;
		getMethod(false);
		callBack.onFinally();
		return this;
	}
	public DBReq_Company getAll(){
		callBack.index=Enum_ListLimit.DEFAULT.value();
		return this;
	}
	public void getMethod(boolean ifInit) {
		try {
			List<T_Company> list = Dao_Company.getRecord(enumType,
					callBack.index++);
			if (DataValidate.checkDataValid(list)) {
				if (ifInit) {
					callBack.onInit(list);
				} else {
					callBack.onLoadMore(list);
				}
				return;

			}

		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		callBack.onFail();
	}

	public abstract static class CallBack {
		public int index = 0;

		public abstract void onFail();

		public abstract void onFinally();

		public abstract void onInit(List<T_Company> list);

		public abstract void onLoadMore(List<T_Company> list);
	}
}
