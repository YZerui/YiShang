package com.yishang.C.dao.daoImpl;

import java.util.List;

import com.format.utils.DataValidate;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import com.yishang.A.global.Enum.Enum_ListLimit;
import com.yishang.A.global.Enum.com.Enum_ComType;
import com.yishang.A.global.Enum.db.Enum_RelaType;
import com.yishang.A.global.Enum.db.Enum_ResModel;
import com.yishang.A.global.Enum.db.Enum_ResSource;
import com.yishang.A.global.Enum.db.Enum_TRelationship;
import com.yishang.A.global.baseClass.SuperDaoImpl;
import com.yishang.A.global.constant.CONSTANT;
import com.yishang.A.global.constant.DAO_CONSTANT;
import com.yishang.B.module.d.BusinessEntity.Recv_business;
import com.yishang.B.module.d.BusinessEntity.Recv_comDetail;
import com.yishang.B.module.f.LoginEntity.Recv_userIfo;
import com.yishang.C.dao.daoModel.T_Resource;
import com.yishang.C.dao.utils.DaoFormatUtil;
import com.yishang.C.dao.utils.Utils_DB;
import com.yishang.Z.utils.FormatUtils;

/**
 * �ĵ���Դ�Ĺ�����
 * 
 * @author MM_Zerui
 * 
 */
public class Dao_Resource extends SuperDaoImpl {
	/**
	 * ����һ���ĵ���Դ
	 * 
	 * @param bean
	 * @throws DbException
	 */
	private static void addBook(T_Resource bean) throws DbException {
		bean.setSelf_id(Dao_Self.getInstance().getUser_id());
		bean.setBook_recvTime(FormatUtils.getCurrentDateValue());
		bean.setBook_freq(0);
		db.save(bean);
	}

	/**
	 * �������ĵ�
	 * 
	 * @param list
	 * @throws DbException
	 */
	private static void addBook(List<T_Resource> list) throws DbException {
		db.saveAll(list);
	}

//	/**
//	 * ����һ����Դ��������
//	 * 
//	 * @param bean
//	 * @throws DbException
//	 */
//	public static void addRes(T_Resource bean) throws DbException {
//		addBook(bean);
//	}

	/**
	 * ��ȡ�����ĵ�
	 * 
	 * @return
	 * @throws DbException
	 */
	public static List<T_Resource> getAllBooks() throws DbException {
		return db.findAll(T_Resource.class);
	}

	/**
	 * �ж�ĳ��Դ�Ƿ����
	 * @param resId
	 * @return
	 */
	private static boolean checkExist(String resId){
		Selector selector=Selector.from(T_Resource.class);
		selector.where(Enum_ResModel.book_id.name(), "=", resId)
			.and(Enum_ResModel.self_id.name(), "=",
				Dao_Self.getInstance().getUser_id());
		try {
			if(db.findFirst(selector)!=null){
				return true;
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * ������ԴId��ȡ��Ӧ��Ϣ
	 * 
	 * @param resId
	 * @return
	 * @throws DbException
	 */
	public static T_Resource getResource(String resId) throws DbException {
		Selector selector = Selector
				.from(T_Resource.class)
				.where(Enum_ResModel.book_id.name(), "=", resId)
				.and(Enum_ResModel.self_id.name(), "=",
						Dao_Self.getInstance().getUser_id());
		return db.findFirst(selector);

	}
	
	/**
	 * ���ĵ��ȶȽ�����һ
	 * @param resId
	 * @throws DbException
	 */
	public static void addResFreq(String resId) throws DbException{
		T_Resource bean=getResource(resId);
		if(DataValidate.checkDataValid(bean)){
			bean.setBook_freq(bean.getBook_freq()+1);
			db.update(bean, WhereBuilder.b(Enum_ResModel.book_id.name(), "=", resId)
					.and(Enum_ResModel.self_id.name(), "=",
							Dao_Self.getInstance().getUser_id()),
					Enum_ResModel.book_freq.name())
					;
		}
		
	}
	/**
	 * ɾ��ĳ��Դ
	 * @param resId
	 * @throws DbException
	 */
	public static boolean delRes(String resId) {
		try {
			db.delete(T_Resource.class, WhereBuilder.b(Enum_ResModel.book_id.name(), "=", resId)
					.and(Enum_ResModel.self_id.name(), "=",
					Dao_Self.getInstance().getUser_id()));
			return true;
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * ������Դ�е�ֱ��ת����������ͷ��
	 * 
	 * @param bookId
	 * @param name
	 * @throws DbException
	 */
	public static void updateSenderName(String bookId, Recv_userIfo recvBean)
			throws DbException {
		T_Resource bean = new T_Resource();
		bean.setBook_id(bookId);
		bean.setSender_name(recvBean.getUser_name());
		bean.setSender_head(recvBean.getUser_head());
		db.update(bean,
				WhereBuilder.b(Enum_ResModel.book_id.name(), "=", bookId)
					.and(Enum_ResModel.self_id.name(), "=",
						Dao_Self.getInstance().getUser_id()),
				Enum_ResModel.sender_name.name(),
				Enum_ResModel.sender_head.name());
	}

	/**
	 * ������Դ�е�����Ӧ��ҵ��Ϣ #��ҵID# #��ҵ��# #�Ƿ����# #��ҵLogo#
	 * 
	 * @param bookId
	 * @param name
	 * @throws DbException
	 */
	public static void updateCom(String resId, Recv_comDetail recvBean)
			throws DbException {
		T_Resource bean = new T_Resource();
		bean.setBook_id(resId);
		bean.setCom_id(recvBean.getCom_id());
		bean.setCom_name(recvBean.getCom_name());
		bean.setCom_relate(Integer.valueOf(recvBean.getUc_status()));
		bean.setCom_logo(recvBean.getCom_logo());
		db.update(bean,
				WhereBuilder.b(Enum_ResModel.book_id.name(), "=",resId)
				.and(Enum_ResModel.self_id.name(), "=",
						Dao_Self.getInstance().getUser_id()),
				Enum_ResModel.com_id.name(), Enum_ResModel.com_name.name(),
				Enum_ResModel.com_relate.name(), Enum_ResModel.com_logo.name());
	}
	/**
	 * ���º�ĳ��ҵ�Ĺ�����ϵ
	 * @param resId
	 * @param value
	 */
	public static void updateCom_relate(String resId,int value){
		T_Resource bean = new T_Resource();
		bean.setCom_relate(value);
		try {
			db.update(bean,
					WhereBuilder.b(Enum_ResModel.book_id.name(), "=",resId)
					.and(Enum_ResModel.self_id.name(), "=",
							Dao_Self.getInstance().getUser_id()),
					Enum_ResModel.com_relate.name());
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * ����һ���ĵ�
	 * 	@���� ����ĵ����ڣ�ɾ��,��������ڣ�ֱ�Ӳ���
	 * 	
	 * @param tBean
	 */
	public static void addResRecord(T_Resource tBean){
		if(checkExist(tBean.getBook_id())){
			delRes(tBean.getBook_id());
		}
		try {
			addBook(tBean);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * ����һ���ĵ�
	 * 	@���� ����ĵ����ڣ�����,��������ڣ�ֱ�Ӳ���
	 * 	
	 * @param tBean
	 */
	public static void addResRecord_ini(T_Resource tBean){
		if(checkExist(tBean.getBook_id())){
			return;
		}
		try {
			addBook(tBean);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	/**
//	 * ������Դ�е�����Ӧ��ҵ��Ϣ #��ҵID# #��ҵ��# #�Ƿ����# #��ҵLogo#
//	 * 
//	 * @param bookId
//	 * @param name
//	 * @throws DbException
//	 */
//	public static void updateCom(String bookId, Recv_comDetail recvBean)
//			throws DbException {
//		T_Resource bean = new T_Resource();
//		bean.setBook_id(bookId);
//		bean.setCom_id(recvBean.getCom_id());
//		bean.setCom_name(recvBean.getCom_name());
//		bean.setCom_relate(Integer.valueOf(recvBean.getUc_status()));
//		bean.setCom_logo(recvBean.getCom_logo());
//		db.update(bean,
//				WhereBuilder.b(Enum_ResModel.book_id.name(), "=", bookId)
//				.and(Enum_ResModel.self_id.name(), "=",
//						Dao_Self.getInstance().getUser_id()),
//						Enum_ResModel.com_id.name(), Enum_ResModel.com_name.name(),
//						Enum_ResModel.com_relate.name(), Enum_ResModel.com_logo.name());
//	}

	/**
	 * ��ȡ�����ĵ�(�����������) ���ȼ���
	 * 
	 * @note �����˵Ĺ�ϵǿ��
	 * @note �յ�ʱ��
	 * @note ����
	 * @note ����һ���ڵ����ݰ�����������ԭ���ö�
	 * @note ���г�����һ���������ݣ���ÿ��һ���ĵ�һ�Σ�����ָ��+1�����ڱ������������ϴ���������
	 * @return
	 * @throws DbException
	 */
	public static List<T_Resource> getAll_Default() throws DbException {
		return db.findAll(Selector.from(T_Resource.class)
				.where(Enum_ResModel.self_id.name(), "=",
						Dao_Self.getInstance().getUser_id())
				.orderBy(
				Utils_DB.cOrderBy(true, Enum_ResModel.sender_freq.name(),
						"CAST(book_recvTime AS NUMERIC)",
						Enum_ResModel.book_freq.name()), true)); // ����Ƶ��(�ȶ�)
	}

	/**
	 * ��Ĭ�����ȼ��Ļ����ϻ�ȡһ���ڵ�����
	 * 
	 * @return
	 */
	public static List<T_Resource> getAll_WeekIn() throws DbException {
		
		return db.findAll(Selector
				.from(T_Resource.class)
				.where("CAST(book_recvTime AS NUMERIC)", ">",
						FormatUtils.getWeekBeforeTime())
						.and(Enum_ResModel.self_id.name(), "=",
								Dao_Self.getInstance().getUser_id())
				.orderBy(
						Utils_DB.cOrderBy(true,
								Enum_ResModel.sender_freq.name(),
								"CAST(book_recvTime AS NUMERIC)",
								Enum_ResModel.book_freq.name()), true)); // ����Ƶ��(�ȶ�)
	}

	/**
	 * ��Ĭ�����ȼ��Ļ����ϻ�ȡһ��ǰ������
	 * 
	 * @return
	 */
	public static List<T_Resource> getAll_WeekOut(int index) throws DbException {
		Selector selector=Selector.from(T_Resource.class);
		selector.where("CAST(book_recvTime AS NUMERIC)", "<=",
				FormatUtils.getWeekBeforeTime());
		selector.and(Enum_ResModel.self_id.name(), "=",
				Dao_Self.getInstance().getUser_id());
		selector.orderBy(
				Utils_DB.cOrderBy(true,
						Enum_ResModel.sender_freq.name(),
						"CAST(book_recvTime AS NUMERIC)",
						Enum_ResModel.book_freq.name()), true);
		if(index!=Enum_ListLimit.DEFAULT.value()){
			selector.limit(Enum_ListLimit.RESOURCE.value())
			.offset(Enum_ListLimit.RESOURCE.value() * index); 
		}
		
		return db.findAll(selector); // ����Ƶ��(�ȶ�)
	}

	/**
	 * ���ݺ�ת���˵����ж�������
	 * 
	 * @param index
	 * @return
	 * @throws DbException
	 */
	public static List<T_Resource> getAll_Relate(int index) throws DbException {
		Selector selector = Selector.from(T_Resource.class);
		selector.where(Enum_ResModel.self_id.name(), "=", Dao_Self
				.getInstance().getUser_id());
		selector.orderBy(Utils_DB.cOrderBy(true,
				Enum_ResModel.sender_freq.name(),
				"CAST(book_recvTime AS NUMERIC)"), true);
		selector.limit(Enum_ListLimit.RESOURCE.value()).offset(
				Enum_ListLimit.RESOURCE.value() * index);
		return db.findAll(selector);
	}

	/**
	 * �����ĵ��յ�ʱ������
	 * 
	 * @param index
	 * @return
	 * @throws DbException
	 */
	public static List<T_Resource> getAll_Time(int index) throws DbException {
		Selector selector = Selector.from(T_Resource.class);
		selector.where(Enum_ResModel.self_id.name(), "=", Dao_Self
				.getInstance().getUser_id());
		selector.orderBy("CAST(book_recvTime AS NUMERIC)", true);
		selector.limit(Enum_ListLimit.RESOURCE.value()).offset(
				Enum_ListLimit.RESOURCE.value() * index);
		return db.findAll(selector);
	}

	/**
	 * �����ĵ��Ĳ���Ƶ������
	 * 
	 * @param index
	 * @return
	 * @throws DbException
	 */
	public static List<T_Resource> getAll_Freq(int index) throws DbException {
		Selector selector = Selector.from(T_Resource.class);
		selector.where(Enum_ResModel.self_id.name(), "=", Dao_Self
				.getInstance().getUser_id());
		selector.orderBy(Enum_ResModel.book_freq.name(), true);
		selector.limit(Enum_ListLimit.RESOURCE.value()).offset(
				Enum_ListLimit.RESOURCE.value() * index);
		return db.findAll(selector);
	}

	/**
	 * ���ݲ�ͬ��Դɸѡ�ĵ�
	 * 
	 * @param source
	 * @param index
	 * @return
	 * @throws DbException
	 */
	public static List<T_Resource> getAll_Source(Enum_ResSource source,
			int index) throws DbException {
		Selector selector = Selector.from(T_Resource.class);
		switch (source) {
		// ������ҵ
		case SOURCE_RELA:
			selector.where(Enum_ResModel.com_relate.name(), "=",
					Enum_ComType.COM_RELA);
			break;
		// �ͻ�
		case SOURCE_CLIENT:
			selector.where(Enum_ResModel.sender_typeResult.name(), "=",
					Enum_RelaType.CLIENT.value()).or(
					Enum_ResModel.sender_typeResult.name(), "=",
					Enum_RelaType.CLIENT_SUPPLIER.value());
			break;
		// ��Ӧ��
		case SOURCE_SUPPLIER:
			selector.where(Enum_ResModel.sender_typeResult.name(), "=",
					Enum_RelaType.SUPPLIER.value()).or(
					Enum_ResModel.sender_typeResult.name(), "=",
					Enum_RelaType.CLIENT_SUPPLIER.value());
			break;
		// ��ϵ��
		case SOURCE_CONTACT:
			selector.where(Enum_ResModel.sender_typeResult.name(), "=",
					Enum_RelaType.CONTACTS.value());
			break;
		// ������
		case SOURCE_NEWFRIEND:
			selector.where(Enum_ResModel.sender_typeResult.name(), "=",
					Enum_RelaType.NEWFRIEND.value());
			break;

		default:
			break;
		}
		selector.and(Enum_ResModel.self_id.name(), "=",
				Dao_Self.getInstance().getUser_id());
		selector.orderBy("CAST(book_recvTime AS NUMERIC)", true)
				.limit(Enum_ListLimit.RESOURCE.value())
				.offset(Enum_ListLimit.RESOURCE.value() * index);
		return db.findAll(selector);
	}

}
