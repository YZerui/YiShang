package com.yishang.A.global.Enum.com;

/**
 * �Ƿ�ӵ�й�Ӧ�����Ȩ��ö��
 * @author MM_Zerui
 *
 */
public enum Enum_ComReview {
	YSE(1),
	NO(0);
	private int value;
	private Enum_ComReview(int value) {
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
