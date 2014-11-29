package com.yishang.D.service.httpRequest;

import com.http.callBack.myHttpResultCallBack;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yishang.A.global.Enum.Enum_SelfIfo;
import com.yishang.A.global.baseClass.HttpRequestClass;
import com.yishang.A.global.constant.API;
import com.yishang.B.module.e.SelfEntity.Req_updateIfo;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.D.service.HttpResultService;
import com.yishang.Z.utils.FormatUtils;

/**
 * 请求更改个人信息
 * 
 * @author MM_Zerui
 * 
 */
public class HttpReq_UpdateIfo extends HttpRequestClass {
	private CallBack_UserIfo callBack;

	public HttpReq_UpdateIfo(String content, final Enum_SelfIfo type,
			final CallBack_UserIfo callBack) {
		// TODO Auto-generated constructor stub
		super();
		this.callBack = callBack;
		Req_updateIfo reqBean = new Req_updateIfo();
		reqBean.setUid(Dao_Self.getInstance().getUser_id());
		reqBean.setT(type.value());
		reqBean.setC(content);
		http.send(HttpMethod.POST, API.SELF_IFO_SETTING,
				FormatUtils.convertBeanToParams(reqBean),
				new RequestCallBack<String>() {
					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						super.onStart();
						callBack.onStart();
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						callBack.onFail();
					}

					@Override
					public void onSuccess(ResponseInfo<String> params) {
						// TODO Auto-generated method stub
						new HttpResultService(params.result,
								new myHttpResultCallBack() {

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

									}
								}, null, false);
					}
				});
	}

	public static abstract class CallBack_UserIfo {
		public abstract void onStart();

		public abstract void onSuccess();

		public abstract void onFail();
	}
}
