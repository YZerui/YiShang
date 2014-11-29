package com.yishang.B.module.a.MsgUi;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomPageView;
import com.customview.view.CustomTopbarView;
import com.exception.utils.P;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ruifeng.yishang.R;
import com.thread.HandlerExtend;
import com.thread.HandlerExtend.handleCallBack;
import com.yishang.A.global.Enum.constant.Enum_Param;
import com.yishang.A.global.baseClass.SuperActivity;
import com.yishang.A.global.callBack.listHttpCallBack;
import com.yishang.C.dao.daoImpl.Dao_Msg;
import com.yishang.C.dao.daoImpl.Dao_MsgNewFri;
import com.yishang.C.dao.daoImpl.Dao_MsgSeq;
import com.yishang.C.dao.daoModel.T_MsgNewFir;
import com.yishang.D.service.dbRequest.DBReq_MsgNewFri;
import com.yishang.D.service.dbRequest.DBReq_MsgNewFri.CallBack_NewFri;
import com.yishang.E.view.adapter.MsgNewFriAdapter;
import com.yishang.E.view.swipelistview.BaseSwipeListViewListener;
import com.yishang.E.view.swipelistview.SwipeListView;
import com.yishang.E.view.swipelistview.XListView;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * 新朋友列表页面
 * @author MM_Zerui
 *
 */
public class MsgNewFriendPage extends SuperActivity{
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	
	@ViewInject(R.id.listView)
	private XListView listView;
	
	@ViewInject(R.id.pageView)
	private CustomPageView pageView;
	
	private DBReq_MsgNewFri dbReq;
	private MsgNewFriAdapter adapter;
	private List<T_MsgNewFir> listDatas;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.msg_page_newfriend_layout);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		listView.setHttpCallBack(new listHttpCallBack() {
			
			@Override
			public void initListView() {
				// TODO Auto-generated method stub
				listDatas=new ArrayList<T_MsgNewFir>();
				adapter=new MsgNewFriAdapter(context);
//				listView.setViewMode(false, true, SwipeListView.SWIPE_ACTION_NONE, 0);
				listView.setMode(true, false);
				handler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						dbReq=new DBReq_MsgNewFri(callBack).onInit();
					}
				},Enum_Param.TIMEOFFSET_LISTLOAD.value());
				
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
				T_MsgNewFir bean=listDatas.get(position-1);
				ViewSwitchUtils.in2LeftIntent(context,
						MsgReceivePage.class, bean.getMsg_new_sendId());
				// 未读数清除操作
				try {
					Dao_MsgNewFri.clearUnRead(bean.getMsg_new_sendId());
					// 通知列表
					listDatas.get(position - 1).setMsg_new_unReadNum(0);
					adapter.notifyDataSetChanged();
					
					//更新消息顺序表新朋友模块的未读数
					Dao_MsgSeq.updateNewFriURNum(Dao_MsgNewFri.getUnReadNum_All());
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					P.v("清除用户未读数失败");
				}
			}
			
		});
	}
	private CallBack_NewFri callBack=new CallBack_NewFri(){

		@Override
		public void onSuccess(List<T_MsgNewFir> datas) {
			// TODO Auto-generated method stub
			listDatas=datas;
			adapter.setDatas(listDatas);
			handlerExtend.onInitView();
			
		}

		@Override
		public void onLoad(List<T_MsgNewFir> datas) {
			// TODO Auto-generated method stub
			listDatas.addAll(datas);
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
			pageView.setDefaultPage()
			.setBottomLayoutVisible(false)
			.setTextOnly("没有相关记录")
			.setVisibility(View.VISIBLE);
		};
		public void call_onFinally() {
			listView.onLoadStop();
		};
	});
	public void finish() {
		super.finish();
		ViewSwitchUtils.finishOut2Right(context);
	};
}
