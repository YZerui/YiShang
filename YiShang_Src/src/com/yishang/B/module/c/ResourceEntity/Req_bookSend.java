package com.yishang.B.module.c.ResourceEntity;

/**
 * ת���ĵ��ķ���
 * @bid �ĵ�ID
 * @uid �û�ID
 * @tran_ini ԭʼת����ID
 * @tran_send ֱ��ת����ID
 * @author MM_Zerui
 *
 */
public class Req_bookSend {
	private String bid;	//�ĵ�ID
	private String uid; //�û�ID
	private String tran_ini; //ԭʼת����ID
	private String tran_send; //ֱ��ת����ID
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getTran_ini() {
		return tran_ini;
	}
	public void setTran_ini(String tran_ini) {
		this.tran_ini = tran_ini;
	}
	public String getTran_send() {
		return tran_send;
	}
	public void setTran_send(String tran_send) {
		this.tran_send = tran_send;
	}
	
}
