package com.yishang.D.service.httpRequest;

import java.util.ArrayList;

import com.http.callBack.myHttpResultCallBack;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yishang.A.global.Enum.com.Enum_ComRela;
import com.yishang.A.global.Enum.db.Enum_RelaType;
import com.yishang.A.global.baseClass.HttpRequestClass;
import com.yishang.A.global.constant.API;
import com.yishang.B.module.d.BusinessEntity.Recv_business;
import com.yishang.B.module.d.BusinessEntity.Req_comSearch;
import com.yishang.D.service.HttpResultService;
import com.yishang.Z.utils.FormatUtils;

/**
 * 获取关联企业
 * @author MM_Zerui
 *
 */
public class HttpReq_GetRelateCompany extends HttpRequestClass{
	private int start=0;
	private int limit=5;
	private Req_comSearch reqBean;
	private CallBack_RelaCom callBack;
	private Enum_ComRela enumType;
	private boolean ifLoad;
	public HttpReq_GetRelateCompany(String uId){
		super();
		
		reqBean=new Req_comSearch();
		reqBean.setUid(uId);
	
		reqBean.setStart(String.valueOf((start++)*limit));
	}
	public HttpReq_GetRelateCompany setLimit(int limit){
		this.limit=limit;
		reqBean.setLimit(String.valueOf(limit));
		return this;
	}
	public boolean isIfLoad() {
		return ifLoad;
	}
	public HttpReq_GetRelateCompany setType(Enum_ComRela enumType){
		this.enumType=enumType;
		return this;
	}
	public HttpReq_GetRelateCompany setCallBack(CallBack_RelaCom callBack){
		this.callBack=callBack;
		return this;
	}
	public void httpRequest(Req_comSearch reqBean){
		switch (enumType) {
		case CORRE_ING:
			
		case CORRE_SUCCESS:
		case CORRE_NOT:
			reqBean.setStatus(enumType.toString());
			break;
		case DEFAULT:
			reqBean.setStatus("");
			break;
		default:
			break;
		}
		http.send(HttpMethod.POST,API.BUSINESS_SEARCH,FormatUtils.convertBeanToParams(reqBean), new RequestCallBack<String>() {

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
							ArrayList<Recv_business> list=(ArrayList<Recv_business>)obj;
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
				}, Recv_business.class, true);
			}
		});
	}
	public void onLoad(){
		reqBean.setStart(String.valueOf((start++)*limit));
		ifLoad=true;
		httpRequest(reqBean);
	}
	public void onInit(){
		start=0;
		reqBean.setStart(String.valueOf((start++)*limit));
		ifLoad=false;
		httpRequest(reqBean);
	}
	public static abstract class CallBack_RelaCom{
		public abstract void onFinally();
		public abstract void onFail();
		public abstract void onRefresh(ArrayList<Recv_business> list);
		public abstract void onLoad(ArrayList<Recv_business> list);
		
	}
}
