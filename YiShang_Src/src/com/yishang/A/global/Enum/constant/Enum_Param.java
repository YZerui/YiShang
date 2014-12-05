package com.yishang.A.global.Enum.constant;

public enum Enum_Param {
	TIMEOFFSET_LISTLOAD(300),
	TIME0FFSET_PAGELOAD(150),
	TIMEOFFSET_BOXDOWN(150);
	private int value;
	Enum_Param(int value){
		this.value=value;
	}
	public int value(){
		return value;
	}
}
