package com.yishang.D.service.httpRequest;

import com.http.callBack.myHttpResultCallBack;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yishang.A.global.baseClass.HttpRequestClass;
import com.yishang.A.global.constant.API;
import com.yishang.B.module.c.ResourceEntity.Req_bookInterest;
import com.yishang.D.service.HttpResultService;
import com.yishang.Z.utils.FormatUtils;

/**
 * 对文档感兴趣的请求操作
 * @author MM_Zerui
 *
 */
public class HttpReq_BookInterest extends HttpRequestClass{
	private CallBack_Interest callBack;
	public HttpReq_BookInterest(Req_bookInterest reqBean,final CallBack_Interest callBack) {
		super();
		this.callBack=callBack;
		reqBean.setContent("感兴趣");
		http.send(HttpMethod.POST, API.BOOK_INTEREST, FormatUtils.convertBeanToParams(reqBean), new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
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
						
					}
				}, null, false);
			}
		});
		// TODO Auto-generated constructor stub
	}
	public static abstract class CallBack_Interest{
		public abstract void onSuccess();
		public abstract void onFail();
		public abstract void onStart();
	}
}
