package com.yishang.A.global.Enum;

public enum Enum_Gender {
	MAN("0"),WOMEN("1");
	private String str;
	private Enum_Gender(String str){
		this.str=str;
	}
	public String value() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	
}
