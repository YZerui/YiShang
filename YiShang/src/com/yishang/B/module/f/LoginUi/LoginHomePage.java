package com.yishang.B.module.f.LoginUi;

import android.os.Bundle;
import android.view.View;

import com.lidroid.xutils.ViewUtils;

import com.lidroid.xutils.view.annotation.event.OnClick;
import com.ruifeng.yishang.R;
import com.yishang.A.global.application.AppManager;
import com.yishang.A.global.baseClass.SuperActivity;
import com.yishang.D.service.sync.SYNCUserIfoService;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * Ӧ�õ�¼��ҳ
 * 
 * @author MM_Zerui
 * @note �ڵ�¼��ҳ����,��������߼�:
 * @step_1 ����TOKEN��ȡ�û���Ϣ��ɳ�ʼ��
 * 
 * 
 */
public class LoginHomePage extends SuperActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_main_layout);
		ViewUtils.inject(this);
	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
//		ViewSwitchUtils.startService(context, SYNCUserIfoService.class);
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		AppManager.getAppManager().finishAllActivity();
	}

	@OnClick(R.id.registerBtn)
	public void registerClick(View v) {
		ViewSwitchUtils.in2TopIntent(context, RegisterPhonePage.class);
	}

	@OnClick(R.id.loginBtn)
	public void loginClick(View v) {
		ViewSwitchUtils.in2TopIntent(context, LoginPage.class);
	}

	@Override
	protected void onClickListener() {
		// TODO Auto-generated method stub
		
	}
	
}
