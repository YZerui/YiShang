package com.yishang.B.module.c.ResourceUi;

import android.os.Bundle;

import com.customview.callBack.sectorBtnCallBack;
import com.customview.view.CustomSectorView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ruifeng.yishang.R;
import com.yishang.A.global.Enum.db.Enum_RelaType;
import com.yishang.A.global.Enum.db.Enum_ResSource;
import com.yishang.A.global.baseClass.SuperDialogActivity;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * »À¬ˆ…∏—°“≥√Ê
 * @author MM_Zerui
 *
 */
public class ResourceFilterDialog extends SuperDialogActivity{
	public static Enum_ResSource enumSource;
	
	@ViewInject(R.id.sector)
	private CustomSectorView sectorView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.resource_filter_dialog);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
		
		
	}
	@Override
	public void setPageHeight() {
		// TODO Auto-generated method stub
		lp.height=sectorView.getHeight_Px(ResourceFilterDialog.this);
	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		enumSource=Enum_ResSource.DEFAULT;
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
				enumSource=Enum_ResSource.SOURCE_RELA;
				
			}
			public void call_firstBtnClick(){
				enumSource=Enum_ResSource.SOURCE_CLIENT;
			}
			public void call_secondBtnClick(){
				enumSource=Enum_ResSource.SOURCE_SUPPLIER;
			}
			public void call_thirdBtnClick(){
				enumSource=Enum_ResSource.SOURCE_CONTACT;
			}
			public void call_fourthBtnClick(){
				enumSource=Enum_ResSource.SOURCE_NEWFRIEND;
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
