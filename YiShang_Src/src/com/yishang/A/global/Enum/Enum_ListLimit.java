package com.yishang.A.global.Enum;

/**
 * ��¼�б����Limitֵ
 * @author MM_Zerui
 *
 */
public enum Enum_ListLimit {
	//Ĭ�ϣ�����ȡȫ��
	DEFAULT(-1),
	//��Ϣ˳���
	MSG_SEQ(12),
	//ϵͳ��Ϣ
	MSG_SYSTEM(15),
	//��ҵ��Ϣ
	MSG_COMPANY(15),
	//�û���Ϣ
	MSG_CONTACT(15),
	//������
	MSG_NEWFRI(15),
	//�����û�
	NEARBY_USER(50),
	//ͬ��Wifi
	WIFI_USER(20),
	//ͬ���û�
	CITY_USER(50),
	//��Դ�ĵ�
	RESOURCE(15),
	//��ҵ����ҳ�ĵ�
	COM_DETAIL_BOOK(5),
	//�������б�
	RELATIONSHIP(30),
	//������ҵ
	COM_RELATE(15),
	//��ҵ��ҳ�б�
	COM_LIST(30),
	//�̻�����
	MSG_REQ_LSIT(30),
	//ÿ�δӷ���˻�ȡ�����б���
	HTTP_RELALATIONSHIP(20);
	
	private int limit;
	private Enum_ListLimit(int limit){
		this.limit=limit;
	}
	public int value(){
		return limit;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(limit);
	}
}	
