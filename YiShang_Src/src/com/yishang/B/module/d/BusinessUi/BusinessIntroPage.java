package com.yishang.B.module.d.BusinessUi;

import android.os.Bundle;
import android.widget.TextView;

import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomTopbarView;
import com.format.utils.DataValidate;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ruifeng.yishang.R;
import com.yishang.A.global.baseClass.SuperActivity;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * 显示企业简介页面
 * @author MM_Zerui
 *
 */
public class BusinessIntroPage extends SuperActivity{
	@ViewInject(R.id.text)
	private TextView text;
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	
	private String introStr;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.business_intro_page);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
	}
	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		introStr=getIntent().getStringExtra("DATA0");
		
	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		if(!DataValidate.checkDataValid(introStr)){
			text.setText("无更多信息");
			return ;
		}
		text.setText(introStr);
	}

	@Override
	protected void onClickListener() {
		// TODO Auto-generated method stub
		topBar.setCallBack(new topBarCallBack() {
			@Override
			public void call_backBtnListener() {
				// TODO Auto-generated method stub
				super.call_backBtnListener();
				finish();
			}
		});
	}
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		ViewSwitchUtils.finishOut2Right(context);
	}
}
