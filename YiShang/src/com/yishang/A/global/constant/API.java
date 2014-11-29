package com.yishang.A.global.constant;

/**
 * 管理接口数据的类
 * @author MM_Zerui
 *
 */
public class API {
	//服务器域名
	final public static String SERVER_DOMAIN="http://es.wisdomeng.com:80";
	
	final public static String BUG_UPLOAD="http://mmapiss.meimeime.com:8081/MM/uploadBugAction.action";
	/**
	 * 通用接口
	 */
	//用户信息查询
	final public static String COMMON_USER_IFO=SERVER_DOMAIN+"/user/find.action";
	//文档详情查询
	final public static String COMMON_BOOK_IFO=SERVER_DOMAIN+"/book/find.action";
	
	//提交手机号码到服务端验证
	final public static String PHONE_SUBMIT_AUTHO=SERVER_DOMAIN+"/user/reg_1.action";
	
	//设定账户
	final public static String PHONE_ACCOUNT_SET=SERVER_DOMAIN+"/user/reg_2.action";
	
	//设定个人信息
	final public static String SELF_IFO_SETTING=SERVER_DOMAIN+"/user/modify.action";
	
	//设定头像
	final public static String SELF_IMG_SET=SERVER_DOMAIN+"/user/uploadHead.action"; 
	
	//登录
	final public static String ACCOUNT_LOGIN=SERVER_DOMAIN+"/user/login.action";
	
	//新增人脉
	final public static String ADD_CONTACTS=SERVER_DOMAIN+"/user/addContacts.action";
	
	//获取人脉列表
	final public static String GET_RELATIONSHIP=SERVER_DOMAIN+"/user/list.action";
	
	//根据关键字获取企业列表
	final public static String BUSINESS_SEARCH=SERVER_DOMAIN+"/company/list.action";
	
	//获取企业信息
	final public static String BUSINESS_DETAIL=SERVER_DOMAIN+"/company/find.action";
	
	//获取企业文档
	final public static String BUSINESS_BOOK=SERVER_DOMAIN+"/company/books.action";
	
	//关联企业操作
	final public static String BUSINESS_RELATE=SERVER_DOMAIN+"/company/relation.action";
	
	//检测用户是否有某企业的审查权
	final public static String BUSINESS_USER_REVIEW=SERVER_DOMAIN+"/company/checkUserRel.action";
	
	//检测用户是否被某企业屏蔽
	final public static String BUSINESS_USER_BLOCK=SERVER_DOMAIN+"/company/checkUserBrush.action";
	
	//查看某用户具有的供应商审查权的企业信息
	final public static String BUSINESS_REVIEW_LIST=SERVER_DOMAIN+"/company/companys.action";
	
	//对文档感兴趣触发
	final public static String BOOK_INTEREST=SERVER_DOMAIN+"/book/interest.action";
	
	//转发文档
	final public static String BOOK_FORWARD=SERVER_DOMAIN+"/book/forward.action";
	
	//同步经纬度
	final public static String USER_SYNCLOC=SERVER_DOMAIN+"/user/synLongLat.action";
	
	//同步avos信息
	final public static String SYNC_AVOS=SERVER_DOMAIN+"/user/updateAVOS.action";
	
	//同步wifiMac地址信息
	final public static String SYNC_WIFIMAC=SERVER_DOMAIN+"/user/changeWifi.action";
	
	//转发文档接口
	final public static String BOOK_TRANS=SERVER_DOMAIN+"/book/forward.action";
	
	//获取商机
	final public static String MSG_LIST=SERVER_DOMAIN+"/msg/list.action"; 
			
	//获取同个WiFi下用户接口
	final public static String WIFI_USER=SERVER_DOMAIN+"/user/listNear.action";
	
	//验证手机号码是否注册
	final public static String PHONE_REGIST_CHECK=SERVER_DOMAIN+"/user/checkPhone.action";
	
	//拨打电话的事件
	final public static String PHONE_CALL=SERVER_DOMAIN+"/user/takePhone.action";
	
	//设置为客户
	final public static String CLIENT_SET=SERVER_DOMAIN+"/user/upCustomer.action";
	
	//屏蔽某人
	final public static String BLOCK_USER=SERVER_DOMAIN+"/user/rebrush.action";
	
	//获取转发文档的URL
	final public static String GET_RESURL=SERVER_DOMAIN+"/book/initForward.action";
	
}
