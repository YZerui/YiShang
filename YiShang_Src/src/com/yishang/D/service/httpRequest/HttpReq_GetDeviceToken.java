package com.yishang.D.service.httpRequest;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yishang.A.global.constant.API;
import com.yishang.A.global.constant.RECV_STATE;
import com.yishang.B.module.f.LoginUi.LoginHomePage;
import com.yishang.C.dao.daoImpl.AppDaoImpl;
import com.yishang.Z.utils.DeviceUtils;
import com.yishang.Z.utils.ParseUtils;
import com.yishang.Z.utils.ViewSwitchUtils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * 获取唯一标识的Token
 * 
 * @author MM_Zerui
 * 
 */
public class HttpReq_GetDeviceToken extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		initResource();

	}

	private void initResource() {
		// TODO Auto-generated method stub
	}

	public void httpGetToken() {
		RequestParams params = new RequestParams();
		params.addBodyParameter("software", "aaaaaa");
		params.addBodyParameter("eq_id", DeviceUtils.getDeviceToken());
		HttpUtils http = new HttpUtils();
//		http.send(HttpMethod.POST, API.GET_KEY, params,
//				new RequestCallBack<String>() {
//
//					@Override
//					public void onFailure(HttpException arg0, String arg1) {
//						// TODO Auto-generated method stub
//						onDestroy();
//					}
//
//					@Override
//					public void onSuccess(ResponseInfo<String> params) {
//						// TODO Auto-generated method stub
//						recvBean = ParseUtils.parseJson_TOTAL(params.result,
//								Recv_bean.class);
//						if (recvBean == null) {
//							return;
//						}
//						switch (Integer.valueOf(recvBean.getState())) {
//						case RECV_STATE.KEY_REQUEST_SUCCESS:
//							// 保存Token
//							AppDaoImpl.setUserToken(recvBean.getData());
//							return;
//						case RECV_STATE.KEY_REQUEST_FREQUENT:
//						case RECV_STATE.KEY_REQUEST_ILLEGAL:
//						case RECV_STATE.KEY_REQUEST_UNALLOW:
//							break;
//						default:
//							break;
//						}
//						onDestroy();
//					}
//				});
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		stopSelf();
	}

}
