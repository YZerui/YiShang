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
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author MM_Zerui 
 */
public class Dialog_Note extends Dialog {

	private Window window = null;
	private View view;

	private RelativeLayout main;
	private TextView title;
	private TextView message;
	private FrameLayout switchBtnLayout;
	private FrameLayout singleBtnLayout;
	private TextView okBtn;
	private TextView cancelBtn;
	private TextView confirmBtn;

	private static int mOrientation = 1;

	private Effectstype animatType = null;

	private int AnimatDuration = 300;

	public Dialog_Note(Context context) {
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
		view = View.inflate(context, R.layout.custom_dialog_layout, null);

		main = (RelativeLayout) view.findViewById(R.id.mainLayout);
		title = (TextView) view.findViewById(R.id.dialog_title);
		message = (TextView) view.findViewById(R.id.dialog_message);
		switchBtnLayout = (FrameLayout) view.findViewById(R.id.switchBtnLayout);
		singleBtnLayout = (FrameLayout) view.findViewById(R.id.singleBtnLayout);
		okBtn = (TextView) view.findViewById(R.id.ok);
		cancelBtn = (TextView) view.findViewById(R.id.cancel);
		confirmBtn = (TextView) view.findViewById(R.id.confirmBtn);

		setContentView(view);

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
		cancelBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dismiss();
			}
		});
		
	}

	public Dialog_Note withAnimat(Effectstype animatType) {
		this.animatType = animatType;
		return this;
	}

	public Dialog_Note withTitle(String strTitle) {
		title.setVisibility(View.VISIBLE);
		title.setText(strTitle);
		return this;
	}

	public Dialog_Note withMessage(String strMsg) {
		message.setVisibility(View.VISIBLE);
		message.setText(strMsg);
		return this;
	}

	public Dialog_Note withSwitchBtnLayout(String okStr, String cancelStr) {
		switchBtnLayout.setVisibility(View.VISIBLE);
		okBtn.setText(okStr);
		cancelBtn.setText(cancelStr);
		return this;

	}
	
	public Dialog_Note setRightBtnTextColor(int color){
		okBtn.setTextColor(color);
		return this;
	}
	public Dialog_Note setLeftBtnTextColor(int color){
		cancelBtn.setTextColor(color);
		return this;
	}

	public Dialog_Note withSingleBtnLayout(String confirmStr) {
		singleBtnLayout.setVisibility(View.VISIBLE);
		confirmBtn.setText(confirmStr);
		return this;
	}

	public Dialog_Note setOkBtnClickListener(
			android.view.View.OnClickListener click) {
		
		okBtn.setOnClickListener(click);
		dismiss();
		return this;
	}

	public Dialog_Note setCancelBtnClickListener(
			android.view.View.OnClickListener click) {
		cancelBtn.setOnClickListener(click);
		dismiss();
		
		return this;
	}

	public Dialog_Note setConfirmBtnClickListener(
			android.view.View.OnClickListener click) {
		dismiss();
		confirmBtn.setOnClickListener(click);
		return this;
	}

	public Dialog_Note withAnimatDuration(int animatDuration) {
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
	public Dialog_Note withShow(){
		show();
		return this;
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

}
