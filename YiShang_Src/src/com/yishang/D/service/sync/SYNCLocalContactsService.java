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
 * @author MM_Zerui 通讯录上传更新服务
 * @tip_1 首先判断本地数据库是否存在联系人记录
 * @tip_2 如果不存在，从设备中获取通讯录信息并存入本地数据库中
 * @tip_3 如果存在，直接本地数据库中提取未注册的号码上传到服务器
 * @tip_4 对服务端返回的数据进行分析，服务端返回的数据均为注册过的，通过这些数据对本地数据库进行信息更改操作
 * @tip_5 该服务结束后通知朋友列表页面，进行相应控件的更新操作
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
		// 如果是第一次运行,则进行数据加载
		System.out.println("service 2...");
		if (!Dao_Contacts.checkContactsExist()) {
			// 获取设备中的通讯录号码
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
	 * 提交手机号码到服务端的方法
	 * 
	 * @tip_1 采取分段提交的方式
	 * @tip_2 每次提交后等待服务端返回数据才进行第二次提交操作
	 * @tip_3 当本地数据库的数据获取为空时，停止上传操作
	 * @tip_3 停止上传后，销毁该服务
	 */
	private void upLoadPhones() {
		// TODO Auto-generated method stub

		// 获取首批提交检验的手机 号码
		List<T_Contacts> phones;
		try {
			phones = Dao_Contacts.getUnRegiPhones(index++);

			// 如果本地通信信息获取为空说明已经上传完毕，则销毁该服务结束本次过程
			if (!DataValidate.checkDataValid(phones)) {
				// 将更新的信息保存在人脉表中
				try {
					//更新本地通讯录到人脉表中
					Dao_Relationship.deleteUnRegiPhoneContact();
					Dao_Relationship.addUnRegiPhoneContact(Dao_Contacts
							.getUnRegiPhones(-1));
					Dao_Relationship.addRegiPhoneContact(Dao_Contacts
							.getRegiPhones(-1));
				} catch (DbException e) {
					e.printStackTrace();
				}
				
				
				// 销毁服务
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
																+ "更新数据失败");
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
				//获取通讯录信息
				initContactsData();
				//同步通讯录
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
		// 发送广播通知同步通讯录完成
		Intent intent = new Intent();
		intent.setAction(Enum_ReceiverAction.CONTACTS_PAGE.name());
		sendBroadcast(intent);
		// 关闭该服务
	
		super.onDestroy();
	}

}
