package com.yishang.B.module.c.ResourceUi;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomPageView;
import com.customview.view.CustomTopbarView;
import com.format.utils.DataValidate;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ruifeng.yishang.R;
import com.thread.HandlerExtend;
import com.thread.HandlerExtend.handleCallBack;
import com.yishang.A.global.Enum.Enum_ListLimit;
import com.yishang.A.global.baseClass.SuperActivity;
import com.yishang.A.global.callBack.listHttpCallBack;
import com.yishang.B.module.d.BusinessEntity.Recv_comBook;
import com.yishang.D.service.httpRequest.HttpReq_GetComBook;
import com.yishang.D.service.httpRequest.HttpReq_GetComBook.CallBack_Book;
import com.yishang.E.view.adapter.ComResourceItemAdapter;
import com.yishang.E.view.swipelistview.BaseSwipeListViewListener;
import com.yishang.E.view.swipelistview.SwipeListView;
import com.yishang.E.view.swipelistview.XListView;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * 显示企业文档的页面
 * 
 * @author MM_Zerui
 * 
 */
public class ResourceComPage extends SuperActivity {
	@ViewInject(R.id.listView)
	private XListView listView;
	@ViewInject(R.id.pageView)
	private CustomPageView pageView;
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;

	private ComResourceItemAdapter adapter;
	private HttpReq_GetComBook httpReq;
	private ArrayList<Recv_comBook> list;
	private String cID;
	private String cName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.resource_page_company);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);

	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		cID = getIntent().getStringExtra("DATA0");
		cName = getIntent().getStringExtra("DATA1");
	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		topBar.setTitle(cName);
		httpReq = new HttpReq_GetComBook(cID, Enum_ListLimit.RESOURCE.value());
		pageView.setBottomLayoutVisible(false).setTextOnly("加载...")
				.setVisibility(View.VISIBLE);
		httpReq.setCallBack(callBack);
		listView.setHttpCallBack(new listHttpCallBack() {

			@Override
			public void initListView() {
				// TODO Auto-generated method stub
				adapter = new ComResourceItemAdapter(context);
				list = new ArrayList<Recv_comBook>();
				// listView.setViewMode(false, true,
				// SwipeListView.SWIPE_ACTION_REVEAL, 0);
				listView.setMode(true, false);
				httpReq.onRefresh();
			}

			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				httpReq.onRefresh();
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
		// listView.setOn
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				// toast.setText("项点击");
				Recv_comBook recvBean = list.get(position - 1);

				// 前往资源详情页
				ViewSwitchUtils.in2LeftIntent(context,
						ResourceDetailPage.class, recvBean.getBook_name(),
						recvBean.getBook_url(), recvBean.getBook_id(),
						recvBean.getBook_com());
			}
		});

		// listView.setSwipeListViewListener(new BaseSwipeListViewListener() {
		//
		// @Override
		// public void onClickFrontView(int position) {
		// // TODO Auto-generated method stub
		// super.onClickFrontView(position);
		// toast.setText("项顶部点击");
		// Recv_comBook recvBean=list.get(position-1);
		// //前往资源详情页
		// ViewSwitchUtils.in2LeftIntent(context,
		// ResourceDetailPage.class,recvBean.getBook_name()
		// ,recvBean.getBook_url());
		// }
		// });
	}

	private CallBack_Book callBack = new CallBack_Book() {

		@Override
		public void onRefresh(ArrayList<Recv_comBook> datas) {
			// TODO Auto-generated method stub
			list = datas;
			adapter.setData(list);
			handlerExtend.onInitView();
			
		}

		@Override
		public void onLoad(ArrayList<Recv_comBook> list) {
			// TODO Auto-generated method stub
			list.addAll(list);
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
				public void call_onInit() {
					// TODO Auto-generated method stub
					pageView.setVisibility(View.GONE);
					listView.setAdapter(adapter);
				}

				@Override
				public void call_onRefresh() {
					// TODO Auto-generated method stub
					adapter.notifyDataSetChanged();
					pageView.setVisibility(View.GONE);
				}

			
				public void call_onFail() {
					if (httpReq.isIfInit()) {
						pageView.
							setBottomLayoutVisible(false).
							setTextOnly("暂无相关资源").
							setVisibility(View.VISIBLE);
					}
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
