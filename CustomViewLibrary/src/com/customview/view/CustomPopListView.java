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

	// �б����ļ��
	protected final int LIST_PADDING = 10;

	// ʵ����һ������
	private Rect mRect = new Rect();

	// �����λ�ã�x��y��
	private final int[] mLocation = new int[2];

	// ��Ļ�Ŀ�Ⱥ͸߶�
	private int mScreenWidth, mScreenHeight;

	// �ж��Ƿ���Ҫ��ӻ�����б�������
	private boolean mIsDirty;

	// λ�ò�������
	private int popupGravity = Gravity.NO_GRAVITY;

	// ����������ѡ��ʱ�ļ���
	private OnItemOnClickListener mItemOnClickListener;

	// �����б����
	private ListView mListView;

	private PopAdapter adapter;

	private ArrayList<item> list;

	// ���嵯���������б�
	// private ArrayList<ActionItem> mActionItems = new ArrayList<ActionItem>();

	public CustomPopListView(Context context) {
		// ���ò��ֵĲ���
		this(context, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	}

	public CustomPopListView(Context context, int width, int height) {
		this.mContext = context;

		// ���ÿ��Ի�ý���
		setFocusable(true);
		// ���õ����ڿɵ��
		setTouchable(true);
		// ���õ�����ɵ��
		setOutsideTouchable(true);

		// �����Ļ�Ŀ�Ⱥ͸߶�
		mScreenWidth = Util.getScreenWidth(mContext);
		mScreenHeight = Util.getScreenHeight(mContext);

		// ���õ����Ŀ�Ⱥ͸߶�
		setWidth(width);
		setHeight(height);

		setBackgroundDrawable(new BitmapDrawable());

		// ���õ����Ĳ��ֽ���
		setContentView(LayoutInflater.from(mContext).inflate(
				R.layout.custom_pop_list_view, null));

		initUI();
	}

	/**
	 * ��ʼ�������б�
	 */
	private void initUI() {
		adapter = new PopAdapter(mContext);
		list = new ArrayList<item>();

		mListView = (ListView) getContentView().findViewById(R.id.popList);

		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {
				// ���������󣬵�����ʧ
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
	 * ��ʾ�����б����
	 */
	public void show(View view) {
		// ��õ����Ļ��λ������
		view.getLocationOnScreen(mLocation);

		// ���þ��εĴ�С
		mRect.set(mLocation[0], mLocation[1], mLocation[0] + view.getWidth(),
				mLocation[1] + view.getHeight());

		// �ж��Ƿ���Ҫ��ӻ�����б�������
		populateActions();

		// ��ʾ������λ��
		showAtLocation(view, popupGravity, mScreenWidth - LIST_PADDING
				- (getWidth() / 2), mRect.bottom);
	}

	/**
	 * ���õ����б�����
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
	 * ���ü����¼�
	 */
	public void setItemOnClickListener(
			OnItemOnClickListener onItemOnClickListener) {
		this.mItemOnClickListener = onItemOnClickListener;
	}

	/**
	 * @author yangyu �������������������ť�����¼�
	 */
	public static interface OnItemOnClickListener {
		public void onItemClick(item item, int position);
	}
}
