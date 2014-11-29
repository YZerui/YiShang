package com.yishang.A.global.Enum.com;

/**
 * 用户和企业间的关系
 * 
 * @author MM_Zerui
 * 
 */
public enum Enum_ComRela {
	DEFAULT(2), 
	CORRE_ING(0), // 申请中
	CORRE_SUCCESS(1), // 关联
	CORRE_NOT(-1);// 无关联
	private int value;

	private Enum_ComRela(int value) {
		// TODO Auto-generated constructor stub
		this.value = value;
	}

	public int value() {
		return value;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(value);
	}

	public Enum_ComRela getEnum(int value){
		switch (value) {
		case -1:
			return Enum_ComRela.CORRE_NOT;
		case 0:
			return Enum_ComRela.CORRE_ING;
		case 1:
			return Enum_ComRela.CORRE_SUCCESS;
			
		default:
			break;
		}
		return Enum_ComRela.DEFAULT;

	}
}
