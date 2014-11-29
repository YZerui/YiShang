package com.customview.view;

import java.util.ArrayList;

import com.customview.adapter.PopAdapter;
import com.customview.model.item;
import com.utils.Util;

import customview.library.R;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;

public class CustomPopListView extends PopupWindow {
	private Context mContext;

	// 列表弹窗的间隔
	protected final int LIST_PADDING = 10;

	// 实例化一个矩形
	private Rect mRect = new Rect();

	// 坐标的位置（x、y）
	private final int[] mLocation = new int[2];

	// 屏幕的宽度和高度
	private int mScreenWidth, mScreenHeight;

	// 判断是否需要添加或更新列表子类项
	private boolean mIsDirty;

	// 位置不在中心
	private int popupGravity = Gravity.NO_GRAVITY;

	// 弹窗子类项选中时的监听
	private OnItemOnClickListener mItemOnClickListener;

	// 定义列表对象
	private ListView mListView;

	private PopAdapter adapter;

	private ArrayList<item> list;

	// 定义弹窗子类项列表
	// private ArrayList<ActionItem> mActionItems = new ArrayList<ActionItem>();

	public CustomPopListView(Context context) {
		// 设置布局的参数
		this(context, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	}

	public CustomPopListView(Context context, int width, int height) {
		this.mContext = context;

		// 设置可以获得焦点
		setFocusable(true);
		// 设置弹窗内可点击
		setTouchable(true);
		// 设置弹窗外可点击
		setOutsideTouchable(true);

		// 获得屏幕的宽度和高度
		mScreenWidth = Util.getScreenWidth(mContext);
		mScreenHeight = Util.getScreenHeight(mContext);

		// 设置弹窗的宽度和高度
		setWidth(width);
		setHeight(height);

		setBackgroundDrawable(new BitmapDrawable());

		// 设置弹窗的布局界面
		setContentView(LayoutInflater.from(mContext).inflate(
				R.layout.custom_pop_list_view, null));

		initUI();
	}

	/**
	 * 初始化弹窗列表
	 */
	private void initUI() {
		adapter = new PopAdapter(mContext);
		list = new ArrayList<item>();

		mListView = (ListView) getContentView().findViewById(R.id.popList);

		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {
				// 点击子类项后，弹窗消失
				dismiss();

				if (mItemOnClickListener != null)
					mItemOnClickListener.onItemClick(list.get(index), index);
			}
		});
	}

	public CustomPopListView withItem(String text, int img) {
		list.add(new item(text, img));
		return this;
	}

	public CustomPopListView withItem(String text) {
		list.add(new item(text, -1));
		return this;
	}

	/**
	 * 显示弹窗列表界面
	 */
	public void show(View view) {
		// 获得点击屏幕的位置坐标
		view.getLocationOnScreen(mLocation);

		// 设置矩形的大小
		mRect.set(mLocation[0], mLocation[1], mLocation[0] + view.getWidth(),
				mLocation[1] + view.getHeight());

		// 判断是否需要添加或更新列表子类项
		populateActions();

		// 显示弹窗的位置
		showAtLocation(view, popupGravity, mScreenWidth - LIST_PADDING
				- (getWidth() / 2), mRect.bottom);
	}

	/**
	 * 设置弹窗列表子项
	 */
	private void populateActions() {
		mIsDirty = false;
		adapter.setItem(list);
		mListView.setAdapter(adapter);
	}
	public CustomPopListView setBg(int bgResource){
		mListView.setBackgroundResource(bgResource);
		return this;
	}
	/**
	 * 设置监听事件
	 */
	public void setItemOnClickListener(
			OnItemOnClickListener onItemOnClickListener) {
		this.mItemOnClickListener = onItemOnClickListener;
	}

	/**
	 * @author yangyu 功能描述：弹窗子类项按钮监听事件
	 */
	public static interface OnItemOnClickListener {
		public void onItemClick(item item, int position);
	}
}
