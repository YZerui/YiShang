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
import com.lidroid.xutils.exception.DbException;
import com.thread.HandlerExtend;
import com.thread.HandlerExtend.handleCallBack;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;
import com.yishang.A.global.baseClass.SearchBoxActivity;
import com.yishang.A.global.constant.CONSTANT;
import com.yishang.B.module.e.SelfUi.UserIfoPage;
import com.yishang.C.dao.daoImpl.Dao_Relationship;
import com.yishang.C.dao.daoImpl.Dao_Resource;
import com.yishang.C.dao.daoModel.T_Relationships;
import com.yishang.C.dao.daoModel.T_Resource;
import com.yishang.E.view.CustomToast;
import com.yishang.E.view.adapter.ResourceDocItemAdapter;
import com.yishang.E.view.adapter.ResourceDocItemAdapter.callBack_Item;
import com.yishang.Z.utils.Benchmark;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * 资源搜索页面
 * 
 * @步骤 首先获取所有资源文档
 * @author MM_Zerui
 * 
 */
public class ResourceSearchActivity extends SearchBoxActivity {
	private List<T_Resource> list;
	private ResourceDocItemAdapter adapter;
	private List<T_Resource> filterList;

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
		adapter.setCallBack(new callBack_Item() {
			
			@Override
			public void onSenderClick(int position) {
				// TODO Auto-generated method stub
				//调转到发送者
				try {
					// 跳转到发送人界面
					T_Resource bean = list.get(position);
					T_Relationships peoBean = Dao_Relationship.getByID(bean
							.getSend_id());
					ViewSwitchUtils.tab_in2LeftIntent(context,
							UserIfoPage.class, peoBean.getRela_id(),
							String.valueOf(peoBean.getRela_register()),
							peoBean.getRela_phone());
				} catch (Exception e) {
					// TODO: handle exception
					new CustomToast(context).locatCenter().setText("信息有误,无法查找该联系人");
					P.v("跳转联系人错误:" + e.getMessage());
				}
			}
			
			@Override
			public void onBackClick(int position) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		 editText.setHint("搜索文档资源(文档名/发送者/公司名");
		list = new ArrayList<T_Resource>();
		adapter = new ResourceDocItemAdapter(context);
		adapter.setData(list);
		localListView.setAdapter(adapter);
		try {
			// 获取所有文档资源
			list = Dao_Resource.getAllBooks();
		} catch (DbException e) {
			e.printStackTrace();
		}
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
	}

	/**
	 * 根据输入框中的值来过滤数据并更新ListView
	 * 
	 * @param filterStr
	 */
	private void filterData(final String filterStr) {
		Benchmark.start("检索过程");
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
					Benchmark.start("资源检索时常");
					for (T_Resource item : list) {
						try {
							String resName = item.getBook_name();
							String resCom = item.getCom_name();
							String resSender = item.getSender_name();
							if (ifMatch(resName, filterStr)
									|| ifMatch(resCom, filterStr)
									|| ifMatch(resSender, filterStr)) {
								filterList.add(item);
							}
						} catch (Exception e) {
							// TODO: handle exception
							P.v("检索错误");
						}
					}
					Benchmark.end("资源检索时常");
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
			Benchmark.end("检索过程");
		}
		@Override
		public void call_onRefresh() {
			// TODO Auto-generated method stub
			
		}
		
	});
}
