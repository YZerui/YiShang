package com.yishang.A.global.Enum.http;

/**
 * �ӿ������������趨
 * @author MM_Zerui
 *
 */
public enum Enum_ReqRelaType {
	CLIENT(1),//�ͻ�
	SUPPLIER(2),//��Ӧ��
	CONTACT(3),//��ϵ��
	NEWFRIEND(4),//������
	BLOCK(0);//����
	private int value;
	private Enum_ReqRelaType(int value) {
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
