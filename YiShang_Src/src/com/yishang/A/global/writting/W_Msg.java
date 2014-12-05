package com.yishang.A.global.writting;

import com.format.utils.DataValidate;
import com.yishang.A.global.Enum.push.Enum_PushType;
import com.yishang.C.dao.daoModel.T_Msg;
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
	/**
	 * ��Ϣ����
	 * @param tEnum
	 * @param bean
	 * @return
	 */
	public static String getContentDetail(Enum_PushType tEnum,T_Msg bean){
		String content=new String();
		switch (tEnum) {
			//����
		case COM_AWARD:
			
			break;
			//����
		case COM_BAOBEI:
			if(bean.getMsg_success()==1){
				content="��ϲ����"+bean.getMsg_comName()+
							"ͨ�������Ĺ������룬���������Դ���"+bean.getMsg_comName()+"��չӪ���ˡ�";
			}else {
				content="�ܱ�Ǹ��"+bean.getMsg_comName()+"û��ͨ�����Ĺ������룬������������ϵ�����Ա��";
			}
			break;
			//�������
		case COM_CHECK:
			if(bean.getMsg_success()==1){
				content="��ϲ��������"+bean.getMsg_comName()+"�ɹ������ͻ�"+bean.getMsg_sendName()+
						"����n�����ڣ�n����ҵ���ã����û��������������";
			}else {
				content="�ܱ�Ǹ������"+bean.getMsg_comName()+"�����Ŀͻ�"+bean.getMsg_sendName()+
						"û�л����׼��������������ϵ�����Ա��";
			}
			break;
			//��ҵ֪ͨ
		case COM_INFORM:
			content=bean.getMsg_content();
			break;
			//��Դ����Ȥ
		case RES_INTEREST:
			content=bean.getMsg_sendName()+"���������ĵ���"+bean.getMsg_resName()+"����ʾ����Ȥ����ȷ�ϸ��û���Ϣ��";
			break;
			//�յ��ĵ�
		case RES_RECEV:
			content="���յ�"+bean.getMsg_sendName()+"ת���������ĵ���"+bean.getMsg_resName()+"��������ġ�";
			break;
			//ϵͳ֪ͨ
		case SYS_INFORM:
			content=bean.getMsg_content();
			break;
			//δ֪��Ϣ
		case DEFAULT:
			content="δ֪��Ϣ";
			break;
			
		default:
			break;
		}
		return content;
	}
	/**
	 * С����Ĭ����ʾ
	 */
	public final static String SYSDefault="��л��ʹ�����̣�������һ���ƶ�����Ӧ�ã�" +
			"�ܹ���������Ч������Ϣ�������������������հ���������ҵ����������Ľ�������" +
			"�Բ鿴��Դ��Ŀ�еġ�����-��ҵ���ɡ�����ƶ����ᡣ�������̵��κ�������飬" +
			"������������������ң����߷��ʼ���peter@wisdomeng.com�������Ǻ���һ�������" +
			"����ף��ҵ�����磡"; 
	
	// public static String msgDoc

}
