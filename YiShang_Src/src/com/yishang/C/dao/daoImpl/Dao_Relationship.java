package com.yishang.C.dao.daoImpl;

import java.util.ArrayList;
import java.util.List;

import com.format.utils.DataValidate;
import com.format.utils.FormatUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import com.yishang.A.global.Enum.Enum_ListLimit;
import com.yishang.A.global.Enum.db.Enum_IfRegister;
import com.yishang.A.global.Enum.db.Enum_RelaNote;
import com.yishang.A.global.Enum.db.Enum_RelaType;
import com.yishang.A.global.Enum.db.Enum_TRelationship;
import com.yishang.A.global.baseClass.SuperDaoImpl;
import com.yishang.A.global.baseClass.SuperDialogActivity;
import com.yishang.A.global.constant.DAO_CONSTANT;
import com.yishang.B.module.b.ContactsEntity.Recv_contacts;
import com.yishang.B.module.f.LoginEntity.Recv_userIfo;
import com.yishang.C.dao.daoModel.T_Contacts;
import com.yishang.C.dao.daoModel.T_Relationships;
import com.yishang.C.dao.utils.Utils_DB;
import com.yishang.C.dao.utils.Utils_DBRNote;
import com.yishang.D.service.httpRequest.HttpReq_SYNCUserIfo;
import com.yishang.D.service.httpRequest.HttpReq_SYNCUserIfo.CallBack_UserIfo;

/**
 * 人脉数据的管理层 优先级：关系强度、最近一次联系时间（任何操作都算），添加为人脉的时间，字母
 * 
 * @author MM_Zerui
 * 
 */
public class Dao_Relationship extends SuperDaoImpl {
	/**
	 * 保存一个记录
	 * 
	 * @param bean
	 * @throws DbException
	 */
	private static void addRecord(T_Relationships bean) throws DbException {
		bean.setRela_recentTime(FormatUtils.getCurrentDateValue());// 最近联系时间
		bean.setSelf_id(Dao_Self.getInstance().getUser_id());
		db.save(bean);
	}

	/**
	 * 保存多项纪录
	 * 
	 * @param list
	 * @throws DbException
	 */
	private static void addList(ArrayList<T_Relationships> list)
			throws DbException {
		db.saveAll(list);
	}

	/**
	 * 删除某用户
	 * @param id
	 */
	private static void delById(String id){
		WhereBuilder whereBuilder=WhereBuilder.
				b(Enum_TRelationship.rela_id.name(), "=",id).and(
				Enum_TRelationship.self_id.name(), "=",
				Dao_Self.getInstance().getUser_id());
		try {
			db.delete(T_Relationships.class,whereBuilder);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 在所有人脉中根据手机号码获取详情
	 * 
	 * @param phone
	 * @return
	 * @throws DbException
	 */
	public static T_Relationships getByPhone_Total(String phone)
			throws DbException {
		Selector selector = Selector.from(T_Relationships.class);
		selector.where(Enum_TRelationship.rela_phone.name(), "=", phone);
		selector.and(Enum_TRelationship.self_id.name(), "=", Dao_Self
				.getInstance().getUser_id());
		return db.findFirst(selector);

	}

	/**
	 * 获取所有人脉记录 优先级：
	 * 
	 * @note 关系强度
	 * @note 最近一次联系时间（任何操作都算）
	 * @note 添加为人脉的时间
	 * @note 字母
	 * 
	 * @return
	 * @throws DbException
	 */
	public static List<T_Relationships> getRecord_All(int index)
			throws DbException {
		Selector selector = Selector.from(T_Relationships.class);
		selector.where(Enum_TRelationship.self_id.name(), "=",
				Dao_Self.getInstance().getUser_id()).orderBy(
				Utils_DB.cOrderBy(true,
						Enum_TRelationship.rela_intension.name(),
						"CAST(rela_recentTime AS NUMERIC)",
						"CAST(rela_addTime AS NUMERIC)"), true);
		if (index != Enum_ListLimit.DEFAULT.value()) {
			selector.limit(Enum_ListLimit.RELATIONSHIP.value()).offset(
					index * Enum_ListLimit.RELATIONSHIP.value());
		}
		return db.findAll(selector);
	}

	/**
	 * 获取不同人脉类型的通讯录名单
	 * 
	 * @param index
	 * @return
	 * @throws DbException
	 */
	public static List<T_Relationships> getRecord_Client(Enum_RelaType type,
			int index) throws DbException {
		Selector selector = Selector.from(T_Relationships.class);
		selector.where(Enum_TRelationship.self_id.name(), "=", Dao_Self
				.getInstance().getUser_id());
		switch (type) {
		case DEFAULT:
			return getRecord_All(index); // 获取所有人脉信
		case ADDRESSLIST: // 除了新朋友和黑名单的熟人
			selector.and(Enum_TRelationship.rela_typeResult.name(), "=",
					Enum_RelaType.CLIENT_SUPPLIER.value())
					.or(Enum_TRelationship.rela_typeResult.name(), "=",
							Enum_RelaType.CLIENT.value())
					.or(Enum_TRelationship.rela_typeResult.name(), "=",
							Enum_RelaType.SUPPLIER.value())
					.or(Enum_TRelationship.rela_typeResult.name(), "=",
							Enum_RelaType.CONTACTS.value());
			break;
		case CLIENT_SUPPLIER:
			selector.and(Enum_TRelationship.rela_typeResult.name(), "=",
					Enum_RelaType.CLIENT_SUPPLIER.value());
			break;
		case CLIENT:
			selector.and(Enum_TRelationship.rela_typeResult.name(), "=",
					Enum_RelaType.CLIENT_SUPPLIER.value()).or(
					Enum_TRelationship.rela_typeResult.name(), "=",
					Enum_RelaType.CLIENT.value());
			break;
		case SUPPLIER:
			selector.and(Enum_TRelationship.rela_typeResult.name(), "=",
					Enum_RelaType.CLIENT_SUPPLIER.value()).or(
					Enum_TRelationship.rela_typeResult.name(), "=",
					Enum_RelaType.SUPPLIER.value());
			break;

		case CONTACTS:
			selector.and(Enum_TRelationship.rela_typeResult.name(), "=",
					Enum_RelaType.CONTACTS.value());
			break;
		case NEWFRIEND:
			selector.and(Enum_TRelationship.rela_typeResult.name(), "=",
					Enum_RelaType.NEWFRIEND.value());
			break;
		case BLACKLIST:
			selector.and(Enum_TRelationship.rela_typeResult.name(), "=",
					Enum_RelaType.BLACKLIST.value());
			break;

		default:
			break;
		}
		selector.orderBy(Utils_DB.cOrderBy(true,
				Enum_TRelationship.rela_intension.name(),
				"CAST(rela_recentTime AS NUMERIC)",
				"CAST(rela_addTime AS NUMERIC)"), true);
		if(index!=Enum_ListLimit.DEFAULT.value()){
			selector.limit(Enum_ListLimit.RELATIONSHIP.value()).offset(
					index * Enum_ListLimit.RELATIONSHIP.value());
		}
	
		return db.findAll(selector);
	}

	/**
	 * 检查某联系人是否存在
	 * 
	 * @param id
	 * @return
	 * @throws DbException
	 */
	private static boolean checkExist(String id) throws DbException {
		if (!DataValidate.checkDataValid(id)) {
			return false;
		}
		Selector selector = Selector.from(T_Relationships.class);
		T_Relationships bean=db.findFirst(selector.where(Enum_TRelationship.rela_id.name(), "=",
				id).and(Enum_TRelationship.self_id.name(), "=",
				Dao_Self.getInstance().getUser_id()));
		if (bean!=null) {
			return true;
		}
		return false;
	}
	
	/**
	 * 更新已经注册的手机用户
	 * 
	 * @note 更新 手机号码 姓名
	 * @param bean
	 * @throws DbException
	 */
	private static void updatePhoneContact(Object bean) throws DbException {
		T_Relationships rBean = parseC2R(bean);
		db.update(
				rBean,
				WhereBuilder.b(Enum_TRelationship.rela_id.name(), "=",
						rBean.getRela_id()).and(
						Enum_TRelationship.self_id.name(), "=",
						Dao_Self.getInstance().getUser_id()),
				Enum_TRelationship.rela_name.name(),
				Enum_TRelationship.rela_phone.name());
	}

	/**
	 * 直接插入通讯录信息到人脉表中
	 * 
	 * @note 设定人脉关系为联系人
	 * @param bean
	 * @throws DbException
	 */
	private static void addPhoneContact(Object bean) throws DbException {
		T_Relationships rBean = parseC2R(bean);
		// 设定为联系人标识
		rBean.setRela_type(Enum_RelaNote.CONTACTS.value());
		addRecord(rBean);
	}

	/**
	 * 增加一个未注册手机用户到人脉中
	 * 
	 * @param bean
	 * @throws DbException
	 */
	public static void addUnRegiPhoneContact(Object bean) throws DbException {
		T_Relationships rBean = parseC2R(bean);
		rBean.setRela_register(Enum_IfRegister.UNREGIST.value());
		addPhoneContact(rBean);
	}
	/**
	 * 添加小秘书联系人
	 * @param tBean
	 */
	public static void addSysContact(T_Relationships tBean){
		try {
			if(checkExist(tBean.getRela_id())){
				return;
			}
			addRecord(tBean);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void addUnRegiPhoneContact(List<T_Contacts> list)
			throws DbException {
		if(list==null){
			return;
		}
		for (T_Contacts item : list) {
			addUnRegiPhoneContact(item);
		}
	}

	/**
	 * 增加一个已注册手机用户到人脉表中
	 * 
	 * @throws DbException
	 * @note 如果人脉表为存在该用户 则直接插入
	 * @note 如果人脉表存在该用户 则更新信息
	 */
	public static void addRegiPhoneContact(List<T_Contacts> list)
			throws DbException {
		for (T_Contacts item : list) {
			addRegiPhoneContact(item);
		}
		;
	}

	/**
	 * 增加已注册手机用户列表到人脉表中
	 * 
	 * @throws DbException
	 * @note 如果人脉表为存在该用户 则直接插入
	 * @note 如果人脉表存在该用户 则更新信息
	 */
	public static void addRegiPhoneContact(Object bean) throws DbException {
		T_Relationships rBean = parseC2R(bean);
		rBean.setRela_register(Enum_IfRegister.REGIST.value());
		if (checkExist(rBean.getRela_id())) {
			updatePhoneContact(rBean);
			return;
		}
		// 直接插入
		addPhoneContact(rBean);
	}

	/**
	 * 删除通讯录好友(未注册)
	 * 
	 * @throws DbException
	 */
	public static void deleteUnRegiPhoneContact() throws DbException {
		db.delete(
				T_Relationships.class,
				WhereBuilder.b(Enum_TRelationship.rela_register.name(), "=",
						Enum_IfRegister.UNREGIST.value()).and(
						Enum_TRelationship.self_id.name(), "=",
						Dao_Self.getInstance().getUser_id()));
	}

	/**
	 * 增加一个从服务端同步过来的人脉信息
	 * 
	 * @note 如果该人脉信息已经存在本地，则更新
	 * @note 如果该人脉信息不存在本地，直接插入
	 * @param bean
	 * @throws DbException
	 */
	public static void addReqContact(Recv_contacts bean) throws DbException {
		T_Relationships rBean = new T_Relationships();
		// 关系建立时间
		rBean.setRela_addTime(bean.getUr_time());
		// 姓名
		rBean.setRela_name(bean.getUser_name());
		// 人脉关系
		rBean.setRela_type(Integer.valueOf(bean.getUr_type()));
		// 头像
		rBean.setRela_head(bean.getUser_head());
		// 均为注册用户
		rBean.setRela_register(Enum_IfRegister.REGIST.value());
		// 对方ID
		rBean.setRela_id(bean.getUr_ob());
		// 关系强度
		rBean.setRela_intension(Integer.valueOf(bean.getUr_co()));
		// 头衔
		rBean.setRela_rank(bean.getUser_title1());
		if (checkExist(bean.getUr_ob())) {
			// 个人ID
			rBean.setSelf_id(Dao_Self.getInstance().getUser_id());
			// 更新本地人脉信息
			db.update(
					rBean,
					WhereBuilder.b(Enum_TRelationship.rela_id.name(), "=",
							bean.getUr_ob()).and(
							Enum_TRelationship.self_id.name(), "=",
							Dao_Self.getInstance().getUser_id()),
					Enum_TRelationship.rela_addTime.name(),
					Enum_TRelationship.rela_name.name(),
					Enum_TRelationship.rela_type.name(),
					Enum_TRelationship.rela_head.name(),
					Enum_TRelationship.self_id.name(),
					Enum_TRelationship.rela_rank.name(),
					Enum_TRelationship.rela_intension.name());
			return;
		}
		// 直接插入
		addRecord(rBean);
	}

	public static void addReqContact(List<Recv_contacts> list)
			throws DbException {
		for (Recv_contacts item : list) {
			addReqContact(item);
		}
	}

	/**
	 * 增加一个人脉信息
	 * 
	 * @note 若果用户不存在，直接插入，如果存在，更新人脉信息返回
	 * @param uid
	 *            对方ID
	 * @throws DbException
	 */
	public static void addRelaRecord(final String uId,final int ur_type,final int ur_co)
			throws DbException {
		
	
		new HttpReq_SYNCUserIfo(uId, new CallBack_UserIfo() {
			
			@Override
			public void onSuccess(Recv_userIfo result) {
				// TODO Auto-generated method stub
				T_Relationships bean = new T_Relationships();
				bean.setRela_id(uId);
				bean.setRela_register(Enum_IfRegister.REGIST.value());// 已经注册
			
				bean.setRela_head(result.getUser_head());
				bean.setRela_name(result.getUser_name());
				bean.setRela_phone(result.getUser_phone());
				bean.setRela_rank(result.getUser_title1());
				bean.setRela_intension(ur_co);
				bean.setRela_type(ur_type);
				try {
					delById(uId);
					addRecord(bean);
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				//如果信息请求失败,只更新关系强度和类型
				try {
					if(checkExist(uId)){
						updateRelation(uId, ur_type);
						updateStrength(uId, ur_co);
					}
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
	}

//	public static void addPushRela(T_Relationships bean) throws DbException {
//		if (checkExist(bean.getRela_id())) {
//			db.update(
//					bean,
//					WhereBuilder.b(Enum_TRelationship.rela_id.name(), "=",
//							bean.getRela_id()).and(
//							Enum_TRelationship.self_id.name(), "=",
//							Dao_Self.getInstance().getUser_id()),
//					Enum_TRelationship.rela_head.name(),
//					Enum_TRelationship.rela_name.name(),
//					Enum_TRelationship.rela_rank.name(),
//					Enum_TRelationship.rela_phone.name(),
//					Enum_TRelationship.rela_recentTime.name(),
//					Enum_TRelationship.rela_intension.name(),
//					Enum_TRelationship.rela_type.name());
//			return;
//		}
//		addRecord(bean);
//	}

	private static T_Relationships parseC2R(Object bean) {
		T_Relationships rBean = new T_Relationships();
		T_Contacts cBean = new T_Contacts();
		if (bean instanceof T_Contacts) {
			cBean = (T_Contacts) bean;
			rBean.setRela_id(((T_Contacts) bean).getUid());
			rBean.setRela_name(cBean.getName());
			rBean.setRela_phone(cBean.getPhone());
		} else {
			rBean = (T_Relationships) bean;
		}
		return rBean;
	}

	/**
	 * 根据电话号码获取信息(未注册通讯录用户)
	 * 
	 * @param phone
	 * @return
	 * @throws DbException
	 */
	public static T_Relationships getByPhone(String phone) throws DbException {
		Selector selector = Selector.from(T_Relationships.class);
		selector.where(Enum_TRelationship.rela_phone.name(), "=", phone)
				.and(Enum_TRelationship.rela_register.name(), "=",
						Enum_IfRegister.UNREGIST.value())
				.and(Enum_TRelationship.self_id.name(), "=",
						Dao_Self.getInstance().getUser_id());
		return db.findFirst(selector);
	}

	/**
	 * 根据用户ID获取信息
	 * 
	 * @param id
	 * @return
	 * @throws DbException
	 */
	public static T_Relationships getByID(String id) throws DbException {
		Selector selector = Selector.from(T_Relationships.class);
		selector.where(Enum_TRelationship.rela_id.name(), "=", id).and(
				Enum_TRelationship.self_id.name(), "=",
				Dao_Self.getInstance().getUser_id());
		return db.findFirst(selector);
	}
	
	/**
	 * 更新和用户间的关系强度
	 * @param uId
	 * @param reltStrength
	 */
	public static void updateStrength(String uId,int reltStrength){
		T_Relationships bean;
		try {
			bean = getByID(uId);
			bean.setRela_recentTime(com.yishang.Z.utils.FormatUtils.getCurrentDateValue());
			bean.setRela_intension(reltStrength);
			WhereBuilder whereBuilder=WhereBuilder.
					b(Enum_TRelationship.rela_id.name(), "=", uId).and(
					Enum_TRelationship.self_id.name(), "=",
					Dao_Self.getInstance().getUser_id());
			db.update(bean,whereBuilder,Enum_TRelationship.rela_intension.name(),
					Enum_TRelationship.rela_recentTime.name());
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	/**
	 * 更新和某用户间的人脉关系
	 * @备注 每次更新都将对最近来往时间进行刷新
	 * @param uId

	 * @throws DbException
	 */
	public static void updateRelation(String uId,int relaType) throws DbException{
		T_Relationships bean=getByID(uId);
		bean.setRela_type(relaType);
		bean.setRela_recentTime(com.yishang.Z.utils.FormatUtils.getCurrentDateValue());
		
//		switch (enumType) {
//		case CLIENT_SUPPLIER:
//			bean.setRela_type(Enum_RelaNote.CLIENT_SUPPLIER.value());
//			break;
//		case CLIENT:
//			bean.setRela_type(Enum_RelaNote.CLIENT.value());
//			break;
//		case SUPPLIER:
//			bean.setRela_type(Enum_RelaNote.SUPPLIER.value());
//			break;
//		case CONTACTS:
//			bean.setRela_type(Enum_RelaNote.CONTACTS.value());
//			break;
//		case NEWFRIEND:
//			bean.setRela_type(Enum_RelaNote.NEWFRIEND.value());
//			break;
//		case BLACKLIST:
//			bean.setRela_type(Enum_RelaNote.BLAKLIST.value());
//			break;
//		default:
//			break;
//		}
		WhereBuilder whereBuilder=WhereBuilder.
				b(Enum_TRelationship.rela_id.name(), "=", uId).and(
				Enum_TRelationship.self_id.name(), "=",
				Dao_Self.getInstance().getUser_id());
		
		db.update(bean,whereBuilder,Enum_TRelationship.rela_type.name(),
				Enum_TRelationship.rela_typeResult.name(),
				Enum_TRelationship.rela_recentTime.name());
	}

}
