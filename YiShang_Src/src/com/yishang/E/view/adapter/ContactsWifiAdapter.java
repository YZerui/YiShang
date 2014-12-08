package com.yishang.E.view.adapter;

import java.util.ArrayList;


import com.customview.callBack.listItemCallBack;
import com.customview.view.CustomListItemView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.ruifeng.yishang.R;
import com.yishang.A.global.application.T_UserPoint;
import com.yishang.A.global.baseClass.SuperAdapter;
import com.yishang.A.global.baseClass.SuperViewHolder;
import com.yishang.A.global.writting.W_UserIfo;
import com.yishang.B.module.a.MsgEntity.MsgBean;
import com.yishang.B.module.b.ContactsEntity.Recv_wifiUser;
import com.yishang.E.view.adapter.ContactsNearbyAdapter.ItemCallBack;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

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
//		.displayer(new FadeInBitmapDisplayer(600))
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
			convertView = inflater.inflate(R.layout.wifi_item_layout, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.refreshData(position);
		return convertView;
	}
	public static abstract class ItemCallBack{
		public abstract void call_select(boolean ifCheck,int position);
	}
	public final class ViewHolder extends SuperViewHolder{
		private TextView title;
		private TextView content;
		private ImageView icon;
		private ImageAware imageAware;
		private CheckBox checkBox;
		public ViewHolder(View parent){
			initView(parent);
		}
		@Override
		public void initView(View parent) {
			// TODO Auto-generated method stub
			title=(TextView)parent.findViewById(R.id.fixDouble_title);
			content=(TextView)parent.findViewById(R.id.fixDouble_content);
			icon=(ImageView)parent.findViewById(R.id.fixIcon);
			checkBox=(CheckBox)parent.findViewById(R.id.select_checkbox);
			imageAware=new ImageViewAware(icon, false);
		}

		@Override
		public void refreshData(final int position) {
			// TODO Auto-generated method stub
			Recv_wifiUser bean = datas.get(position);
			title.setText(bean.getUser_name());
			content.setText(W_UserIfo.label(bean.getUser_lable()));
			imageLoader.displayImage(bean.getUser_head(),imageAware,loadOptions);
			checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					itemCallBack.call_select(isChecked,position);
				}
			});
			if(bean.isSelect()){
				checkBox.setChecked(true);
				itemCallBack.call_select(true,position);
			}else {
				checkBox.setChecked(false);
				itemCallBack.call_select(false,position);
			}
		}
	}
}
