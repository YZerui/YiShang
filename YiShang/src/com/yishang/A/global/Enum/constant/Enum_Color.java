package com.yishang.A.global.Enum.constant;

public enum Enum_Color {
	TextLevelOne(0xff6c6c6c),
	TextLevelTwo(0xffa3a3a3),
	TextLevelThree(0xffbcbcbc),
	TextNote(0xffff7e00);
	private int colorValue;
	private Enum_Color(int colorValue) {
		// TODO Auto-generated constructor stub
		this.colorValue=colorValue;
	}
	public int color(){
		return colorValue;
	}
}
