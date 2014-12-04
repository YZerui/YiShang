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
 * ��Ϣ˳���(�ñ��������T_Msg����)
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
	 * �ж�ϵͳ��Ϣ�Ƿ����
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
	 * �ж�ĳ��ҵ�Ƿ����
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
	 * �ж�ĳ�û��Ƿ����
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
	 * �ж������Ѽ�¼�Ƿ����
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
	 * ����ϵͳ��Ϣ
	 * 
	 * @note ͨ����Ϣ��Դ����ʶmsg_seq_source
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
	 * ������ҵ��Ϣ
	 * 
	 * @note ͨ����ҵID����ʶmsg_seq_comId
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
	 * ���������Ѱ�����Ϣ��δ�����������ѱ��ṩ��
	 * @param bean
	 * @throws DbException
	 */
	public static void updateNewFriRecord(T_MsgNewFir bean) throws DbException{
		T_MsgSeq seqBean=new T_MsgSeq();
		seqBean.setMsg_seq_sendName(bean.getMsg_new_sendName());//����������
		seqBean.setMsg_seq_resName(bean.getMsg_new_resName());//��Դ��
		seqBean.setMsg_seq_type(bean.getMsg_new_type());//��Ϣ����
		seqBean.setMsg_seq_recvTime(com.yishang.Z.utils.FormatUtils.getCurrentDateValue());//���ؼ�¼ʱ��
		seqBean.setMsg_seq_unReadNum(bean.getMsg_new_unReadNum());//��Ϣδ����
		seqBean.setMsg_seq_source(Enum_PushSource.NEWFRIEND.value());//��Ϣ��Դ
		seqBean.setMsg_seq_sendId(bean.getMsg_new_sendId());//������ID
		seqBean.setSelf_id(Dao_Self.getInstance().getUser_id());
		seqBean.setMsg_seq_resId(bean.getMsg_new_resId());//��ԴID
		seqBean.setMsg_seq_sendHead(bean.getMsg_new_sendHead());
		if(ifNewFriRecordExist()){
			//���������Ѽ�¼
			db.update(seqBean, WhereBuilder.b(Enum_MsgSeqModel.msg_seq_source.name(), "=", Enum_PushSource.NEWFRIEND.value())
					.and(Enum_MsgSeqModel.self_id.name(), "=", Dao_Self
							.getInstance().getUser_id()));
			return;
		}
		//���������Ѽ�¼
		addRecord(seqBean, Enum_PushSource.NEWFRIEND);
	}
	/**
	 * ��ȡ��ϵͳ���δ����
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
	 * ��ȡ����ҵ���δ����
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
	 * ��ȡ��ĳ�û����δ����(�������ѣ�
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
	 * ��ȡ�������Ѽ��δ����
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
	 * �����ĳ�û����δ����
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
	 * �����ĳ��ҵ���δ����
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
	 * ���ϵͳ֪ͨ��δ����
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
	 * ɾ����ĳ���ͷ������Ϣ��¼
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
	 * ����������ģ�����δ����
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
	 * ��ȡ���м�¼��δ����
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
	 * �����û���Ϣ
	 * 
	 * @note ͨ���û�Id����ʶmsg_seq_sendId
	 * @��ע @1 ÿ�θ��¶�����δ������1
	 * @2 ÿ�β����ʼΪ0
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
			//ɾ����ҵ��Ϣ
			whereBuilder.and(Enum_MsgSeqModel.msg_seq_sendId.name(),"=",id);
//			db.delete(T_MsgSeq.class,whereBuilder);
			//ͬʱɾ����Ϣ��¼��
			Dao_Msg.delMsg(id);
			break;
		case NEWFRIEND:
			//ɾ����������Ϣ
//			whereBuilder.
			break;
		case SYSTEM:
			//ɾ��ϵͳ��¼
			whereBuilder.and(Enum_MsgSeqModel.msg_seq_source.name(),"=",Enum_PushSource.SYSTEM.value());
			
			Dao_Msg.delSystem();
			break;
		case USER:
			//ɾ���û���¼
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
	 * ����һ����Ϣ˳���¼
	 * 
	 * @note ÿ��������Ҫ���º͸ö���ļ�¼
	 * @note ���Ϊϵͳ ͨ����Ϣ��Դ����ʶ
	 * @note ���Ϊ��ҵ ͨ�� ��ҵID����ʶ
	 * @note ���Ϊ�û� ͨ���û�ID����ʶ
	 * @note ����������߼��¼����������ݣ���������¼
	 * @param msg
	 * @throws DbException
	 */
	public static void createRecord(T_Msg msg) throws DbException {
		T_MsgSeq sBean = new T_MsgSeq();
		sBean.setMsg_seq_sendId(msg.getMsg_sendId());
		sBean.setMsg_seq_comId(msg.getMsg_comId());
		sBean.setMsg_seq_content(msg.getMsg_content());
		sBean.setMsg_seq_recvTime(FormatUtils.getCurrentDateValue());
		sBean.setMsg_seq_source(msg.getMsg_source()); // ������Դ
		sBean.setMsg_seq_type(msg.getMsg_type()); // ��Ϣ����
		sBean.setMsg_seq_serverTime(msg.getMsg_time());// ��������ʱ��
		sBean.setMsg_seq_resName(msg.getMsg_resName()); // �ĵ���
		sBean.setMsg_seq_success(msg.getMsg_success()); // �¼��Ƿ�ɹ��ı�ʶ
		sBean.setMsg_seq_resId(msg.getMsg_resId());//��ȡ�ĵ�ID
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
		case SYSTEM: // ����ϵͳ
			updateSys(sBean);
			break;
		case COMPANY: // ������ҵ
			updateCom(sBean);
			break;
		case USER: // �����û�
			updateUser(sBean);
			break;

		default:
			break;
		}
		// addRecord(sBean);
	}

	/**
	 * ������ҵ��Ϣ #logo# #��ҵ��#
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
	 * �����û���Ϣ #head# #�û���#
	 * @��ע ͬʱҪ����������Ϣ˳�����и���
	 * 
	 * @param uID
	 * @throws DbException
	 */
	public static void updateUserIfo(String uID, String uName, String uLogo)
			throws DbException {
		//�������ѵ���Ϣ˳�����и���
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
	 * ��ȡ��Ϣ˳��������
	 * 
	 * @������� ʱ������
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
		case ALL: // Ĭ��(ȫ��)

			break;
		case SYSTEM: // ����ϵͳ��Ϣ
			selector.and(Enum_MsgSeqModel.msg_seq_source.name(), "=",
					Enum_PushSource.SYSTEM.value());
			break;
		case COMPANY: // ������ҵ��Ϣ
			selector.and(Enum_MsgSeqModel.msg_seq_source.name(), "=",
					Enum_PushSource.COMPANY.value());
			break;
		case USER: // �����û���Ϣ
			selector.and(Enum_MsgSeqModel.msg_seq_source.name(), "=",
					Enum_PushSource.USER.value());
			break;
		case NEWFRIEND://����������
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
