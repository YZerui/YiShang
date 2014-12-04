package com.yishang.E.view.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.customview.view.CustomListItemView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.ruifeng.yishang.R;
import com.yishang.A.global.baseClass.SuperAdapter;
import com.yishang.B.module.d.BusinessEntity.Recv_comBook;
import com.yishang.C.dao.daoModel.T_Resource;
import com.yishang.E.view.adapter.ResourceDocItemAdapter.ViewHolder;

/**
 * 显示企业文档的项适配器
 * @author MM_Zerui
 *
 */
public class ResourceComBookAdapter extends SuperAdapter{
	private ArrayList<Recv_comBook> list;
	private LayoutInflater inflater;
	private Context context;
	public ResourceComBookAdapter(Context context){
		this.context=context;
		inflater = LayoutInflater.from(context);
		loadOptions= new DisplayImageOptions.Builder()			
		.showImageForEmptyUri(R.drawable.default_load_img)	
		.showImageOnFail(R.drawable.default_load_img)
		.showImageForEmptyUri(R.drawable.default_load_img)
		.cacheInMemory(true)						
		.cacheOnDisc(true)						
		.build();	
	}
	public void setData(ArrayList<Recv_comBook> list) {
		this.list = list;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.business_book_item_layout, null);
			holder.item=(CustomListItemView)convertView.findViewById(R.id.item);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Recv_comBook bean=list.get(position);
		holder.item.setFixDouble_title(bean.getBook_name());
		holder.item.setFixDouble_content(bean.getBook_sum());
//		imageLoader.displayImage(null, imageAware, options)
		holder.item.setFix_icon(R.drawable.default_load_img);
		return convertView;
	}
	public final class ViewHolder {
		CustomListItemView item;
	}
}
