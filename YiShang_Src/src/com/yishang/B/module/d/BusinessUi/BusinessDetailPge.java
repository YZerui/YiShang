package com.yishang.B.module.d.BusinessUi;

import java.util.ArrayList;

import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomBtnView;
import com.customview.view.CustomItemView;
import com.customview.view.CustomPageView;
import com.customview.view.CustomTopbarView;
import com.customview.view.ImageView_Rect;
import com.customview.view.ImageView_Rounded;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.ruifeng.yishang.R;
import com.thread.HandlerExtend;
import com.thread.HandlerExtend.handleCallBack;
import com.yishang.A.global.Enum.Enum_PageSource;
import com.yishang.A.global.Enum.com.Enum_ComRela;
import com.yishang.A.global.Enum.constant.Enum_Color;
import com.yishang.A.global.baseClass.SuperActivity;
import com.yishang.A.global.constant.CONSTANT;
import com.yishang.A.global.constant.CONSTANT_API;
import com.yishang.B.module.c.ResourceUi.ResourceComPage;
import com.yishang.B.module.c.ResourceUi.ResourceDetailPage;
import com.yishang.B.module.d.BusinessEntity.Recv_comBook;
import com.yishang.B.module.d.BusinessEntity.Recv_comDetail;
import com.yishang.D.service.httpRequest.HttpReq_GetComBook;
import com.yishang.D.service.httpRequest.HttpReq_GetComIfo;
import com.yishang.D.service.httpRequest.HttpReq_RelateCom;
import com.yishang.D.service.httpRequest.HttpReq_GetComBook.CallBack_Book;
import com.yishang.D.service.httpRequest.HttpReq_GetComIfo.CallBack;
import com.yishang.D.service.httpRequest.HttpReq_RelateCom.CallBack_Relate;
import com.yishang.E.view.MyDialog;
import com.yishang.E.view.adapter.ResourceComBookAdapter;
import com.yishang.E.view.dialog.Effectstype;
import com.yishang.E.view.swipelistview.BaseSwipeListViewListener;
import com.yishang.E.view.swipelistview.SwipeListView;
import com.yishang.E.view.swipelistview.XListView;

import com.yishang.Z.utils.ViewSwitchUtils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

/**
 * �̼�����ҳ
 * 
 * @���� ��ҵID ��Դҳ��
 * @author MM_Zerui
 * 
 */
public class BusinessDetailPge extends SuperActivity {

	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.com_icon)
	private ImageView_Rect comIcon;
	@ViewInject(R.id.com_keyword)
	private TextView comKeyWord;
	@ViewInject(R.id.com_title)
	private TextView comTitle;
	@ViewInject(R.id.correlateBtn)
	private CustomBtnView correlateBtn;
	@ViewInject(R.id.noteText)
	private TextView noteText;
	@ViewInject(R.id.com_detail)
	private TextView comDetail;
	@ViewInject(R.id.bookMoreBtn)
	private CustomItemView moreItem;
	@ViewInject(R.id.listView)
	private XListView listView;
	@ViewInject(R.id.pageView)
	private CustomPageView pageView;
	@ViewInject(R.id.overallView)
	private CustomPageView overallView;

	private String comId;
	private Recv_comDetail bean;
	private HttpReq_GetComBook getBookService;
	private ResourceComBookAdapter bookAdapter;
	private ArrayList<Recv_comBook> bookList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.business_detail_page);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void obtainIntentValue() {
		comId = getIntent().getStringExtra("DATA0");
		SOURCE_PAGE = getIntent().getStringExtra("DATA1");
	}

	@Override
	protected void initResource() {
		overallView.setBottomLayoutVisible(false).setTextOnly("����...");
		loadOptions = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.msg_default_icon)
				.showImageOnFail(R.drawable.msg_default_icon)
				.showImageForEmptyUri(R.drawable.msg_default_icon)
				.cacheInMemory(true).cacheOnDisc(true).build();

//		listView.setViewMode(false, false, SwipeListView.SWIPE_ACTION_REVEAL, 0);
		listView.setMode(false, false);
		
		getBookService = new HttpReq_GetComBook(comId,
				CONSTANT.DAO_COM_BOOK_LIMIT);
		bookAdapter = new ResourceComBookAdapter(context);
		bookList = new ArrayList<Recv_comBook>();
		// ���ؽ�������ʾ
		topBar.setTitle("������...").setProVisibility(true);
		//��ʼ�пؼ����ɴ�
		correlateBtn.setEnabled(false);
		new HttpReq_GetComIfo(comId, new CallBack() {

			@Override
			public void requestSuccess(Recv_comDetail recvBean) {
				// TODO Auto-generated method stub
				bean = recvBean;
				handlerExtend.onInitView();
			}

			@Override
			public void requestFail() {
				// TODO Auto-generated method stub
				handlerExtend.onFail();
			}

			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
				handlerExtend.onFinally();
			}
		});
	}

	private HandlerExtend handlerExtend = new HandlerExtend(
			new handleCallBack() {

				@Override
				public void call_onRefresh() {
					// TODO Auto-generated method stub

				}

				@Override
				public void call_onInit() {
					// TODO Auto-generated method stub
					overallView.setVisibility(View.GONE);
					initComInformation();
					topBar.setTitle(bean.getCom_name());
				}

				public void call_onFail() {
//					toast.setText("��Ϣ��ȡ������");
					overallView.setTextOnly("��Ϣ��ȡʧ��");
					topBar.setTitle("��Ϣ��ȡʧ��");
				};

				public void call_onFinally() {
					topBar.setProVisibility(false);
				};
			});

	/**
	 * ����ҵ��Ϣ���г�ʼ����ˢ�½���
	 */
	private void initComInformation() {
		//��ҵ�ɴ�
		correlateBtn.setEnabled(true);
		//
		topBar.setTitle(bean.getCom_name());
		imageLoader.displayImage(bean.getCom_logo(), comIcon, loadOptions);
		//��ҵ����
		comKeyWord.setText(bean.getCom_remark());
		comTitle.setText(bean.getCom_abb());
		comDetail.setText(bean.getCom_industry());
		Enum_ComRela rEnum = Enum_ComRela.DEFAULT.getEnum(Integer.valueOf(bean
				.getUc_status()));
		switch (rEnum) {
		case CORRE_ING:
			correlateBtn.setText("���������");
			correlateBtn.setBtnStyle(CustomBtnView.BTN_GREEN);
			break;
		case CORRE_NOT:
			correlateBtn.setText("����͸���ҵ�Ĺ���");
			correlateBtn.setBtnStyle(CustomBtnView.BTN_GREEN);
			break;
		case CORRE_SUCCESS:
			correlateBtn.setText("ȡ���͸���ҵ�Ĺ���");
			correlateBtn.setBtnStyle(CustomBtnView.BTN_RED);
			break;

		default:
			break;
		}
		pageView.setBottomLayoutVisible(false).setProgress("����...").setVisibility(View.VISIBLE);
		getBookService.setCallBack(new CallBack_Book() {

			@Override
			public void onRefresh(ArrayList<Recv_comBook> list) {
				// TODO Auto-generated method stub
				bookList.clear();
				bookList.addAll(list);
				bookAdapter.setData(bookList);
				listView.setAdapter(bookAdapter);
				pageView.setVisibility(View.GONE);
			}

			@Override
			public void onLoad(ArrayList<Recv_comBook> list) {
				// TODO Auto-generated method stub
				bookList.addAll(list);
				bookAdapter.notifyDataSetChanged();
				pageView.setVisibility(View.GONE);
			}

			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				pageView.setTextOnly("������ĵ�").setVisibility(View.VISIBLE);
			}

			@Override
			public void onFinally() {
				// TODO Auto-generated method stub

			}
		});
		getBookService.onRefresh();
	}

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
		});
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
//				toast.setText("����");
				Recv_comBook recvBean=bookList.get(position-1);
				//ǰ����Դ����ҳ
				ViewSwitchUtils.in2LeftIntent(context, ResourceDetailPage.class,recvBean.getBook_name()
						,recvBean.getBook_url(),recvBean.getBook_id(),recvBean.getBook_com());
			}
		});
//		listView.setSwipeListViewListener(new BaseSwipeListViewListener() {
//			@Override
//			public void onClickFrontView(int position) {
//				// TODO Auto-generated method stub
//				super.onClickFrontView(position);
//				toast.setText("������");
//			}
//		});

	}

	@OnClick(R.id.bookMoreBtn)
	private void onMoreClick(View v) {
		ViewSwitchUtils.in2LeftIntent(context, ResourceComPage.class, comId,
				bean.getCom_name());
	}

	/**
	 * ����ҵ���й�������
	 * 
	 * @param v
	 */
	@OnClick(R.id.correlateBtn)
	public void relateClick(View v) {
		if (bean.getUc_status().equals(Enum_ComRela.CORRE_ING.toString())) {
			toast.setText("���Ѿ������ҵ�����˹�������,��ȴ����֪ͨ");
			return;
		}
		if (bean.getUc_status().equals(Enum_ComRela.CORRE_SUCCESS.toString())) {
			// ȡ�������Ĳ���
			myDialog=new MyDialog(context).withTitle("��ʾ")
					.withSwitchBtnLayout("ȡ������", "����")
					.withMessage("ȷ��Ҫ�͸���ҵȡ������ô��")
					.withAnimat(Effectstype.Shake)
					.setOkBtnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							// ȡ�������Ĳ�������
							new HttpReq_RelateCom(new CallBack_Relate() {

								@Override
								public void onSuccess() {
									// TODO Auto-generated method stub
									httpRelaEnd();
									correlateBtn.setText("����͸���ҵ�Ĺ���");
									correlateBtn
											.setBtnStyle(CustomBtnView.BTN_GREEN);
									bean.setUc_status(Enum_ComRela.CORRE_NOT
											.toString());
								}

								@Override
								public void onStart() {
									// TODO Auto-generated method stub
									httpRelaRun();
									myDialog.dismiss();
								}

								@Override
								public void onFail() {
									// TODO Auto-generated method stub
									httpRelaEnd();
									toast.setText("����ʧ��");
								}
							}, comId, false);
						}
					}).setRightBtnTextColor(Enum_Color.TextNote.color()).withShow();
			return;
		}
		// ���й�������
		new HttpReq_RelateCom(new CallBack_Relate() {

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				httpRelaEnd();
				correlateBtn.setText("���������");
				bean.setUc_status(Enum_ComRela.CORRE_ING.toString());
				correlateBtn.setBtnStyle(CustomBtnView.BTN_GREEN);
			}

			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				httpRelaRun();
			}

			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				httpRelaEnd();
				toast.setText("����ʧ��");
			}
		}, comId, true);
	}

	public void httpRelaRun() {
		correlateBtn.setEnabled(false);
		topBar.setTitle("������...");
		topBar.setProVisibility(true);
	}

	public void httpRelaEnd() {
		correlateBtn.setEnabled(true);
		topBar.setTitle(bean.getCom_name());
		topBar.setProVisibility(false);
	}
	/**
	 * ��ת����ҵ����ҳ
	 * @param v
	 */
	@OnClick(R.id.com_keyword)
	public void detailClick(View v){
		ViewSwitchUtils.in2LeftIntent(context, BusinessIntroPage.class,bean.getCom_remark());
	}
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		if (SOURCE_PAGE != null) {
			if (SOURCE_PAGE.equals(Enum_PageSource.MsgReceivePage.name())
					||SOURCE_PAGE.equals(Enum_PageSource.ResDetailPage.name())) {
				ViewSwitchUtils.finishOut2Bottom(context);
				return;
			}

		}
		ViewSwitchUtils.finishOut2Right(context);
	}
}
