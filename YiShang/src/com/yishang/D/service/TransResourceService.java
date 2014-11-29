package com.yishang.D.service;

import java.util.ArrayList;
import java.util.List;

import com.format.utils.DataValidate;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;
import com.yishang.A.global.Enum.db.Enum_IfRegister;
import com.yishang.B.module.c.ResourceEntity.Req_bookSend;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.C.dao.daoModel.T_Relationships;
import com.yishang.C.dao.daoModel.T_Resource;
import com.yishang.D.service.httpRequest.HttpReq_ResSend;
import com.yishang.D.service.httpRequest.HttpReq_ResSend.CallBack_ResSend;

/**
 * 转发多个文档给某用户的服务
 * @流程 首先区分注册用户和非注册用户
 * @流程 注册用户调用后台接口以相应格式发送
 * @流程 非注册用户调用短信服务发送
 * @author MM_Zerui
 *
 */
public class TransResourceService {
	private String resID="";
	private String sourceID="";
	private List<T_Resource> listDatas;
	private String uId;
	private CallBack callBack;
	public TransResourceService(String uId,List<T_Resource> listDatas,CallBack callBack) {
		this.listDatas=listDatas;
		this.uId=uId;
		this.callBack=callBack;
		new RunnableService(new runCallBack(){

			@Override
			public void start() {
				// TODO Auto-generated method stub
				run();
			}

			@Override
			public void end() {
				// TODO Auto-generated method stub
				
			}
			
		},true);
		
	
	}
	private void run(){
		//保证文档ID和原始转发人ID均不能为空
		for(T_Resource item:listDatas){
			if(DataValidate.checkDataValid(item.getBook_id())&&
					DataValidate.checkDataValid(item.getBook_creater_id())){
				addResItem(item);  //获取资源ID
				addSourceIdItem(item); //获取资源的原始转发人ID
			}
		}
		if(DataValidate.checkDataValid(resID)){
			Req_bookSend reqBean=new Req_bookSend();
			reqBean.setUid(uId);
			reqBean.setTran_ini(sourceID.subSequence(0, sourceID.length()-1).toString());
			reqBean.setTran_send(Dao_Self.getInstance().getUser_id());
			reqBean.setBid(resID.subSequence(0, resID.length()-1).toString());
			new HttpReq_ResSend(reqBean,new CallBack_ResSend() {
				
				@Override
				public void onSuccess() {
					// TODO Auto-generated method stub
					callBack.onSuccess();
				}
				
				@Override
				public void onStart() {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onFinally() {
					// TODO Auto-generated method stub
					callBack.onEnd();
				}
				
				@Override
				public void onFail() {
					// TODO Auto-generated method stub
					callBack.onFail();
				}
			});
			return;
		}
		callBack.onFail();
	}
	/**
	 * 提取资源的文档ID
	 * @param item
	 * @return
	 */
	public String addResItem(T_Resource item){
		resID+=item.getBook_id()+",";
		return resID;
	}
	/**
	 * 提取资源的原始转发人ID
	 * @param item
	 * @return
	 */
	public String addSourceIdItem(T_Resource item){
		sourceID+=item.getBook_creater_id()+",";
		return sourceID;
	}
	public static abstract class CallBack{
		public abstract void onSuccess();
		public abstract void onFail();
		public abstract void onEnd();
	}
//	public String add
	
	
}
