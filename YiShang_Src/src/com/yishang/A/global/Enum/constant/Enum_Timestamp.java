package com.yishang.A.global.Enum.constant;

/**
 * ʱ�����س���
 * @author MM_Zerui
 *
 */
public enum Enum_Timestamp {
	DAY(86400000);
	private int value;
	private Enum_Timestamp(int value){
		this.value=value;
	}
	public int value(){
		return value;
	}
}
