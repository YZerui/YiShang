package com.yishang.A.global.constant;

/**
 * HTTP请求返回状态码对照表
 * 
 * @author MM_Zerui
 * 
 */
public class RECV_STATE {
	/*************************** 全局通用 ****************************/
	final public static int TOKEN_ERROR=7;
	final public static int TOKEN_WRONG=8;
	final public static int TOKEN_NULL=9;
	/*************************** 接口 通讯密匙 ****************************/
	final public static int KEY_REQUEST_FREQUENT = 1;
	final public static int KEY_REQUEST_UNALLOW = 2;
	final public static int KEY_REQUEST_SUCCESS = 3;
	final public static int KEY_REQUEST_ILLEGAL = 4;
	
	/*************************** 用户信息同步***************************/
	final public static int SYNC_USER_IFO_ERROR=1;
	final public static int SYNC_USER_IFO_SUCCESS=2;
	
	/*************************** 手机号码提交***************************/
	final public static int PHONE_REGI_REPEAT=1;
	final public static int PHONE_REGI_NOTOKEN=2;
	final public static int PHONE_REGI_HASDONE=3;
	final public static int PHONE_REGI_SUCCESS=4;
	final public static int PHONE_REGI_PHONEERROR=5;
	
	/*************************** 账号密码设定***************************/
	final public static int PASSWORD_SUCCESS=1;
	final public static int PASSWORD_FOMATERROR=2;
	
	/*************************** 切换用户 ****************************/
	final public static int LOGIN_ACCOUNT_ERROR=2;
	final public static int LOGIN_PASSWORD_ERROR=3;
	final public static int LOGIN_ACCOUNT_FORBID=4;
	final public static int LOGIN_SUCCESS=5;
	final public static int LOGIN_ERROR_UNKNOW=6;
	
	
	/*************************** 个人信息设定***************************/
	final public static int SELF_IFO_SET_SUCCESS=1;
	final public static int SELF_IFO_PARAM_ERROR=2;
	
	/**************************** 头像编辑 *****************************/
	final public static int IMG_UPLOAD_FORMAT_ERROR=2;
	final public static int IMG_UPLOAD_ID_ERROR=1;
	
	
	/**************************** 标识语  *****************************/
	//针对Token不存在或不正确的情况
	final public static String NOTE_TOKEN_ERROR="当前用户身份可能已失效,请尝试重新登录";
	final public static String NOTE_NETWORK_ERROR="网络处状况了,请检查后再来吧";
	final public static String NOTE_UNKONW_ERROR="抱歉,出现了未知错误";
}
