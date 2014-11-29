package com.yishang.B.module.d.BusinessUi;

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

import com.format.utils.DataValidate;
import com.http.callBack.myHttpResultCallBack;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.thread.HandlerExtend;
import com.thread.HandlerExtend.handleCallBack;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;
import com.yishang.A.global.Enum.Enum_ListLimit;
import com.yishang.A.global.Enum.com.Enum_ComType;
import com.yishang.A.global.baseClass.SearchBoxActivity;
import com.yishang.A.global.constant.API;
import com.yishang.A.global.constant.CONSTANT;
import com.yishang.A.global.constant.DAO_CONSTANT;
import com.yishang.A.global.writting.W_Msg;
import com.yishang.B.module.d.BusinessEntity.Recv_business;
import com.yishang.B.module.d.BusinessEntity.Req_comSearch;
import com.yishang.C.dao.daoImpl.AppDaoImpl;
import com.yishang.C.dao.daoImpl.Dao_Company;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.C.dao.daoModel.T_Company;
import com.yishang.C.dao.daoModel.T_Resource;
import com.yishang.D.service.HttpResultService;
import com.yishang.E.view.adapter.BusinessItemAdapter;
import com.yishang.E.view.adapter.RecvBusinessItemAdapter;
import com.yishang.E.view.swipelistview.XListView.IXListViewListener;
import com.yishang.Z.utils.FormatUtils;
import com.yishang.Z.utils.ParseUtils;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * ��ҵ����ҳ��
 * 
 * @author MM_Zerui
 * @note ��ҵ��������Ϊ����������
 * @step_1 �ڱ༭����������еı��ؼ���
 * @step_2 �ڱ༭���������������
 * @��ע �ӱ������ݿ��л�ȡ������ҵ��Ϣ
 * 
 */
public class BusinessSearchActivity extends SearchBoxActivity {
	private ArrayList<Recv_business> netList;
	private List<T_Company> localList;
	List<T_Company> filterList;
	private RequestParams params;
	private BusinessItemAdapter localAdapter;
	private RecvBusinessItemAdapter adapter;
	private Req_comSearch reqBean;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		callBack();
		init();
	}

	private void init() {
		// TODO Auto-generated method stub

		localAdapter = new BusinessItemAdapter(context);
		localList = new ArrayList<T_Company>();
		localAdapter.setData(localList);
		localListView.setAdapter(localAdapter);
		try {
			localList = Dao_Company.getRecord(Enum_ComType.DEFAULT,
					Enum_ListLimit.DEFAULT.value());
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		netList = new ArrayList<Recv_business>();
		adapter = new RecvBusinessItemAdapter(context);
		reqBean = new Req_comSearch();
		reqBean.setUid(Dao_Self.getInstance().getUser_id());
		reqBean.setLimit(String.valueOf(CONSTANT.DAO_COMPANY_LIMIT));

		netListView.setMode(true, false);
		
		editText.setHint("������ҵ");

	}

	private void callBack() {
		// TODO Auto-generated method stub
		editText.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				localLayout.setVisibility(View.VISIBLE);
				netLayout.setVisibility(View.VISIBLE);
				overallLayout
						.setBackgroundColor(CONSTANT.COLOR_SEARCH_RESULT_PAGE);
				// ���صļ���
				System.out.println("�༭��:" + s);
				filterData(s.toString().trim());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				// ����˵�����
				System.out.println("�������:" + editText.getText().toString());
				if (DataValidate.checkDataValid(editText.getText().toString())) {
					httpRequest(editText.getText().toString(), false);
				}

			}
		});
		cancelBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		localListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub]
				T_Company bean=filterList.get(position);
				ViewSwitchUtils.in2LeftIntent(context, BusinessDetailPge.class,bean.getCom_id());
			}
		});
		netListView.setXListViewListener(new IXListViewListener() {

			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub

			}

			@Override
			public void onLoadMore() {
				// TODO Auto-generated method stub
				httpRequest(null, true);
			}
		});
		netListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				ViewSwitchUtils.in2LeftIntent(context, BusinessDetailPge.class,
						netList.get(position - 1).getCom_id());
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
				filterList = new ArrayList<T_Company>();

				if (TextUtils.isEmpty(filterStr)) {
					filterList.clear();
				} else {
					filterList.clear();
					if(!DataValidate.checkDataValid(localList)){
						return;
					}try{
					for (T_Company item : localList) {
						String abb = W_Msg.Y(item.getCom_abb());// ��ҵ���
						String name = W_Msg.Y(item.getCom_name());// ��ҵȫ��
						if (ifMatch(abb, filterStr) || ifMatch(name, filterStr)) {
							filterList.add(item);
						}
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
			
			@Override
			public void end() {
				// TODO Auto-generated method stub
				
			}
		}, true);
		
		handlerExtend.onInitView();

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
			localAdapter.setData(filterList);
			localAdapter.notifyDataSetChanged();
		}
		@Override
		public void call_onRefresh() {
			// TODO Auto-generated method stub
			
		}
	
	});
	/**
	 * ��ҵ��Ϣ������
	 */
	public void httpRequest(String keywords, final boolean ifLoad) {
		if (ifLoad) {
			reqBean.setStart(String.valueOf((load_index++)
					* CONSTANT.DAO_COMPANY_LIMIT));
		} else {
			if (netList != null) {
				netList.clear();
			}
			load_index = 0;
			reqBean.setStart(String.valueOf(load_index++));
			reqBean.setKw(keywords);
		}

		http.send(HttpMethod.POST, API.BUSINESS_SEARCH,
				FormatUtils.convertBeanToParams(reqBean),
				new RequestCallBack<String>() {
					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						super.onStart();
						httpRun(ifLoad);
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						customText.setTextOnly("�������,�޷��ѵ������ҵ");
					}

					@Override
					public void onSuccess(ResponseInfo<String> params) {
						new HttpResultService(params.result,
								new myHttpResultCallBack() {

									@Override
									public void onSuccess() {
										// TODO Auto-generated method stub

									}

									@Override
									public void onData(boolean validity,
											Object obj) {
										// TODO Auto-generated method stub
										super.onData(validity, obj);
										httpEnd();
										if (validity) {
											customText.setVisibility(View.GONE);
											netListView
													.setVisibility(View.VISIBLE);
											ArrayList<Recv_business> list = (ArrayList<Recv_business>) obj;
											if (ifLoad) {
												netList.addAll(list);
												adapter.notifyDataSetChanged();
												return;
											}

											netList = list;
											adapter.setData(netList);
											netListView.setAdapter(adapter);
										} else if (ifLoad) {
											customText.setVisibility(View.GONE);
											netListView
													.setVisibility(View.VISIBLE);
										} else {
											customText.setTextOnly("�����ѵ��������");
										}
									}

									@Override
									public void onRequestFail() {
										// TODO Auto-generated method stub
										customText.setTextOnly("�����ѵ��������");
									}

									@Override
									public void onFinally() {
										// TODO Auto-generated method stub

									}
								}, Recv_business.class, true);
					}
				});
	}

	public void httpRun(boolean ifLoad) {
		if (ifLoad) {
			return;
		}
		customText.setProLeft().setText("��������...");
		customText.setVisibility(View.VISIBLE);
		netListView.setVisibility(View.GONE);
	}

	public void httpEnd() {
		netListView.stopLoadMore();
	}
}
