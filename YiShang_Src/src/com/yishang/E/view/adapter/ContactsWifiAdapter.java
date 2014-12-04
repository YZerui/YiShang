package com.yishang.E.view.adapter;

import java.util.ArrayList;


import com.customview.callBack.listItemCallBack;
import com.customview.view.CustomListItemView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.ruifeng.yishang.R;
import com.yishang.A.global.baseClass.SuperAdapter;
import com.yishang.B.module.a.MsgEntity.MsgBean;
import com.yishang.B.module.b.ContactsEntity.Recv_wifiUser;
import com.yishang.E.view.adapter.ContactsNearbyAdapter.ItemCallBack;

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
 * 显示消息列表的Adapter
 * @author MM_Zerui 
 */
public class ContactsWifiAdapter extends SuperAdapter {
	private LayoutInflater inflater;
	private ArrayList<Recv_wifiUser> datas;
	private ItemCallBack itemCallBack;

	public ContactsWifiAdapter(Context context) {
		// TODO Auto-generated constructor stub
		super();
		inflater = LayoutInflater.from(context);
		loadOptions= new DisplayImageOptions.Builder()			
		.showImageForEmptyUri(R.drawable.default_img_head)	
		.showImageOnFail(R.drawable.default_img_head)
		.showImageForEmptyUri(R.drawable.default_img_head)
		.displayer(new FadeInBitmapDisplayer(600))
//		.showStubImage(R.drawable.default_img_msg)
//		.showStubImage(R.drawable.app_icon)
		.cacheInMemory(true)						
		.cacheOnDisc(true)						
		.build();	
	}

	public void setData(ArrayList<Recv_wifiUser> datas) {
		this.datas = datas;
	}
	
	public void updateListView(ArrayList<Recv_wifiUser> datas){
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
	public void setItemCallBack(ItemCallBack itemCallBack){
		this.itemCallBack=itemCallBack;
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
			convertView = inflater.inflate(R.layout.contacts_wifi_item_layout, null);
			holder.item = (CustomListItemView) convertView
					.findViewById(R.id.item);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Recv_wifiUser bean = datas.get(position);
		holder.item.setFixDouble_title(bean.getUser_name());
		holder.item.setFixDouble_content(bean.getUser_title1());
		holder.item.setCallBack(new listItemCallBack() {
			@Override
			public void checkbox_select_callBack(boolean ifCheck) {
				// TODO Auto-generated method stub
				super.checkbox_select_callBack(ifCheck);
				itemCallBack.call_select(ifCheck,position);
			}
		});
		if(bean.isSelect()){
			holder.item.setCheckSelect(true);
		}else {
			holder.item.setCheckSelect(false);
		}
		itemCallBack.call_select(bean.isSelect(),position);
		imageLoader.displayImage(bean.getUser_head(), holder.item.getFixIcon(),loadOptions);
		return convertView;
	}
	public static abstract class ItemCallBack{
		public abstract void call_select(boolean ifCheck,int position);
	}
	public final class ViewHolder {
		public CustomListItemView item;
	}
}
