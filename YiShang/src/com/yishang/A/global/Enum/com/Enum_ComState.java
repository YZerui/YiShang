package com.yishang.A.global.Enum.com;

/**
 * 
 * @author MM_Zerui
 *
 */
public enum Enum_ComState {
	ING(0),
	RELATE(1),
	UNRELATE(-1);
	private int value;
	private Enum_ComState(int value) {
		// TODO Auto-generated constructor stub
		this.value=value;
	}
	public int value(){
		return value;
	}
}
