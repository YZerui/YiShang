package com.customview.view;

import com.customview.callBack.popWinCallBack;

import customview.library.R;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class CustomPopWinView extends PopupWindow {
	private View mPopView;
	private LinearLayout firstItem, secondItem, thirdItem, fourthItem;
	private TextView firstItemText,secondItemText,thirdItemText,fourthItemText;
	private ImageView firstItemImg,secondItemImg,thirdItemImg,fourthItemImg;
	private boolean onShow=false;
	public CustomPopWinView(Context context) {
		// TODO Auto-generated constructor stub
		mPopView = LayoutInflater.from(context).inflate(
				R.layout.custom_pop_view, null);
		setContentView(mPopView);

		firstItemImg=(ImageView)mPopView.findViewById(R.id.first_item_img);
		secondItemImg=(ImageView)mPopView.findViewById(R.id.second_item_img);
		thirdItemImg=(ImageView)mPopView.findViewById(R.id.third_item_img);
		fourthItemImg=(ImageView)mPopView.findViewById(R.id.fourth_item_img);
		
		firstItem = (LinearLayout) mPopView
				.findViewById(R.id.first_item_layout);
		secondItem = (LinearLayout) mPopView
				.findViewById(R.id.second_item_layout);
		thirdItem = (LinearLayout) mPopView
				.findViewById(R.id.third_item_layout);
		fourthItem = (LinearLayout) mPopView
				.findViewById(R.id.fourth_item_layout);

		firstItemText=(TextView)mPopView.findViewById(R.id.first_item_text);
		secondItemText=(TextView)mPopView.findViewById(R.id.second_item_text);
		thirdItemText=(TextView)mPopView.findViewById(R.id.third_item_text);
		fourthItemText=(TextView)mPopView.findViewById(R.id.fourth_item_text);
		
		setWidth(LayoutParams.MATCH_PARENT);
		setHeight(LayoutParams.WRAP_CONTENT);
		setFocusable(true);
		setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffe8e8e8")));
		setAnimationStyle(R.style.popwin_anim_style);
		setOutsideTouchable(false);
		onClickListener();
	}

	public CustomPopWinView setItemNum(int num) {
		switch (num) {
		case 1:
			secondItem.setVisibility(View.GONE);
			thirdItem.setVisibility(View.GONE);
			fourthItem.setVisibility(View.GONE);
			break;
		case 2:
			thirdItem.setVisibility(View.GONE);
			fourthItem.setVisibility(View.GONE);
			break;
		case 3:
			fourthItem.setVisibility(View.GONE);
			break;

		default:
			break;
		}
		return this;
	}

	private popWinCallBack callBack;

	public CustomPopWinView setCallBack(popWinCallBack callBack) {
		this.callBack = callBack;
		return this;
	}

	public void onClickListener() {
		firstItem.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.call_firstItemClick();
			}
		});
		secondItem.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.call_secondItemClick();
			}
		});
		thirdItem.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.call_thirdItemClick();
			}
		});
		fourthItem.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.call_fourthItemClick();
			}
		});
	}

	/**
	 * 设定背景
	 * 
	 * @param drawable
	 * @return
	 */
	public CustomPopWinView setBgDrable(Drawable drawable) {
		setBackgroundDrawable(drawable);
		return this;
	}

	/**
	 * 设定下拉位置(相对某控件)
	 * 
	 * @param view
	 * @param xOff
	 * @param yOff
	 * @return
	 */
	public CustomPopWinView setAsDropDown(View view, int xOff, int yOff) {
		showAsDropDown(view, 0, -0);
		onShow=!onShow;
		return this;
	}

	public CustomPopWinView upDate() {
		update();
		return this;
	}
	
	public boolean onShow(){
		return onShow;
	}
	
	public CustomPopWinView setFirstItemText(String str){
		firstItemText.setText(str);
		return this;
	}
	public CustomPopWinView setSecondItemText(String str){
		secondItemText.setText(str);
		return this;
	}
	public CustomPopWinView setThirdItemText(String str){
		thirdItemText.setText(str);
		return this;
	}
	public CustomPopWinView setFourthItemText(String str){
		fourthItemText.setText(str);
		return this;
	}
	
	public CustomPopWinView setFirstItemImg(int img){
		firstItemImg.setImageResource(img);
		return this;
	}
	public CustomPopWinView setSecondItemImg(int img){
		secondItemImg.setImageResource(img);
		return this;
	}
	public CustomPopWinView setThirdItemImg(int img){
		thirdItemImg.setImageResource(img);
		return this;
	}
	public CustomPopWinView setFourthItemImg(int img){
		fourthItemImg.setImageResource(img);
		return this;
	}

}
