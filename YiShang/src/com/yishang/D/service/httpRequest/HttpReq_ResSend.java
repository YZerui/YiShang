package com.yishang.D.service.httpRequest;

import com.format.utils.FormatUtils;
import com.http.callBack.myHttpResultCallBack;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yishang.A.global.baseClass.HttpRequestClass;
import com.yishang.A.global.constant.API;
import com.yishang.B.module.c.ResourceEntity.Req_bookSend;
import com.yishang.D.service.HttpResultService;

/**
 * 转发文档的请求方法
 * 
 * @author MM_Zerui
 * 
 */
public class HttpReq_ResSend extends HttpRequestClass{
	public HttpReq_ResSend(Req_bookSend reqBean,final CallBack_ResSend callBack) {
		super();
		
		http.send(HttpMethod.POST,API.BOOK_TRANS,FormatUtils.convertBeanToParams(reqBean),new RequestCallBack<String>() {

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

	public static abstract class CallBack_ResSend {
		public abstract void onStart();

		public abstract void onSuccess();

		public abstract void onFail();
		
		public abstract void onFinally();
	}
}
