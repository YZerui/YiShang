package com.customview.view;



import customview.library.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * 自定义文本样式
 * @author MM_Zerui
 *
 */
public class CustomTextView extends LinearLayout {
	private static final int MODE_PRO_LEFT = 0;
	private static final int MODE_PRO_RIGHT = 1;
	private static final int MODE_PRO_NULL = 2;

	private ProgressBar progressBarLeft, progressBarRight;
	private TextView textView;

	public CustomTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.custom_view_text, null);
		progressBarLeft = (ProgressBar) view
				.findViewById(R.id.progressBar_left);
		progressBarRight = (ProgressBar) view
				.findViewById(R.id.progressBar_right);
		textView = (TextView) view.findViewById(R.id.text);
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.CustomTextView);
		int resourceId = -1;
		int n = typedArray.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = typedArray.getIndex(i);
			switch (attr) {
			case R.styleable.CustomTextView_text_progress_mode:
				resourceId = typedArray.getInt(
						R.styleable.CustomTextView_text_progress_mode, 0);
				switch (resourceId) {
				case MODE_PRO_LEFT:
					progressBarRight.setVisibility(View.GONE);

					break;
				case MODE_PRO_RIGHT:
					progressBarLeft.setVisibility(View.GONE);
					break;
				case MODE_PRO_NULL:
					progressBarLeft.setVisibility(View.GONE);
					progressBarRight.setVisibility(View.GONE);
					break;

				default:
					break;
				}
				break;
			case R.styleable.CustomTextView_text_content:
				resourceId = typedArray.getResourceId(
						R.styleable.CustomTextView_text_content, 0);
				textView.setText(resourceId > 0 ? typedArray.getResources()
						.getText(resourceId) : typedArray
						.getString(R.styleable.CustomTextView_text_content));
				break;
			}
		}
		addView(view);
	}
	public CustomTextView setTextColor(int color){
		textView.setTextColor(color);
		return this;
	}
	
	public CustomTextView setProLeft(){
		progressBarLeft.setVisibility(View.VISIBLE);
		progressBarRight.setVisibility(View.GONE);
		textView.setVisibility(View.VISIBLE);
		return this;
	}
	
	public CustomTextView setProRight(){
		progressBarLeft.setVisibility(View.GONE);
		progressBarRight.setVisibility(View.VISIBLE);
		textView.setVisibility(View.VISIBLE);
		return this;
	}
	
	public CustomTextView setProgressOnly(){
		progressBarLeft.setVisibility(View.VISIBLE);
		progressBarRight.setVisibility(View.GONE);
		textView.setVisibility(View.GONE);
		return this;
	}
	
	public CustomTextView setText(String text){
		textView.setVisibility(View.VISIBLE);
		textView.setText(text);
		return this;
	}
	
	public CustomTextView setTextOnly(String text){
		progressBarLeft.setVisibility(View.GONE);
		progressBarRight.setVisibility(View.GONE);
		textView.setVisibility(View.VISIBLE);
		textView.setText(text);
		return this;
	}
	
	

}
