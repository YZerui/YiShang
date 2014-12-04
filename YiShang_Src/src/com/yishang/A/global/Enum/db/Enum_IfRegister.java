package com.yishang.A.global.Enum.db;

public enum Enum_IfRegister {
	REGIST(1),
	UNREGIST(0);
	private int value;
	private Enum_IfRegister(int value) {
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
