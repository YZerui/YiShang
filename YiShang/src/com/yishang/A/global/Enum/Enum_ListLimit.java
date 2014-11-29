package com.yishang.A.global.Enum;

/**
 * 记录列表项的Limit值
 * @author MM_Zerui
 *
 */
public enum Enum_ListLimit {
	//默认，即获取全部
	DEFAULT(-1),
	//消息顺序表
	MSG_SEQ(12),
	//系统消息
	MSG_SYSTEM(15),
	//企业消息
	MSG_COMPANY(15),
	//用户消息
	MSG_CONTACT(15),
	//新朋友
	MSG_NEWFRI(15),
	//附近用户
	NEARBY_USER(50),
	//同个Wifi
	WIFI_USER(20),
	//同城用户
	CITY_USER(50),
	//资源文档
	RESOURCE(15),
	//企业详情页文档
	COM_DETAIL_BOOK(5),
	//人脉总列表
	RELATIONSHIP(30),
	//关联企业
	COM_RELATE(15),
	//企业首页列表
	COM_LIST(30),
	//商机请求
	MSG_REQ_LSIT(30),
	//每次从服务端获取人脉列表项
	HTTP_RELALATIONSHIP(20);
	
	private int limit;
	private Enum_ListLimit(int limit){
		this.limit=limit;
	}
	public int value(){
		return limit;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(limit);
	}
}	
