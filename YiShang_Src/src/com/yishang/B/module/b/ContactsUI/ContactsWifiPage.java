package com.yishang.B.module.b.ContactsUI;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomBarView;
import com.customview.view.CustomBarView.callBack_Bar;
import com.customview.view.CustomTopbarView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
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
import com.yishang.B.module.b.ContactsEntity.Recv_wifiUser;
import com.yishang.B.module.c.ResourceUi.ResourceSelectPage_Single;
import com.yishang.B.module.e.SelfUi.UserIfoPage;
import com.yishang.D.service.httpRequest.HttpReq_AddContacts;
import com.yishang.D.service.httpRequest.HttpReq_GetLocalWifiUser;
import com.yishang.D.service.httpRequest.HttpReq_AddContacts.ACCallBack;
import com.yishang.D.service.httpRequest.HttpReq_GetLocalWifiUser.CallBack_WifiUser;
import com.yishang.D.service.sync.SYNCRelationshipService;
import com.yishang.E.view.adapter.ContactsWifiAdapter;
import com.yishang.E.view.adapter.ContactsWifiAdapter.ItemCallBack;
import com.yishang.E.view.swipelistview.SwipeListView;
import com.yishang.E.view.swipelistview.XListView;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * 显示同一wifi下的朋友
 * 
 * @author MM_Zerui
 * 
 */
public class ContactsWifiPage extends ListActivity {
	private final static int REQ_NEARBY_SELECT = 1;

	@ViewInject(R.id.listView)
	private XListView listView;
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.bottomBar)
	private CustomBarView bottomBar;

	private ContactsWifiAdapter adapter;
	private ArrayList<Recv_wifiUser> list;
	private ArrayList<Recv_wifiUser> selectItems;
	private HttpReq_GetLocalWifiUser httpReq;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.contacts_wifi_page_layout);
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
		topBar.setTitle("同一WIFI下");
		adapter = new ContactsWifiAdapter(context);
		selectItems = new ArrayList<Recv_wifiUser>();
		httpReq = new HttpReq_GetLocalWifiUser();
		listView.setHttpCallBack(new listHttpCallBack() {

			@Override
			public void initListView() {
				// TODO Auto-generated method stub
//				listView.setViewMode(true, true,
//						SwipeListView.SWIPE_ACTION_REVEAL, 0);
				listView.setMode(false, false);
				list = new ArrayList<Recv_wifiUser>();
				httpReq.setCallBack(callBack).onInit();
			}

			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				httpReq.onInit();
			}

			@Override
			public void onLoadMore() {
				// TODO Auto-generated method stub
				httpReq.onLoad();
			}

		});
		topBar.setRightTextColor(getResources().getColor(R.color.color_note));

	}

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

			@Override
			public void call_rightImgBtnListener() {
				// TODO Auto-generated method stub
				super.call_rightImgBtnListener();
				// 跳转到功能选择框（转发文档/添加为新朋友）
				// ViewSwitchUtils.in2TopIntent(context,
				// ContactsActionDialog.class);
				// 跳转到功能选择框（转发文档/添加为新朋友）
				ViewSwitchUtils.in2TopIntent_result(ContactsWifiPage.this,
						ContactsActionDialog.class, REQ_NEARBY_SELECT);
			}
		});
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				try {
					position--;
					Recv_wifiUser bean = list.get(position);
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
				// TODO Auto-generated method stub
				// 全选
				for(Recv_wifiUser item:list){
					item.setSelect(true);
				}
				adapter.notifyDataSetChanged();

			}

			@Override
			public void call_SecondItem(int... position) {
				// 反选
				for(Recv_wifiUser item:list){
					item.setSelect(!item.isSelect());
				}
				adapter.notifyDataSetChanged();
			}

			@Override
			public void call_ThirdItem(int... position) {
				// 取消
				for(Recv_wifiUser item:list){
					item.setSelect(false);
				}
				adapter.notifyDataSetChanged();
			}

			@Override
			public void call_FourthItem(int... position) {

			}

			@Override
			public void call_FifthItem(int... position) {
				// TODO Auto-generated method stub

			}
		}, 0);
		// 列表项中CheckBox选择触发
		adapter.setItemCallBack(new ItemCallBack() {

			@Override
			public void call_select(boolean ifCheck, int position) {
				// TODO Auto-generated method stub
				list.get(position).setSelect(ifCheck);
				if (ifCheck) {
					if (!selectItems.contains(list.get(position))) {
						selectItems.add(list.get(position));
					}
					return;
				}
				if (selectItems.contains(list.get(position))) {
					selectItems.remove(list.get(position));
				}

			}
		});
	}

	private CallBack_WifiUser callBack = new CallBack_WifiUser() {

		@Override
		public void onSuccess(ArrayList<Recv_wifiUser> datas) {
			// TODO Auto-generated method stub
			list = datas;
			adapter.setData(list);
			handlerExtend.onInitView();
		}

		@Override
		public void onLoad(ArrayList<Recv_wifiUser> datas) {
			// TODO Auto-generated method stub
			list.addAll(datas);
			handlerExtend.onRefreshView();
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
				public void call_onRefresh() {
					// TODO Auto-generated method stub
					adapter.notifyDataSetChanged();
				}

				@Override
				public void call_onInit() {
					// TODO Auto-generated method stub
					listView.setAdapter(adapter);

				}

				public void call_onFail() {

				};

				public void call_onFinally() {
					listView.onLoadStop();
				};
			});
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case REQ_NEARBY_SELECT:
			switch (ContactsActionDialog.ACTION_NOTE) {
			case ContactsActionDialog.TRANS_RES:
				//增加新朋友
				new HttpReq_AddContacts(new ACCallBack() {
					
					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						topBar.setTitle("添加...").setProVisibility(true);
					}
					
					@Override
					public void onFinally() {
						// TODO Auto-generated method stub
						topBar.setTitle("同一WIFI下").setProVisibility(false).setRightImgVisibility(true);
					}
					
					@Override
					public void add_Success() {
						// TODO Auto-generated method stub
//						toast.setText("添加成功");
						//转发文档事件
						ViewSwitchUtils.in2TopIntent(context, ResourceSelectPage_Single.class,
								HttpReq_AddContacts.parseUid_Wifi(selectItems) );
						//开启同步通讯录服务
						ViewSwitchUtils.startService(context,SYNCRelationshipService.class);
					}
					
					@Override
					public void add_Fail() {
						// TODO Auto-generated method stub
						toast.setText("操作失败,数据或网络出状况 了");
					}
				}).setSource(Enum_ReqRelaSource.WIFI)
				.setType(Enum_ReqRelaType.NEWFRIEND)
				.setBeUid(HttpReq_AddContacts.parseUid_Wifi(selectItems))
				.httpRequest();
				
				break;
			case ContactsActionDialog.ADD_NEWFRIEND:
				//增加新朋友
				new HttpReq_AddContacts(new ACCallBack() {
					
					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						topBar.setTitle("添加...").setProVisibility(true);
					}
					
					@Override
					public void onFinally() {
						// TODO Auto-generated method stub
						topBar.setTitle("同一WIFI下").setProVisibility(false).setRightImgVisibility(true);
					}
					
					@Override
					public void add_Success() {
						// TODO Auto-generated method stub
						toast.setText("添加成功");
						//开启同步通讯录服务
						ViewSwitchUtils.startService(context,SYNCRelationshipService.class);
					}
					
					@Override
					public void add_Fail() {
						// TODO Auto-generated method stub
						toast.setText("添加失败");
					}
				}).setSource(Enum_ReqRelaSource.WIFI)
				.setType(Enum_ReqRelaType.NEWFRIEND)
				.setBeUid(HttpReq_AddContacts.parseUid_Wifi(selectItems))
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
