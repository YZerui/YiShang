package com.yishang.C.dao.daoImpl;

import java.util.List;

import com.format.utils.DataValidate;
import com.format.utils.FormatUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import com.yishang.A.global.Enum.Enum_ListLimit;
import com.yishang.A.global.Enum.db.Enum_MsgFriModel;
import com.yishang.A.global.Enum.db.Enum_MsgModel;
import com.yishang.A.global.Enum.db.Enum_MsgSeqModel;
import com.yishang.A.global.Enum.db.Enum_ResModel;
import com.yishang.A.global.Enum.push.Enum_PushSource;
import com.yishang.A.global.baseClass.SuperDaoImpl;
import com.yishang.A.global.writting.W_Msg;
import com.yishang.C.dao.daoModel.T_Msg;
import com.yishang.C.dao.daoModel.T_MsgNewFir;
import com.yishang.C.dao.daoModel.T_MsgSeq;

/**
 * 消息顺序表(该表的数据由T_Msg产生)
 * 
 * @author MM_Zerui
 * 
 */
public class Dao_MsgSeq extends SuperDaoImpl {
	
	private static void addRecord(T_MsgSeq msgSeq,Enum_PushSource enumSource) throws DbException {
		msgSeq.setSelf_id(Dao_Self.getInstance().getUser_id());
		switch (enumSource) {
		case SYSTEM:
			msgSeq.setMsg_seq_unReadNum(getUnReadNum_SYSTEM()+1);
			break;
		case COMPANY:
			msgSeq.setMsg_seq_unReadNum(getUnReadNum_COMPANY()+1);
			break;
		case USER:
			msgSeq.setMsg_seq_unReadNum(getUnReadNum_USER(msgSeq
					.getMsg_seq_sendId()) + 1);
			break;
		case NEWFRIEND:
			msgSeq.setMsg_seq_unReadNum(getUnReadNum_New() + 1);
			break;
		default:
			break;
		}
		
		db.save(msgSeq);
	}
	
	
	/**
	 * 判断系统信息是否存在
	 * 
	 * @return
	 * @throws DbException
	 */
	private static boolean ifSysExist() throws DbException {
		Selector selector = Selector.from(T_MsgSeq.class);
		selector.where(Enum_MsgSeqModel.msg_seq_source.name(), "=",
				Enum_PushSource.SYSTEM.value());
		selector.and(Enum_MsgSeqModel.self_id.name(), "=",
				Dao_Self.getInstance().getUser_id());
		if (db.findFirst(selector) != null) {
			return true;
		}
		return false;
	}

	/**
	 * 判断某企业是否存在
	 * 
	 * @return
	 * @throws DbException
	 */
	private static boolean ifComExist(String comId) throws DbException {
		if (!DataValidate.checkDataValid(comId)) {
			return false;
		}
		Selector selector = Selector.from(T_MsgSeq.class);
		selector.where(Enum_MsgSeqModel.msg_seq_comId.name(), "=", comId);
		selector.and(Enum_MsgSeqModel.self_id.name(), "=",
				Dao_Self.getInstance().getUser_id());
		if (db.findFirst(selector) != null) {
			return true;
		}
		return false;
	}

	/**
	 * 判断某用户是否存在
	 * 
	 * @return
	 * @throws DbException
	 */
	private static boolean ifUserExist(String sendId) throws DbException {
		if (!DataValidate.checkDataValid(sendId)) {
			return false;
		}
		Selector selector = Selector.from(T_MsgSeq.class);
		selector.where(Enum_MsgSeqModel.msg_seq_sendId.name(), "=", sendId);
		selector.and(Enum_MsgSeqModel.self_id.name(), "=",
				Dao_Self.getInstance().getUser_id());
		if (db.findFirst(selector) != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断新朋友记录是否存在
	 * @return
	 * @throws DbException 
	 */
	private static boolean ifNewFriRecordExist() throws DbException{
		Selector selector = Selector.from(T_MsgSeq.class);
		selector.where(Enum_MsgSeqModel.msg_seq_source.name(), "=", Enum_PushSource.NEWFRIEND.value());
		selector.and(Enum_MsgSeqModel.self_id.name(), "=", Dao_Self
				.getInstance().getUser_id());
		if(db.findFirst(selector)!=null){
			return true;
		}
		return false;
	}

	/**
	 * 更新系统信息
	 * 
	 * @note 通过消息来源来标识msg_seq_source
	 * @param msgSeq
	 * @throws DbException
	 */
	private static void updateSys(T_MsgSeq msgSeq) throws DbException {
		if (ifSysExist()) {
			msgSeq.setSelf_id(Dao_Self.getInstance().getUser_id());
			msgSeq.setMsg_seq_unReadNum(getUnReadNum_SYSTEM()+1);
			db.update(msgSeq, WhereBuilder.b(
					Enum_MsgSeqModel.msg_seq_source.name(), "=",
					Enum_PushSource.SYSTEM.value()).and(Enum_MsgSeqModel.self_id.name(), "=", Dao_Self
							.getInstance().getUser_id()),
					Enum_MsgSeqModel.msg_seq_type.name(),
					Enum_MsgSeqModel.msg_seq_content.name(),
					Enum_MsgSeqModel.msg_seq_serverTime.name(),
					Enum_MsgSeqModel.msg_seq_recvTime.name(),
					Enum_MsgSeqModel.self_id.name(),
					Enum_MsgSeqModel.msg_seq_unReadNum.name());
			return;
		}
		addRecord(msgSeq,Enum_PushSource.SYSTEM);

	}

	/**
	 * 更新企业信息
	 * 
	 * @note 通过企业ID来标识msg_seq_comId
	 * @param msgSeq
	 * @throws DbException
	 */
	private static void updateCom(T_MsgSeq msgSeq) throws DbException {
		if (ifComExist(msgSeq.getMsg_seq_comId())) {
			msgSeq.setSelf_id(Dao_Self.getInstance().getUser_id());
			db.update(msgSeq, WhereBuilder.b(
					Enum_MsgSeqModel.msg_seq_comId.name(), "=",
					msgSeq.getMsg_seq_comId()).and(Enum_MsgSeqModel.self_id.name(), "=", Dao_Self
							.getInstance().getUser_id()), Enum_MsgSeqModel.msg_seq_source
					.name(), Enum_MsgSeqModel.msg_seq_type.name(),
					Enum_MsgSeqModel.msg_seq_content.name(),
					Enum_MsgSeqModel.msg_seq_serverTime.name(),
					Enum_MsgSeqModel.msg_seq_recvTime.name(),
					Enum_MsgSeqModel.self_id.name(),
					Enum_MsgSeqModel.msg_seq_comLogo.name(),
					Enum_MsgSeqModel.msg_seq_comName.name());
			return;
		}
		addRecord(msgSeq,Enum_PushSource.COMPANY);

	}
	
	/**
	 * 更新新朋友板块的信息（未读数由新朋友表提供）
	 * @param bean
	 * @throws DbException
	 */
	public static void updateNewFriRecord(T_MsgNewFir bean) throws DbException{
		T_MsgSeq seqBean=new T_MsgSeq();
		seqBean.setMsg_seq_sendName(bean.getMsg_new_sendName());//发送者名称
		seqBean.setMsg_seq_resName(bean.getMsg_new_resName());//资源名
		seqBean.setMsg_seq_type(bean.getMsg_new_type());//消息类型
		seqBean.setMsg_seq_recvTime(com.yishang.Z.utils.FormatUtils.getCurrentDateValue());//本地记录时间
		seqBean.setMsg_seq_unReadNum(bean.getMsg_new_unReadNum());//消息未读数
		seqBean.setMsg_seq_source(Enum_PushSource.NEWFRIEND.value());//消息来源
		seqBean.setMsg_seq_sendId(bean.getMsg_new_sendId());//发送者ID
		seqBean.setSelf_id(Dao_Self.getInstance().getUser_id());
		seqBean.setMsg_seq_resId(bean.getMsg_new_resId());//资源ID
		seqBean.setMsg_seq_sendHead(bean.getMsg_new_sendHead());
		if(ifNewFriRecordExist()){
			//更新新朋友记录
			db.update(seqBean, WhereBuilder.b(Enum_MsgSeqModel.msg_seq_source.name(), "=", Enum_PushSource.NEWFRIEND.value())
					.and(Enum_MsgSeqModel.self_id.name(), "=", Dao_Self
							.getInstance().getUser_id()));
			return;
		}
		//插入新朋友记录
		addRecord(seqBean, Enum_PushSource.NEWFRIEND);
	}
	/**
	 * 获取和系统间的未读数
	 * @return
	 * @throws DbException
	 */
	private static int getUnReadNum_SYSTEM() throws DbException{
		Selector selector = Selector.from(T_MsgSeq.class);
		selector.where(Enum_MsgSeqModel.msg_seq_source.name(), "=", Enum_PushSource.SYSTEM.value());
		selector.and(Enum_MsgSeqModel.self_id.name(), "=", Dao_Self
				.getInstance().getUser_id());
		T_MsgSeq seqBean = db.findFirst(selector);
		if(DataValidate.checkDataValid(seqBean)){
			return seqBean.getMsg_seq_unReadNum();
		}
		return 0;
	}
	/**
	 * 获取和企业间的未读数
	 * @return
	 * @throws DbException
	 */
	private static int getUnReadNum_COMPANY() throws DbException{
		Selector selector = Selector.from(T_MsgSeq.class);
		selector.where(Enum_MsgSeqModel.msg_seq_source.name(), "=", Enum_PushSource.COMPANY.value());
		selector.and(Enum_MsgSeqModel.self_id.name(), "=", Dao_Self
				.getInstance().getUser_id());
		T_MsgSeq seqBean = db.findFirst(selector);
		if(DataValidate.checkDataValid(seqBean)){
			return seqBean.getMsg_seq_unReadNum();
		}
		return 0;
	}
	/**
	 * 获取和某用户间的未读数(非新朋友）
	 * 
	 * @param sendId
	 * @return
	 * @throws DbException
	 */
	private static int getUnReadNum_USER(String sendId) throws DbException {
		Selector selector = Selector.from(T_MsgSeq.class);
		selector.where(Enum_MsgSeqModel.msg_seq_sendId.name(), "=", sendId);
		selector.and(Enum_MsgSeqModel.self_id.name(), "=", Dao_Self
				.getInstance().getUser_id());
		selector.and(Enum_MsgSeqModel.msg_seq_source.name(),"=",Enum_PushSource.USER.value());
		T_MsgSeq seqBean = db.findFirst(selector);
		if(DataValidate.checkDataValid(seqBean)){
			return seqBean.getMsg_seq_unReadNum();
		}
		return 0;
	}

	/**
	 * 获取和新朋友间的未读数
	 * @return
	 * @throws DbException 
	 */
	private static int getUnReadNum_New() throws DbException{
		Selector selector = Selector.from(T_MsgSeq.class);
		selector.where(Enum_MsgSeqModel.msg_seq_source.name(), "=", Enum_PushSource.NEWFRIEND.value());
		selector.and(Enum_MsgSeqModel.self_id.name(), "=", Dao_Self
				.getInstance().getUser_id());
		T_MsgSeq seqBean=db.findFirst(selector);
		if(DataValidate.checkDataValid(seqBean)){
			return seqBean.getMsg_seq_unReadNum();
		}
		return 0;
	}
	/**
	 * 清除和某用户间的未读数
	 * 
	 * @param sendId
	 * @throws DbException
	 */
	public static void clearUnRead_USER(String sendId) throws DbException {
		T_MsgSeq seqBean = new T_MsgSeq();
		seqBean.setMsg_seq_unReadNum(0);
		db.update(
				seqBean,
				WhereBuilder.b(Enum_MsgSeqModel.msg_seq_sendId.name(), "=",
						sendId).and(Enum_MsgSeqModel.self_id.name(), "=",
						Dao_Self.getInstance().getUser_id()),
				Enum_MsgSeqModel.msg_seq_unReadNum.name());
	}
	/**
	 * 清除和某企业间的未读数
	 * 
	 * @param sendId
	 * @throws DbException
	 */
	public static void clearUnRead_Com(String cId) throws DbException {
		T_MsgSeq seqBean = new T_MsgSeq();
		seqBean.setMsg_seq_unReadNum(0);
		db.update(
				seqBean,
				WhereBuilder.b(Enum_MsgSeqModel.msg_seq_comId.name(), "=",
						cId).and(Enum_MsgSeqModel.self_id.name(), "=",
								Dao_Self.getInstance().getUser_id()),
								Enum_MsgSeqModel.msg_seq_unReadNum.name());
	}
	/**
	 * 清除系统通知的未读数
	 * @throws DbException
	 */
	public static void clearUnRead_SYSTEM() throws DbException{
		T_MsgSeq seqBean = new T_MsgSeq();
		seqBean.setMsg_seq_unReadNum(0);
		db.update(
				seqBean,
				WhereBuilder.b(Enum_MsgSeqModel.msg_seq_source.name(),"=",Enum_PushSource.SYSTEM.value())
					.and(Enum_MsgSeqModel.self_id.name(), "=",
						Dao_Self.getInstance().getUser_id()),
				Enum_MsgSeqModel.msg_seq_unReadNum.name());
	}
	/**
	 * 删除和某发送方间的消息记录
	 * @param senderId
	 * @throws DbException
	 */
	public static void delRecord_USER(String senderId) throws DbException {
		db.delete(T_MsgSeq.class, WhereBuilder.b(
				Enum_MsgSeqModel.msg_seq_sendId.name(), "=", senderId)
				.and(Enum_MsgSeqModel.self_id.name(), "=",
						Dao_Self.getInstance().getUser_id()));
	}
	/**
	 * 更新新朋友模块的总未读数
	 * @param num
	 * @throws DbException
	 */
	public static void updateNewFriURNum(int num) throws DbException{
		WhereBuilder whereBuilder=WhereBuilder.b
				(Enum_MsgSeqModel.msg_seq_source.name(),"=",Enum_PushSource.NEWFRIEND.value());
		whereBuilder.and(Enum_MsgSeqModel.self_id.name(), "=",
						Dao_Self.getInstance().getUser_id());
		T_MsgSeq bean=new T_MsgSeq();
		bean.setMsg_seq_unReadNum(num);
		db.update(bean, whereBuilder, Enum_MsgSeqModel.msg_seq_unReadNum.name());
						
		
	}
	/**
	 * 获取所有记录的未读数
	 * 
	 * @return
	 * @throws DbException
	 */
	public static int getUnRead_TOTAL() throws DbException {
		Selector selector = Selector.from(T_MsgSeq.class);
		selector.where(Enum_MsgSeqModel.self_id.name(), "=", Dao_Self
				.getInstance().getUser_id());
		List<T_MsgSeq> list = db.findAll(selector);;
		if (!DataValidate.checkDataValid(list)) {
			return 0;
		}
		int num = 0;
		for (T_MsgSeq item : list) {
			num += item.getMsg_seq_unReadNum();
		}
		return num;
	}
	
	/**
	 * 更新用户信息
	 * 
	 * @note 通过用户Id来标识msg_seq_sendId
	 * @备注 @1 每次更新都将对未读数加1
	 * @2 每次插入初始为0
	 * @param msgSeq
	 * @throws DbException
	 */
	private static void updateUser(T_MsgSeq msgSeq) throws DbException {
		if (ifUserExist(msgSeq.getMsg_seq_sendId())) {
			msgSeq.setSelf_id(Dao_Self.getInstance().getUser_id());
			msgSeq.setMsg_seq_unReadNum(getUnReadNum_USER(msgSeq
					.getMsg_seq_sendId()) + 1);
			db.update(msgSeq, WhereBuilder.b(
					Enum_MsgSeqModel.msg_seq_sendId.name(), "=",
					msgSeq.getMsg_seq_sendId())
					.and(Enum_MsgSeqModel.self_id.name(), "=",
							Dao_Self.getInstance().getUser_id()),
					Enum_MsgSeqModel.msg_seq_source.name(),
					Enum_MsgSeqModel.msg_seq_type.name(),
					Enum_MsgSeqModel.msg_seq_content.name(),
					Enum_MsgSeqModel.msg_seq_serverTime.name(),
					Enum_MsgSeqModel.msg_seq_recvTime.name(),
					Enum_MsgSeqModel.msg_seq_resName.name(),
					Enum_MsgSeqModel.msg_seq_unReadNum.name(),
					Enum_MsgSeqModel.msg_seq_resId.name(),
					Enum_MsgSeqModel.self_id.name())
					;
			return;
		}
		addRecord(msgSeq,Enum_PushSource.USER);

	}
	public static void delMsg(String id,Enum_PushSource enumSource) throws DbException{
		WhereBuilder whereBuilder=WhereBuilder.b(Enum_MsgSeqModel.self_id.name(), "=",
							Dao_Self.getInstance().getUser_id());
		switch (enumSource) {
		case COMPANY:
			//删除企业信息
			whereBuilder.and(Enum_MsgSeqModel.msg_seq_sendId.name(),"=",id);
//			db.delete(T_MsgSeq.class,whereBuilder);
			//同时删除消息记录表
			Dao_Msg.delMsg(id);
			break;
		case NEWFRIEND:
			//删除新朋友信息
//			whereBuilder.
			break;
		case SYSTEM:
			//删除系统记录
			whereBuilder.and(Enum_MsgSeqModel.msg_seq_source.name(),"=",Enum_PushSource.SYSTEM.value());
			
			Dao_Msg.delSystem();
			break;
		case USER:
			//删除用户记录
			whereBuilder.and(Enum_MsgSeqModel.msg_seq_sendId.name(),"=",id);
//			db.delete(T_MsgSeq.class,whereBuilder);
			Dao_Msg.delMsg(id);
			break;
		default:
			break;
		}
		db.delete(T_MsgSeq.class,whereBuilder);
	}
	public static void updataResName(String resID,String resName) throws DbException{
		T_MsgSeq bean=new T_MsgSeq();
		bean.setMsg_seq_resName(resName);
		db.update(bean,WhereBuilder.b(Enum_MsgSeqModel.msg_seq_resId.name(), "=", resID)
				.and(Enum_MsgSeqModel.self_id.name(), "=",
				Dao_Self.getInstance().getUser_id()),
				Enum_MsgSeqModel.msg_seq_resName.name());
		
		
	}

	/**
	 * 生成一个消息顺序记录
	 * 
	 * @note 每次生成需要更新和该对象的记录
	 * @note 如果为系统 通过消息来源来标识
	 * @note 如果为企业 通过 企业ID来标识
	 * @note 如果为用户 通过用户ID来标识
	 * @note 如果存在两者间记录，则更新数据，否则插入记录
	 * @param msg
	 * @throws DbException
	 */
	public static void createRecord(T_Msg msg) throws DbException {
		T_MsgSeq sBean = new T_MsgSeq();
		sBean.setMsg_seq_sendId(msg.getMsg_sendId());
		sBean.setMsg_seq_comId(msg.getMsg_comId());
		sBean.setMsg_seq_content(msg.getMsg_content());
		sBean.setMsg_seq_recvTime(FormatUtils.getCurrentDateValue());
		sBean.setMsg_seq_source(msg.getMsg_source()); // 推送来源
		sBean.setMsg_seq_type(msg.getMsg_type()); // 消息类型
		sBean.setMsg_seq_serverTime(msg.getMsg_time());// 推送生成时间
		sBean.setMsg_seq_resName(msg.getMsg_resName()); // 文档名
		sBean.setMsg_seq_success(msg.getMsg_success()); // 事件是否成功的标识
		sBean.setMsg_seq_resId(msg.getMsg_resId());//获取文档ID
		sBean.setMsg_seq_sendHead(msg.getMsg_sendHead());
		sBean.setMsg_seq_sendName(msg.getMsg_sendName());
		sBean.setMsg_seq_comName(msg.getMsg_comName());
		sBean.setMsg_seq_comLogo(msg.getMsg_comLogo());
		// sBean.setMsg_seq_comLogo("");
		// sBean.setMsg_seq_comName("");
		// sBean.setMsg_seq_recvTime(msg_seq_recvTime)
		Enum_PushSource sEnum = Enum_PushSource.DEFAULT.valueOf(msg
				.getMsg_source());
		switch (sEnum) {
		case SYSTEM: // 来自系统
			updateSys(sBean);
			break;
		case COMPANY: // 来自企业
			updateCom(sBean);
			break;
		case USER: // 来自用户
			updateUser(sBean);
			break;

		default:
			break;
		}
		// addRecord(sBean);
	}

	/**
	 * 更新企业信息 #logo# #企业名#
	 * 
	 * @param cID
	 * @throws DbException
	 */
	public static void updateComIfo(String cID, String cName, String cLogo)
			throws DbException {
		T_MsgSeq bean = new T_MsgSeq();
		bean.setMsg_seq_comName(cName);
		bean.setMsg_seq_comLogo(cLogo);
		if (ifComExist(cID)) {
			db.update(bean, WhereBuilder.b(
					Enum_MsgSeqModel.msg_seq_comId.name(), "=", cID)
					.and(Enum_MsgSeqModel.self_id.name(), "=",
							Dao_Self.getInstance().getUser_id()),
					Enum_MsgSeqModel.msg_seq_comName.name(),
					Enum_MsgSeqModel.msg_seq_comLogo.name());
			return;
		}
		// bean.setMsg_seq_comId(cID);
		// addRecord(bean);
	}

	/**
	 * 更新用户信息 #head# #用户名#
	 * @备注 同时要对新朋友消息顺序表进行更新
	 * 
	 * @param uID
	 * @throws DbException
	 */
	public static void updateUserIfo(String uID, String uName, String uLogo)
			throws DbException {
		//对新朋友的消息顺序表进行更新
		Dao_MsgNewFri.upDateIfo(uID, uName, uLogo);
		
		T_MsgSeq bean = new T_MsgSeq();
		bean.setMsg_seq_sendId(uID);
		bean.setMsg_seq_sendHead(uLogo);
		bean.setMsg_seq_sendName(uName);
		if (ifUserExist(uID)) {
			db.update(bean, WhereBuilder.b(
					Enum_MsgSeqModel.msg_seq_sendId.name(), "=", uID)
					.and(Enum_MsgSeqModel.self_id.name(), "=",
							Dao_Self.getInstance().getUser_id()),
					Enum_MsgSeqModel.msg_seq_sendName.name(),
					Enum_MsgSeqModel.msg_seq_sendHead.name());
			return;
		}
		
		// bean.setMsg_seq_comId(cID);
		// addRecord(bean);
	}

	/**
	 * 获取消息顺序表的数据
	 * 
	 * @排序规则 时间排序
	 * @param sEnum
	 * @param index
	 * @return
	 * @throws DbException
	 */
	public static List<T_MsgSeq> getListDatas(Enum_PushSource sEnum, int index)
			throws DbException {
		Selector selector = Selector.from(T_MsgSeq.class);
		selector.where(Enum_MsgSeqModel.self_id.name(), "=",
				Dao_Self.getInstance().getUser_id());
		switch (sEnum) {
		case ALL: // 默认(全部)

			break;
		case SYSTEM: // 来自系统消息
			selector.and(Enum_MsgSeqModel.msg_seq_source.name(), "=",
					Enum_PushSource.SYSTEM.value());
			break;
		case COMPANY: // 来自企业消息
			selector.and(Enum_MsgSeqModel.msg_seq_source.name(), "=",
					Enum_PushSource.COMPANY.value());
			break;
		case USER: // 来自用户消息
			selector.and(Enum_MsgSeqModel.msg_seq_source.name(), "=",
					Enum_PushSource.USER.value());
			break;
		case NEWFRIEND://来自新朋友
			selector.and(Enum_MsgSeqModel.msg_seq_source.name(),"=",
					Enum_PushSource.NEWFRIEND.value());
		default:
			break;
		}
		
		selector.orderBy("CAST(msg_seq_serverTime AS NUMERIC)", true)
				.limit(Enum_ListLimit.MSG_SEQ.value())
				.offset(index * Enum_ListLimit.MSG_SEQ.value());
		return db.findAll(selector);
	}
}
