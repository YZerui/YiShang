package com.yishang.B.module.f.LoginUi;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;


import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomBtnView;
import com.customview.view.CustomTopbarView;
import com.format.callBack.callBack_dataVaildate;
import com.format.utils.DataValidate;
import com.http.callBack.myHttpResultCallBack;
import com.http.utils.myHttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.ruifeng.yishang.R;
import com.yishang.A.global.baseClass.SuperActivity;
import com.yishang.A.global.constant.API;
import com.yishang.A.global.constant.CONSTANT;
import com.yishang.A.global.constant.RECV_STATE;
import com.yishang.B.module.f.LoginEntity.Req_phoneAutho;
import com.yishang.C.dao.daoImpl.AppDaoImpl;
import com.yishang.D.service.HttpResultService;
import com.yishang.D.service.httpRequest.HttpReq_GetDeviceToken;
import com.yishang.E.view.ClearEditText;
import com.yishang.E.view.CustomToast;
import com.yishang.E.view.ProgressDialog;
import com.yishang.Z.utils.DataCheckUtils;
import com.yishang.Z.utils.DeviceUtils;
import com.yishang.Z.utils.ParseUtils;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * 注册手机号的页面
 * @author MM_Zerui
 * @intent_value 该页面向密码设定页面传递了：号码 
 *
 */
public class RegisterPhonePage extends SuperActivity{
	Req_phoneAutho reqBean;
	
	@ViewInject(R.id.phoneText)
	private ClearEditText phone;
	@ViewInject(R.id.submitBtn)
	private CustomBtnView submitBtn;
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.login_register_phone_layout);
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
		reqBean=new Req_phoneAutho();
		topBar.setCallBack(new topBarCallBack() {
			@Override
			public void call_backBtnListener() {
				// TODO Auto-generated method stub
				super.call_backBtnListener();
				finish();
			}
		});
	}
	@OnClick(R.id.submitBtn)
	public void submitClick(View v){
		final String phoneStr=phone.getText().toString().trim();
		DataValidate.checkPhone(phoneStr, new callBack_dataVaildate() {
			
			@Override
			public void call_valid() {
				// TODO Auto-generated method stub
				httpPhoneAutho(phoneStr);
			}
			
			@Override
			public void call_lengthShort() {
				// TODO Auto-generated method stub
				toast.setText("号码位数不够哦");
			}
			
			@Override
			public void call_lengthNull() {
				// TODO Auto-generated method stub
				toast.setText("请输入手机号码");
			}
			
			@Override
			public void call_lengthLong() {
				// TODO Auto-generated method stub
				toast.setText("号码过长,请再检查下");
			}
			
			@Override
			public void call_lengthInvalid() {
				// TODO Auto-generated method stub
				toast.setText("格式不对哦,注意是11位手机号码");
			}
		});	
	}
	/**
	 * 提交手机号码到服务端验证
	 * @param	software	标明软件来源
	 * @param	token	用户关联本设备的唯一标识
	 */
	private void httpPhoneAutho(final String phone) {
		// TODO Auto-generated method stub
		reqBean.setMobile(phone);
		http.send(HttpMethod.POST,API.PHONE_SUBMIT_AUTHO,myHttpUtils.convertMapToParams(reqBean),new RequestCallBack<String>() {
			@Override
			public void onStart() {
				super.onStart();
				httpRun();
			}
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				httpEnd();
				toast.setText("提交失败,网络好像出状况了哦");
			}
			@Override
			public void onSuccess(ResponseInfo<String> params) {
				new HttpResultService(params.result,new myHttpResultCallBack() {
					
					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						ViewSwitchUtils.in2TopIntent(context,RegisterPasswordPage.class,reqBean.getMobile());
					}
					
					@Override
					public void onRequestFail() {
						// TODO Auto-generated method stub
						toast.setText("提交失败,该号码可能已被注册啦");
					}
					
					@Override
					public void onFinally() {
						// TODO Auto-generated method stub
						httpEnd();
					}
				}, null,false);	
			}
		});
	}
	public void httpRun(){
		topBar.setTitle("提交...");
		topBar.setProVisibility(true);
		submitBtn.setEnabled(false);
	}
	public void httpEnd(){
		topBar.setTitle("手机注册");
		topBar.setProVisibility(false);
		submitBtn.setEnabled(true);
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		ViewSwitchUtils.finishOut2Bottom(context);
	}
	@Override
	protected void onClickListener() {
		// TODO Auto-generated method stub
		
	}
}
