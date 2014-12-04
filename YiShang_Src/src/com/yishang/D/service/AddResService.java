package com.yishang.D.service;

import android.app.Application;

import com.exception.utils.P;
import com.format.utils.DataValidate;
import com.format.utils.FormatUtils;
import com.lidroid.xutils.exception.DbException;
import com.yishang.A.global.Enum.Enum_ReceiverAction;
import com.yishang.A.global.Enum.com.Enum_ComRela;
import com.yishang.A.global.Enum.com.Enum_ComType;
import com.yishang.A.global.application.AppContextApplication;
import com.yishang.B.module.c.ResourceEntity.Recv_bookIfo;
import com.yishang.B.module.d.BusinessEntity.Recv_comDetail;
import com.yishang.B.module.f.LoginEntity.Recv_userIfo;
import com.yishang.C.dao.daoImpl.Dao_Company;
import com.yishang.C.dao.daoImpl.Dao_Msg;
import com.yishang.C.dao.daoImpl.Dao_Resource;
import com.yishang.C.dao.daoModel.T_Company;
import com.yishang.C.dao.daoModel.T_Msg;
import com.yishang.C.dao.daoModel.T_Resource;
import com.yishang.D.service.httpRequest.HttpReq_GetComIfo;
import com.yishang.D.service.httpRequest.HttpReq_GetResIfo;
import com.yishang.D.service.httpRequest.HttpReq_SYNCLocPoint;
import com.yishang.D.service.httpRequest.HttpReq_SYNCUserIfo;
import com.yishang.D.service.httpRequest.HttpReq_GetComIfo.CallBack;
import com.yishang.D.service.httpRequest.HttpReq_GetResIfo.CallBack_Res;
import com.yishang.D.service.httpRequest.HttpReq_SYNCLocPoint.CallBack_SYNCLOC;
import com.yishang.D.service.httpRequest.HttpReq_SYNCUserIfo.CallBack_UserIfo;
import com.yishang.F.receive.model.Recv_push;
import com.yishang.Z.utils.BroadcastUtil;

/**
 * 添加文档到本地的服务 同时更新消息表中的企业ID数据(根据资源ID)
 * 
 * @author MM_Zerui
 * 
 */
public class AddResService {
//	private T_Resource tBean;
//
//	public AddResService(final String resID,final String sendId,final String iniId,
//			final int u_co,final int u_rela) {
//		tBean = new T_Resource();
//		new HttpReq_GetResIfo(resID, new CallBack_Res() {
//
//			@Override
//			public void onSuccess(Recv_bookIfo bean) {
//				// TODO Auto-generated method stub
//				P.v("成功获取到文档信息");
//				tBean.setBook_name(bean.getBook_name()); // 文档名
//				tBean.setBook_summary(bean.getBook_sum());// 书摘要
//				tBean.setBook_kw(bean.getBook_kw());// 关键词
////				tBean.setBook_status(Integer.valueOf(bean.getBook_status())); // 书状态
//
//				tBean.setBook_id(resID);// 文档ID
//
//				tBean.setSend_id(sendId); // 文档发送方的ID
//				tBean.setSender_freq(u_co);// 关系强度
//				tBean.setSender_type(u_rela);// 来源关系
//				tBean.setBook_creater_id(iniId);// 文件创建ID（原始转发人）
//
//				tBean.setCom_id(bean.getCom_id()); // 获取该文档对应企业
//				// 文档URL
//				tBean.setBook_url(bean.getBook_url());
//				// 文档的访问html地址
////				tBean.setBook_html(bean.getBook_html());
//				// 文档收到时间
//				tBean.setBook_recvTime(FormatUtils.getCurrentDateValue());
//				// 文档操作热度
//				tBean.setBook_freq(0);
//
//				try {
//					// 增加一个文档到本地
//					Dao_Resource.addRes(tBean);
//					// 更新消息表中企业id信息
//					Dao_Msg.updataComId(resID, tBean.getCom_id());
//					//更新文档ID对应的文档名
//					Dao_Msg.updataResName(resID, tBean.getBook_name());
//				} catch (DbException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//			}
//
//			@Override
//			public void onFinally() {
//				// TODO Auto-generated method stub
//				// 信息更新操作
//				String bookId = tBean.getBook_id();
//				if (!DataValidate.checkDataValid(bookId)) {
//					return;
//				}
//				if (DataValidate.checkDataValid(tBean.getSend_id())) {
//					getSendIfo(tBean.getSend_id(), tBean.getBook_id());
//				}
//
//			}
//
//			@Override
//			public void onFail() {
//				// TODO Auto-generated method stub
//				P.v("获取到文档信息失败");
//				tBean.setBook_id(resID);// 文档ID
//
//				tBean.setSend_id(sendId); // 文档发送方的ID
//				tBean.setSender_freq(u_co);// 关系强度
//				tBean.setSender_type(u_rela);// 来源关系
//				tBean.setBook_creater_id(iniId);// 文件创建ID（原始转发人）
//
//				// 文档收到时间
//				tBean.setBook_recvTime(FormatUtils.getCurrentDateValue());
//				// 文档操作热度
//				tBean.setBook_freq(0);
//				try {
//					// 增加一个文档到本地
//					Dao_Resource.addRes(tBean);
//				} catch (DbException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//			}
//		});
//	}
//
//	/**
//	 * 获取转发人姓名和logo
//	 * 
//	 * @param uid
//	 */
//	public void getSendIfo(String uid, final String bookId) {
//		new HttpReq_SYNCUserIfo(uid, new CallBack_UserIfo() {
//
//			@Override
//			public void onSuccess(Recv_userIfo result) {
//				// TODO Auto-generated method stub
//				try {
//					Dao_Resource.updateSenderName(bookId, result);
//					P.v("更新文档中个人信息");
//					// 更新消息表中
//				} catch (DbException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//
//			@Override
//			public void onFinally() {
//				// TODO Auto-generated method stub
//				//
//				if (DataValidate.checkDataValid(tBean.getCom_id())) {
//					getComIfo(tBean.getCom_id(), tBean.getBook_id());
//				}
//			}
//
//			@Override
//			public void onFail() {
//				// TODO Auto-generated method stub
//				P.v("更新文档中个人信息失败");
//			}
//		});
//	}
//
//	/**
//	 * 获取转发文档对应的企业信息
//	 * 
//	 * @param comId
//	 */
//	public void getComIfo(final String comId, final String bookId) {
//		new HttpReq_GetComIfo(comId, new CallBack() {
//
//			@Override
//			public void requestSuccess(Recv_comDetail bean) {
//				// TODO Auto-generated method stub
//				try {
//					// 更新企业信息
//					Dao_Resource.updateCom(bookId, bean);
//					// 将文档对应的企业信息存入企业表中（设定为供应商）
//					T_Company comBean = new T_Company();
//					comBean.setCom_id(comId);
//					comBean.setCom_name(bean.getCom_name());
//					comBean.setCom_relate(Enum_ComType.COM_SUPPLIER.value());// 供应商
//					// 建立关系时间，这里为当前
//					comBean.setCom_relateTime(com.yishang.Z.utils.FormatUtils
//							.getCurrentDateValue());
//					comBean.setCom_review(Integer.valueOf(bean.getUc_in()));// 供应商审查权
//					comBean.setCom_state(Integer.valueOf(bean.getUc_status()));// 和企业的关联关系
//					comBean.setCom_icon(bean.getCom_logo());
//					comBean.setCom_abb(bean.getCom_abb());
//					Dao_Company.addComRecord_Total(comBean);
//					P.v("更新文档中企业信息");
//					P.v("更新了推送中的供应商信息");
//				} catch (DbException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//
//			@Override
//			public void requestFail() {
//				// TODO Auto-generated method stub
//				P.v("更新文档中企业信息失败");
//			}
//
//			@Override
//			public void onFinally() {
//				// TODO Auto-generated method stub
//				// 发送文档更新通知
//				BroadcastUtil.sendBroadCast(
//						AppContextApplication.getInstance(),
//						Enum_ReceiverAction.RESOURCE_PAGE.name());
//				// 发送更新企业的通知
//				BroadcastUtil.sendBroadCast(
//						AppContextApplication.getInstance(),
//						Enum_ReceiverAction.COMPANY.name());
//			}
//		});
//	}
}
