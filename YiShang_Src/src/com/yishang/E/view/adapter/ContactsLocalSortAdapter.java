package com.yishang.E.view.adapter;

import java.util.List;

import com.ruifeng.yishang.R;
import com.yishang.A.global.constant.CONSTANT;
import com.yishang.C.dao.daoModel.T_Contacts;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.SectionIndexer;
import android.widget.TextView;

/**
 * 本地通讯录名单的adapter
 * @author MM_Zerui
 * 
 */
public class ContactsLocalSortAdapter extends BaseAdapter implements SectionIndexer {
	private List<T_Contacts> list = null;
	private Context mContext;
//	private FriendsDaoImpl friendDaoImpl;

	public ContactsLocalSortAdapter(Context mContext, List<T_Contacts> list) {
		this.mContext = mContext;
		this.list = list;
//		friendDaoImpl = new FriendsDaoImpl();
	}

	/**
	 * 当ListView数据发生变化时,调用此方法来更新ListView
	 * 
	 * @param list
	 */
	public void updateListView(List<T_Contacts> list) {
		this.list = list;
		notifyDataSetChanged();
	}

	public int getCount() {
		return this.list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	public View getView(final int position, View view, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		final T_Contacts mContent = list.get(position);
		if (view == null) {
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(mContext).inflate(
					R.layout.contacts_local_item_layout, null);
			viewHolder.tvTitle = (TextView) view.findViewById(R.id.name);
			viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
			viewHolder.followBtn = (Button) view
					.findViewById(R.id.contactAddBtn);
			viewHolder.followNote = (TextView) view
					.findViewById(R.id.contactFollowText);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
//		// 注册状态
//		if (mContent.getIfRegister() == Integer
//				.valueOf(CONSTANT.DAO_CONTACTS_REGI)) {
//			// 判断和本地用户是否为关注状态
//			if (friendDaoImpl.checkFollowRelate(mContent.getUid())) {
//				// 如果已关注对方
//				viewHolder.followBtn.setVisibility(View.INVISIBLE);
//				viewHolder.followNote.setVisibility(View.VISIBLE);
//
//			} else {
//				viewHolder.followBtn.setVisibility(View.VISIBLE);
//				viewHolder.followNote.setVisibility(View.INVISIBLE);
//				viewHolder.followBtn.setOnClickListener(new OnClickListener() {
//
//					@Override
//					public void onClick(View v) {
//						// TODO Auto-generated method stub
//						if (mItemFollowClickListener != null) {
//							mItemFollowClickListener.click(position);
//						}
//					}
//				});
//			}
//		} else {
//			viewHolder.followBtn.setVisibility(View.INVISIBLE);
//			viewHolder.followNote.setVisibility(View.INVISIBLE);
//		}

		// 根据position获取分类的首字母的Char ascii值
		int section = getSectionForPosition(position);

		// 如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
		if (position == getPositionForSection(section)) {
			viewHolder.tvLetter.setVisibility(View.VISIBLE);
			viewHolder.tvLetter.setText(mContent.getFirstLetter());
		} else {
			viewHolder.tvLetter.setVisibility(View.GONE);
		}

		viewHolder.tvTitle.setText(this.list.get(position).getName());

		return view;

	}

	final static class ViewHolder {
		TextView tvLetter;
		TextView tvTitle;
		TextView followNote;
		Button followBtn;
	}

	/**
	 * 根据ListView的当前位置获取分类的首字母的Char ascii值
	 */
	public int getSectionForPosition(int position) {
		return list.get(position).getFirstLetter().charAt(0);
	}

	/**
	 * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
	 */
	public int getPositionForSection(int section) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = list.get(i).getFirstLetter();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}
		return -1;
	}

	private onItemFollowClickListener mItemFollowClickListener;

	public void setOnItemFollowClickListener(
			onItemFollowClickListener mItemFollowClickListener) {
		this.mItemFollowClickListener = mItemFollowClickListener;
	}

	public interface onItemFollowClickListener {
		void click(int position);
	}

	/**
	 * 提取英文的首字母，非英文字母用#代替。
	 * 
	 * @param str
	 * @return
	 */
	private String getAlpha(String str) {
		String sortStr = str.trim().substring(0, 1).toUpperCase();
		// 正则表达式，判断首字母是否是英文字母
		if (sortStr.matches("[A-Z]")) {
			return sortStr;
		} else {
			return "#";
		}
	}

	@Override
	public Object[] getSections() {
		return null;
	}
}