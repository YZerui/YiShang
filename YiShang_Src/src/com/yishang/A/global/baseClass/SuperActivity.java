package com.yishang.A.global.baseClass;

import com.lidroid.xutils.HttpUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yishang.A.global.application.AppManager;
import com.yishang.A.global.constant.PARAMS;
import com.yishang.E.view.CustomToast;
import com.yishang.E.view.MyDialog;
import com.yishang.E.view.ProgressDialog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

public abstract class SuperActivity extends Activity{
	//标明跳转的来源页面
	protected int SOURCE_PAGE_NOTE;
	protected String SOURCE_PAGE;
	//上下文环境
	protected Context context;
	//图像加载器
	protected ImageLoader imageLoader;
	protected DisplayImageOptions loadOptions;
	protected HttpUtils http;
	protected ProgressDialog progressDialog;
	protected CustomToast toast;
	protected MyDialog myDialog;
	protected Handler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		context=this;
		imageLoader=ImageLoader.getInstance();
		http=new HttpUtils();
		progressDialog=new ProgressDialog(context);
		toast=new CustomToast(context).locatCenter();
		AppManager.getAppManager().addActivity(this);
		handler=new Handler();
		obtainIntentValue();
		initResource();
		onClickListener();
		super.onCreate(savedInstanceState);		
	}
	/**
	 * 活动Intent传递的数值
	 */
	protected abstract void obtainIntentValue();


	/**
	 * 初始化变量资源
	 */
	protected abstract void initResource();

	protected abstract void onClickListener();
}
