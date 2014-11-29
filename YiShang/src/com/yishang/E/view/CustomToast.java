package com.yishang.E.view;



import com.ruifeng.yishang.R;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 自定义Toast样式
 * @author MM_Zerui
 *
 */
public class CustomToast {
	private Toast toast; 
	private Context context;
	private View view;
	
	private LinearLayout layout;
	private TextView toastText;
	private ImageView leftImg;
	private ImageView rightImg;
	private ImageView topImg;
	private ImageView bottomImg;
//	private Activity activity;
//	public CustomToast(Activity actvity) {
//		// TODO Auto-generated constructor stub
////		this.context=context;
//		this.activity=actvity;
//		toast=new Toast(actvity);
//		//默认样式
//		toast.setDuration(Toast.LENGTH_SHORT);
//		withCusomView();
//		
//	}
	public CustomToast(Context context) {
		// TODO Auto-generated constructor stub
//		this.context=context;
		this.context=context;
		toast=new Toast(context);
		//默认样式
		toast.setDuration(Toast.LENGTH_SHORT);
		withCusomView();
		
	}
	public CustomToast withCusomView(){	
//		LayoutInflater inflater = activity.getLayoutInflater();
//		view=inflater.inflate(R.layout.custom_toast_view, null);
		view = View.inflate(context, R.layout.custom_toast_view, null);
		layout=(LinearLayout)view.findViewById(R.id.toast_layout);
		toastText=(TextView)view.findViewById(R.id.toast_text);
		leftImg=(ImageView)view.findViewById(R.id.toast_left_img);
		rightImg=(ImageView)view.findViewById(R.id.toast_right_img);
		topImg=(ImageView)view.findViewById(R.id.toast_top_img);
		bottomImg=(ImageView)view.findViewById(R.id.toast_bottom_img);
		
		leftImg.setVisibility(View.GONE);
		rightImg.setVisibility(View.GONE);
		topImg.setVisibility(View.GONE);
		bottomImg.setVisibility(View.GONE);
		return this;
	}
	public CustomToast setBg(int color){
		layout.setBackgroundColor(color);
		return this;
	}
	public CustomToast withShowShort(){
		toast.setDuration(Toast.LENGTH_SHORT);
		return this;
	}
	public CustomToast withShowLong(){
		toast.setDuration(Toast.LENGTH_LONG);
		return this;
	}
	public CustomToast locatCenter(){
		toast.setGravity(Gravity.CENTER, 0, 0);
		return this;
	}
	public CustomToast locatBottom(){
		toast.setGravity(Gravity.BOTTOM,0, 150);
		return this;
	}
	public CustomToast locatTop(){
		toast.setGravity(Gravity.TOP, 0, 50);
		return this;
	}
	public CustomToast withImgLeft(int drawable){
		leftImg.setVisibility(View.VISIBLE);
		leftImg.setImageResource(drawable);
		return this;
	}
	public CustomToast withImgRight(int drawable){
		rightImg.setVisibility(View.VISIBLE);
		rightImg.setImageResource(drawable);
		return this;
	}
	public CustomToast withImgTop(int drawable){
		topImg.setVisibility(View.VISIBLE);
		topImg.setImageResource(drawable);
		return this;
	}
	public CustomToast withImgBottom(int drawable){
		bottomImg.setVisibility(View.VISIBLE);
		bottomImg.setImageResource(drawable);
		return this;
	}
	public CustomToast withTextColor(int color){
		toastText.setTextColor(color);
		return this;
	}
	public CustomToast withTextSize(int size){
		toastText.setTextSize(size);
		return this;
	}
	public CustomToast withNormal(){
		return this;
	}
	public CustomToast setText(String content){
		toastText.setText(content);
		show();
		return this;
	}
	public void show(){
		toast.setView(view);
		toast.show();
	}
	
}
