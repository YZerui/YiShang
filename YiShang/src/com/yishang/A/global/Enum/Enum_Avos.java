package com.yishang.A.global.Enum;

/**
 * AVOS配置的相关信息
 * @author MM_Zerui
 *
 */
public enum Enum_Avos {
	ID("bi59ml7oayentqx3a3i6njj611pffnkwv2yv1jqa0jw4pp4x"),
	KEY("p1dek02ab0h1gyyxcvmmzdrn78s2djky4lvw32iqj9c0va95");
	private String param;
	private Enum_Avos(String param){
		this.param=param;
	}
	public String value(){
		return param;
	}
}
