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
 * ����ĵ������صķ��� ͬʱ������Ϣ���е���ҵID����(������ԴID)
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
//				P.v("�ɹ���ȡ���ĵ���Ϣ");
//				tBean.setBook_name(bean.getBook_name()); // �ĵ���
//				tBean.setBook_summary(bean.getBook_sum());// ��ժҪ
//				tBean.setBook_kw(bean.getBook_kw());// �ؼ���
////				tBean.setBook_status(Integer.valueOf(bean.getBook_status())); // ��״̬
//
//				tBean.setBook_id(resID);// �ĵ�ID
//
//				tBean.setSend_id(sendId); // �ĵ����ͷ���ID
//				tBean.setSender_freq(u_co);// ��ϵǿ��
//				tBean.setSender_type(u_rela);// ��Դ��ϵ
//				tBean.setBook_creater_id(iniId);// �ļ�����ID��ԭʼת���ˣ�
//
//				tBean.setCom_id(bean.getCom_id()); // ��ȡ���ĵ���Ӧ��ҵ
//				// �ĵ�URL
//				tBean.setBook_url(bean.getBook_url());
//				// �ĵ��ķ���html��ַ
////				tBean.setBook_html(bean.getBook_html());
//				// �ĵ��յ�ʱ��
//				tBean.setBook_recvTime(FormatUtils.getCurrentDateValue());
//				// �ĵ������ȶ�
//				tBean.setBook_freq(0);
//
//				try {
//					// ����һ���ĵ�������
//					Dao_Resource.addRes(tBean);
//					// ������Ϣ������ҵid��Ϣ
//					Dao_Msg.updataComId(resID, tBean.getCom_id());
//					//�����ĵ�ID��Ӧ���ĵ���
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
//				// ��Ϣ���²���
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
//				P.v("��ȡ���ĵ���Ϣʧ��");
//				tBean.setBook_id(resID);// �ĵ�ID
//
//				tBean.setSend_id(sendId); // �ĵ����ͷ���ID
//				tBean.setSender_freq(u_co);// ��ϵǿ��
//				tBean.setSender_type(u_rela);// ��Դ��ϵ
//				tBean.setBook_creater_id(iniId);// �ļ�����ID��ԭʼת���ˣ�
//
//				// �ĵ��յ�ʱ��
//				tBean.setBook_recvTime(FormatUtils.getCurrentDateValue());
//				// �ĵ������ȶ�
//				tBean.setBook_freq(0);
//				try {
//					// ����һ���ĵ�������
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
//	 * ��ȡת����������logo
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
//					P.v("�����ĵ��и�����Ϣ");
//					// ������Ϣ����
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
//				P.v("�����ĵ��и�����Ϣʧ��");
//			}
//		});
//	}
//
//	/**
//	 * ��ȡת���ĵ���Ӧ����ҵ��Ϣ
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
//					// ������ҵ��Ϣ
//					Dao_Resource.updateCom(bookId, bean);
//					// ���ĵ���Ӧ����ҵ��Ϣ������ҵ���У��趨Ϊ��Ӧ�̣�
//					T_Company comBean = new T_Company();
//					comBean.setCom_id(comId);
//					comBean.setCom_name(bean.getCom_name());
//					comBean.setCom_relate(Enum_ComType.COM_SUPPLIER.value());// ��Ӧ��
//					// ������ϵʱ�䣬����Ϊ��ǰ
//					comBean.setCom_relateTime(com.yishang.Z.utils.FormatUtils
//							.getCurrentDateValue());
//					comBean.setCom_review(Integer.valueOf(bean.getUc_in()));// ��Ӧ�����Ȩ
//					comBean.setCom_state(Integer.valueOf(bean.getUc_status()));// ����ҵ�Ĺ�����ϵ
//					comBean.setCom_icon(bean.getCom_logo());
//					comBean.setCom_abb(bean.getCom_abb());
//					Dao_Company.addComRecord_Total(comBean);
//					P.v("�����ĵ�����ҵ��Ϣ");
//					P.v("�����������еĹ�Ӧ����Ϣ");
//				} catch (DbException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//
//			@Override
//			public void requestFail() {
//				// TODO Auto-generated method stub
//				P.v("�����ĵ�����ҵ��Ϣʧ��");
//			}
//
//			@Override
//			public void onFinally() {
//				// TODO Auto-generated method stub
//				// �����ĵ�����֪ͨ
//				BroadcastUtil.sendBroadCast(
//						AppContextApplication.getInstance(),
//						Enum_ReceiverAction.RESOURCE_PAGE.name());
//				// ���͸�����ҵ��֪ͨ
//				BroadcastUtil.sendBroadCast(
//						AppContextApplication.getInstance(),
//						Enum_ReceiverAction.COMPANY.name());
//			}
//		});
//	}
}
