package com.thread;

import com.thread.callBack.runCallBack;
import com.thread.global.ThreadPool;

public class RunnableService {
	private Runnable able=new Runnable() {
		
		@Override
		public void run() {
			callBack.start();
			callBack.next();
			callBack.end();
		}
	};
	private runCallBack callBack;
	public RunnableService(runCallBack callBack,boolean threadMode){
//		this.able=able;
		this.callBack=callBack;
		if(threadMode){
//			new Thread(this.able).start();	
			ThreadPool.getInstance().getExecutorService().execute(able);
			return;
		}
		this.able.run();
		
	}
}
