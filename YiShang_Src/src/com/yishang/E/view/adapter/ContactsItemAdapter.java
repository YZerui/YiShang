package com.yishang.E.view.adapter;

import java.util.List;

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
import com.yishang.A.global.baseClass.SuperAdapter;
import com.yishang.A.global.writting.W_UserIfo;
import com.yishang.C.dao.daoModel.T_Relationships;
import com.yishang.Z.utils.Benchmark;

import android.content.Context;
import android.graphics.Bitmap.CompressFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * 显示联系列表的Adapter
 * 
 * @author MM_Zerui
 */
public class ContactsItemAdapter extends SuperAdapter {
	private LayoutInflater inflater;
	private List<T_Relationships> datas;

	public ContactsItemAdapter(Context context) {
//		 TODO Auto-generated constructor stub
		inflater = LayoutInflater.from(context);
		loadOptions= new DisplayImageOptions.Builder()			
		.showImageForEmptyUri(R.drawable.default_img_head)	
		.showImageOnFail(R.drawable.default_img_head)
		.showImageForEmptyUri(R.drawable.default_img_head)
//		.showStubImage(R.drawable.default_img_msg)
//		.showStubImage(R.drawable.app_icon)
		.displayer(new FadeInBitmapDisplayer(600))
		.cacheInMemory(true)
                    .cacheOnDisk(true)
                    .build();					
//		.cacheOnDisc(true)						
//		.build();	
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
			Benchmark.start("convertView");
			convertView = inflater.inflate(R.layout.contacts_page_list_item,
					null);
			holder = new ViewHolder(convertView);
			// holder.backView=(CustomBarView)convertView.findViewById(R.id.frontItem);
//			holder.backItem1 = (RelativeLayout) convertView
//					.findViewById(R.id.backBtn_1);
//			holder.backItem2 = (RelativeLayout) convertView
//					.findViewById(R.id.backBtn_2);
			holder.item = (CustomListItemView) convertView
					.findViewById(R.id.item);
			holder.imageAware = new ImageViewAware(holder.item.getFixIcon());
			convertView.setTag(holder);
			Benchmark.end("convertView");
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
//		Benchmark.start("convertViewData");
		T_Relationships bean = datas.get(position);
		holder.item.setFixDouble_title(bean.getRela_name());
		holder.item.setFixDouble_content(W_UserIfo.rank(bean.getRela_rank()));
//		P.v("搜索头衔:" + W_UserIfo.rank(bean.getRela_rank()));
//		holder.backItem1.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				callBar.onBackClick_1(position);
//			}
//		});
//		holder.backItem2.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				callBar.onBackClick_2(position);
//			}
//		});
		// System.out.println(bean.getRela_head());
		
		ImageLoader.getInstance().displayImage(bean.getRela_head(), holder.imageAware, loadOptions);
		
		
//		ImageLoader.getInstance().
//			displayImage("https://dn-dev.qbox.me/avatar/37e25cf8-64fe-440b-a9c1-7a0ac8d2b840?imageView2/1/w/120/h/120", holder.imageAware, loadOptions);
		
		
//		Benchmark.end("convertViewData");
		return convertView;
	}

	private callBack_Item callBar;

	public static abstract class callBack_Item {
		public abstract void onBackClick_1(int position);

		public abstract void onBackClick_2(int position);
	}

	private final static class ViewHolder {

		// public CustomBarView backView;
		public RelativeLayout backItem1, backItem2;
		public CustomListItemView item;
		public ImageAware imageAware;

		public ViewHolder(View parent) {
			initView(parent);
		}

		private void initView(View parent) {
			// holder.backItem1=(RelativeLayout)convertView.findViewById(R.id.backBtn_1);
			// holder.backItem2=(RelativeLayout)convertView.findViewById(R.id.backBtn_2);
			// holder.item=(CustomListItemView)convertView.findViewById(R.id.item);
		}

		public void refreshData(T_Relationships rel) {

		}
	}
}
