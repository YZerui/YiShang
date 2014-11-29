package com.yishang.D.service.httpRequest;

import com.http.callBack.myHttpResultCallBack;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yishang.A.global.baseClass.HttpRequestClass;
import com.yishang.A.global.constant.API;
import com.yishang.B.module.d.BusinessEntity.Req_comRelate;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.D.service.HttpResultService;
import com.yishang.Z.utils.FormatUtils;

/**
 * 和企业进行关联操作的回调
 * @author MM_Zerui
 *
 */
public class HttpReq_RelateCom extends HttpRequestClass {
	private CallBack_Relate callBack;

	public HttpReq_RelateCom(CallBack_Relate callBack, String cid,
			boolean ifRelate) {
		super();
		this.callBack = callBack;
		Req_comRelate reqBean = new Req_comRelate();
		reqBean.setUid(Dao_Self.getInstance().getUser_id());
		reqBean.setCid(cid);
		if (ifRelate) {
			reqBean.setIsRelation("1");
		} else {
			reqBean.setIsRelation("0");
		}
		http.send(HttpMethod.POST, API.BUSINESS_RELATE,
				FormatUtils.convertBeanToParams(reqBean),
				new RequestCallBack<String>() {
					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						super.onStart();
						HttpReq_RelateCom.this.callBack.onStart();
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						HttpReq_RelateCom.this.callBack.onFail();
					}

					@Override
					public void onSuccess(ResponseInfo<String> params) {
						// TODO Auto-generated method stub
						new HttpResultService(params.result,
								new myHttpResultCallBack() {

									@Override
									public void onSuccess() {
										// TODO Auto-generated method stub
										HttpReq_RelateCom.this.callBack
												.onSuccess();
									}

									@Override
									public void onRequestFail() {
										// TODO Auto-generated method stub
										HttpReq_RelateCom.this.callBack.onFail();
									}

									@Override
									public void onFinally() {
										// TODO Auto-generated method stub

									}
								}, null, false);
					}
				});
	}

	public static abstract class CallBack_Relate {
		public abstract void onStart();

		public abstract void onSuccess();

		public abstract void onFail();
		
	}
}
