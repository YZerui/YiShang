package com.yishang.E.view.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.customview.view.CustomListItemView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.ruifeng.yishang.R;
import com.yishang.A.global.baseClass.SuperAdapter;
import com.yishang.A.global.constant.PROTOCOL;
import com.yishang.B.module.a.MsgEntity.MsgBean;

public class MsgDetailItemAdapter extends SuperAdapter{

	private LayoutInflater inflater;
	private ArrayList<MsgBean> datas;
	public MsgDetailItemAdapter(Context context) {
		// TODO Auto-generated constructor stub
		super();
		inflater = LayoutInflater.from(context);
		loadOptions= new DisplayImageOptions.Builder()			
		.showImageForEmptyUri(R.drawable.msg_default_icon)	
		.showImageOnFail(R.drawable.msg_default_icon)
		.showImageForEmptyUri(R.drawable.msg_default_icon)
//		.showStubImage(R.drawable.default_img_msg)
//		.showStubImage(R.drawable.app_icon)
		.cacheInMemory(true)						
		.cacheOnDisc(true)						
		.build();	
	}

	public void setData(ArrayList<MsgBean> datas) {
		this.datas = datas;
	}
	
	public void updateListView(ArrayList<MsgBean> datas){
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
			convertView = inflater.inflate(R.layout.msg_detail_page_list_item, null);
			holder.item=(CustomListItemView)convertView.findViewById(R.id.item);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		MsgBean bean = datas.get(position);
//		holder.item.getUnFixIcon().setImageResource(resId)
		holder.item.setUnfix_title(bean.getTitle());
		holder.item.setUnfix_content(bean.getContent());
//		imageLoader.displayImage(null, holder.icon, loadOptions, null);
		holder.item.setUnfix_timer("15:30");
		switch (bean.getStyle()) {
		case PROTOCOL.PUSH_ENTERPRISE:
			holder.item.getUnFixIcon().setImageResource(R.drawable.message_plus_favorite_normal);
			break;
		case PROTOCOL.PUSH_SYSTEM:
			holder.item.getUnFixIcon().setImageResource(R.drawable.app_icon);
			break;
		case PROTOCOL.PUSH_RECEIVE:
			holder.item.getUnFixIcon().setImageResource(R.drawable.message_plus_business_card_normal);
			break;
		default:
			break;
		}
		return convertView;
	}

	public final class ViewHolder {
		public ImageView icon;
		public TextView title;
		public TextView content;
		public TextView timer;
		public CustomListItemView item;
//		public ImageView unReadIcon;
// 		public ImageView unReadNumIcon;
//		public TextView unReadNumText;	
//		public RelativeLayout msgUnReadLayout;
	}
	
}
