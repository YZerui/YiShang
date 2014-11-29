package com.yishang.A.global.Enum.http;

/**
 * 接口中的人脉设定来源
 * @author MM_Zerui
 *
 */
public enum Enum_ReqRelaSource {
	DOWNLOAD(0),//下载注册
	CONTACTS(1),//通讯录
	PHONETRANS(2),//输入号码转发
	INTEREST(3),//感兴趣
	RECVBOOK(4),//接收文档
	NEARBY(5),//附近
	WIFI(6),//wifi
	CITY(7);//同城
	private int value;
	private Enum_ReqRelaSource(int value) {
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
