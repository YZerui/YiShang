package com.yishang.B.module.c.ResourceUi;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.exception.utils.P;
import com.format.utils.DataValidate;
import com.thread.HandlerExtend;
import com.thread.HandlerExtend.handleCallBack;
import com.thread.callBack.runCallBack;
import com.thread.RunnableService;
import com.yishang.A.global.baseClass.SearchBoxActivity;
import com.yishang.A.global.constant.CONSTANT;
import com.yishang.C.dao.daoModel.T_Resource;
import com.yishang.E.view.adapter.ResourceSelectAdapter_Search;
import com.yishang.E.view.adapter.ResourceSelectAdapter_Search.ItemCallBack;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * ��Դ����ҳ��(����ѡ����Դ)
 * 
 * @���� ���Ȼ�ȡ������Դ�ĵ�
 * @author MM_Zerui
 * 
 */
public class ResourceSearchActivity_Select extends SearchBoxActivity {
	private List<T_Resource> list;
	private ResourceSelectAdapter_Search adapter;
	private List<T_Resource> filterList;
	private List<T_Resource> selectItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		init();
		initCallBack();
	}

	private void initCallBack() {
		// TODO Auto-generated method stub
		localListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				T_Resource bean = filterList.get(position);
				ViewSwitchUtils.in2LeftIntent(context,
						ResourceDetailPage.class, bean.getCom_name(),
						bean.getBook_url(), bean.getBook_id(), bean.getCom_id());
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		editText.setHint("�����ĵ���Դ(�ĵ���/������/��˾��");
		list = new ArrayList<T_Resource>();
		adapter = new ResourceSelectAdapter_Search(context);
		adapter.setDatas(list);
		localListView.setAdapter(adapter);
		// ��ȡ��Դ����
		list = ResourceSelectPage.listDatas;
		selectItems = ResourceSelectPage.selectDatas;
		callBack();

	}

	private void callBack() {
		// TODO Auto-generated method stub
		editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				localLayout.setVisibility(View.VISIBLE);
				netLayout.setVisibility(View.GONE);
				overallLayout
						.setBackgroundColor(CONSTANT.COLOR_SEARCH_RESULT_PAGE);
				// ������������ֵΪ�գ�����Ϊԭ�����б�����Ϊ���������б�
				filterData(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		cancelBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		// ѡ��CheckBox�Ļص�
		adapter.setItemCallBack(new ItemCallBack() {

			@Override
			public void call_select(boolean ifCheck, int position) {
				// TODO Auto-generated method stub
				T_Resource item = filterList.get(position);
				filterList.get(position).setItem_select(ifCheck);
				if (ifCheck) {
					if (!selectItems.contains(item)) {
						selectItems.add(0, item);
					}
				} else {
					if (selectItems.contains(item)) {
						selectItems.remove(item);
					}
				}
			}
		});
	}

	/**
	 * ����������е�ֵ���������ݲ�����ListView
	 * 
	 * @param filterStr
	 */
	private void filterData(final String filterStr) {
		new RunnableService(new runCallBack() {
			
			@Override
			public void start() {
				// TODO Auto-generated method stub
				filterList = new ArrayList<T_Resource>();

				if (TextUtils.isEmpty(filterStr)) {
					filterList = new ArrayList<T_Resource>();
				} else {
					filterList.clear();
					if (!DataValidate.checkDataValid(list)) {
						return;
					}
					try {
						for (T_Resource item : list) {
							String resName = item.getBook_name();
							String resCom = item.getCom_name();
							String resSender = item.getSender_name();
							if (ifMatch(resName, filterStr)
									|| ifMatch(resCom, filterStr)
									|| ifMatch(resSender, filterStr)) {
								filterList.add(item);
							}
						}
					} catch (Exception e) {
						// TODO: handle exception
						P.v("������Դ����");
					}
				}
			}
			
			@Override
			public void end() {
				// TODO Auto-generated method stub
				handlerExtend.onInitView();
			}
		}, true);
		
		

	}
	private HandlerExtend handlerExtend=new HandlerExtend(new handleCallBack() {
		
		@Override
		public void call_onInit() {
			// TODO Auto-generated method stub
			if (!DataValidate.checkDataValid(filterList)) {
				localResultNote.setVisibility(View.VISIBLE);
				localListView.setVisibility(View.GONE);
			} else {
				localResultNote.setVisibility(View.GONE);
				localListView.setVisibility(View.VISIBLE);
			}

			// ����a-z��������
			adapter.setDatas(filterList);
			adapter.notifyDataSetChanged();
		}
		@Override
		public void call_onRefresh() {
			// TODO Auto-generated method stub
			
		}
	
	});
}
