package com.yishang.D.service.httpRequest;

import java.util.ArrayList;
 
import com.exception.utils.P;
import com.http.callBack.myHttpResultCallBack;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yishang.A.global.Enum.http.Enum_ReqRelaSource;
import com.yishang.A.global.Enum.http.Enum_ReqRelaType;
import com.yishang.A.global.application.T_UserPoint;
import com.yishang.A.global.baseClass.HttpRequestClass;
import com.yishang.A.global.constant.API;
import com.yishang.B.module.b.ContactsEntity.Recv_wifiUser;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.D.service.HttpResultService;
import com.yishang.D.service.entity.Req_addContact;
import com.yishang.Z.utils.BeanUtils;
import com.yishang.Z.utils.FormatUtils;
import com.yishang.Z.utils.ParseUtils;

/**
 * 新增人脉的服务
 * 
 * @author MM_Zerui
 * 
 */
public class HttpReq_AddContacts extends HttpRequestClass{
	private ACCallBack accBack;
	private Req_addContact reqBean;
	private Enum_ReqRelaType enumType;
	private Enum_ReqRelaSource enumSource;
	public HttpReq_AddContacts( ACCallBack callBack) {
		reqBean=new Req_addContact();
		accBack = callBack;

	}
	/**
	 * 要添加的对方ID
	 * @param bUid
	 * @return
	 */
	public HttpReq_AddContacts setBeUid(String bUid){
		reqBean.setBeUID(bUid);
		return this;
	}
	/**
	 * 设定的人脉类型
	 * @param enumType
	 * @return
	 */
	public HttpReq_AddContacts setType(Enum_ReqRelaType enumType){
		this.enumType=enumType;
		return this;
	}
	/**
	 * 人脉来源
	 * @param enumSource
	 * @return
	 */
	public HttpReq_AddContacts setSource(Enum_ReqRelaSource enumSource){
		this.enumSource=enumSource;
		return this;
	}
	public void httpRequest() {
		
		reqBean.setUid(Dao_Self.getInstance().getUser_id());
		reqBean.setSource(enumSource.toString());
		reqBean.setType(enumType.toString());
		http.send(HttpMethod.POST, API.ADD_CONTACTS,
				FormatUtils.convertBeanToParams(reqBean),
				new RequestCallBack<String>() {
					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						super.onStart();
						accBack.onStart();
					}
					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						accBack.add_Fail();
						P.v("requestError...");
						accBack.onFinally();
					}

					@Override
					public void onSuccess(ResponseInfo<String> params) {
						// TODO Auto-generated method stub
						new HttpResultService(params.result,
								new myHttpResultCallBack() {

									@Override
									public void onSuccess() {
										// TODO Auto-generated method stub
										accBack.add_Success();
										P.v("requestSuccess...");
									}

									@Override
									public void onRequestFail() {
										// TODO Auto-generated method stub
										accBack.add_Fail();
										P.v("requestFail...");
									}

									@Override
									public void onFinally() {
										// TODO Auto-generated method stub
										accBack.onFinally();
									}
								}, null, false);
					}
				});

	}

	public static abstract class ACCallBack {
		public abstract void onStart();
		public abstract void add_Success();
		public abstract void add_Fail();
		public abstract void onFinally();
	}
	public static String parseUid(ArrayList<T_UserPoint> datas){
		String beUid=new String();
		
		for(T_UserPoint item:datas){
			beUid+=item.getUser_id()+",";
		}
		if(beUid.length()>0){
			return beUid.subSequence(0, beUid.length()-1).toString();
		}
		return new String();
	}
	public static String parseUid_Wifi(ArrayList<Recv_wifiUser> datas){
		String beUid=new String();
		for(Recv_wifiUser item:datas){
			beUid+=item.getUser_id()+",";
		}
		if(beUid.length()>0){
			return beUid.subSequence(0, beUid.length()-1).toString();
		}
		return new String();
	}
}
