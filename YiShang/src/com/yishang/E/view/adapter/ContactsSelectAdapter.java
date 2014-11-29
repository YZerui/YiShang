package com.yishang.E.view.adapter;

import java.util.List;


import com.customview.callBack.listItemCallBack;
import com.customview.view.CustomListItemView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.ruifeng.yishang.R;
import com.yishang.A.global.baseClass.SuperAdapter;
import com.yishang.B.module.a.MsgEntity.MsgBean;
import com.yishang.C.dao.daoModel.T_Relationships;

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
 * 选择人脉列表的Adapter
 * @author MM_Zerui 
 */
public class ContactsSelectAdapter extends SuperAdapter {
	private LayoutInflater inflater;
	private List<T_Relationships> datas;
	private ItemCallBack itemCallBack;

	public ContactsSelectAdapter(Context context) {
		// TODO Auto-generated constructor stub
		super();
		inflater = LayoutInflater.from(context);
		loadOptions= new DisplayImageOptions.Builder()			
		.showImageForEmptyUri(R.drawable.default_img_head)	
		.showImageOnFail(R.drawable.default_img_head)
		.showImageForEmptyUri(R.drawable.default_img_head)
//		.showStubImage(R.drawable.default_img_msg)
//		.showStubImage(R.drawable.app_icon)
		.cacheInMemory(true)						
		.cacheOnDisk(true)			
		.considerExifParams(true)
		.build();	
	}
	public void setItemCallBack(ItemCallBack itemCallBack){
		this.itemCallBack=itemCallBack;
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
			convertView = inflater.inflate(R.layout.contacts_nearby_item_layout,null);
			holder.item = (CustomListItemView) convertView
					.findViewById(R.id.item);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		T_Relationships bean = datas.get(position);
		holder.item.setFixDouble_title(bean.getRela_name());
		holder.item.setFixDouble_content(bean.getRela_rank());
		ImageLoader.getInstance().displayImage(bean.getRela_head(),
				holder.item.getFixIcon(),loadOptions);
		holder.item.setCallBack(new listItemCallBack() {
			@Override
			public void checkbox_select_callBack(boolean ifCheck) {
				// TODO Auto-generated method stub
				super.checkbox_select_callBack(ifCheck);
				itemCallBack.call_select(ifCheck,position);
			}
		});
		if(bean.isItem_select()){
			holder.item.setCheckSelect(true);
			itemCallBack.call_select(true,position);
		}else {
			holder.item.setCheckSelect(false);
			itemCallBack.call_select(false,position);
		}
		return convertView;
	}
	public static abstract class ItemCallBack{
		public abstract void call_select(boolean ifCheck,int position);
	}
	public final class ViewHolder {
		public CustomListItemView item;
	}
}
