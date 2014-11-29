package com.yishang.D.service.httpRequest;

import java.util.ArrayList;
import java.util.List;

import com.http.callBack.myHttpResultCallBack;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yishang.A.global.Enum.Enum_ListLimit;
import com.yishang.A.global.Enum.http.Enum_ReqMsgSource;
import com.yishang.A.global.application.T_UserPoint;
import com.yishang.A.global.baseClass.HttpRequestClass;
import com.yishang.A.global.constant.API;
import com.yishang.B.module.a.MsgEntity.Req_Msg;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.C.dao.daoModel.T_MsgBuffer;
import com.yishang.D.service.HttpResultService;
import com.yishang.Z.utils.FormatUtils;

/**
 * 获取商机接口
 * 
 * @author MM_Zerui
 * 
 */
public class HttpReq_GetMsg extends HttpRequestClass {
	private Enum_ReqMsgSource enumSource;
	private CallBack callBack;
	private Req_Msg reqMsg;

	public HttpReq_GetMsg() {
		reqMsg = new Req_Msg();
		// 每次获取的项数
		reqMsg.setLimit(Enum_ListLimit.MSG_REQ_LSIT.toString());
		// 默认为自己ID
		reqMsg.setUid(Dao_Self.getInstance().getUser_id());
	}

	public HttpReq_GetMsg setCallBack(CallBack callBack) {
		this.callBack = callBack;

		return this;
	}

	/**
	 * 设定商机的来源
	 * 
	 * @param enumSource
	 * @return
	 */
	public HttpReq_GetMsg setMsgSource(Enum_ReqMsgSource enumSource) {
		this.enumSource = enumSource;
		return this;
	}

	/**
	 * 设定发送方的Id
	 * 
	 * @param senderId
	 * @return
	 */
	public HttpReq_GetMsg setSenderId(String senderId) {
		reqMsg.setSendId(senderId);

		return this;
	}

	/**
	 * 设定时间戳(用户获取该时间后的商机信息)
	 * 
	 * @param time
	 * @return
	 */
	public HttpReq_GetMsg setTime(String time) {
		reqMsg.setTime(time);
		return this;
	}

	/**
	 * 设定消息ID(用户获取该ID之后的所有商机)
	 * 
	 * @param id
	 * @return
	 */
	public HttpReq_GetMsg setMsgID(long id) {
		reqMsg.setMsgID(String.valueOf(id));
		return this;
	}

	public void onInit() {
		callBack.index = 0;

		httpMethod(true);

	}

	public void onLoad() {

		httpMethod(false);
	}

	public void httpMethod(final boolean ifInit) {
		reqMsg.setStart(String.valueOf(callBack.index++));
		reqMsg.setSendSource(enumSource.toString());
		http.send(HttpMethod.POST, API.MSG_LIST,
				FormatUtils.convertBeanToParams(reqMsg),
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						callBack.onFail();
					}

					@Override
					public void onSuccess(ResponseInfo<String> params) {
						// TODO Auto-generated method stub
						new HttpResultService(params.result,
								new myHttpResultCallBack() {
									@Override
									public void onData(boolean validity,
											Object obj) {
										// TODO Auto-generated method stub
										super.onData(validity, obj);
										if (validity) {
											if (ifInit) {
												callBack.onInit((List<T_MsgBuffer>) obj);
												return;
											}
											callBack.onLoad((List<T_MsgBuffer>) obj);
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
								}, T_MsgBuffer.class, true);
					}
				});
	}

	public static abstract class CallBack {
		private int index = 0;

		public abstract void onInit(List<T_MsgBuffer> list);

		public abstract void onLoad(List<T_MsgBuffer> list);

		public abstract void onFail();

		public abstract void onFinally();
	}
}
