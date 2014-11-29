package com.yishang.E.view.adapter;

import java.util.List;

import com.customview.model.item;
import com.customview.view.CustomListItemView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.ruifeng.yishang.R;
import com.yishang.A.global.Enum.constant.Enum_Color;
import com.yishang.A.global.baseClass.SuperAdapter;
import com.yishang.C.dao.daoModel.T_Resource;
import com.yishang.Z.utils.FormatUtils;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 显示消息列表的Adapter
 * 
 * @author MM_Zerui
 */
public class ResourceDocItemAdapter extends SuperAdapter {
	private LayoutInflater inflater;
	private List<T_Resource> datas;

	public ResourceDocItemAdapter(Context context) {
		// TODO Auto-generated constructor stub
		super();
		inflater = LayoutInflater.from(context);
		loadOptions = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.default_load_img)
				.showImageOnFail(R.drawable.default_load_img)
				.showImageForEmptyUri(R.drawable.default_load_img)
				// .showStubImage(R.drawable.default_img_msg)
				// .showStubImage(R.drawable.app_icon)
				.cacheInMemory(true).cacheOnDisc(true).build();
	}

	public void setData(List<T_Resource> datas) {
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
	public void setCallBack(callBack_Item callBackItem){
		this.callBackItem=callBackItem;
	}
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(
					R.layout.resource_doc_page_list_item, null);
			holder.item = (CustomListItemView) convertView
					.findViewById(R.id.item);
//			holder.backImg_Delete=(RelativeLayout)convertView.findViewById(R.id.backBtn);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		T_Resource bean = datas.get(position);
		holder.item.setFixDouble_title(bean.getCom_name());
//		holder.item.onDoubleTitle_left2("来自").onDoubleTitle_left2_size(14)
//				.onDoubleTitle_left2_color(Enum_Color.TextLevelTwo.color());
//		holder.item.onDoubleTitle_left3("来自  "+bean.getSender_name())
//				.onDoubleTitle_left3_size(14)
//				.onDoubleTitle_left3_color(Enum_Color.TextNote.color());
		holder.item.setFixDouble_timer(FormatUtils.getListItemTime(bean.getBook_recvTime()));
		holder.item.onDoubleBRText("来自 "+bean.getSender_name());
		holder.item.setFixDouble_content("《"+bean.getBook_name()+"》");

		imageLoader.displayImage(null, holder.item.getFixIcon(), loadOptions,
				null);
//		holder.backImg_Delete.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				callBackItem.onBackClick(position);
//			}
//		});
		holder.item.onDoubleBRTextColor(R.drawable.text_color_orange);
		holder.item.getDoubleBRText().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBackItem.onSenderClick(position);
			}
		});
		return convertView;
	}
	private callBack_Item callBackItem;
	public static abstract class callBack_Item{
		public abstract void onBackClick(int position);
		public abstract void onSenderClick(int position);
	}
	public final class ViewHolder {
		RelativeLayout backImg_Delete;
		CustomListItemView item;
	}
}
