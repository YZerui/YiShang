package com.yishang.B.module.b.ContactsUI;

import android.os.Bundle;

import com.customview.callBack.sectorBtnCallBack;
import com.customview.view.CustomSectorView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ruifeng.yishang.R;
import com.yishang.A.global.Enum.db.Enum_RelaType;
import com.yishang.A.global.baseClass.SuperDialogActivity;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * 人脉筛选页面
 * @author MM_Zerui
 *
 */
public class ContactsFilterDialog extends SuperDialogActivity{
	public static Enum_RelaType enumType;
	
	@ViewInject(R.id.sector)
	private CustomSectorView sectorView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.contacts_filter_dialog);
		ViewUtils.inject(this);
		//默认
		enumType=Enum_RelaType.UNKNOW;
		super.onCreate(savedInstanceState);
		
		
	}
	@Override
	public void setPageHeight() {
		// TODO Auto-generated method stub
		lp.height=sectorView.getHeight_Px(ContactsFilterDialog.this);
	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onClickListener() {
		// TODO Auto-generated method stub
		sectorView.setCallBack(new sectorBtnCallBack() {
			public void call_bottomBtnClick(){
				finish();
			}
			@Override
			public void call_topBtnClick() {
				// TODO Auto-generated method stub
				super.call_topBtnClick();
				enumType=Enum_RelaType.DEFAULT;
			}
			public void call_firstBtnClick(){
				enumType=Enum_RelaType.CLIENT;
			}
			public void call_secondBtnClick(){
				enumType=Enum_RelaType.SUPPLIER;
			}
			public void call_thirdBtnClick(){
				enumType=Enum_RelaType.CONTACTS;
			}
			public void call_fourthBtnClick(){
				enumType=Enum_RelaType.NEWFRIEND;
			}
			@Override
			public void onFinal() {
				// TODO Auto-generated method stub
				finish();
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
