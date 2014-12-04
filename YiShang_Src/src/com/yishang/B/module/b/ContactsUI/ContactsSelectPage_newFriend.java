package com.yishang.B.module.b.ContactsUI;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomBarView;
import com.customview.view.CustomTopbarView;
import com.customview.view.CustomBarView.callBack_Bar;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ruifeng.yishang.R;
import com.thread.HandlerExtend;
import com.thread.HandlerExtend.handleCallBack;
import com.yishang.A.global.Enum.db.Enum_RelaType;
import com.yishang.A.global.baseClass.SuperDialogActivity;
import com.yishang.A.global.callBack.listHttpCallBack;
import com.yishang.C.dao.daoModel.T_Relationships;
import com.yishang.D.service.dbRequest.DBReq_Relationship;
import com.yishang.D.service.dbRequest.DBReq_Relationship.CallBack_Rela;
import com.yishang.E.view.adapter.ContactsSelectAdapter;
import com.yishang.E.view.adapter.ContactsSelectAdapter.ItemCallBack;
import com.yishang.E.view.swipelistview.SwipeListView;
import com.yishang.E.view.swipelistview.XListView;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * ѡ�������ѵ�ҳ��
 * 
 * @�������� ��ѡ����û�List
 * @author MM_Zerui
 * 
 */
public class ContactsSelectPage_newFriend extends SuperDialogActivity {
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.listView)
	private XListView listView;
	@ViewInject(R.id.bottomBar)
	private CustomBarView bottomBar;

	private ContactsSelectAdapter adapter;
	private DBReq_Relationship dbReq;
	private List<T_Relationships> listDatas;

	private int selectNum=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.contacts_select_page_new_friend);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void setPageHeight() {
		// TODO Auto-generated method stub
		lp.height = getWindowManager().getDefaultDisplay().getHeight() * 4 / 5;
		getWindow().setAttributes(lp);// �����ú����Ե�lpӦ�õ��Ի���
	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void onClickListener() {
		// TODO Auto-generated method stub
		topBar.setCallBack(new topBarCallBack() {
			@Override
			public void call_rightTextBtnListener() {
				// TODO Auto-generated method stub
				super.call_rightTextBtnListener();
				finish();
			}
		});
	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		listView.setHttpCallBack(new listHttpCallBack() {

			@Override
			public void initListView() {
				// TODO Auto-generated method stub
//				listView.setViewMode(false, true,
//						SwipeListView.SWIPE_ACTION_REVEAL, 0);
				listView.setMode(false, false);
				listDatas = new ArrayList<T_Relationships>();
				adapter = new ContactsSelectAdapter(context);
				dbReq = new DBReq_Relationship();
			}

			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub

			}

			@Override
			public void onLoadMore() {
				// TODO Auto-generated method stub
				dbReq.callLoad();
			}

		});
		dbReq.setCallBack(new CallBack_Rela() {

			@Override
			public void onInit(List<T_Relationships> list) {
				// TODO Auto-generated method stub
				listDatas = list;
				adapter.setData(listDatas);
				handlerExtend.onInitView();
			}

			@Override
			public void onLoad(List<T_Relationships> list) {
				// TODO Auto-generated method stub
				listDatas.addAll(list);
				handlerExtend.onRefreshView();
			}

			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
				handlerExtend.onFinally();
			}

			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				handlerExtend.onFail();
			}
		}, Enum_RelaType.NEWFRIEND).callInit();
		// ѡ��CheckBox�Ļص�
		adapter.setItemCallBack(new ItemCallBack() {

			@Override
			public void call_select(boolean ifCheck, int position) {
				// TODO Auto-generated method stub
				listDatas.get(position).setItem_select(ifCheck);
				T_Relationships item = listDatas.get(position);
				if (ifCheck) {
					if (!ContactsSelectPage.selectItems.contains(item)) {
						ContactsSelectPage.selectItems.add(0, item);
						selectNum++;
						topBar.setLeftText(selectNum+"λ");
					}
				} else {
					if (ContactsSelectPage.selectItems.contains(item)) {
						ContactsSelectPage.selectItems.remove(item);
						selectNum--;
						topBar.setLeftText(selectNum+"λ");
					}
				}
			}
		});
		// �ײ����ĵ���¼�
		bottomBar.setCallBack(new callBack_Bar() {

			@Override
			public void call_FirstItem(int... position) {
				// TODO Auto-generated method stub
				// ȫѡ
				for (T_Relationships item : listDatas) {
					item.setItem_select(true);
				}
				adapter.notifyDataSetChanged();
			}

			@Override
			public void call_SecondItem(int... position) {
				// TODO Auto-generated method stub
				// ��ѡ
				for (T_Relationships item : listDatas) {
					item.setItem_select(!item.isItem_select());
				}
				adapter.notifyDataSetChanged();
			}

			@Override
			public void call_ThirdItem(int... position) {
				// ȡ��
				for (T_Relationships item : listDatas) {
					item.setItem_select(false);
				}
				adapter.notifyDataSetChanged();

			}

			@Override
			public void call_FourthItem(int... position) {
				// TODO Auto-generated method stub

			}

			@Override
			public void call_FifthItem(int... position) {
				// TODO Auto-generated method stub

			}
		}, 0);
	}

	private HandlerExtend handlerExtend = new HandlerExtend(
			new handleCallBack() {

				@Override
				public void call_onInit() {
					// TODO Auto-generated method stub
					listView.setAdapter(adapter);
				}

				@Override
				public void call_onRefresh() {
					// TODO Auto-generated method stub
					adapter.notifyDataSetChanged();
				}

				public void call_onFail() {

				};

				public void call_onFinally() {
					listView.stopLoadMore();
				};
			});
	public void finish() {
		super.finish();
		ViewSwitchUtils.finishOut2Bottom(context);
	};
}
