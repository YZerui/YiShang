package com.yishang.A.global.Enum.db;

/**
 * 人脉关系标识
 * @author MM_Zerui
 *
 */
public enum Enum_RelaType {
	SYSTEM(8),//小秘书
	CLIENT_SUPPLIER(0),  
	CLIENT(1),
	SUPPLIER(2),
	CONTACTS(3),
	NEWFRIEND(4),
	BLACKLIST(5),
	UNKNOW(-1),
	DEFAULT(6),
	ADDRESSLIST(7),
	SELF(9);//认识的人
	private int value;
	private Enum_RelaType(int value){
		this.value=value;
	}
	public int value(){
		return value;
	}
	
	public static Enum_RelaType valueOf(int value){
		switch (value) {
		case 0:
			return CLIENT_SUPPLIER;
		case 1:
			return CLIENT;
		case 2:
			return SUPPLIER;
		case 3:
			return CONTACTS;
		case 4:
			return NEWFRIEND;
		case 5:
			return BLACKLIST;
		case -1:
			return UNKNOW;
		case 6:
			return DEFAULT;
		case 7:
			return ADDRESSLIST;
		case 8:
			return SYSTEM;
		case 9:
			return SELF;
		default:
			return UNKNOW;
		}
	}
}
