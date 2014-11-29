package com.yishang.B.module.b.ContactsUI;

import java.util.ArrayList;
import java.util.List;

import android.app.PendingIntent.OnFinished;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.ruifeng.yishang.R;
import com.yishang.A.global.baseClass.SuperActivity;
import com.yishang.C.dao.daoImpl.Dao_Contacts;
import com.yishang.C.dao.daoModel.T_Contacts;
import com.yishang.E.view.SideBar;
import com.yishang.E.view.SideBar.OnTouchingLetterChangedListener;
import com.yishang.E.view.adapter.ContactsLocalSortAdapter;
import com.yishang.E.view.swipelistview.SwipeListView;
import com.yishang.Z.utils.ThreadPool;
import com.yishang.Z.utils.ViewShowUtils;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * 显示本地通讯录成员的页面
 * 
 * @author MM_Zerui
 * 
 */
public class ContactsLocalPage extends SuperActivity implements OnItemClickListener{
	private final static int CONTACT_OBT_SUCCESS=1001;
	
	@ViewInject(R.id.listView)
	private SwipeListView listView;
	@ViewInject(R.id.backLayout)
	private RelativeLayout backLayout;
	@ViewInject(R.id.sidebar)
	private SideBar sideBar;
	@ViewInject(R.id.pingYinDialog)
	private TextView pingYinDialogText;

	private ArrayList<T_Contacts> sortBeans;
	private ContactsLocalSortAdapter sortAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.contacts_local_page_layout);
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
		// 下拉属性
		listView.setOnItemClickListener(this);
		listView.setPullLoadEnable(false);
		listView.setPullRefreshEnable(false);
		
		listView.setOffsetLeft(getResources().getDisplayMetrics().widthPixels * 4 / 5);
			
		sideBar.setTextView(pingYinDialogText);
		sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {

			@Override
			public void onTouchingLetterChanged(String s) {
				// TODO Auto-generated method stub
				// 该字母首次出现的位置
				if (sortBeans == null || sortBeans.size() == 0) {
					return;
				}
				int position = sortAdapter.getPositionForSection(s.charAt(0));
				if (position != -1) {
					listView.setSelection(position);
				}
			}
		});
		
		ThreadPool.getExecutorService().execute(run);
	}
	/**
	 * 该服务用于本地数据库中通信名单的获取
	 */
	public Runnable run = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			List<T_Contacts> list = Dao_Contacts.getAllContactsRecord();
			if (list != null && list.size() > 0) {
				Message msg = handler.obtainMessage();
				msg.obj = list;
				msg.what =CONTACT_OBT_SUCCESS;
				handler.sendMessage(msg);
			}
		}
	};
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			// 获取号码成功
			case CONTACT_OBT_SUCCESS:
				sortBeans = (ArrayList<T_Contacts>) msg.obj;
				sortAdapter = new ContactsLocalSortAdapter(context, sortBeans);
				// 设定关注按钮的监听
//				sortAdapter
//						.setOnItemFollowClickListener(itemFollowClitListener);
				listView.setAdapter(sortAdapter);
				break;
//			case FOLLOW_REFRESH:
//				sortAdapter.updateListView(sortBeans);
//				break;
			default:
				break;
			}
		};
	};
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		System.out.println("点击有效。。。");
		ViewShowUtils.toastNormal("点击有效");
	}
	@OnClick(R.id.backLayout)
	public void backClick(View v){
		finish();
	}
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		ViewSwitchUtils.finishOut2Right(context);
	}

	@Override
	protected void onClickListener() {
		// TODO Auto-generated method stub
		
	}
}
