package com.yishang.E.view.adapter;

import java.util.List;


import com.customview.view.CustomListItemView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.ruifeng.yishang.R;
import com.yishang.A.global.baseClass.SuperAdapter;
import com.yishang.A.global.writting.W_UserIfo;
import com.yishang.C.dao.daoModel.T_Relationships;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 显示联系列表的Adapter
 * @author MM_Zerui 
 */
public class ContactsSearchAdapter extends SuperAdapter{
	private LayoutInflater inflater;
	private List<T_Relationships> datas;


	public ContactsSearchAdapter(Context context) {
		// TODO Auto-generated constructor stub
		inflater = LayoutInflater.from(context);
		loadOptions= new DisplayImageOptions.Builder()			
		.showImageForEmptyUri(R.drawable.default_img_head)	
		.showImageOnFail(R.drawable.default_img_head)
		.showImageForEmptyUri(R.drawable.default_img_head)
		.cacheInMemory(true)						
		.cacheOnDisc(true)						
		.build();	
	}

	public void setData(List<T_Relationships> datas) {
		this.datas = datas;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.contacts_search_page_list_item, null);
			holder.item=(CustomListItemView)convertView.findViewById(R.id.item);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		T_Relationships bean = datas.get(position);
		holder.item.setFixDouble_title(bean.getRela_name());
		holder.item.setFixDouble_content(W_UserIfo.rank(bean.getRela_rank()));
		imageLoader.displayImage(bean.getRela_head(), holder.item.getFixIcon(), loadOptions, null);
		return convertView;
	}
	private final static class ViewHolder {
		public CustomListItemView item;
	}
}
