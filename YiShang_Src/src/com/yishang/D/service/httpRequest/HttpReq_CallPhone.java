package com.yishang.D.service.httpRequest;

import java.util.ArrayList;

import com.http.callBack.myHttpResultCallBack;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yishang.A.global.application.T_UserPoint;
import com.yishang.A.global.baseClass.HttpRequestClass;
import com.yishang.A.global.constant.API;
import com.yishang.B.module.e.SelfEntity.Req_callPhone;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.D.service.HttpResultService;
import com.yishang.Z.utils.FormatUtils;

/**
 * 拨打电话的操作
 * @author MM_Zerui
 *
 */
public class HttpReq_CallPhone extends HttpRequestClass{
	private Req_callPhone reqBean;
	private CallBack_Phone callBack;
	public HttpReq_CallPhone(){
		// TODO Auto-generated constructor stub
		super();
		reqBean=new Req_callPhone();
		reqBean.setUid(Dao_Self.getInstance().getUser_id());
		
	}
	public HttpReq_CallPhone setPid(String pid){
		reqBean.setPid(pid);
		return this;
	}
	public HttpReq_CallPhone setCallBack(CallBack_Phone callBack){
		this.callBack=callBack;
		return this;
	}
	public void httpMethod(){
		http.send(HttpMethod.POST, API.PHONE_CALL,FormatUtils.convertBeanToParams(reqBean),
				new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				callBack.onFail();
				callBack.onFinally();
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
				}, null,false);
			}
		});
	}
	public static abstract class CallBack_Phone{
		public abstract void onSuccess();
		public abstract void onFail();
		public abstract void onFinally();
	}
}
