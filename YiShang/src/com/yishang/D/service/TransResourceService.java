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
 * ת������ĵ���ĳ�û��ķ���
 * @���� ��������ע���û��ͷ�ע���û�
 * @���� ע���û����ú�̨�ӿ�����Ӧ��ʽ����
 * @���� ��ע���û����ö��ŷ�����
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
		//��֤�ĵ�ID��ԭʼת����ID������Ϊ��
		for(T_Resource item:listDatas){
			if(DataValidate.checkDataValid(item.getBook_id())&&
					DataValidate.checkDataValid(item.getBook_creater_id())){
				addResItem(item);  //��ȡ��ԴID
				addSourceIdItem(item); //��ȡ��Դ��ԭʼת����ID
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
	 * ��ȡ��Դ���ĵ�ID
	 * @param item
	 * @return
	 */
	public String addResItem(T_Resource item){
		resID+=item.getBook_id()+",";
		return resID;
	}
	/**
	 * ��ȡ��Դ��ԭʼת����ID
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
