package com.yishang.A.global.baseClass;

import com.lidroid.xutils.HttpUtils;

public abstract class HttpRequestClass {
	protected HttpUtils http;
	public HttpRequestClass() {
		http=new HttpUtils();
	}
}
