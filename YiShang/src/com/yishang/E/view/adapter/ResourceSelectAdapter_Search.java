package com.yishang.E.view.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.customview.callBack.listItemCallBack;
import com.customview.view.CustomItemView;
import com.customview.view.CustomListItemView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.ruifeng.yishang.R;
import com.yishang.A.global.Enum.constant.Enum_Color;
import com.yishang.A.global.baseClass.SuperAdapter;
import com.yishang.C.dao.daoModel.T_Resource;
import com.yishang.E.view.adapter.ContactsSelectAdapter.ItemCallBack;
import com.yishang.E.view.swipelistview.StickyListHeadersAdapter;
import com.yishang.Z.utils.FormatUtils;

/**
 * 资源选择页面
 * 
 * @author MM_Zerui
 * 
 */
public class ResourceSelectAdapter_Search extends SuperAdapter {
	private Context context;
	private List<T_Resource> datas;
	private ItemCallBack itemCallBack;

	public ResourceSelectAdapter_Search(Context context) {
		this.context = context;
		inflater = LayoutInflater.from(context);
		loadOptions = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(
						R.drawable.default_load_img)
				.showImageOnFail(R.drawable.default_load_img)
				.showImageForEmptyUri(
						R.drawable.default_load_img)
				.cacheInMemory(true)
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
				.cacheOnDisc(true).build();
	}

	public void setDatas(List<T_Resource> datas) {
		this.datas = datas;
	}

	public void setItemCallBack(ItemCallBack itemCallBack) {
		this.itemCallBack = itemCallBack;
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
	public View getView(final int position, View contentView, ViewGroup arg2) {
		ViewHolder holder = null;
		if (contentView == null) {
			holder = new ViewHolder();
			contentView = inflater.inflate(
					R.layout.res_select_list_item_layout, null);
			holder.item = (CustomListItemView) contentView
					.findViewById(R.id.item);
			contentView.setTag(holder);
		} else {
			holder = (ViewHolder) contentView.getTag();
		}
		T_Resource bean = datas.get(position);
		holder.item.setCallBack(new listItemCallBack() {
			@Override
			public void checkbox_select_callBack(boolean ifCheck) {
				// TODO Auto-generated method stub
				super.checkbox_select_callBack(ifCheck);
				itemCallBack.call_select(ifCheck, position);
			}
		});
		if (bean.isItem_select()) {
			holder.item.setCheckSelect(true);
			itemCallBack.call_select(true, position);
		} else {
			holder.item.setCheckSelect(false);
			itemCallBack.call_select(false, position);
		}
		holder.item.setFixDouble_title(bean.getCom_name());
//		holder.item.onDoubleTitle_left2("来自").onDoubleTitle_left2_size(14)
//				.onDoubleTitle_left2_color(Enum_Color.TextLevelTwo.color());
//		holder.item.onDoubleTitle_left3(bean.getSender_name())
//				.onDoubleTitle_left3_size(14)
//				.onDoubleTitle_left3_color(Enum_Color.TextNote.color());
		holder.item.setFixDouble_content("《"+bean.getBook_name()+"》");

		imageLoader.displayImage(null, holder.item.getFixIcon(), loadOptions,
				null);
		return contentView;
	}

	public static abstract class ItemCallBack {
		public abstract void call_select(boolean ifCheck, int position);
	}

	public class ViewHolder {
		public CustomListItemView item;
	}

}
