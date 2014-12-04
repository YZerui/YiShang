package com.yishang.D.service.httpRequest;

import java.util.ArrayList;

import com.format.utils.FormatUtils;
import com.http.callBack.myHttpResultCallBack;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yishang.A.global.Enum.Enum_ListLimit;
import com.yishang.A.global.baseClass.HttpRequestClass;
import com.yishang.A.global.constant.API;
import com.yishang.B.module.b.ContactsEntity.Recv_contacts;
import com.yishang.B.module.b.ContactsEntity.Recv_wifiUser;
import com.yishang.B.module.b.ContactsEntity.Req_wifiUser;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.D.service.HttpResultService;
import com.yishang.Z.utils.DeviceUtils;

/**
 * 获取同个Wif下用户列表信息
 * 
 * @author MM_Zerui
 * 
 */
public class HttpReq_GetLocalWifiUser extends HttpRequestClass {
	private CallBack_WifiUser callBack;
	private Req_wifiUser reqBean;
	public HttpReq_GetLocalWifiUser() {
		super();
		reqBean = new Req_wifiUser();
		reqBean.setWifiMac(DeviceUtils.getWifiMac());
		reqBean.setUid(Dao_Self.getInstance().getUser_id());
		reqBean.setLimit(Enum_ListLimit.WIFI_USER.toString());
	}
	public HttpReq_GetLocalWifiUser setCallBack(CallBack_WifiUser callBack){
		this.callBack=callBack;
		return this;
	}
	public void onInit(){
		callBack.index=0;
		reqBean.setStart(String.valueOf(callBack.index++));
		httpRequest(true);
	}
	public void onLoad(){
		reqBean.setStart(String.valueOf(callBack.index++));
		httpRequest(false);
	}
	public void httpRequest(final boolean ifInit){
		http.send(HttpMethod.POST, API.WIFI_USER,
				FormatUtils.convertBeanToParams(reqBean),
				new RequestCallBack<String>(){

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
									if(ifInit){
										callBack.onSuccess((ArrayList<Recv_wifiUser>)obj);
										return;
									}
									callBack.onLoad((ArrayList<Recv_wifiUser>)obj);
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
						}, Recv_wifiUser.class, true);
					}
				});
	}
	public static abstract class CallBack_WifiUser {
		public int index = 0;

		public abstract void onSuccess(ArrayList<Recv_wifiUser> list);

		public abstract void onLoad(ArrayList<Recv_wifiUser> list);

		public abstract void onFail();
		public abstract void onFinally();
	}
}
