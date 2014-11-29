package com.yishang.D.service.httpRequest;

import com.http.callBack.myHttpResultCallBack;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yishang.A.global.baseClass.HttpRequestClass;
import com.yishang.A.global.baseClass.Req_Common;
import com.yishang.A.global.constant.API;
import com.yishang.B.module.f.LoginEntity.Recv_userIfo;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.D.service.HttpResultService;
import com.yishang.Z.utils.FormatUtils;

/**
 * 获取个人用户信息接口
 * @author MM_Zerui
 *
 */
public class HttpReq_SYNCUserIfo extends HttpRequestClass{
	private CallBack_UserIfo callBack;
	public HttpReq_SYNCUserIfo(String uid,final CallBack_UserIfo callBack) {
		super();
		this.callBack=callBack;
		Req_Common reqBean=new Req_Common();
		reqBean.setId(uid);
		http.send(HttpMethod.POST, API.COMMON_USER_IFO, FormatUtils.convertBeanToParams(reqBean), new RequestCallBack<String>() {

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
					public void onData(boolean validity, Object obj) {
						// TODO Auto-generated method stub
						super.onData(validity, obj);
						if(validity){
							callBack.onSuccess((Recv_userIfo)obj);
							return;
						}
						callBack.onFail();
					}
					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						
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
				}, Recv_userIfo.class, false);
			}
		});
	}
	public static abstract class CallBack_UserIfo{
		public abstract void onSuccess(Recv_userIfo result);
		public abstract void onFail();
		public abstract void onFinally();
	}
}
