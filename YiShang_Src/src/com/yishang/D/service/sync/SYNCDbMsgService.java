package com.yishang.D.service.sync;

import java.util.List;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import com.exception.utils.P;
import com.format.utils.DataValidate;
import com.lidroid.xutils.exception.DbException;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;
import com.yishang.A.global.Enum.Enum_ReceiverAction;
import com.yishang.A.global.Enum.com.Enum_ComRela;
import com.yishang.A.global.Enum.com.Enum_ComState;
import com.yishang.A.global.Enum.com.Enum_ComType;
import com.yishang.A.global.Enum.db.Enum_RelaType;
import com.yishang.A.global.Enum.push.Enum_PushSource;
import com.yishang.A.global.Enum.push.Enum_PushType;
import com.yishang.A.global.baseClass.SuperDaoImpl;
import com.yishang.A.global.writting.W_Msg;
import com.yishang.B.module.c.ResourceEntity.Recv_bookIfo;
import com.yishang.B.module.d.BusinessEntity.Recv_comDetail;
import com.yishang.C.dao.daoImpl.Dao_Company;
import com.yishang.C.dao.daoImpl.Dao_Msg;
import com.yishang.C.dao.daoImpl.Dao_MsgBuffer;
import com.yishang.C.dao.daoImpl.Dao_Relationship;
import com.yishang.C.dao.daoImpl.Dao_Resource;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.C.dao.daoModel.T_Company;
import com.yishang.C.dao.daoModel.T_Msg;
import com.yishang.C.dao.daoModel.T_MsgBuffer;
import com.yishang.C.dao.daoModel.T_Resource;
import com.yishang.C.dao.utils.Utils_DBRNote;
import com.yishang.D.service.httpRequest.HttpReq_GetComIfo;
import com.yishang.D.service.httpRequest.HttpReq_GetComIfo.CallBack;
import com.yishang.D.service.httpRequest.HttpReq_GetResIfo;
import com.yishang.D.service.httpRequest.HttpReq_GetResIfo.CallBack_Res;
import com.yishang.Z.utils.BroadcastUtil;
import com.yishang.Z.utils.FormatUtils;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * ͬ���̻����ݿ����Ϣ����
 * 
 * @���� ͬ����Ϻ󽫸����̻���
 * @���� 1.�Ȼ�ȡT_MsgBuffer���е�����,����ÿ�����ݽ���ѭ����ȡ 2.��ѭ����ȡ����������з���
 *     3.�Բ�ͬ����Ϣ���ͽ��в�ͬ�Ķ���������ͬ���������ݱ�
 * 
 * @author MM_Zerui
 * 
 */
public class SYNCDbMsgService extends Service {
	private Context context;
	private int num = 0;

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		context = getApplicationContext();
		new RunnableService(new runCallBack() {
			
			@Override
			public void start() {
				// TODO Auto-generated method stub
				List<T_MsgBuffer> bufferList = Dao_MsgBuffer.getAll();
				if (!DataValidate.checkDataValid(bufferList)) {
					P.v("֪ͨ��Ϣҳ��ˢ��");
					BroadcastUtil.sendBroadCast(context,
							Enum_ReceiverAction.MSG_PAGE_NULL.name());
//					onDestroy();
					stopSelf();
					return;
				}
				num = bufferList.size();
				P.v("׼��ͬ��������:"+num);
				for (T_MsgBuffer item : bufferList) {
					num--;
					P.v("ʣ�������:"+num);
					int type = item.getMsg_send();
					switch (type) {
					case 1:// ����ϵͳ
						handleSystem(item);
						break;
					case 2:// ������ҵ
						handleCom(item);
						break;
					case 3:// �����û�
						handleUser(item);
						break;
					default:
						break;
					}
				}
			}
			
			@Override
			public void end() {
				// TODO Auto-generated method stub
				
			}
		}, true);
		
	}

	/**
	 * ����ϵͳ֪ͨ��ͬ��
	 * 
	 * @param item
	 */
	private void handleSystem(T_MsgBuffer item) {
		T_Msg msgBean=new T_Msg();
		msgBean.setMsg_source(Enum_PushSource.SYSTEM.value());
		msgBean.setMsg_type(item.getMsg_type());
		msgBean.setMsg_content(item.getMsg_content());
		msgBean.setMsg_sendName("����С����");
		msgBean.setMsg_time(FormatUtils.getCurrentDateValue());
		msgBean.setSelf_id(Dao_Self.getInstance().getUser_id());
		try {
			Dao_Msg.addPushRecord(msgBean);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ������ҵ֪ͨ��ͬ��
	 * 
	 * @param item
	 */
	private void handleCom(T_MsgBuffer item) {
		T_Msg msgBean = new T_Msg();
		msgBean.setMsg_type(item.getMsg_type());
		msgBean.setMsg_comId(item.getMsg_com());
		msgBean.setMsg_comName(item.getName());
		msgBean.setMsg_content(item.getMsg_content());
		msgBean.setMsg_comLogo(item.getHead());
		msgBean.setMsg_time(FormatUtils.getDateValue_Default(item.getMsg_time()));
		msgBean.setMsg_id(item.getMsg_id());
		msgBean.setMsg_source(Enum_PushSource.COMPANY.value());
		//����ɹ���������Ϣ
		if(msgBean.getMsg_content().equals("true")){
			msgBean.setMsg_success(1);
		}
		try {
			Dao_Msg.addPushRecord(msgBean);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (num == 0) {
			onDestroy();
		}
	}

	/**
	 * �����û�֪ͨ��ͬ��
	 * 
	 * @param item
	 */
	private void handleUser(T_MsgBuffer item) {
		T_Msg msgBean = new T_Msg();
		String urType = item.getUr_type();// ��ϵ����
		int type = Utils_DBRNote.handle(Integer.valueOf(urType));
		if (type == Enum_RelaType.NEWFRIEND.value()) {
			// �����ԴΪ������
			msgBean.setMsg_source(Enum_PushSource.NEWFRIEND.value());
		} else {
			// �����ԴΪ��ϵ��
			msgBean.setMsg_source(Enum_PushSource.USER.value());
		}
		try{
		msgBean.setMsg_sendHead(item.getHead());
		msgBean.setMsg_sendName(item.getName());
		msgBean.setMsg_sendId(item.getMsg_user());
		msgBean.setMsg_resId(item.getBook_id());
		msgBean.setMsg_resName(item.getMsg_content());
		msgBean.setMsg_uId(item.getMsg_ini());
		msgBean.setMsg_type(item.getMsg_type());
		msgBean.setMsg_content(item.getMsg_content());
		msgBean.setMsg_recvTime(FormatUtils.getCurrentDateValue());
		msgBean.setMsg_time(FormatUtils.getDateValue_Default(item.getMsg_time()));
		msgBean.setMsg_id(item.getMsg_id());
		}catch (Exception e) {
			// TODO: handle exception
		}
		try {
			Dao_Msg.addPushRecord(msgBean);
			if (num == 0) {
				// ֪ͨ��Ϣҳ����и���
				BroadcastUtil.sendBroadCast(context,
						Enum_ReceiverAction.MSG_PAGE.name());
			}

		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		Enum_PushType typeEnum = Enum_PushType.valueOf(msgBean.getMsg_type());
		// ͬ��������Ϣ
		syncRelationship(item.getMsg_user(), item.getUr_type(), Integer.valueOf(item.getUr_co()));
		switch (typeEnum) {
		// ����Ȥ֪ͨ
		case RES_INTEREST:
			// ͬ����Դ��Ϣ
			syncResInterest(item.getMsg_content(), item.getBook_id());
			// onDestroy();
			break;
		// �յ��ĵ�
		case RES_RECEV:

			// ͬ����Դ��Ϣ
			syncResource(item.getMsg_ini(), msgBean.getMsg_sendId(),
					msgBean.getMsg_sendName(), item.getUr_type(),
					Integer.valueOf(item.getUr_co()), item.getMsg_content(), item.getBook_id());

			break;

		default:
			break;
		}
	}

	/**
	 * �Ը���Ȥ����Ϣ���б���ͬ��
	 * 
	 * @param ur_type
	 * @param ur_co
	 * @param msg_content
	 * @param book_id
	 */
	private void syncResInterest(String resName, String resId) {
		// TODO Auto-generated method stub
		final T_Resource tBean = new T_Resource();
		tBean.setBook_name(resName);
		tBean.setBook_id(resId);
		new HttpReq_GetResIfo(resId, new CallBack_Res(){

			@Override
			public void onSuccess(Recv_bookIfo bean) {
				// TODO Auto-generated method stub
				tBean.setCom_id(bean.getCom_id());
				tBean.setCom_name(bean.getCom_abb());
				tBean.setBook_url(bean.getBook_url());

				Dao_Resource.addResRecord_ini(tBean);
				if (num == 0) {
					// ���͸�����Դ�б��֪ͨ
					BroadcastUtil.sendBroadCast(context,
							Enum_ReceiverAction.RESOURCE_PAGE.name());
				}
			}

			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
				if (num == 0) {
					stopSelf();
				}
			}
			
		});
	}

	/**
	 * ͬ��������ϵ
	 * 
	 * @param uid
	 *            ���ͷ�ID
	 * @param ur_type
	 *            ��������
	 * @param ur_co
	 *            ��ϵǿ��
	 */
	private void syncRelationship(String uId, String ur_type, int ur_co) {

		try {
			Dao_Relationship
					.addRelaRecord(uId, Integer.valueOf(ur_type), ur_co);
			if (num == 0) {
				// ֪ͨ����ҳ����Ϣ���и���
				BroadcastUtil.sendBroadCast(context,
						Enum_ReceiverAction.CONTACTS_PAGE.name());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			P.v("ͬ���̻��е�������ϵʧ��");
		}
	}

	/**
	 * ͬ���յ�����Դ��Ϣ
	 * 
	 * @param sendId
	 *            ������ID
	 * @param sendName
	 *            ����������
	 * @param resName
	 *            ��Դ����
	 * @param resId
	 *            ��ԴID
	 */
	private void syncResource(final String iniId, final String sendId,
			final String sendName, final String ur_type, final int ur_co,
			final String resName, final String resId) {
		final T_Resource tBean = new T_Resource();
		try {
			tBean.setSend_id(sendId);
			tBean.setSender_name(sendName);
			tBean.setSender_type(Integer.valueOf(ur_type));
			tBean.setSender_freq(ur_co);
			tBean.setBook_name(resName);
			tBean.setBook_id(resId);
			tBean.setBook_creater_id(iniId);
		} catch (Exception e) {
			// TODO: handle exception
			P.v("ͬ�������ĵ���Դ����:" + e.getMessage());
			return;
		}
		new HttpReq_GetResIfo(resId, new CallBack_Res() {

			@Override
			public void onSuccess(Recv_bookIfo bean) {
				// TODO Auto-generated method stub
				tBean.setCom_id(bean.getCom_id());
				tBean.setCom_name(bean.getCom_abb());
				tBean.setBook_url(bean.getBook_url());

				Dao_Resource.addResRecord(tBean);
				if (num == 0) {
					// ���͸�����Դ�б��֪ͨ
					BroadcastUtil.sendBroadCast(context,
							Enum_ReceiverAction.RESOURCE_PAGE.name());
				}
			}

			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
				if (tBean.getCom_id() == null) {
					return;
				}
				new HttpReq_GetComIfo(tBean.getCom_id(), new CallBack() {

					@Override
					public void requestSuccess(Recv_comDetail bean) {
					
						// ͬ����ҵ��Ϣ
						T_Company tBean = new T_Company();
						tBean.setCom_abb(bean.getCom_abb());
						tBean.setCom_icon(bean.getCom_logo());
						tBean.setCom_id(bean.getCom_id());
						tBean.setCom_name(bean.getCom_name());

						// ��Ӧ�̹�ϵ
						tBean.setCom_relate(Enum_ComType.COM_SUPPLIER.value());
						tBean.setCom_relateTime(bean.getUc_time());
						tBean.setCom_review(Integer.valueOf(bean.getUc_in()));
						tBean.setCom_state(Integer.valueOf(bean.getUc_status()));
						// ��ҵ״̬
						tBean.setCom_status(bean.getCom_status());
						try {
							Dao_Company.addComRecord_Total(tBean);
							// ������ҵ������ϵ
							Dao_Resource.updateCom_relate(resId,
									Integer.valueOf(bean.getUc_status()));
							if (num == 0) {
								// ���͸�����ҵ��Ϣ��֪ͨ
								BroadcastUtil.sendBroadCast(context,
										Enum_ReceiverAction.COMPANY.name());
							}
						} catch (DbException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					@Override
					public void requestFail() {
						// TODO Auto-generated method stub

					}

					@Override
					public void onFinally() {
						// TODO Auto-generated method stub
						if (num == 0) {
							stopSelf();
						}
					}
				});
			}

			@Override
			public void onFail() {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// ����֪ͨ�����̻���
		// BroadcastUtil.sendBroadCast(getApplicationContext(),
		// Enum_ReceiverAction.MSG_PAGE.name());
//		stopSelf();
	}

}
