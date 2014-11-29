package com.yishang.B.module.b.ContactsUI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.LinearLayout;

import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomBarView;
import com.customview.view.CustomBarView.callBack_Bar;
import com.customview.view.CustomBtnView;
import com.customview.view.CustomPageView;
import com.customview.view.CustomTopbarView;
import com.exception.utils.P;
import com.format.utils.DataValidate;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.ruifeng.yishang.R;
import com.thread.HandlerExtend;
import com.thread.HandlerExtend.handleCallBack;
import com.yishang.A.global.Enum.constant.Enum_Param;
import com.yishang.A.global.Enum.db.Enum_RelaType;
import com.yishang.A.global.baseClass.SuperActivity;
import com.yishang.A.global.callBack.listHttpCallBack;
import com.yishang.A.global.constant.CONSTANT;
import com.yishang.A.global.constant.PARAMS;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.C.dao.daoModel.T_Relationships;
import com.yishang.D.service.TransUsersService;
import com.yishang.D.service.TransUsersService.CallBack;
import com.yishang.D.service.dbRequest.DBReq_Relationship;
import com.yishang.D.service.dbRequest.DBReq_Relationship.CallBack_Rela;
import com.yishang.D.service.httpRequest.HttpReq_GetTransUrl;
import com.yishang.D.service.httpRequest.HttpReq_GetTransUrl.CallBack_TransUrl;
import com.yishang.E.view.MyDialog;
import com.yishang.E.view.adapter.ContactsSelectAdapter;
import com.yishang.E.view.adapter.ContactsSelectAdapter.ItemCallBack;
import com.yishang.E.view.swipelistview.SwipeListView;
import com.yishang.E.view.swipelistview.XListView;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * 选择联系人的页面
 * @接收数据 原始转发人ID
 * @接收数据 文档ID
 * @author MM_Zerui
 *
 */
public class ContactsSelectPage extends SuperActivity{
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.searchBtn)
	private CustomBtnView searchBtn;
	@ViewInject(R.id.listView)
	private XListView listView;
	@ViewInject(R.id.bottomBar)
	private CustomBarView bottomBar;
	@ViewInject(R.id.overalLayout)
	private LinearLayout overalLayout;
	@ViewInject(R.id.pageView)
	private CustomPageView pageView;
	
	private ContactsSelectAdapter adapter;
	private DBReq_Relationship dbReq;
	public static List<T_Relationships> listDatas;
	public static List<T_Relationships> selectItems;
	private String sourceId;
	private String bId;
	private String transUrl;
	private float anim_y;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.contacts_select_page);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
		
	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		bId=getIntent().getStringExtra("DATA0");
		sourceId=getIntent().getStringExtra("DATA1");
		transUrl=getIntent().getStringExtra("DATA2");
		if(!DataValidate.checkDataValid(sourceId)){
			sourceId=Dao_Self.getInstance().getUser_id();
		}
		if(!DataValidate.checkDataValid(transUrl)){
			try{
			new HttpReq_GetTransUrl(new CallBack_TransUrl() {

				@Override
				public void onSuccess(String url) {
					// TODO Auto-generated method stub
					transUrl = url;
					P.v("获得转发URL:" + url);
				}

				@Override
				public void onFinally() {
					// TODO Auto-generated method stub

				}

				@Override
				public void onFail() {
					// TODO Auto-generated method stub
					P.v("获得转发URL失败");
				}
			}).setIniId(sourceId).setResId(bId)
					.setUserId(Dao_Self.getInstance().getUser_id())
						.httpMethod();
			} catch (Exception e) {
						// TODO: handle exception
					}
		}
	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		dbReq=new DBReq_Relationship();
		dbReq.setCallBack(callBack, Enum_RelaType.DEFAULT);
		listView.setHttpCallBack(new listHttpCallBack() {
			@Override
			public void initListView() {
				// TODO Auto-generated method stub
//				listView.setViewMode(false, false, SwipeListView.SWIPE_ACTION_REVEAL, 0);
				listView.setMode(false, false);
				listDatas=new ArrayList<T_Relationships>();
				selectItems=new ArrayList<T_Relationships>();
				adapter=new ContactsSelectAdapter(context);
//				pageView.onProgressOnly().setVisibility(View.VISIBLE);
				pageView.setBottomLayoutVisible(false).setTextOnly("载入中...").setVisibility(View.VISIBLE);
				handler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						dbReq.getAll().callInit();
					}
				}, Enum_Param.TIME0FFSET_PAGELOAD.value());
				
				
			}
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				dbReq.callInit();
			}
			
			@Override
			public void onLoadMore() {
				// TODO Auto-generated method stub
				dbReq.callLoad();
			}
			
		
		});
	}
	private CallBack_Rela callBack=new CallBack_Rela() {
		
		@Override
		public void onInit(List<T_Relationships> list) {
			// TODO Auto-generated method stub
			listDatas=list;
			adapter.setData(listDatas);
			handlerExtend.onInitView();
		}
		@Override
		public void onLoad(List<T_Relationships> list) {
			// TODO Auto-generated method stub
			listDatas.addAll(list);
			handlerExtend.onRefreshView();
		}
	
		
		@Override
		public void onFail() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onFinally() {
			// TODO Auto-generated method stub
			handlerExtend.onFinally();
		}
	};
	private HandlerExtend handlerExtend=new HandlerExtend(new handleCallBack() {

		@Override
		public void call_onInit() {
			// TODO Auto-generated method stub
			listView.setPullLoadEnable(true);
			listView.setAdapter(adapter);
			pageView.setVisibility(View.INVISIBLE);
		}
		@Override
		public void call_onRefresh() {
			// TODO Auto-generated method stub
			adapter.notifyDataSetChanged();
			pageView.setVisibility(View.INVISIBLE);
		}
		
		public void call_onFail() {
			listView.setPullLoadEnable(false);
			if(dbReq.isIfInit()){
				pageView.setTextOnly("暂无人脉信息哦").setVisibility(View.VISIBLE);
			}
		};
		public void call_onFinally() {
			listView.stopLoadMore();
		};
	});
	/**
	 * 搜索联系人页面
	 * @param v
	 */
	@OnClick(R.id.searchBtn)
	private void searchClick(View v){
		anim_y=searchBtn.getHeight();
		TranslateAnimation animation = new TranslateAnimation(0, 0, 0, -anim_y);
		animation.setDuration(CONSTANT.SEARCH_BOX_ANIM);
		animation.setFillAfter(true);
		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				Intent intent = new Intent();
				intent.setClass(context,ContactsSearchActivity_Contacts.class);
				startActivityForResult(intent,PARAMS.SEARCH_CALLBACK_NOTE);
				overridePendingTransition(R.anim.search_box_animation_2,R.anim.search_box_animation_1);
			}
		});
		overalLayout.startAnimation(animation);
	}
	@Override
	protected void onClickListener() {
		// TODO Auto-generated method stub
		topBar.setCallBack(new topBarCallBack() {
			@Override
			public void call_backBtnListener() {
				// TODO Auto-generated method stub
				super.call_backBtnListener();
				finish();
			}
			@Override
			public void call_rightTextBtnListener() {
				// TODO Auto-generated method stub
				super.call_rightTextBtnListener();
				//完成操作，调用转发接口
				if(selectItems.size()<=0){
					toast.setText("请选择目标客户后再转发");
					return;
				}
				topBar.setTitle("转发...").setProVisibility(true);
				new TransUsersService(bId, sourceId, transUrl,selectItems, sendResCallBack);
			}
		});
		//选择CheckBox的回调
		adapter.setItemCallBack(new ItemCallBack() {

			@Override
			public void call_select(boolean ifCheck, int position) {
				// TODO Auto-generated method stub
				listDatas.get(position).setItem_select(ifCheck);
				T_Relationships item=listDatas.get(position);
				if(ifCheck){
					if(!selectItems.contains(item)){
						selectItems.add(0, item);
					}
				}else {
					if(selectItems.contains(item)){
						selectItems.remove(item);
					}
				}
			}
		});
		//底部栏的点击事件
		bottomBar.setCallBack(new callBack_Bar() {
			
			@Override
			public void call_FirstItem(int... position) {
				// TODO Auto-generated method stub
				//全选
				for(T_Relationships item:listDatas){
					item.setItem_select(true);
				}
				adapter.notifyDataSetChanged();
			}
			
			@Override
			public void call_SecondItem(int... position) {
				// TODO Auto-generated method stub
				//反选
				for(T_Relationships item:listDatas){
					item.setItem_select(!item.isItem_select());
				}
				adapter.notifyDataSetChanged();
			}
			@Override
			public void call_ThirdItem(int... position) {
				// TODO Auto-generated method stub
				//新朋友
//				ViewSwitchUtils.in2TopIntent(context, ContactsSelectPage_newFriend.class);
				//取消
				for(T_Relationships item:listDatas){
					item.setItem_select(false);
				}
				adapter.notifyDataSetChanged();
			}
			
			@Override
			public void call_FourthItem(int... position) {
				// TODO Auto-generated method stub
//				//取消
//				for(T_Relationships item:listDatas){
//					item.setItem_select(false);
//				}
//				adapter.notifyDataSetChanged();
			}
		
			
			@Override
			public void call_FifthItem(int... position) {
				// TODO Auto-generated method stub
				
			}
		}, 0);
	}
	private CallBack sendResCallBack=new CallBack() {
		
		@Override
		public void onSuccess() {
			//转发成功
//			new MyDialog(context)
//				.withTitle("提示")
//				.withMessage("转发成功")
//				.withSingleBtnLayout("知道了").show();
			toast.setText("转发成功");
			finish();
		}
		
		@Override
		public void onFail() {
			//转发失败
			new MyDialog(context)
				.withTitle("提示")
				.withMessage("转发失败了,数据有误或网络异常")
				.withSingleBtnLayout("知道了").show();
		}
		
		@Override
		public void onEnd() {
			//结束
			topBar.setTitle("选择好友转发").setProVisibility(false).setRighTextVisibility(true);
		}
	};
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case PARAMS.SEARCH_CALLBACK_NOTE:
			handler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					TranslateAnimation animation = new TranslateAnimation(0, 0, -anim_y, 0);
					animation.setFillAfter(true);
					animation.setDuration(CONSTANT.SEARCH_BOX_ANIM);
					overalLayout.startAnimation(animation);
					//刷新选择列表
					handlerExtend.onRefreshView();
				}
			},Enum_Param.TIMEOFFSET_BOXDOWN.value());
		
			break;

		default:
			break;
		}
	}
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		ViewSwitchUtils.finishOut2Bottom(context);
	}
}
