package com.yishang.A.global.writting;

import com.yishang.C.dao.daoImpl.Dao_Self;

public class W_Share {
	/**
	 * ��ữ����
	 * @param url
	 * @return
	 */
	public static String shareSocial(String url){
		return "����ܲ��������Ƽ���"+url+"���������ɡ����̡��ƶ�Ӫ��App���ɣ����԰�ȫ������Ĳ鿴��";
	}
	/**
	 * �ʼ�����
	 * @param name
	 * @param url
	 * @return
	 */
	public static String shareEmail(String name,String url){
		return name+"���ã�"+
		"����"+Dao_Self.getInstance().getUser_name()+
			"������Ϊ������϶������а����������������Ӳ鿴���������ɡ����̡��ƶ�Ӫ��App���ɣ����԰�ȫ������Ĳ鿴��"+url;
	}
	/**
	 * ���ŷ���
	 * @param name
	 * @return
	 */
	public static String shareMsg(String name,String url){
		return name+"���ã�"+"����"+Dao_Self.getInstance().getUser_name()+
					"������Ϊ������϶������а����������������Ӳ鿴���������ɡ����̡��ƶ�Ӫ��App���ɣ����԰�ȫ������Ĳ鿴��"+url;
	}
}
