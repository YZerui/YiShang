package com.customview.view;

import com.customview.callBack.listItemCallBack;
import com.utils.Util;

import customview.library.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

/**
 * 自定义列表项视图
 * 
 * @author MM_Zerui
 * 
 */
public class CustomListItemView extends LinearLayout {
	public static final int MODE_FIX_SINGLE = 0;
	public static final int MODE_FIX_DOUBLE = 1;
	public static final int MODE_UNFIX = 2;

	private Context context;
	private View fixLayout, unFixLayout, bottomLayout;

	private ImageView unFix_icon;
	private TextView unFix_title, unFix_content, unFix_timer;

	private RelativeLayout fix_doubleLayout, fix_singleLayout, fixUnreadLayout,
			fixUnreadNumLayout;
	private View unFixItem, fixItem;
	private ImageView fix_icon, fix_more_note, fixUnreadNote, fixOuter,
			unFixOuter;
	private TextView fix_double_title, fix_double_content, fix_double_timer,
			fixUnreadNumText,fix_double_bottom_right_text;
	private TextView fix_single_title,fix_single_timer;

	private CheckBox checkBox_fileDownload, checkBox_select;

	private com.customview.callBack.listItemCallBack listItemCallBack;

	private TextView rightNoteText;
	
//	private TextView titleText2,titleText3,titleRightText,titleRightText2;

	private LinearLayout totalLayout;
	/** 底部控件 **/
//	private LinearLayout bottomLayout_1, bottomLayout_2, bottomLayout_3,
//			bottomLayout_4;
//	private ImageView bottomImg_1, bottomImg_2, bottomImg_3, bottomImg_4;
//	private ImageView bottomSqlit_1, bottomSqlit_2, bottomSqlit_3;
//	private TextView bottomText_1, bottomText_2, bottomText_3, bottomText_4;

	private View bottomSplitLine, fixSplitLine,unfixBottomLine;
	
	private TextView bottomText;
	
	private CustomBarView bottomBar;


	public void setCallBack(com.customview.callBack.listItemCallBack callBack) {
		this.listItemCallBack = callBack;
	}

	public CustomListItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context = context;
		listItemCallBack = new listItemCallBack() {
		};
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.custom_view_list_item, null);
		totalLayout=(LinearLayout)view.findViewById(R.id.totalLayout);
		fixLayout = (View) view.findViewById(R.id.fixLayout);
		unFixLayout = (View) view.findViewById(R.id.unFixLayout);
		fixItem = (RelativeLayout) view.findViewById(R.id.fixItem_layout);
		// unFixItem = (RelativeLayout) view
		// .findViewById(R.id.unFixItem);
		fix_doubleLayout = (RelativeLayout) view
				.findViewById(R.id.fixDouble_layout);
		fix_singleLayout = (RelativeLayout) view
				.findViewById(R.id.fixSingle_layout);
		fixOuter = (ImageView) view.findViewById(R.id.fixCropOuter);
		unFix_icon = (ImageView) view.findViewById(R.id.unfix_icon);
		unFix_title = (TextView) view.findViewById(R.id.unfix_title);
		unFix_content = (TextView) view.findViewById(R.id.unfix_content);
		unFix_timer = (TextView) view.findViewById(R.id.unfix_timer);
		unFixOuter = (ImageView) view.findViewById(R.id.unfix_outer);
		fix_icon = (ImageView) view.findViewById(R.id.fixIcon);
		fix_single_title = (TextView) view.findViewById(R.id.fixSingle_title);
		fix_single_timer=(TextView)view.findViewById(R.id.fixSingle_timer);
		fix_double_title = (TextView) view.findViewById(R.id.fixDouble_title);
		fix_double_content = (TextView) view
				.findViewById(R.id.fixDouble_content);
		fix_double_timer = (TextView) view.findViewById(R.id.fixDouble_timer);
		fix_more_note = (ImageView) view.findViewById(R.id.fixRight_img);

		checkBox_fileDownload = (CheckBox) view
				.findViewById(R.id.fixDown_checkbox);

		fixUnreadLayout = (RelativeLayout) view
				.findViewById(R.id.fix_unreadLayout);
		fixUnreadNote = (ImageView) view.findViewById(R.id.fix_unreadNote);
		fixUnreadNumLayout = (RelativeLayout) view
				.findViewById(R.id.fix_unreadNumLayout);
		fixUnreadNumText = (TextView) view.findViewById(R.id.fix_unreadNumText);
		checkBox_select = (CheckBox) view.findViewById(R.id.select_checkbox);
		rightNoteText = (TextView) view.findViewById(R.id.rightTextNote);

		
		bottomBar=(CustomBarView)view.findViewById(R.id.bottomBar);
		
		fixSplitLine = (View) view.findViewById(R.id.fix_split_part_line);
		unfixBottomLine=(View)view.findViewById(R.id.unfix_bottom_split_line);
		
		bottomText=(TextView)view.findViewById(R.id.bottom_text_content);
		
//		titleText2=(TextView)view.findViewById(R.id.fixDouble_title_2);
//		titleText3=(TextView)view.findViewById(R.id.fixDouble_title_3);
//		titleRightText=(TextView)view.findViewById(R.id.fixDouble_title_right);
//		titleRightText2=(TextView)view.findViewById(R.id.fixDouble_title_right_2);
		// bottomLayout=(View)view.findViewById(R.id.bottomLayout);
		fix_double_bottom_right_text=(TextView)view.findViewById(R.id.fixDouble_bottom_right_text);
		setFix_checkbox_download_file(false);
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.CustomListItemView);
		int resourceId = -1;
		int n = typedArray.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = typedArray.getIndex(i);
			switch (attr) {
			case R.styleable.CustomListItemView_list_item_height:
				int size = (int) typedArray.getDimension(
						R.styleable.CustomListItemView_list_item_height,
						Util.dip2px(context, 60));
				fixLayout.setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, size));
				break;
//			case R.styleable.CustomListItemView_list_item_split_visible:
//				
//				break;
			case R.styleable.CustomListItemView_LIST_ITEM_MODE:
				resourceId = typedArray.getInt(
						R.styleable.CustomListItemView_LIST_ITEM_MODE, 0);
				switch (resourceId) {
				case MODE_FIX_SINGLE:
					fixLayout.setVisibility(View.VISIBLE);
					unFixLayout.setVisibility(View.GONE);
					fix_singleLayout.setVisibility(View.VISIBLE);
					fix_doubleLayout.setVisibility(View.INVISIBLE);
					break;
				case MODE_FIX_DOUBLE:
					fixLayout.setVisibility(View.VISIBLE);
					unFixLayout.setVisibility(View.GONE);
					fix_singleLayout.setVisibility(View.INVISIBLE);
					fix_doubleLayout.setVisibility(View.VISIBLE);
					break;
				case MODE_UNFIX:
					fixLayout.setVisibility(View.INVISIBLE);
					unFixLayout.setVisibility(View.VISIBLE);
					break;

				default:
					break;
				}
				break;
			case R.styleable.CustomListItemView_list_item_unfix_icon:
				resourceId = typedArray.getResourceId(
						R.styleable.CustomListItemView_list_item_unfix_icon, 0);
				unFix_icon.setImageResource(resourceId > 0 ? resourceId
						: R.drawable.app_icon);
				break;
			case R.styleable.CustomListItemView_list_item_unfix_content:
				resourceId = typedArray.getResourceId(
						R.styleable.CustomListItemView_list_item_unfix_content,
						0);
				unFix_content
						.setText(resourceId > 0 ? typedArray.getResources()
								.getText(resourceId)
								: typedArray
										.getString(R.styleable.CustomListItemView_list_item_unfix_content));
				break;
			case R.styleable.CustomListItemView_list_item_unfix_timer:
				unFix_timer.setVisibility(View.VISIBLE);
				resourceId = typedArray
						.getResourceId(
								R.styleable.CustomListItemView_list_item_unfix_timer,
								0);
				unFix_timer
						.setText(resourceId > 0 ? typedArray.getResources()
								.getText(resourceId)
								: typedArray
										.getString(R.styleable.CustomListItemView_list_item_unfix_timer));
				break;
			case R.styleable.CustomListItemView_list_item_unfix_title:
				resourceId = typedArray
						.getResourceId(
								R.styleable.CustomListItemView_list_item_unfix_title,
								0);
				unFix_title
						.setText(resourceId > 0 ? typedArray.getResources()
								.getText(resourceId)
								: typedArray
										.getString(R.styleable.CustomListItemView_list_item_unfix_title));
				break;

			case R.styleable.CustomListItemView_list_item_fix_double_content:
				resourceId = typedArray
						.getResourceId(
								R.styleable.CustomListItemView_list_item_fix_double_content,
								0);
				fix_double_content
						.setText(resourceId > 0 ? typedArray.getResources()
								.getText(resourceId)
								: typedArray
										.getString(R.styleable.CustomListItemView_list_item_fix_double_content));
				break;
			case R.styleable.CustomListItemView_list_item_fix_double_timer:
				resourceId = typedArray
						.getResourceId(
								R.styleable.CustomListItemView_list_item_fix_double_timer,
								0);
				fix_double_timer
						.setText(resourceId > 0 ? typedArray.getResources()
								.getText(resourceId)
								: typedArray
										.getString(R.styleable.CustomListItemView_list_item_fix_double_timer));
				break;
			case R.styleable.CustomListItemView_list_item_fix_double_title:
				resourceId = typedArray
						.getResourceId(
								R.styleable.CustomListItemView_list_item_fix_double_title,
								0);
				fix_double_title
						.setText(resourceId > 0 ? typedArray.getResources()
								.getText(resourceId)
								: typedArray
										.getString(R.styleable.CustomListItemView_list_item_fix_double_title));
				break;
			case R.styleable.CustomListItemView_list_item_fix_icon:
				resourceId = typedArray.getResourceId(
						R.styleable.CustomListItemView_list_item_fix_icon, 0);
				fix_icon.setImageResource(resourceId > 0 ? resourceId
						: R.drawable.app_icon);
				break;
			case R.styleable.CustomListItemView_list_item_fix_single_title:
				resourceId = typedArray
						.getResourceId(
								R.styleable.CustomListItemView_list_item_fix_single_title,
								0);
				fix_single_title
						.setText(resourceId > 0 ? typedArray.getResources()
								.getText(resourceId)
								: typedArray
										.getString(R.styleable.CustomListItemView_list_item_fix_single_title));
				break;
			case R.styleable.CustomListItemView_list_item_more_note_icon:
				resourceId = typedArray
						.getInt(R.styleable.CustomListItemView_list_item_more_note_icon,
								0);
				if (resourceId == 1) {
					fix_more_note.setVisibility(View.VISIBLE);
				}
				break;
			case R.styleable.CustomListItemView_list_item_checkbox_download_file:
				resourceId = typedArray
						.getInt(R.styleable.CustomListItemView_list_item_checkbox_download_file,
								0);
				if (resourceId == 1) {
					checkBox_fileDownload.setVisibility(View.VISIBLE);
				}

				break;
			case R.styleable.CustomListItemView_list_item_fixLayout_bg:
				resourceId = typedArray.getResourceId(
						R.styleable.CustomListItemView_list_item_fixLayout_bg,
						0);
				fixItem.setBackgroundResource(resourceId);
				break;
			case R.styleable.CustomListItemView_list_item_unFixLlayout_bg:
				// resourceId = typedArray
				// .getResourceId(
				// R.styleable.CustomListItemView_list_item_unFixLlayout_bg,
				// 0);
				// unFixItem.setBackgroundResource(resourceId);
				break;
			case R.styleable.CustomListItemView_list_item_head_mode:
				resourceId = typedArray.getInt(
						R.styleable.CustomListItemView_list_item_head_mode, 0);
				if (resourceId == 1) {
					fixOuter.setBackgroundResource(R.drawable.clip_rect_bg);
					unFixOuter.setBackgroundResource(R.drawable.clip_rect_bg);
				} else {
					fixOuter.setBackgroundResource(R.drawable.list_crop_cricle);
					unFixOuter
							.setBackgroundResource(R.drawable.list_crop_cricle);
				}
				break;
			case R.styleable.CustomListItemView_list_item_checkbox_select:
				resourceId = typedArray
						.getInt(R.styleable.CustomListItemView_list_item_checkbox_select,
								0);
				if (resourceId == 1) {
					checkBox_select.setVisibility(View.VISIBLE);
				}
				break;
			case R.styleable.CustomListItemView_list_item_note_text_visible:
				resourceId = typedArray
						.getInt(R.styleable.CustomListItemView_list_item_note_text_visible,
								0);
				if (resourceId == 1) {
					rightNoteText.setVisibility(View.VISIBLE);
				} else {
					rightNoteText.setVisibility(View.INVISIBLE);
				}
				break;
			case R.styleable.CustomListItemView_list_item_right_note_text:
				resourceId = typedArray
						.getResourceId(
								R.styleable.CustomListItemView_list_item_right_note_text,
								0);
				rightNoteText
						.setText(resourceId > 0 ? typedArray.getResources()
								.getText(resourceId)
								: typedArray
										.getString(R.styleable.CustomListItemView_list_item_right_note_text));
				break;
			case R.styleable.CustomListItemView_list_item_note_text_color:
				resourceId = typedArray
						.getInt(R.styleable.CustomListItemView_list_item_note_text_color,
								0);
				switch (resourceId) {
				case 2:
					rightNoteText.setTextColor(getResources().getColor(
							R.color.color_theme));
					break;
				case 0:
					rightNoteText.setTextColor(getResources().getColor(
							R.color.text_color_level_one));
					break;
				case 1:
					rightNoteText.setTextColor(getResources().getColor(
							R.color.color_btn_green));
					break;
				default:
					break;
				}
				//底部bar单元数
			case R.styleable.CustomListItemView_list_item_bottom_unit_num:
				resourceId = typedArray
						.getInt(R.styleable.CustomListItemView_list_item_bottom_unit_num,
								0);
				bottomBar.onUnitNum(resourceId).setVisibility(View.VISIBLE);
				break;
			case R.styleable.CustomListItemView_list_item_bottom_visible:
				resourceId = typedArray
						.getInt(R.styleable.CustomListItemView_list_item_bottom_visible,
								0);
				if (resourceId == 1) {
					bottomBar.setVisibility(View.VISIBLE);
				} else {
					bottomBar.setVisibility(View.GONE);
				}
				break;
			case R.styleable.CustomListItemView_list_item_bottom_unit_img_1:
				resourceId = typedArray
						.getResourceId(
								R.styleable.CustomListItemView_list_item_bottom_unit_img_1,
								0);
				bottomBar.onImg_1(resourceId > 0 ? resourceId
						: R.drawable.app_icon);
				break;
			case R.styleable.CustomListItemView_list_item_bottom_unit_img_2:
				resourceId = typedArray
						.getResourceId(
								R.styleable.CustomListItemView_list_item_bottom_unit_img_2,
								0);
				bottomBar.onImg_2(resourceId > 0 ? resourceId
						: R.drawable.app_icon);
				break;
			case R.styleable.CustomListItemView_list_item_bottom_unit_img_3:
				resourceId = typedArray
						.getResourceId(
								R.styleable.CustomListItemView_list_item_bottom_unit_img_3,
								0);
				bottomBar.onImg_3(resourceId > 0 ? resourceId
						: R.drawable.app_icon);
				break;
			case R.styleable.CustomListItemView_list_item_bottom_unit_img_4:
				resourceId = typedArray
						.getResourceId(
								R.styleable.CustomListItemView_list_item_bottom_unit_img_4,
								0);
				bottomBar.onImg_4(resourceId > 0 ? resourceId
						: R.drawable.app_icon);
				break;
			case R.styleable.CustomListItemView_list_item_bottom_unit_text_1:
				resourceId = typedArray
						.getResourceId(
								R.styleable.CustomListItemView_list_item_bottom_unit_text_1,
								0);
				bottomBar.onText_1(
						resourceId > 0 ? typedArray.getResources()
								.getText(resourceId).toString()
								: typedArray
										.getString(R.styleable.CustomListItemView_list_item_bottom_unit_text_1).toString());
				break;
			case R.styleable.CustomListItemView_list_item_bottom_unit_text_2:
				resourceId = typedArray
						.getResourceId(
								R.styleable.CustomListItemView_list_item_bottom_unit_text_2,
								0);
				bottomBar.onText_2(resourceId > 0 ? typedArray.getResources()
								.getText(resourceId).toString()
								: typedArray
										.getString(R.styleable.CustomListItemView_list_item_bottom_unit_text_2).toString());

				break;
			case R.styleable.CustomListItemView_list_item_bottom_unit_text_3:
				resourceId = typedArray
						.getResourceId(
								R.styleable.CustomListItemView_list_item_bottom_unit_text_3,
								0);
				bottomBar.onText_3(resourceId > 0 ? typedArray.getResources()
								.getText(resourceId).toString()
								: typedArray
										.getString(R.styleable.CustomListItemView_list_item_bottom_unit_text_3).toString());

				break;
			case R.styleable.CustomListItemView_list_item_bottom_unit_text_4:
				resourceId = typedArray
						.getResourceId(
								R.styleable.CustomListItemView_list_item_bottom_unit_text_4,
								0);
				bottomBar.onText_4(resourceId > 0 ? typedArray.getResources()
								.getText(resourceId).toString()
								: typedArray
										.getString(R.styleable.CustomListItemView_list_item_bottom_unit_text_4).toString());
				break;
			case R.styleable.CustomListItemView_list_item_fix_split_line:
				resourceId = typedArray
						.getInt(R.styleable.CustomListItemView_list_item_fix_split_line,
								0);
				if (resourceId == 1) {
					fixSplitLine.setVisibility(View.VISIBLE);
					bottomSplitLine.setVisibility(View.GONE);
				} else {
					fixSplitLine.setVisibility(View.GONE);
				}
				break;
			case R.styleable.CustomListItemView_list_item_bottom_split_line:
				resourceId = typedArray
						.getInt(R.styleable.CustomListItemView_list_item_bottom_split_line,
								0);
				if (resourceId == 1) {
					bottomSplitLine.setVisibility(View.VISIBLE);
					fixSplitLine.setVisibility(View.GONE);
				} else {
					bottomSplitLine.setVisibility(View.GONE);
				}
				break;
			case R.styleable.CustomListItemView_list_item_content_clickable:
				//是否可触发
				resourceId = typedArray.getInt(
						R.styleable.CustomListItemView_list_item_content_clickable, 0);
				if(resourceId==1){
					fixItem.setBackgroundResource(R.drawable.list_item_bg);
				}else {
					fixItem.setBackgroundResource(R.drawable.list_null_selector);
				}
				break;
//			case R.styleable.CustomListItemView_list_item_total_clickable:
//				resourceId = typedArray.getInt(
//						R.styleable.CustomListItemView_list_item_total_clickable, 0);
//				if(resourceId==1){
//					totalLayout.setBackgroundResource(R.drawable.list_item_bg);
//				}else {
//					totalLayout.setBackgroundResource(R.drawable.list_null_selector);
//				}
//				break;
			}
		}
		listener();
		addView(view);
	}

	/**
	 * 监听文档下载按钮事件
	 */
	private void listener() {
		// TODO Auto-generated method stub
		checkBox_fileDownload.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (checkBox_fileDownload.isChecked()) {
					listItemCallBack.checkbox_downfile_checkListener(true);
				} else {
					listItemCallBack.checkbox_downfile_checkListener(false);
				}
			}
		});
		checkBox_select.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (checkBox_select.isChecked()) {
					listItemCallBack.checkbox_select_callBack(true);
				} else {
					listItemCallBack.checkbox_select_callBack(false);
				}
			}
		});

		
	}

	/**
	 * 设定列表样式
	 * 
	 * @param MODE
	 * @return
	 */
	public CustomListItemView setItemMode(int MODE) {
		switch (MODE) {
		case MODE_FIX_SINGLE:
			fixLayout.setVisibility(View.VISIBLE);
			unFixLayout.setVisibility(View.INVISIBLE);
			fix_singleLayout.setVisibility(View.VISIBLE);
			break;
		case MODE_FIX_DOUBLE:
			fixLayout.setVisibility(View.VISIBLE);
			unFixLayout.setVisibility(View.INVISIBLE);
			fix_doubleLayout.setVisibility(View.VISIBLE);
			break;
		case MODE_UNFIX:
			fixLayout.setVisibility(View.INVISIBLE);
			unFixLayout.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
		return this;
	}

	public CustomListItemView setUnfix_icon(int img) {
		unFix_icon.setImageResource(img);
		return this;
	}

	public CustomListItemView setUnfix_title(String title) {
		unFix_title.setText(title);
		return this;
	}

	public CustomListItemView setUnfix_content(String content) {
		unFix_content.setText(content);
		return this;
	}

	public CustomListItemView setUnfix_timer(String timer) {
		unFix_timer.setVisibility(View.VISIBLE);
		unFix_timer.setText(timer);
		return this;
	}

	public CustomListItemView setFix_icon(int img) {
		fix_icon.setImageResource(img);
		return this;
	}

	public CustomListItemView setFixSingle_title(String title) {
		fix_single_title.setText(title);
		return this;
		
	}
	public CustomListItemView setFixSingle_timer(String title) {
		fix_single_timer.setText(title);
		return this;
		
	}

	public CustomListItemView setFixDouble_title(String title) {
		fix_double_title.setText(title);
		return this;
	}

	public CustomListItemView setFixDouble_content(String content) {
		fix_double_content.setText(content);
		return this;
	}

	public CustomListItemView setFixDouble_timer(String timer) {
		fix_double_timer.setVisibility(View.VISIBLE);
		fix_double_timer.setText(timer);
		return this;
	}

	/**
	 * 设定箭头图标的可见性
	 * 
	 * @param visible
	 * @return
	 */
	public CustomListItemView setFix_more_note_visibility(boolean visible) {
		if (visible) {
			fix_more_note.setVisibility(View.VISIBLE);
		} else {
			fix_more_note.setVisibility(View.INVISIBLE);
		}
		return this;
	}

	/**
	 * 设定文档下载图标的可见性
	 * 
	 * @param visible
	 * @return
	 */
	public CustomListItemView setFix_checkbox_download_file(boolean visible) {
		if (visible) {
			checkBox_fileDownload.setVisibility(View.VISIBLE);
		} else {
			checkBox_fileDownload.setVisibility(View.INVISIBLE);
		}
		return this;
	}

	public ImageView getFixIcon() {
		return fix_icon;
	}

	public ImageView getUnFixIcon() {
		return unFix_icon;
	}
	public CustomBarView getBottomBar(){
		return bottomBar;
	}
	/**
	 * 显示未读数（精确到个位）
	 * 
	 * @param num
	 * @return
	 */
	public CustomListItemView setUnReadNum(String num) {
		fixUnreadLayout.setVisibility(View.VISIBLE);
		fixUnreadNumLayout.setVisibility(View.VISIBLE);
		fixUnreadNote.setVisibility(View.INVISIBLE);
		fixUnreadNumText.setText(num);
		return this;
	}

	/**
	 * 显示未读标识（未读数超过十）
	 * 
	 * @return
	 */
	public CustomListItemView setUnReadNote() {
		fixUnreadLayout.setVisibility(View.VISIBLE);
		fixUnreadNote.setVisibility(View.VISIBLE);
		fixUnreadNumLayout.setVisibility(View.INVISIBLE);
		return this;
	}

	/**
	 * 设定未读布局的可见性
	 * 
	 * @param visible
	 * @return
	 */
	public CustomListItemView setUnReadLayoutVisible(boolean visible) {
		if (visible) {
			fixUnreadLayout.setVisibility(View.VISIBLE);
		} else {
			fixUnreadLayout.setVisibility(View.INVISIBLE);
		}
		return this;
	}

	public CustomListItemView setRightImg(int img) {
		fix_more_note.setVisibility(View.VISIBLE);
		fix_more_note.setImageResource(img);
		return this;
	}

	public CustomListItemView setRectOuter() {
		fixOuter.setBackgroundResource(R.drawable.clip_rect_bg);
		return this;
	}

	public CustomListItemView setRoundOuter() {
		fixOuter.setBackgroundResource(R.drawable.list_crop_cricle);
		return this;
	}

	public CustomListItemView setCheckSelectVisible(boolean visible) {
		if (visible) {
			checkBox_select.setVisibility(View.VISIBLE);
		} else {
			checkBox_select.setVisibility(View.INVISIBLE);
		}
		return this;
	}

	public CustomListItemView setCheckSelect(boolean ifCheck) {
		if (ifCheck) {
			checkBox_select.setChecked(true);
		} else {
			checkBox_select.setChecked(false);
		}
		return this;
	}

	public CustomListItemView setRightNoteText(String str) {
		rightNoteText.setText(str);
		return this;
	}

	public CustomListItemView setRightNoteColor_uncheck() {
		rightNoteText.setTextColor(getResources().getColor(
				R.color.text_color_level_one));
		return this;
	}

	public CustomListItemView setRightNoteColor_check() {
		rightNoteText.setTextColor(getResources().getColor(
				R.color.color_btn_gray));
		return this;
	}

	public CustomListItemView setRightNoteColor_warn() {
		rightNoteText
				.setTextColor(getResources().getColor(R.color.color_theme));
		return this;
	}

	public CustomListItemView setRightNoteVisible(boolean visible) {
		if (visible) {
			rightNoteText.setVisibility(View.VISIBLE);
		} else {
			rightNoteText.setVisibility(View.INVISIBLE);
		}
		return this;
	}
	
	//关于底部条的设计
	public CustomListItemView onBottomBarVisible(boolean visible){
		if(visible){
			bottomLayout.setVisibility(View.VISIBLE);
			unfixBottomLine.setVisibility(View.VISIBLE);
		}else {
			bottomLayout.setVisibility(View.INVISIBLE);
			unfixBottomLine.setVisibility(View.GONE);
		}
		return this;
	}
	
	
	public CustomListItemView setBottomTextContent(String str){
		bottomText.setVisibility(View.VISIBLE);
		bottomText.setText(str);
		return this;
	}
//	
//	public CustomListItemView onDoubleTitle_left2(String str){
//		titleText2.setText(str);
//		titleText2.setVisibility(View.VISIBLE);
//		return this;
//	}
//	public CustomListItemView onDoubleTitle_left2_size(int sizeDp){
////		titleText2.setTextSize(Util.dip2px(context,sizeDp));
//		titleText2.setTextSize(TypedValue.COMPLEX_UNIT_PX,Util.dip2px(context,sizeDp));
//		return this;
//	}
//	public CustomListItemView onDoubleTitle_left2_color(int color){
//		titleText2.setTextColor(color);
//		return this;
//	}
//	
//	
//	public CustomListItemView onDoubleTitle_left3(String str){
//		titleText3.setText(str);
//		titleText3.setVisibility(View.VISIBLE);
//		return this;
//	}
//	public CustomListItemView onDoubleTitle_left3_size(int sizeDp){
////		titleText3.setTextSize(Util.dip2px(context, sizeDp));
//		titleText3.setTextSize(TypedValue.COMPLEX_UNIT_PX,Util.dip2px(context,sizeDp));
//		return this;
//	}
//	public CustomListItemView onDoubleTitle_left3_color(int color){
//		titleText3.setTextColor(color);
//		return this;
//	}
//	
//	
//	public CustomListItemView onDoubleTitle_right(String str){
//		titleRightText.setText(str);
//		titleRightText.setVisibility(View.VISIBLE);
//		return this;
//	}
//	public CustomListItemView onDoubleTitle_right_size(int sizeDp){
////		titleRightText.setTextSize(Util.dip2px(context, sizeDp));
//		titleRightText.setTextSize(TypedValue.COMPLEX_UNIT_PX,Util.dip2px(context,sizeDp));
//		return this;
//	}
//	public CustomListItemView onDoubleTitle_right_color(int color){
//		titleRightText.setTextColor(color);
//		return this;
//	}
//	
//	
//	public CustomListItemView onDoubleTitle_right2(String str){
//		titleRightText2.setText(str);
//		titleRightText2.setVisibility(View.VISIBLE);
//		return this;
//	}
//	public CustomListItemView onDoubleTitle_right2_size(int sizeDp){
////		titleRightText2.setTextSize(Util.dip2px(context, sizeDp));
//		titleRightText2.setTextSize(TypedValue.COMPLEX_UNIT_PX,Util.dip2px(context,sizeDp));
//		return this;
//	}
//	public CustomListItemView onDoubleTitle_right2_color(int color){
//		titleRightText2.setTextColor(color);
//		return this;
//	}
	
	public CustomListItemView onDoubleBRText(String str){
		fix_double_bottom_right_text.setVisibility(View.VISIBLE);
		fix_double_bottom_right_text.setText(str);
		return this;
	}
	
	public CustomListItemView onDoubleBRTextColor(int color){
		fix_double_bottom_right_text.setTextColor(getResources().getColor(color));
		return this;
	}
	
	public TextView getDoubleBRText(){
		return fix_double_bottom_right_text;
	}
}
