package com.customview.view;




import com.customview.utils.DeviceUtils;
import customview.library.R;
import com.widget.SlipButton;
import com.widget.SlipButton.OnChangedListener;

import android.content.ClipData.Item;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Item项的自定义控件
 * 
 * @author MM_Zerui
 * 
 */
public class CustomItemView extends LinearLayout {
	private RelativeLayout totalLayout,lineLayout;
	private TextView textNote, textContent,textMode,lineLeftText;
	private ImageView imgRightNote,imgNote;
	private View topSplitView, bottomSplitView, bottomPartSplitView;
	private Context context;
	private LinearLayout normalLayout;
	private SlipButton slipButton;
	private TextView rightNoteText;
	private ImageView_Rounded rightNoteIcon;
	public CustomItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context = context;
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.custom_view_item, null);
		totalLayout = (RelativeLayout) view.findViewById(R.id.item_layout);
		textNote = (TextView) view.findViewById(R.id.text_note);
		textContent = (TextView) view.findViewById(R.id.text_content);
		imgRightNote = (ImageView) view.findViewById(R.id.img_right_note);
		topSplitView = (View) view.findViewById(R.id.split_line_total_top);
		bottomSplitView = (View) view
				.findViewById(R.id.split_line_total_bottom);
		bottomPartSplitView = (View) view
				.findViewById(R.id.split_line_part_bottom);
		normalLayout=(LinearLayout)view.findViewById(R.id.normal_mode);
		textMode=(TextView)view.findViewById(R.id.text_mode);
		lineLayout=(RelativeLayout)view.findViewById(R.id.lineMode);
		lineLeftText=(TextView)view.findViewById(R.id.line_left_text);
		slipButton=(SlipButton)view.findViewById(R.id.slipButton);
		imgNote=(ImageView)view.findViewById(R.id.img_note);
		rightNoteIcon=(ImageView_Rounded)view.findViewById(R.id.icon_right_note);
		rightNoteText=(TextView)view.findViewById(R.id.text_right_note);
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.CustomItemView);
		int resourceId = -1;
		int n = typedArray.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = typedArray.getIndex(i);
			switch (attr) {
			case R.styleable.CustomItemView_item_imgRightNote:
				resourceId = typedArray.getResourceId(
						R.styleable.CustomItemView_item_imgRightNote, 0);
				imgRightNote.setImageResource(resourceId > 0 ? resourceId
						: R.drawable.note_more_icon);
				break;
			case R.styleable.CustomItemView_item_itemHight:
				int height = (int) typedArray.getDimension(
						R.styleable.CustomItemView_item_itemHight, 45);
				// int hPx = DeviceUtils.dip2px(context, height);
				totalLayout.setLayoutParams(new LinearLayout.LayoutParams(
						LayoutParams.FILL_PARENT, height));
				break;
			case R.styleable.CustomItemView_item_splitBottomShow:
				resourceId = typedArray.getInt(
						R.styleable.CustomItemView_item_splitBottomShow, 0);
				if (resourceId == 1) {
					bottomSplitView.setVisibility(View.VISIBLE);
				}
				break;
			case R.styleable.CustomItemView_item_splitPartBottomShow:
				resourceId = typedArray.getInt(
						R.styleable.CustomItemView_item_splitPartBottomShow, 0);
				if (resourceId == 1) {
					bottomPartSplitView.setVisibility(View.VISIBLE);
				}
				break;
			case R.styleable.CustomItemView_item_splitTopShow:
				resourceId = typedArray.getInt(
						R.styleable.CustomItemView_item_splitTopShow, 0);
				if (resourceId == 1) {
					topSplitView.setVisibility(View.VISIBLE);
				}
				break;
			case R.styleable.CustomItemView_item_textContent:
				resourceId = typedArray.getResourceId(
						R.styleable.CustomItemView_item_textContent, 0);
				textContent
						.setText(resourceId > 0 ? typedArray.getResources()
								.getText(resourceId)
								: typedArray
										.getString(R.styleable.CustomItemView_item_textContent));
				break;
			case R.styleable.CustomItemView_item_textNote:
				textNote.setVisibility(View.VISIBLE);
				imgNote.setVisibility(View.INVISIBLE);
				resourceId = typedArray.getResourceId(
						R.styleable.CustomItemView_item_textNote, 0);
				textNote.setText(resourceId > 0 ? typedArray.getResources()
						.getText(resourceId) : typedArray
						.getString(R.styleable.CustomItemView_item_textNote));
				break;
			case R.styleable.CustomItemView_item_imgNote:
				textNote.setVisibility(View.INVISIBLE);
				imgNote.setVisibility(View.VISIBLE);
				resourceId = typedArray.getResourceId(
						R.styleable.CustomItemView_item_imgNote, 0);
				imgNote.setImageResource(resourceId > 0 ? resourceId
						: R.drawable.app_icon);
				break;
			case R.styleable.CustomItemView_item_text_mode:
				normalLayout.setVisibility(View.INVISIBLE);
				textMode.setVisibility(View.VISIBLE);
				lineLayout.setVisibility(View.INVISIBLE);
				
				resourceId = typedArray.getResourceId(
						R.styleable.CustomItemView_item_text_mode, 0);
				
				textMode.setText(resourceId > 0 ? typedArray.getResources()
						.getText(resourceId) : typedArray
						.getString(R.styleable.CustomItemView_item_text_mode));
				break;
			
			case R.styleable.CustomItemView_item_line_mode:
				normalLayout.setVisibility(View.INVISIBLE);
				textMode.setVisibility(View.INVISIBLE);
				lineLayout.setVisibility(View.VISIBLE);
				resourceId = typedArray.getResourceId(
						R.styleable.CustomItemView_item_line_mode, 0);
				lineLeftText.setText(resourceId > 0 ? typedArray.getResources()
						.getText(resourceId) : typedArray
						.getString(R.styleable.CustomItemView_item_line_mode));
				break;
			case R.styleable.CustomItemView_item_slipButtonShow:
				resourceId = typedArray.getInt(
						R.styleable.CustomItemView_item_slipButtonShow, 0);
				if (resourceId == 1) {
					imgRightNote.setVisibility(View.INVISIBLE);
					slipButton.setVisibility(View.VISIBLE);
				}
				break;
			case R.styleable.CustomItemView_item_rightNoteShow:
				resourceId = typedArray.getInt(
						R.styleable.CustomItemView_item_rightNoteShow, 0);
				if (resourceId == 1) {
					imgRightNote.setVisibility(View.VISIBLE);
					slipButton.setVisibility(View.INVISIBLE);
				}
				break;
			case R.styleable.CustomItemView_item_rightNoteIconShow:
				resourceId= typedArray.getInt(
						R.styleable.CustomItemView_item_rightNoteIconShow, 0);
				if(resourceId==1){
					rightNoteIcon.setVisibility(View.VISIBLE);
				}else {
					rightNoteIcon.setVisibility(View.INVISIBLE);
				}
				break;
			case R.styleable.CustomItemView_item_rightNoteTextShow:
				resourceId= typedArray.getInt(
						R.styleable.CustomItemView_item_rightNoteTextShow, 0);
				if(resourceId==1){
					rightNoteText.setVisibility(View.VISIBLE);
				}else {
					rightNoteText.setVisibility(View.INVISIBLE);
				}
				break;
			case R.styleable.CustomItemView_item_rightNoteText:
				rightNoteText.setVisibility(View.VISIBLE);
				imgRightNote.setVisibility(View.VISIBLE);
				resourceId = typedArray.getResourceId(
						R.styleable.CustomItemView_item_rightNoteText, 0);
				
				rightNoteText.setText(resourceId > 0 ? typedArray.getResources()
						.getText(resourceId) : typedArray
						.getString(R.styleable.CustomItemView_item_rightNoteText));
				break;
			case R.styleable.CustomItemView_item_rightNoteIcon:
				rightNoteIcon.setVisibility(View.VISIBLE);
				imgRightNote.setVisibility(View.VISIBLE);
				resourceId = typedArray.getResourceId(
						R.styleable.CustomItemView_item_rightNoteIcon, 0);
				rightNoteIcon.setImageResource(resourceId > 0 ? resourceId
						: R.drawable.app_icon);
				break;
			}
		}
		addView(view);
		typedArray.recycle();
	}

	/**
	 * 设定项的高度
	 * 
	 * @param hDP
	 *            高度参数(dp)
	 * @return
	 */
	public CustomItemView setItemHeight(int hDP) {
		int hPx = DeviceUtils.dip2px(context, hDP);
		totalLayout.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, hPx));
		return this;
	}
	
	/**
	 * 设定文字大小
	 * @param sDP
	 * @return
	 */
	public CustomItemView setTextSize(int sDP){
		int sPx = DeviceUtils.dip2px(context, sDP);
		textNote.setTextSize(sPx);
		textContent.setTextSize(sPx);
		return this;
	}
	
	/**
	 * 设定标识文字颜色
	 * @param color
	 * @return
	 */
	public CustomItemView setNoteTextColor(int color){
		textNote.setTextColor(color);
		return this;
	}
	/**
	 * 设定内容文字颜色
	 * @param color
	 * @return
	 */
	public CustomItemView setContentTextColor(int color){
		textContent.setTextColor(color);
		return this;
	}
	
	/**
	 * 设定顶部分割线的可见性
	 * @param ifShow
	 * @return
	 */
	public CustomItemView setTopLineVisibility(boolean ifShow){
		if(ifShow){
			topSplitView.setVisibility(View.VISIBLE);
		}else {
			topSplitView.setVisibility(View.INVISIBLE);
		}
		return this;
		
	}
	
	/**
	 * 设定底部分割线的可见性
	 * @param ifShow
	 * @return
	 */
	public CustomItemView setBottomLineVisibility(boolean ifShow){
		if(ifShow){
			bottomSplitView.setVisibility(View.VISIBLE);
		}else {
			bottomSplitView.setVisibility(View.INVISIBLE);
		}
		return this;
		
	}
	
	/**
	 * 设定底部内容分割线的可见性
	 * @param ifShow
	 * @return
	 */
	public CustomItemView setBottomPartLineVisibility(boolean ifShow){
		if(ifShow){
			bottomPartSplitView.setVisibility(View.VISIBLE);
		}else {
			bottomPartSplitView.setVisibility(View.INVISIBLE);
		}
		return this;
		
	}
	
	/**
	 * 设定标识图标的可见性
	 * @param ifShow
	 * @return
	 */
	public CustomItemView setRightImgVisibility(boolean ifShow){
		if(ifShow){
			imgRightNote.setVisibility(View.VISIBLE);
		}else {
			imgRightNote.setVisibility(View.INVISIBLE);
		}
		return this;
		
	}
	
	/**
	 * 设定标识图标资源
	 * @param img
	 * @return
	 */
	public CustomItemView setRightImg(int img){
		imgRightNote.setImageResource(img);
		return this;
	}
	
	/**
	 * 设定文字模式的文字内容
	 * @param text
	 * @return
	 */
	public CustomItemView setTextMode(String text){
		normalLayout.setVisibility(View.INVISIBLE);
		lineLayout.setVisibility(View.INVISIBLE);
		textMode.setVisibility(View.VISIBLE);
		textMode.setText(text);
		return this;
	}
	
	/**
	 * 设定文字模式的文字颜色
	 * @param color
	 * @return
	 */
	public CustomItemView setTextModeColor(int color){
		textMode.setTextColor(color);
		return this;
	}
	
	/**
	 * 滑动控件的滑动事件
	 * @param callBack
	 * @return
	 */
	public CustomItemView setSlipListener(OnChangedListener callBack){
		slipButton.SetOnChangedListener(callBack);
		return this;
	}
	
	/**
	 * 整个Item的点击事件
	 * @param callBack
	 * @return
	 */
	public CustomItemView setItemListener(OnClickListener callBack){
		totalLayout.setOnClickListener(callBack);
		return this;
	}
	
	public CustomItemView setSlipCheck(boolean check){
		slipButton.setCheck(check);
		return this;
	}
	public CustomItemView setTextContent(String str){
		textContent.setText(str);
		return this;
	}
	public CustomItemView setTextTitle(String str){
		textNote.setText(str);
		return this;
	}
	public TextView getTitle(){
		return textNote;
	}
	public TextView getContent(){
		return textContent;
	}
	
	//右边标识的相关设定
	public CustomItemView setRightNoteText(String str){
		imgRightNote.setVisibility(View.VISIBLE);
		rightNoteText.setVisibility(View.VISIBLE);
		rightNoteIcon.setVisibility(View.INVISIBLE);
		rightNoteText.setText(str);
		return this;
	}
	public CustomItemView setRightNoteTextVisible(boolean visible){
		if (visible) {
			rightNoteText.setVisibility(View.VISIBLE);
			
		}else {
			rightNoteText.setVisibility(View.INVISIBLE);
		}
		return this;
	}
	public CustomItemView setRightNoteIconVisible(boolean visible){
		if (visible) {
			rightNoteIcon.setVisibility(View.VISIBLE);
		}else {
			rightNoteIcon.setVisibility(View.INVISIBLE);
		}
		return this;
	}
	public CustomItemView setRightNoteIcon(int img){
		imgRightNote.setVisibility(View.VISIBLE);
		rightNoteIcon.setVisibility(View.VISIBLE);
		rightNoteText.setVisibility(View.INVISIBLE);
		rightNoteIcon.setImageResource(img);
		return this;
	}
	
	public ImageView_Rounded getRightNoteIcon(){
		rightNoteIcon.setVisibility(View.VISIBLE);
		return rightNoteIcon;
	}

}
