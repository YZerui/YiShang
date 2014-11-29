package com.thread;

import android.os.Handler;
import android.os.Message;

import com.thread.HandlerExtend.handleCallBack;

public class HandleView extends Handler{
	final private static int DATA=0; 
//	final private static int LIST_REFRESH_VIEW=1; 
	private Object obj;
	private viewCallBack callBack;
	public HandleView(viewCallBack callBack) {
		this.callBack=callBack;
	}
	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		super.handleMessage(msg);
		switch (msg.what) {
		case DATA:
			callBack.call_onData(obj);
			break;
		default:
			break;
		}
	}
	public void onData(Object obj){
		sendEmptyMessage(DATA);
		this.obj=obj;
	}
	
	public static abstract class viewCallBack{
		public abstract void call_onData(Object obj);
//		public abstract void call_onFail();
//		public abstract void call_onFail();
//		public abstract void call_onRefresh();
	}
}
