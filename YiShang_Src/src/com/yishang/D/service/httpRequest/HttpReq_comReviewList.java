package com.yishang.D.service.httpRequest;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yishang.A.global.baseClass.HttpRequestClass;
import com.yishang.A.global.constant.API;
import com.yishang.B.module.d.BusinessEntity.Req_comReviewList;
import com.yishang.Z.utils.FormatUtils;

/**
 * 获取某用户具有的供应商审查权的企业列表信息
 * 
 * @author MM_Zerui
 * 
 */
public class HttpReq_comReviewList extends HttpRequestClass {
	private CallBack_ReviewList callBack;

	public HttpReq_comReviewList(Req_comReviewList reqBean,
			CallBack_ReviewList callBack) {
		// TODO Auto-generated constructor stub
		super();
		this.callBack = callBack;
		http.send(HttpMethod.POST, API.BUSINESS_REVIEW_LIST,
				FormatUtils.convertBeanToParams(reqBean),
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(ResponseInfo<String> params) {
						// TODO Auto-generated method stub
						
					}
				});
	}

	public static abstract class CallBack_ReviewList {

	}
}
