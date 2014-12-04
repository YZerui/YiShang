package com.yishang.B.module.d.BusinessUi;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupWindow.OnDismissListener;

import com.customview.callBack.popWinCallBack;
import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomPageView;
import com.customview.view.CustomPopWinView;
import com.customview.view.CustomTopbarView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ruifeng.yishang.R;
import com.thread.HandlerExtend;
import com.thread.HandlerExtend.handleCallBack;
import com.yishang.A.global.Enum.Enum_PageSource;
import com.yishang.A.global.Enum.Enum_ReceiverAction;
import com.yishang.A.global.Enum.com.Enum_ComType;
import com.yishang.A.global.Enum.constant.Enum_Param;
import com.yishang.A.global.baseClass.ListActivity;
import com.yishang.A.global.callBack.listHttpCallBack;
import com.yishang.A.global.constant.PARAMS;
import com.yishang.C.dao.daoModel.T_Company;
import com.yishang.D.service.dbRequest.DBReq_Company;
import com.yishang.D.service.dbRequest.DBReq_Company.CallBack;
import com.yishang.E.view.adapter.BusinessItemAdapter;
import com.yishang.E.view.swipelistview.BaseSwipeListViewListener;
import com.yishang.E.view.swipelistview.StickyListHeadersListView;
import com.yishang.E.view.swipelistview.SwipeListView;
import com.yishang.Z.utils.BroadcastUtil;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * 获取企业名单的列表
 * @author MM_Zerui
 *
 */
public class BusinessActivity extends ListActivity{
	private final static int COM_FILTER_ACTION=100;
	
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.listView)
	private StickyListHeadersListView listView;
	@ViewInject(R.id.pageView)
	private CustomPageView pageView;
	
//	private CustomPopWinView popWinView;
	private BusinessItemAdapter adapter;
	public static List<T_Company> list;
	
	private DBReq_Company dbReq;
	
	private NotifyReceiver notifyReceiver;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.business_page_layout);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
		registerReceiver();
	}
	private void registerReceiver() {
		// TODO Auto-generated method stub
		notifyReceiver=new NotifyReceiver();
		BroadcastUtil.regiReceiver(this, notifyReceiver, Enum_ReceiverAction.COMPANY.name());
	}
	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		
//		popWinView = new CustomPopWinView(context).setFirstItemText("关联企业")
//				.setSecondItemText("客户").setThirdItemText("供应商")
//				.setFourthItemText("申请中").setItemNum(4);
		dbReq=new DBReq_Company();
	
		listView.setHttpCallBack(new listHttpCallBack() {
			@Override
			public void initListView() {
				// TODO Auto-generated method stub
				list=new ArrayList<T_Company>();
				adapter = new BusinessItemAdapter(context);
//				listView.setViewMode(false, true, SwipeListView.SWIPE_ACTION_REVEAL, 0);
				listView.setMode(true, false);
				pageView.onProgressOnly().setVisibility(View.VISIBLE);
				handler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						dbReq.setCallBack(callBack).setComType(Enum_ComType.DEFAULT).getAll().onInit();
					}
				}, Enum_Param.TIME0FFSET_PAGELOAD.value());
			
			}
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLoadMore() {
				// TODO Auto-generated method stub
				dbReq.onLoad();
			}
		});
		
	}
	@Override
	protected void onClickListener() {
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg2) {
				// TODO Auto-generated method stub
				T_Company bean=list.get(position-1);
				ViewSwitchUtils.tab_in2LeftIntent(context, BusinessDetailPge.class,bean.getCom_id(),Enum_PageSource.Company.name());
			}
		});
		topBar.setCallBack(new topBarCallBack() {
			@Override
			public void call_rightTextBtnListener() {
				// TODO Auto-generated method stub
				super.call_rightTextBtnListener();
//				pageView.setShadowPage().setVisibility(View.VISIBLE);
				ViewSwitchUtils.tab_in2TopIntent_result(BusinessActivity.this,BusinessFilterDialog.class,COM_FILTER_ACTION);
			}
			@Override
			public void call_leftImgBtnListener() {
				// TODO Auto-generated method stub
				super.call_leftImgBtnListener();
				ViewSwitchUtils.in2TopIntent(context, BusinessSearchActivity.class);
			}
		});
//		popWinView.setOnDismissListener(new OnDismissListener() {
//			
//			@Override
//			public void onDismiss() {
//				// TODO Auto-generated method stub
//				pageView.setVisibility(View.INVISIBLE);
//			}
//		});
//		popWinView.setCallBack(new popWinCallBack() {
//			@Override
//			public void call_firstItemClick() {
//				// TODO Auto-generated method stub
//				super.call_firstItemClick();
//				popWinView.dismiss();
//				
//			}
//			@Override
//			public void call_secondItemClick() {
//				// TODO Auto-generated method stub
//				super.call_secondItemClick();
//				popWinView.dismiss();
//				
//			}
//			@Override
//			public void call_thirdItemClick() {
//				// TODO Auto-generated method stub
//				super.call_thirdItemClick();
//				popWinView.dismiss();
//				
//			}
//			@Override
//			public void call_fourthItemClick() {
//				// TODO Auto-generated method stub
//				super.call_fourthItemClick();
//				popWinView.dismiss();
//				
//			}
//		});
	}
	private CallBack callBack=new CallBack() {
		
		@Override
		public void onLoadMore(List<T_Company> datas) {
			// TODO Auto-generated method stub
			list.addAll(datas);
			handlerExtend.onInitView();
		}
		
		@Override
		public void onInit(List<T_Company> datas) {
			// TODO Auto-generated method stub
			list=datas;
			adapter.setData(list);
			handlerExtend.onInitView();
		}
		
		@Override
		public void onFinally() {
			// TODO Auto-generated method stub
			handlerExtend.onFinally();
		}
		
		@Override
		public void onFail() {
			// TODO Auto-generated method stub
			handlerExtend.onFail();
		}
	};
	private HandlerExtend handlerExtend=new HandlerExtend(new handleCallBack() {
		@Override
		public void call_onInit() {
			listView.setPullLoadEnable(true);
			listView.setAdapter(adapter);
			pageView.setVisibility(View.GONE);
		}
		@Override
		public void call_onRefresh() {
			adapter.notifyDataSetChanged();
			pageView.setVisibility(View.GONE);
		}
		public void call_onFail() {
			if(dbReq.isIfInit()){
				pageView.setDefaultPage()
				.setTextOnly("还没有相关企业信息哦")
				.setBottomLayoutVisible(false)
				.setVisibility(View.VISIBLE);
			}
			listView.setPullLoadEnable(false);
		
		};
		public void call_onFinally() {
			listView.stopLoadMore();
		};
	});
	private class NotifyReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if(intent.getAction().equals(Enum_ReceiverAction.COMPANY.name())){
				//更新企业列表
				dbReq.setComType(Enum_ComType.DEFAULT).onInit();
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
		unregisterReceiver(notifyReceiver);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case PARAMS.SEARCH_CALLBACK_NOTE:
			break;
		case COM_FILTER_ACTION:
			if(BusinessFilterDialog.enumType==Enum_ComType.DEFAULT){
				return;
			}
			pageView.onProgressOnly().setVisibility(View.VISIBLE);
			handler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					dbReq.setComType(BusinessFilterDialog.enumType).getAll().onInit();		
				}
			},Enum_Param.TIME0FFSET_PAGELOAD.value());
			
//			switch (BusinessFilterDialog.enumType) {
//			case COM_ALL:
//				dbReq.setComType(Enum_ComType.COM_ALL).onInit();
//				break;
//			case COM_RELA:
//				//关联企业
//				dbReq.setComType(Enum_ComType.COM_RELA).onInit();
//				break;
//			case COM_CLIENT:
//				//客户
//				dbReq.setComType(Enum_ComType.COM_CLIENT).onInit();
//				break;
//			case COM_SUPPLIER:
//				//供应商
//				dbReq.setComType(Enum_ComType.COM_SUPPLIER).onInit();
//				break;
//			case COM_RELA_ING:
//				//申请中
//				dbReq.setComType(Enum_ComType.COM_RELA_ING).onInit();
//				break;
//			default:
//				break;
//			}
//			break;
		default:
			break;
		}
	}
	
	
}
