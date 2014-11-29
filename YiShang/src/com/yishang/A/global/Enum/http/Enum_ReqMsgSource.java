package com.yishang.A.global.Enum.http;

public enum Enum_ReqMsgSource {
	ALL(0),
	SYSTEM(1),
	COMPANY(2),
	USER(3);
	private int value;
	private Enum_ReqMsgSource(int value) {
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
