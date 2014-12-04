package com.yishang.B.module.e.SelfUi;

import android.os.Bundle;

import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomItemView;
import com.customview.view.CustomTopbarView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ruifeng.yishang.R;
import com.yishang.A.global.baseClass.SuperActivity;
import com.yishang.A.global.writting.W_UserIfo;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * 用户的详细资料页面
 * @备注 接收来自用户个人页面的信息数据
 * 
 * @author MM_Zerui
 *
 */
public class UserIfoDetailPage extends SuperActivity{
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.email_item)
	private CustomItemView emailItem;
	@ViewInject(R.id.rank_item)
	private CustomItemView rankItem;
	@ViewInject(R.id.label_item)
	private CustomItemView labelItem;
	@ViewInject(R.id.fax_item)
	private CustomItemView faxItem;
	@ViewInject(R.id.address_item)
	private CustomItemView addressItem;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.user_ifo_detail_page);
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
	
		rankItem.setTextContent(UserIfoPage.result.getUser_title1());
		labelItem.setTextContent(UserIfoPage.result.getUser_lable());
		
		emailItem.setTextContent(W_UserIfo.email(UserIfoPage.result.getUser_email()));
		addressItem.setTextContent(W_UserIfo.address(UserIfoPage.result.getUser_address()));
		faxItem.setTextContent(W_UserIfo.fax(UserIfoPage.result.getUser_fax()));
		topBar.setBackText(UserIfoPage.result.getUser_name());
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
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		ViewSwitchUtils.finishOut2Right(context);
	}
}
