package com.yishang.A.global.Enum.db;

/**
 * ������ϵ��Э���ʶ
 * @author MM_Zerui
 *
 */
public enum Enum_RelaNote {
	SELF(11111),
	CLIENT_SUPPLIER(11000),
	CLIENT(10000),
	SUPPLIER(1000),
	CONTACTS(100),
	NEWFRIEND(10),
	BLAKLIST(1);
	private int value;
	private Enum_RelaNote(int value) {
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
