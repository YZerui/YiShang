package com.yishang.E.view.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.customview.view.CustomListItemView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.ruifeng.yishang.R;
import com.yishang.A.global.Enum.push.Enum_PushType;
import com.yishang.A.global.baseClass.SuperAdapter;
import com.yishang.A.global.writting.W_Msg;
import com.yishang.C.dao.daoModel.T_MsgNewFir;
import com.yishang.Z.utils.FormatUtils;

public class MsgNewFriAdapter extends SuperAdapter {
	private List<T_MsgNewFir> listDatas;
	private Context context;

	public MsgNewFriAdapter(Context context) {
		// TODO Auto-generated constructor stub
		super();
		this.context = context;
		inflater = LayoutInflater.from(context);
		loadOptions = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.default_img_head)
				.showImageOnFail(R.drawable.default_img_head)
				.showImageForEmptyUri(R.drawable.default_img_head)
				// .showStubImage(R.drawable.default_img_msg)
				// .showStubImage(R.drawable.app_icon)
				.cacheInMemory(true).cacheOnDisc(true).build();
	}

	public void setDatas(List<T_MsgNewFir> listDatas) {
		this.listDatas = listDatas;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listDatas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.msg_page_newfri_list_item,
					null);
			holder.item = (CustomListItemView) convertView
					.findViewById(R.id.item);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// 处理标题和logo
		T_MsgNewFir bean = listDatas.get(position);
		Enum_PushType tEnum = Enum_PushType.DEFAULT.valueOf(bean
				.getMsg_new_type());
		String name = bean.getMsg_new_sendName();
		String title = W_Msg.getNote(true, tEnum);
		String content = "文档" + "《" + bean.getMsg_new_resName() + "》";
		holder.item.setFixDouble_title(name).onDoubleBRText(title)
				.onDoubleBRTextColor(R.color.color_note);
		holder.item.setFixDouble_content(content);
		holder.item.setFixDouble_timer(FormatUtils.getListItemTime(bean.getMsg_new_serverTime()));
		imageLoader.displayImage(bean.getMsg_new_sendHead(), holder.item.getFixIcon(), loadOptions);
		
		if (bean.getMsg_new_unReadNum() == 0) {
			holder.item.setUnReadLayoutVisible(false);
		} else {
			// 如果未读信息数大于9，则以一个小红点表示
			if (bean.getMsg_new_unReadNum()> 9) {
				holder.item.setUnReadNote();
			} else {
				holder.item.setUnReadNum(String.valueOf(bean.getMsg_new_unReadNum()));
			}
		}
		return convertView;
	}

	public final class ViewHolder {
		public CustomListItemView item;
	}
}
