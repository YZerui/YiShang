package com.yishang.B.module.d.BusinessUi;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomTopbarView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ruifeng.yishang.R;
import com.thread.HandlerExtend;
import com.thread.HandlerExtend.handleCallBack;
import com.yishang.A.global.Enum.Enum_ListLimit;
import com.yishang.A.global.Enum.com.Enum_ComRela;
import com.yishang.A.global.Enum.push.Enum_PushSource;
import com.yishang.A.global.baseClass.SuperActivity;
import com.yishang.A.global.callBack.listHttpCallBack;
import com.yishang.B.module.d.BusinessEntity.Recv_business;
import com.yishang.D.service.httpRequest.HttpReq_GetRelateCompany;
import com.yishang.D.service.httpRequest.HttpReq_GetRelateCompany.CallBack_RelaCom;
import com.yishang.E.view.adapter.ComRelateItemAdapter;
import com.yishang.E.view.swipelistview.SwipeListView;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * 关联企业列表页
 * @接收 用户ID
 * @author MM_Zerui
 *
 */
public class BusinessRelatePage extends SuperActivity{
	private String uID;
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.listView)
	private SwipeListView listView;
	
	private List<Recv_business> listDatas;
	private ComRelateItemAdapter adapter;
	private HttpReq_GetRelateCompany httpReq;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.business_relate_page);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
		
	}
	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		uID=getIntent().getStringExtra("DATA0");
	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		topBar.setTitle("加载...").setProVisibility(true);
		listView.setHttpCallBack(new listHttpCallBack() {
			
			@Override
			public void initListView() {
				// TODO Auto-generated method stub
				listView.setViewMode(false, true, SwipeListView.SWIPE_ACTION_REVEAL, 0);
				listDatas=new ArrayList<Recv_business>();
				adapter=new ComRelateItemAdapter(context);
				httpReq=new HttpReq_GetRelateCompany(uID).setLimit( Enum_ListLimit.COM_RELATE.value());
				httpReq.setCallBack(callBack).setType(Enum_ComRela.CORRE_SUCCESS).onInit();
				
				
			}
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLoadMore() {
				// TODO Auto-generated method stub
				httpReq.onLoad();
			}
		
		});
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
		});
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				ViewSwitchUtils.in2LeftIntent(context, 
						BusinessDetailPge.class,
						listDatas.get(position-1).getCom_id(),
						Enum_PushSource.RELACOMLIST.name());
			}
		});
	}
	private CallBack_RelaCom callBack=new CallBack_RelaCom() {
		
		@Override
		public void onRefresh(ArrayList<Recv_business> list) {
			// TODO Auto-generated method stub
			listDatas=list;
			adapter.setData(list);
			handlerExtend.onInitView();
		}
		
		@Override
		public void onLoad(ArrayList<Recv_business> list) {
			// TODO Auto-generated method stub
			listDatas.addAll(list);
			handlerExtend.onRefreshView();
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
			// TODO Auto-generated method stub
			listView.setAdapter(adapter);
		}
		@Override
		public void call_onRefresh() {
			// TODO Auto-generated method stub
			adapter.notifyDataSetChanged();
		}
		
		
		public void call_onFail() {
			
		};
		public void call_onFinally() {
			listView.stopLoadMore();
			topBar.setTitle("用户关联企业").setProVisibility(false);
		};
	});
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		ViewSwitchUtils.finishOut2Bottom(context);
	}
	
}
