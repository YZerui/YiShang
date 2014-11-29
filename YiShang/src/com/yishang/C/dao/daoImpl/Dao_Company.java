package com.yishang.C.dao.daoImpl;

import java.util.ArrayList;
import java.util.List;

import com.exception.utils.P;
import com.format.utils.DataValidate;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import com.yishang.A.global.Enum.Enum_ListLimit;
import com.yishang.A.global.Enum.com.Enum_ComRela;
import com.yishang.A.global.Enum.com.Enum_ComState;
import com.yishang.A.global.Enum.com.Enum_ComType;
import com.yishang.A.global.Enum.db.Enum_Company;
import com.yishang.A.global.Enum.db.Enum_MsgModel;
import com.yishang.A.global.baseClass.SuperDaoImpl;
import com.yishang.A.global.constant.CONSTANT;
import com.yishang.A.global.constant.DAO_CONSTANT;
import com.yishang.B.module.d.BusinessEntity.Recv_business;
import com.yishang.B.module.d.BusinessEntity.Recv_comDetail;
import com.yishang.C.dao.daoModel.T_Company;
import com.yishang.C.dao.daoModel.T_Resource;
import com.yishang.C.dao.utils.Utils_DB;
import com.yishang.D.service.httpRequest.HttpReq_GetComIfo;
import com.yishang.D.service.httpRequest.HttpReq_GetComIfo.CallBack;
import com.yishang.Z.utils.FormatUtils;

public class Dao_Company extends SuperDaoImpl {
	/**
	 * ���һ����ҵ
	 * 
	 * @param bean
	 * @throws DbException
	 */
	private static void addCompany(T_Company bean) throws DbException {
		P.v("ID"+Dao_Self.getInstance().getUser_id());
		bean.setSelf_id(Dao_Self.getInstance().getUser_id());// ��ʶ�����û�
		bean.setCom_relateTime(FormatUtils.getCurrentDateValue());// ��ʶ����ʱ��
		db.save(bean);
	}

	
	/**
	 * ��Ӷ����ҵ
	 * 
	 * @param list
	 * @throws DbException
	 */
	private static void addCompany(List<T_Company> list) throws DbException {
		db.saveAll(list);
	}

	/**
	 * �ж�ĳ��ҵ�Ƿ����
	 * 
	 * @param cID
	 * @throws DbException
	 */
	private static boolean checkComExist(String cID) throws DbException {
		if (!DataValidate.checkDataValid(cID)) {
			return false;
		}
		Selector selector = Selector.from(T_Company.class);
		selector.where(Enum_Company.com_id.name(), "=", cID);
		selector.and(Enum_Company.self_id.name(), "=", Dao_Self.getInstance()
				.getUser_id());
		if (db.findFirst(selector) != null) {
			return true;
		}
		return false;
	}

	/**
	 * ���º�ĳ��ҵ�Ĺ�ϵ
	 * @param enumType
	 */
	public static void updateComRela(final String comId,final Enum_ComType enumType){
		T_Company bean=new T_Company();
		try {
			if(checkComExist(comId)){
				bean.setCom_relate(enumType.value());
				db.update(bean, WhereBuilder.b(Enum_Company.com_id.name(),"=",comId)
						.and(Enum_Company.self_id.name(),"=",Dao_Self.getInstance().getUser_id()),
						Enum_Company.com_relate.name());
				P.v("������ҵ���ϵ");
				return;
			}
			//���������ȡ����ҵ��Ϣ
			new HttpReq_GetComIfo(comId,new CallBack() {
				
				@Override
				public void requestSuccess(Recv_comDetail bean) {
					// TODO Auto-generated method stub
					T_Company comBean=new T_Company();
					comBean.setCom_id(comId);
					comBean.setCom_name(bean.getCom_name());
					comBean.setCom_relate(enumType.value());//��Ӧ��
					//������ϵʱ�䣬����Ϊ��ǰ
					comBean.setCom_relateTime(com.yishang.Z.utils.FormatUtils.getCurrentDateValue());
					comBean.setCom_review(Integer.valueOf(bean.getUc_in()));//��Ӧ�����Ȩ
					comBean.setCom_state(Integer.valueOf(bean.getUc_status()));//����ҵ�Ĺ�����ϵ
					comBean.setCom_icon(bean.getCom_logo());
					comBean.setCom_abb(bean.getCom_abb());
					try {
						P.v("��Ӹ�����ҵ��Ϣ");
						addComRecord_Total(comBean);
					} catch (DbException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				@Override
				public void requestFail() {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onFinally() {
					// TODO Auto-generated method stub
					
				}
			});
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * ������ҵ��Ϣ(��������ҵ��ϵ)
	 * 
	 * @param record
	 * @throws DbException
	 */
	private static void updateComRecord(T_Company record) throws DbException {
		record.setSelf_id(Dao_Self.getInstance().getUser_id());// ��ʶ�����û�
		record.setCom_relateTime(FormatUtils.getCurrentDateValue());// ��ʶ����ʱ��
		db.update(
				record,
				WhereBuilder.b(Enum_Company.com_id.name(), "=",
						record.getCom_id()).and(Enum_Company.self_id.name(),
						"=", Dao_Self.getInstance().getUser_id())
						,Enum_Company.com_abb.name()
						,Enum_Company.com_icon.name()
						,Enum_Company.com_id.name()
						,Enum_Company.com_name.name()
						,Enum_Company.com_state.name()
						,Enum_Company.com_relateTime.name()
						,Enum_Company.com_review.name()
						,Enum_Company.com_status.name()
						,Enum_Company.self_id.name());
	}
	/**
	 * ������ҵ��Ϣ(������ҵ��ϵ)
	 * 
	 * @param record
	 * @throws DbException
	 */
	private static void updateComRecord_Total(T_Company record) throws DbException {
		record.setSelf_id(Dao_Self.getInstance().getUser_id());// ��ʶ�����û�
		record.setCom_relateTime(FormatUtils.getCurrentDateValue());// ��ʶ����ʱ��
		db.update(
				record,
				WhereBuilder.b(Enum_Company.com_id.name(), "=",
						record.getCom_id()).and(Enum_Company.self_id.name(),
								"=", Dao_Self.getInstance().getUser_id()));
	}

	/**
	 * ������ҵ��Ϣ(�����ڻ�ȡ��ҵ�б��е�ͬ������)
	 * @param recvList
	 * @throws DbException
	 */
	public static void addRecvComRecord(ArrayList<Recv_business> recvList) throws DbException{
		for(Recv_business item:recvList){
			if(item.getUc_status().equals("-1")){
				return;
			}
			T_Company bean=new T_Company();
			
			bean.setCom_review(Integer.valueOf(item.getUc_in()));
			bean.setCom_id(item.getCom_id());
			bean.setCom_abb(item.getCom_abb());
			bean.setCom_name(item.getCom_name());
			bean.setCom_state(Integer.valueOf(item.getUc_status()));
			bean.setCom_icon(item.getCom_logo());
			//�����е���ҵ����Ϊ������ҵ
			if(Integer.valueOf(item.getUc_status())==Enum_ComState.RELATE.value()){
				bean.setCom_relate(Enum_ComType.COM_RELA.value());
				
			}else {
				bean.setCom_relate(Enum_ComType.DEFAULT.value());
				
			}
			addComRecord(bean);
			
			
		} 
	}
	/**
	 * ����һ����ҵ��¼
	 * 
	 * @��ע
	 * @1 ��ӹ���������Ѿ��и���ҵ��¼����ô���ĸ���ҵ��Ϣ�������������ʱ�䣩
	 * @2 ���û�и���ҵ��¼������뵽�������ݿ���
	 * @param record
	 * @throws DbException
	 */
	public static void addComRecord(T_Company record) throws DbException {
		if (checkComExist(record.getCom_id())) {
			updateComRecord(record);
			return;
		}
		addCompany(record);
	}
	/**
	 * ����һ����ҵ��¼
	 * 
	 * @��ע
	 * @1 ��ӹ���������Ѿ��и���ҵ��¼����ô���ĸ���ҵ��Ϣ�������������ʱ�䣩
	 * @2 ���û�и���ҵ��¼������뵽�������ݿ���
	 * @param record
	 * @throws DbException
	 */
	public static void addComRecord_Total(T_Company record) throws DbException {
		if (checkComExist(record.getCom_id())) {
			updateComRecord_Total(record);
			return;
		}
		addCompany(record);
	}

	/**
	 * ������ҵID��ȡ��Ӧ����
	 * 
	 * @param cID
	 * @return
	 * @throws DbException
	 */
	public static T_Company getComRecord(String cID) throws DbException {
		Selector selector = Selector.from(T_Company.class);
		selector.where(Enum_Company.com_id.name(), "=", cID);
		selector.and(Enum_Company.self_id.name(), "=",
				Dao_Self.getInstance().getUser_id());
		return db.findFirst(selector);
	}

	/**
	 * ��ȡ������ҵ
	 * 
	 * @�������
	 * @1 ������ҵ
	 * @2 �ͻ�
	 * @3 ��Ӧ��
	 * @4 ÿ�������ڰ��ս�����ϵ��ʱ������
	 * @param index
	 * @return
	 * @throws DbException
	 */
	private static List<T_Company> getAllCompany(int index) throws DbException {
		Selector selector = Selector.from(T_Company.class);
		selector.where(Enum_Company.com_relate.name(),"=",Enum_ComType.COM_RELA.value())
			.or(Enum_Company.com_relate.name(),"=",Enum_ComType.COM_CLIENT.value())
			.or(Enum_Company.com_relate.name(),"=",Enum_ComType.COM_SUPPLIER.value());
		selector.and(Enum_Company.self_id.name(), "=", Dao_Self.getInstance()
				.getUser_id());
		selector.orderBy(Utils_DB.cOrderBy(false,
				Enum_Company.com_relate.name(),
				Enum_Company.com_relateTime.name()), true);
		
		if(index!=Enum_ListLimit.DEFAULT.value()){
			selector.limit(Enum_ListLimit.COM_LIST.value()).offset(
					index * Enum_ListLimit.COM_LIST.value());
		}
		
		return db.findAll(selector);
	}

	/**
	 * ��ȡ������ҵ
	 * 
	 * @param index
	 * @return
	 * @throws DbException
	 */
	private static List<T_Company> getCompany_relate(int index)
			throws DbException {
		Selector selector = Selector.from(T_Company.class);
		selector.where(Enum_Company.com_relate.name(), "=",
				Enum_ComType.COM_RELA.value());
		selector.and(Enum_Company.self_id.name(), "=", Dao_Self.getInstance()
				.getUser_id());
		selector.limit(Enum_ListLimit.COM_LIST.value()).offset(
				index * Enum_ListLimit.COM_LIST.value());
		return db.findAll(selector);
	}
	
	
//	/**
//	 * ��ȡ�����е���ҵ
//	 * 
//	 * @param index
//	 * @return
//	 * @throws DbException
//	 */
//	private static List<T_Company> getCompany_ing(int index)
//			throws DbException {
//		selector = Selector.from(T_Company.class);
//		selector.where(Enum_Company.com_relate.name(), "=",
//				Enum_ComRela.CORRE_ING.value());
//		selector.limit(Enum_ListLimit.COM_LIST.value()).offset(
//				index * Enum_ListLimit.COM_LIST.value());
//		return db.findAll(selector);
//	}

	/**
	 * ��ȡ�ͻ���ҵ
	 * 
	 * @param index
	 * @return
	 * @throws DbException
	 */
	private static List<T_Company> getCompany_client(int index)
			throws DbException {
		Selector selector = Selector.from(T_Company.class);
		selector.where(Enum_Company.com_relate.name(), "=",
				Enum_ComType.COM_CLIENT.value());
		selector.and(Enum_Company.self_id.name(), "=", Dao_Self.getInstance()
				.getUser_id());
		selector.limit(Enum_ListLimit.COM_LIST.value()).offset(
				index * Enum_ListLimit.COM_LIST.value());
		return db.findAll(selector);
	}

	/**
	 * ��ȡ��Ӧ����ҵ
	 * 
	 * @param index
	 * @return
	 * @throws DbException
	 */
	private static List<T_Company> getCompany_supplier(int index)
			throws DbException {
		Selector selector = Selector.from(T_Company.class);
		selector.where(Enum_Company.com_relate.name(), "=",
				Enum_ComType.COM_SUPPLIER.value());
		selector.and(Enum_Company.self_id.name(), "=", Dao_Self.getInstance()
				.getUser_id());
		selector.limit(Enum_ListLimit.COM_LIST.value()).offset(
				index * Enum_ListLimit.COM_LIST.value());
		return db.findAll(selector);
	}
	/**
	 * ��ȡ���й����е���ҵ
	 * @return
	 * @throws DbException 
	 */
	private static List<T_Company> getCompany_relaIng(int index) throws DbException{
		Selector selector = Selector.from(T_Company.class);
		selector.where(Enum_Company.com_state.name(), "=",
				Enum_ComState.ING.value());
		selector.and(Enum_Company.self_id.name(), "=", Dao_Self.getInstance()
				.getUser_id());
		selector.orderBy(Utils_DB.cOrderBy(false,
				Enum_Company.com_relate.name(),
				Enum_Company.com_relateTime.name()), true);
		selector.limit(Enum_ListLimit.COM_LIST.value()).offset(
				index * Enum_ListLimit.COM_LIST.value());
		return db.findAll(selector);
	}
//	public static 
	/**
	 * ��ȡ���ݵĿ��Žӿ�
	 * @param enumType
	 * @param index
	 * @return
	 * @throws DbException
	 */
	public static List<T_Company> getRecord(Enum_ComType enumType,int index) throws DbException {
		switch (enumType) {
		case COM_ALL:
			return getAllCompany(index);
		case COM_RELA:
			return getCompany_relate(index);
		case COM_CLIENT:
			return getCompany_client(index);
		case COM_SUPPLIER:
			return getCompany_supplier(index);
		case COM_RELA_ING:
			return getCompany_relaIng(index);
		default:
			return getAllCompany(index);
		}
	}
}
