package com.format.callBack;

/**
 * ����������Ч�ԵĻص�
 * @author MM_Zerui
 *
 */
public abstract class callBack_dataVaildate {
	/**
	 * ����Ϊ��
	 */
	public abstract void call_lengthNull();
	/**
	 * ���ݳ��ȹ�С
	 */
	public abstract void call_lengthShort();
	/**
	 * ���ݳ��ȹ���
	 */
	public abstract void call_lengthLong();
	/**
	 * ���ݲ����Ϲ���
	 */
	public abstract void call_lengthInvalid();
	
	/**
	 * ������ȷ
	 */
	public abstract void call_valid();
}
