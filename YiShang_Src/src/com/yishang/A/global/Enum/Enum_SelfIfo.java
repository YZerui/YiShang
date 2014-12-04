package com.yishang.A.global.Enum;

import android.provider.ContactsContract.CommonDataKinds.Nickname;

/**
 * 个人信息标识
 * @author MM_Zerui
 *
 */
public enum Enum_SelfIfo {
	NICKNAME(1),GENDER(2),BIRTHDAY(3),RANK(4),LABEL(5),QQ(6),WECHAT(7),EMAIL(8),FAX(9),INTRO(10),ADDRESS(11);
	private int t;
	private Enum_SelfIfo(int t) {
		// TODO Auto-generated constructor stub
		this.t=t;
	}
	public String value() {
		return String.valueOf(t);
	}
	public int value_int() {
		return t;
	}
	public void setValue(int t) {
		this.t = t;
	}
	public static Enum_SelfIfo getEnum(int value){
		switch (value) {
		case 1:
			return NICKNAME;
		case 2:
			return GENDER;
		case 3:
			return BIRTHDAY;
		case 4:
			return RANK;
		case 5:
			return LABEL;
		case 6:
			return QQ;
		case 7:
			return WECHAT;
		case 8:
			return EMAIL;
		case 9:
			return FAX;
		case 10:
			return INTRO;
		case 11:
			return ADDRESS;
		default:
			return null;
		}
		
	}
	
	
}
