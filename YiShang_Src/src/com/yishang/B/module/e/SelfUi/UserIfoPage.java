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
 * 显示用户信息的页面
 * 
 * @接收 #用户ID# #是否为注册易商用户# #电话号码# #来源#
 * @运行流程 首先根据传递的ID从服务端获取详情，如果成功本次操作结束。
 * @运行流程 如果没有传递到ID，则判断对象是否为通讯录好友
 * @运行流程 如果对象为通讯录好友，则以电话为标识从本地获取相应信息
 * @运行流程 如果对象为注册用户，则根据ID从本地获取相应信息
 * 
 * @备注 对方和你的关系强度大于1才可打电话
 * @备注 设为客户 和 屏蔽控件的 变化
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
		topBar.setTitle("获取...").setProVisibility(true);
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
					topBar.setTitle("用户详情");
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
						// 从本地获取信息
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
								topBar.setTitle("用户详情");
								return;
							}
						} catch (DbException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						dataError();
						return;
					}
					// 说明为通讯录好友
					try {
						// 设定更多信息按钮不可用
						moreItem.setEnabled(false);
//						toast.setText("无更多信息");
						bean = Dao_Relationship.getByPhone(phone);

						if (bean != null && DataValidate.checkDataValid(phone)) {
							name.setText(W_UserIfo.name(bean.getRela_name()));
							rankItem.setTextContent(W_UserIfo.rank(bean
									.getRela_rank()));
							labelItem.setTextContent(W_UserIfo.label(""));
							imageLoader.displayImage(bean.getRela_head(),
									headImg, loadOptions);
							topBar.setTitle("通讯录好友");
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
					// 控件的变化
					try {
						T_Relationships tBean = Dao_Relationship.getByID(uid);
						Enum_RelaType enumType = Enum_RelaType
								.valueOf(Utils_DBRNote.handle(tBean
										.getRela_type()));
						switch (enumType) {
						case CLIENT_SUPPLIER:
						case CLIENT:
							// 客户控件不可控
							clientBtnEnable(false);
							break;
						case BLACKLIST:
							// 屏蔽控件不可控
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
		toast.setText("获取信息出错");
		bottomBar.setVisibility(View.GONE);
		topBar.setTitle("信息出错");
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
				// 跳转到商机详情页面（用户间）
				ViewSwitchUtils.in2LeftIntent(context, MsgReceivePage.class,
						uid, Enum_PageSource.UserIfoPage.name());
			}
		});
		bottomBar.setCallBack(new callBack_Bar() {

			@Override
			public void call_FirstItem(int... position) {
				// 打电话
				// 用intent启动拨打电话 ，关系强度大于1才可以(对于已注册好友)
				// 注意，拨打操作要和服务端进行同步
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
										toast.setText("数据异常,无法进行该操作");
									}
								}).httpMethod();

						return;
					}
					toast.setText("你和对方的商务关系不够强，无法进行拨打电话操作");
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}

			}

			@Override
			public void call_SecondItem(int... position) {
				// 转发文档
				ViewSwitchUtils.in2TopIntent(context, ResourceSelectPage.class,
						uid);
			}

			@Override
			public void call_ThirdItem(int... position) {
				// 设为客户
				new HttpReq_SetClient(clientCall).setCid(uid).httpMethod();
			}

			@Override
			public void call_FourthItem(int... position) {
				// 屏蔽
				new HttpReq_BlockUser(blockCall).setCid(uid).setReason("屏蔽")
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
			toast.setText("设定成功");
			clientBtnEnable(false);
			blockBtnEnable(true);
			// 同步本地通讯录
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
			toast.setText("操作失败");
		}
	};
	private CallBack_BlockUser blockCall = new CallBack_BlockUser() {

		@Override
		public void onSuccess() {
			// TODO Auto-generated method stub
			toast.setText("已屏蔽该用户");
			blockBtnEnable(false);
			clientBtnEnable(true);
			// 同步本地通讯录
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
			toast.setText("操作失败");
		}
	};

	/**
	 * 设定客户控件的可触性
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
	 * 屏蔽控件的可触性
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
	 * 跳转到该用户所关联企业页面
	 */
	@OnClick(R.id.com_item)
	public void comClick(View v) {
		ViewSwitchUtils.in2TopIntent(context, BusinessRelatePage.class, uid);
	}

	/**
	 * 跳转到该用户更多信息页面
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
