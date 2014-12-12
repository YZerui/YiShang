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
 * 接收AVOS平台推送的接收器
 * 
 * @步骤 解析推送的json数据
 * @步骤 获取消息ID,根据消息ID从服务端获取具体消息
 * 
 * @author MM_Zerui
 * @note 参照格式
 */
public class AVOSCustomReceiver extends BroadcastReceiver {
	private Context context;

	@Override
	public void onReceive(Context context, Intent intent) {
		this.context = context;
		String jsonStr = intent.getExtras()
				.getString("com.avos.avoscloud.Data");
		if (DataValidate.checkDataValid(jsonStr)) {
			P.v("收到推送:" + jsonStr);
			JSONObject json = JSONObject.parseObject(jsonStr);
			if (json == null) {
				return;
			}
			String msgIfo = json.getString(Enum_PushModel.msg_ifo.name());
			ViewSwitchUtils.startService(context, SYNCMsgService.class);
			String m_s = json.getString(Enum_PushModel.m_s.name());
			try{
			if (Integer.valueOf(m_s) == Enum_RelaNote.NEWFRIEND.value()) {
				// 如果来源为新朋友，不进行通知
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
	// * 系统来源
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
	// // 发起通知更新商机列表
	// sendBroadcast();
	// }
	//
	// /**
	// * 企业来源
	// *
	// * @note 根据企业ID获取企业信息更新T_MsgSeq表
	// * @note 更新信息为 #企业logo# #企业名#
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
	// // 解析other获取更多内容
	// JSONObject json = JSONObject.parseObject(bean.getOther());
	// // 获取企业ID
	// tBean.setMsg_comId(W_Msg.Y(json.getString(Enum_PushModel.c_d.name())));
	//
	// // ...待补充
	// switch (eType) {
	// case COM_AWARD:// 颁发积分
	//
	// break;
	// case COM_BAOBEI: // 报备
	// tBean.setMsg_content(W_Msg.Y(json.getString(Enum_PushModel.success
	// .name())));
	// // if
	// // (W_Msg.Y(json.getString(Enum_PushModel.success.name())).equals(
	// // "true")) {
	// // tBean.setMsg_success(1);
	// // tBean.setMsg_content("你对本公司的报备成功");
	// // } else {
	// // tBean.setMsg_content("你对本公司的报备失败");
	// // }
	// break;
	// case COM_CHECK: // 关联审核
	// tBean.setMsg_content(W_Msg.Y(json.getString(Enum_PushModel.success
	// .name())));
	// // if
	// // (W_Msg.Y(json.getString(Enum_PushModel.success.name())).equals(
	// // "true")) {
	// // tBean.setMsg_success(1);
	// // tBean.setMsg_content("你和本公司的关联成功");
	// // } else {
	// // tBean.setMsg_content("你和本公司的关联失败");
	// // }
	// break;
	// case COM_INFORM:// 通知文本
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
	// // 更新企业信息
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
	// // 通知商家列表页面进行刷新
	// sendBroadcast();
	// }
	// });
	// // 发起通知更新商机列表
	// sendBroadcast();
	// }
	//
	// /**
	// * 用户来源
	// *
	// * @note 用户来源需要对人脉信息进行更新：#关系强度# 和 #关系#
	// * @note 请求网络对用户数据进行获取，更新 #用户头像# 和 #姓名# 信息
	// * @备注 用户收到文档后，该文档对应的企业将作为供应商添加到本地中
	// * @备注 类型为客户的企业是由某用户对你原始转发的文档感兴趣后产生的
	// * @param bean
	// */
	// public void userMethod(Recv_push bean) {
	// T_Msg tBean = new T_Msg();
	// // 如果是新朋友
	// if (Utils_DBRNote.parse(bean.getMsgSourceStr()) ==
	// Enum_RelaType.NEWFRIEND
	// .value()) {
	// tBean.setMsg_source(Enum_PushSource.NEWFRIEND.value());
	// } else {
	// // 否则视为好友
	// tBean.setMsg_source(Enum_PushSource.USER.value());
	// }
	//
	// tBean.setMsg_time(bean.getSendTime());
	// tBean.setSelf_id(Dao_Self.getInstance().getUser_id());
	// tBean.setMsg_type(Integer.valueOf(bean.getMsgType()));
	// // 解析other获取更多内容
	// JSONObject json = JSONObject.parseObject(bean.getOther());
	// // 获取原始转发人ID
	// tBean.setMsg_uId(W_Msg.Y(json.getString(Enum_PushModel.s_i.name())));
	// // 直接转发人ID
	// tBean.setMsg_sendId(W_Msg.Y(json.getString(Enum_PushModel.u_d.name())));
	// // 消息ID
	// // tBean.setMsg_id(bean.getMsg_id());
	// Enum_PushType eType = Enum_PushType.DEFAULT
	// .valueOf(tBean.getMsg_type());
	//
	// switch (eType) {
	// case RES_INTEREST: // 对文档感兴趣
	//
	// final String resId = W_Msg.Y(json.getString(Enum_PushModel.b_d
	// .name()));
	// tBean.setMsg_resId(resId);
	// // 发送通知栏
	// brocastMethod("有人对你转发的文档感兴趣啦");
	// // 更新文档对应的企业信息
	// new HttpReq_GetResIfo(resId, new CallBack_Res() {
	//
	// @Override
	// public void onSuccess(Recv_bookIfo resBean) {
	// // TODO Auto-generated method stub
	// // 更新消息表中企业id信息
	// try {
	// Dao_Msg.updataComId(resId, resBean.getCom_id());
	// // 更新文档ID对应的文档名
	// Dao_Msg.updataResName(resId, resBean.getBook_name());
	// // 通知页面更新
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
	// case RES_RECEV: // 收到一个文档
	// tBean.setMsg_resId(W_Msg.Y(json.getString(Enum_PushModel.b_d.name())));
	// // 对文档数据保存在本地中
	// new AddResService(tBean.getMsg_resId(), tBean.getMsg_sendId(),
	// tBean.getMsg_uId(), bean.getRelateS(), bean.getMsgSource());
	// // 发送通知栏
	// brocastMethod("你收到了一个文档哦");
	// break;
	// default:
	// break;
	// }
	// try {
	// // 更新人脉库
	// updateRela(tBean.getMsg_sendId(), bean.getRelateS(),
	// bean.getMsgSource());
	// // 更新消息表中的消息来源
	// Dao_Msg.updateSenderSource(tBean.getMsg_sendId(),
	// tBean.getMsg_source());
	// // 增加消息记录
	// Dao_Msg.addPushRecord(tBean);
	// } catch (DbException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// // 获取对方信息更新消息表中需要的信息
	// new HttpReq_SYNCUserIfo(tBean.getMsg_sendId(), new CallBack_UserIfo() {
	//
	// @Override
	// public void onSuccess(Recv_userIfo result) {
	// try {
	// Dao_Msg.updatePushRecord_User(result.getUser_id(),
	// result.getUser_name(), result.getUser_head());
	// P.v("更新发送方信息");
	// } catch (DbException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	// @Override
	// public void onFinally() {
	// // TODO Auto-generated method stub
	// // 这里通知商机列表页面进行更新
	// sendBroadcast();
	// // 通知人脉列表进行更新
	// sendBroadcastContact();
	// }
	//
	// @Override
	// public void onFail() {
	// // TODO Auto-generated method stub
	// P.v("推送获取信息失败");
	// }
	// });
	// // 发起通知更新商机列表
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
	// recvBean.setRelateS(json.getIntValue(Enum_PushModel.m_co.name()));// 关系强度
	// recvBean.setSendTime(W_Msg.Y(json.getString(Enum_PushModel.m_time
	// .name())));
	// recvBean.setSourceID(W_Msg.Y(json.getString(Enum_PushModel.s_i.name())));//
	// 原始转发人
	// recvBean.setMsg_id(W_Msg.Y(json.getString(Enum_PushModel.m_d.name())));
	// return recvBean;
	// }
	//
	// /**
	// * 更新人脉信息
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
	// // 已经注册
	// bean.setRela_register(Enum_IfRegister.REGIST.value());
	//
	// // 请求网络更新该人脉信息
	// new HttpReq_SYNCUserIfo(uid, new CallBack_UserIfo() {
	//
	// @Override
	// public void onSuccess(Recv_userIfo result) {
	// // TODO Auto-generated method stub
	// bean.setRela_head(result.getUser_head());// 头像
	// bean.setRela_name(result.getUser_name());// 姓名
	// bean.setRela_rank(result.getUser_title1());// 头衔
	// bean.setRela_phone(result.getUser_phone());// 手机
	// bean.setRela_recentTime(FormatUtils.getCurrentDateValue());// 当前时间
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
		 * @tip_1 当在应用界面时，不显示通知栏，只震动
		 * 
		 * 
		 * 
		 * @tip_3 当不在本应用时，震动、弹出通知栏
		 */
		if (DeviceUtils.getRunningActivity().equals(
				TabBarActivity.class.getName())) {
			System.out.println("Receiver TarBarActivity...");
			// 震动
			FunUtils.AcquireWakeLock(context);
		} else {
			FunUtils.setNotification(context, TabBarActivity.class, "易商",
					"你有新的商机了", msg, R.drawable.app_icon);
		}
	};
}
