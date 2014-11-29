package com.yishang.D.service.httpRequest;

import com.http.callBack.myHttpResultCallBack;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yishang.A.global.constant.API;
import com.yishang.B.module.d.BusinessEntity.Recv_comDetail;
import com.yishang.B.module.d.BusinessEntity.Req_comDetail;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.D.service.HttpResultService;
import com.yishang.Z.utils.FormatUtils;

/**
 * 获取对应企业信息
 * 
 * @author MM_Zerui
 * 
 */
public class HttpReq_GetComIfo {
	private CallBack callBack;
	public HttpReq_GetComIfo(String comId,CallBack call) {
		this.callBack=call;
		HttpUtils http = new HttpUtils();
		Req_comDetail reqBean = new Req_comDetail();
		reqBean.setId(comId);
		reqBean.setUid(Dao_Self.getInstance().getUser_id());
		http.send(HttpMethod.POST, API.BUSINESS_DETAIL,
				FormatUtils.convertBeanToParams(reqBean),
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						callBack.requestFail();
						callBack.onFinally();
					}

					@Override
					public void onSuccess(ResponseInfo<String> params) {
						new HttpResultService(params.result,new myHttpResultCallBack() {
							@Override
							public void onData(boolean validity, Object obj) {
								// TODO Auto-generated method stub
								super.onData(validity, obj);
								if(validity){
									Recv_comDetail recvBean=(Recv_comDetail)obj;
									callBack.requestSuccess(recvBean);
									return;
								}
								callBack.requestFail();
							}
							@Override
							public void onSuccess() {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void onRequestFail() {
								// TODO Auto-generated method stub
								callBack.requestFail();
							}
							
							@Override
							public void onFinally() {
								// TODO Auto-generated method stub
								callBack.onFinally();
							}
						}, Recv_comDetail.class,false);
					}
				});
	}
	public static abstract class CallBack{
		public abstract void onFinally();
		public abstract void requestFail();
		public abstract void requestSuccess(Recv_comDetail bean);
	}
}
