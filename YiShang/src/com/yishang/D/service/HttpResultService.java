package com.yishang.D.service;

import org.json.JSONException;
import org.json.JSONObject;

import com.http.callBack.myHttpResultCallBack;
import com.yishang.Z.utils.ParseUtils;

/**
 * 检测请求状况的服务
 * 
 * @author MM_Zerui
 * 
 */
public class HttpResultService {
	public HttpResultService(Object httpResult, myHttpResultCallBack callBack,
			Class<?> beanClass, boolean ifArray) {
		JSONObject jsonObject = new JSONObject();
		if (httpResult == null) {
			callBack.onNetError();
			callBack.onFinally();
			return;
		}
		if (httpResult instanceof String) {
			String str = (String) httpResult;
			try {
				jsonObject = new JSONObject(str);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				callBack.onNetError();
				callBack.onFinally();
				return;
			}
		}
		if (httpResult instanceof JSONObject) {
			jsonObject = (JSONObject) httpResult;
		}
		if (jsonObject == null) {
			callBack.onNetError();
		} else if (ParseUtils.parseJsonState(jsonObject)) {
			callBack.onSuccess();
			Object obj = new Object();
			if (!ifArray) {
				if (beanClass == null) {
					obj = ParseUtils.parseResultData(jsonObject);
				} else {
					obj = ParseUtils.parseResultJson(jsonObject, beanClass);
				}

			} else {
				obj = ParseUtils.parseLocalJsonArray(jsonObject, beanClass);
			}
			if (obj == null) {
				callBack.onData(false, null);
			} else {
				callBack.onData(true, obj);
			}
			// return;
		} else {
			callBack.onRequestFail();

		}
		callBack.onFinally();
	}
}
