package com.yishang.A.global.baseClass;

import android.view.View;

public abstract class SuperViewHolder {
	/**
	 * ��Դ��
	 * @param parent
	 */
	public abstract void initView(View parent);
	/**
	 * ���ݱ仯����
	 * @param position
	 */
	public abstract void refreshData(int position);
	
}
