package com.yishang.B.module.a.MsgUi;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomBarView.callBack_Bar;
import com.customview.view.CustomTopbarView;
import com.exception.utils.P;
import com.format.utils.DataValidate;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ruifeng.yishang.R;
import com.thread.HandlerExtend;
import com.thread.HandlerExtend.handleCallBack;
import com.thread.callBack.runCallBack;
import com.thread.RunnableService;
import com.yishang.A.global.Enum.Enum_PageSource;
import com.yishang.A.global.Enum.constant.Enum_Param;
import com.yishang.A.global.Enum.push.Enum_PushType;
import com.yishang.A.global.baseClass.SharePage;
import com.yishang.A.global.baseClass.SuperActivity;
import com.yishang.A.global.callBack.listHttpCallBack;
import com.yishang.B.module.c.ResourceUi.ResourceDetailPage;
import com.yishang.B.module.d.BusinessUi.BusinessDetailPge;
import com.yishang.B.module.e.SelfUi.UserIfoPage;
import com.yishang.C.dao.daoImpl.Dao_Msg;
import com.yishang.C.dao.daoImpl.Dao_Relationship;
import com.yishang.C.dao.daoImpl.Dao_Resource;
import com.yishang.C.dao.daoModel.T_Msg;
import com.yishang.C.dao.daoModel.T_Relationships;
import com.yishang.C.dao.daoModel.T_Resource;
import com.yishang.D.service.dbRequest.DBReq_MsgReceiver;
import com.yishang.D.service.dbRequest.DBReq_MsgReceiver.CallBack_MsgRecv;
import com.yishang.E.view.adapter.MsgReceivePageAdapter;
import com.yishang.E.view.adapter.MsgSystemPageAdapter;
import com.yishang.E.view.swipelistview.SwipeListView;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * 用于显示关于用户事件的列表页面
 * 
 * @接收 用户UID 页面来源
 * @author MM_Zerui
 * 
 */
public class MsgReceivePage extends SuperActivity {
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.listView)
	private SwipeListView listView;

	private List<T_Msg> list;
	private MsgReceivePageAdapter adapter;
	private String uID;
	private DBReq_MsgReceiver dbReq;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.msg_page_receive);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		uID = getIntent().getStringExtra("DATA0");
		SOURCE_PAGE = getIntent().getStringExtra("DATA1");
	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		adapter = new MsgReceivePageAdapter(context, callBackBar);
		list = new ArrayList<T_Msg>();
		if (DataValidate.checkDataValid(SOURCE_PAGE)
				&& SOURCE_PAGE.equals(Enum_PageSource.UserIfoPage.name())) {
			topBar.setBackText("用户详情");
		}
		if (!DataValidate.checkDataValid(uID)) {

			return;
		}

		dbReq = new DBReq_MsgReceiver(uID, callBack);
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
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				T_Msg msg = list.get(position - 1);
				// 跳转到资源详情页
				ViewSwitchUtils.in2LeftIntent(context,
						ResourceDetailPage.class, msg.getMsg_comName(), "",
						msg.getMsg_resId(), msg.getMsg_comId());
			}
		});
	}

	private listHttpCallBack listCallBack = new listHttpCallBack() {

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
			}, Enum_Param.TIME0FFSET_PAGELOAD.value());
			

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
	private CallBack_MsgRecv callBack = new CallBack_MsgRecv() {

		@Override
		public void callInit(List<T_Msg> datas) {
			// TODO Auto-generated method stub
			list.clear();
			list = datas;
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
	private callBack_Bar callBackBar = new callBack_Bar() {

		@Override
		public void call_FirstItem(int... position) {
			// TODO Auto-generated method stub
			// 转发操作
			// T_Msg bean=list.get(position[0]);
			// ViewSwitchUtils.in2TopIntent(context,SharePage.class,bean.getMsg_resId(),bean.getMsg_uId());
			try {
				T_Msg bean = list.get(position[0]);
				
				Enum_PushType enumType=Enum_PushType.valueOf(bean.getMsg_type());
				switch (enumType) {
				case RES_INTEREST:
					//感兴趣通知
					//查看文档
					T_Resource rBean=Dao_Resource.getResource(bean.getMsg_resId());
					ViewSwitchUtils.in2TopIntent(context,ResourceDetailPage.class,
							rBean.getCom_name(),rBean.getBook_url(),rBean.getBook_id(),rBean.getCom_id()
							,Enum_PageSource.MsgReceivePage.name());
					break;
				case RES_RECEV:
					//收到文档
					// 查看转发人
					T_Relationships tBean = Dao_Relationship.getByID(bean
							.getMsg_sendId());
					ViewSwitchUtils.in2TopIntent(context, UserIfoPage.class,
							tBean.getRela_id(),
							String.valueOf(tBean.getRela_register()),
							tBean.getRela_phone(),
							Enum_PageSource.MsgReceivePage.name());
					break;

				default:
					break;
				}
				
			
			} catch (Exception e) {
				// TODO: handle exception
				toast.setText("信息出错");
				P.v("查看文档:"+e.getMessage());
			}
		}

		@Override
		public void call_SecondItem(int... position) {

			// 查看企业代表
			try {
				T_Msg bean = list.get(position[0]);
				Enum_PushType enumType=Enum_PushType.valueOf(bean.getMsg_type());
				switch (enumType) {
				case RES_INTEREST:
					//查看客户（感兴趣的人）
					ViewSwitchUtils.in2TopIntent(context, UserIfoPage.class,
							bean.getMsg_sendId(), "", "",
							Enum_PageSource.MsgReceivePage.name());
					break;
				case RES_RECEV:
					//查看企业代表（原始转发人）
					ViewSwitchUtils.in2TopIntent(context, UserIfoPage.class,
							bean.getMsg_uId(), "", "",
							Enum_PageSource.MsgReceivePage.name());
					break;
				default:
					break;
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				toast.setText("信息有误");
				P.v(e.getMessage());
			}
		}

		@Override
		public void call_ThirdItem(int... position) {
			// TODO Auto-generated method stub
			toast.setText("点击了:" + position[0]);
		}

		@Override
		public void call_FourthItem(int... position) {
			// TODO Auto-generated method stub

		}

		@Override
		public void call_FifthItem(int... position) {
			// TODO Auto-generated method stub

		}
	};
	HandlerExtend handlerExtend = new HandlerExtend(new handleCallBack() {

		@Override
		public void call_onRefresh() {
			adapter.addAll(list);
		}

		@Override
		public void call_onInit() {
			// 以发送者姓名作为标题
			topBar.setTitle(list.get(0).getMsg_sendName());
			listView.setAdapter(adapter);
		}

		public void call_onFail() {

		};

		public void call_onFinally() {
			listView.stopLoadMore();
		};
	});

	public void finish() {
		super.finish();
		ViewSwitchUtils.finishOut2Right(context);
	};

}
