package com.yishang.D.service.httpRequest;

import com.http.callBack.myHttpResultCallBack;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yishang.A.global.baseClass.HttpRequestClass;
import com.yishang.A.global.baseClass.Req_Common;
import com.yishang.A.global.constant.API;
import com.yishang.B.module.e.SelfEntity.Req_avosIfo;
import com.yishang.B.module.e.SelfEntity.Req_wifiMac;
import com.yishang.B.module.f.LoginEntity.Recv_userIfo;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.D.service.HttpResultService;
import com.yishang.D.service.httpRequest.HttpReq_SYNCUserIfo.CallBack_UserIfo;
import com.yishang.Z.utils.DeviceUtils;
import com.yishang.Z.utils.FormatUtils;

/**
 * 同步Wifi Mac地址相关信息
 * @author MM_Zerui
 *
 */
public class HttpReq_SYNCWifi extends HttpRequestClass{
	private CallBack_Wifi callBack;
	public HttpReq_SYNCWifi(final CallBack_Wifi callBack) {
		super();
		this.callBack=callBack;
		Req_wifiMac reqBean=new Req_wifiMac();
		reqBean.setUid(Dao_Self.getInstance().getUser_id());
		reqBean.setWifiMac(DeviceUtils.getWifiMac());
		http.send(HttpMethod.POST, API.SYNC_WIFIMAC, FormatUtils.convertBeanToParams(reqBean), new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				callBack.onFail();
			}

			@Override
			public void onSuccess(ResponseInfo<String> params) {
				// TODO Auto-generated method stub
				new HttpResultService(params.result, new myHttpResultCallBack() {
					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						callBack.onSuccess();
					}
					
					@Override
					public void onRequestFail() {
						// TODO Auto-generated method stub
						callBack.onFail();
					}
					
					@Override
					public void onFinally() {
						// TODO Auto-generated method stub
						callBack.onFinally();
					}
				}, null, false);
			}
		});
	}
	public static abstract class CallBack_Wifi{
		public abstract void onSuccess();
		public abstract void onFail();
		public abstract void onFinally();
	}
}
