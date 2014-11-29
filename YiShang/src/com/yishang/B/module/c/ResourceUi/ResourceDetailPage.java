package com.yishang.B.module.c.ResourceUi;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomBarView;
import com.customview.view.CustomBarView.callBack_Bar;
import com.customview.view.CustomTopbarView;
import com.exception.utils.P;
import com.format.utils.DataValidate;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ruifeng.yishang.R;
import com.thread.HandlerExtend;
import com.thread.HandlerExtend.handleCallBack;
import com.yishang.A.global.Enum.Enum_PageSource;
import com.yishang.A.global.Enum.Enum_ReceiverAction;
import com.yishang.A.global.Enum.com.Enum_ComType;
import com.yishang.A.global.application.AppContextApplication;
import com.yishang.A.global.baseClass.SharePage;
import com.yishang.A.global.baseClass.SuperActivity;
import com.yishang.B.module.c.ResourceEntity.Recv_bookIfo;
import com.yishang.B.module.c.ResourceEntity.Req_bookInterest;
import com.yishang.B.module.d.BusinessUi.BusinessDetailPge;
import com.yishang.C.dao.daoImpl.Dao_Company;
import com.yishang.C.dao.daoImpl.Dao_Resource;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.C.dao.daoModel.T_Resource;
import com.yishang.D.service.httpRequest.HttpReq_BookInterest;
import com.yishang.D.service.httpRequest.HttpReq_GetResIfo;
import com.yishang.D.service.httpRequest.HttpReq_BookInterest.CallBack_Interest;
import com.yishang.D.service.httpRequest.HttpReq_GetResIfo.CallBack_Res;
import com.yishang.D.service.httpRequest.HttpReq_GetTransUrl;
import com.yishang.D.service.httpRequest.HttpReq_GetTransUrl.CallBack_TransUrl;
import com.yishang.Z.utils.BroadcastUtil;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * ��ʾ�ĵ������ҳ��
 * 
 * @���� #��ҵ��# #URL# #��ԴID# #��ҵID# #��תҳ��#
 * @��ע ÿ�γɹ����ĵ��������Ա��ĵ����ȶ���1
 * @author MM_Zerui
 * 
 */
public class ResourceDetailPage extends SuperActivity {
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.webView)
	private WebView webView;
	@ViewInject(R.id.bottomBar)
	private CustomBarView bottomBar;

	private T_Resource resBean;
	private String name;
	private String url;
	private String resID;
	private String cID;
	
	private String transUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.resource_page_detail);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
		getComId();
	}

	/**
	 * �����ĵ�ID��ȡ��ҵID
	 */
	private void getComId() {
		// TODO Auto-generated method stub
		if (!DataValidate.checkDataValid(cID)) {
			new HttpReq_GetResIfo(resID, new CallBack_Res() {

				@Override
				public void onSuccess(Recv_bookIfo bean) {
					// TODO Auto-generated method stub
					cID = bean.getCom_id();
				}

				@Override
				public void onFinally() {
					// TODO Auto-generated method stub

				}

				@Override
				public void onFail() {
					// TODO Auto-generated method stub
					cID = null;
				}
			});
		}

		topBar.setTitle(name == null ? "�����ĵ�" : name);
		resBean = new T_Resource();

		try {
			if (!DataValidate.checkDataValid(resID)) {
				toast.setText("�ĵ���Ϣ����");
				bottomBar.setVisibility(View.GONE);
				return;
			}
			resBean = Dao_Resource.getResource(resID);
			// ÿ�γɹ����ĵ����������ĵ��ȶ���1
			Dao_Resource.addResFreq(resID);
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ��ȡ��ԴURL
		if (!DataValidate.checkDataValid(url)) {
			// ���url������
			new HttpReq_GetResIfo(resID, new CallBack_Res() {

				@Override
				public void onSuccess(Recv_bookIfo bean) {
					// TODO Auto-generated method stub
					webView.loadUrl(bean.getBook_url());
				}

				@Override
				public void onFinally() {
					// TODO Auto-generated method stub

				}

				@Override
				public void onFail() {
					// TODO Auto-generated method stub

				}
			});
		} else {
			webView.loadUrl(url);
		}
		T_Resource resBean;
		try {
			resBean = Dao_Resource.getResource(resID);

			new HttpReq_GetTransUrl(new CallBack_TransUrl() {

				@Override
				public void onSuccess(String url) {
					// TODO Auto-generated method stub
					transUrl=url;
					P.v("���ת��URL:"+url);
				}

				@Override
				public void onFinally() {
					// TODO Auto-generated method stub

				}

				@Override
				public void onFail() {
					// TODO Auto-generated method stub
					P.v("���ת��URLʧ��");
				}
			}).setIniId(resBean.getBook_creater_id()).setResId(resID)
					.setUserId(Dao_Self.getInstance().getUser_id()).httpMethod();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		name = getIntent().getStringExtra("DATA0");
		url = getIntent().getStringExtra("DATA1");
		resID = getIntent().getStringExtra("DATA2");
		cID = getIntent().getStringExtra("DATA3");
		SOURCE_PAGE = getIntent().getStringExtra("DATA4");
	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		if (DataValidate.checkDataValid(SOURCE_PAGE)
				&& SOURCE_PAGE.equals(Enum_PageSource.MsgReceivePage)) {
			topBar.setBackText("��Ϣ�б�");
		}
		topBar.setCallBack(new topBarCallBack() {
			@Override
			public void call_rightTextBtnListener() {
				// TODO Auto-generated method stub
				super.call_rightTextBtnListener();
				ViewSwitchUtils.in2TopIntent(context, SharePage.class,
						resBean.getBook_id(), resBean.getBook_creater_id());

			}

			@Override
			public void call_backBtnListener() {
				// TODO Auto-generated method stub
				super.call_backBtnListener();
				finish();
			}
		});
		// �趨֧��JS
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient() {

			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub
				super.onPageStarted(view, url, favicon);
			}

			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				// TODO Auto-generated method stub
				super.onReceivedError(view, errorCode, description, failingUrl);
			}
		});

	}

	@Override
	protected void onClickListener() {
		// TODO Auto-generated method stub
		bottomBar.setCallBack(new callBack_Bar() {

			@Override
			public void call_ThirdItem(int... position) {
				// TODO Auto-generated method stub
				// �鿴��ҵ
				if (!DataValidate.checkDataValid(cID)) {
					toast.setText("�޷�������,���ĵ��Ѿ����ڻ����翪С����");
					return;
				}
				ViewSwitchUtils.in2TopIntent(context, BusinessDetailPge.class,
						cID, Enum_PageSource.ResDetailPage.name());
			}

			@Override
			public void call_SecondItem(int... position) {
				// TODO Auto-generated method stub
				// ����Ȥ����
				interestMethod();
			}

			@Override
			public void call_FourthItem(int... position) {
				// TODO Auto-generated method stub

			}

			@Override
			public void call_FirstItem(int... position) {
				// TODO Auto-generated method stub
				T_Resource bean;
				try {
					bean = Dao_Resource.getResource(resID);
					if (!DataValidate.checkDataValid(bean)) {
						// ����������ݿⲻ���ڣ����ĵ���ԭʼת���˱�Ϊ�Լ�
						ViewSwitchUtils.in2TopIntent(context, SharePage.class,
								resID, Dao_Self.getInstance().getUser_id(),transUrl,name);
						return;
					}
					ViewSwitchUtils.in2TopIntent(context, SharePage.class,
							bean.getBook_id(), bean.getBook_creater_id(),transUrl,name);
					return;
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				toast.setText("�ĵ���Ϣ����,�޷�ת��");
			}

			@Override
			public void call_FifthItem(int... position) {
				// TODO Auto-generated method stub

			}
		}, 0);
	}

	/**
	 * ����Ȥ����
	 * 
	 * @���� ����Ȥ�ɹ�����ĵ���Ӧ����ҵ���б��ش������趨Ϊ��Ӧ��
	 * @���� �������ҵ�Ѿ�������ҵ���У�������Ӧ�Ĺ�ϵΪ��Ӧ��
	 * @���� �������ҵδ���ڱ����У���Ӹ���ҵ��Ϣ����ҵ���в��趨Ϊ��Ӧ��
	 */
	private void interestMethod() {
		// TODO Auto-generated method stub
		Req_bookInterest reqBean = new Req_bookInterest();
		reqBean.setBid(resID);
		reqBean.setUid(Dao_Self.getInstance().getUser_id());
		// ����ĵ��ڱ����м�¼��˵������ԭʼת���˵���Ϣ��
		if (DataValidate.checkDataValid(resBean)) {
			reqBean.setTran_ini(resBean.getBook_creater_id());
			reqBean.setTran_send(resBean.getSend_id());
		}

		new HttpReq_BookInterest(reqBean, new CallBack_Interest() {

			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				handlerExtend.onRefreshView();
				// ����Ȥ�ɹ��󣬸��ĵ���Ӧ����ҵӦ�ó�Ϊ��Ӧ��
				P.v("����Ȥ�ɹ�...");
				Dao_Company.updateComRela(cID, Enum_ComType.COM_SUPPLIER);
				// ����֪ͨ���и���
				// ���͸�����ҵ��֪ͨ
				BroadcastUtil.sendBroadCast(
						AppContextApplication.getInstance(),
						Enum_ReceiverAction.COMPANY.name());

			}

			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				handlerExtend.onInitView();
			}

			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				handlerExtend.onFail();
			}
		});
	}

	private HandlerExtend handlerExtend = new HandlerExtend(
			new handleCallBack() {

				@Override
				public void call_onInit() {
					// TODO Auto-generated method stub

					topBar.setTitle("����Ȥ...").setProVisibility(true);
				}

				@Override
				public void call_onRefresh() {
					// TODO Auto-generated method stub
					toast.setText("�Ѹ���Ȥ");
					topBar.setTitle(name).setProVisibility(false);
				}

				public void call_onFail() {
					toast.setText("����ʧ��");
					topBar.setTitle(name).setProVisibility(false);
				};

			});

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		ViewSwitchUtils.finishOut2Right(context);
	}

}
