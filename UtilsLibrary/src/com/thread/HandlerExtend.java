package com.thread;

import android.os.Handler;
import android.os.Message;

public class HandlerExtend extends Handler{
	final private static int LIST_INIT_VIEW=0; 
	final private static int LIST_REFRESH_VIEW=1; 
	final private static int LIST_FAIL_VIEW=2; 
	final private static int LIST_FINALLY=3; 
	final private static int LIST_LOAD_NULL=5; 
	final private static int DATA=4; 
	private handleCallBack callBack;
	private Object obj;
	public HandlerExtend(handleCallBack callBack) {
		// TODO Auto-generated constructor stub
		this.callBack=callBack;
	}
	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		super.handleMessage(msg);
		switch (msg.what) {
		case LIST_INIT_VIEW:
			callBack.call_onInit();
			break;
		case LIST_REFRESH_VIEW:
			callBack.call_onRefresh();
			break;
		case LIST_FAIL_VIEW:
			callBack.call_onFail();
			break;
		case LIST_FINALLY:
			callBack.call_onFinally();
			break;
		case DATA:
			callBack.call_onData(obj);
			break;
		case LIST_LOAD_NULL:
			callBack.call_onLoadNull();
			break;
		default:
			break;
		}
		callBack.call_onFinally();
	}
	public void onRefreshView(){
		sendEmptyMessage(LIST_REFRESH_VIEW);
	}
	public void onInitView(){
		sendEmptyMessage(LIST_INIT_VIEW);
	}
	public void onFail(){
		sendEmptyMessage(LIST_FAIL_VIEW);
	}
	public void onFinally(){
		sendEmptyMessage(LIST_FINALLY);
	}
	public void onLoadNull(){
		sendEmptyMessage(LIST_LOAD_NULL);
	}
	public void onData(Object obj){
		sendEmptyMessage(DATA);
		this.obj=obj;
	}
	public static abstract class handleCallBack{
		public abstract void call_onInit();
		public abstract void call_onRefresh();
		public void call_onData(Object obj){
			
		}
		public void call_onFail(){
			
		}
		public void call_onFinally(){
			
		}
		public void call_onLoadNull(){
			
		}
	}
}
