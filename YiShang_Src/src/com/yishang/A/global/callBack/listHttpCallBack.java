package com.yishang.A.global.callBack;

/**
 * �б������¼��ص�
 * @author MM_Zerui
 *
 */
public abstract class listHttpCallBack {
	/**
	 * ����б�ĳ�ʼ��
	 */
	public abstract void initListView();
	/**
//	 * �Գ�ʼ��ȡ�����ݽ����б�ĳ�ʼ������
//	 */
//	public abstract void initResult();
	/**
	 * �б�����ˢ�µ��¼�
	 */
	public abstract void onRefresh();
	/**
	 * �б��������ص��¼�
	 */
	public abstract void onLoadMore();
//	public abstract void onRefreshResult(boolean condition,Object obj);
//	public abstract void onLoadResult(boolean condition,Object obj);
}
