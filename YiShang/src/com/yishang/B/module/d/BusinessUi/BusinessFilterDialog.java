package com.yishang.B.module.d.BusinessUi;

import android.os.Bundle;

import com.customview.callBack.sectorBtnCallBack;
import com.customview.view.CustomSectorView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ruifeng.yishang.R;
import com.yishang.A.global.Enum.com.Enum_ComType;
import com.yishang.A.global.baseClass.SuperDialogActivity;
import com.yishang.Z.utils.ViewSwitchUtils;

public class BusinessFilterDialog extends SuperDialogActivity{
	@ViewInject(R.id.sector)
	private CustomSectorView sectorView;
	
	public static Enum_ComType enumType;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.business_filter_dialog);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
	}
	@Override
	public void setPageHeight() {
		// TODO Auto-generated method stub
		lp.height=sectorView.getHeight_Px(BusinessFilterDialog.this);
	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		enumType=Enum_ComType.DEFAULT;
	}

	@Override
	protected void onClickListener() {
		// TODO Auto-generated method stub
		sectorView.setCallBack(new sectorBtnCallBack() {
			@Override
			public void call_topBtnClick() {
				// TODO Auto-generated method stub
				super.call_topBtnClick();
				//ÏÔÊ¾È«²¿
				enumType=Enum_ComType.COM_ALL;
			}
			@Override
			public void call_firstBtnClick() {
				// TODO Auto-generated method stub
				super.call_firstBtnClick();
				enumType=Enum_ComType.COM_RELA;
			}
			@Override
			public void call_secondBtnClick() {
				// TODO Auto-generated method stub
				super.call_secondBtnClick();
				enumType=Enum_ComType.COM_CLIENT;
			}
			@Override
			public void call_thirdBtnClick() {
				// TODO Auto-generated method stub
				super.call_thirdBtnClick();
				enumType=Enum_ComType.COM_SUPPLIER;
			}
			@Override
			public void call_fourthBtnClick() {
				// TODO Auto-generated method stub
				super.call_fourthBtnClick();
				enumType=Enum_ComType.COM_RELA_ING;
			}
			@Override
			public void call_bottomBtnClick() {
				// TODO Auto-generated method stub
				super.call_bottomBtnClick();
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
