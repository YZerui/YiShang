package com.yishang.C.dao.utils;

import com.yishang.A.global.constant.DAO_CONSTANT;

public class DaoFormatUtil {
	/**
	 * ��������ϵ����ת��Ϊ���ֱ�ʶ
	 * @param relaType
	 * @return
	 */
	public static String getRelaNote(int relaType){
		String note=new String();
		switch (relaType) {
		case DAO_CONSTANT.DAO_RELA_CLIENT:
			note="�ҵĿͻ�";
			break;
		case DAO_CONSTANT.DAO_RELA_SUPPLIER:
			note="�ҵĹ�Ӧ��";
			break;
		case DAO_CONSTANT.DAO_RELA_CONTACT:
			note="��ϵ��";
			break;
		case DAO_CONSTANT.DAO_RELA_FRIENDS:
			note="�µ�����";
			break;
		case DAO_CONSTANT.DAO_RELA_BLAKLIST:
			note="�ҵĺ�����";
			break;
		case DAO_CONSTANT.DAO_RELA_CONTACT_UNREGI:
			note="����ͨ��¼��δע�����";
			break;
		default:
			note="δ֪���";
			break;
		}
		return note;
	}
}
