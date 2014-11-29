package com.yishang.A.global.baseClass;

import java.util.ArrayList;

import org.json.JSONObject;

import cn.smssdk.SMSSDK;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.ruifeng.yishang.R;
import com.yishang.A.global.application.AppManager;
import com.yishang.A.global.constant.API;
import com.yishang.A.global.constant.CONSTANT;
import com.yishang.A.global.constant.RECV_STATE;
import com.yishang.B.module.f.LoginUi.LoginHomePage;
import com.yishang.C.dao.daoImpl.AppDaoImpl;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.C.dao.daoModel.T_SelfIfo;
import com.yishang.D.service.sync.SYNCAvosIfoService;
import com.yishang.D.service.sync.SYNCUserIfoService;
import com.yishang.E.view.CustomToast;
import com.yishang.E.view.ProgressDialog;
import com.yishang.Z.utils.DeviceUtils;
import com.yishang.Z.utils.ParseUtils;
import com.yishang.Z.utils.ViewSwitchUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * @author MM_Zerui 应用的初始启动页
 */
public class AppStartActivity extends Activity {

	private Context context;
	@ViewInject(R.id.actionBtn)
	private ImageView actionBtn;
	@ViewInject(R.id.progressBar)
	private ProgressBar progressBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		AppManager.getAppManager().addActivity(this);
		final View view = View.inflate(this, R.layout.app_action_first_page,
				null);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(view);
		ViewUtils.inject(this);
		AlphaAnimation aa = new AlphaAnimation(0.1f, 1.0f);
		aa.setDuration(CONSTANT.APP_START_PAGE_TIME);
		view.startAnimation(aa);

		aa.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation arg0) {
				accountCheckMethod();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationStart(Animation animation) {
			}
		});
	}

	
	/**
	 * 应用�?��时的账号�?��
	 * @step	从数据库�?��，如果发现无个人信息，跳转到登录页面
	 * @step	如有个人信息,�?��Token的有效�?,有效则直接进入到主页,无效则跳转到登录页面
	 * @note	登录到主页时�?��个人信息更新服务
	 */
	public void accountCheckMethod(){
		if(Dao_Self.checkExist()){
			ViewSwitchUtils.in2NormalIntent(context, TabBarActivity.class);
			//这里添加�?��个人信息更新服务的代�?
			Intent intent=new Intent(context, SYNCUserIfoService.class);
			startService(intent);
			//更新AVOS信息
			Intent intent2=new Intent(context, SYNCAvosIfoService.class);
			startService(intent2);
			return;
		}
		ViewSwitchUtils.in2TopIntent(context,LoginHomePage.class);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}

	
}
