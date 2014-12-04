package com.dialog;








import com.dialog.animstyle.BaseEffects;
import com.dialog.animstyle.Effectstype;

import customview.library.R;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author MM_Zerui 
 */
public class Dialog_Select extends Dialog {

	private Window window = null;
	private View view;
	private TextView title,btn_1,btn_2,btn_3;
	private View split_0,split_1,split_2,split_3;
	private RelativeLayout main;
	private static int mOrientation = 1;

	private Effectstype animatType = null;

	private int AnimatDuration = 300;

	public Dialog_Select(Context context) {
		super(context, R.style.dialog_untran);
		int ort = context.getResources().getConfiguration().orientation;
		if (mOrientation != ort) {
			mOrientation = ort;
		}
		initView(context);
		 
	}
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		WindowManager.LayoutParams params = getWindow().getAttributes();
		params.height = ViewGroup.LayoutParams.MATCH_PARENT;
		params.width = ViewGroup.LayoutParams.MATCH_PARENT;
		getWindow().setAttributes(
				(android.view.WindowManager.LayoutParams) params);

	}

	private void initView(Context context) {
		// TODO Auto-generated method stub
		windowDeploy();
		view = View.inflate(context, R.layout.custom_view_dialog_select, null);
		main=(RelativeLayout)view.findViewById(R.id.mainLayout);
		title = (TextView) view.findViewById(R.id.title);
		btn_1=(TextView)view.findViewById(R.id.btn1);
		btn_2=(TextView)view.findViewById(R.id.btn2);
		btn_3=(TextView)view.findViewById(R.id.btn3);
		
		split_0=(View)view.findViewById(R.id.split_0);
		split_1=(View)view.findViewById(R.id.split_1);
		split_2=(View)view.findViewById(R.id.split_2);
		
		

		setContentView(view);
		onClickListener();
		
		
	}

	private void onClickListener() {
		// TODO Auto-generated method stub
		btn_1.setOnClickListener(new android.view.View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.click_btn1();
			}
		});
		btn_2.setOnClickListener(new android.view.View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.click_btn2();
			}
		});
		btn_3.setOnClickListener(new android.view.View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.click_btn3();
			}
		});
		this.setOnShowListener(new OnShowListener() {

			@Override
			public void onShow(DialogInterface dialog) {
				// TODO Auto-generated method stub
				  if(animatType==null){
					  animatType=Effectstype.Slidetop;
	                }
				start(animatType);
			}
		});
		view.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dismiss();
			}
		});
	}

	public Dialog_Select withAnimat(Effectstype animatType) {
		this.animatType = animatType;
		return this;
	}

	public Dialog_Select withTitle(String strTitle) {
//		title.setVisibility(View.VISIBLE);
		title.setText(strTitle);
		return this;
	}
	public Dialog_Select withBtn_1(String strTitle) {
		split_0.setVisibility(View.VISIBLE);
		btn_1.setVisibility(View.VISIBLE);
		btn_1.setText(strTitle);
		return this;
	}
	public Dialog_Select withBtn_2(String strTitle) {
		split_1.setVisibility(View.VISIBLE);
		btn_2.setVisibility(View.VISIBLE);
		btn_2.setText(strTitle);
		return this;
	}
	public Dialog_Select withBtn_3(String strTitle) {
		split_2.setVisibility(View.VISIBLE);
		btn_3.setVisibility(View.VISIBLE);
		btn_3.setText(strTitle);
		return this;
	}


	public Dialog_Select withAnimatDuration(int animatDuration) {
		this.AnimatDuration = animatDuration;
		return this;
	}

	public void showDialog(int layoutResID) {
		windowDeploy();
		setContentView(layoutResID);

	}

	private void start(Effectstype type) {
		BaseEffects animator = type.getAnimator();
		if (AnimatDuration != -1) {
			animator.setDuration(Math.abs(AnimatDuration));
		}
		animator.start(main);
		
	}

	public void windowDeploy() {

		setCanceledOnTouchOutside(true);
		
	}
	public Dialog_Select withShow(){
		show();
		return this;
	}
	public Dialog_Select setCallBack(callBack_Dialog callBack){
		this.callBack=callBack;
		return this;
	}
	private callBack_Dialog callBack;
	public static abstract class callBack_Dialog{
		public void click_btn1(){
			
		}
		public void click_btn2(){
			
		}
		public void click_btn3(){
			
		}
	}
	@Override
	public void show() {

		super.show();
	}

	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();	
	}


//	@Override
//	public void onClick(View v) {
//		// TODO Auto-generated method stub
//		if(callBack==null){
//			return;
//		}
//		switch (v.getId()) {
//		case R.id.btn1:
//			callBack.click_btn1();
//			break;
//		case R.id.btn2:
//			callBack.click_btn2();
//			break;
//		case R.id.btn3:
//			callBack.click_btn3();
//			break;
//
//		default:
//			break;
//		}
//		dismiss();
//	}

}
