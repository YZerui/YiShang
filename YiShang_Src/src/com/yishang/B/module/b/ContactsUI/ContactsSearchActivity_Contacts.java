package com.yishang.B.module.b.ContactsUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.format.utils.DataValidate;
import com.lidroid.xutils.exception.DbException;
import com.thread.HandlerExtend;
import com.thread.HandlerExtend.handleCallBack;
import com.thread.callBack.runCallBack;
import com.thread.RunnableService;
import com.yishang.A.global.Enum.Enum_ListLimit;
import com.yishang.A.global.baseClass.SearchBoxActivity;
import com.yishang.A.global.constant.CONSTANT;
import com.yishang.A.global.writting.W_Msg;
import com.yishang.B.module.e.SelfUi.UserIfoPage;
import com.yishang.C.dao.daoImpl.Dao_Relationship;
import com.yishang.C.dao.daoModel.T_Relationships;
import com.yishang.E.view.adapter.ContactsItemAdapter;
import com.yishang.E.view.adapter.ContactsSearchAdapter;
import com.yishang.E.view.adapter.ContactsSearchAdapter_Select;
import com.yishang.E.view.adapter.ContactsSearchAdapter_Select.ItemCallBack;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * 人脉搜索页面
 * 
 * @步骤 首先从数据库中提取所有人脉信息到列表List中
 * @author MM_Zerui
 * 
 */
public class ContactsSearchActivity_Contacts extends SearchBoxActivity {
	private List<T_Relationships> listDatas;
	private ContactsSearchAdapter_Select adapter;
	protected List<T_Relationships> filterList;
	private List<T_Relationships> selectItems;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// callBack();
		init();
		initCallBack();
	}

	private void initCallBack() {
		// TODO Auto-generated method stub
		localListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int positiion, long arg3) {
				// TODO Auto-generated method stub
				T_Relationships bean = filterList.get(positiion);
				ViewSwitchUtils.in2LeftIntent(context, UserIfoPage.class,
						bean.getRela_id(),
						String.valueOf(bean.getRela_register()),
						bean.getRela_phone());
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
//		editText.setHint("搜索好友(姓名/头衔/手机号)");
		editText.setHint("搜索好友");
		editText.requestFocus();	
		Timer timer = new Timer(); //设置定时器
		timer.schedule(new TimerTask() {
		@Override
			public void run() { //弹出软键盘的代码
			InputMethodManager inputManager =  
		               (InputMethodManager)editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);  
		           inputManager.showSoftInput(editText, 0);  
			}
		}, 500);
		
		listDatas = new ArrayList<T_Relationships>();
		adapter = new ContactsSearchAdapter_Select(context);
		adapter.setData(listDatas);
		localListView.setAdapter(adapter);
		// 从选择页面中获取列表数据
		listDatas = ContactsSelectPage.listDatas;
		selectItems=ContactsSelectPage.selectItems;
		callBack();
	}

	/**
	 * 根据输入框中的值来过滤数据并更新ListView
	 * 
	 * @param filterStr
	 */
	private void filterData(final String filterStr) {
		new RunnableService(new runCallBack() {
			
			@Override
			public void start() {
				// TODO Auto-generated method stub
				filterList = new ArrayList<T_Relationships>();

				if (TextUtils.isEmpty(filterStr)) {
					filterList = new ArrayList<T_Relationships>();
				} else {
					filterList.clear();
					if (!DataValidate.checkDataValid(listDatas)) {
						return;
					}try{
					for (T_Relationships item : listDatas) {
						String name = W_Msg.Y(item.getRela_name());
						String rank = W_Msg.Y(item.getRela_rank());
						String phone = W_Msg.Y(item.getRela_phone());
						if (ifMatch(name, filterStr) || ifMatch(rank, filterStr)
								|| ifMatch(phone, filterStr)) {
							filterList.add(item);
						}
					}}catch (Exception e) {
						// TODO: handle exception
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
			// 根据a-z进行排序
			adapter.setData(filterList);
			adapter.notifyDataSetChanged();
		}
		@Override
		public void call_onRefresh() {
			// TODO Auto-generated method stub
			
		}
		
		
	});
	private void callBack() {
		// TODO Auto-generated method stub
		editText.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				localLayout.setVisibility(View.VISIBLE);
				netLayout.setVisibility(View.GONE);
				overallLayout
						.setBackgroundColor(CONSTANT.COLOR_SEARCH_RESULT_PAGE);
				// 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
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
		// 选择CheckBox的回调
		adapter.setItemCallBack(new ItemCallBack() {
			
			@Override
			public void call_select(boolean ifCheck, int position) {
				// TODO Auto-generated method stub
				filterList.get(position).setItem_select(ifCheck);
				T_Relationships item = filterList.get(position);
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
}
