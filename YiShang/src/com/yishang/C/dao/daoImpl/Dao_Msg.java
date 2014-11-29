package com.yishang.C.dao.daoImpl;

import java.util.ArrayList;
import java.util.List;

import com.format.utils.DataValidate;
import com.format.utils.FormatUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import com.yishang.A.global.Enum.Enum_ListLimit;
import com.yishang.A.global.Enum.db.Enum_MsgModel;
import com.yishang.A.global.Enum.db.Enum_MsgSeqModel;
import com.yishang.A.global.Enum.push.Enum_PushSource;
import com.yishang.A.global.baseClass.SuperDaoImpl;
import com.yishang.A.global.constant.CONSTANT;
import com.yishang.A.global.constant.DAO_CONSTANT;
import com.yishang.C.dao.daoModel.T_Company;
import com.yishang.C.dao.daoModel.T_Msg;
import com.yishang.C.dao.daoModel.T_MsgSeq;
import com.yishang.C.dao.utils.Utils_DB;

/**
 * 管理推送消息 的所有记录
 * 
 * @author MM_Zerui
 * 
 */
public class Dao_Msg extends SuperDaoImpl {
	/**
	 * 每次增加一条数据，都将对该数据进行判断
	 * 	@1 如果数据来源为新朋友，则更新新朋友表
	 * 	@2 如果来源为其它，则更新消息顺序表(注意，每次更新都要检查下新朋友表，保证关系的同步）
	 * @param msg
	 * @throws DbException
	 */
	private static void addRecord(T_Msg msg) throws DbException {
		msg.setSelf_id(Dao_Self.getInstance().getUser_id());
		msg.setMsg_recvTime(FormatUtils.getCurrentDateValue());
		db.save(msg);
		if(msg.getMsg_source()==Enum_PushSource.NEWFRIEND.value()){
			//更新新朋友顺序表
			Dao_MsgNewFri.updateRecord(msg);
			return;
		}
		// 刷新消息顺序表
		Dao_MsgSeq.createRecord(msg);
		// 更新新朋友记录（避免关系升级带来的不同步问题）
		Dao_MsgNewFri.delById(msg.getMsg_sendId());
	}

	private static void addRecord(List<T_Msg> list) throws DbException {
		db.saveAll(list);
	}

	/**
	 * 判断关于某企业信息是否存在
	 * 
	 * @return
	 * @throws DbException
	 */
	private static boolean ifComExist(String cID) throws DbException {
		if (!DataValidate.checkDataValid(cID)) {
			return false;
		}
		Selector selector = Selector.from(T_Msg.class);
		selector.where(Enum_MsgModel.msg_comId.name(), "=", cID);
		selector.and(Enum_MsgModel.self_id.name(), "=",
				Dao_Self.getInstance().getUser_id());
		if (db.findFirst(selector) != null) {
			return true;
		}
		return false;
	}

	/**
	 * 判断关于某用户信息是否存在
	 * 
	 * @return
	 * @throws DbException
	 */
	private static boolean ifUserExist(String uID) throws DbException {
		if (!DataValidate.checkDataValid(uID)) {
			return false;
		}
		Selector selector = Selector.from(T_Msg.class);
		selector.where(Enum_MsgModel.msg_sendId.name(), "=", uID);
		selector.and(Enum_MsgModel.self_id.name(), "=",
				Dao_Self.getInstance().getUser_id());
		if (db.findFirst(selector) != null) {
			return true;
		}
		return false;
	}
	/**
	 * 判断某消息是否存在
	 * 
	 * @return
	 * @throws DbException
	 */
	private static boolean ifMsgExist(long mID) throws DbException {
		if (!DataValidate.checkDataValid(mID)) {
			return false;
		}
		Selector selector = Selector.from(T_Msg.class);
		selector.where(Enum_MsgModel.msg_id.name(), "=", mID);
		selector.and(Enum_MsgModel.self_id.name(), "=",
				Dao_Self.getInstance().getUser_id());
		if (db.findFirst(selector) != null) {
			return true;
		}
		return false;
	}

	/**
	 * 增加一条推送消息到本地
	 * 
	 * @步骤  首先判断本条消息是否已经插入,如果没有直接插入
	 * @步骤 每次插入都将对其它表进行更新操作
	 * 
	 * @param msg
	 * @throws DbException
	 */
	public static void addPushRecord(T_Msg msg) throws DbException {
		if(ifMsgExist(msg.getMsg_id())){
			return;
		}
		addRecord(msg);

	}
	/**
	 * 更新商机的消息来源类型
	 * @param uid
	 * @param enumSource
	 * @throws DbException
	 */
	public static void updateSenderSource(String uid,int source) throws DbException{
		T_Msg bean=new T_Msg();
		bean.setMsg_source(source);
		db.update(bean, WhereBuilder.b(Enum_MsgModel.msg_sendId.name(), "=", uid)
				.and(Enum_MsgModel.self_id.name(), "=", Dao_Self.getInstance().getUser_id()),
				Enum_MsgModel.msg_source.name());
	}
	/**
	 * 更新消息表中的企业信息
	 * 
	 * @note 本次更新将会对消息顺序表进行更新
	 * 
	 * @param cID
	 * @param cName
	 * @param cLogo
	 * @throws DbException
	 */
	public static void updatePushRecord_Com(String cID, String cName,
			String cLogo) throws DbException {
		T_Msg bean = new T_Msg();
		bean.setMsg_comName(cName);
		bean.setMsg_comLogo(cLogo);
		bean.setMsg_comId(cID);
		// 更新消息顺序表
		Dao_MsgSeq.updateComIfo(cID, cName, cLogo);
		if (ifComExist(cID)) {
			db.update(bean,
					WhereBuilder.b(Enum_MsgModel.msg_comId.name(), "=", cID).and(Enum_MsgModel.self_id.name(), "=",
							Dao_Self.getInstance().getUser_id()),
					Enum_MsgModel.msg_comName.name(),
					Enum_MsgModel.msg_comLogo.name());
			return;
		}

		// addRecord(bean);

	}
	
	/**
	 * 更新消息表中的用户信息
	 * 
	 * @note 本次更新将会对消息顺序表进行更新
	 * 
	 */
	public static void updatePushRecord_User(String uID, String uName,
			String uLogo) throws DbException {
		T_Msg bean = new T_Msg();
		bean.setMsg_sendId(uID);
		bean.setMsg_sendName(uName);
		bean.setMsg_sendHead(uLogo);
		// 更新消息顺序表
		Dao_MsgSeq.updateUserIfo(uID, uName, uLogo);
		
		if (ifUserExist(uID)) {
			db.update(bean,
					WhereBuilder.b(Enum_MsgModel.msg_sendId.name(), "=", uID).and(Enum_MsgModel.self_id.name(), "=",
							Dao_Self.getInstance().getUser_id()),
					Enum_MsgModel.msg_sendName.name(),
					Enum_MsgModel.msg_sendHead.name());
			return;
		}

		// addRecord(bean);

	}
	/**
	 * 更新文档对应的企业ID信息
	 * @param resID
	 * @param comID
	 * @throws DbException
	 */
	public static void updataComId(String resID,String comID) throws DbException{
		T_Msg bean=new T_Msg();
		bean.setMsg_comId(comID);
		db.update(bean,WhereBuilder.b(Enum_MsgModel.msg_resId.name(), "=", resID)
				.and(Enum_MsgModel.self_id.name(), "=",
				Dao_Self.getInstance().getUser_id()),
				Enum_MsgModel.msg_comId.name());
	}

	/**
	 * 更新文档ID对应的文档名
	 * @备注 将对新朋友的消息顺序表进行更新
	 * @param resID
	 * @param resName
	 * @throws DbException
	 */
	public static void updataResName(String resID,String resName) throws DbException{
		T_Msg bean=new T_Msg();
		bean.setMsg_resName(resName);
		db.update(bean,WhereBuilder.b(Enum_MsgModel.msg_resId.name(), "=", resID)
				.and(Enum_MsgModel.self_id.name(), "=",
				Dao_Self.getInstance().getUser_id()),
				Enum_MsgModel.msg_resName.name());
		Dao_MsgNewFri.updataResName(resID, resName);
	}
	/**
	 * 获取系统通知的所有记录
	 * 
	 * @param sendID
	 * @param msgSource
	 * @return
	 * @throws DbException
	 */
	public static List<T_Msg> getAllRecord_SYSTEM(int index) throws DbException {
		Selector selector=Selector.from(T_Msg.class);
		List<T_Msg> list = db.findAll(selector
				.where(Enum_MsgModel.msg_source.name(), "=",
						Enum_PushSource.SYSTEM.value())
				.and(Enum_MsgModel.self_id.name(), "=",
						Dao_Self.getInstance().getUser_id()).orderBy("CAST(msg_time AS NUMERIC)", true)
				.limit(Enum_ListLimit.MSG_SYSTEM.value())
				.offset(index * Enum_ListLimit.MSG_SYSTEM.value()));
		return list;
	}
	
	/**
	 * 获取某企业通知的所有记录
	 * 
	 * @param sendID
	 * @param msgSource
	 * @return
	 * @throws DbException
	 */
	public static List<T_Msg> getAllRecord_COMPANY(String cID,int index) throws DbException {
		Selector selector=Selector.from(T_Msg.class);
		List<T_Msg> list = db.findAll(selector
				.where(Enum_MsgModel.msg_source.name(), "=",
						Enum_PushSource.COMPANY.value())
						.and(Enum_MsgModel.self_id.name(), "=",
								Dao_Self.getInstance().getUser_id()).
								and(Enum_MsgModel.msg_comId.name(), "=", cID).
								orderBy(Utils_DB.cOrderBy(true,
										"CAST(msg_time AS NUMERIC)",
										"CAST(msg_recvTime AS NUMERIC)"), true)
								.limit(Enum_ListLimit.MSG_COMPANY.value())
								.offset(index * Enum_ListLimit.MSG_COMPANY.value()));
		return list;
	}
	
	/**
	 * 获取用户来源的所有接收记录
	 * 
	 * @param sendID
	 * @param msgSource
	 * @return
	 * @throws DbException
	 */
	public static List<T_Msg> getAllRecord_USER(String uID,int index) throws DbException {
		Selector selector=Selector.from(T_Msg.class);
		List<T_Msg> list = db.findAll(selector
				.where(" ( "+Enum_MsgModel.msg_source.name(), "=",
						Enum_PushSource.USER.value()).
						
						or(Enum_MsgModel.msg_source.name(), "=", Enum_PushSource.NEWFRIEND.value()).expr(" ) ")
						.and(Enum_MsgModel.self_id.name(), "=",
								Dao_Self.getInstance().getUser_id()).
								and(Enum_MsgModel.msg_sendId.name(), "=", uID).
								orderBy(Utils_DB.cOrderBy(true,
										"CAST(msg_time AS NUMERIC)",
										"CAST(msg_recvTime AS NUMERIC)"), true)
										.limit(Enum_ListLimit.MSG_CONTACT.value())
										.offset(index * Enum_ListLimit.MSG_CONTACT.value()));
		return list;
	}
	/**
	 * 删除某发送方的所有数据
	 * @param id
	 * @throws DbException 
	 */
	public static void delMsg(String id) throws DbException{
		WhereBuilder whereBuilder=WhereBuilder.b(Enum_MsgModel.self_id.name(), "=",
								Dao_Self.getInstance().getUser_id());
		whereBuilder.and(Enum_MsgModel.msg_sendId.name(),"=",id);
			db.delete(T_Msg.class, whereBuilder);
	}
	
	/**
	 * 删除系统通知记录
	 * 
	 * @throws DbException
	 */
	public static void delSystem() throws DbException{
		WhereBuilder whereBuilder=WhereBuilder.b(Enum_MsgModel.self_id.name(), "=",
				Dao_Self.getInstance().getUser_id());
		whereBuilder.and(Enum_MsgModel.msg_source.name(),"=",Enum_PushSource.SYSTEM.value());
			db.delete(T_Msg.class, whereBuilder);
	}
	/**
	 * 获取最新的一条商机记录
	 * @return
	 */
	public static T_Msg getNewstMsg(){
	
		Selector selector=Selector.from(T_Msg.class);
		selector.where(Enum_MsgModel.self_id.name(), "=",
				Dao_Self.getInstance().getUser_id());
		selector.orderBy(Enum_MsgModel.msg_id.name(), true);
		try {
			return db.findFirst(selector);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	

}
