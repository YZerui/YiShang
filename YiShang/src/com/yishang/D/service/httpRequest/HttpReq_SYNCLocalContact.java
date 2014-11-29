package com.yishang.D.service.httpRequest;

import java.util.List;

import com.http.callBack.myHttpResultCallBack;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yishang.A.global.baseClass.HttpRequestClass;
import com.yishang.A.global.constant.API;
import com.yishang.B.module.b.ContactsEntity.Recv_phoneCheck;
import com.yishang.B.module.b.ContactsEntity.Req_phoneCheck;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.D.service.HttpResultService;
import com.yishang.Z.utils.FormatUtils;

/**
 * 同步本地通讯录到服务端
 * @author MM_Zerui
 *
 */
public class HttpReq_SYNCLocalContact extends HttpRequestClass{
	private Req_phoneCheck reqBean;
	private CallBack_CheckPhone callBack;
	public HttpReq_SYNCLocalContact(CallBack_CheckPhone callBack) {
		// TODO Auto-generated constructor stub
		super();
		reqBean=new Req_phoneCheck();
		reqBean.setUid(Dao_Self.getInstance().getUser_id());
		this.callBack=callBack;
	}
	public HttpReq_SYNCLocalContact setPhones(String phones){
		reqBean.setPhones(phones);
		return this;
	}
	
	public void httpRequest(){
		http.send(HttpMethod.POST, API.PHONE_REGIST_CHECK,FormatUtils.convertBeanToParams(reqBean),new RequestCallBack<String>() {

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
					public void onData(boolean validity, Object obj) {
						// TODO Auto-generated method stub
						super.onData(validity, obj);
						if(validity){
							callBack.onSuccess((List<Recv_phoneCheck>)obj);
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
				},Recv_phoneCheck.class,true);
			}
		});
	}
	public static abstract class CallBack_CheckPhone{
		public abstract void onSuccess(List<Recv_phoneCheck> listDatas);
		public abstract void onFail();
		public abstract void onFinally();
	}
}
