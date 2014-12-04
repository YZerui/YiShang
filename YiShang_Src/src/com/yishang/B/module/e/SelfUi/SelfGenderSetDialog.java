package com.yishang.B.module.e.SelfUi;

import com.exception.utils.P;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.ruifeng.yishang.R;
import com.yishang.A.global.Enum.Enum_Gender;
import com.yishang.A.global.Enum.Enum_IfoType;
import com.yishang.A.global.Enum.Enum_SelfIfo;
import com.yishang.A.global.baseClass.SuperDialogActivity;
import com.yishang.A.global.constant.CONSTANT;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.D.service.httpRequest.HttpReq_UpdateIfo;
import com.yishang.D.service.httpRequest.HttpReq_UpdateIfo.CallBack_UserIfo;
import com.yishang.E.view.wheel.WheelView;
import com.yishang.Z.utils.DeviceUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;

/**
 * 性别设定的页面
 * @author MM_Zerui 
 */
public class SelfGenderSetDialog extends SuperDialogActivity{
	public final static String INTENT_GENDER="INTENT_GENDER";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.self_gender_choose_dialog);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
		
	}
	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setPageHeight() {
		// TODO Auto-generated method stub
		lp.height = DeviceUtils.dip2px(SelfGenderSetDialog.this, 45)*2;
		getWindow().setAttributes(lp);// 将设置好属性的lp应用到对话框
	}
	@OnClick(R.id.manSelectBtn)
	public void manSetClick(View v){
		httpMethod(Enum_Gender.MAN.value());
	}
	@OnClick(R.id.womanSelectBtn)
	public void womenSetClick(View v){
		httpMethod(Enum_Gender.WOMEN.value());
		
	}
	private void httpMethod(final String content){
		new HttpReq_UpdateIfo(content, Enum_SelfIfo.GENDER,new CallBack_UserIfo() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Dao_Self.setParams(Enum_IfoType.user_sex,content);
				P.v("设定性别内容:"+content);
				finish();
			}
			
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				toast.setText("设定性别失败");
				finish();
			}
		});
	}
	// 重写finish（）方法，加入关闭时的动画
	public void finish() {
		super.finish();
		this.overridePendingTransition(0, R.anim.out_top_to_bottom_page);
	}
	@Override
	protected void onClickListener() {
		// TODO Auto-generated method stub
		
	}
	

}
