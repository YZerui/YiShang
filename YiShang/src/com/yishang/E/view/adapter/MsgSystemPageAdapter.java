package com.yishang.E.view.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.customview.view.CustomListItemView;
import com.ruifeng.yishang.R;
import com.yishang.A.global.baseClass.SuperAdapter;
import com.yishang.A.global.writting.W_Msg;
import com.yishang.C.dao.daoModel.T_Msg;
import com.yishang.E.view.adapter.RecvBusinessItemAdapter.ViewHolder;
import com.yishang.Z.utils.FormatUtils;

public class MsgSystemPageAdapter extends SuperAdapter {
	private List<T_Msg> list;
	private LayoutInflater inflater;
	Context context;
	public MsgSystemPageAdapter(Context context) {
		this.context=context;
		inflater = LayoutInflater.from(context);
	}
	public void setDatas(List<T_Msg> list){
		this.list=list;
	}
	public void updataListView(List<T_Msg> list){
		this.list=list;
		notifyDataSetChanged();
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
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.msg_page_system_list_item, null);
			holder.item=(CustomListItemView)convertView.findViewById(R.id.item);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		T_Msg msg=list.get(position);
		holder.item.getFixIcon().setImageResource(R.drawable.msg_system_icon);
		holder.item.setFixSingle_title("ϵͳ֪ͨ");
		holder.item.setFixSingle_timer(FormatUtils.getListItemTime(msg.getMsg_time()));
		holder.item.setBottomTextContent(msg.getMsg_content());
		return convertView;
	}

	public final class ViewHolder {
		public CustomListItemView item;
	}
}
