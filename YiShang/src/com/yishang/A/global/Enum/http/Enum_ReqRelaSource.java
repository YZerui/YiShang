package com.yishang.A.global.Enum.http;

/**
 * �ӿ��е������趨��Դ
 * @author MM_Zerui
 *
 */
public enum Enum_ReqRelaSource {
	DOWNLOAD(0),//����ע��
	CONTACTS(1),//ͨѶ¼
	PHONETRANS(2),//�������ת��
	INTEREST(3),//����Ȥ
	RECVBOOK(4),//�����ĵ�
	NEARBY(5),//����
	WIFI(6),//wifi
	CITY(7);//ͬ��
	private int value;
	private Enum_ReqRelaSource(int value) {
		// TODO Auto-generated constructor stub
		this.value=value;
	}
	public int value(){
		return value;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(value);
	}
}
