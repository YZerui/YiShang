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
	 * 设定窗体高度
	 * 	@note 此处必须添加代码设定高度属性: lp.height=h 其中h表示实际窗口高度值
	 */
	public abstract void setPageHeight();
	private void init() {
		
		screenHeight = getWindow().getWindowManager().getDefaultDisplay()
				.getHeight();// 获取屏幕高度
		lp = getWindow().getAttributes();// //lp包含了布局的很多信息，通过lp来设置对话框的布局
		lp.width = LayoutParams.FILL_PARENT;
		lp.gravity = Gravity.BOTTOM;
	}
	/**
	 * 活动Intent传递的数值
	 */
	protected abstract void obtainIntentValue();


	/**
	 * 初始化变量资源
	 */
	protected abstract void initResource();
}
