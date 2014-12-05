package com.yishang.E.view.adapter;

import java.util.List;

import com.customview.view.CustomListItemView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.ruifeng.yishang.R;
import com.yishang.A.global.Enum.constant.Enum_Color;
import com.yishang.A.global.Enum.push.Enum_PushSource;
import com.yishang.A.global.Enum.push.Enum_PushType;
import com.yishang.A.global.baseClass.SuperAdapter;
import com.yishang.A.global.writting.W_Msg;
import com.yishang.C.dao.daoModel.T_MsgSeq;
import com.yishang.Z.utils.FormatUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * 显示消息首页列表的Adapter
 * 
 * @author MM_Zerui
 */
public class MsgSeqItemAdapter extends SuperAdapter {
	private LayoutInflater inflater;
	private List<T_MsgSeq> datas;
	private Context context;
	private callBack call;
	public MsgSeqItemAdapter(Context context) {
		super();
		inflater = LayoutInflater.from(context);
		loadOptions = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.message_plus_free_message_normal)
				.showImageOnFail(R.drawable.message_plus_free_message_normal)
				.showImageForEmptyUri(R.drawable.message_plus_free_message_normal)
				.cacheInMemory(true)
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
				.cacheOnDisc(true).build();
		this.context = context;
	}
	public void setCallBack(callBack call){
		this.call=call;
	}
	public void setData(List<T_MsgSeq> datas) {
		this.datas = datas;
	}

	public void updateListView(List<T_MsgSeq> datas) {
		this.datas = datas;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas==null?0:datas.size();
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
			convertView = inflater.inflate(R.layout.msg_page_list_item, null);
			holder.itemView = (CustomListItemView) convertView
					.findViewById(R.id.customListItem);
//			holder.backBtn = (Button) convertView.findViewById(R.id.backBtn);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		T_MsgSeq bean = datas.get(position);
		Enum_PushSource sEnum = Enum_PushSource.DEFAULT.valueOf(bean
				.getMsg_seq_source());
		Enum_PushType tEnum = Enum_PushType.DEFAULT.valueOf(bean
				.getMsg_seq_type());
		// 处理标题和logo
		String title = new String();
		String name = new String();
		switch (sEnum) {
		
		case SYSTEM:
			name="易商小秘书";
			title =W_Msg.getNote(true, tEnum);
			
			holder.itemView.getFixIcon().setImageResource(
					R.drawable.msg_system_icon);
			break;
		case COMPANY:
			name=bean.getMsg_seq_comName();
			title = W_Msg.getNote(true, tEnum);
			imageLoader.displayImage(bean.getMsg_seq_comLogo(),
					holder.itemView.getFixIcon(), loadOptions);
			break;
		case USER:
			name=bean.getMsg_seq_sendName();
			title = W_Msg.getNote( true, tEnum);
			imageLoader.displayImage(bean.getMsg_seq_sendHead(),
					holder.itemView.getFixIcon(), loadOptions);
			break;
		case NEWFRIEND:
			name=bean.getMsg_seq_sendName();
			title = W_Msg.getNote( true, tEnum);
			holder.itemView.getFixIcon().setImageResource(R.drawable.msg_new_friend);
			break;

		default:
			break;
		}
		// 处理详情文本
		String content = W_Msg.getContent(tEnum, bean);

		holder.itemView
			.setFixDouble_title(name)
			.onDoubleBRText(title)
			.onDoubleBRTextColor(R.color.color_note);
		
		holder.itemView.setFixDouble_content(content);
		holder.itemView.setFixDouble_timer(FormatUtils.getListItemTime(bean.getMsg_seq_serverTime()));
//		holder.backBtn.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				call.onBackClick(position);
//			}
//		});
		if (bean.getMsg_seq_unReadNum() == 0) {
			holder.itemView.setUnReadLayoutVisible(false);
		} else {
			// 如果未读信息数大于9，则以一个小红点表示
			if (bean.getMsg_seq_unReadNum() > 9) {
				holder.itemView.setUnReadNote();
			} else {
				holder.itemView.setUnReadNum(String.valueOf(bean.getMsg_seq_unReadNum()));
			}
		}
		return convertView;
	}
	public static abstract class callBack{
		public abstract void onBackClick(int position);
	}
	public final class ViewHolder {
		public CustomListItemView itemView;
		public Button backBtn;
	}
}
