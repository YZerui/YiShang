package com.yishang.D.service.httpRequest;

import com.http.callBack.myHttpResultCallBack;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yishang.A.global.baseClass.HttpRequestClass;
import com.yishang.A.global.constant.API;
import com.yishang.B.module.b.ContactsEntity.Req_syncLoc;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.D.service.GetLocPointService;
import com.yishang.D.service.HttpResultService;
import com.yishang.D.service.GetLocPointService.CallBack_Loc;
import com.yishang.Z.utils.FormatUtils;

/**
 * 同步经纬度接口
 * 
 * @author MM_Zerui
 * 
 */
public class HttpReq_SYNCLocPoint extends HttpRequestClass {
	Req_syncLoc reqBean;
	private CallBack_SYNCLOC callBack;
	public HttpReq_SYNCLocPoint(final CallBack_SYNCLOC callBack) {
		super();
		this.callBack=callBack;
		reqBean = new Req_syncLoc();
		reqBean.setUid(Dao_Self.getInstance().getUser_id());
		new GetLocPointService(new CallBack_Loc() {

			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				callBack.onFail();
			}

			@Override
			public void getLocPoint(double longitude, double latitude,String city) {
				// TODO Auto-generated method stub
				reqBean.setUser_lat(String.valueOf(latitude));
				reqBean.setUser_long(String.valueOf(longitude));
				http.send(HttpMethod.POST, API.USER_SYNCLOC,
						FormatUtils.convertBeanToParams(reqBean),
						new RequestCallBack<String>() {

							@Override
							public void onFailure(HttpException arg0,
									String arg1) {
								// TODO Auto-generated method stub
								callBack.onFail();
							}

							@Override
							public void onSuccess(ResponseInfo<String> arg0) {
								// TODO Auto-generated method stub
								new HttpResultService(arg0.result, new myHttpResultCallBack() {
									
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
								},null,false);
							}
						});
			}
		});
	}

	public static abstract class CallBack_SYNCLOC {
		public abstract void onSuccess();
		public abstract void onFail();
		public abstract void onFinally();
	}
}
