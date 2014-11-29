package com.yishang.D.service.httpRequest;

import com.http.callBack.myHttpResultCallBack;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yishang.A.global.baseClass.HttpRequestClass;
import com.yishang.A.global.constant.API;
import com.yishang.B.module.d.BusinessEntity.Req_comIfBlock;
import com.yishang.D.service.HttpResultService;
import com.yishang.Z.utils.FormatUtils;

/**
 * 检测用户是否被屏蔽
 * 
 * @author MM_Zerui
 * 
 */
public class HttpReq_ComUserBolck extends HttpRequestClass {
	private CallBack_Block callBack;

	public HttpReq_ComUserBolck(Req_comIfBlock reqBean, final CallBack_Block callBack) {
		// TODO Auto-generated constructor stub
		super();
		this.callBack = callBack;
		http.send(HttpMethod.POST, API.BUSINESS_USER_BLOCK,
				FormatUtils.convertBeanToParams(reqBean),
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						callBack.onFail();
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
								
							}
						},null, false);
					}
				});

	}

	public static abstract class CallBack_Block {
		public abstract void onSuccess();
		public abstract void onFail();
		public abstract void onStart();
	}
}
