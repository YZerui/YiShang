package com.yishang.A.global.constant;

/**
 * 管理不变常量
 * @author MM_Zerui
 *
 */
public class CONSTANT {
	/** ***************************启动页面参数**************************/
	public final static int APP_START_PAGE_TIME=500;
	
	/**************************  个人信息存储标识*************************/
	public final static String DB_USERTOKEN="USERTOKEN";
	
	/**************************视图相关**********************/
	public final static int TABBAR_GRAY=0xFF8C8C8C;
	public final static int TABBAR_ORANGE=0xFFFF8000;
	
	//搜索框移动间隔时间
	public final static int SEARCH_BOX_ANIM=350;
	
	/************************请求访问***********************/
	public final static int LIST_LOAD_LIMIT_DEFAULT=15;
	public final static int LIST_LOAD_LIMIT_MSG=10;
	
	/*************************数据库***********************/
	//通信好友未注册
	public final static String DAO_CONTACTS_UNREGI="0";
	//通信好友已注册
	public final static String DAO_CONTACTS_REGI="1";
	//每次获取的推送消息数
	public final static int DAO_MSG_LIMIT=15;
	//每次往通讯录中获取的未注册的个数
	public final static int DAO_CONTACTS_UNREAD_LIMIT=30;
	//每次往文档资源中获取的文档个数(资源首页)
	public final static int DAO_BOOK_LIMIT=15;
	//企业详情页显示的文档个数
	public final static int DAO_COM_BOOK_LIMIT=5;
	//每次获取到的企业个数
	public final static int DAO_COMPANY_LIMIT=15;
	
	/***********************ShareSDK*********************/
	public final static String SHARE_SMS_APPKEY="3181186de960";
	public final static String SHARE_SMS_APPSECRET="9ebf2204663e0d271a6b3a12ed7f6eb7";
	
	/***********************AvosSDK*********************/
	public final static String AVOS_ID="bi59ml7oayentqx3a3i6njj611pffnkwv2yv1jqa0jw4pp4x";
	public final static String AVOS_KEY="p1dek02ab0h1gyyxcvmmzdrn78s2djky4lvw32iqj9c0va95";
	
	/** *************************倒计时模式的设定************************ **/
	public final static int TIMER_SECOND = 200;
	public final static int TIMER_MINUTE = 201;
	public final static int TIMER_HOUR = 202;
	public final static int TIMER_HOUR_MINUTE = 203;
	public final static int TIMER_MINUTE_SECOND = 204;
	public final static int TIMER_ALL = 205;
	
	/** *************************照片截图常量************************ **/
	//保存在本地的头像截图名
	public final static String IMG_HEAD_CROP_NAME="self_crop_head.png";
	public final static String IMG_HEAD_CROP="self_crop_head";
	//文件名
	public final static String IMG_CROP_FOLDER_NAME="嗨";
	
	
	/************************颜色常量***************************/
	public final static int COLOR_WHITE=0xffffffff;
	public final static int COLOR_WHITE_TRANS=0xaaffffff;
	public final static int COLOR_GRAY=0xffa3a3a3;
	public final static int COLOR_GRAY_DEEP=0xff6c6c6c;
	public final static int COLOR_NOTE=0xffff7e00;
	//搜索结果页面的背景颜色
	public final static int COLOR_SEARCH_RESULT_PAGE=0xeeffffff;
	
	/************************接口参数的相关标识************/
	public final static String API_SMSTYPE_REGI="0";
	public final static String API_SMSTYPE_LOST="1";
	
	
	
}
