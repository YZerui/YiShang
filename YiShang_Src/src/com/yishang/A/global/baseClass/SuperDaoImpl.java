package com.yishang.A.global.baseClass;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.yishang.A.global.application.AppContextApplication;


/**
 * dao实现层的超类，完成数据存储工具的初始化
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
