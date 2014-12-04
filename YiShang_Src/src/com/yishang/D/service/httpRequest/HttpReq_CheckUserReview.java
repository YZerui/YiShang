package com.yishang.D.service.httpRequest;

import com.http.callBack.myHttpResultCallBack;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yishang.A.global.baseClass.HttpRequestClass;
import com.yishang.A.global.constant.API;
import com.yishang.B.module.d.BusinessEntity.Req_comUserReview;
import com.yishang.D.service.HttpResultService;
import com.yishang.Z.utils.FormatUtils;

/**
 * 检测用户审查权
 * 
 * @author MM_Zerui
 * 
 */
public class HttpReq_CheckUserReview extends HttpRequestClass {
	private CallBack_Review callBack;

	public HttpReq_CheckUserReview(Req_comUserReview reqBean,
			final CallBack_Review callBack) {
		// TODO Auto-generated constructor stub
		super();
		this.callBack = callBack;
		http.send(HttpMethod.POST, API.BUSINESS_USER_REVIEW,
				FormatUtils.convertBeanToParams(reqBean),
				new RequestCallBack<String>() {
					@Override
					public void onStart() {
						super.onStart();
						callBack.onStart();
					}
					@Override
					public void onFailure(HttpException arg0, String arg1) {
						callBack.onFail();
					}

					@Override
					public void onSuccess(ResponseInfo<String> params) {
						new HttpResultService(params.result, new myHttpResultCallBack() {
							@Override
							public void onData(boolean validity, Object obj) {
								// TODO Auto-generated method stub
								super.onData(validity, obj);
								if(validity){
									String ableStr=(String)obj;
									if(ableStr.equals("true")){
										callBack.onSuccess();
									}
									return;
								}
								callBack.onFail();
							}
							@Override
							public void onSuccess() {
								
							}
							
							@Override
							public void onRequestFail() {
								callBack.onFail();
							}
							
							@Override
							public void onFinally() {
								
							}
						}, null, false);
					}
				});
	}

	public static abstract class CallBack_Review {
		public abstract void onSuccess();
		public abstract void onFail();
		public abstract void onStart();
	}
}
