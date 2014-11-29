package com.yishang.C.dao.daoImpl;

import java.util.List;

import com.format.utils.DataValidate;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import com.yishang.A.global.Enum.Enum_ListLimit;
import com.yishang.A.global.Enum.db.Enum_MsgFriModel;
import com.yishang.A.global.Enum.db.Enum_MsgModel;
import com.yishang.A.global.Enum.db.Enum_MsgSeqModel;
import com.yishang.A.global.baseClass.SuperDaoImpl;
import com.yishang.C.dao.daoModel.T_Msg;
import com.yishang.C.dao.daoModel.T_MsgNewFir;
import com.yishang.C.dao.daoModel.T_MsgSeq;
import com.yishang.Z.utils.FormatUtils;

/**
 * 新朋友的商机消息顺序表
 * 
 * @author MM_Zerui
 * 
 */
public class Dao_MsgNewFri extends SuperDaoImpl {

	private static void addRecord(T_MsgNewFir bean) throws DbException {
		bean.setSelf_id(Dao_Self.getInstance().getUser_id());
		bean.setMsg_new_recvTime(FormatUtils.getCurrentDateValue());
		bean.setMsg_new_unReadNum(getUnReadNum(bean.getMsg_new_sendId())+1);
		db.save(bean);
	}

	private static boolean checkExist(String uid) throws DbException {
		if (!DataValidate.checkDataValid(uid)) {
			return false;
		}
		Selector selector = Selector.from(T_MsgNewFir.class);
		selector.where(Enum_MsgFriModel.msg_new_sendId.name(), "=", uid);
		selector.and(Enum_MsgFriModel.self_id.name(), "=", Dao_Self
				.getInstance().getUser_id());
		if (db.findFirst(selector) != null) {
			return true;
		}
		return false;
	}

	public static void delById(String uid) throws DbException {
		if (!DataValidate.checkDataValid(uid)) {
			return;
		}
		db.delete(
				T_MsgNewFir.class,
				WhereBuilder
						.b(Enum_MsgFriModel.msg_new_sendId.name(), "=", uid)
						.and(Enum_MsgFriModel.self_id.name(), "=",
								Dao_Self.getInstance().getUser_id()));
	}

	private static int getUnReadNum(String uid) throws DbException {
		Selector selector = Selector.from(T_MsgNewFir.class);
		selector.where(Enum_MsgFriModel.msg_new_sendId.name(), "=", uid);
		selector.and(Enum_MsgFriModel.self_id.name(), "=", Dao_Self
				.getInstance().getUser_id());
		T_MsgNewFir bean = db.findFirst(selector);
		if (DataValidate.checkDataValid(bean)) {
			return bean.getMsg_new_unReadNum();
		}
		return 0;
	}

	private static void update(T_MsgNewFir bean) throws DbException {
		bean.setMsg_new_recvTime(FormatUtils.getCurrentDateValue());
		bean.setMsg_new_unReadNum(getUnReadNum(bean.getMsg_new_sendId()) + 1);
		bean.setSelf_id(Dao_Self.getInstance().getUser_id());
		db.update(
				bean,
				WhereBuilder.b(Enum_MsgFriModel.msg_new_sendId.name(), "=",
						bean.getMsg_new_sendId()).and(
						Enum_MsgFriModel.self_id.name(), "=",
						Dao_Self.getInstance().getUser_id()));
	}
	
	/**
	 * 获取所有的未读数（来自新朋友的）
	 * @return
	 * @throws DbException 
	 */
	public static int getUnReadNum_All() throws DbException{
		int num=0;
		List<T_MsgNewFir> listDatas=db.findAll(T_MsgNewFir.class);
		if(DataValidate.checkDataValid(listDatas)){
			for(T_MsgNewFir item:listDatas){
				num+=item.getMsg_new_unReadNum();
			}
		}
		return num;
	}

	public static void clearUnRead(String uid) throws DbException{
		T_MsgNewFir bean=new T_MsgNewFir();
		bean.setMsg_new_unReadNum(0);
		db.update(bean, WhereBuilder.b(Enum_MsgFriModel.msg_new_sendId.name(), "=", uid)
				.and(Enum_MsgFriModel.self_id.name(), "=", Dao_Self.getInstance().getUser_id()),
				Enum_MsgFriModel.msg_new_unReadNum.name());
	}
	/**
	 * 更新资源名(同时对消息顺序表进行更新)
	 * @param resID
	 * @param resName
	 * @throws DbException
	 */
	public static void updataResName(String resID,String resName) throws DbException{
		T_MsgNewFir bean=new T_MsgNewFir();
		bean.setMsg_new_resName(resName);
		db.update(bean,WhereBuilder.b(Enum_MsgFriModel.msg_new_resId.name(), "=", resID)
				.and(Enum_MsgModel.self_id.name(), "=",
				Dao_Self.getInstance().getUser_id()),
				Enum_MsgFriModel.msg_new_resName.name());
		
		Dao_MsgSeq.updataResName(resID, resName);
	}
	/**
	 * 接收消息表的记录更新新朋友表的记录
	 * 
	 * @步骤
	 * @1 判断该记录对于的用户是否已经存在表中，如果存在，更新相应数据，如果不存在，插入信息
	 * @2 更新本表后需要对消息顺序表进行更新
	 * @param msgBean
	 * @throws DbException
	 */
	public static void updateRecord(T_Msg msgBean) throws DbException {
		T_MsgNewFir newBean = new T_MsgNewFir();
		newBean.setMsg_new_sendId(msgBean.getMsg_sendId());
		newBean.setMsg_new_content(msgBean.getMsg_content());
		newBean.setMsg_new_resName(msgBean.getMsg_resName());
		newBean.setMsg_new_sendHead(msgBean.getMsg_sendHead());
		newBean.setMsg_new_serverTime(msgBean.getMsg_time());
		newBean.setMsg_new_type(msgBean.getMsg_type());
		newBean.setMsg_new_sendName(msgBean.getMsg_sendName());
		newBean.setMsg_new_resId(msgBean.getMsg_resId());
		if (checkExist(msgBean.getMsg_sendId())) {
			update(newBean);
		} else {
			addRecord(newBean);
		}
		//获取所有的未读数
		newBean.setMsg_new_unReadNum(getUnReadNum_All());
		// 对消息顺序表进行更新
		Dao_MsgSeq.updateNewFriRecord(newBean);
	}
	
	/**
	 * 更新信息
	 * @param uID
	 * @param uName
	 * @param uLogo
	 * @throws DbException
	 */
	public static void upDateIfo(String uID, String uName, String uLogo) throws DbException{
		T_MsgNewFir bean = new T_MsgNewFir();
		bean.setMsg_new_sendId(uID);
		bean.setMsg_new_sendName(uName);
		bean.setMsg_new_sendHead(uLogo);
		if (checkExist(uID)) {
			db.update(bean, WhereBuilder.b(Enum_MsgFriModel.msg_new_sendId.name(), "=",
					bean.getMsg_new_sendId()).and(
					Enum_MsgFriModel.self_id.name(), "=",
					Dao_Self.getInstance().getUser_id()),
					Enum_MsgFriModel.msg_new_sendName.name(),
					Enum_MsgFriModel.msg_new_sendHead.name());
			return;
		}
	}
	
	public static List<T_MsgNewFir> getAllRecord(int index) throws DbException{
		Selector selector=Selector.from(T_MsgNewFir.class);
		selector.where(Enum_MsgFriModel.self_id.name(), "=",
					Dao_Self.getInstance().getUser_id());
		selector.orderBy("CAST(msg_new_serverTime AS NUMERIC)", true);
		selector.limit(Enum_ListLimit.MSG_NEWFRI.value())
			.offset(Enum_ListLimit.MSG_NEWFRI.value()*index);
		return db.findAll(selector);
	}
}
