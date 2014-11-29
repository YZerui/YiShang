package com.customview.view;

import com.utils.Util;

import customview.library.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Bar ”Õº
 * 
 * @author MM_Zerui
 * 
 */
public class CustomBarView extends LinearLayout {
	private View view;
	private Context context;
	private View hLayout, vLayout;
	private LinearLayout hItem, vItem;
	private LinearLayout hUnit1, hUnit2, hUnit3,hUnit4,hUnit5;
	private ImageView hImg1, hImg2, hImg3, hImg4,hImg5;
	private TextView hText1, hText2, hText3, hText4,hText5;
	private ImageView hSplit1, hSplit2, hSplit3,hSplit4;

	private LinearLayout vUnit1, vUnit2, vUnit3, vUnit4,vUnit5;
	private ImageView vImg1, vImg2, vImg3, vImg4,vImg5;
	private TextView vText1, vText2, vText3, vText4,vText5;
	private ImageView vSplit1, vSplit2, vSplit3,vSplit4;

	private View vTSplit, vBSplit;
	private View hTSplit, hBSplit;

	private void hViewInit() {
		// TODO Auto-generated method stub
		hItem = (LinearLayout) view.findViewById(R.id.bar_horizontal_layout);

		hUnit1 = (LinearLayout) view.findViewById(R.id.bar_h_unit_1);
		hUnit2 = (LinearLayout) view.findViewById(R.id.bar_h_unit_2);
		hUnit3 = (LinearLayout) view.findViewById(R.id.bar_h_unit_3);
		hUnit4 = (LinearLayout) view.findViewById(R.id.bar_h_unit_4);
		hUnit5 = (LinearLayout) view.findViewById(R.id.bar_h_unit_5);

		hSplit1 = (ImageView) view.findViewById(R.id.bar_h_split_1);
		hSplit2 = (ImageView) view.findViewById(R.id.bar_h_split_2);
		hSplit3 = (ImageView) view.findViewById(R.id.bar_h_split_3);
		hSplit4 = (ImageView) view.findViewById(R.id.bar_h_split_4);

		hImg1 = (ImageView) view.findViewById(R.id.bar_h_img_1);
		hImg2 = (ImageView) view.findViewById(R.id.bar_h_img_2);
		hImg3 = (ImageView) view.findViewById(R.id.bar_h_img_3);
		hImg4 = (ImageView) view.findViewById(R.id.bar_h_img_4);
		hImg5 = (ImageView) view.findViewById(R.id.bar_h_img_5);

		hText1 = (TextView) view.findViewById(R.id.bar_h_text_1);
		hText2 = (TextView) view.findViewById(R.id.bar_h_text_2);
		hText3 = (TextView) view.findViewById(R.id.bar_h_text_3);
		hText4 = (TextView) view.findViewById(R.id.bar_h_text_4);
		hText5 = (TextView) view.findViewById(R.id.bar_h_text_5);
	}

	private void vViewInit() {
		// TODO Auto-generated method stub
		vItem = (LinearLayout) view.findViewById(R.id.bar_vetical_layout);

		vUnit1 = (LinearLayout) view.findViewById(R.id.bar_v_unit_1);
		vUnit2 = (LinearLayout) view.findViewById(R.id.bar_v_unit_2);
		vUnit3 = (LinearLayout) view.findViewById(R.id.bar_v_unit_3);
		vUnit4 = (LinearLayout) view.findViewById(R.id.bar_v_unit_4);
		vUnit5 = (LinearLayout) view.findViewById(R.id.bar_v_unit_5);

		vSplit1 = (ImageView) view.findViewById(R.id.bar_v_split_1);
		vSplit2 = (ImageView) view.findViewById(R.id.bar_v_split_2);
		vSplit3 = (ImageView) view.findViewById(R.id.bar_v_split_3);
		vSplit4 = (ImageView) view.findViewById(R.id.bar_v_split_4);

		vImg1 = (ImageView) view.findViewById(R.id.bar_v_img_1);
		vImg2 = (ImageView) view.findViewById(R.id.bar_v_img_2);
		vImg3 = (ImageView) view.findViewById(R.id.bar_v_img_3);
		vImg4 = (ImageView) view.findViewById(R.id.bar_v_img_4);
		vImg5 = (ImageView) view.findViewById(R.id.bar_v_img_5);

		vText1 = (TextView) view.findViewById(R.id.bar_v_text_1);
		vText2 = (TextView) view.findViewById(R.id.bar_v_text_2);
		vText3 = (TextView) view.findViewById(R.id.bar_v_text_3);
		vText4 = (TextView) view.findViewById(R.id.bar_v_text_4);
		vText5 = (TextView) view.findViewById(R.id.bar_v_text_5);
	}

	public CustomBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context = context;
		callBack=new callBack_Bar() {

			@Override
			public void call_FirstItem(int... position) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void call_SecondItem(int... position) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void call_ThirdItem(int... position) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void call_FourthItem(int... position) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void call_FifthItem(int... position) {
				// TODO Auto-generated method stub
				
			}
			
		};
		LayoutInflater inflater = LayoutInflater.from(context);
		view = inflater.inflate(R.layout.custom_view_bar, null);
		hLayout = (View) view.findViewById(R.id.bar_h_layout);
		vLayout = (View) view.findViewById(R.id.bar_v_layout);
		vBSplit = (View) view.findViewById(R.id.bar_split_v_bottom_view);
		vTSplit = (View) view.findViewById(R.id.bar_split_v_top_view);
		hBSplit = (View) view.findViewById(R.id.bar_split_h_bottom_view);
		hTSplit = (View) view.findViewById(R.id.bar_split_h_top_view);

		hViewInit();
		vViewInit();
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.CustomBarView);
		int resourceId = -1;
		int n = typedArray.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = typedArray.getIndex(i);
			switch (attr) {
			case R.styleable.CustomBarView_bar_mode:
				resourceId = typedArray.getInt(
						R.styleable.CustomBarView_bar_mode, 0);
				onBarMode(resourceId);
				break;
			case R.styleable.CustomBarView_bar_unit_num:
				resourceId = typedArray.getInt(
						R.styleable.CustomBarView_bar_unit_num, 0);
				onUnitNum(resourceId);
				break;
			case R.styleable.CustomBarView_bar_split_view_visible:
				resourceId = typedArray.getInt(
						R.styleable.CustomBarView_bar_split_view_visible, 0);
				onSplitViewVisible(resourceId);
				break;
			case R.styleable.CustomBarView_bar_unit_one_text:
				resourceId = typedArray.getResourceId(
						R.styleable.CustomBarView_bar_unit_one_text, 0);
				vText1.setVisibility(View.VISIBLE);
				hText1.setVisibility(View.VISIBLE);
				vText1.setText(typedArray
						.getString(R.styleable.CustomBarView_bar_unit_one_text));
				hText1.setText(typedArray
						.getString(R.styleable.CustomBarView_bar_unit_one_text));
				break;
			case R.styleable.CustomBarView_bar_unit_two_text:
				resourceId = typedArray.getResourceId(
						R.styleable.CustomBarView_bar_unit_two_text, 0);
				vText2.setVisibility(View.VISIBLE);
				hText2.setVisibility(View.VISIBLE);
				vText2.setText(typedArray
						.getString(R.styleable.CustomBarView_bar_unit_two_text));
				hText2.setText(typedArray
						.getString(R.styleable.CustomBarView_bar_unit_two_text));
				break;
			case R.styleable.CustomBarView_bar_unit_three_text:
				resourceId = typedArray.getResourceId(
						R.styleable.CustomBarView_bar_unit_three_text, 0);
				vText3.setVisibility(View.VISIBLE);
				hText3.setVisibility(View.VISIBLE);
				vText3.setText(typedArray
						.getString(R.styleable.CustomBarView_bar_unit_three_text));
				hText3.setText(typedArray
						.getString(R.styleable.CustomBarView_bar_unit_three_text));
				break;
			case R.styleable.CustomBarView_bar_unit_four_text:
				resourceId = typedArray.getResourceId(
						R.styleable.CustomBarView_bar_unit_four_text, 0);
				vText4.setVisibility(View.VISIBLE);
				hText4.setVisibility(View.VISIBLE);
				vText4.setText(typedArray
						.getString(R.styleable.CustomBarView_bar_unit_four_text));
				hText4.setText(typedArray
						.getString(R.styleable.CustomBarView_bar_unit_four_text));
				break;
			case R.styleable.CustomBarView_bar_unit_five_text:
				resourceId = typedArray.getResourceId(
						R.styleable.CustomBarView_bar_unit_five_text, 0);
				vText5.setVisibility(View.VISIBLE);
				hText5.setVisibility(View.VISIBLE);
				vText5.setText(typedArray
						.getString(R.styleable.CustomBarView_bar_unit_five_text));
				hText5.setText(typedArray
						.getString(R.styleable.CustomBarView_bar_unit_five_text));
				break;
			case R.styleable.CustomBarView_bar_unit_one_img:
				hImg1.setVisibility(View.VISIBLE);
				vImg1.setVisibility(View.VISIBLE);
				resourceId = typedArray.getResourceId(
						R.styleable.CustomBarView_bar_unit_one_img, 0);
				hImg1.setImageResource(resourceId);
				vImg1.setImageResource(resourceId);
				break;
			case R.styleable.CustomBarView_bar_unit_two_img:
				hImg2.setVisibility(View.VISIBLE);
				vImg2.setVisibility(View.VISIBLE);
				resourceId = typedArray.getResourceId(
						R.styleable.CustomBarView_bar_unit_two_img, 0);
				hImg2.setImageResource(resourceId > 0 ? resourceId
						: R.drawable.app_icon);
				vImg2.setImageResource(resourceId > 0 ? resourceId
						: R.drawable.app_icon);
				break;
			case R.styleable.CustomBarView_bar_unit_three_img:
				hImg3.setVisibility(View.VISIBLE);
				vImg3.setVisibility(View.VISIBLE);
				resourceId = typedArray.getResourceId(
						R.styleable.CustomBarView_bar_unit_three_img, 0);
				hImg3.setImageResource(resourceId);
				vImg3.setImageResource(resourceId);
				break;
			case R.styleable.CustomBarView_bar_unit_four_img:
				hImg4.setVisibility(View.VISIBLE);
				vImg4.setVisibility(View.VISIBLE);
				resourceId = typedArray.getResourceId(
						R.styleable.CustomBarView_bar_unit_four_img, 0);
				hImg4.setImageResource(resourceId);
				vImg4.setImageResource(resourceId);
				break;
			case R.styleable.CustomBarView_bar_unit_five_img:
				hImg5.setVisibility(View.VISIBLE);
				vImg5.setVisibility(View.VISIBLE);
				resourceId = typedArray.getResourceId(
						R.styleable.CustomBarView_bar_unit_five_img, 0);
				hImg5.setImageResource(resourceId);
				vImg5.setImageResource(resourceId);
				break;
			case R.styleable.CustomBarView_bar_t_split_view_visible:
				resourceId = typedArray.getInt(
						R.styleable.CustomBarView_bar_t_split_view_visible, 0);
				onTSplitVisible(resourceId);
				break;
			case R.styleable.CustomBarView_bar_b_split_view_visible:
				resourceId = typedArray.getInt(
						R.styleable.CustomBarView_bar_b_split_view_visible, 0);
				onBSplitVisible(resourceId);
				break;
			case R.styleable.CustomBarView_bar_text_color:
				resourceId = typedArray.getColor(
						R.styleable.CustomBarView_bar_text_color, resourceId);
				vText1.setTextColor(resourceId);
				vText2.setTextColor(resourceId);
				vText3.setTextColor(resourceId);
				vText4.setTextColor(resourceId);
				vText5.setTextColor(resourceId);
				hText1.setTextColor(resourceId);
				hText2.setTextColor(resourceId);
				hText3.setTextColor(resourceId);
				hText4.setTextColor(resourceId);
				hText5.setTextColor(resourceId);
				break;
			case R.styleable.CustomBarView_bar_h_item_heigth:
				int size = (int) typedArray.getDimension(
						R.styleable.CustomBarView_bar_h_item_heigth,
						Util.dip2px(context, 40));
				hItem.setLayoutParams(new LayoutParams(
						LayoutParams.FILL_PARENT, size));
				break;
			case R.styleable.CustomBarView_bar_v_item_heigth:
				int size2 = (int) typedArray.getDimension(
						R.styleable.CustomBarView_bar_v_item_heigth,
						Util.dip2px(context, 55));
				vItem.setLayoutParams(new LayoutParams(
						LayoutParams.FILL_PARENT, size2));
				break;
			case R.styleable.CustomBarView_bar_unit_one_bg:
				resourceId = typedArray.getResourceId(
						R.styleable.CustomBarView_bar_unit_one_bg, 0);
				hUnit1.setBackgroundResource(resourceId);
				vUnit1.setBackgroundResource(resourceId);
				break;
			case R.styleable.CustomBarView_bar_unit_two_bg:
				resourceId = typedArray.getResourceId(
						R.styleable.CustomBarView_bar_unit_two_bg, 0);
				hUnit2.setBackgroundResource(resourceId);
				vUnit2.setBackgroundResource(resourceId);
				break;
			case R.styleable.CustomBarView_bar_unit_three_bg:
				resourceId = typedArray.getResourceId(
						R.styleable.CustomBarView_bar_unit_three_bg, 0);
				hUnit3.setBackgroundResource(resourceId);
				vUnit3.setBackgroundResource(resourceId);
				break;
			case R.styleable.CustomBarView_bar_unit_four_bg:
				resourceId = typedArray.getResourceId(
						R.styleable.CustomBarView_bar_unit_four_bg, 0);
				hUnit4.setBackgroundResource(resourceId);
				vUnit4.setBackgroundResource(resourceId);
				break;
			case R.styleable.CustomBarView_bar_unit_five_bg:
				resourceId = typedArray.getResourceId(
						R.styleable.CustomBarView_bar_unit_five_bg, 0);
				hUnit5.setBackgroundResource(resourceId);
				vUnit5.setBackgroundResource(resourceId);
				break;
			case R.styleable.CustomBarView_bar_img_size:
				int imgSize = (int) typedArray.getDimension(
						R.styleable.CustomBarView_bar_img_size,
						Util.dip2px(context, 25));
				onImgSize(imgSize);
				break;
			}
			
		}
		itemClickListener();
		addView(view);
	}

	private CustomBarView onTSplitVisible(int resourceId) {
		if (resourceId == 1) {
			onTSplitVisible();
		} else {
			onTSplitUnVisible();
		}
		return this;
	}
	public CustomBarView onImgSize(int size){
		vImg1.setLayoutParams(new LayoutParams(size, size));
		vImg2.setLayoutParams(new LayoutParams(size, size));
		vImg3.setLayoutParams(new LayoutParams(size, size));
		vImg4.setLayoutParams(new LayoutParams(size, size));
		vImg5.setLayoutParams(new LayoutParams(size, size));
		
		hImg1.setLayoutParams(new LayoutParams(size, size));
		hImg2.setLayoutParams(new LayoutParams(size, size));
		hImg3.setLayoutParams(new LayoutParams(size, size));
		hImg4.setLayoutParams(new LayoutParams(size, size));
		hImg5.setLayoutParams(new LayoutParams(size, size));
		
		return this;
	}
	public CustomBarView onTSplitVisible(){
		vTSplit.setVisibility(View.VISIBLE);
		hTSplit.setVisibility(View.VISIBLE);
		return this;
	}
	public CustomBarView onTSplitUnVisible(){
		vTSplit.setVisibility(View.VISIBLE);
		hTSplit.setVisibility(View.VISIBLE);
		return this;
	}
	private CustomBarView onBSplitVisible(int resourceId) {
		if (resourceId == 1) {
			onBSplitVisible();
		} else {
			onBSplitUnVisible();
		}
		return this;
	}
	public CustomBarView onBSplitVisible(){
		vBSplit.setVisibility(View.VISIBLE);
		hBSplit.setVisibility(View.VISIBLE);
		return this;
	}
	public CustomBarView onBSplitUnVisible(){
		vBSplit.setVisibility(View.VISIBLE);
		hBSplit.setVisibility(View.VISIBLE);
		return this;
	}
	public CustomBarView onVMode(){
		vLayout.setVisibility(View.VISIBLE);
		hLayout.setVisibility(View.GONE);
		return this;
	}
	public CustomBarView onHMode(){
		hLayout.setVisibility(View.VISIBLE);
		vLayout.setVisibility(View.GONE);
		return this;
	}

	public CustomBarView onSplitVisible(){
		vSplit1.setVisibility(View.VISIBLE);
		vSplit2.setVisibility(View.VISIBLE);
		vSplit3.setVisibility(View.VISIBLE);
		vSplit4.setVisibility(View.VISIBLE);
		hSplit1.setVisibility(View.VISIBLE);
		hSplit2.setVisibility(View.VISIBLE);
		hSplit3.setVisibility(View.VISIBLE);
		hSplit4.setVisibility(View.VISIBLE);
		return this;
	}
	
	public CustomBarView onSplitUnVisible(){
		vSplit1.setVisibility(View.GONE);
		vSplit2.setVisibility(View.GONE);
		vSplit3.setVisibility(View.GONE);
		vSplit4.setVisibility(View.GONE);
		hSplit1.setVisibility(View.GONE);
		hSplit2.setVisibility(View.GONE);
		hSplit3.setVisibility(View.GONE);
		hSplit4.setVisibility(View.GONE);
		return this;
	}
	public CustomBarView onText_1(String str){
		vText1.setVisibility(View.VISIBLE);
		hText1.setVisibility(View.VISIBLE);
		vText1.setText(str);
		hText1.setText(str);
		return this;
	}
	public CustomBarView onText_2(String str){
		vText2.setVisibility(View.VISIBLE);
		hText2.setVisibility(View.VISIBLE);
		vText2.setText(str);
		hText2.setText(str);
		return this;
	}
	public CustomBarView onText_3(String str){
		vText3.setVisibility(View.VISIBLE);
		hText3.setVisibility(View.VISIBLE);
		vText3.setText(str);
		hText3.setText(str);
		return this;
	}
	public CustomBarView onText_4(String str){
		vText4.setVisibility(View.VISIBLE);
		hText4.setVisibility(View.VISIBLE);
		vText4.setText(str);
		hText4.setText(str);
		return this;
	}
	public CustomBarView onImg_1(int img){
		hImg1.setVisibility(View.VISIBLE);
		vImg1.setVisibility(View.VISIBLE);
		hImg1.setImageResource(img);
		vImg1.setImageResource(img);
		return this;
	}
	public CustomBarView onImg_2(int img){
		hImg2.setVisibility(View.VISIBLE);
		vImg2.setVisibility(View.VISIBLE);
		hImg2.setImageResource(img);
		vImg2.setImageResource(img);
		return this;
	}
	public CustomBarView onImg_3(int img){
		hImg3.setVisibility(View.VISIBLE);
		vImg3.setVisibility(View.VISIBLE);
		hImg3.setImageResource(img);
		vImg3.setImageResource(img);
		return this;
	}
	public CustomBarView onImg_4(int img){
		hImg4.setVisibility(View.VISIBLE);
		vImg4.setVisibility(View.VISIBLE);
		hImg4.setImageResource(img);
		vImg4.setImageResource(img);
		return this;
	}
	
	/**
	 * …Ë∂®±≥æ∞
	 * @param colorId
	 * @return
	 */
	public CustomBarView onBg(int colorId){
		hItem.setBackgroundColor(getResources().getColor(colorId));
		vItem.setBackgroundColor(getResources().getColor(colorId));
		return this;
	}
	private CustomBarView onBarMode(int resourceId) {
		if (resourceId == 1) {
			onVMode();
		} else {
			onHMode();
		}
		return this;
	}

	private CustomBarView onSplitViewVisible(int resourceId) {
		if (resourceId == 1) {
			onSplitVisible();
		} else {
			onSplitUnVisible();
		}
		return this;
	}
	private void itemClickListener() {
		// TODO Auto-generated method stub
		vUnit1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.call_FirstItem();
			}
		});
		vUnit2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.call_SecondItem();
			}
		});
		vUnit3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.call_ThirdItem();
			}
		});
		vUnit4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.call_FourthItem();
			}
		});
		vUnit5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.call_FifthItem();
			}
		});
		hUnit1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.call_FirstItem(position);
			}
		});
		hUnit2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.call_SecondItem(position);
			}
		});
		hUnit3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.call_ThirdItem(position);
			}
		});
		hUnit4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.call_FourthItem(position);
			}
		});
		hUnit5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.call_FifthItem(position);
			}
		});
		
	}
	private callBack_Bar callBack;
	private int position[];
	private int index;
	public void setCallBack(callBack_Bar call,int... position){
		this.callBack=call;
		this.position=position;
		index=0;
		for(int value:position){
			position[index]=value;
			index++;
		}
		
	}
	public CustomBarView onUnitEnable(int index,boolean enabled){
		switch (index) {
		case 1:
			hUnit1.setEnabled(enabled);
			vUnit1.setEnabled(enabled);
			break;
		case 2:
			hUnit2.setEnabled(enabled);
			vUnit2.setEnabled(enabled);
			break;
		case 3:
			hUnit3.setEnabled(enabled);
			vUnit3.setEnabled(enabled);
			break;
		case 4:
			hUnit4.setEnabled(enabled);
			vUnit4.setEnabled(enabled);
			break;
		case 5:
			hUnit5.setEnabled(enabled);
			vUnit5.setEnabled(enabled);
			break;
		default:
			break;
		}
		return this;
	}
	public CustomBarView onUnitBg(int index,int colorId){
		switch (index) {
		case 1:
			hUnit1.setBackgroundColor(getResources().getColor(colorId));
			vUnit1.setBackgroundColor(getResources().getColor(colorId));
			break;
		case 2:
			hUnit2.setBackgroundColor(getResources().getColor(colorId));
			vUnit2.setBackgroundColor(getResources().getColor(colorId));
			break;
		case 3:
			hUnit3.setBackgroundColor(getResources().getColor(colorId));
			vUnit3.setBackgroundColor(getResources().getColor(colorId));
			break;
		case 4:
			hUnit4.setBackgroundColor(getResources().getColor(colorId));
			vUnit4.setBackgroundColor(getResources().getColor(colorId));
			break;
		case 5:
			hUnit5.setBackgroundColor(getResources().getColor(colorId));
			vUnit5.setBackgroundColor(getResources().getColor(colorId));
			break;
		default:
			break;
		}
		return this;
	}
	public CustomBarView onUnitNum(int num){
		
		switch (num) {
		case 5:
			vUnit5.setVisibility(View.VISIBLE);
			hUnit5.setVisibility(View.VISIBLE);
			vSplit4.setVisibility(View.VISIBLE);
			hSplit4.setVisibility(View.VISIBLE);
		case 4:
			vUnit4.setVisibility(View.VISIBLE);
			hUnit4.setVisibility(View.VISIBLE);
			vSplit3.setVisibility(View.VISIBLE);
			hSplit3.setVisibility(View.VISIBLE);
		case 3:
			vUnit3.setVisibility(View.VISIBLE);
			hUnit3.setVisibility(View.VISIBLE);
			vSplit2.setVisibility(View.VISIBLE);
			hSplit2.setVisibility(View.VISIBLE);
		case 2:
			vUnit2.setVisibility(View.VISIBLE);
			hUnit2.setVisibility(View.VISIBLE);
			vSplit1.setVisibility(View.VISIBLE);
			hSplit1.setVisibility(View.VISIBLE);
		case 1:
			vUnit1.setVisibility(View.VISIBLE);
			hUnit1.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
		return this;
	}
	public static abstract class callBack_Bar{
		public abstract void call_FirstItem(int... position);
		public abstract void call_SecondItem(int... position);
		public abstract void call_ThirdItem(int... position);
		public abstract void call_FourthItem(int... position);
		public abstract void call_FifthItem(int... position);
	}
}
