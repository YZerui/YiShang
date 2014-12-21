package com.customview.view;

import com.constant.COMMON;
import com.customview.callBack.topBarCallBack;
import customview.library.R;

import android.content.ClipData.Item;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

/**
 * TopBar栏的自定义控件
 * 
 * @author MM_Zerui
 * 
 */
public class CustomTopbarView extends LinearLayout {
	private RelativeLayout backLayout;
	private TextView backText, title, leftTextBtn, rightTextBtn,
			rightSecondTextBtn_Text, rightSecondTextBtn_Btn;
	private ProgressBar progressBar;
	private ImageView rightBtn, leftBtn, rightSecondBtn_Text,
			rightSecondBtn_Btn;
	private View splitLine, splitSecondLine_Text, splitSecondLine_Btn;
	private Context context;
	private topBarCallBack callBack;
	private RelativeLayout topbarLayout;
	private int bgColor = COMMON.COLOR_THEME;

	public void setCallBack(topBarCallBack callBack) {
		this.callBack = callBack;
	}

	// public CustomTopbarView(Context context, AttributeSet attrs) {
	// // TODO Auto-generated constructor stub
	// }
	public CustomTopbarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context = context;
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.custom_view_topbar, null);
		backLayout = (RelativeLayout) view.findViewById(R.id.backLayout);
		backText = (TextView) view.findViewById(R.id.backText);
		title = (TextView) view.findViewById(R.id.topbar_title);
		leftTextBtn = (TextView) view.findViewById(R.id.left_text_btn);
		splitLine = (View) view.findViewById(R.id.splitLine);
		rightTextBtn = (TextView) view.findViewById(R.id.right_text_btn);
		progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
		rightBtn = (ImageView) view.findViewById(R.id.rightBtn);
		leftBtn = (ImageView) view.findViewById(R.id.leftBtn);
		topbarLayout = (RelativeLayout) view.findViewById(R.id.topbar_layout);
		rightSecondBtn_Btn = (ImageView) view.findViewById(R.id.rightSecondBtn);
		rightSecondBtn_Text = (ImageView) view
				.findViewById(R.id.rightSecondBtn_Text);
		rightSecondTextBtn_Btn = (TextView) view
				.findViewById(R.id.right_text_second_btn_Btn);
		rightSecondTextBtn_Text = (TextView) view
				.findViewById(R.id.right_text_second_btn_Text);
		splitSecondLine_Btn = (View) view
				.findViewById(R.id.right_vetical_split_Btn);
		splitSecondLine_Text = (View) view
				.findViewById(R.id.right_vetical_split_Text);
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.CustomTopBarView);
		int resourceId = -1;
		float height;
		int n = typedArray.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = typedArray.getIndex(i);
			switch (attr) {
			case R.styleable.CustomTopBarView_topbar_bg_color:
				resourceId = typedArray.getColor(
						R.styleable.CustomTopBarView_topbar_bg_color, bgColor);
				topbarLayout.setBackgroundColor(resourceId);
				break;
			case R.styleable.CustomTopBarView_topbar_height:
				height = typedArray.getDimension(
						R.styleable.CustomTopBarView_topbar_height, 45);
				topbarLayout.setLayoutParams(new LinearLayout.LayoutParams(
						LayoutParams.FILL_PARENT, (int) height));
				break;
			case R.styleable.CustomTopBarView_topbar_back_text:
				// backText.setVisibility(View.VISIBLE);
				backLayout.setVisibility(View.VISIBLE);
				resourceId = typedArray.getResourceId(
						R.styleable.CustomTopBarView_topbar_back_text, 0);
				backText.setText(resourceId > 0 ? typedArray.getResources()
						.getText(resourceId)
						: typedArray
								.getString(R.styleable.CustomTopBarView_topbar_back_text));
				break;
			case R.styleable.CustomTopBarView_topbar_back_visibility:
				resourceId = typedArray.getInt(
						R.styleable.CustomTopBarView_topbar_back_visibility, 0);
				if (resourceId == 1) {
					backLayout.setVisibility(View.VISIBLE);
				} else {
					backLayout.setVisibility(View.INVISIBLE);
				}
				break;
			case R.styleable.CustomTopBarView_topbar_left_btn_visibility:
				resourceId = typedArray
						.getInt(R.styleable.CustomTopBarView_topbar_left_btn_visibility,
								0);
				if (resourceId == 1) {
					leftBtn.setVisibility(View.VISIBLE);
				} else {
					leftBtn.setVisibility(View.INVISIBLE);
				}
				break;
			case R.styleable.CustomTopBarView_topbar_left_img_src:
				leftBtn.setVisibility(View.VISIBLE);
				// backLayout.setVisibility(View.VISIBLE);
				resourceId = typedArray.getResourceId(
						R.styleable.CustomTopBarView_topbar_left_img_src, 0);
				leftBtn.setImageResource(resourceId);
				break;
			case R.styleable.CustomTopBarView_topbar_left_text:
				// leftBtn.setVisibility(View.VISIBLE);
				leftTextBtn.setVisibility(View.VISIBLE);
				// backLayout.setVisibility(View.VISIBLE);
				resourceId = typedArray.getResourceId(
						R.styleable.CustomTopBarView_topbar_left_text, 0);
				leftTextBtn
						.setText(resourceId > 0 ? typedArray.getResources()
								.getText(resourceId)
								: typedArray
										.getString(R.styleable.CustomTopBarView_topbar_left_text));
				break;
			case R.styleable.CustomTopBarView_topbar_left_text_btn_visibility:
				resourceId = typedArray
						.getInt(R.styleable.CustomTopBarView_topbar_left_text_btn_visibility,
								0);
				if (resourceId == 1) {
					leftTextBtn.setVisibility(View.VISIBLE);
				} else {
					leftTextBtn.setVisibility(View.INVISIBLE);
				}
				break;
			case R.styleable.CustomTopBarView_topbar_progress_visibility:
				resourceId = typedArray
						.getInt(R.styleable.CustomTopBarView_topbar_progress_visibility,
								0);
				if (resourceId == 1) {
					progressBar.setVisibility(View.VISIBLE);
				} else {
					progressBar.setVisibility(View.INVISIBLE);
				}
				break;
			case R.styleable.CustomTopBarView_topbar_right_btn_visibility:
				resourceId = typedArray
						.getInt(R.styleable.CustomTopBarView_topbar_right_btn_visibility,
								0);

				if (resourceId == 1) {
					rightBtn.setVisibility(View.VISIBLE);
				} else {
					rightBtn.setVisibility(View.INVISIBLE);
				}
				break;
			case R.styleable.CustomTopBarView_topbar_right_img_src:
				rightBtn.setVisibility(View.VISIBLE);
				resourceId = typedArray.getResourceId(
						R.styleable.CustomTopBarView_topbar_right_img_src, 0);
				rightBtn.setImageResource(resourceId);
				break;
			case R.styleable.CustomTopBarView_topbar_right_text:
				rightTextBtn.setVisibility(View.VISIBLE);
				resourceId = typedArray.getResourceId(
						R.styleable.CustomTopBarView_topbar_right_text, 0);
				rightTextBtn
						.setText(resourceId > 0 ? typedArray.getResources()
								.getText(resourceId)
								: typedArray
										.getString(R.styleable.CustomTopBarView_topbar_right_text));
				break;
			case R.styleable.CustomTopBarView_topbar_right_text_btn_visibility:
				resourceId = typedArray
						.getInt(R.styleable.CustomTopBarView_topbar_right_text_btn_visibility,
								0);
				if (resourceId == 1) {
					rightTextBtn.setVisibility(View.VISIBLE);
				} else {
					rightTextBtn.setVisibility(View.INVISIBLE);
				}
				break;
			case R.styleable.CustomTopBarView_topbar_title:
				title.setVisibility(View.VISIBLE);
				resourceId = typedArray.getResourceId(
						R.styleable.CustomTopBarView_topbar_title, 0);
				title.setText(resourceId > 0 ? typedArray.getResources()
						.getText(resourceId) : typedArray
						.getString(R.styleable.CustomTopBarView_topbar_title));
				break;
			case R.styleable.CustomTopBarView_topbar_title_color:
				resourceId = typedArray.getColor(
						R.styleable.CustomTopBarView_topbar_title_color,
						bgColor);
				title.setTextColor(resourceId);
				break;
			case R.styleable.CustomTopBarView_topbar_text_color:
				resourceId = typedArray
						.getColor(
								R.styleable.CustomTopBarView_topbar_text_color,
								bgColor);
				leftTextBtn.setTextColor(resourceId);
				rightTextBtn.setTextColor(resourceId);
				break;
			case R.styleable.CustomTopBarView_topbar_right_second_btn:
				// 右边第二个按钮控件(相对按钮控件而言)
				splitSecondLine_Btn.setVisibility(View.VISIBLE);
				rightSecondBtn_Btn.setVisibility(View.VISIBLE);
				resourceId = typedArray.getResourceId(
						R.styleable.CustomTopBarView_topbar_right_second_btn, 0);
				rightSecondBtn_Btn.setImageResource(resourceId);
				break;
			case R.styleable.CustomTopBarView_topbar_right_second_btn_text:
				// 右边第二个按钮控件(相对文本控件而言)
				splitSecondLine_Text.setVisibility(View.VISIBLE);
				rightSecondBtn_Text.setVisibility(View.VISIBLE);
				resourceId = typedArray.getResourceId(
						R.styleable.CustomTopBarView_topbar_right_second_btn_text, 0);
				rightSecondBtn_Text.setImageResource(resourceId);
				break;
			case R.styleable.CustomTopBarView_topbar_right_second_text_btn:
				// 右边第二个文本控件(相对按钮控件而言)|
				splitSecondLine_Btn.setVisibility(View.VISIBLE);
				rightSecondTextBtn_Btn.setVisibility(View.VISIBLE);
				resourceId = typedArray
						.getResourceId(
								R.styleable.CustomTopBarView_topbar_right_second_text_btn,
								0);
				rightSecondTextBtn_Btn
						.setText(resourceId > 0 ? typedArray.getResources()
								.getText(resourceId)
								: typedArray
										.getString(R.styleable.CustomTopBarView_topbar_right_second_text_btn));
				break;
			case R.styleable.CustomTopBarView_topbar_right_second_text_btn_text:
				// 右边第二个文本控件(相对文本控件而言)
				splitSecondLine_Text.setVisibility(View.VISIBLE);
				rightSecondTextBtn_Text.setVisibility(View.VISIBLE);
				resourceId = typedArray
						.getResourceId(
								R.styleable.CustomTopBarView_topbar_right_second_text_btn_text,
								0);
				rightSecondTextBtn_Text
						.setText(resourceId > 0 ? typedArray.getResources()
								.getText(resourceId)
								: typedArray
										.getString(R.styleable.CustomTopBarView_topbar_right_second_text_btn_text));
				break;
			// case R.styleable.cu
			}

		}
		onClickListener();
		addView(view);
		typedArray.recycle();
	}

	public void onClickListener() {
		backLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.call_backBtnListener();
			}
		});
		leftBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.call_leftImgBtnListener();
			}
		});
		leftTextBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.call_leftTextBtnListener();
			}
		});
		rightBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.call_rightImgBtnListener();
			}
		});
		rightTextBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.call_rightTextBtnListener();
			}
		});
		
		                                                                             
		rightSecondBtn_Btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.call_rightSecondClick();
				
			}
		});
		rightSecondBtn_Text.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.call_rightSecondClick();
			}
		});
		rightSecondTextBtn_Btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.call_rightSecondClick();
			}
		});
		rightSecondTextBtn_Text.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.call_rightSecondClick();
			}
		});
	}

	/**
	 * 设定标题
	 * 
	 * @param title
	 * @return
	 */
	public CustomTopbarView setTitle(String title) {
		this.title.setText(title);
		return this;
	}
	
	public CustomTopbarView setTitleVisible(boolean visible){
		if(visible){
			this.title.setVisibility(View.VISIBLE);
		}else {
			this.title.setVisibility(View.INVISIBLE);
		}
		return this;
	}

	public CustomTopbarView setBackText(String text) {
		backText.setText(text);
		return this;
	}

	/**
	 * 设定右边文本控件内容
	 * 
	 * @param text
	 * @return
	 */
	public CustomTopbarView setRightText(String text) {
		rightTextBtn.setVisibility(View.VISIBLE);
		rightTextBtn.setText(text);
		return this;
	}

	public CustomTopbarView setRightTextColor(int color) {
		rightTextBtn.setTextColor(color);
		return this;
	}

	/**
	 * 设定左边文本控件内容
	 * 
	 * @param text
	 * @return
	 */
	public CustomTopbarView setLeftText(String text) {
		leftTextBtn.setVisibility(View.VISIBLE);
		leftTextBtn.setText(text);
		return this;
	}

	public CustomTopbarView setLeftTextColor(int color) {
		leftTextBtn.setTextColor(color);
		return this;
	}

	/**
	 * 设定右边图片控件的图片资源
	 * 
	 * @param img
	 * @return
	 */
	public CustomTopbarView setRightImg(int img) {
		rightBtn.setVisibility(View.VISIBLE);
		rightBtn.setImageResource(img);
		return this;
	}

	/**
	 * 设定右边图片控件的可见性
	 * 
	 * @param visible
	 * @return
	 */
	public CustomTopbarView setRightImgVisibility(boolean visible) {
		if (visible) {
			rightTextBtn.setVisibility(View.INVISIBLE);
			progressBar.setVisibility(View.INVISIBLE);
			rightBtn.setVisibility(View.VISIBLE);
		} else {
			rightBtn.setVisibility(View.INVISIBLE);
		}
		return this;
	}

	/**
	 * 设定左边图片控件的可见性
	 * 
	 * @param visible
	 * @return
	 */
	public CustomTopbarView setLeftImgVisibility(boolean visible) {
		if (visible) {
			leftTextBtn.setVisibility(View.INVISIBLE);
			leftBtn.setVisibility(View.VISIBLE);
		} else {
			leftBtn.setVisibility(View.INVISIBLE);
		}
		return this;
	}

	/**
	 * 设定左边图片控件的图片资源
	 * 
	 * @param img
	 * @return
	 */
	public CustomTopbarView setLeftImg(int img) {
		leftBtn.setVisibility(View.VISIBLE);
		leftBtn.setImageResource(img);
		return this;
	}

	/**
	 * 设定进度圈的可见性
	 * 
	 * @param visible
	 * @return
	 */
	public CustomTopbarView setProVisibility(boolean visible) {
		if (visible) {
			onRightSecondBtnInvisible();
			rightBtn.setVisibility(View.INVISIBLE);
			progressBar.setVisibility(View.VISIBLE);
			rightTextBtn.setVisibility(View.INVISIBLE);
		} else {
			progressBar.setVisibility(View.INVISIBLE);
		}
		return this;
	}

	/**
	 * 设定左边文本控件的可见性
	 * 
	 * @param visible
	 * @return
	 */
	public CustomTopbarView setLeftTextVisibility(boolean visible) {
		if (visible) {
			leftBtn.setVisibility(View.INVISIBLE);
			leftTextBtn.setVisibility(View.VISIBLE);
		} else {
			leftTextBtn.setVisibility(View.INVISIBLE);
		}
		return this;
	}

	/**
	 * 设定右边文本控件的可见性
	 * 
	 * @param visible
	 * @return
	 */
	public CustomTopbarView setRighTextVisibility(boolean visible) {
		if (visible) {
			rightBtn.setVisibility(View.INVISIBLE);
			progressBar.setVisibility(View.INVISIBLE);
			rightTextBtn.setVisibility(View.VISIBLE);
		} else {
			rightTextBtn.setVisibility(View.INVISIBLE);
		}
		return this;
	}

	/**
	 * 设定返回控件的可见性
	 * 
	 * @param visible
	 * @return
	 */
	public CustomTopbarView setBackLayoutVisible(boolean visible) {
		if (visible) {
			leftBtn.setVisibility(View.INVISIBLE);
			backLayout.setVisibility(View.VISIBLE);
		} else {
			backLayout.setVisibility(View.INVISIBLE);
		}
		return this;
	}
	
	/**
	 * 右边第二控件的不可见性
	 * @return
	 */
	public CustomTopbarView onRightSecondBtnInvisible(){
		rightSecondBtn_Btn.setVisibility(View.INVISIBLE);
		rightSecondBtn_Text.setVisibility(View.INVISIBLE);
		rightSecondTextBtn_Btn.setVisibility(View.INVISIBLE);
		rightSecondTextBtn_Text.setVisibility(View.INVISIBLE);
		splitSecondLine_Btn.setVisibility(View.INVISIBLE);
		splitSecondLine_Text.setVisibility(View.INVISIBLE);
		return this;
	}
	
	public CustomTopbarView onRightSecondBtn_Text(boolean visible){
		if(visible){
			splitSecondLine_Text.setVisibility(View.VISIBLE);
			rightSecondBtn_Text.setVisibility(View.VISIBLE);
			return this;
		}
		rightSecondBtn_Text.setVisibility(View.INVISIBLE);
		return this;
	}
	public CustomTopbarView onRightSecondBtn_Btn(boolean visible){
		if(visible){
			splitSecondLine_Btn.setVisibility(View.VISIBLE);
			rightSecondBtn_Btn.setVisibility(View.VISIBLE);
			return this;
		}
		rightSecondBtn_Btn.setVisibility(View.INVISIBLE);
		return this;
	}
	public CustomTopbarView onRightSecondTextBtn_Text(boolean visible){
		if(visible){
			splitSecondLine_Text.setVisibility(View.VISIBLE);
			rightSecondTextBtn_Text.setVisibility(View.VISIBLE);
			return this;
		}
		rightSecondTextBtn_Text.setVisibility(View.INVISIBLE);
		return this;
	}
	public CustomTopbarView onRightSecondTextBtn_Btn(boolean visible){
		if(visible){
			splitSecondLine_Btn.setVisibility(View.VISIBLE);
			rightSecondTextBtn_Btn.setVisibility(View.VISIBLE);
			return this;
		}
		rightSecondTextBtn_Btn.setVisibility(View.INVISIBLE);
		return this;
	}
}
