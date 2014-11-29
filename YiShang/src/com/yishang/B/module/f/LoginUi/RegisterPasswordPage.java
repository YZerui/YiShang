package com.yishang.B.module.f.LoginUi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
import com.yishang.A.global.baseClass.TabBarActivity;
import com.yishang.A.global.constant.API;
import com.yishang.A.global.constant.CONSTANT;
import com.yishang.A.global.constant.RECV_STATE;
import com.yishang.B.module.e.SelfUi.SelfIfoSettingPage;
import com.yishang.B.module.f.LoginEntity.Recv_userIfo;
import com.yishang.B.module.f.LoginEntity.Req_accountSet;
import com.yishang.C.dao.daoImpl.AppDaoImpl;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.C.dao.daoModel.T_SelfIfo;
import com.yishang.D.service.HttpResultService;
import com.yishang.E.view.CustomDigitalClock;
import com.yishang.E.view.CustomToast;
import com.yishang.E.view.CustomDigitalClock.ClockListener;
import com.yishang.Z.utils.BeanUtils;
import com.yishang.Z.utils.DataCheckUtils;
import com.yishang.Z.utils.DeviceUtils;
import com.yishang.Z.utils.ParseUtils;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * 密码设定页面
 * 
 * @author MM_Zerui
 * @value intent传递信息：手机号码
 * @note 页面跳转目标：应用主页
 */
public class RegisterPasswordPage extends SuperActivity {

	private Req_accountSet reqBean;
	private String phone;

	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.password_first)
	private EditText pFirst;
	@ViewInject(R.id.password_second)
	private EditText pSecond;
	@ViewInject(R.id.confirmBtn)
	private CustomBtnView confirmBtn;
	@ViewInject(R.id.autho_number)
	private EditText authoCode;
	@ViewInject(R.id.codeBtn)
	private CustomBtnView codeBtn;
	@ViewInject(R.id.clock)
	private CustomDigitalClock clock;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.login_register_password_layout);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);

	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		phone = getIntent().getStringExtra("DATA0");
	}

	@Override
	protected void initResource() {
		reqBean = new Req_accountSet();
		clock.setEndTime(System.currentTimeMillis() + 60 * 1000,
				CONSTANT.TIMER_SECOND, "后重新发送");
		clock.setClockListener(clockListener);

		topBar.setCallBack(new topBarCallBack() {
			@Override
			public void call_backBtnListener() {
				// TODO Auto-generated method stub
				super.call_backBtnListener();
				finish();
			}
		});
	}

	private ClockListener clockListener = new ClockListener() {

		@Override
		public void timeEnd() {
			// TODO Auto-generated method stub
			clock.setVisibility(View.INVISIBLE);
			codeBtn.setVisibility(View.VISIBLE);
		}

		@Override
		public void remainFiveMinutes() {
			// TODO Auto-generated method stub

		}
	};

	@OnClick(R.id.codeBtn)
	public void codeClick(View v) {
		clock.setVisibility(View.VISIBLE);
		codeBtn.setVisibility(View.INVISIBLE);
		clock.setEndTime(System.currentTimeMillis() + 60 * 1000,
				CONSTANT.TIMER_SECOND, "后重新发送");
		clock.reqeat();
		RequestParams params = new RequestParams();
		params.addBodyParameter("mobile", phone);
		http.send(HttpMethod.POST, API.PHONE_SUBMIT_AUTHO, params, null);
	}

	@OnClick(R.id.confirmBtn)
	public void confirmClick(View v) {
		reqBean.setMobile(phone);
		final String p1 = pFirst.getText().toString().trim();
		String p2 = pSecond.getText().toString().trim();
		final String code = authoCode.getText().toString().trim();
		if (p1 == null || p1.isEmpty() || p2 == null || p2.isEmpty()
				|| code == null || code.isEmpty()) {
			toast.setText("请编辑好信息后再提交");
			return;
		}
		if (!p1.equals(p2)) {
			toast.setText("前后密码不匹配");
			return;
		}
		DataValidate.checkPassword(p1, new callBack_dataVaildate() {

			@Override
			public void call_valid() {
				// TODO Auto-generated method stub
				reqBean.setCode(code);
				reqBean.setPassword(p1);
				reqBean.setSmsType(CONSTANT.API_SMSTYPE_REGI);
				http.send(HttpMethod.POST, API.PHONE_ACCOUNT_SET,
						myHttpUtils.convertMapToParams(reqBean),
						new RequestCallBack<String>() {
							@Override
							public void onStart() {
								// TODO Auto-generated method stub
								super.onStart();
								httpRun();
							}

							@Override
							public void onFailure(HttpException arg0,
									String arg1) {
								// TODO Auto-generated method stub
								httpEnd();
								toast.setText("注册失败了哦,网络好像出状况了");
							}

							@Override
							public void onSuccess(ResponseInfo<String> params) {
								// TODO Auto-generated method stub
								new HttpResultService(params.result,
										new myHttpResultCallBack() {
											@Override
											public void onData(
													boolean validity, Object obj) {
												// TODO Auto-generated method
												// stub
												super.onData(validity, obj);
												if (validity) {
													Recv_userIfo bean = (Recv_userIfo) obj;
													T_SelfIfo ifoBean = new T_SelfIfo();
													try {
														BeanUtils
																.copyProperties(
																		ifoBean,
																		bean);
														Dao_Self
																.save(ifoBean);
														// 跳转到个人信息设定页面
														ViewSwitchUtils
																.in2BigIntent_int(
																		context,
																		SelfIfoSettingPage.class,
																		SelfIfoSettingPage.SOURCE_REGI_PAGE);
														return;
													} catch (Exception e) {
														// TODO Auto-generated
														// catch block
														e.printStackTrace();
													}

												}
												toast.setText("获取信息出错");
											}

											@Override
											public void onSuccess() {

											}

											@Override
											public void onRequestFail() {
												toast.setText("注册失败了哦,请检查验证码是否正确");
											}

											@Override
											public void onFinally() {

												httpEnd();
											}
										}, Recv_userIfo.class,false);
							}
						});
			}

			@Override
			public void call_lengthShort() {
				// TODO Auto-generated method stub
				toast.setText("密码至少要3位以上");
			}

			@Override
			public void call_lengthNull() {
				// TODO Auto-generated method stub

			}

			@Override
			public void call_lengthLong() {
				// TODO Auto-generated method stub
				toast.setText("密码长度过长");
			}

			@Override
			public void call_lengthInvalid() {
				// TODO Auto-generated method stub
				toast.setText("密码应该由字母或数字组合");
			}
		});

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		ViewSwitchUtils.finishOut2Bottom(context);

	}

	public void httpRun() {
		topBar.setProVisibility(true);
		topBar.setTitle("提交...");
		confirmBtn.setEnabled(false);
	}

	public void httpEnd() {
		topBar.setProVisibility(false);
		topBar.setTitle("设定密码");
		confirmBtn.setEnabled(true);
	}

	@Override
	protected void onClickListener() {
		// TODO Auto-generated method stub
		
	}
}
