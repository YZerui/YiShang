package com.yishang.A.global.constant;

/**
 * HTTP���󷵻�״̬����ձ�
 * 
 * @author MM_Zerui
 * 
 */
public class RECV_STATE {
	/*************************** ȫ��ͨ�� ****************************/
	final public static int TOKEN_ERROR=7;
	final public static int TOKEN_WRONG=8;
	final public static int TOKEN_NULL=9;
	/*************************** �ӿ� ͨѶ�ܳ� ****************************/
	final public static int KEY_REQUEST_FREQUENT = 1;
	final public static int KEY_REQUEST_UNALLOW = 2;
	final public static int KEY_REQUEST_SUCCESS = 3;
	final public static int KEY_REQUEST_ILLEGAL = 4;
	
	/*************************** �û���Ϣͬ��***************************/
	final public static int SYNC_USER_IFO_ERROR=1;
	final public static int SYNC_USER_IFO_SUCCESS=2;
	
	/*************************** �ֻ������ύ***************************/
	final public static int PHONE_REGI_REPEAT=1;
	final public static int PHONE_REGI_NOTOKEN=2;
	final public static int PHONE_REGI_HASDONE=3;
	final public static int PHONE_REGI_SUCCESS=4;
	final public static int PHONE_REGI_PHONEERROR=5;
	
	/*************************** �˺������趨***************************/
	final public static int PASSWORD_SUCCESS=1;
	final public static int PASSWORD_FOMATERROR=2;
	
	/*************************** �л��û� ****************************/
	final public static int LOGIN_ACCOUNT_ERROR=2;
	final public static int LOGIN_PASSWORD_ERROR=3;
	final public static int LOGIN_ACCOUNT_FORBID=4;
	final public static int LOGIN_SUCCESS=5;
	final public static int LOGIN_ERROR_UNKNOW=6;
	
	
	/*************************** ������Ϣ�趨***************************/
	final public static int SELF_IFO_SET_SUCCESS=1;
	final public static int SELF_IFO_PARAM_ERROR=2;
	
	/**************************** ͷ��༭ *****************************/
	final public static int IMG_UPLOAD_FORMAT_ERROR=2;
	final public static int IMG_UPLOAD_ID_ERROR=1;
	
	
	/**************************** ��ʶ��  *****************************/
	//���Token�����ڻ���ȷ�����
	final public static String NOTE_TOKEN_ERROR="��ǰ�û���ݿ�����ʧЧ,�볢�����µ�¼";
	final public static String NOTE_NETWORK_ERROR="���紦״����,�����������";
	final public static String NOTE_UNKONW_ERROR="��Ǹ,������δ֪����";
}
