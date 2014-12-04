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
import com.yishang.B.module.f.LoginEntity.Recv_userIfo;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.D.service.HttpResultService;
import com.yishang.D.service.httpRequest.HttpReq_SYNCUserIfo.CallBack_UserIfo;
import com.yishang.Z.utils.DeviceUtils;
import com.yishang.Z.utils.FormatUtils;

/**
 * 同步Avos相关信息
 * @author MM_Zerui
 *
 */
public class HttpReq_SYNCAvosIfo extends HttpRequestClass{
	private CallBack_AVOS callBack;
	public HttpReq_SYNCAvosIfo(final CallBack_AVOS callBack) {
		super();
		this.callBack=callBack;
		Req_avosIfo reqBean=new Req_avosIfo();
		reqBean.setAvid(DeviceUtils.getDeviceToken());
		reqBean.setEqType("Android");
		reqBean.setUid(Dao_Self.getInstance().getUser_id());
		http.send(HttpMethod.POST, API.SYNC_AVOS, FormatUtils.convertBeanToParams(reqBean), new RequestCallBack<String>() {

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
	public static abstract class CallBack_AVOS{
		public abstract void onSuccess();
		public abstract void onFail();
		public abstract void onFinally();
	}
}
