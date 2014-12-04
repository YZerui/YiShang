package com.yishang.B.module.b.ContactsUI;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomBarView;
import com.customview.view.CustomPageView;
import com.customview.view.CustomTopbarView;
import com.customview.view.CustomBarView.callBack_Bar;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.ruifeng.yishang.R;
import com.thread.HandlerExtend;
import com.thread.HandlerExtend.handleCallBack;
import com.yishang.A.global.Enum.Enum_BackSytle;
import com.yishang.A.global.Enum.db.Enum_IfRegister;
import com.yishang.A.global.Enum.http.Enum_ReqRelaSource;
import com.yishang.A.global.Enum.http.Enum_ReqRelaType;
import com.yishang.A.global.application.T_UserPoint;
import com.yishang.A.global.baseClass.ListActivity;
import com.yishang.A.global.callBack.listHttpCallBack;
import com.yishang.B.module.b.ContactsEntity.Contacts_Nearby;
import com.yishang.B.module.c.ResourceUi.ResourceSelectPage_Single;
import com.yishang.B.module.e.SelfUi.UserIfoPage;
import com.yishang.D.service.HttpResultService;
import com.yishang.D.service.httpRequest.HttpReq_AddContacts;
import com.yishang.D.service.httpRequest.HttpReq_GetAtCity;
import com.yishang.D.service.httpRequest.HttpReq_GetNearby;
import com.yishang.D.service.httpRequest.HttpReq_AddContacts.ACCallBack;
import com.yishang.D.service.httpRequest.HttpReq_GetAtCity.CallBack_City;
import com.yishang.D.service.httpRequest.HttpReq_GetNearby.CallBack_Nearby;
import com.yishang.D.service.sync.SYNCRelationshipService;
import com.yishang.E.view.adapter.ContactsCityAdapter;
import com.yishang.E.view.adapter.ContactsNearbyAdapter;
import com.yishang.E.view.adapter.ContactsWifiAdapter;
import com.yishang.E.view.adapter.ContactsCityAdapter.ItemCallBack;
import com.yishang.E.view.swipelistview.SwipeListView;
import com.yishang.E.view.swipelistview.XListView;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * 显示同城的朋友
 * 
 * @author MM_Zerui
 * 
 */
public class ContactsAtCityPage extends ListActivity {
	private final static int REQ_NEARBY_SELECT = 1;

	@ViewInject(R.id.listView)
	private XListView listView;
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.pageView)
	private CustomPageView pageView;
	@ViewInject(R.id.bottomBar)
	private CustomBarView bottomBar;

	ArrayList<Contacts_Nearby> myList;
	private ContactsCityAdapter adapter;
	private ArrayList<T_UserPoint> list;
	private ArrayList<T_UserPoint> selectItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.contacts_city_page_layout);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);

	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		SOURCE_PAGE_NOTE = getIntent().getIntExtra("DATA0", 0);
	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		myList = new ArrayList<Contacts_Nearby>();
		adapter = new ContactsCityAdapter(context);
		selectItems = new ArrayList<T_UserPoint>();
		pageView.setProgress("获取中...").setVisibility(View.VISIBLE);
		listView.setHttpCallBack(new listHttpCallBack() {

			@Override
			public void initListView() {
				// TODO Auto-generated method stub
//				listView.setViewMode(false, false,
//						SwipeListView.SWIPE_ACTION_NONE, 0);
				listView.setMode(false, false);
				getAtCityData();
			}

			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub

			}

			@Override
			public void onLoadMore() {
				// TODO Auto-generated method stub

			}

		});
		topBar.setRightTextColor(getResources().getColor(R.color.color_note));

	}

	private void getAtCityData() {
		// TODO Auto-generated method stub
		new HttpReq_GetAtCity(new CallBack_City() {

			@Override
			public void onSuccess(ArrayList<T_UserPoint> listData) {
				// TODO Auto-generated method stub
				list = listData;
				adapter.setData(list);
				handlerExtend.onInitView();
			}

			@Override
			public void onFail() {
				pageView.setDefaultPage().setErrorText("暂无获取到同城用户哦")
						.setVisibility(View.VISIBLE);
			}

			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
			}

		});
	}

	HandlerExtend handlerExtend = new HandlerExtend(new handleCallBack() {

		@Override
		public void call_onRefresh() {
			// TODO Auto-generated method stub

		}

		@Override
		public void call_onInit() {
			// TODO Auto-generated method stub
			listView.setAdapter(adapter);
			pageView.setVisibility(View.GONE);
		}
	});

	@Override
	protected void onClickListener() {
		// TODO Auto-generated method stub
		topBar.setCallBack(new topBarCallBack() {
			@Override
			public void call_backBtnListener() {
				// TODO Auto-generated method stub
				super.call_backBtnListener();
				outFinish();
			}

			public void call_rightImgBtnListener() {
				// 跳转到功能选择框（转发文档/添加为新朋友）
				ViewSwitchUtils.in2TopIntent_result(ContactsAtCityPage.this,
						ContactsActionDialog.class, REQ_NEARBY_SELECT);
				// ViewSwitchUtils.in2TopIntent(context,
				// ContactsActionDialog.class);
			};

		});
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				try {
					position--;
					T_UserPoint bean = list.get(position);
					ViewSwitchUtils.in2TopIntent(context, UserIfoPage.class,
							bean.getUser_id(),Enum_IfRegister.REGIST.toString()
							,"",Enum_BackSytle.VETICAL.toString());
				} catch (Exception e) {
					// TODO: handle exception
					toast.setText("信息有误");
				}
			}
		});
		bottomBar.setCallBack(new callBack_Bar() {

			@Override
			public void call_FirstItem(int... position) {
				// 全选
				for (T_UserPoint item : list) {
					item.setIfSelect(1);
				}
				adapter.notifyDataSetChanged();
			}

			@Override
			public void call_SecondItem(int... position) {
				// 反选
				for (T_UserPoint item : list) {
					item.setIfSelect(item.getIfSelect() == 0 ? 1 : 0);
				}
				adapter.notifyDataSetChanged();
			}

			@Override
			public void call_ThirdItem(int... position) {
				// 取消
				for (T_UserPoint item : list) {
					item.setIfSelect(0);
				}
				adapter.notifyDataSetChanged();
			}

			@Override
			public void call_FourthItem(int... position) {

			}

			@Override
			public void call_FifthItem(int... position) {

			}
		}, 0);
		// 列表项中CheckBox选择触发
		adapter.setItemCallBack(new ItemCallBack() {

			@Override
			public void call_select(boolean ifCheck, int position) {
				// TODO Auto-generated method stub
				if (ifCheck) {
					list.get(position).setIfSelect(1);
					if (!selectItems.contains(list.get(position))) {
						selectItems.add(list.get(position));
					}
					return;
				}
				list.get(position).setIfSelect(0);
				if (selectItems.contains(list.get(position))) {
					selectItems.remove(list.get(position));
				}

			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case REQ_NEARBY_SELECT:
			switch (ContactsActionDialog.ACTION_NOTE) {
			case ContactsActionDialog.TRANS_RES:
				// 增加新朋友
				new HttpReq_AddContacts(new ACCallBack() {

					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						topBar.setTitle("添加...").setProVisibility(true);
					}

					@Override
					public void onFinally() {
						// TODO Auto-generated method stub
						topBar.setTitle("同城的人").setProVisibility(false)
								.setRightImgVisibility(true);
					}

					@Override
					public void add_Success() {
						// TODO Auto-generated method stub
//						toast.setText("添加成功");
						// 转发文档事件
						ViewSwitchUtils.in2TopIntent(context, ResourceSelectPage_Single.class,
								HttpReq_AddContacts.parseUid(selectItems) );
						// 开启同步通讯录服务
						ViewSwitchUtils.startService(context,
								SYNCRelationshipService.class);
					}

					@Override
					public void add_Fail() {
						// TODO Auto-generated method stub
						toast.setText("操作失败,数据或网络出状况 了");
					}
				}).setSource(Enum_ReqRelaSource.NEARBY)
						.setType(Enum_ReqRelaType.NEWFRIEND)
						.setBeUid(HttpReq_AddContacts.parseUid(selectItems))
						.httpRequest();
				
				break;
			case ContactsActionDialog.ADD_NEWFRIEND:
				// 增加新朋友
				new HttpReq_AddContacts(new ACCallBack() {

					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						topBar.setTitle("添加...").setProVisibility(true);
					}

					@Override
					public void onFinally() {
						// TODO Auto-generated method stub
						topBar.setTitle("同城的人").setProVisibility(false)
								.setRightImgVisibility(true);
					}

					@Override
					public void add_Success() {
						// TODO Auto-generated method stub
						toast.setText("添加成功");
						// 开启同步通讯录服务
						ViewSwitchUtils.startService(context,
								SYNCRelationshipService.class);
					}

					@Override
					public void add_Fail() {
						// TODO Auto-generated method stub
						toast.setText("添加失败");
					}
				}).setSource(Enum_ReqRelaSource.NEARBY)
						.setType(Enum_ReqRelaType.NEWFRIEND)
						.setBeUid(HttpReq_AddContacts.parseUid(selectItems))
						.httpRequest();
				break;
			default:
				break;
			}
			break;

		default:
			break;
		}
	}

	@Override
	protected void outFinish() {
		// TODO Auto-generated method stub
		finish();
		ViewSwitchUtils.finishOut2Right(context);
	}
}
