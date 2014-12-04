package com.yishang.B.module.a.MsgUi;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomTopbarView;
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
import com.yishang.C.dao.daoImpl.Dao_MsgSeq;
import com.yishang.C.dao.daoModel.T_Msg;
import com.yishang.D.service.dbRequest.DBReq_MsgSystem;
import com.yishang.D.service.dbRequest.DBReq_MsgSystem.CallBack_MsgSys;
import com.yishang.E.view.adapter.MsgSystemPageAdapter;
import com.yishang.E.view.swipelistview.SwipeListView;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * 用于显示系统通知的列表页面
 * @备注 每次进入都将对系统通知的未读数消0
 * @author MM_Zerui
 *
 */
public class MsgSystemPage extends SuperActivity{
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.listView)
	private SwipeListView listView;
	
	private List<T_Msg> list;
	private MsgSystemPageAdapter adapter;
	private DBReq_MsgSystem dbReq;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.msg_page_system);
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
		adapter=new MsgSystemPageAdapter(context);
		list=new ArrayList<T_Msg>();
		dbReq=new DBReq_MsgSystem(callBack);
		listView.setHttpCallBack(listCallBack);
		
		//清除未读数
		try {
			Dao_MsgSeq.clearUnRead_SYSTEM();
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			
		}
		
		@Override
		public void onLoadMore() {
			// TODO Auto-generated method stub
			dbReq.onLoad();
		}
	};
	private CallBack_MsgSys callBack=new CallBack_MsgSys() {
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
			adapter.notifyDataSetChanged();
		}
		
		@Override
		public void call_onInit() {
			// TODO Auto-generated method stub
			listView.setAdapter(adapter);
		}
		public void call_onFail() {
			//提示没有任何消息
		};
		public void call_onFinally() {
			//停止刷新
			listView.stopLoadMore();
		};
	});
	public void finish() {
		super.finish();
		ViewSwitchUtils.finishOut2Right(context);
	};

}
