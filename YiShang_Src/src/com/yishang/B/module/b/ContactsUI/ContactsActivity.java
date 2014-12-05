package com.yishang.B.module.b.ContactsUI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.style.LineHeightSpan.WithDensity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.customview.callBack.pageCallBack;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomBarView.callBack_Bar;
import com.customview.view.CustomPageView;
import com.customview.view.CustomTopbarView;
import com.exception.utils.P;
import com.format.utils.DataValidate;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.ruifeng.yishang.R;
import com.thread.HandleView;
import com.thread.HandleView.viewCallBack;
import com.thread.HandlerExtend;
import com.thread.HandlerExtend.handleCallBack;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;
import com.yishang.A.global.Enum.Enum_ReceiverAction;
import com.yishang.A.global.Enum.constant.Enum_Param;
import com.yishang.A.global.Enum.db.Enum_IfRegister;
import com.yishang.A.global.Enum.db.Enum_RelaType;
import com.yishang.A.global.baseClass.ListActivity;
import com.yishang.A.global.baseClass.SharePage;
import com.yishang.A.global.callBack.listHttpCallBack;
import com.yishang.B.module.a.MsgUi.FeedBackDialog;
import com.yishang.B.module.c.ResourceUi.ResourceSelectPage;
import com.yishang.B.module.e.SelfUi.UserIfoPage;
import com.yishang.C.dao.daoModel.T_Relationships;
import com.yishang.D.service.dbRequest.DBReq_Relationship;
import com.yishang.D.service.dbRequest.DBReq_Relationship.CallBack_Rela;
import com.yishang.D.service.sync.SYNCLocalContactsService;
import com.yishang.E.view.ClearEditText;
import com.yishang.E.view.Dialog_Select;
import com.yishang.E.view.Dialog_Select.callBack_Dialog;
import com.yishang.E.view.MyDialog;
import com.yishang.E.view.adapter.ContactsItemAdapter;
import com.yishang.E.view.adapter.ContactsItemAdapter.callBack_Item;
import com.yishang.E.view.swipelistview.BaseSwipeListViewListener;
import com.yishang.E.view.swipelistview.SwipeListView;
import com.yishang.E.view.swipelistview.XListView;
import com.yishang.E.view.swipelistview.XListView.IXListViewListener;
import com.yishang.Z.utils.Benchmark;
import com.yishang.Z.utils.BroadcastUtil;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * 展示人脉信息的页面
 * 
 * @author MM_Zerui
 * @tip_1 首先跳转到该页面时将立即进行通讯录的同步操作
 * @tip_2 显示当前所有人脉
 * 
 */
public class ContactsActivity extends ListActivity {
	final static int RESULT_CONTACT_SELECT = 1;

	final static int CONTACT_SELECT_CLIENT = 1;
	final static int CONTACT_SELECT_SUPPLIER = 2;
	final static int CONTACT_SELECT_CONTACT = 3;
	final static int CONTACT_SELECT_FRIEND = 4;
	final static int CONTACT_SELECT_BLAKLIST = 5;

	@ViewInject(R.id.listView)
	private XListView listView;
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.pageView)
	private CustomPageView pageView;
	// @ViewInject(R.id.progressBar2)
	// private ProgressBar progressBar;

	private ContactsItemAdapter adapter;
	List<T_Relationships> list;
	public static List<T_Relationships> listCopy;

	private NotifyReceive_CONTACT notifyReceive;

	private DBReq_Relationship DBRequest;

	private boolean ifInit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.contacts_page_layout);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
		regiReceiver();
	}

	private void regiReceiver() {
		// TODO Auto-generated method stub
		// 注册监听器
		notifyReceive = new NotifyReceive_CONTACT();
		BroadcastUtil.regiReceiver(this, notifyReceive,
				Enum_ReceiverAction.CONTACTS_PAGE.name());
	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onClickListener() {
		// TODO Auto-generated method stub
		topBar.setCallBack(new topBarCallBack() {
			@Override
			public void call_rightTextBtnListener() {
				// TODO Auto-generated method stub
				super.call_rightTextBtnListener();
				ViewSwitchUtils.tab_in2TopIntent_result(ContactsActivity.this,
						ContactsFilterDialog.class, RESULT_CONTACT_SELECT);
			}

			@Override
			public void call_leftImgBtnListener() {
				// TODO Auto-generated method stub
				super.call_leftImgBtnListener();
				ViewSwitchUtils.nor_toIntent(context,
						ContactsSearchActivity.class);
			}

			@Override
			public void call_leftTextBtnListener() {
				// TODO Auto-generated method stub
				super.call_leftTextBtnListener();

			}

			@Override
			public void call_rightSecondClick() {
				// TODO Auto-generated method stub
				super.call_rightSecondClick();
				ViewSwitchUtils.tab_in2TopIntent(context,
						ContactsNewPhoneDialog.class);
			}
		});
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				T_Relationships bean = list.get(position - 1);
				Enum_RelaType enumType=Enum_RelaType.valueOf(bean.getRela_typeResult());
				switch (enumType) {
				case SYSTEM:
					ViewSwitchUtils.tab_in2TopIntent(context, FeedBackDialog.class);
					break;

				default:
					ViewSwitchUtils.tab_in2LeftIntent(context, UserIfoPage.class,
							bean.getRela_id(),
							String.valueOf(bean.getRela_register()),
							bean.getRela_phone());
					break;
				}
			
			}
		});
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				position--;
				Dialog_Select dialog = new Dialog_Select(context);
				final T_Relationships bean = list.get(position);
				String title = bean.getRela_name();
				dialog.withTitle("选择对 " + title).withBtn_1("转发文档")
						.withBtn_2("屏蔽该联系人").setCallBack(new callBack_Dialog() {
							@Override
							public void click_btn1() {
								// TODO Auto-generated method stub
								super.click_btn1();
								// 转发文档
								// 分享操作
								ViewSwitchUtils.tab_in2TopIntent(context,
										ResourceSelectPage.class,
										bean.getRela_id());
							}

							@Override
							public void click_btn2() {
								// TODO Auto-generated method stub
								super.click_btn2();
								// 屏蔽联系人
							}
						}).show();
				return false;
			}
		});
		pageView.setCallBack(new pageCallBack() {

			@Override
			public void onRefreshClick() {
				// TODO Auto-generated method stub
			}
		});
		adapter.setCallBack(new callBack_Item() {

			@Override
			public void onBackClick_2(int position) {
				// 删除操作
				myDialog = new MyDialog(context).withTitle("屏蔽联系人")
						.withMessage("屏蔽后此人将不再人脉中显示")
						.withSwitchBtnLayout("屏蔽", "取消")
						.setOkBtnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								myDialog.dismiss();
							}
						}).withShow();
			}

			@Override
			public void onBackClick_1(int position) {
				// 分享操作
				ViewSwitchUtils.tab_in2TopIntent(context,
						ResourceSelectPage.class, list.get(position)
								.getRela_id());
			}
		});
	}

	@Override
	protected void initResource() {
		ifInit = true;
		adapter = new ContactsItemAdapter(context);
		// 滑动属性
		// listView.setSwipeListViewListener(new BaseSwipeListViewListener() {
		// @Override
		// public void onClickFrontView(int position) {
		// // TODO Auto-generated method stub
		// super.onClickFrontView(position);
		// T_Relationships bean = list.get(position - 1);
		// ViewSwitchUtils.in2LeftIntent(context, UserIfoPage.class,
		// bean.getRela_id(),
		// String.valueOf(bean.getRela_register()),
		// bean.getRela_phone());
		// }
		// });
		// pageView.onProgressOnly().setVisibility(View.VISIBLE);
		listView.setHttpCallBack(new listHttpCallBack() {
			@Override
			public void initListView() {
				list = new ArrayList<T_Relationships>();
				// listView.setViewMode(false, true,
				// SwipeListView.SWIPE_MODE_LEFT, 2);
				listView.setListMode(false, true);
				DBRequest = new DBReq_Relationship();
				pageView.onProgressOnly().setVisibility(View.VISIBLE);
				handler.postDelayed(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						DBRequest.setCallBack(callBack, Enum_RelaType.DEFAULT)
								.callInit();
					}
				}, Enum_Param.TIMEOFFSET_LISTLOAD.value());

			}

			@Override
			public void onRefresh() {
				DBRequest.callInit();
			}

			@Override
			public void onLoadMore() {
				DBRequest.callLoad();
			}

		});

	}

	// private HandleView handleView = new HandleView(new viewCallBack() {
	//
	// @Override
	// public void call_onData(Object obj) {
	// // TODO Auto-generated method stub
	// int position = (Integer) obj;
	// toast.setText("删除了的项:" + position);
	// }
	// });
	private CallBack_Rela callBack = new CallBack_Rela() {

		@Override
		public void onInit(List<T_Relationships> datas) {
			list.clear();
			list = datas;
			adapter.setData(list);
			if (ifInit) {
				handlerExtend.onInitView();
				return;
			}
			handlerExtend.onRefreshView();

		}

		@Override
		public void onLoad(List<T_Relationships> datas) {
			if (DataValidate.checkDataValid(datas)) {
				list.addAll(datas);
				handlerExtend.onRefreshView();
				return;
			}
			// handlerExtend.onLoadNull();

		}

		@Override
		public void onFail() {
			// TODO Auto-generated method stub

			handlerExtend.onFail();
		}

		@Override
		public void onFinally() {
			// TODO Auto-generated method stub
			handlerExtend.onFinally();
		}
	};
	private HandlerExtend handlerExtend = new HandlerExtend(
			new handleCallBack() {
				@Override
				public void call_onInit() {
					// 每次初始，开启加载更多
					listView.setPullLoadEnable(true);
					Benchmark.start("Adapter");
					listView.setAdapter(adapter);
					pageView.setVisibility(View.GONE);
					Benchmark.end("Adapter");

				}

				@Override
				public void call_onRefresh() {
					// TODO Auto-generated method stub
					adapter.notifyDataSetChanged();
					pageView.setVisibility(View.GONE);
					listView.onLoadStop();
				}

				public void call_onFail() {
					// 如果没有更多数据,关闭加载更多
					listView.setPullLoadEnable(false);
					if (DBRequest.isIfInit()) {
						pageView.setTextOnly("无相关人脉信息哦")
								.setBottomLayoutVisible(false)
								.setVisibility(View.VISIBLE);
					}
				};

				public void call_onFinally() {
					listView.onLoadStop();
					listView.setVisibility(View.VISIBLE);

					// progressBar.setVisibility(View.INVISIBLE);
				};

				public void call_onLoadNull() {
					// 如果没有更多数据,关闭加载更多
					listView.setPullLoadEnable(false);
				}
			});

	/**
	 * 同步通讯录
	 * 
	 * @param v
	 */
	@OnClick(R.id.contacts_local_sync_btn)
	private void localSyncClick(View v) {
		topBar.setTitle("正在同步...").setProVisibility(true);
		ViewSwitchUtils.startService(context, SYNCLocalContactsService.class);
	}

	/**
	 * 跳转到附近用户页面
	 * 
	 * @param v
	 */
	@OnClick(R.id.contacts_nearby_btn)
	private void nearbyClick(View v) {
		ViewSwitchUtils
				.tab_in2LeftIntent_int(context, ContactsNearbyPage.class);
	}

	/**
	 * 跳转到同一wifi下用户页面
	 * 
	 * @param v
	 */
	@OnClick(R.id.contacts_same_wifi_btn)
	private void sameWifiClick(View v) {
		// ViewSwitchUtils.tab_in2LeftIntent_int(context,
		// ContactsWifiPage.class);
		ViewSwitchUtils.tab_in2LeftIntent(context, ContactsWifiPage.class);
	}

	/**
	 * 跳转到同一城市下用户页面
	 * 
	 * @param v
	 */
	@OnClick(R.id.contacts_same_city_btn)
	private void sameCityClick(View v) {
		ViewSwitchUtils
				.tab_in2LeftIntent_int(context, ContactsAtCityPage.class);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case RESULT_CONTACT_SELECT:
			if (ContactsFilterDialog.enumType == Enum_RelaType.UNKNOW) {
				return;
			}
			pageView.onProgressOnly().setVisibility(View.VISIBLE);
			listView.setVisibility(View.INVISIBLE);
			handler.postDelayed(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					DBRequest.setCallBack(callBack,
							ContactsFilterDialog.enumType).callInit();
				}
			}, Enum_Param.TIMEOFFSET_LISTLOAD.value());

		default:
			break;
		}
	}

	public void clearUnMapList(int relaType) {
		if (DataValidate.checkDataValid(list)) {
			Iterator<T_Relationships> iterable = list.iterator();
			while (iterable.hasNext()) {
				if (iterable.next().getRela_type() != relaType) {
					iterable.remove();
				}
			}
		}
	}

	class NotifyReceive_CONTACT extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if (intent.getAction().equals(
					Enum_ReceiverAction.CONTACTS_PAGE.name())) {
				try {
					DBRequest.setEnum(Enum_RelaType.DEFAULT);
					DBRequest.callInit();
					P.v("刷新");
					topBar.setProVisibility(false).setTitle("人脉")
							.setRighTextVisibility(true)
							.onRightSecondTextBtn_Text(true);
				} catch (Exception e) {
					// TODO: handle exception
					P.v("人脉页面接收器异常");
				}
			}
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		// unregisterReceiver(notifyReceive);
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
