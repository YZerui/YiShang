package com.yishang.B.module.b.ContactsUI;

import android.os.Bundle;

import com.customview.callBack.sectorBtnCallBack;
import com.customview.view.CustomSectorView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ruifeng.yishang.R;
import com.yishang.A.global.baseClass.SuperDialogActivity;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * 添加新朋友的选择框
 * @author MM_Zerui
 *
 */
public class ContactsActionDialog extends SuperDialogActivity{
	public static int ACTION_NOTE=0;
	public final static int TRANS_RES=1;
	public final static int ADD_NEWFRIEND=2;
	@ViewInject(R.id.sector)
	private CustomSectorView sector;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.contacts_action_choice_dialog);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
	}
	@Override
	public void setPageHeight() {
		// TODO Auto-generated method stub
		lp.height=sector.getHeight_Px(ContactsActionDialog.this);
	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		ACTION_NOTE=0;
	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onClickListener() {
		// TODO Auto-generated method stub
		sector.setCallBack(new sectorBtnCallBack() {
			@Override
			public void call_firstBtnClick() {
				// TODO Auto-generated method stub
				super.call_firstBtnClick();
				//添加新朋友呢
				ACTION_NOTE=ADD_NEWFRIEND;
				finish();
			}
			@Override
			public void call_secondBtnClick() {
				// TODO Auto-generated method stub
				super.call_secondBtnClick();
				//添加为新朋友并转发文档
				ACTION_NOTE=TRANS_RES;
				
				finish();
			}
			@Override
			public void call_bottomBtnClick() {
				// TODO Auto-generated method stub
				super.call_bottomBtnClick();
				finish();
			}
			@Override
			public void onFinal() {
				// TODO Auto-generated method stub
				
			}
		});
	}
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		ViewSwitchUtils.finishOut2Bottom(context);
	}
}
