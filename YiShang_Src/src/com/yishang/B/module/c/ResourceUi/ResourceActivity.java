package com.yishang.B.module.c.ResourceUi;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomBarView;
import com.customview.view.CustomBarView.callBack_Bar;
import com.customview.view.CustomPageView;
import com.customview.view.CustomTopbarView;
import com.exception.utils.P;
import com.format.utils.DataValidate;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ruifeng.yishang.R;
import com.thread.HandlerExtend;
import com.thread.HandlerExtend.handleCallBack;
import com.yishang.A.global.Enum.Enum_ReceiverAction;
import com.yishang.A.global.Enum.constant.Enum_Color;
import com.yishang.A.global.Enum.constant.Enum_Param;
import com.yishang.A.global.Enum.db.Enum_ResSource;
import com.yishang.A.global.Enum.db.Enum_ResType;
import com.yishang.A.global.baseClass.ListActivity;
import com.yishang.A.global.callBack.listHttpCallBack;
import com.yishang.B.module.b.ContactsUI.ContactsSelectPage;
import com.yishang.B.module.e.SelfUi.UserIfoPage;
import com.yishang.C.dao.daoImpl.Dao_Relationship;
import com.yishang.C.dao.daoImpl.Dao_Resource;
import com.yishang.C.dao.daoModel.T_Relationships;
import com.yishang.C.dao.daoModel.T_Resource;
import com.yishang.D.service.dbRequest.DBReq_Resource;
import com.yishang.D.service.dbRequest.DBReq_Resource.callBack_Res;
import com.yishang.E.view.Dialog_Select;
import com.yishang.E.view.Dialog_Select.callBack_Dialog;
import com.yishang.E.view.MyDialog;
import com.yishang.E.view.adapter.ResourceDocItemAdapter;
import com.yishang.E.view.adapter.ResourceDocItemAdapter.callBack_Item;
import com.yishang.E.view.dialog.Effectstype;
import com.yishang.E.view.swipelistview.BaseSwipeListViewListener;
import com.yishang.E.view.swipelistview.SwipeListView;
import com.yishang.E.view.swipelistview.XListView;
import com.yishang.Z.utils.BroadcastUtil;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * 显示用户资源的模块界面
 * 
 * @author MM_Zerui
 * 
 */
public class ResourceActivity extends ListActivity {
	final private int RES_SOURCE_SELECT = 1;

	@ViewInject(R.id.listView)
	private XListView listView;

	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;

	@ViewInject(R.id.barView)
	private CustomBarView barView;

	@ViewInject(R.id.overallLayout)
	private LinearLayout overallLayout;

	@ViewInject(R.id.pageView)
	private CustomPageView pageView;

	public static List<T_Resource> bookList;
	// private CustomPopWinView popWinView;
	private ResourceDocItemAdapter adapter;

	private DBReq_Resource dbReq;

	private NotifyReceive_RESOURCE notifyReceive;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.resource_page_layout);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
		regiReceiver();
	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub

	}

	private void regiReceiver() {
		// TODO Auto-generated method stub
		// 注册监听器
		notifyReceive = new NotifyReceive_RESOURCE();
		BroadcastUtil.regiReceiver(this, notifyReceive,
				Enum_ReceiverAction.RESOURCE_PAGE.name());
	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		dbReq = new DBReq_Resource();

		// popWinView = new CustomPopWinView(context).setFirstItemText("我感兴趣的")
		// .setSecondItemText("来自通信录").setThirdItemText("来自朋友")
		// .setItemNum(3);
		bookList = new ArrayList<T_Resource>();
		adapter = new ResourceDocItemAdapter(context);
		listView.setHttpCallBack(new listHttpCallBack() {
			@Override
			public void initListView() {
				// listView.setViewMode(true, true,
				// SwipeListView.SWIPE_MODE_LEFT,
				// 1);
				listView.setMode(true, false);
				pageView.onProgressOnly().setVisibility(View.VISIBLE);
				handler.postDelayed(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						dbReq.setCallBack_Default(callRes)
								.setEnum_ResType(Enum_ResType.DEFAULT)
								.callInit();
					}
				}, Enum_Param.TIME0FFSET_PAGELOAD.value());

			}

			@Override
			public void onRefresh() {
				dbReq.callInit();
			}

			@Override
			public void onLoadMore() {
				dbReq.callLoad();

			}
		});
	}

	@Override
	protected void onClickListener() {
		// TODO Auto-generated method stub
		// listView.setSwipeListViewListener(new BaseSwipeListViewListener() {
		// @Override
		// public void onClickFrontView(int position) {
		// // TODO Auto-generated method stub
		// super.onClickFrontView(position);
		// T_Resource book = bookList.get(position - 1);
		// ViewSwitchUtils.tab_in2LeftIntent(context,
		// ResourceDetailPage.class, book.getBook_name(),
		// book.getBook_url(), book.getBook_id(), book.getCom_id());
		// }
		// });
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				T_Resource book = bookList.get(position - 1);
				ViewSwitchUtils.tab_in2LeftIntent(context,
						ResourceDetailPage.class, book.getBook_name(),
						book.getBook_url(), book.getBook_id(), book.getCom_id());
			}
		});
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				final int posi = --position;
				final T_Resource bean = bookList.get(position);
				new Dialog_Select(context).withTitle("选择对该资源")
						.withBtn_1("转发给好友").withBtn_2("从本地删除")
						.setCallBack(new callBack_Dialog() {
							@Override
							public void click_btn1() {
								// TODO Auto-generated method stub
								super.click_btn1();
								// 转发给好友
								try {
									ViewSwitchUtils.tab_in2TopIntent(context,
											ContactsSelectPage.class,
											bean.getBook_creater_id(),
											bean.getBook_id());
								} catch (Exception e) {
									// TODO: handle exception
									toast.setText("数据出错,无法转发");
								}
							}

							@Override
							public void click_btn2() {
								// TODO Auto-generated method stub
								super.click_btn2();
								// 从本地删除
								bookList.remove(posi);
								adapter.notifyDataSetChanged();
								
								//本地数据库的删除
								if(!Dao_Resource.delRes(bean.getBook_id())){
									toast.setText("删除失败");
								}

							}
						}).show();
				return false;
			}
		});
		topBar.setCallBack(new topBarCallBack() {

			@Override
			public void call_leftImgBtnListener() {
				// TODO Auto-generated method stub
				super.call_leftImgBtnListener();
				// 跳转到搜索页面
				ViewSwitchUtils.in2NormalIntent(context,
						ResourceSearchActivity.class);
			}

			@Override
			public void call_rightTextBtnListener() {
				// TODO Auto-generated method stub
				super.call_rightTextBtnListener();
				// 跳转到来源显示页面
				ViewSwitchUtils.tab_in2TopIntent_result(ResourceActivity.this,
						ResourceFilterDialog.class, RES_SOURCE_SELECT);
				// ViewSwitchUtils
			}
		});
		// 资源类型分类
		barView.setCallBack(new callBack_Bar() {

			@Override
			public void call_ThirdItem(int... position) {
				barView.setEnabled(false);
				listView.setVisibility(View.INVISIBLE);
				pageView.onProgressOnly().setVisibility(View.VISIBLE);
				// 时间排序
				handler.postDelayed(new Runnable() {

					@Override
					public void run() {
						dbReq.setEnum_ResType(Enum_ResType.TIME).callInit();
					}
				}, Enum_Param.TIME0FFSET_PAGELOAD.value());

			}

			@Override
			public void call_SecondItem(int... position) {
				// TODO Auto-generated method stub
				// 密切度
				pageView.onProgressOnly().setVisibility(View.VISIBLE);
				listView.setVisibility(View.INVISIBLE);
				barView.setEnabled(false);
				// 时间排序
				handler.postDelayed(new Runnable() {

					@Override
					public void run() {
						dbReq.setEnum_ResType(Enum_ResType.RELA_STRENGTH)
								.callInit();
					}
				}, Enum_Param.TIME0FFSET_PAGELOAD.value());

			}

			@Override
			public void call_FourthItem(int... position) {
				// TODO Auto-generated method stub
				// 常用排序
				pageView.onProgressOnly().setVisibility(View.VISIBLE);
				listView.setVisibility(View.INVISIBLE);
				barView.setEnabled(false);
				handler.postDelayed(new Runnable() {

					@Override
					public void run() {
						dbReq.setEnum_ResType(Enum_ResType.FREQUENCE)
								.callInit();
					}
				}, Enum_Param.TIME0FFSET_PAGELOAD.value());

			}

			@Override
			public void call_FirstItem(int... position) {
				// TODO Auto-generated method stub
				// 默认列表数据
				pageView.onProgressOnly().setVisibility(View.VISIBLE);
				listView.setVisibility(View.INVISIBLE);
				barView.setEnabled(false);
				handler.postDelayed(new Runnable() {

					@Override
					public void run() {
						dbReq.setEnum_ResType(Enum_ResType.DEFAULT).callInit();
					}
				}, Enum_Param.TIME0FFSET_PAGELOAD.value());

			}

			@Override
			public void call_FifthItem(int... position) {
				// TODO Auto-generated method stub

			}
		}, 0);
		// 资源删除按钮
		adapter.setCallBack(new callBack_Item() {

			@Override
			public void onBackClick(final int position) {
				// TODO Auto-generated method stub
				myDialog = new MyDialog(context).withTitle("删除提示")
						.withMessage("确定删除该文档么?")
						.withSwitchBtnLayout("删除", "取消")
						.setRightBtnTextColor(Enum_Color.TextNote.color())
						.withAnimat(Effectstype.Shake)
						.setOkBtnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								//
								T_Resource bean = bookList.get(position);
								if (Dao_Resource.delRes(bean.getBook_id())) {
									bookList.remove(position);
									adapter.notifyDataSetChanged();
									myDialog.dismiss();
									return;
								}
								toast.setText("删除失败,文档资源或不存在");
								myDialog.dismiss();
							}
						}).withShow();
			}

			@Override
			public void onSenderClick(int position) {
				// TODO Auto-generated method stub

				try {
					// 跳转到发送人界面
					T_Resource bean = bookList.get(position);
					T_Relationships peoBean = Dao_Relationship.getByID(bean
							.getSend_id());
					ViewSwitchUtils.tab_in2LeftIntent(context,
							UserIfoPage.class, peoBean.getRela_id(),
							String.valueOf(peoBean.getRela_register()),
							peoBean.getRela_phone());
				} catch (Exception e) {
					// TODO: handle exception
					toast.setText("信息有误,无法查找该联系人");
					P.v("跳转联系人错误:" + e.getMessage());
				}

			}
		});
	}

	/**
	 * 显示人脉信息的回调
	 */
	private callBack_Res callRes = new callBack_Res() {

		@Override
		public void onInit(List<T_Resource> list) {
			// TODO Auto-generated method stub
			bookList.clear();
			bookList = list;
			adapter.setData(list);
			handleExtend.onInitView();
		}

		@Override
		public void onLoadMore(List<T_Resource> list) {
			// TODO Auto-generated method stub
			bookList.addAll(list);
			handleExtend.onRefreshView();
		}

		@Override
		public void onFail() {
			// TODO Auto-generated method stub
			handleExtend.onFail();
		}

		@Override
		public void onFinally() {
			// TODO Auto-generated method stub
			handleExtend.onFinally();
		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == RES_SOURCE_SELECT) {
			if (ResourceFilterDialog.enumSource == Enum_ResSource.DEFAULT) {
				return;
			}
			pageView.onProgressOnly().setVisibility(View.VISIBLE);
			listView.setVisibility(View.INVISIBLE);
			handler.postDelayed(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					dbReq.setEnum_Source(ResourceFilterDialog.enumSource)
							.callInit();
				}
			}, Enum_Param.TIME0FFSET_PAGELOAD.value());

		}
	}

	private HandlerExtend handleExtend = new HandlerExtend(
			new handleCallBack() {

				@Override
				public void call_onInit() {
					listView.setVisibility(View.VISIBLE);
					listView.setPullLoadEnable(true);
					listView.setAdapter(adapter);
					pageView.setVisibility(View.GONE);
				}

				@Override
				public void call_onRefresh() {
					listView.setVisibility(View.VISIBLE);
					adapter.notifyDataSetChanged();
					pageView.setVisibility(View.GONE);
				}

				public void call_onFail() {
					if (dbReq.isIfInit()) {
						pageView.setDefaultPage().setTextOnly("无相关资源")
								.setBottomLayoutVisible(false)
								.setVisibility(View.VISIBLE);
					}
					listView.setPullLoadEnable(false);

				};

				public void call_onFinally() {
					listView.onLoadStop();
					barView.setEnabled(true);
				};
			});

	class NotifyReceive_RESOURCE extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if (intent.getAction().equals(
					Enum_ReceiverAction.RESOURCE_PAGE.name())) {
				P.v("资源列表接收器接收刷新通知");
				dbReq.callInit();
			}
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
