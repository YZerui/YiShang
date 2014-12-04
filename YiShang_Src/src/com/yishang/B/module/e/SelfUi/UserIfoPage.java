package com.yishang.B.module.e.SelfUi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomBarView;
import com.customview.view.CustomBarView.callBack_Bar;
import com.customview.view.CustomItemView;
import com.customview.view.CustomTopbarView;
import com.customview.view.ImageView_Rounded;
import com.format.utils.DataValidate;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.ruifeng.yishang.R;
import com.thread.HandleView;
import com.thread.HandleView.viewCallBack;
import com.thread.HandlerExtend;
import com.thread.HandlerExtend.handleCallBack;
import com.yishang.A.global.Enum.Enum_BackSytle;
import com.yishang.A.global.Enum.Enum_Gender;
import com.yishang.A.global.Enum.Enum_PageSource;
import com.yishang.A.global.Enum.db.Enum_IfRegister;
import com.yishang.A.global.Enum.db.Enum_RelaNote;
import com.yishang.A.global.Enum.db.Enum_RelaType;
import com.yishang.A.global.Enum.http.Enum_ReqRelaSource;
import com.yishang.A.global.Enum.http.Enum_ReqRelaType;
import com.yishang.A.global.baseClass.SuperActivity;
import com.yishang.A.global.writting.W_UserIfo;
import com.yishang.B.module.a.MsgUi.MsgReceivePage;
import com.yishang.B.module.c.ResourceUi.ResourceSelectPage;
import com.yishang.B.module.d.BusinessUi.BusinessRelatePage;
import com.yishang.B.module.f.LoginEntity.Recv_userIfo;
import com.yishang.C.dao.daoImpl.Dao_Relationship;
import com.yishang.C.dao.daoModel.T_Relationships;
import com.yishang.C.dao.utils.Utils_DBRNote;
import com.yishang.D.service.httpRequest.HttpReq_AddContacts;
import com.yishang.D.service.httpRequest.HttpReq_BlockUser;
import com.yishang.D.service.httpRequest.HttpReq_CallPhone;
import com.yishang.D.service.httpRequest.HttpReq_SYNCUserIfo;
import com.yishang.D.service.httpRequest.HttpReq_SetClient;
import com.yishang.D.service.httpRequest.HttpReq_AddContacts.ACCallBack;
import com.yishang.D.service.httpRequest.HttpReq_BlockUser.CallBack_BlockUser;
import com.yishang.D.service.httpRequest.HttpReq_CallPhone.CallBack_Phone;
import com.yishang.D.service.httpRequest.HttpReq_SYNCUserIfo.CallBack_UserIfo;
import com.yishang.D.service.httpRequest.HttpReq_SetClient.CallBack_SetClient;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * ��ʾ�û���Ϣ��ҳ��
 * 
 * @���� #�û�ID# #�Ƿ�Ϊע�������û�# #�绰����# #��Դ#
 * @�������� ���ȸ��ݴ��ݵ�ID�ӷ���˻�ȡ���飬����ɹ����β���������
 * @�������� ���û�д��ݵ�ID�����ж϶����Ƿ�ΪͨѶ¼����
 * @�������� �������ΪͨѶ¼���ѣ����Ե绰Ϊ��ʶ�ӱ��ػ�ȡ��Ӧ��Ϣ
 * @�������� �������Ϊע���û��������ID�ӱ��ػ�ȡ��Ӧ��Ϣ
 * 
 * @��ע �Է�����Ĺ�ϵǿ�ȴ���1�ſɴ�绰
 * @��ע ��Ϊ�ͻ� �� ���οؼ��� �仯
 * @author MM_Zerui
 * 
 */
public class UserIfoPage extends SuperActivity {
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;

	@ViewInject(R.id.head)
	private ImageView_Rounded headImg;

	@ViewInject(R.id.name)
	private TextView name;

	@ViewInject(R.id.genderIcon)
	private ImageView genderIcon;

	@ViewInject(R.id.rank_item)
	private CustomItemView rankItem;

	@ViewInject(R.id.label_item)
	private CustomItemView labelItem;

	@ViewInject(R.id.com_item)
	private CustomItemView comItem;

	@ViewInject(R.id.more_item)
	private CustomItemView moreItem;

	@ViewInject(R.id.bottomBar)
	private CustomBarView bottomBar;

	public static Recv_userIfo result;
	private String uid;
	private String ifRegister;
	private String phone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.user_ifo_page);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		uid = getIntent().getStringExtra("DATA0");
		ifRegister = getIntent().getStringExtra("DATA1");
		phone = getIntent().getStringExtra("DATA2");
		SOURCE_PAGE = getIntent().getStringExtra("DATA3");
	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		result = new Recv_userIfo();
		loadOptions = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.default_img_head)
				.showImageOnFail(R.drawable.default_img_head)
				.showImageForEmptyUri(R.drawable.default_img_head)
				// .showStubImage(R.drawable.default_img_msg)
				// .showStubImage(R.drawable.app_icon)
				.cacheInMemory(true).cacheOnDisc(true).build();
		topBar.setTitle("��ȡ...").setProVisibility(true);
		new HttpReq_SYNCUserIfo(uid, new CallBack_UserIfo() {

			@Override
			public void onSuccess(Recv_userIfo result) {
				// TODO Auto-generated method stub
				handlerExtend.onData(result);
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

				}

				public void call_onData(Object obj) {
					topBar.setTitle("�û�����");
					result = (Recv_userIfo) obj;
					imageLoader.displayImage(result.getUser_head(), headImg,
							loadOptions);
					name.setText(W_UserIfo.name(result.getUser_name()));
					rankItem.setTextContent(W_UserIfo.rank(result
							.getUser_title1()));
					labelItem.setTextContent(W_UserIfo.label(result
							.getUser_lable()));
					phone = result.getUser_phone();
					if (result.getUser_sex().equals(Enum_Gender.MAN.value())) {
						genderIcon.setImageResource(R.drawable.gender_man);
					} else {
						genderIcon.setImageResource(R.drawable.gender_women);
					}

				};

				public void call_onFail() {
					T_Relationships bean = new T_Relationships();
					if (ifRegister.equals(Enum_IfRegister.REGIST.toString())) {
						// �ӱ��ػ�ȡ��Ϣ
						try {
							bean = Dao_Relationship.getByID(uid);
							if (DataValidate.checkDataValid(bean)) {
								name.setText(W_UserIfo.name(bean.getRela_name()));
								rankItem.setTextContent(W_UserIfo.rank(bean
										.getRela_rank()));
								labelItem.setTextContent(W_UserIfo.label(""));
								imageLoader.displayImage(bean.getRela_head(),
										headImg, loadOptions);
								phone = bean.getRela_phone();
								topBar.setTitle("�û�����");
								return;
							}
						} catch (DbException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						dataError();
						return;
					}
					// ˵��ΪͨѶ¼����
					try {
						// �趨������Ϣ��ť������
						moreItem.setEnabled(false);
//						toast.setText("�޸�����Ϣ");
						bean = Dao_Relationship.getByPhone(phone);

						if (bean != null && DataValidate.checkDataValid(phone)) {
							name.setText(W_UserIfo.name(bean.getRela_name()));
							rankItem.setTextContent(W_UserIfo.rank(bean
									.getRela_rank()));
							labelItem.setTextContent(W_UserIfo.label(""));
							imageLoader.displayImage(bean.getRela_head(),
									headImg, loadOptions);
							topBar.setTitle("ͨѶ¼����");
							return;
						}
					} catch (DbException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					dataError();
				};

				public void call_onFinally() {
					topBar.setProVisibility(false).setRighTextVisibility(true);
					// �ؼ��ı仯
					try {
						T_Relationships tBean = Dao_Relationship.getByID(uid);
						Enum_RelaType enumType = Enum_RelaType
								.valueOf(Utils_DBRNote.handle(tBean
										.getRela_type()));
						switch (enumType) {
						case CLIENT_SUPPLIER:
						case CLIENT:
							// �ͻ��ؼ����ɿ�
							clientBtnEnable(false);
							break;
						case BLACKLIST:
							// ���οؼ����ɿ�
							blockBtnEnable(false);
							break;
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				};
			});

	private void dataError() {
		toast.setText("��ȡ��Ϣ����");
		bottomBar.setVisibility(View.GONE);
		topBar.setTitle("��Ϣ����");
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

			@Override
			public void call_rightTextBtnListener() {
				// TODO Auto-generated method stub
				super.call_rightTextBtnListener();
				// ��ת���̻�����ҳ�棨�û��䣩
				ViewSwitchUtils.in2LeftIntent(context, MsgReceivePage.class,
						uid, Enum_PageSource.UserIfoPage.name());
			}
		});
		bottomBar.setCallBack(new callBack_Bar() {

			@Override
			public void call_FirstItem(int... position) {
				// ��绰
				// ��intent��������绰 ����ϵǿ�ȴ���1�ſ���(������ע�����)
				// ע�⣬�������Ҫ�ͷ���˽���ͬ��
				T_Relationships bean=new T_Relationships();
				try {
					bean = Dao_Relationship.getByID(uid);
					if (bean != null && bean.getRela_intension() >= 1) {
						new HttpReq_CallPhone().setPid(uid).setCallBack(
								new CallBack_Phone() {

									@Override
									public void onSuccess() {
										Intent intent = new Intent(
												Intent.ACTION_CALL, Uri
														.parse("tel:" + phone));
										startActivity(intent);
									}

									@Override
									public void onFinally() {

									}

									@Override
									public void onFail() {
										toast.setText("�����쳣,�޷����иò���");
									}
								}).httpMethod();

						return;
					}
					toast.setText("��ͶԷ��������ϵ����ǿ���޷����в���绰����");
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}

			}

			@Override
			public void call_SecondItem(int... position) {
				// ת���ĵ�
				ViewSwitchUtils.in2TopIntent(context, ResourceSelectPage.class,
						uid);
			}

			@Override
			public void call_ThirdItem(int... position) {
				// ��Ϊ�ͻ�
				new HttpReq_SetClient(clientCall).setCid(uid).httpMethod();
			}

			@Override
			public void call_FourthItem(int... position) {
				// ����
				new HttpReq_BlockUser(blockCall).setCid(uid).setReason("����")
						.httpMethod();
			}

			@Override
			public void call_FifthItem(int... position) {

			}
		}, 0);
	}

	private CallBack_SetClient clientCall = new CallBack_SetClient() {

		@Override
		public void onSuccess() {
			// TODO Auto-generated method stub
			toast.setText("�趨�ɹ�");
			clientBtnEnable(false);
			blockBtnEnable(true);
			// ͬ������ͨѶ¼
			try {
				Dao_Relationship.updateRelation(uid, Enum_RelaNote.CLIENT.value());
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void onFinally() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onFail() {
			// TODO Auto-generated method stub
			toast.setText("����ʧ��");
		}
	};
	private CallBack_BlockUser blockCall = new CallBack_BlockUser() {

		@Override
		public void onSuccess() {
			// TODO Auto-generated method stub
			toast.setText("�����θ��û�");
			blockBtnEnable(false);
			clientBtnEnable(true);
			// ͬ������ͨѶ¼
			try {
				Dao_Relationship.updateRelation(uid, Enum_RelaNote.BLAKLIST.value());
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void onFinally() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onFail() {
			// TODO Auto-generated method stub
			toast.setText("����ʧ��");
		}
	};

	/**
	 * �趨�ͻ��ؼ��Ŀɴ���
	 * 
	 * @param enable
	 */
	private void clientBtnEnable(boolean enable) {
		if (enable) {
			bottomBar.onUnitBg(3, R.color.page_bg_level_one);
			bottomBar.onUnitEnable(3, true);
			return;
		}
		bottomBar.onUnitBg(3, R.color.page_bg_level_three);
		bottomBar.onUnitEnable(3, false);
	}

	/**
	 * ���οؼ��Ŀɴ���
	 * 
	 * @param enable
	 */
	private void blockBtnEnable(boolean enable) {
		if (enable) {
			bottomBar.onUnitBg(4, R.color.page_bg_level_one);
			bottomBar.onUnitEnable(4, true);
			return;
		}
		bottomBar.onUnitBg(4, R.color.page_bg_level_three);
		bottomBar.onUnitEnable(4, false);
	}

	/**
	 * ��ת�����û���������ҵҳ��
	 */
	@OnClick(R.id.com_item)
	public void comClick(View v) {
		ViewSwitchUtils.in2TopIntent(context, BusinessRelatePage.class, uid);
	}

	/**
	 * ��ת�����û�������Ϣҳ��
	 */
	@OnClick(R.id.more_item)
	public void moreClick(View v) {
		ViewSwitchUtils.in2LeftIntent(context, UserIfoDetailPage.class);
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		if (SOURCE_PAGE != null
				&& SOURCE_PAGE.equals(Enum_PageSource.MsgReceivePage.name())) {
			ViewSwitchUtils.finishOut2Bottom(context);
			return;
		}
		if(SOURCE_PAGE != null&&SOURCE_PAGE.equals(Enum_BackSytle.VETICAL.toString())){
			ViewSwitchUtils.finishOut2Bottom(context);
			return;
		}
		ViewSwitchUtils.finishOut2Right(context);
	}
}
