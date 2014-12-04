package com.yishang.A.global.Enum.http;

/**
 * 接口中人脉类型设定
 * @author MM_Zerui
 *
 */
public enum Enum_ReqRelaType {
	CLIENT(1),//客户
	SUPPLIER(2),//供应商
	CONTACT(3),//联系人
	NEWFRIEND(4),//新朋友
	BLOCK(0);//屏蔽
	private int value;
	private Enum_ReqRelaType(int value) {
		// TODO Auto-generated constructor stub
		this.value=value;
	}
	public int value(){
		return value;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(value);
	}
}
