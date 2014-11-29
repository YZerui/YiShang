package com.customview.view;

import com.customview.callBack.sectorBtnCallBack;
import com.utils.Util;

import customview.library.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ListView.FixedViewInfo;

/**
 * 底部选项控件的布局
 * 
 * @author MM_Zerui
 * 
 */
public class CustomSectorView extends LinearLayout {
	private TextView topBtn, firstBtn, secondBtn, thirdBtn, fourthBtn,
			fifthBtn,sixBtn, bottomBtn;
	private View split_1,split_2,split_3,split_4,split_5;
	private int unitNum = 0;

	public CustomSectorView(Context context, AttributeSet attrs) {
		super(context, attrs);

		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.custom_view_bottom_sector, null);
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.CustomSectorView);
		topBtn = (TextView) view.findViewById(R.id.topSector);
		firstBtn = (TextView) view.findViewById(R.id.firstSector);
		secondBtn = (TextView) view.findViewById(R.id.secondSector);
		thirdBtn = (TextView) view.findViewById(R.id.thirdSector);
		fourthBtn = (TextView) view.findViewById(R.id.fourthSector);
		fifthBtn = (TextView) view.findViewById(R.id.fifthSector);
		sixBtn=(TextView)view.findViewById(R.id.sixSector);
		bottomBtn = (TextView) view.findViewById(R.id.bottomBtn);
	
		
		split_1=(View)view.findViewById(R.id.split_1);
		split_2=(View)view.findViewById(R.id.split_2);
		split_3=(View)view.findViewById(R.id.split_3);
		split_4=(View)view.findViewById(R.id.split_4);
		split_5=(View)view.findViewById(R.id.split_5);
		int resourceId = -1;
		int n = typedArray.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = typedArray.getIndex(i);
			switch (attr) {
			case R.styleable.CustomSectorView_sector_bottom_visible:
				resourceId = typedArray.getInt(
						R.styleable.CustomSectorView_sector_bottom_visible, 0);
				if (resourceId == 1) {
					bottomBtn.setVisibility(View.VISIBLE);
					unitNum++;
				} else {
					bottomBtn.setVisibility(View.INVISIBLE);
				}
				break;
			case R.styleable.CustomSectorView_sector_top_visible:
				resourceId = typedArray.getInt(
						R.styleable.CustomSectorView_sector_top_visible, 0);
				if (resourceId == 1) {
					topBtn.setVisibility(View.VISIBLE);
					unitNum++;
				} else {
					topBtn.setVisibility(View.INVISIBLE);
				}
				break;
			case R.styleable.CustomSectorView_sector_unit_num:
				resourceId = typedArray.getInt(
						R.styleable.CustomSectorView_sector_unit_num, 0);
				switch (resourceId) {
				case 1:
					firstBtn.setVisibility(View.VISIBLE);
					unitNum++;
					break;
				case 2:
					firstBtn.setVisibility(View.VISIBLE);
					secondBtn.setVisibility(View.VISIBLE);
					split_1.setVisibility(View.VISIBLE);
					unitNum = unitNum + 2;

					break;
				case 3:
					firstBtn.setVisibility(View.VISIBLE);
					split_1.setVisibility(View.VISIBLE);
					secondBtn.setVisibility(View.VISIBLE);
					split_2.setVisibility(View.VISIBLE);
					thirdBtn.setVisibility(View.VISIBLE);
					
					unitNum = unitNum + 3;
					break;
				case 4:
					firstBtn.setVisibility(View.VISIBLE);
					split_1.setVisibility(View.VISIBLE);
					secondBtn.setVisibility(View.VISIBLE);
					split_2.setVisibility(View.VISIBLE);
					thirdBtn.setVisibility(View.VISIBLE);
					split_3.setVisibility(View.VISIBLE);
					fourthBtn.setVisibility(View.VISIBLE);
					unitNum = unitNum + 4;
					break;
				case 5:
					firstBtn.setVisibility(View.VISIBLE);
					split_1.setVisibility(View.VISIBLE);
					secondBtn.setVisibility(View.VISIBLE);
					split_2.setVisibility(View.VISIBLE);
					thirdBtn.setVisibility(View.VISIBLE);
					split_3.setVisibility(View.VISIBLE);
					fourthBtn.setVisibility(View.VISIBLE);
					split_4.setVisibility(View.VISIBLE);
					fifthBtn.setVisibility(View.VISIBLE);
					unitNum = unitNum + 5;
					break;
				case 6:
					firstBtn.setVisibility(View.VISIBLE);
					split_1.setVisibility(View.VISIBLE);
					secondBtn.setVisibility(View.VISIBLE);
					split_2.setVisibility(View.VISIBLE);
					thirdBtn.setVisibility(View.VISIBLE);
					split_3.setVisibility(View.VISIBLE);
					fourthBtn.setVisibility(View.VISIBLE);
					split_4.setVisibility(View.VISIBLE);
					fifthBtn.setVisibility(View.VISIBLE);
					split_5.setVisibility(View.VISIBLE);
					sixBtn.setVisibility(View.VISIBLE);
					unitNum = unitNum + 6;
					break;
				default:
					break;
				}
			case R.styleable.CustomSectorView_sector_first_text:
				resourceId = typedArray.getResourceId(
						R.styleable.CustomSectorView_sector_first_text, 0);
				firstBtn.setText(resourceId > 0 ? typedArray.getResources()
						.getText(resourceId)
						: typedArray
								.getString(R.styleable.CustomSectorView_sector_first_text));
				break;
			case R.styleable.CustomSectorView_sector_second_text:
				resourceId = typedArray.getResourceId(
						R.styleable.CustomSectorView_sector_second_text, 0);
				secondBtn
						.setText(resourceId > 0 ? typedArray.getResources()
								.getText(resourceId)
								: typedArray
										.getString(R.styleable.CustomSectorView_sector_second_text));
				break;
			case R.styleable.CustomSectorView_sector_third_text:
				resourceId = typedArray.getResourceId(
						R.styleable.CustomSectorView_sector_third_text, 0);
				thirdBtn.setText(resourceId > 0 ? typedArray.getResources()
						.getText(resourceId)
						: typedArray
								.getString(R.styleable.CustomSectorView_sector_third_text));
				break;
			case R.styleable.CustomSectorView_sector_fourth_text:
				resourceId = typedArray.getResourceId(
						R.styleable.CustomSectorView_sector_fourth_text, 0);
				fourthBtn.setText(resourceId > 0 ? typedArray.getResources()
						.getText(resourceId)
						: typedArray
								.getString(R.styleable.CustomSectorView_sector_fourth_text));
				break;
			case R.styleable.CustomSectorView_sector_fifth_text:
				resourceId = typedArray.getResourceId(
						R.styleable.CustomSectorView_sector_fifth_text, 0);
				fifthBtn.setText(resourceId > 0 ? typedArray.getResources()
						.getText(resourceId)
						: typedArray
								.getString(R.styleable.CustomSectorView_sector_fifth_text));
				break;
			case R.styleable.CustomSectorView_sector_sixth_text:
				resourceId = typedArray.getResourceId(
						R.styleable.CustomSectorView_sector_sixth_text, 0);
				sixBtn.setText(resourceId > 0 ? typedArray.getResources()
						.getText(resourceId)
						: typedArray
						.getString(R.styleable.CustomSectorView_sector_sixth_text));
				break;
			case R.styleable.CustomSectorView_sector_top_text:
				resourceId = typedArray.getResourceId(
						R.styleable.CustomSectorView_sector_top_text, 0);
				topBtn.setText(resourceId > 0 ? typedArray.getResources()
						.getText(resourceId)
						: typedArray
								.getString(R.styleable.CustomSectorView_sector_top_text));
				break;
			case R.styleable.CustomSectorView_sector_bottom_text:
				resourceId = typedArray.getResourceId(
						R.styleable.CustomSectorView_sector_bottom_text, 0);
				bottomBtn
						.setText(resourceId > 0 ? typedArray.getResources()
								.getText(resourceId)
								: typedArray
										.getString(R.styleable.CustomSectorView_sector_bottom_text));
				break;
			}
		}
		init();
		addView(view);
	}

	private void init() {
		// TODO Auto-generated method stub
		callBack = new sectorBtnCallBack() {

			@Override
			public void onFinal() {
				// TODO Auto-generated method stub
				
			}
		};
		topBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.call_topBtnClick();
				callBack.onFinal();
			}
		});
		firstBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.call_firstBtnClick();
				callBack.onFinal();
			}
		});
		secondBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.call_secondBtnClick();
				callBack.onFinal();
			}
		});
		thirdBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.call_thirdBtnClick();
				callBack.onFinal();
			}
		});
		fourthBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.call_fourthBtnClick();
				callBack.onFinal();
			}
		});
		fifthBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.call_fifthBtnClick();
				callBack.onFinal();
			}
		});
		bottomBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.call_bottomBtnClick();
				callBack.onFinal();
			}
		});
		sixBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.call_sixthBtnClick();
				callBack.onFinal();
			}
		});
	}

	private sectorBtnCallBack callBack;

	public void setCallBack(sectorBtnCallBack callBack) {
		this.callBack = callBack;
	}

	/**
	 * 获取窗体高度
	 */
	public int getHeight_Px(Context context) {
		return Util.dip2px(context, 40)*unitNum+Util.dip2px(context,10)*2;
	}
}
