package com.yishang.B.module.c.ResourceUi;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.LinearLayout;

import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomBarView;
import com.customview.view.CustomBarView.callBack_Bar;
import com.customview.view.CustomBtnView;
import com.customview.view.CustomPageView;
import com.customview.view.CustomTopbarView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.ruifeng.yishang.R;
import com.thread.HandlerExtend;
import com.thread.HandlerExtend.handleCallBack;
import com.yishang.A.global.Enum.constant.Enum_Param;
import com.yishang.A.global.Enum.db.Enum_ResType;
import com.yishang.A.global.baseClass.SuperActivity;
import com.yishang.A.global.callBack.listHttpCallBack;
import com.yishang.A.global.constant.CONSTANT;
import com.yishang.A.global.constant.PARAMS;
import com.yishang.B.module.b.ContactsUI.ContactsSearchActivity_Contacts;
import com.yishang.B.module.b.ContactsUI.ContactsSelectPage;
import com.yishang.C.dao.daoModel.T_Relationships;
import com.yishang.C.dao.daoModel.T_Resource;
import com.yishang.D.service.TransResourceService;
import com.yishang.D.service.TransResourceService.CallBack;
import com.yishang.D.service.dbRequest.DBReq_Resource;
import com.yishang.D.service.dbRequest.DBReq_Resource.callBack_Res;
import com.yishang.E.view.MyDialog;
import com.yishang.E.view.adapter.ResourceSelectAdapter;
import com.yishang.E.view.adapter.ResourceSelectAdapter.ItemCallBack;
import com.yishang.E.view.swipelistview.StickyListHeadersListView;
import com.yishang.E.view.swipelistview.SwipeListView;
import com.yishang.E.view.swipelistview.XListView;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * ��Դѡ��ͷ���ҳ��
 * @�������� �û�ID
 * @author MM_Zerui
 * 
 */
public class ResourceSelectPage extends SuperActivity {
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.searchBtn)
	private CustomBtnView searchBtn;
	@ViewInject(R.id.listView)
	private XListView listView;
	@ViewInject(R.id.bottomBar)
	private CustomBarView bottomBar;
	@ViewInject(R.id.overalLayout)
	private LinearLayout overalLayout;
	@ViewInject(R.id.pageView)
	private CustomPageView pageView;

	private String uID;
	private ResourceSelectAdapter adapter;
	private DBReq_Resource dbReq;
	public static List<T_Resource> listDatas;
	public static List<T_Resource> selectDatas;
	int anim_y;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.resource_select_page);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);

	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		uID=getIntent().getStringExtra("DATA0");
	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		dbReq = new DBReq_Resource();

		listView.setHttpCallBack(new listHttpCallBack() {

			@Override
			public void initListView() {
				// TODO Auto-generated method stub
//				listView.setViewMode(false, false,
//						SwipeListView.SWIPE_ACTION_REVEAL, 0);
				listView.setMode(true, false);
				adapter = new ResourceSelectAdapter(context);
				listDatas = new ArrayList<T_Resource>();
				selectDatas=new ArrayList<T_Resource>();
//				pageView.onProgressOnly().setVisibility(View.VISIBLE);
				pageView.setBottomLayoutVisible(false).setTextOnly("������...").setVisibility(View.VISIBLE);
				handler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						dbReq.setCallBack_Default(callBack_Res)
						.setEnum_ResType(Enum_ResType.DEFAULT).getAll().callInit();
					}
				}, Enum_Param.TIMEOFFSET_LISTLOAD.value());
			
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

	}

	private callBack_Res callBack_Res = new callBack_Res() {

		@Override
		public void onInit(List<T_Resource> list) {
			// TODO Auto-generated method stub
			listDatas = list;
			adapter.setDatas(listDatas);
			handlerExtend.onInitView();
		}

		@Override
		public void onLoadMore(List<T_Resource> list) {
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
	};
	private HandlerExtend handlerExtend = new HandlerExtend(
			new handleCallBack() {

				@Override
				public void call_onInit() {
					// TODO Auto-generated method stub
					listView.setPullLoadEnable(true);
					listView.setAdapter(adapter);
					pageView.setVisibility(View.GONE);
				}

				@Override
				public void call_onRefresh() {
					// TODO Auto-generated method stub
					adapter.notifyDataSetChanged();
					pageView.setVisibility(View.GONE);
				}

				public void call_onFail() {
					if(dbReq.isIfInit()){
						pageView.setTextOnly("���������Դ").setVisibility(View.VISIBLE);
					}
					listView.setPullLoadEnable(false);
				};

				public void call_onFinally() {
					listView.stopLoadMore();
				};
			});

	@Override
	protected void onClickListener() {
		// TODO Auto-generated method stub
		topBar.setCallBack(new topBarCallBack() {
			@Override
			public void call_backBtnListener() {
				// TODO Auto-generated method stub
				super.call_backBtnListener();
				finish();
			}

			@Override
			public void call_rightTextBtnListener() {
				// TODO Auto-generated method stub
				super.call_rightTextBtnListener();
				//ת������
				if(selectDatas.size()<=0){
					toast.setText("��ѡ���ĵ�����ת��");
					return;
				}
				topBar.setTitle("����...").setProVisibility(true);
				//���Ͳ���
				new TransResourceService(uID,selectDatas, callBack);
				
			}
		});
		// ѡ��CheckBox�Ļص�
		adapter.setItemCallBack(new ItemCallBack() {

			@Override
			public void call_select(boolean ifCheck, int position) {
				// TODO Auto-generated method stub
				T_Resource item = listDatas.get(position);
				listDatas.get(position).setItem_select(ifCheck);
				if (ifCheck) {
					if (!selectDatas.contains(item)) {
						selectDatas.add(0, item);
//						selectNum++;
//						topBar.setLeftText(selectNum+"λ");
					}
				} else {
					if (selectDatas.contains(item)) {
						selectDatas.remove(item);
//						selectNum--;
//						topBar.setLeftText(selectNum+"λ");
					}
				}
			}
		});
		bottomBar.setCallBack(new callBack_Bar() {

			@Override
			public void call_FirstItem(int... position) {
				// TODO Auto-generated method stub
				// ȫѡ
				for (T_Resource item : listDatas) {
					item.setItem_select(true);
				}
				adapter.notifyDataSetChanged();
			}

			@Override
			public void call_SecondItem(int... position) {
				// TODO Auto-generated method stub
				// ��ѡ
				for (T_Resource item : listDatas) {
					item.setItem_select(!item.isItem_select());
				}
				adapter.notifyDataSetChanged();
			}

			@Override
			public void call_ThirdItem(int... position) {
				// TODO Auto-generated method stub
				// ȡ��
				for (T_Resource item : listDatas) {
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
	@OnClick(R.id.searchBtn)
	private void searchClick(View v){
		anim_y=searchBtn.getHeight();
		TranslateAnimation animation = new TranslateAnimation(0, 0, 0, -anim_y);
		animation.setDuration(CONSTANT.SEARCH_BOX_ANIM);
		animation.setFillAfter(true);
		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				Intent intent = new Intent();
				intent.setClass(context,ResourceSearchActivity_Select.class);
				startActivityForResult(intent,PARAMS.SEARCH_CALLBACK_NOTE);
				overridePendingTransition(R.anim.search_box_animation_2,R.anim.search_box_animation_1);
			}
		});
		overalLayout.startAnimation(animation);
	}
	private CallBack callBack=new CallBack() {
		
		@Override
		public void onSuccess() {
			// TODO Auto-generated method stub
			//ת���ɹ�
//			new MyDialog(context)
//				.withTitle("��ʾ")
//				.withMessage("ת���ɹ�")
//				.withSingleBtnLayout("֪����").show();
			toast.setText("ת���ɹ�");
			finish();
		}
		
		@Override
		public void onFail() {
			// TODO Auto-generated method stub
			new MyDialog(context)
			.withTitle("��ʾ")
			.withMessage("ת��ʧ����,������������ݳ���")
			.withSingleBtnLayout("֪����").show();
		}
		
		@Override
		public void onEnd() {
			// TODO Auto-generated method stub
			topBar.setTitle("ѡ����Դת��").setRighTextVisibility(true);
		}
	};
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case PARAMS.SEARCH_CALLBACK_NOTE:
			handler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					TranslateAnimation animation = new TranslateAnimation(0, 0, -anim_y, 0);
					animation.setDuration(CONSTANT.SEARCH_BOX_ANIM);
					animation.setFillAfter(true);
					overalLayout.startAnimation(animation);
				}
			}, Enum_Param.TIMEOFFSET_BOXDOWN.value());
			
			//ˢ��ѡ���б�
			handlerExtend.onRefreshView();
			break;

		default:
			break;
		}
	}
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		ViewSwitchUtils.finishOut2Bottom(context);
	}

}
