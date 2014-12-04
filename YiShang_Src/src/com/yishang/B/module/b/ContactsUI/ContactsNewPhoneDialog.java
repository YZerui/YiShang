package com.yishang.B.module.b.ContactsUI;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import cn.sharesdk.framework.statistics.NewAppReceiver;

import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomTopbarView;
import com.device.PHONE;
import com.format.callBack.callBack_dataVaildate;
import com.format.utils.DataValidate;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ruifeng.yishang.R;
import com.thread.HandlerExtend;
import com.thread.HandlerExtend.handleCallBack;
import com.yishang.A.global.Enum.constant.Enum_Color;
import com.yishang.A.global.Enum.db.Enum_IfRegister;
import com.yishang.A.global.baseClass.SuperDialogActivity;
import com.yishang.B.module.b.ContactsEntity.Recv_contacts;
import com.yishang.B.module.b.ContactsEntity.Recv_phoneCheck;
import com.yishang.B.module.e.SelfUi.UserIfoPage;
import com.yishang.C.dao.daoImpl.Dao_Relationship;
import com.yishang.C.dao.daoModel.T_Contacts;
import com.yishang.C.dao.daoModel.T_Relationships;
import com.yishang.D.service.httpRequest.HttpReq_GetRelationship;
import com.yishang.D.service.httpRequest.HttpReq_SYNCLocalContact;
import com.yishang.D.service.httpRequest.HttpReq_GetRelationship.CallBack_Rela;
import com.yishang.D.service.httpRequest.HttpReq_SYNCLocalContact.CallBack_CheckPhone;
import com.yishang.E.view.ClearEditText;
import com.yishang.E.view.MyDialog;
import com.yishang.Z.utils.DeviceUtils;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * 添加新号码的页面
 * @步骤  
 * 		@1 首先检验该手机号码是否已经存在本地，如果存在，提醒对方无需继续添加，否则转入下一步
 * 		@2 先将手机号码上传到服务端验证，如果对方号码已经注册，则启动同步通讯录的服务Service
 * 		@3 如果对方的手机号码未注册，则存入本地中
 * @author MM_Zerui
 *
 */
public class ContactsNewPhoneDialog extends SuperDialogActivity{
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.new_name)
	private ClearEditText editName;
	@ViewInject(R.id.new_phone)
	private ClearEditText editPhone;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.contacts_new_phone_dialog);
		ViewUtils.inject(this);
		context=this;
		super.onCreate(savedInstanceState);
	}
	@Override
	public void setPageHeight() {
		// TODO Auto-generated method stub
		lp.height=DeviceUtils.dip2px(ContactsNewPhoneDialog.this, 160);
	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initResource() {
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
				
				//添加号码到本地通讯录并同步
				DataValidate.checkPhone(editPhone.getText().toString(),new callBack_dataVaildate() {
					
					@Override
					public void call_valid() {
						// TODO Auto-generated method stub
						if(!DataValidate.checkDataValid(editName.getText().toString())){
							toast.setText("请编辑主人名称");
							return;
						}
						contactsMethod(editPhone.getText().toString(),editName.getText().toString());
					}
					
					@Override
					public void call_lengthShort() {
						// TODO Auto-generated method stub
						toast.setText("号码格式不正确");
					}
					
					@Override
					public void call_lengthNull() {
						// TODO Auto-generated method stub
						toast.setText("请输入手机号码");
					}
					
					@Override
					public void call_lengthLong() {
						// TODO Auto-generated method stub
						toast.setText("号码格式不正确");
					}
					
					@Override
					public void call_lengthInvalid() {
						// TODO Auto-generated method stub
						toast.setText("号码格式不正确");
					}
				});
			}
		});
	}
	/**
	 * 联系人处理方法
	 */
	private void contactsMethod(String phone,String name){
		topBar.setTitle("检验...").setProVisibility(true);
		try {
			final T_Relationships bean=Dao_Relationship.getByPhone_Total(phone);
			if(DataValidate.checkDataValid(bean)){
				myDialog=new MyDialog(context).withTitle("号码已存在")
				.withMessage("该号码主人# "+bean.getRela_name()+" #已经存在你人脉列表中,要前往查看么？")
				.withSwitchBtnLayout("前往", "取消")
				.setRightBtnTextColor(Enum_Color.TextNote.color())
				.setOkBtnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						myDialog.dismiss();
						ViewSwitchUtils.in2TopIntent(context, UserIfoPage.class,bean.getRela_id(),
								String.valueOf(bean.getRela_register()),bean.getRela_phone());
					}
				}).withShow();
				topBar.setTitle("添加新号码").setProVisibility(false).setRighTextVisibility(true);
				return;
			}
			
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		topBar.setTitle("上传验证...");
		//上传服务端验证号码
		new HttpReq_SYNCLocalContact(new CallBack_CheckPhone() {
			
			@Override
			public void onSuccess(List<Recv_phoneCheck> listDatas) {
				// TODO Auto-generated method stub
				Recv_phoneCheck bean=listDatas.get(0);
				//暂且以手机号为标识
				new HttpReq_GetRelationship().setPhone(bean.getUser_phone())
					.setCallBack(new CallBack_Rela() {
						
						@Override
						public void onSuccess(ArrayList<Recv_contacts> list) {
							// TODO Auto-generated method stub
							try {
								Dao_Relationship.addReqContact(list);
								handlerExtend.onInitView();
								return;
							} catch (DbException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							handlerExtend.onFail();
						}
						
						@Override
						public void onLoad(ArrayList<Recv_contacts> list) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onFail() {
							// TODO Auto-generated method stub
							//直接添加本号码到本地通讯录并设为联系人
							handlerExtend.onFail();
						}

						@Override
						public void onFinally() {
							// TODO Auto-generated method stub
							handlerExtend.onFinally();
						}
					}).onInit();
			}
			
			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				//直接添加本号码到本地通讯录并设为联系人
				handlerExtend.onFail();
				handlerExtend.onFinally();
			}
		}).setPhones(phone).httpRequest();
	}
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		ViewSwitchUtils.finishOut2Bottom(context);
	}
	private HandlerExtend handlerExtend=new HandlerExtend(new handleCallBack() {
		
		@Override
		public void call_onRefresh() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void call_onInit() {
			// TODO Auto-generated method stub
			//更新成功
			toast.setText("添加成功");
		
		}
		public void call_onFail() {
			//直接添加本号码到本地通讯录并设为联系人
			String phone=editPhone.getText().toString();
			String name=editName.getText().toString();
			PHONE.addContact2Local(context,
					phone,name);
			//添加到本应用数据库
			T_Contacts bean=new T_Contacts();
			bean.setName(name);
			bean.setPhone(phone);
			bean.setIfRegister(Enum_IfRegister.UNREGIST.value());
			try {
				Dao_Relationship.addUnRegiPhoneContact(bean);
				toast.setText("添加成功");
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		public void call_onFinally() {
			finish();
		};
	});
}
