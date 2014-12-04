package com.yishang.A.global.baseClass;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.SaveCallback;
import com.exception.utils.P;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.ruifeng.yishang.R;
import com.widget.BadgeView;
import com.yishang.A.global.Enum.Enum_ReceiverAction;
import com.yishang.A.global.application.AppManager;
import com.yishang.A.global.constant.CONSTANT;
import com.yishang.B.module.a.MsgUi.MsgActivity;
import com.yishang.B.module.b.ContactsUI.ContactsActivity;
import com.yishang.B.module.c.ResourceUi.ResourceActivity;
import com.yishang.B.module.d.BusinessUi.BusinessActivity;
import com.yishang.B.module.e.SelfUi.SelfActivity;
import com.yishang.B.module.f.LoginUi.LoginHomePage;
import com.yishang.B.module.f.LoginUi.LoginPage;
import com.yishang.B.module.f.LoginUi.RegisterPasswordPage;
import com.yishang.B.module.f.LoginUi.RegisterPhonePage;
import com.yishang.C.dao.daoImpl.Dao_MsgSeq;
import com.yishang.D.service.sync.SYNCMsgService;
import com.yishang.D.service.sync.SYNCRelateCompanyService;
import com.yishang.D.service.sync.SYNCRelationshipService;
import com.yishang.D.service.sync.SYNCUserLocPointService;
import com.yishang.D.service.sync.SYNCWifiMacService;
import com.yishang.E.view.MyDialog;
import com.yishang.E.view.TabView;
import com.yishang.Z.utils.BroadcastUtil;
import com.yishang.Z.utils.DBUtils;
import com.yishang.Z.utils.ViewSwitchUtils;

import android.app.TabActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TabHost.TabSpec;

public class TabBarActivity extends TabActivity {

	private TabWidget tabWidget;
	private BadgeView badgeView;
	private TabHost tabHost;
	private NotifyReceive notifyReceive;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabbar_main_layout);
		ViewUtils.inject(this);
		initView();
		initService();
		registerReceiver();
//		String str=null;
//		int a=str.length();
	}

	private void registerReceiver() {
		// TODO Auto-generated method stub
		// 注册监听器
		notifyReceive = new NotifyReceive();
		BroadcastUtil.regiReceiver(this, notifyReceive,
				Enum_ReceiverAction.MSG_PAGE.name());
	}

	/**
	 * 执行相关服务
	 */
	private void initService() {
		//同步位置信息
		Intent intent=new Intent(this,SYNCUserLocPointService.class);
		startService(intent);
		//更新本设备的AVOS信息
		AVInstallation.getCurrentInstallation().saveInBackground(
				new SaveCallback() {
					@Override
					public void done(AVException e) {
						AVInstallation.getCurrentInstallation()
								.saveInBackground();
					}
				});
		//同步人脉信息
		ViewSwitchUtils.startService(TabBarActivity.this, SYNCRelationshipService.class);
		//同步WifiMac地址信息
		ViewSwitchUtils.startService(TabBarActivity.this, SYNCWifiMacService.class);
		//同步关联企业信息
		ViewSwitchUtils.startService(TabBarActivity.this, SYNCRelateCompanyService.class);
		
		
	}

	private void initView() {
		
		
		Cursor c = getContentResolver().query(
				ContactsContract.Contacts.CONTENT_URI,
				null,
				null,
				null,
				ContactsContract.Contacts.DISPLAY_NAME
						+ " COLLATE LOCALIZED ASC");
//
//		// 测试数据初始化
//		if (DBUtils.getSharedPreInt(TabBarActivity.this, "FIRST") != 1) {
//			Test.initCompanyData();
//			Test.initContactsData();
//			Test.initMsgData();
//			Test.initResourceData();
//		}
//		DBUtils.setSharedPreInt(TabBarActivity.this, "FIRST", 1);


		tabWidget = (TabWidget) findViewById(android.R.id.tabs);
		
		tabHost = getTabHost();
		TabView itemView1 = new TabView(this, 
				R.drawable.tabbar_msg_icon, R.drawable.tabbar_msg_icon_check);
		TabSpec tabSpec1 = tabHost.newTabSpec("tab1");
		tabSpec1.setIndicator(itemView1);
		Intent intent1 = new Intent(this, MsgActivity.class);
		tabSpec1.setContent(intent1);

		TabView itemView2 = new TabView(this, 
				R.drawable.tabbar_contacts_icon,
				R.drawable.tabbar_contacts_icon_check);
		TabSpec tabSpec2 = tabHost.newTabSpec("tab2");
		tabSpec2.setIndicator(itemView2);
		Intent intent2 = new Intent(this, ContactsActivity.class);
		tabSpec2.setContent(intent2);

		TabView itemView3 = new TabView(this, 
				R.drawable.tabbar_resource_icon,
				R.drawable.tabbar_resource_icon_check);
		TabSpec tabSpec3 = tabHost.newTabSpec("tab3");
		tabSpec3.setIndicator(itemView3);
		Intent intent3 = new Intent(this, ResourceActivity.class);
		tabSpec3.setContent(intent3);

		TabView itemView4 = new TabView(this,
				R.drawable.tabbar_business_icon,
				R.drawable.tabbar_business_icon_check);
		TabSpec tabSpec4 = tabHost.newTabSpec("tab4");
		tabSpec4.setIndicator(itemView4);
		Intent intent4 = new Intent(this, BusinessActivity.class);
		tabSpec4.setContent(intent4);

		TabView itemView5 = new TabView(this,
				R.drawable.tabbar_self_icon, R.drawable.tabbar_self_icon_check);
		TabSpec tabSpec5 = tabHost.newTabSpec("tab5");
		tabSpec5.setIndicator(itemView5);
		Intent intent5 = new Intent(this, SelfActivity.class);
		tabSpec5.setContent(intent5);

		tabHost.addTab(tabSpec1);
		tabHost.addTab(tabSpec2);
		tabHost.addTab(tabSpec3);
		tabHost.addTab(tabSpec4);
		tabHost.addTab(tabSpec5);
		tabHost.setCurrentTabByTag("tab1");
		
		//一定要注意badeView初始化的顺序
		badgeView = new BadgeView(this,tabWidget, 0);
		badgeView.setTextSize(14);
		badgeView.setBadgeMargin(3);
		badgeView.show(true);
		
		
//		//关闭缓存页面
//		Handler handler=new Handler();
//		handler.postDelayed(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				AppManager.getAppManager().finishActivity(AppStartActivity.class);
////				AppManager.getAppManager().finishActivity(LoginHomePage.class);
////				AppManager.getAppManager().finishActivity(LoginPage.class);
////				AppManager.getAppManager().finishActivity(RegisterPasswordPage.class);
////				AppManager.getAppManager().finishActivity(RegisterPhonePage.class);
//			}
//		}, 800);

	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			new MyDialog(TabBarActivity.this).withTitle("提示")
					.withMessage("	准备退出该应用么?").withSwitchBtnLayout("退出", "算了")
					.setRightBtnTextColor(CONSTANT.COLOR_NOTE)
					.setOkBtnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							AppManager.getAppManager().finishAllActivity();
						}
					}).show();
			return false;
		}
		return super.dispatchKeyEvent(event);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		updateBadegView();
//		tabHost.setCurrentTabByTag("tab1");
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub

		if (ViewSwitchUtils.ANIM_IN != 0 && ViewSwitchUtils.ANIM_OUT != 0) {
			super.overridePendingTransition(ViewSwitchUtils.ANIM_IN,
					ViewSwitchUtils.ANIM_OUT);
			ViewSwitchUtils.clear();
		}
		super.onPause();
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(notifyReceive);
	}
	/**
	 * 定义一个接受通知的内部类，用于更新视图
	 * 
	 * @author MM_Zerui
	 * @tip_1 当有消息到达时，触发该监听器
	 * @tip_2 监听器随该Activity启动而开启zhuc
	 * @tip_3 监听器当Activity销毁时便取消注册不再监听
	 */
	class NotifyReceive extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if (intent.getAction().equals(Enum_ReceiverAction.MSG_PAGE.name())) {
				updateBadegView();
			}
		}

	}
	
	/**
	 * 更新消息提示的小红点
	 */
	private void updateBadegView() {
		// int unRead=msgDaoImpl.getAllUnReadNum();

		try {
			int unRead;
			unRead = Dao_MsgSeq.getUnRead_TOTAL();
			P.v("未读数："+unRead);
			
			if (unRead > 0 && unRead < 10) {
				badgeView.setText(String.valueOf(unRead));
				badgeView.show();
				System.out.println("...1");
			} else if (unRead >= 10) {
				badgeView.setText("New");
				badgeView.show();
				System.out.println("...2");
			} else {
				badgeView.hide(false);
//				badgeView.
				System.out.println("...3");
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			System.out.println("更新小红点时出现错误");
			e.printStackTrace();
		}

	}
}
