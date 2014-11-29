package com.yishang.A.global.constant;

/**
 * 管理特定标识的类
 * 
 * @author MM_Zerui
 * 
 */
public class PARAMS {
	/** 网络访问的状态 **/
	public final static int HTTP_REQUEST_SUCCESS = 1000;
	public final static int HTTP_REQUEST_FAIL = 1001;
	public final static int HTTP_REQUEST_DATANULL = 1002;

	/** 搜索框回调信号 **/
	public final static int SEARCH_CALLBACK_NOTE = 1003;

	/** 倒计时模式的设定 **/
	public final static int TIMER_SECOND = 200;
	public final static int TIMER_MINUTE = 201;
	public final static int TIMER_HOUR = 202;
	public final static int TIMER_HOUR_MINUTE = 203;
	public final static int TIMER_MINUTE_SECOND = 204;
	public final static int TIMER_ALL = 205;

	/** 个人信息设定的标识 **/
//	public final static int SELF_SET_NICKNAME = 1;
//	public final static int SELF_SET_GENDER = 2;
//	public final static int SELF_SET_EMAIL = 3;
//	public final static int SELF_SET_BIRTHDAY = 4;
//	public final static int SELF_SET_RANK = 5;
//	public final static int SELF_SET_LABEL = 6;

	/** 拍照截图信号 **/
	public final static int PHOTO_REQUEST=1000;
	// Activity返回码 
	public static int REQUEST_CODE;
	// 拍照的返回码
	public final static int PHOTO_CAPTURE = 1001;
	// 截图的返回码
	public final static int PHOTO_CROP = 1002;
	// 照片缩小比例
	public static final int SCALE = 5;
	// 相片截后的完成状态
	public final static int PHOTO_CAPTURE_COMPLETE = 1003;
	// 截图成功后的状态
	public final static int PHOTO_CAPTURE_SUCCESS = 1004;
	// 截图图片质量
	public final static int IMG_WALL_SIZE = 300;
}
