package com.customview.adapter;

import java.util.ArrayList;

import com.customview.model.item;

import customview.library.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PopAdapter extends BaseAdapter{
	private LayoutInflater inflater;
	private ArrayList<item> list;
	public PopAdapter(Context context) {
		inflater = LayoutInflater.from(context);
	}
	public void setItem(ArrayList<item> list){
		this.list=list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		item  item=list.get(position);
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.pop_item_layout, null);
			holder.img=(ImageView)convertView.findViewById(R.id.img);
			holder.text=(TextView)convertView.findViewById(R.id.text);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if(item.getImgR()==-1){
			holder.img.setVisibility(View.GONE);
		}else {
			holder.img.setVisibility(View.VISIBLE);
			holder.img.setImageResource(item.getImgR());
		}
		holder.text.setText(item.getTextStr());
		return convertView;
	}
	
	public class ViewHolder{
		public ImageView img;
		public TextView text;
	}
	

}
