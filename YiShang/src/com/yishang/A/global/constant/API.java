package com.yishang.A.global.constant;

/**
 * ����ӿ����ݵ���
 * @author MM_Zerui
 *
 */
public class API {
	//����������
	final public static String SERVER_DOMAIN="http://es.wisdomeng.com:80";
	
	final public static String BUG_UPLOAD="http://mmapiss.meimeime.com:8081/MM/uploadBugAction.action";
	/**
	 * ͨ�ýӿ�
	 */
	//�û���Ϣ��ѯ
	final public static String COMMON_USER_IFO=SERVER_DOMAIN+"/user/find.action";
	//�ĵ������ѯ
	final public static String COMMON_BOOK_IFO=SERVER_DOMAIN+"/book/find.action";
	
	//�ύ�ֻ����뵽�������֤
	final public static String PHONE_SUBMIT_AUTHO=SERVER_DOMAIN+"/user/reg_1.action";
	
	//�趨�˻�
	final public static String PHONE_ACCOUNT_SET=SERVER_DOMAIN+"/user/reg_2.action";
	
	//�趨������Ϣ
	final public static String SELF_IFO_SETTING=SERVER_DOMAIN+"/user/modify.action";
	
	//�趨ͷ��
	final public static String SELF_IMG_SET=SERVER_DOMAIN+"/user/uploadHead.action"; 
	
	//��¼
	final public static String ACCOUNT_LOGIN=SERVER_DOMAIN+"/user/login.action";
	
	//��������
	final public static String ADD_CONTACTS=SERVER_DOMAIN+"/user/addContacts.action";
	
	//��ȡ�����б�
	final public static String GET_RELATIONSHIP=SERVER_DOMAIN+"/user/list.action";
	
	//���ݹؼ��ֻ�ȡ��ҵ�б�
	final public static String BUSINESS_SEARCH=SERVER_DOMAIN+"/company/list.action";
	
	//��ȡ��ҵ��Ϣ
	final public static String BUSINESS_DETAIL=SERVER_DOMAIN+"/company/find.action";
	
	//��ȡ��ҵ�ĵ�
	final public static String BUSINESS_BOOK=SERVER_DOMAIN+"/company/books.action";
	
	//������ҵ����
	final public static String BUSINESS_RELATE=SERVER_DOMAIN+"/company/relation.action";
	
	//����û��Ƿ���ĳ��ҵ�����Ȩ
	final public static String BUSINESS_USER_REVIEW=SERVER_DOMAIN+"/company/checkUserRel.action";
	
	//����û��Ƿ�ĳ��ҵ����
	final public static String BUSINESS_USER_BLOCK=SERVER_DOMAIN+"/company/checkUserBrush.action";
	
	//�鿴ĳ�û����еĹ�Ӧ�����Ȩ����ҵ��Ϣ
	final public static String BUSINESS_REVIEW_LIST=SERVER_DOMAIN+"/company/companys.action";
	
	//���ĵ�����Ȥ����
	final public static String BOOK_INTEREST=SERVER_DOMAIN+"/book/interest.action";
	
	//ת���ĵ�
	final public static String BOOK_FORWARD=SERVER_DOMAIN+"/book/forward.action";
	
	//ͬ����γ��
	final public static String USER_SYNCLOC=SERVER_DOMAIN+"/user/synLongLat.action";
	
	//ͬ��avos��Ϣ
	final public static String SYNC_AVOS=SERVER_DOMAIN+"/user/updateAVOS.action";
	
	//ͬ��wifiMac��ַ��Ϣ
	final public static String SYNC_WIFIMAC=SERVER_DOMAIN+"/user/changeWifi.action";
	
	//ת���ĵ��ӿ�
	final public static String BOOK_TRANS=SERVER_DOMAIN+"/book/forward.action";
	
	//��ȡ�̻�
	final public static String MSG_LIST=SERVER_DOMAIN+"/msg/list.action"; 
			
	//��ȡͬ��WiFi���û��ӿ�
	final public static String WIFI_USER=SERVER_DOMAIN+"/user/listNear.action";
	
	//��֤�ֻ������Ƿ�ע��
	final public static String PHONE_REGIST_CHECK=SERVER_DOMAIN+"/user/checkPhone.action";
	
	//����绰���¼�
	final public static String PHONE_CALL=SERVER_DOMAIN+"/user/takePhone.action";
	
	//����Ϊ�ͻ�
	final public static String CLIENT_SET=SERVER_DOMAIN+"/user/upCustomer.action";
	
	//����ĳ��
	final public static String BLOCK_USER=SERVER_DOMAIN+"/user/rebrush.action";
	
	//��ȡת���ĵ���URL
	final public static String GET_RESURL=SERVER_DOMAIN+"/book/initForward.action";
	
}
