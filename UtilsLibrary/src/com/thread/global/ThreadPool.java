package com.thread.global;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 全局的线程池
 * @author MM_Zerui
 *
 */
public class ThreadPool {
	 private static ExecutorService executorService;
	 private static ThreadPool instance;
	 static{
		 executorService=Executors.newCachedThreadPool();
	 }
	 public static ExecutorService getThread(){
		 return getInstance().getExecutorService();
	 }
	 public static ThreadPool getInstance(){
		 if(instance==null){
			 instance=new ThreadPool();
		 }
		 return instance;
	 }
	public static ExecutorService getExecutorService() {
		return executorService;
	}
	
	 
}
