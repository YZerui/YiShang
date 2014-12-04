package com.yishang.B.module.e.SelfUi;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.AnticipateOvershootInterpolator;


import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomTopbarView;
import com.exception.utils.P;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;


import com.ruifeng.yishang.R;
import com.yishang.A.global.Enum.Enum_IfoType;
import com.yishang.A.global.Enum.Enum_SelfIfo;
import com.yishang.A.global.baseClass.SuperActivity;
import com.yishang.A.global.baseClass.SuperDialogActivity;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.D.service.httpRequest.HttpReq_UpdateIfo;
import com.yishang.D.service.httpRequest.HttpReq_UpdateIfo.CallBack_UserIfo;
import com.yishang.E.view.wheel.StrericWheelAdapter;
import com.yishang.E.view.wheel.WheelView;
import com.yishang.Z.utils.DeviceUtils;
import com.yishang.Z.utils.FormatUtils;

public class SelfBirthdaySetDialog extends SuperDialogActivity{
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.wheel_1)
	private WheelView yearView;	
	@ViewInject(R.id.wheel_2)
	private WheelView monthView;	
	@ViewInject(R.id.wheel_3)
	private WheelView dayView;
	
	private String values[];
	private String dateValue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.self_birthday_set_layout);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		String timeValue=getIntent().getStringExtra("DATA0");
		String formatValue="1990-3-1";
		values=new String[3];
		
		values=formatValue.split("-");
		for(int i=0;i<values.length;i++){
			if(values[i]==null){
				values[i]="0";
			}
		}
	}

	@Override
	protected void initResource() {
	
		
		yearView.setAdapter(new StrericWheelAdapter(FormatUtils.getYearArrays()));
		yearView.setCurrentItem(FormatUtils.getYearItem(Integer.valueOf(values[0])));
		yearView.setCyclic(true);
		yearView.setInterpolator(new AnticipateOvershootInterpolator());

		monthView.setAdapter(new StrericWheelAdapter(FormatUtils
				.getMonthArrays()));
		monthView.setCurrentItem(FormatUtils.getMonthItem(Integer.valueOf(values[1])));
		monthView.setCyclic(true);
		monthView.setInterpolator(new AnticipateOvershootInterpolator());

		dayView.setAdapter(new StrericWheelAdapter(FormatUtils.getDayArrays()));
		dayView.setCurrentItem(FormatUtils.getDayItem(Integer.valueOf(values[2])));
		dayView.setCyclic(true);
		dayView.setInterpolator(new AnticipateOvershootInterpolator());
	}
	/**
	 * 获取生日的时间戳形式
	 * @return
	 */
	public String obtSetData(){
		String year = yearView.getCurrentItemValue();
		String month = monthView.getCurrentItemValue();
		String day = dayView.getCurrentItemValue();
		String yearValue = year.substring(0, year.length() - 1);
		String monthValue = month.substring(0, month.length() - 1);
		String dayValue = day.substring(0, day.length() - 1);
		dateValue = yearValue + "-" + monthValue + "-" + dayValue;
		return FormatUtils.getDateValueBirthday(dateValue);
	}
	// 重写finish（）方法，加入关闭时的动画
	public void finish() {
		super.finish();
		this.overridePendingTransition(0, R.anim.out_top_to_bottom_page);
	}
	@Override
	protected void onClickListener() {
		// TODO Auto-generated method stub
		topBar.setCallBack(new topBarCallBack() {
			@Override
			public void call_rightTextBtnListener() {
				// TODO Auto-generated method stub
				super.call_rightTextBtnListener();
				P.v("设定生日:"+obtSetData());
				final String dateValue=obtSetData();
				new HttpReq_UpdateIfo(dateValue, Enum_SelfIfo.BIRTHDAY,new CallBack_UserIfo() {
					
					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						//保存到本地
						Dao_Self.setParams(Enum_IfoType.user_bth, dateValue);
						finish();
					}
					
					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						topBar.setTitle("提交...").setProVisibility(true);
					}
					
					@Override
					public void onFail() {
						toast.setText("操作失败");
						finish();
					}
				});
			}
		});
	}

	@Override
	public void setPageHeight() {
		lp.height = DeviceUtils.dip2px(SelfBirthdaySetDialog.this, 45)
				+( WheelView.ADDITIONAL_ITEM_HEIGHT+40)
				* WheelView.DEF_VISIBLE_ITEMS;
		getWindow().setAttributes(lp);// 将设置好属性的lp应用到对话框
	}
}
