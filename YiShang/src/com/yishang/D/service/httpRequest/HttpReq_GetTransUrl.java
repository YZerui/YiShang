package com.yishang.D.service.httpRequest;

import com.http.callBack.myHttpResultCallBack;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yishang.A.global.baseClass.HttpRequestClass;
import com.yishang.A.global.constant.API;
import com.yishang.D.service.HttpResultService;
import com.yishang.D.service.entity.Req_getUrl;
import com.yishang.Z.utils.FormatUtils;

/**
 * 生成转发文档的URL
 * @author MM_Zerui
 *
 */
public class HttpReq_GetTransUrl extends HttpRequestClass{
	private Req_getUrl reqBean;
	private CallBack_TransUrl callBack;
	public HttpReq_GetTransUrl(CallBack_TransUrl callBack){
		reqBean=new Req_getUrl();
		this.callBack=callBack;
	}
	public HttpReq_GetTransUrl setIniId(String iniId){
		reqBean.setIni(iniId);
		return this;
	}
	public HttpReq_GetTransUrl setUserId(String uId){
		reqBean.setSend(uId);
		return this;
	}
	public HttpReq_GetTransUrl setResId(String rId){
		reqBean.setBid(rId);
		return this;
	}
	public void httpMethod(){
		http.send(HttpMethod.POST, API.GET_RESURL,FormatUtils.convertBeanToParams(reqBean),
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
						new HttpResultService(params.result,new myHttpResultCallBack() {
							@Override
							public void onData(boolean validity, Object obj) {
								// TODO Auto-generated method stub
								super.onData(validity, obj);
								if(validity){
									callBack.onSuccess((String)obj);
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
						},null, false);
					}
		});
	}
	public static abstract class CallBack_TransUrl{
		public abstract void onSuccess(String url);
		public abstract void onFail();
		public abstract void onFinally();
	}
}
