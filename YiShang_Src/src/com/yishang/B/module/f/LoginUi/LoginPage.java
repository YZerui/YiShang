package com.yishang.B.module.f.LoginUi;

import android.os.Bundle;
import android.view.View;

import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomBtnView;
import com.customview.view.CustomTopbarView;
import com.format.callBack.callBack_dataVaildate;
import com.format.utils.DataValidate;
import com.http.callBack.myHttpResultCallBack;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.ruifeng.yishang.R;
import com.yishang.A.global.baseClass.SuperActivity;
import com.yishang.A.global.baseClass.TabBarActivity;
import com.yishang.A.global.constant.API;
import com.yishang.B.module.f.LoginEntity.Recv_userIfo;
import com.yishang.B.module.f.LoginEntity.Req_login;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.D.service.HttpResultService;
import com.yishang.E.view.ClearEditText;
import com.yishang.Z.utils.DeviceUtils;
import com.yishang.Z.utils.FormatUtils;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * ��¼ҳ��
 * 
 * @author MM_Zerui
 * @note ��¼�߼����£�
 * @step_1 �����ж���¼�˻�����ȷ�ԣ��û���+���룩
 * @step_2 �л��ɹ����ٴ��������˻�ȡTOKEN
 * @step_3 ����TOKENͬ���û���Ϣ
 * @setp_4 ֪ͨ�л��ɹ������뵽��ҳ��
 * 
 */
public class LoginPage extends SuperActivity {
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.account_edit)
	private ClearEditText account;
	@ViewInject(R.id.password_edit)
	private ClearEditText password;
	@ViewInject(R.id.loginBtn)
	private CustomBtnView customBtn;
	
	private String accountStr, passwordStr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.login_layout);
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
		topBar.setCallBack(new topBarCallBack() {
			@Override
			public void call_backBtnListener() {
				// TODO Auto-generated method stub
				super.call_backBtnListener();
				finish();
			}
		});
	}

	@OnClick(R.id.loginBtn)
	public void loginClick(View v) {
		checkInputData();
	}

	public void checkInputData() {
		
		accountStr = account.getText().toString().trim();
		passwordStr = password.getText().toString().trim();
		if(!DataValidate.checkDataValid(accountStr)){
			toast.setText("�������˺���");
			return;
		}
		DataValidate.checkPassword(passwordStr, new callBack_dataVaildate() {
			
			@Override
			public void call_valid() {
				httpLoginRequest();
			}
			
			@Override
			public void call_lengthShort() {
				// TODO Auto-generated method stub
				toast.setText("��������ȷ����");
			}
			
			@Override
			public void call_lengthNull() {
				// TODO Auto-generated method stub
				toast.setText("����������");
			}
			
			@Override
			public void call_lengthLong() {
				// TODO Auto-generated method stub
				toast.setText("���벻��Ŷ");
			}
			
			@Override
			public void call_lengthInvalid() {
				// TODO Auto-generated method stub
				toast.setText("��������ȷ����");
			}
		});
	}

	public void httpLoginRequest() {
		httpRun();
		Req_login reqBean=new Req_login();
		reqBean.setAvid(DeviceUtils.getDeviceToken());
		reqBean.setEqType(DeviceUtils.getEqType());
		reqBean.setMobile(accountStr);
		reqBean.setPassword(passwordStr);
		reqBean.setWifiMac(DeviceUtils.getWifiMac());
		reqBean.setToken(DeviceUtils.getDeviceToken());
		http.send(HttpMethod.POST, API.ACCOUNT_LOGIN,FormatUtils.convertBeanToParams(reqBean),
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						toast.setText("��������״����");
						httpEnd();
					}

					@Override
					public void onSuccess(ResponseInfo<String> params) {
						new HttpResultService(params.result,new myHttpResultCallBack() {
							@Override
							public void onData(boolean validity, Object obj) {
								// TODO Auto-generated method stub
								super.onData(validity, obj);
								if(validity){
									Recv_userIfo recvBean=(Recv_userIfo)obj;
									Dao_Self.save(recvBean);
									ViewSwitchUtils.nor_toIntent(context, TabBarActivity.class);
									return;
								}
								toast.setText("���ݳ���");
							}
							@Override
							public void onSuccess() {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void onRequestFail() {
								// TODO Auto-generated method stub
								toast.setText("��¼ʧ��,���������Ƿ���ȷ");
							}
							
							@Override
							public void onFinally() {
								// TODO Auto-generated method stub
								httpEnd();
							}
						},Recv_userIfo.class,false);
					}
				});
	}
	public void httpRun(){
		topBar.setProVisibility(true);
		topBar.setTitle("�ύ...");
		
	}
	public void httpEnd(){
		topBar.setProVisibility(false);
		topBar.setTitle("��¼");
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
