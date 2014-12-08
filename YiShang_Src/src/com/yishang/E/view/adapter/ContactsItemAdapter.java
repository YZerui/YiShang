package com.yishang.E.view.adapter;

import java.util.List;

import com.customview.model.item;
import com.customview.view.CustomListItemView;
import com.exception.utils.P;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.nostra13.universalimageloader.core.imageaware.NonViewAware;
import com.ruifeng.yishang.R;
import com.yishang.A.global.Enum.db.Enum_RelaType;
import com.yishang.A.global.Enum.db.Enum_ResSource;
import com.yishang.A.global.baseClass.SuperAdapter;
import com.yishang.A.global.writting.W_UserIfo;
import com.yishang.C.dao.daoModel.T_Relationships;
import com.yishang.Z.utils.Benchmark;

import android.content.Context;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 显示联系列表的Adapter
 * 
 * @author MM_Zerui
 */
public class ContactsItemAdapter extends SuperAdapter {
	private LayoutInflater inflater;
	private List<T_Relationships> datas;

	public ContactsItemAdapter(Context context) {
		// TODO Auto-generated constructor stub
		inflater = LayoutInflater.from(context);
		loadOptions = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.default_img_head)
				.showImageOnFail(R.drawable.default_img_head)
				.cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
				.build();
	}

	public void setData(List<T_Relationships> datas) {
		this.datas = datas;
	}

	public void setCallBack(callBack_Item call) {
		this.callBar = call;
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
			convertView = inflater.inflate(R.layout.contacts_item_layout, null);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.refreshData(position);
		return convertView;
	}

	private callBack_Item callBar;

	public static abstract class callBack_Item {
		public abstract void onBackClick_1(int position);

		public abstract void onBackClick_2(int position);
	}

	private final class ViewHolder {
		private TextView title, content;
		private ImageView icon;
		public ImageAware imageAware;

		public ViewHolder(View parent) {
			initView(parent);
		}

		private void initView(View parent) {
			title = (TextView) parent.findViewById(R.id.fixDouble_title);
			content = (TextView) parent.findViewById(R.id.fixDouble_content);
			icon = (ImageView) parent.findViewById(R.id.fixIcon);
			imageAware = new ImageViewAware(icon, false);
		}

		public void refreshData(int position) {
			T_Relationships bean = datas.get(position);
			title.setText(bean.getRela_name());
			content.setText(W_UserIfo.rank(bean.getRela_rank()));
			Enum_RelaType enumType = Enum_RelaType.valueOf(bean
					.getRela_typeResult());
			switch (enumType) {
			case SYSTEM:
				icon.setImageResource(R.drawable.msg_system_icon);
				break;
			default:
				ImageLoader.getInstance().displayImage(bean.getRela_head(),
						imageAware, loadOptions);
				break;
			}
		}
	}
}
