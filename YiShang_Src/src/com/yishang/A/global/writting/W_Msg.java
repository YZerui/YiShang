package com.yishang.A.global.writting;

import com.format.utils.DataValidate;
import com.yishang.A.global.Enum.push.Enum_PushType;
import com.yishang.C.dao.daoModel.T_MsgSeq;

/**
 * �̻�ģ����İ���ʽ
 * 
 * @author MM_Zerui
 * 
 */
public class W_Msg {
	/**
	 * ��ֹ�ǿ�
	 * 
	 * @param str
	 * @return
	 */
	public static String Y(String str) {
		if (DataValidate.checkDataValid(str)) {
			return str;
		}
		return new String();
	}

	/**
	 * ��Ϣ��ҳ���ı���ʽ
	 * @param name Ϊ��ҵ�����û���
	 * @param tEnum ��Ϣ����
	 * @param success �й�֪ͨ������
	 * @return
	 */
	public static String getNote(boolean success,Enum_PushType tEnum) {
		String note="";
		switch (tEnum) {
		// ϵͳ֪ͨ
		case SYS_INFORM:
			note="����С����";
			break;
		// ��ҵ����
		case COM_AWARD:
			note="�䷢����";
			break;
		// ��ҵ������Ϣ
		case COM_BAOBEI:
			if(success){
				note="�����ɹ�";
			}else {
				note="����ʧ��";
			}
			
			break;
		// ��ҵ�������
		case COM_CHECK:
//			if(success){
//				note="�����ɹ�";
//			}else {
//				note="����ʧ��";
//			}
			note="����֪ͨ";
			break;
		// ��ҵ֪ͨ
		case COM_INFORM:
			note="��ҵ֪ͨ";
			break;
		// ��Դ����Ȥ
		case RES_INTEREST:
			note="����Ȥ";
			break;
		// �յ���Դ�ĵ�
		case RES_RECEV:
			note="�����ĵ�";
			break;
		// δ֪��Ϣ
		case DEFAULT:
			note="δ֪��Ϣ";
			break;

		default:
			break;
		}
		return note;
	}
	
	public static String getContent(Enum_PushType tEnum,T_MsgSeq bean){
		String content=new String();
		switch (tEnum) {
		case COM_AWARD:
			
			break;
		case COM_BAOBEI:
			if(bean.getMsg_seq_success()==1){
				content="���Ա���˾�ı����ɹ�";
			}else {
				content="���Ա���˾�ı���ʧ��";
			}
			
			break;
		case COM_CHECK:
//			if(bean.getMsg_seq_success()==1){
//				content="���ͱ���˾�Ĺ����ɹ�";
//			}else {
//				content="���ͱ���˾�Ĺ���ʧ��";
//			}
			content= bean.getMsg_seq_content();
			break;
		case COM_INFORM:
			content=bean.getMsg_seq_content();
			break;
		case RES_INTEREST:
			content="�ĵ� ��"+bean.getMsg_seq_resName()+"��";
			break;
		case RES_RECEV:
			content="�ĵ� ��"+bean.getMsg_seq_resName()+"��";
			break;
		case SYS_INFORM:
			content=bean.getMsg_seq_content();
			break;
		case DEFAULT:
			content="δ֪��Ϣ";
			break;

		default:
			break;
		}
		return content;
	}

	// public static String msgDoc

}
