package com.yishang.D.service.httpRequest;

import com.http.callBack.myHttpResultCallBack;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yishang.A.global.baseClass.HttpRequestClass;
import com.yishang.A.global.constant.API;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.D.service.HttpResultService;
import com.yishang.D.service.entity.Req_FeedBack;
import com.yishang.Z.utils.FormatUtils;

/**
 * Ã·Ωª∑¥¿°
 * @author MM_Zerui
 *
 */
public class HttpReq_FeedBack extends HttpRequestClass{
	private Req_FeedBack reqBean;
	private CallBack_FeedBack callBack;
	public HttpReq_FeedBack(CallBack_FeedBack callBack){
		this.callBack=callBack;
		reqBean=new Req_FeedBack();
		reqBean.setUid(Dao_Self.getInstance().getUser_id());
	}
	public HttpReq_FeedBack setContent(String content){
		reqBean.setContent(content);
		return this;
	}
	public void httpAction(){
		http.send(HttpMethod.POST, API.FEEDBACK_COMMIT,FormatUtils.convertBeanToParams(reqBean),new RequestCallBack<String>() {
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
				callBack.onFinally();
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
				}, null, false);
			}
		});
	}
	public static abstract class CallBack_FeedBack{
		public abstract void onSuccess();
		public abstract void onFail();
		public abstract void onStart();
		public abstract void onFinally();
	}
}
