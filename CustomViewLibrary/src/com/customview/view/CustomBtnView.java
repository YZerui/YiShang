package com.customview.view;



import com.utils.Util;

import customview.library.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 自定义多种样式的按钮控件
 * 
 * @author MM_Zerui
 * 
 */
public class CustomBtnView extends LinearLayout {
	public static final int BTN_GREEN = 0;
	public static final int BTN_GRAY = 1;
	public static final int BTN_RED = 2;
	public static final int BTN_OUTLINE = 3;
	public static final int BTN_NULL = 4;

	private Context context;
	private RelativeLayout btnLayout;
	private TextView btnText;
	private ImageView btnImg;
	private View topSplitView,bottomSplitView;
	public CustomBtnView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context = context;
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.custom_view_btn, null);
		btnLayout = (RelativeLayout) view.findViewById(R.id.btn_layout);
		btnText = (TextView) view.findViewById(R.id.btn_text);
		btnImg=(ImageView)view.findViewById(R.id.btn_img);
		topSplitView=(View)view.findViewById(R.id.topSplitLine);
		bottomSplitView=(View)view.findViewById(R.id.bottomSplitLine);
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.CustomBtnView);
		int resourceId = -1;
		int n = typedArray.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = typedArray.getIndex(i);
			switch (attr) {
			case R.styleable.CustomBtnView_btn_color_style:
				resourceId = typedArray.getInt(
						R.styleable.CustomBtnView_btn_color_style, 0);
				switch (resourceId) {
				case BTN_GREEN:
					btnLayout.setBackgroundResource(R.drawable.btn_green_style);
					break;
				case BTN_GRAY:
					btnLayout.setBackgroundResource(R.drawable.btn_gray_style);
					break;
				case BTN_RED:
					btnLayout.setBackgroundResource(R.drawable.btn_red_style);
					break;
				case BTN_OUTLINE:
					btnLayout.setBackgroundResource(R.drawable.btn_outline_bg);
					break;
				case BTN_NULL:
					btnLayout.setBackgroundResource(R.drawable.btn_selector_bg);
					break;
				}
				break;
			case R.styleable.CustomBtnView_btn_top_splitline_visible:
				resourceId = typedArray.getInt(
						R.styleable.CustomBtnView_btn_top_splitline_visible, 0);
				if (resourceId == 1) {
					topSplitView.setVisibility(View.VISIBLE);
				}else {
					topSplitView.setVisibility(View.GONE);
				}
				break;
			case R.styleable.CustomBtnView_btn_bottom_splitline_visible:
				resourceId = typedArray.getInt(
						R.styleable.CustomBtnView_btn_bottom_splitline_visible, 0);
				if (resourceId == 1) {
					bottomSplitView.setVisibility(View.VISIBLE);
				}else {
					bottomSplitView.setVisibility(View.GONE);
				}
				break;
			case R.styleable.CustomBtnView_btn_text:
				resourceId = typedArray.getResourceId(
						R.styleable.CustomBtnView_btn_text, 0);
				btnText.setVisibility(View.VISIBLE);
				btnText.setText(resourceId > 0 ? typedArray.getResources()
						.getText(resourceId) : typedArray
						.getString(R.styleable.CustomBtnView_btn_text));
				break;
			case R.styleable.CustomBtnView_btn_img:
				btnImg.setVisibility(View.VISIBLE);
				resourceId = typedArray.getResourceId(
						R.styleable.CustomBtnView_btn_img, 0);
				btnImg.setImageResource(resourceId > 0 ? resourceId
						: R.drawable.app_icon);
				break;
			case R.styleable.CustomBtnView_btn_bg:
				resourceId = typedArray.getResourceId(
						R.styleable.CustomBtnView_btn_bg, 0);
				btnLayout.setBackgroundResource(resourceId);
				break;
			case R.styleable.CustomBtnView_btn_text_color:
				resourceId=typedArray.getColor(R.styleable.CustomBtnView_btn_text_color,resourceId);
				btnText.setTextColor(resourceId);
				break;
			case R.styleable.CustomBtnView_btn_img_size:
				int size = (int) typedArray.getDimension(
						R.styleable.CustomBtnView_btn_img_size,
						Util.dip2px(context, 20));
				btnImg.setLayoutParams(new LayoutParams(size, size));
				break;
			case R.styleable.CustomBtnView_btn_text_size:
				float textSize =  typedArray.getDimension(
						R.styleable.CustomBtnView_btn_text_size,
						Util.dip2px(context, 20));
				btnText.setTextSize(textSize);
				break;
			}
			

		}
		addView(view);
	}

	public CustomBtnView setText(String text) {
		btnText.setText(text);
		return this;
	}

	public CustomBtnView setTextColor(int color) {
		btnText.setTextColor(color);
		return this;
	}
	
	public CustomBtnView setBtnStyle(int mode){
		switch (mode) {
		case BTN_GREEN:
			btnLayout.setBackgroundResource(R.drawable.btn_green_style);
			break;
		case BTN_GRAY:
			btnLayout.setBackgroundResource(R.drawable.btn_gray_style);
			break;
		case BTN_RED:
			btnLayout.setBackgroundResource(R.drawable.btn_red_style);
			break;
		case BTN_OUTLINE:
			btnLayout.setBackgroundResource(R.drawable.btn_outline_bg);
			break;
		}
		return this;
	}

}
