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
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.D.service.HttpResultService;
import com.yishang.D.service.entity.Req_setClient;
import com.yishang.Z.utils.FormatUtils;

/**
 * 设定为客户的方法
 * @author MM_Zerui
 *
 */
public class HttpReq_SetClient extends HttpRequestClass{
	private CallBack_SetClient callBack;
	private Req_setClient reqBean;
	public HttpReq_SetClient(CallBack_SetClient callBack) {
		// TODO Auto-generated constructor stub
		super();
		this.callBack=callBack;
		reqBean=new Req_setClient();
		reqBean.setUid(Dao_Self.getInstance().getUser_id());
	}
	public HttpReq_SetClient setCid(String cid){
		reqBean.setCid(cid);
		return this;
	}
	public void httpMethod(){
		http.send(HttpMethod.POST, API.CLIENT_SET,FormatUtils.convertBeanToParams(reqBean), new RequestCallBack<String>() {

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
				},null, false);
			}
		});
	}
	public static abstract class CallBack_SetClient{
		public abstract void onSuccess();
		public abstract void onFail();
		public abstract void onFinally();
	}
}
