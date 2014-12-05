package com.yishang.E.view.adapter;

import java.util.List;


import com.customview.view.CustomListItemView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.ruifeng.yishang.R;
import com.yishang.A.global.Enum.com.Enum_ComRela;
import com.yishang.A.global.Enum.com.Enum_ComType;
import com.yishang.A.global.baseClass.SuperAdapter;
import com.yishang.A.global.constant.DAO_CONSTANT;
import com.yishang.C.dao.daoModel.T_Company;
import com.yishang.C.dao.utils.DaoFormatUtil;
import com.yishang.E.view.swipelistview.StickyListHeadersAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 显示企业信息列表的Adapter
 * @author MM_Zerui 
 */
public class BusinessItemAdapter extends SuperAdapter implements StickyListHeadersAdapter {
	private LayoutInflater inflater;
	private List<T_Company> datas;


	public BusinessItemAdapter(Context context) {
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

	public void setData(List<T_Company> datas) {
		this.datas = datas;
	}
	
	public void updateListView(List<T_Company> datas){
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
			convertView = inflater.inflate(R.layout.business_page_list_item, null);
			holder.itemView=(CustomListItemView)convertView.findViewById(R.id.customListItem);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		T_Company bean = datas.get(position);
		holder.itemView.setFixDouble_title(bean.getCom_abb());
		holder.itemView.setFixDouble_content(bean.getCom_name());      
//		if(bean.getCom_state()==Enum_ComRela.CORRE_SUCCESS.value()){
//			holder.itemView.setRightNoteText("已关联").setRightNoteVisible(true);
//		}else if (bean.getCom_state()==Enum_ComRela.CORRE_ING.value()) {
//			holder.itemView.setRightNoteText("申请中").setRightNoteVisible(true);
//		}
//		else {
//			holder.itemView.setRightNoteVisible(false);
//		}
		imageLoader.displayImage(bean.getCom_icon(), holder.itemView.getFixIcon(), loadOptions);
		return convertView;
	}

	public final class ViewHolder {
		private CustomListItemView itemView;
	}

	@Override
	public View getHeaderView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		HeaderViewHolder holder;
		if (convertView == null) {
			holder = new HeaderViewHolder();
			convertView = inflater.inflate(R.layout.listview_header_item, parent, false);
			holder.text = (TextView) convertView.findViewById(R.id.text);
			convertView.setTag(holder);
		} else {
			holder = (HeaderViewHolder) convertView.getTag();
		}
		T_Company bean=datas.get(position);
		Enum_ComType enumType=Enum_ComType.DEFAULT.valueOf(bean.getCom_relate());
		switch (enumType) {
		case COM_CLIENT:
			holder.text.setText("客户");
			break;
		case COM_RELA:
			holder.text.setText("关联企业");
			break;
		case COM_SUPPLIER:
			holder.text.setText("我关注的");
			break;
//		case COM_RELA_ING:
//			holder.text.setText("申请关联中的企业");
//			break;
		default:
			holder.text.setText("企业");
			break;
		}
		
//		holder.text.setText(DaoFormatUtil.getRelaNote(datas.get(position).getRela_type()));
		return convertView;
	}

	@Override
	public long getHeaderId(int position) {
		// TODO Auto-generated method stub
		return datas.get(position).getCom_relate();
	}
	class HeaderViewHolder {

		TextView text;
	}
}
