package com.yishang.B.module.a.MsgUi;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.PopupWindow.OnDismissListener;

import com.customview.callBack.popWinCallBack;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomPageView;
import com.customview.view.CustomPopWinView;
import com.customview.view.CustomTopbarView;
import com.exception.utils.P;
import com.format.utils.DataValidate;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;

import com.ruifeng.yishang.R;
import com.smoothprogressbar.SmoothProgressBar;
import com.thread.HandlerExtend;
import com.thread.HandlerExtend.handleCallBack;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;
import com.yishang.A.global.Enum.Enum_ReceiverAction;
import com.yishang.A.global.Enum.constant.Enum_Color;
import com.yishang.A.global.Enum.constant.Enum_Param;
import com.yishang.A.global.Enum.push.Enum_PushSource;
import com.yishang.A.global.baseClass.ListActivity;
import com.yishang.A.global.baseClass.TabBarActivity;
import com.yishang.A.global.callBack.listHttpCallBack;
import com.yishang.A.global.constant.CONSTANT;
import com.yishang.A.global.constant.DAO_CONSTANT;
import com.yishang.A.global.constant.PROTOCOL;
import com.yishang.B.module.a.MsgEntity.MsgBean;
import com.yishang.C.dao.daoImpl.Dao_Company;
import com.yishang.C.dao.daoImpl.Dao_Msg;
import com.yishang.C.dao.daoImpl.Dao_MsgNewFri;
import com.yishang.C.dao.daoImpl.Dao_MsgSeq;
import com.yishang.C.dao.daoModel.T_Company;
import com.yishang.C.dao.daoModel.T_Msg;
import com.yishang.C.dao.daoModel.T_MsgSeq;
import com.yishang.D.service.dbRequest.DBReq_MsgSeq;
import com.yishang.D.service.dbRequest.DBReq_MsgSeq.CallBack_Msg;
import com.yishang.D.service.sync.SYNCMsgService;
import com.yishang.E.view.Dialog_Select;
import com.yishang.E.view.Dialog_Select.callBack_Dialog;
import com.yishang.E.view.MyDialog;
import com.yishang.E.view.adapter.MsgSeqItemAdapter;
import com.yishang.E.view.adapter.MsgSeqItemAdapter.callBack;
import com.yishang.E.view.swipelistview.BaseSwipeListViewListener;
import com.yishang.E.view.swipelistview.SwipeListView;
import com.yishang.E.view.swipelistview.XListView;
import com.yishang.Z.utils.Benchmark;
import com.yishang.Z.utils.BroadcastUtil;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * ��Ϣ֪ͨҳ��
 * 
 * @author MM_Zerui
 * 
 */
public class MsgActivity extends ListActivity {
	private final static int REQ_NEW_FRIEND = 100;
	private final static int MSG_SOURCE_NOTE = 101;
	@ViewInject(R.id.listView)
	private XListView listView;
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.pageView)
	private CustomPageView pageView;
	@ViewInject(R.id.syncProgress)
	private SmoothProgressBar syncProgress;

	// private CustomPopWinView popWinView;
	private MsgSeqItemAdapter adapter;
	List<T_MsgSeq> list;

	private DBReq_MsgSeq dbMsgSeq;
	private NotifyReceive notifyReceive;

	private int N_Position = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.msg_page_layout);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
		regiReceiver();
	}

	private void regiReceiver() {
		// TODO Auto-generated method stub
		// ע�������
		notifyReceive = new NotifyReceive();
		BroadcastUtil.regiReceiver(this, notifyReceive,
				Enum_ReceiverAction.MSG_PAGE.name());
		BroadcastUtil.regiReceiver(this, notifyReceive,
				Enum_ReceiverAction.MSG_PAGE_NULL.name());
	}

	@Override
	protected void obtainIntentValue() {

	}

	@Override
	protected void initResource() {
		// ͬ���̻�����
		ViewSwitchUtils.startService(MsgActivity.this, SYNCMsgService.class);
		//
		topBar.setProVisibility(true).setTitle("ͬ��...");
		listView.setHttpCallBack(new listHttpCallBack() {

			@Override
			public void initListView() {
				// TODO Auto-generated method stub
				// listView.onR
				listView.setMode(true, false);
				list = new ArrayList<T_MsgSeq>();
				adapter = new MsgSeqItemAdapter(context);
				dbMsgSeq = new DBReq_MsgSeq(Enum_PushSource.DEFAULT, callMsg);
				dbMsgSeq.onInit();
			}

			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub

			}

			@Override
			public void onLoadMore() {
				// TODO Auto-generated method stub
				dbMsgSeq.onLoad();
			}

		});
	}

	@Override
	protected void onClickListener() {
		// TODO Auto-generated method stub

		topBar.setCallBack(new topBarCallBack() {
			@Override
			public void call_rightImgBtnListener() {
				super.call_rightImgBtnListener();
				ViewSwitchUtils.tab_in2TopIntent_result(MsgActivity.this,
						MsgFilterDialog.class, MSG_SOURCE_NOTE);

			}

			@Override
			public void call_leftTextBtnListener() {
				super.call_leftTextBtnListener();
				dbMsgSeq.setSourceType(Enum_PushSource.DEFAULT).onInit();
			}
		});
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				position--;
				Enum_PushSource sEnum = Enum_PushSource.DEFAULT.valueOf(list
						.get(position).getMsg_seq_source());
				T_MsgSeq seqBean = list.get(position);
				switch (sEnum) {
				case SYSTEM: // ϵͳ֪ͨ
					ViewSwitchUtils.tab_in2LeftIntent(context,
							MsgSystemPage.class);
					// δ�����������
					try {
						Dao_MsgSeq.clearUnRead_SYSTEM();
						// ֪ͨ�б�
						list.get(position).setMsg_seq_unReadNum(0);
						adapter.notifyDataSetChanged();
					} catch (DbException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						P.v("����û�δ����ʧ��");
					}
					return;
				case COMPANY: // ��ҵ֪ͨ
					ViewSwitchUtils.tab_in2LeftIntent(context,
							MsgCompanyPage.class, list.get(position)
									.getMsg_seq_comId());
					try {
						Dao_MsgSeq.clearUnRead_Com(list.get(position)
								.getMsg_seq_comId());
						// ֪ͨ�б�
						list.get(position).setMsg_seq_unReadNum(0);
						adapter.notifyDataSetChanged();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case USER: // �û����
					Benchmark.start("");
					ViewSwitchUtils.tab_in2LeftIntent(context,
							MsgReceivePage.class, seqBean.getMsg_seq_sendId());
					// δ�����������
					try {
						Dao_MsgSeq.clearUnRead_USER(seqBean.getMsg_seq_sendId());
						// ֪ͨ�б�
						list.get(position).setMsg_seq_unReadNum(0);
						adapter.notifyDataSetChanged();
					} catch (DbException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						P.v("����û�δ����ʧ��");
					}
					break;
				case NEWFRIEND:
					// ������
					ViewSwitchUtils.tab_in2LeftIntent_result(MsgActivity.this,
							MsgNewFriendPage.class, REQ_NEW_FRIEND);
					N_Position = position;
					break;
				default:
					break;
				}
			}
		});
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stu
				final int posi=--position;
				final T_MsgSeq bean = list.get(posi);
				if (bean.getMsg_seq_source() == Enum_PushSource.NEWFRIEND
						.value()) {
					return false;
				}
				Dialog_Select dialog = new Dialog_Select(context);
				dialog.withTitle("����").withBtn_1("ɾ���ü�¼")
						.setCallBack(new callBack_Dialog() {
							@Override
							public void click_btn1() {
								// TODO Auto-generated method stub
								super.click_btn1();
								try {
									Dao_MsgSeq.delMsg(bean.getMsg_seq_sendId(),
											Enum_PushSource.valueOf(bean
													.getMsg_seq_source()));
									list.remove(posi);
									adapter.notifyDataSetChanged();
								} catch (Exception e) {
									// TODO: handle exception
									P.v("ɾ����Ϣ��¼����:" + e);
									toast.setText("ɾ��ʧ��");
								}
							}
						}).show();
				return false;
			}
		});
		// adapter.setCallBack(new callBack() {
		//
		// @Override
		// public void onBackClick(final int position) {
		// // TODO Auto-generated method stub
		//
		// myDialog = new MyDialog(context).withTitle("��ʾ")
		// .withMessage("ȷ��ɾ���ü�¼ô?")
		// .withSwitchBtnLayout("ɾ��", "ȡ��")
		// .setRightBtnTextColor(Enum_Color.TextNote.color())
		// .setOkBtnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// myDialog.dismiss();
		// // ɾ���ü�¼
		// try {
		// Dao_MsgSeq.delRecord_USER(list
		// .get(position).getMsg_seq_sendId());
		// list.remove(position);
		//
		// adapter.notifyDataSetChanged();
		// if (list.size() == 0) {
		// dbMsgSeq.onInit();
		// }
		// } catch (DbException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// toast.setText("ɾ��ʧ��");
		// }
		//
		// }
		// }).withShow();
		// }
		// });
	}

	private CallBack_Msg callMsg = new CallBack_Msg() {

		@Override
		public void onSuccess(List<T_MsgSeq> datas) {
			// TODO Auto-generated method stub
			list.clear();
			list = datas;
			adapter.setData(list);
			handlerExtend.onInitView();
		}

		@Override
		public void onLoad(List<T_MsgSeq> datas) {
			// TODO Auto-generated method stub
			list.addAll(datas);
			handlerExtend.onRefreshView();
		}

		@Override
		public void onFinally() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onFail() {
			// TODO Auto-generated method stub
			handlerExtend.onFail();
		}
	};
	public HandlerExtend handlerExtend = new HandlerExtend(
			new handleCallBack() {

				@Override
				public void call_onInit() {
					listView.setAdapter(adapter);
					listView.setPullLoadEnable(true);
					// listView.startLoadMore();
					listView.setVisibility(View.VISIBLE);
					pageView.setVisibility(View.GONE);

				}

				@Override
				public void call_onRefresh() {
					adapter.notifyDataSetChanged();
					listView.setVisibility(View.VISIBLE);
					pageView.setVisibility(View.GONE);
				}

				public void call_onLoadNull() {
					listView.stopLoadMore();
				};

				public void call_onFail() {
					listView.setPullLoadEnable(false);
					if (dbMsgSeq.isIfInit()) {
						pageView.setDefaultPage().setTextOnly("��û���̻�Ŷ")
								.setBottomLayoutVisible(false)
								.setVisibility(View.VISIBLE);
					}

				};
			});

	/**
	 * ����һ������֪ͨ���ڲ��࣬���ڸ����б���ͼ
	 * 
	 * @author MM_Zerui
	 * @tip_1 ������Ϣ����ʱ�������ü�����
	 * @tip_2 ���������Activity����������
	 * @tip_3 ��������Activity����ʱ��ȡ��ע�᲻�ټ���
	 */
	class NotifyReceive extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			P.v("�̻�ҳ��:" + intent.getAction());
			if (intent.getAction().equals(Enum_ReceiverAction.MSG_PAGE.name())) {
				// onResume();
				dbMsgSeq.onInit();
				syncProgress.setVisibility(View.GONE);
				topBar.setRightImgVisibility(true).setTitle("�̻�");
				P.v("ˢ���̼��б�(������)");
			}
			if (intent.getAction().equals(
					Enum_ReceiverAction.MSG_PAGE_NULL.name())) {

				syncProgress.setVisibility(View.GONE);
				topBar.setRightImgVisibility(true).setTitle("�̻�");
				P.v("ˢ���̼��б�(������)");
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case REQ_NEW_FRIEND:
			// ����������ģ��δ����
			try {
				list.get(N_Position).setMsg_seq_unReadNum(
						Dao_MsgNewFri.getUnReadNum_All());
				adapter.notifyDataSetChanged();
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		case MSG_SOURCE_NOTE:
			if (MsgFilterDialog.enumSource == Enum_PushSource.DEFAULT) {
				return;
			}
			listView.setVisibility(View.INVISIBLE);
			pageView.onProgressOnly().setVisibility(View.VISIBLE);
			handler.postDelayed(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					dbMsgSeq.setSourceType(MsgFilterDialog.enumSource).onInit();
				}
			}, Enum_Param.TIMEOFFSET_LISTLOAD.value());

		}
	}

	@Override
	protected void outFinish() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(notifyReceive);
	}

}
