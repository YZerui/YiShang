package com.yishang.D.service.httpRequest;

import java.util.ArrayList;

import com.http.callBack.myHttpResultCallBack;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yishang.A.global.constant.API;
import com.yishang.B.module.d.BusinessEntity.Recv_comBook;
import com.yishang.B.module.d.BusinessEntity.Req_comBook;
import com.yishang.D.service.HttpResultService;
import com.yishang.Z.utils.FormatUtils;

/**
 * 获取企业文档的服务
 * @author MM_Zerui
 *
 */
public class HttpReq_GetComBook {
	private int start=0;
	private int limit=5;
	HttpUtils http;
	private Req_comBook reqBean;
	private CallBack_Book callBack;
	private boolean ifInit;
	public HttpReq_GetComBook(String cId,int limit){
		this.limit=limit;
		http=new HttpUtils();
		reqBean=new Req_comBook();
		reqBean.setCid(cId);
		reqBean.setLimit(String.valueOf(limit));
		reqBean.setStart(String.valueOf((start++)*limit));
	}
	
	public boolean isIfInit() {
		return ifInit;
	}

	public void setIfInit(boolean ifInit) {
		this.ifInit = ifInit;
	}

	public void setCallBack(CallBack_Book callBack){
		this.callBack=callBack;
	}
	public void httpRequest(Req_comBook reqBean,final boolean ifLoad){
		http.send(HttpMethod.POST,API.BUSINESS_BOOK,FormatUtils.convertBeanToParams(reqBean), new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				callBack.onFail();
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
							ArrayList<Recv_comBook> list=(ArrayList<Recv_comBook>)obj;
							if(ifLoad){
								callBack.onLoad(list);
								return;
							}
							callBack.onRefresh(list);
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
				}, Recv_comBook.class, true);
			}
		});
	}
	public void onLoad(){
		setIfInit(false);
		reqBean.setStart(String.valueOf((start++)*limit));
		httpRequest(reqBean,true);
	}
	public void onRefresh(){
		setIfInit(true);
		start=0;
		reqBean.setStart(String.valueOf((start++)*limit));
		httpRequest(reqBean, false);
	}
	public static abstract class CallBack_Book{
		public abstract void onFinally();
		public abstract void onFail();
		public abstract void onRefresh(ArrayList<Recv_comBook> list);
		public abstract void onLoad(ArrayList<Recv_comBook> list);
		
	}
}
