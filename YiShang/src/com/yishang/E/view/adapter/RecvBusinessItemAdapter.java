package com.yishang.E.view.adapter;

import java.util.ArrayList;


import com.customview.view.CustomListItemView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.ruifeng.yishang.R;
import com.yishang.A.global.baseClass.SuperAdapter;
import com.yishang.B.module.a.MsgEntity.MsgBean;
import com.yishang.B.module.d.BusinessEntity.Recv_business;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 显示企业搜索列表的Adapter
 * @author MM_Zerui 
 */
public class RecvBusinessItemAdapter extends SuperAdapter {
	private LayoutInflater inflater;
	private ArrayList<Recv_business> datas;


	public RecvBusinessItemAdapter(Context context) {
		// TODO Auto-generated constructor stub
		super();
		inflater = LayoutInflater.from(context);
		loadOptions= new DisplayImageOptions.Builder()			
		.showImageForEmptyUri(R.drawable.msg_default_icon)	
		.showImageOnFail(R.drawable.msg_default_icon)
		.showImageForEmptyUri(R.drawable.msg_default_icon)
		.cacheInMemory(true)						
		.cacheOnDisc(true)						
		.build();	
	}

	public void setData(ArrayList<Recv_business> datas) {
		this.datas = datas;
	}
	
	public void updateListView(ArrayList<Recv_business> datas){
		this.datas=datas;
		notifyDataSetChanged();
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
			convertView = inflater.inflate(R.layout.business_recv_list_item, null);
			holder.item=(CustomListItemView)convertView.findViewById(R.id.customListItem);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Recv_business bean = datas.get(position);
		holder.item.setFixDouble_title(bean.getCom_abb());
		holder.item.setFixDouble_content(bean.getCom_name());
		imageLoader.displayImage(bean.getCom_logo(), holder.item.getFixIcon(), loadOptions, null);
		return convertView;
	}

	public final class ViewHolder {
		public CustomListItemView item;
	}
}
