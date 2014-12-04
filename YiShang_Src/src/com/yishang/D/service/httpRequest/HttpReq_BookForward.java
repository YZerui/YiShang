package com.yishang.D.service.httpRequest;

import com.http.callBack.myHttpResultCallBack;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yishang.A.global.baseClass.HttpRequestClass;
import com.yishang.A.global.constant.API;
import com.yishang.B.module.c.ResourceEntity.Req_bookForward;
import com.yishang.D.service.HttpResultService;
import com.yishang.Z.utils.FormatUtils;

/**
 * 转发文档的请求操作
 * 
 * @author MM_Zerui
 * 
 */
public class HttpReq_BookForward extends HttpRequestClass {
	private CallBack_Forward callBack;

	public HttpReq_BookForward(Req_bookForward reqBean,
			final CallBack_Forward callBack) {
		// TODO Auto-generated constructor stub
		super();
		this.callBack = callBack;
		http.send(HttpMethod.POST, API.BOOK_FORWARD,
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
						new HttpResultService(params, new myHttpResultCallBack() {
							
							@Override
							public void onSuccess() {
								//转发成功
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

	public static abstract class CallBack_Forward {
		public abstract void onSuccess();
		public abstract void onFail();
		public abstract void onStart();
	}
}
