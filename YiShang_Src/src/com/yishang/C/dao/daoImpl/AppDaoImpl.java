package com.yishang.C.dao.daoImpl;

import android.content.Context;

import com.yishang.A.global.application.AppContextApplication;
import com.yishang.A.global.constant.CONSTANT;
import com.yishang.Z.utils.DBUtils;

/**
 * �����û���Ϣ��Dao��ʵ����
 * @author MM_Zerui
 * @param USERTOKEN �û���ݱ�ʶ
 *
 */
public class AppDaoImpl {
	private static Context context;
	static{
		context=AppContextApplication.getInstance();
	}
	
	private static String userToken;
	
	public static String getUserToken() {
		System.out.println("UserToken:"+DBUtils.getSharedPreStr(context,CONSTANT.DB_USERTOKEN,null));
		return DBUtils.getSharedPreStr(context,CONSTANT.DB_USERTOKEN,null);
	}
	public static void setUserToken(String userToken) {
		DBUtils.setSharedPreStr(context, CONSTANT.DB_USERTOKEN,userToken);
	}
	public static void clear(){
		setUserToken("");
	}
	

}
