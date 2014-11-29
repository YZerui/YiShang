package com.http.callBack;

public abstract class myHttpResultCallBack {
	public void onNetError(){
		
	}
	public abstract void onRequestFail();
	public abstract void onSuccess();
	public abstract void onFinally();
	
	public void onData(boolean validity,Object obj){
		
	}
}
