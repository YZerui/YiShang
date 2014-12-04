	package com.yishang.B.module.a.MsgUi;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomTopbarView;
import com.format.utils.DataValidate;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ruifeng.yishang.R;
import com.thread.HandlerExtend;
import com.thread.HandlerExtend.handleCallBack;
import com.thread.callBack.runCallBack;
import com.thread.RunnableService;
import com.yishang.A.global.Enum.constant.Enum_Param;
import com.yishang.A.global.baseClass.SuperActivity;
import com.yishang.A.global.callBack.listHttpCallBack;
import com.yishang.C.dao.daoImpl.Dao_Msg;
import com.yishang.C.dao.daoModel.T_Msg;
import com.yishang.D.service.dbRequest.DBReq_MsgCompany;
import com.yishang.D.service.dbRequest.DBReq_MsgCompany.CallBack_MsgCom;
import com.yishang.E.view.adapter.MsgCompanyPageAdapter;
import com.yishang.E.view.adapter.MsgSystemPageAdapter;
import com.yishang.E.view.swipelistview.SwipeListView;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * 用于显示企业通知的列表页面
 * @author MM_Zerui
 *
 */
public class MsgCompanyPage extends SuperActivity{
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.listView)
	private SwipeListView listView;
	
	private List<T_Msg> list;
	private MsgCompanyPageAdapter adapter;
	private String comID;
	private DBReq_MsgCompany dbReq;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.msg_page_company);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
		
	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		comID=getIntent().getStringExtra("DATA0");
	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		adapter=new MsgCompanyPageAdapter(context);
		list=new ArrayList<T_Msg>();
		
		if(!DataValidate.checkDataValid(comID)){
			handlerExtend.onFail();
			return;
		}
		dbReq=new DBReq_MsgCompany(comID,callBack);
		listView.setHttpCallBack(listCallBack);
		
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
	}

	private listHttpCallBack listCallBack=new listHttpCallBack() {
		
		@Override
		public void initListView() {
			// TODO Auto-generated method stub
			listView.setViewMode(false, true, SwipeListView.SWIPE_MODE_NONE, 0);
			handler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					dbReq.onInit();
				}
			}, Enum_Param.TIMEOFFSET_LISTLOAD.value());
			
		}
		@Override
		public void onRefresh() {
			// TODO Auto-generated method stub
			dbReq.onInit();
		}
		
		@Override
		public void onLoadMore() {
			// TODO Auto-generated method stub
			dbReq.onLoad();
		}
	};
	private CallBack_MsgCom callBack=new CallBack_MsgCom() {
		
		@Override
		public void callInit(List<T_Msg> datas) {
			// TODO Auto-generated method stub
			list.clear();
			list=datas;
			adapter.setDatas(list);
			handlerExtend.onInitView();
		}
		@Override
		public void callLoad(List<T_Msg> datas) {
			// TODO Auto-generated method stub
			list.addAll(datas);
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
	HandlerExtend handlerExtend=new HandlerExtend(new handleCallBack() {
		
		@Override
		public void call_onRefresh() {
			adapter.addAll(list);
			
			listView.onLoadStop();
		}
		
		@Override
		public void call_onInit() {
			// TODO Auto-generated method stub
			//以企业名作为标题
			topBar.setTitle(list.get(0).getMsg_comName());
			listView.setAdapter(adapter);
			listView.onLoadStop();
		}
		public void call_onFail() {
			
		}
		public void call_onFinally() {
			listView.stopLoadMore();
		};
	});
	public void finish() {
		super.finish();
		ViewSwitchUtils.finishOut2Right(context);
	};

}
