package com.yishang.B.module.a.MsgUi;

import android.os.Bundle;

import com.customview.callBack.sectorBtnCallBack;
import com.customview.view.CustomSectorView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ruifeng.yishang.R;
import com.yishang.A.global.Enum.push.Enum_PushSource;
import com.yishang.A.global.baseClass.SuperDialogActivity;
import com.yishang.Z.utils.ViewSwitchUtils;

public class MsgFilterDialog extends SuperDialogActivity{
	@ViewInject(R.id.sector)
	private CustomSectorView sectorView;
	
	public static Enum_PushSource enumSource;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.msg_filter_dialog);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
		
	}
	@Override
	public void setPageHeight() {
		// TODO Auto-generated method stub
		lp.height=sectorView.getHeight_Px(MsgFilterDialog.this);
	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		enumSource=Enum_PushSource.DEFAULT;
	}

	@Override
	protected void onClickListener() {
		// TODO Auto-generated method stub
		sectorView.setCallBack(new sectorBtnCallBack() {
			@Override
			public void call_topBtnClick() {
				// TODO Auto-generated method stub
				super.call_topBtnClick();
				//所有商机
				enumSource=Enum_PushSource.ALL;
			}
			@Override
			public void call_firstBtnClick() {
				// TODO Auto-generated method stub
				super.call_firstBtnClick();
				//系统通知
				enumSource=Enum_PushSource.SYSTEM;
			}
			@Override
			public void call_secondBtnClick() {
				// TODO Auto-generated method stub
				super.call_secondBtnClick();
				//企业通知
				enumSource=Enum_PushSource.COMPANY;
			}
			@Override
			public void call_thirdBtnClick() {
				// TODO Auto-generated method stub
				super.call_thirdBtnClick();
				//我收到的
				enumSource=Enum_PushSource.USER;
			}
			@Override
			public void call_fourthBtnClick() {
				// TODO Auto-generated method stub
				super.call_fourthBtnClick();
				//新朋友
				enumSource=Enum_PushSource.NEWFRIEND;
			}
			@Override
			public void call_bottomBtnClick() {
				// TODO Auto-generated method stub
				super.call_bottomBtnClick();
				//取消
				enumSource=Enum_PushSource.DEFAULT;
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
