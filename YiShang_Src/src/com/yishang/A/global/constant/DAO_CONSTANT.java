package com.yishang.A.global.constant;

/**
 * 数据库相关标识常量
 * 
 * @author MM_Zerui
 * 
 */
public class DAO_CONSTANT {
	/** 人脉表 **/
	public final static int DAO_RELA_CLIENT = 0;
	public final static int DAO_RELA_SUPPLIER = 1;
	public final static int DAO_RELA_CONTACT = 2;
	public final static int DAO_RELA_FRIENDS = 3;
	public final static int DAO_RELA_BLAKLIST = 4;
	public final static int DAO_RELA_CONTACT_UNREGI = 5;
	
	/** 推送消息表 **/
	//推送消息来源类型
	public final static int DAO_MSG_SYSTEM=1;
	public final static int DAO_MSG_COMPANY=2;
	public final static int DAO_MSG_RECEIVE=3;
	public final static int DAO_MSG_FRIEND=4;
	//推送内容类型
	public final static int DAO_MSGTYPE_SYSTEM_INFORM=11;
	public final static int DAO_MSGTYPE_COM_VERIFY=21;//关联审查
	public final static int DAO_MSGTYPE_COM_REPORT=22;//报备
	public final static int DAO_MSGTYPE_COM_INFORM=23;//企业通知
	public final static int DAO_MSGTYPE_COM_CREDIT=24;//企业颁发积分
	public final static int DAO_MSGTYPE_USER_RECEIVE=31;
	public final static int DAO_MSGTYPE_USER_INTEREST=32;
	public final static int DAO_MSGTYPE_USER_CLIENT=33;//成为意向客户
	public final static int DAO_MSGTYPE_FRIEND_RECEIVE=41;
	/** 文档资源表 **/
	//文档来源类型
	public final static int DAO_BOOK_INTEREST=0;
	public final static int DAO_BOOK_CONTACT=1;
	public final static int DAO_BOOK_FRIEND=2;
	//文档状态
	public final static int DAO_BOOKSTATUS_UNRELEASE=0;
	public final static int DAO_BOOKSTATUS_RELEASE=1;
	public final static int DAO_BOOKSTATUS_DROP=2;
	//文档是否下载
	public final static int DAO_BOOKDOWN_TRUE=1;
	public final static int DAO_BOOKDOWN_FALSE=0;
	
	/**企业表**/
	public final static int DAO_COMPANY_RELATE=0;
	public final static int DAO_COMPANY_CLIENT=1;
	public final static int DAO_COMPANY_SUPPLIER=2;
	
	/** 是否为易商注册用户 **/
	public final static int DAO_REGISTER_TRUE=1;
	public final static int DAO_REGISTER_FALSE=0;
}
