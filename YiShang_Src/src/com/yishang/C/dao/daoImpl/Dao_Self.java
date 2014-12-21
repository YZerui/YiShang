package com.yishang.C.dao.daoImpl;

import com.format.utils.DataValidate;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.yishang.A.global.Enum.Enum_IfoType;
import com.yishang.A.global.baseClass.SuperDaoImpl;
import com.yishang.A.global.constant.CONSTANT_SELF;
import com.yishang.B.module.f.LoginEntity.Recv_userIfo;
import com.yishang.C.dao.daoModel.T_SelfIfo;
import com.yishang.Z.utils.BeanUtils;

/**
 * 管理个人信息的Bean
 * 
 * @author MM_Zerui
 * 
 */
public class Dao_Self extends SuperDaoImpl {
	public static boolean checkExist() {
		T_SelfIfo bean;
		try {
			bean = db.findFirst(Selector.from(T_SelfIfo.class));
			if (bean == null) {
				return false;
			}
			return true;
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static T_SelfIfo getInstance() {
		try {
			T_SelfIfo tBean=db.findFirst(Selector.from(T_SelfIfo.class));
			if(DataValidate.checkDataValid(tBean)){
				return tBean;
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new T_SelfIfo();
	}

	public static void save(Object obj) {
		try {
			delete();
			T_SelfIfo tBean = new T_SelfIfo();
			Recv_userIfo rBean = new Recv_userIfo();
			if (obj instanceof T_SelfIfo) {
				tBean = (T_SelfIfo) obj;
			} else if (obj instanceof Recv_userIfo) {
				rBean = (Recv_userIfo) obj;
				BeanUtils.copyProperties(tBean, rBean);

			} else {
				return;
			}
			db.save(tBean);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("保存个人数据出现错误");
		}
	}

	public static void delete() {
		try {
			if (db.tableIsExist(T_SelfIfo.class)) {
				db.deleteAll(T_SelfIfo.class);
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 更改某项信息
	 * 
	 * @param paramName
	 * @param paramValue
	 */
	public static void setParams(Enum_IfoType type, String paramValue) {
		T_SelfIfo bean = getInstance();
		switch (type) {
		case user_email:
			bean.setUser_email(paramValue);
			break;
		case user_bth:
			bean.setUser_bth(paramValue);
			break;
		// case user_eq:
		// bean.setUser_eq(paramValue);
		// break;
		case user_fax:
			bean.setUser_fax(paramValue);
			break;
		case user_address:
			bean.setUser_address(paramValue);
			break;
		case user_head:
			bean.setUser_head(paramValue);
			break;
		case user_id:
			bean.setUser_id(paramValue);
			break;
		case user_intro:
			bean.setUser_intro(paramValue);
			break;
		case user_lable:
			bean.setUser_lable(paramValue);
			break;
		// case user_lat:
		// bean.setUser_lat(paramValue);
		// break;
		case user_locity:
			bean.setUser_locity(paramValue);
			break;
		case user_long:
			bean.setUser_long(paramValue);
			break;
		case user_name:
			bean.setUser_name(paramValue);
			break;
		case user_phcity:
			bean.setUser_phcity(paramValue);
			break;
		case user_phone:
			bean.setUser_phone(paramValue);
			break;
		case user_pt:
			bean.setUser_pt(paramValue);
			break;
		case user_qq:
			bean.setUser_qq(paramValue);
			break;
		case user_rt:
			bean.setUser_rt(paramValue);
			break;
		case user_sex:
			bean.setUser_sex(paramValue);
			break;
		case user_status:
			bean.setUser_status(paramValue);
			break;
		case user_title1:
			bean.setUser_title1(paramValue);
			break;
		case user_title2:
			bean.setUser_title2(paramValue);
			break;
		case user_type:
			bean.setUser_type(paramValue);
			break;
		case user_wx:
			bean.setUser_wx(paramValue);
			break;
		default:
			break;
		}
		try {
			db.update(bean, type.toString());
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
