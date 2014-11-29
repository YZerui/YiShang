package com.yishang.D.service;

import java.util.ArrayList;
import java.util.List;

import com.device.SMS;
import com.exception.utils.P;
import com.format.utils.DataValidate;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;
import com.yishang.A.global.Enum.db.Enum_IfRegister;
import com.yishang.A.global.application.AppContextApplication;
import com.yishang.B.module.c.ResourceEntity.Req_bookSend;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.C.dao.daoModel.T_Relationships;
import com.yishang.D.service.httpRequest.HttpReq_ResSend;
import com.yishang.D.service.httpRequest.HttpReq_ResSend.CallBack_ResSend;

/**
 * ת���ĵ��ķ���
 * 
 * @���� ������������ע���û��ͷ�ע���û�
 * @���� ע���û����ú�̨�ӿ�����Ӧ��ʽ����
 * @���� ��ע���û����ö��ŷ�����
 * @author MM_Zerui
 * 
 */
public class TransUsersService {
	private String recvID = "";
	private List<T_Relationships> listDatas;
	private String bId, sourceId;
	private CallBack callBack;
	private String transUrl;

	public TransUsersService(String bId, String sourceId, String transUrl,
			List<T_Relationships> listDatas, CallBack callBack) {
		this.listDatas = listDatas;
		this.bId = bId;
		this.sourceId = sourceId;
		this.callBack = callBack;
		this.transUrl = transUrl;
		new RunnableService(new runCallBack() {

			@Override
			public void start() {
				// TODO Auto-generated method stub
				run();
			}

			@Override
			public void end() {
				// TODO Auto-generated method stub

			}

		}, true);

	}

	private void run() {
		for (T_Relationships item : listDatas) {
			if (item.isItem_select()
					&& item.getRela_register() == Enum_IfRegister.REGIST
							.value()) {
				addRegiItem(item);
				// listDatas.remove(item);
			}
			if (item.isItem_select()
					&& item.getRela_register() == Enum_IfRegister.UNREGIST
							.value()
					&& DataValidate.checkDataValid(item.getRela_phone())) {
				try {
					SMS.SEND(AppContextApplication.getInstance(),
							item.getRela_phone(), "����һ���ܲ�����ĵ����㿴��:" + transUrl);
				} catch (Exception e) {
					// TODO: handle exception
					P.v("�Զ��巢�ͷ����ĵ�����ʧ��");
				}

			}
		}
		if (DataValidate.checkDataValid(recvID)) {
			Req_bookSend reqBean = new Req_bookSend();
			reqBean.setUid(recvID);
			reqBean.setTran_ini(sourceId);
			reqBean.setTran_send(Dao_Self.getInstance().getUser_id());
			reqBean.setBid(bId);
			new HttpReq_ResSend(reqBean, new CallBack_ResSend() {

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
		}
	}

	/**
	 * ��ȡע���û���ID���ϳ��ַ������ַ�����
	 * 
	 * @param item
	 * @return
	 */
	public String addRegiItem(T_Relationships item) {
		recvID += item.getRela_id() + ",";
		return recvID;
	}

	public static abstract class CallBack {
		public abstract void onSuccess();

		public abstract void onFail();

		public abstract void onEnd();
	}
	// public String add

}
