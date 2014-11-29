package com.yishang.A.global.Enum;

public enum Enum_BackSytle {
	HORIZONTAL(0),
	VETICAL(1);
	private int value;
	private Enum_BackSytle(int value) {
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
