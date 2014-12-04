package com.yishang.E.view.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.customview.view.CustomListItemView;
import com.ruifeng.yishang.R;
import com.yishang.A.global.Enum.push.Enum_PushType;
import com.yishang.A.global.baseClass.SuperAdapter;
import com.yishang.A.global.constant.DAO_CONSTANT;
import com.yishang.A.global.writting.W_Msg;
import com.yishang.C.dao.daoModel.T_Msg;
import com.yishang.E.view.adapter.RecvBusinessItemAdapter.ViewHolder;
import com.yishang.Z.utils.FormatUtils;

/**
 * ��ҵ֪ͨ�б����
 * @author MM_Zerui
 *
 */
public class MsgCompanyPageAdapter extends SuperAdapter {
	private List<T_Msg> list;
	private LayoutInflater inflater;
	Context context;

	public MsgCompanyPageAdapter(Context context) {
		this.context = context;
		inflater = LayoutInflater.from(context);
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
			convertView = inflater.inflate(R.layout.msg_page_company_list_item,
					null);
			holder.item = (CustomListItemView) convertView
					.findViewById(R.id.item);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		T_Msg msg = list.get(position);
		Enum_PushType tEnum = Enum_PushType.DEFAULT.valueOf(msg.getMsg_type());
		String success=msg.getMsg_content();
		switch (tEnum) {
		case COM_AWARD: // ��ҵ����
			
			break;
		case COM_BAOBEI:// ��ҵ������Ϣ
			holder.item.getFixIcon().setImageResource(R.drawable.msg_com_baobei);
			holder.item.setFixSingle_title("����֪ͨ");
			if(success.equals("true")){
				holder.item.setBottomTextContent("��Ը���ҵ�ı����ɹ�");
			}else {
				holder.item.setBottomTextContent("��Ը���ҵ�ı���ʧ��");
			}
			break;
		case COM_CHECK:// ��ҵ�����Ϣ
			holder.item.getFixIcon().setImageResource(R.drawable.msg_com_note);
			holder.item.setFixSingle_title("����֪ͨ");
			if(success.equals("true")){
				holder.item.setBottomTextContent("���Ѻ͸���ҵʵ���˹���");
			}else {
				holder.item.setBottomTextContent("��͸���ҵ�Ĺ���ʧ��");
			}
			break;
		case COM_INFORM:// ��ҵ֪ͨ��Ϣ
			holder.item.setFixSingle_title("��ҵ֪ͨ");
			holder.item.getFixIcon().setImageResource(R.drawable.msg_com_inform);
			holder.item.setBottomTextContent("��͸���ҵ�Ĺ���ʧ��");
			break;

		default:
			break;
		}
//		holder.item.setFixSingle_title(W_Msg.getNote(msg.getMsg_comName(),
//				msg.getMsg_success()==1?true:false, tEnum));
//		holder.item.setBottomTextContent(W_Msg.getNote(msg.getMsg_comName(),
//				msg.getMsg_success()==1?true:false, tEnum));
		holder.item.setFixSingle_timer(FormatUtils.getListItemTime(msg.getMsg_time()));
		return convertView;
	}

	public final class ViewHolder {
		public CustomListItemView item;
	}
}
