package com.yishang.C.dao.daoImpl;

import java.util.List;

import com.lidroid.xutils.exception.DbException;
import com.yishang.A.global.baseClass.SuperDaoImpl;
import com.yishang.C.dao.daoModel.T_MsgBuffer;

/**
 * 消息缓冲表的操作
 * @author MM_Zerui
 *
 */
public class Dao_MsgBuffer extends SuperDaoImpl{
	public static void clearRecord(){
		try {
			db.deleteAll(T_MsgBuffer.class);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void addRecord(List<T_MsgBuffer> listDatas){
		try {
			db.saveAll(listDatas);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static List<T_MsgBuffer> getAll(){
		try {
			return db.findAll(T_MsgBuffer.class);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
