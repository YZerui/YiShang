package com.yishang.D.service.sync;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.exception.utils.P;
import com.format.utils.DataValidate;
import com.http.callBack.myHttpResultCallBack;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;
import com.yishang.A.global.Enum.Enum_ReceiverAction;
import com.yishang.A.global.constant.API;
import com.yishang.B.module.b.ContactsEntity.Recv_phoneCheck;
import com.yishang.B.module.b.ContactsEntity.Req_phoneCheck;
import com.yishang.B.module.b.ContactsUI.ContactsActivity;
import com.yishang.C.dao.daoImpl.Dao_Contacts;
import com.yishang.C.dao.daoImpl.Dao_Relationship;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.C.dao.daoModel.T_Contacts;
import com.yishang.D.service.HttpResultService;
import com.yishang.Z.utils.ConstactUtil;
import com.yishang.Z.utils.FormatUtils;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

/**
 * @author MM_Zerui ͨѶ¼�ϴ����·���
 * @tip_1 �����жϱ������ݿ��Ƿ������ϵ�˼�¼
 * @tip_2 ��������ڣ����豸�л�ȡͨѶ¼��Ϣ�����뱾�����ݿ���
 * @tip_3 ������ڣ�ֱ�ӱ������ݿ�����ȡδע��ĺ����ϴ���������
 * @tip_4 �Է���˷��ص����ݽ��з���������˷��ص����ݾ�Ϊע����ģ�ͨ����Щ���ݶԱ������ݿ������Ϣ���Ĳ���
 * @tip_5 �÷��������֪ͨ�����б�ҳ�棬������Ӧ�ؼ��ĸ��²���
 */
public class SYNCLocalContactsService extends Service {
	private int index = 0;
	private HttpUtils http;

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		http = new HttpUtils();
		super.onCreate();
	}

	private void initResource() {
	}

	private void initContactsData() {
		// TODO Auto-generated method stub
		// ����ǵ�һ������,��������ݼ���
		System.out.println("service 2...");
		if (!Dao_Contacts.checkContactsExist()) {
			// ��ȡ�豸�е�ͨѶ¼����
			ArrayList<T_Contacts> list = ConstactUtil.getSortContactData();
			// daoImpl.insetContacts(list);
			try {
				Dao_Contacts.insertContactsRecord(list);
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("service 3...");
			return;
		}
//		onDestroy();
	}

	/**
	 * �ύ�ֻ����뵽����˵ķ���
	 * 
	 * @tip_1 ��ȡ�ֶ��ύ�ķ�ʽ
	 * @tip_2 ÿ���ύ��ȴ�����˷������ݲŽ��еڶ����ύ����
	 * @tip_3 ���������ݿ�����ݻ�ȡΪ��ʱ��ֹͣ�ϴ�����
	 * @tip_3 ֹͣ�ϴ������ٸ÷���
	 */
	private void upLoadPhones() {
		// TODO Auto-generated method stub

		// ��ȡ�����ύ������ֻ� ����
		List<T_Contacts> phones;
		try {
			phones = Dao_Contacts.getUnRegiPhones(index++);

			// �������ͨ����Ϣ��ȡΪ��˵���Ѿ��ϴ���ϣ������ٸ÷���������ι���
			if (!DataValidate.checkDataValid(phones)) {
				// �����µ���Ϣ��������������
				try {
					//���±���ͨѶ¼����������
					Dao_Relationship.deleteUnRegiPhoneContact();
					Dao_Relationship.addUnRegiPhoneContact(Dao_Contacts
							.getUnRegiPhones(-1));
					Dao_Relationship.addRegiPhoneContact(Dao_Contacts
							.getRegiPhones(-1));
				} catch (DbException e) {
					e.printStackTrace();
				}
				
				
				// ���ٷ���
//				onDestroy();
				stopSelf();

				return;
			}
			String upLoadPhones = FormatUtils.changeArrayPhoneType(phones);
			Req_phoneCheck reqBean = new Req_phoneCheck();
			reqBean.setUid(Dao_Self.getInstance().getUser_id());
			reqBean.setPhones(upLoadPhones);
			http.send(HttpMethod.POST, API.PHONE_REGIST_CHECK,
					FormatUtils.convertBeanToParams(reqBean),
					new RequestCallBack<String>() {

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							// TODO Auto-generated method stub
							upLoadPhones();
						}

						@Override
						public void onSuccess(ResponseInfo<String> arg0) {
							// TODO Auto-generated method stub
							new HttpResultService(arg0.result,
									new myHttpResultCallBack() {
										@Override
										public void onData(boolean validity,
												Object obj) {
											// TODO Auto-generated method stub
											super.onData(validity, obj);
											if (validity) {
												ArrayList<Recv_phoneCheck> list = (ArrayList<Recv_phoneCheck>) obj;
												for (Recv_phoneCheck item : list) {
													try {
														Dao_Contacts
																.updatePhoneIfo(item);
													} catch (DbException e) {
														// TODO Auto-generated
														// catch block
														e.printStackTrace();
														P.v(getClass()
																.getName()
																+ "��������ʧ��");
													}
												}
											}
										}

										@Override
										public void onSuccess() {
											// TODO Auto-generated method stub

										}

										@Override
										public void onRequestFail() {
											// TODO Auto-generated method stub

										}

										@Override
										public void onFinally() {
											// TODO Auto-generated method stub
											upLoadPhones();
										}
									}, Recv_phoneCheck.class, true);
						}
					});
		} catch (DbException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
//			onDestroy();
			stopSelf();
		}
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		new RunnableService(new runCallBack(){

			@Override
			public void start() {
				// TODO Auto-generated method stub
				initResource();
				//��ȡͨѶ¼��Ϣ
				initContactsData();
				//ͬ��ͨѶ¼
				upLoadPhones();
			}

			@Override
			public void end() {
				// TODO Auto-generated method stub
				
			}
			
		}, true);
	
		
		super.onStart(intent, startId);
	}

	@Override
	public void onDestroy() {
		// ���͹㲥֪ͨͬ��ͨѶ¼���
		Intent intent = new Intent();
		intent.setAction(Enum_ReceiverAction.CONTACTS_PAGE.name());
		sendBroadcast(intent);
		// �رո÷���
	
		super.onDestroy();
	}

}
