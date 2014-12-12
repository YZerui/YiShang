package com.yishang.F.receive;

import com.alibaba.fastjson.JSONObject;
import com.device.FunUtils;
import com.exception.utils.P;
import com.format.utils.DataValidate;
import com.format.utils.FormatUtils;
import com.lidroid.xutils.exception.DbException;
import com.ruifeng.yishang.R;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;
import com.yishang.A.global.Enum.Enum_ReceiverAction;
import com.yishang.A.global.Enum.db.Enum_IfRegister;
import com.yishang.A.global.Enum.db.Enum_RelaNote;
import com.yishang.A.global.Enum.db.Enum_RelaType;
import com.yishang.A.global.Enum.push.Enum_PushModel;
import com.yishang.A.global.Enum.push.Enum_PushSource;
import com.yishang.A.global.Enum.push.Enum_PushType;
import com.yishang.A.global.baseClass.TabBarActivity;
import com.yishang.A.global.writting.W_Msg;
import com.yishang.B.module.c.ResourceEntity.Recv_bookIfo;
import com.yishang.B.module.d.BusinessEntity.Recv_comDetail;
import com.yishang.B.module.f.LoginEntity.Recv_userIfo;
import com.yishang.C.dao.daoImpl.Dao_Msg;
import com.yishang.C.dao.daoImpl.Dao_Relationship;
import com.yishang.C.dao.daoImpl.Dao_Resource;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.C.dao.daoModel.T_Msg;
import com.yishang.C.dao.daoModel.T_Relationships;
import com.yishang.C.dao.utils.Utils_DB;
import com.yishang.C.dao.utils.Utils_DBRNote;
import com.yishang.D.service.AddResService;
import com.yishang.D.service.httpRequest.HttpReq_GetComIfo;
import com.yishang.D.service.httpRequest.HttpReq_GetResIfo;
import com.yishang.D.service.httpRequest.HttpReq_SYNCUserIfo;
import com.yishang.D.service.httpRequest.HttpReq_GetComIfo.CallBack;
import com.yishang.D.service.httpRequest.HttpReq_GetResIfo.CallBack_Res;
import com.yishang.D.service.httpRequest.HttpReq_SYNCUserIfo.CallBack_UserIfo;
import com.yishang.D.service.sync.SYNCMsgService;
import com.yishang.F.receive.model.Recv_push;
import com.yishang.Z.utils.BroadcastUtil;
import com.yishang.Z.utils.DeviceUtils;
import com.yishang.Z.utils.ViewSwitchUtils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * ����AVOSƽ̨���͵Ľ�����
 * 
 * @���� �������͵�json����
 * @���� ��ȡ��ϢID,������ϢID�ӷ���˻�ȡ������Ϣ
 * 
 * @author MM_Zerui
 * @note ���ո�ʽ
 */
public class AVOSCustomReceiver extends BroadcastReceiver {
	private Context context;

	@Override
	public void onReceive(Context context, Intent intent) {
		this.context = context;
		String jsonStr = intent.getExtras()
				.getString("com.avos.avoscloud.Data");
		if (DataValidate.checkDataValid(jsonStr)) {
			P.v("�յ�����:" + jsonStr);
			JSONObject json = JSONObject.parseObject(jsonStr);
			if (json == null) {
				return;
			}
			String msgIfo = json.getString(Enum_PushModel.msg_ifo.name());
			ViewSwitchUtils.startService(context, SYNCMsgService.class);
			String m_s = json.getString(Enum_PushModel.m_s.name());
			try{
			if (Integer.valueOf(m_s) == Enum_RelaNote.NEWFRIEND.value()) {
				// �����ԴΪ�����ѣ�������֪ͨ
				return;
			}}catch (Exception e) {
				// TODO: handle exception
			}
			brocastMethod(msgIfo);

		}
	}

	// public void sendBroadcast() {
	// BroadcastUtil.sendBroadCast(context,
	// Enum_ReceiverAction.MSG_PAGE.name());
	//
	// }
	//
	// public void sendBroadcastContact() {
	// BroadcastUtil.sendBroadCast(context,
	// Enum_ReceiverAction.CONTACTS_PAGE.name());
	// }
	//
	// /**
	// * ϵͳ��Դ
	// *
	// * @param bean
	// */
	// public void sysMethod(Recv_push bean) {
	// T_Msg tBean = new T_Msg();
	// tBean.setMsg_source(Enum_PushSource.SYSTEM.value());
	// tBean.setMsg_time(bean.getSendTime());
	// tBean.setMsg_type(Integer.valueOf(bean.getMsgType()));
	// tBean.setSelf_id(Dao_Self.getInstance().getUser_id());
	// tBean.setMsg_content(bean.getOther());
	// try {
	// Dao_Msg.addPushRecord(tBean);
	// } catch (DbException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// // ����֪ͨ�����̻��б�
	// sendBroadcast();
	// }
	//
	// /**
	// * ��ҵ��Դ
	// *
	// * @note ������ҵID��ȡ��ҵ��Ϣ����T_MsgSeq��
	// * @note ������ϢΪ #��ҵlogo# #��ҵ��#
	// * @param bean
	// */
	// public void comMethod(Recv_push bean) {
	// T_Msg tBean = new T_Msg();
	// tBean.setMsg_source(Enum_PushSource.COMPANY.value());
	// tBean.setMsg_time(bean.getSendTime());
	// tBean.setMsg_type(Integer.valueOf(bean.getMsgType()));
	// Enum_PushType eType = Enum_PushType.DEFAULT
	// .valueOf(tBean.getMsg_type());
	// tBean.setSelf_id(Dao_Self.getInstance().getUser_id());
	// // ����other��ȡ��������
	// JSONObject json = JSONObject.parseObject(bean.getOther());
	// // ��ȡ��ҵID
	// tBean.setMsg_comId(W_Msg.Y(json.getString(Enum_PushModel.c_d.name())));
	//
	// // ...������
	// switch (eType) {
	// case COM_AWARD:// �䷢����
	//
	// break;
	// case COM_BAOBEI: // ����
	// tBean.setMsg_content(W_Msg.Y(json.getString(Enum_PushModel.success
	// .name())));
	// // if
	// // (W_Msg.Y(json.getString(Enum_PushModel.success.name())).equals(
	// // "true")) {
	// // tBean.setMsg_success(1);
	// // tBean.setMsg_content("��Ա���˾�ı����ɹ�");
	// // } else {
	// // tBean.setMsg_content("��Ա���˾�ı���ʧ��");
	// // }
	// break;
	// case COM_CHECK: // �������
	// tBean.setMsg_content(W_Msg.Y(json.getString(Enum_PushModel.success
	// .name())));
	// // if
	// // (W_Msg.Y(json.getString(Enum_PushModel.success.name())).equals(
	// // "true")) {
	// // tBean.setMsg_success(1);
	// // tBean.setMsg_content("��ͱ���˾�Ĺ����ɹ�");
	// // } else {
	// // tBean.setMsg_content("��ͱ���˾�Ĺ���ʧ��");
	// // }
	// break;
	// case COM_INFORM:// ֪ͨ�ı�
	// tBean.setMsg_content(W_Msg.Y(json.getString(Enum_PushModel.msg_ifo
	// .name())));
	// break;
	// default:
	// break;
	// }
	// try {
	// Dao_Msg.addPushRecord(tBean);
	// } catch (DbException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// // ������ҵ��Ϣ
	// new HttpReq_GetComIfo(tBean.getMsg_comId(), new CallBack() {
	//
	// @Override
	// public void requestSuccess(Recv_comDetail bean) {
	// // TODO Auto-generated method stub
	// try {
	// Dao_Msg.updatePushRecord_Com(bean.getCom_id(),
	// bean.getCom_name(), bean.getCom_logo());
	// } catch (DbException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	// @Override
	// public void requestFail() {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void onFinally() {
	// // TODO Auto-generated method stub
	// // ֪ͨ�̼��б�ҳ�����ˢ��
	// sendBroadcast();
	// }
	// });
	// // ����֪ͨ�����̻��б�
	// sendBroadcast();
	// }
	//
	// /**
	// * �û���Դ
	// *
	// * @note �û���Դ��Ҫ��������Ϣ���и��£�#��ϵǿ��# �� #��ϵ#
	// * @note ����������û����ݽ��л�ȡ������ #�û�ͷ��# �� #����# ��Ϣ
	// * @��ע �û��յ��ĵ��󣬸��ĵ���Ӧ����ҵ����Ϊ��Ӧ����ӵ�������
	// * @��ע ����Ϊ�ͻ�����ҵ����ĳ�û�����ԭʼת�����ĵ�����Ȥ�������
	// * @param bean
	// */
	// public void userMethod(Recv_push bean) {
	// T_Msg tBean = new T_Msg();
	// // �����������
	// if (Utils_DBRNote.parse(bean.getMsgSourceStr()) ==
	// Enum_RelaType.NEWFRIEND
	// .value()) {
	// tBean.setMsg_source(Enum_PushSource.NEWFRIEND.value());
	// } else {
	// // ������Ϊ����
	// tBean.setMsg_source(Enum_PushSource.USER.value());
	// }
	//
	// tBean.setMsg_time(bean.getSendTime());
	// tBean.setSelf_id(Dao_Self.getInstance().getUser_id());
	// tBean.setMsg_type(Integer.valueOf(bean.getMsgType()));
	// // ����other��ȡ��������
	// JSONObject json = JSONObject.parseObject(bean.getOther());
	// // ��ȡԭʼת����ID
	// tBean.setMsg_uId(W_Msg.Y(json.getString(Enum_PushModel.s_i.name())));
	// // ֱ��ת����ID
	// tBean.setMsg_sendId(W_Msg.Y(json.getString(Enum_PushModel.u_d.name())));
	// // ��ϢID
	// // tBean.setMsg_id(bean.getMsg_id());
	// Enum_PushType eType = Enum_PushType.DEFAULT
	// .valueOf(tBean.getMsg_type());
	//
	// switch (eType) {
	// case RES_INTEREST: // ���ĵ�����Ȥ
	//
	// final String resId = W_Msg.Y(json.getString(Enum_PushModel.b_d
	// .name()));
	// tBean.setMsg_resId(resId);
	// // ����֪ͨ��
	// brocastMethod("���˶���ת�����ĵ�����Ȥ��");
	// // �����ĵ���Ӧ����ҵ��Ϣ
	// new HttpReq_GetResIfo(resId, new CallBack_Res() {
	//
	// @Override
	// public void onSuccess(Recv_bookIfo resBean) {
	// // TODO Auto-generated method stub
	// // ������Ϣ������ҵid��Ϣ
	// try {
	// Dao_Msg.updataComId(resId, resBean.getCom_id());
	// // �����ĵ�ID��Ӧ���ĵ���
	// Dao_Msg.updataResName(resId, resBean.getBook_name());
	// // ֪ͨҳ�����
	// sendBroadcast();
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }
	//
	// @Override
	// public void onFail() {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void onFinally() {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// });
	// break;
	// case RES_RECEV: // �յ�һ���ĵ�
	// tBean.setMsg_resId(W_Msg.Y(json.getString(Enum_PushModel.b_d.name())));
	// // ���ĵ����ݱ����ڱ�����
	// new AddResService(tBean.getMsg_resId(), tBean.getMsg_sendId(),
	// tBean.getMsg_uId(), bean.getRelateS(), bean.getMsgSource());
	// // ����֪ͨ��
	// brocastMethod("���յ���һ���ĵ�Ŷ");
	// break;
	// default:
	// break;
	// }
	// try {
	// // ����������
	// updateRela(tBean.getMsg_sendId(), bean.getRelateS(),
	// bean.getMsgSource());
	// // ������Ϣ���е���Ϣ��Դ
	// Dao_Msg.updateSenderSource(tBean.getMsg_sendId(),
	// tBean.getMsg_source());
	// // ������Ϣ��¼
	// Dao_Msg.addPushRecord(tBean);
	// } catch (DbException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// // ��ȡ�Է���Ϣ������Ϣ������Ҫ����Ϣ
	// new HttpReq_SYNCUserIfo(tBean.getMsg_sendId(), new CallBack_UserIfo() {
	//
	// @Override
	// public void onSuccess(Recv_userIfo result) {
	// try {
	// Dao_Msg.updatePushRecord_User(result.getUser_id(),
	// result.getUser_name(), result.getUser_head());
	// P.v("���·��ͷ���Ϣ");
	// } catch (DbException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	// @Override
	// public void onFinally() {
	// // TODO Auto-generated method stub
	// // ����֪ͨ�̻��б�ҳ����и���
	// sendBroadcast();
	// // ֪ͨ�����б���и���
	// sendBroadcastContact();
	// }
	//
	// @Override
	// public void onFail() {
	// // TODO Auto-generated method stub
	// P.v("���ͻ�ȡ��Ϣʧ��");
	// }
	// });
	// // ����֪ͨ�����̻��б�
	// sendBroadcast();
	// }
	//
	// public Recv_push parse(String str) {
	// JSONObject json = JSONObject.parseObject(str);
	// if (json == null) {
	// return null;
	// }
	// Recv_push recvBean = new Recv_push();
	// recvBean.setAlert(json.getString(Enum_PushModel.alert.name()));
	// recvBean.setMsgSource(Utils_DBRNote.trueNum(json
	// .getString(Enum_PushModel.m_s.name())));
	// recvBean.setMsgSourceStr(json.getString(Enum_PushModel.m_s.name()));
	// recvBean.setMsgType(W_Msg.Y(json.getString(Enum_PushModel.m_t.name())));
	// recvBean.setOther(W_Msg.Y(json.getString(Enum_PushModel.other.name())));
	// recvBean.setRelateS(json.getIntValue(Enum_PushModel.m_co.name()));// ��ϵǿ��
	// recvBean.setSendTime(W_Msg.Y(json.getString(Enum_PushModel.m_time
	// .name())));
	// recvBean.setSourceID(W_Msg.Y(json.getString(Enum_PushModel.s_i.name())));//
	// ԭʼת����
	// recvBean.setMsg_id(W_Msg.Y(json.getString(Enum_PushModel.m_d.name())));
	// return recvBean;
	// }
	//
	// /**
	// * ����������Ϣ
	// *
	// * @param uid
	// * @param relaStrength
	// * @param relaType
	// */
	// public void updateRela(final String uid, final int relaStrength,
	// final int relaType) {
	// final T_Relationships bean = new T_Relationships();
	// bean.setRela_intension(relaStrength);
	// bean.setRela_type(relaType);
	// bean.setRela_id(uid);
	// // �Ѿ�ע��
	// bean.setRela_register(Enum_IfRegister.REGIST.value());
	//
	// // ����������¸�������Ϣ
	// new HttpReq_SYNCUserIfo(uid, new CallBack_UserIfo() {
	//
	// @Override
	// public void onSuccess(Recv_userIfo result) {
	// // TODO Auto-generated method stub
	// bean.setRela_head(result.getUser_head());// ͷ��
	// bean.setRela_name(result.getUser_name());// ����
	// bean.setRela_rank(result.getUser_title1());// ͷ��
	// bean.setRela_phone(result.getUser_phone());// �ֻ�
	// bean.setRela_recentTime(FormatUtils.getCurrentDateValue());// ��ǰʱ��
	//
	// try {
	// Dao_Relationship.addPushRela(bean);
	// } catch (DbException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	// @Override
	// public void onFinally() {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void onFail() {
	// // TODO Auto-generated method stub
	// try {
	// Dao_Relationship.addPushRela(bean.getRela_id(),
	// bean.getRela_intension(), bean.getRela_type());
	// } catch (DbException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// });
	//
	// }
	//
	private void brocastMethod(String msg) {
		// TODO Auto-generated method stub
		/*
		 * @tip_1 ����Ӧ�ý���ʱ������ʾ֪ͨ����ֻ��
		 * 
		 * 
		 * 
		 * @tip_3 �����ڱ�Ӧ��ʱ���𶯡�����֪ͨ��
		 */
		if (DeviceUtils.getRunningActivity().equals(
				TabBarActivity.class.getName())) {
			System.out.println("Receiver TarBarActivity...");
			// ��
			FunUtils.AcquireWakeLock(context);
		} else {
			FunUtils.setNotification(context, TabBarActivity.class, "����",
					"�����µ��̻���", msg, R.drawable.app_icon);
		}
	};
}
