package com.yishang.A.global.baseClass;

import com.lidroid.xutils.HttpUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yishang.A.global.application.AppManager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public abstract class SuperDialogActivity extends SuperActivity{
	protected static int screenHeight;
	protected WindowManager.LayoutParams lp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		init();
		setPageHeight();
		super.onCreate(savedInstanceState);
		
		

	}
	/**
	 * �趨����߶�
	 * 	@note �˴�������Ӵ����趨�߶�����: lp.height=h ����h��ʾʵ�ʴ��ڸ߶�ֵ
	 */
	public abstract void setPageHeight();
	private void init() {
		
		screenHeight = getWindow().getWindowManager().getDefaultDisplay()
				.getHeight();// ��ȡ��Ļ�߶�
		lp = getWindow().getAttributes();// //lp�����˲��ֵĺܶ���Ϣ��ͨ��lp�����öԻ���Ĳ���
		lp.width = LayoutParams.FILL_PARENT;
		lp.gravity = Gravity.BOTTOM;
	}
	/**
	 * �Intent���ݵ���ֵ
	 */
	protected abstract void obtainIntentValue();


	/**
	 * ��ʼ��������Դ
	 */
	protected abstract void initResource();
}
