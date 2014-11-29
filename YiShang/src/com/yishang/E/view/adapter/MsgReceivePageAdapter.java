package com.yishang.E.view.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.customview.view.CustomListItemView;
import com.customview.view.CustomBarView.callBack_Bar;
import com.ruifeng.yishang.R;
import com.yishang.A.global.Enum.push.Enum_PushType;
import com.yishang.A.global.baseClass.SuperAdapter;
import com.yishang.A.global.constant.DAO_CONSTANT;
import com.yishang.A.global.writting.W_Msg;
import com.yishang.C.dao.daoModel.T_Msg;
import com.yishang.E.view.adapter.RecvBusinessItemAdapter.ViewHolder;
import com.yishang.Z.utils.FormatUtils;

/**
 * 接收到的信息页面
 * 
 * @author MM_Zerui
 * 
 */
public class MsgReceivePageAdapter extends SuperAdapter {
	private List<T_Msg> list;
	private LayoutInflater inflater;
	Context context;

	public MsgReceivePageAdapter(Context context,callBack_Bar callBar) {
		this.context = context;
		inflater = LayoutInflater.from(context);
		this.callBar=callBar;
	}

	public void setDatas(List<T_Msg> list) {
		this.list = list;
	}

	public void updataListView(List<T_Msg> list) {
		this.list = list;
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
			convertView = inflater.inflate(R.layout.msg_page_receive_list_item,
					null);
			holder.item = (CustomListItemView) convertView
					.findViewById(R.id.item);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		T_Msg msg = list.get(position);
		Enum_PushType tEnum = Enum_PushType.DEFAULT.valueOf(msg.getMsg_type());
		switch (tEnum) {
		case RES_INTEREST:// 用户感兴趣
			holder.item.getFixIcon().setImageResource(R.drawable.msg_doc_interest);
			holder.item.setFixSingle_title("感兴趣");
			holder.item.setBottomTextContent(msg.getMsg_resName());
			holder.item.getBottomBar()
			.onHMode().onUnitNum(2)
			.onImg_1(R.drawable.msg_doc_icon)
			.onImg_2(R.drawable.msg_peo_icon)
			.onText_1("查看文档")
			.onText_2("查看客户")
			.onBg(R.color.page_bg_level_four).onBSplitVisible()
			.setVisibility(View.VISIBLE);
			break;
		case RES_RECEV:// 收到文档
			holder.item.getFixIcon().setImageResource(R.drawable.msg_user_doc);
			holder.item.setFixSingle_title("收到文档");
			holder.item.getBottomBar()
			.onHMode().onUnitNum(2)
			.onImg_1(R.drawable.msg_peo_icon)
			.onImg_2(R.drawable.msg_peo_icon)
			.onText_1("查看转发人")
			.onText_2("查看企业代表")
			.onBg(R.color.page_bg_level_four).onBSplitVisible()
			.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
		holder.item.getBottomBar().setCallBack(callBar, position);
		holder.item.setBottomTextContent(msg.getMsg_resName());
		holder.item.setFixSingle_timer(FormatUtils.getListItemTime(msg.getMsg_time()));
		return convertView;
	}
	private callBack_Bar callBar;
	public final class ViewHolder {
		public CustomListItemView item;
	}
}
