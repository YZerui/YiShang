package com.yishang.A.global.Enum.push;

/**
 * 推送来源
 * @author MM_Zerui
 *
 */
public enum Enum_PushSource {
	DEFAULT(-1),
	ALL(0), //来自全部
	SYSTEM(1),  //来自系统
	COMPANY(2), //来自企业
	USER(3),	//来自用户
	NEWFRIEND(4),//来自新朋友
	RELACOMLIST(5);//来自关联企业列表
	private int value;
	private Enum_PushSource(int value) {
		// TODO Auto-generated constructor stub
		this.value=value;
	}
	public final int value(){
		return value;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(value);
	}
	public static Enum_PushSource valueOf(int i){
		switch (i) {
		case 1:
			return Enum_PushSource.SYSTEM;
		case 2:
			return Enum_PushSource.COMPANY;
		case 3:
			return Enum_PushSource.USER;
		case 4:
			return Enum_PushSource.NEWFRIEND;
		default:
			return Enum_PushSource.DEFAULT;
		}
	}
}
