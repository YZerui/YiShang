package com.yishang.D.service.httpRequest;

import com.format.utils.FormatUtils;
import com.http.callBack.myHttpResultCallBack;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yishang.A.global.baseClass.HttpRequestClass;
import com.yishang.A.global.constant.API;
import com.yishang.B.module.c.ResourceEntity.Recv_bookIfo;
import com.yishang.B.module.c.ResourceEntity.Req_bookIfo;
import com.yishang.D.service.HttpResultService;

/**
 * 请求获取某个文档的详细信息
 * @author MM_Zerui
 *
 */
public class HttpReq_GetResIfo extends HttpRequestClass{
	private CallBack_Res callBack;
	public HttpReq_GetResIfo(String rID,final CallBack_Res call){
		this.callBack=call;
		Req_bookIfo reqBean=new Req_bookIfo();
		reqBean.setId(rID);
		http.send(HttpMethod.POST, API.COMMON_BOOK_IFO,
				FormatUtils.convertBeanToParams(reqBean),new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				call.onFail();
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
							call.onSuccess((Recv_bookIfo)obj);
							return;
						}
						call.onFail();
					}
					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onRequestFail() {
						// TODO Auto-generated method stub
						call.onFail();
					}
					
					@Override
					public void onFinally() {
						// TODO Auto-generated method stub
						call.onFinally();
					}
				}, Recv_bookIfo.class,false);
			}
		});
	}
	public static abstract class CallBack_Res{
		public abstract void onSuccess(Recv_bookIfo bean);
		public abstract void onFail();
		public abstract void onFinally();
	}
}
