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
 * ����������Ϣ �����м�¼
 * 
 * @author MM_Zerui
 * 
 */
public class Dao_Msg extends SuperDaoImpl {
	/**
	 * ÿ������һ�����ݣ������Ը����ݽ����ж�
	 * 	@1 ���������ԴΪ�����ѣ�����������ѱ�
	 * 	@2 �����ԴΪ�������������Ϣ˳���(ע�⣬ÿ�θ��¶�Ҫ����������ѱ���֤��ϵ��ͬ����
	 * @param msg
	 * @throws DbException
	 */
	private static void addRecord(T_Msg msg) throws DbException {
		msg.setSelf_id(Dao_Self.getInstance().getUser_id());
		msg.setMsg_recvTime(FormatUtils.getCurrentDateValue());
		db.save(msg);
		if(msg.getMsg_source()==Enum_PushSource.NEWFRIEND.value()){
			//����������˳���
			Dao_MsgNewFri.updateRecord(msg);
			return;
		}
		// ˢ����Ϣ˳���
		Dao_MsgSeq.createRecord(msg);
		// ���������Ѽ�¼�������ϵ���������Ĳ�ͬ�����⣩
		Dao_MsgNewFri.delById(msg.getMsg_sendId());
	}

	private static void addRecord(List<T_Msg> list) throws DbException {
		db.saveAll(list);
	}

	/**
	 * �жϹ���ĳ��ҵ��Ϣ�Ƿ����
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
	 * �жϹ���ĳ�û���Ϣ�Ƿ����
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
	 * �ж�ĳ��Ϣ�Ƿ����
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
	 * ����һ��������Ϣ������
	 * 
	 * @����  �����жϱ�����Ϣ�Ƿ��Ѿ�����,���û��ֱ�Ӳ���
	 * @���� ÿ�β��붼������������и��²���
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
	 * �����̻�����Ϣ��Դ����
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
	 * ������Ϣ���е���ҵ��Ϣ
	 * 
	 * @note ���θ��½������Ϣ˳�����и���
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
		// ������Ϣ˳���
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
	 * ������Ϣ���е��û���Ϣ
	 * 
	 * @note ���θ��½������Ϣ˳�����и���
	 * 
	 */
	public static void updatePushRecord_User(String uID, String uName,
			String uLogo) throws DbException {
		T_Msg bean = new T_Msg();
		bean.setMsg_sendId(uID);
		bean.setMsg_sendName(uName);
		bean.setMsg_sendHead(uLogo);
		// ������Ϣ˳���
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
	 * �����ĵ���Ӧ����ҵID��Ϣ
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
	 * �����ĵ�ID��Ӧ���ĵ���
	 * @��ע ���������ѵ���Ϣ˳�����и���
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
	 * ��ȡϵͳ֪ͨ�����м�¼
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
	 * ��ȡĳ��ҵ֪ͨ�����м�¼
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
	 * ��ȡ�û���Դ�����н��ռ�¼
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
	 * ɾ��ĳ���ͷ�����������
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
	 * ɾ��ϵͳ֪ͨ��¼
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
	 * ��ȡ���µ�һ���̻���¼
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
