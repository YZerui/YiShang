package com.yishang.A.global.baseClass;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.yishang.A.global.application.AppContextApplication;


/**
 * daoʵ�ֲ�ĳ��࣬������ݴ洢���ߵĳ�ʼ��
 * @author MM_Zerui
 *
 */
public class SuperDaoImpl {
	protected static DbUtils db;
//	protected static Selector selector;
	static{
		db = DbUtils.create(AppContextApplication.getInstance());
		db.configAllowTransaction(true);
		db.configDebug(true);
	}
}
