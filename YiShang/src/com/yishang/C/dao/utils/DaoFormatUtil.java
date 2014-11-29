package com.yishang.C.dao.utils;

import com.yishang.A.global.constant.DAO_CONSTANT;

public class DaoFormatUtil {
	/**
	 * 将人脉关系类型转化为文字标识
	 * @param relaType
	 * @return
	 */
	public static String getRelaNote(int relaType){
		String note=new String();
		switch (relaType) {
		case DAO_CONSTANT.DAO_RELA_CLIENT:
			note="我的客户";
			break;
		case DAO_CONSTANT.DAO_RELA_SUPPLIER:
			note="我的供应商";
			break;
		case DAO_CONSTANT.DAO_RELA_CONTACT:
			note="联系人";
			break;
		case DAO_CONSTANT.DAO_RELA_FRIENDS:
			note="新的朋友";
			break;
		case DAO_CONSTANT.DAO_RELA_BLAKLIST:
			note="我的黑名单";
			break;
		case DAO_CONSTANT.DAO_RELA_CONTACT_UNREGI:
			note="来自通信录的未注册好友";
			break;
		default:
			note="未知身份";
			break;
		}
		return note;
	}
}
