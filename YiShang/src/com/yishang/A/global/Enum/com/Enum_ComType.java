package com.yishang.A.global.Enum.com;

import com.yishang.A.global.Enum.push.Enum_PushSource;

public enum Enum_ComType {
	
	COM_RELA(0),
	COM_CLIENT(1),
	COM_SUPPLIER(2),
	COM_RELA_ING(3),//关联中
	COM_ALL(4),//全部
	DEFAULT(-1);
	private int value;
	private Enum_ComType(int value){
		this.value=value;
	}
	public int value(){
		return value;
	}
	public Enum_ComType valueOf(int i){
		switch (i) {
		case 0:
			return Enum_ComType.COM_RELA;
		case 1:
			return Enum_ComType.COM_CLIENT;
		case 2:
			return Enum_ComType.COM_SUPPLIER;
		default:
			return Enum_ComType.DEFAULT;
		}
	}
}
