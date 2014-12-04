package com.yishang.C.dao.daoImpl;

import java.util.List;

import android.content.Context;

import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import com.yishang.A.global.Enum.db.Enum_IfRegister;
import com.yishang.A.global.baseClass.SuperDaoImpl;
import com.yishang.A.global.constant.CONSTANT;
import com.yishang.A.global.constant.PARAMS;
import com.yishang.B.module.b.ContactsEntity.Recv_phoneCheck;
import com.yishang.C.dao.daoModel.T_Contacts;

/**
 * @author MM_Zerui
 *管理通讯录记录的数据库实现类
 */
public class Dao_Contacts extends SuperDaoImpl{
	
	public static  boolean checkContactsExist() {
		// TODO Auto-generated method stub
		try {
			T_Contacts bean=db.findFirst(T_Contacts.class);
			if(bean!=null){
				return true;
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static void insertContactsRecord(List<T_Contacts> beanList) throws DbException {
		// TODO Auto-generated method stub
		db.saveAll(beanList);
	}


	public static List<T_Contacts> getAllContactsRecord() {
		// TODO Auto-generated method stub
		List<T_Contacts> list;
		try {
			list = db.findAll(T_Contacts.class);
			return list;
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取所有未注册的手机用户
	 * @param index -1则默认获取全部
	 * @return
	 * @throws DbException 
	 */
	public static List<T_Contacts> getUnRegiPhones(int index) throws DbException {
		Selector selector=Selector.from(T_Contacts.class);
		selector.where("ifRegister","=",Integer.valueOf(Enum_IfRegister.UNREGIST.value()));
		if(index!=-1){
			selector.limit(CONSTANT.DAO_CONTACTS_UNREAD_LIMIT).
			offset(index*CONSTANT.DAO_CONTACTS_UNREAD_LIMIT);
		}
		return db.findAll(selector);
	}
	/**
	 * 获取所有已经注册的手机用户
	 * @param index -1则默认获取全部
	 * @return
	 * @throws DbException 
	 */
	public static List<T_Contacts> getRegiPhones(int index) throws DbException {
		Selector selector=Selector.from(T_Contacts.class);
		selector.
		where("ifRegister","=",Integer.valueOf(Enum_IfRegister.REGIST.value()));
		if(index!=-1){
			selector.limit(CONSTANT.DAO_CONTACTS_UNREAD_LIMIT).
			offset(index*CONSTANT.DAO_CONTACTS_UNREAD_LIMIT);
		}
		return db.findAll(selector);
	}

	
	public static void updatePhoneFollow(String phone) {
		// TODO Auto-generated method stub
		
	}
	public static void updatePhoneIfo(Recv_phoneCheck bean) throws DbException {
		// TODO Auto-generated method stub
		T_Contacts updateBean=new T_Contacts();
		updateBean.setUid(bean.getUser_id());
		updateBean.setIfRegister(Integer.valueOf(CONSTANT.DAO_CONTACTS_REGI));
		updateBean.setPhone(bean.getUser_phone());
		db.update(updateBean,WhereBuilder.b("phone","=", bean.getUser_phone()), new String[]{"uid","ifRegister"});
	}
	
	

}
