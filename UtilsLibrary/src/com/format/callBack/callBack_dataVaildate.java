package com.format.callBack;

/**
 * 检验数据有效性的回调
 * @author MM_Zerui
 *
 */
public abstract class callBack_dataVaildate {
	/**
	 * 数据为空
	 */
	public abstract void call_lengthNull();
	/**
	 * 数据长度过小
	 */
	public abstract void call_lengthShort();
	/**
	 * 数据长度过长
	 */
	public abstract void call_lengthLong();
	/**
	 * 数据不符合规则
	 */
	public abstract void call_lengthInvalid();
	
	/**
	 * 数据正确
	 */
	public abstract void call_valid();
}
