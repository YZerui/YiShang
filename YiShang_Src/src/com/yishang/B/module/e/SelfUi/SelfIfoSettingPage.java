package com.yishang.B.module.e.SelfUi;

import java.io.File;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomItemView;
import com.customview.view.CustomTopbarView;
import com.http.callBack.myHttpResultCallBack;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.ruifeng.yishang.R;
import com.thread.HandlerExtend;
import com.yishang.A.global.Enum.Enum_IfoType;
import com.yishang.A.global.Enum.Enum_SelfIfo;
import com.yishang.A.global.baseClass.SuperActivity;
import com.yishang.A.global.baseClass.TabBarActivity;
import com.yishang.A.global.constant.API;
import com.yishang.A.global.constant.CONSTANT;
import com.yishang.A.global.constant.CONSTANT_SELF;
import com.yishang.A.global.constant.PARAMS;
import com.yishang.A.global.constant.RECV_STATE;
import com.yishang.A.global.writting.W_UserIfo;
import com.yishang.B.module.f.LoginUi.LoginHomePage;
import com.yishang.C.dao.daoImpl.AppDaoImpl;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.C.dao.daoModel.T_SelfIfo;
import com.yishang.D.service.HttpResultService;
import com.yishang.E.view.CustomToast;
import com.yishang.E.view.MyDialog;
import com.yishang.E.view.ProgressDialog;
import com.yishang.Z.utils.DeviceUtils;
import com.yishang.Z.utils.FormatUtils;
import com.yishang.Z.utils.ParseUtils;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * ������Ϣ�趨ҳ��
 * 
 * @author MM_Zerui
 * @note ��ҳ�潫������������Դ��ע��ŵ��趨�͸���ҳ����趨
 * @note_1 ע��������趨ҳ��ֻ����������Ϣ�趨�ؼ�
 * @note_2 ����ҳ��������趨���˸�����Ϣ���趨�������Ƴ���¼���������̵ĵȲ�������
 * 
 */
public class SelfIfoSettingPage extends SuperActivity {
	public int SOURCE_PAGE;
	public final static int SOURCE_REGI_PAGE = 1;
	public final static int SOURCE_SELF_PAGE = 2;
	public final static int SOURCE_GENDER_PAGE = 3;
	
	@ViewInject(R.id.bottom)
	private TextView bottomBtn;
	@ViewInject(R.id.img_set)
	private ImageView imgSetBtn;
	@ViewInject(R.id.self_Icon)
	private ImageView selfIcon;
	@ViewInject(R.id.icon_layout)
	private RelativeLayout iconLayout;

	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	
	@ViewInject(R.id.head_item)
	private CustomItemView headItem;
	@ViewInject(R.id.nickname_item)
	private CustomItemView nameItem;
	@ViewInject(R.id.gender_item)
	private CustomItemView genderItem;

	@ViewInject(R.id.birthday_item)
	private CustomItemView birthdayItem;
	
	@ViewInject(R.id.email_item)
	private CustomItemView emailItem;
	@ViewInject(R.id.rank_item)
	private CustomItemView rankItem;
	@ViewInject(R.id.label_item)
	private CustomItemView labelItem;
	@ViewInject(R.id.fax_item)
	private CustomItemView faxItem;
	@ViewInject(R.id.address_item)
	private CustomItemView addressItem;

	private Bitmap uplodBitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.self_ifo_setting_page);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		SOURCE_PAGE_NOTE = getIntent().getIntExtra("DATA0", 0);
	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		switch (SOURCE_PAGE_NOTE) {
		// ��ע��ҳ��ת����
		case SOURCE_REGI_PAGE:
			topBar.setBackLayoutVisible(false);
			break;
		// �Ӹ�������ҳ����ת����
		case SOURCE_SELF_PAGE:
			topBar.setTitle("�ҵ���Ϣ");
			topBar.setBackLayoutVisible(true);
			iconLayout.setVisibility(View.GONE);
			bottomBtn.setText("ע�����˻�");
			
			break;
		default:
			break;
		}
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
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		refreshDataView();
	}

	public void refreshDataView() {
		T_SelfIfo bean=Dao_Self.getInstance();
		imageLoader.displayImage(Dao_Self.getInstance().getUser_head(),
				headItem.getRightNoteIcon(),loadOptions);
		nameItem.setTextContent(W_UserIfo.name(bean.getUser_name()));
		genderItem.setTextContent(W_UserIfo.gender(bean.getUser_sex()));
		emailItem.setTextContent(W_UserIfo.email(bean.getUser_email()));
		rankItem.setTextContent(W_UserIfo.rank(bean.getUser_title1()));
		labelItem.setTextContent(W_UserIfo.label(bean.getUser_lable()));
		birthdayItem.setTextContent(W_UserIfo.birthday(bean.getUser_bth()));
		faxItem.setTextContent(bean.getUser_fax());
		addressItem.setTextContent(bean.getUser_address());
	}

	

	/**
	 * �趨ͷ��
	 * 
	 * @param v
	 */
	@OnClick(R.id.img_set)
	public void imgSetClick(View v) {
//		Intent intent = new Intent(this, SelfImgSetDialog.class);
//		startActivityForResult(intent, PARAMS.PHOTO_CAPTURE_COMPLETE);
//		overridePendingTransition(R.anim.in_bottom_to_top_page, 0);
		ViewSwitchUtils.in2TopIntent_result(SelfIfoSettingPage.this, 
				SelfImgSetDialog.class, PARAMS.PHOTO_REQUEST);
	}

	/**
	 * ��ʵ�����趨
	 * 
	 * @param v
	 */
	@OnClick(R.id.nickname_item)
	public void nickNameClick(View v) {
//		ViewSwitchUtils.in2TopIntent_int(context, SelfUnitSetPage.class,
//				PARAMS.SELF_SET_NICKNAME);
		ViewSwitchUtils.in2TopIntent(context, SelfUnitSetPage.class,
				Enum_SelfIfo.NICKNAME.value(),Dao_Self.getInstance().getUser_name());
	}

	@OnClick(R.id.head_item)
	public void headItemClick(View v){
		ViewSwitchUtils.in2TopIntent_result(SelfIfoSettingPage.this, 
				SelfImgSetDialog.class, PARAMS.PHOTO_REQUEST);
	}
	/**
	 * �Ա��趨
	 * 
	 * @param v
	 */
	@OnClick(R.id.gender_item)
	public void genderClick(View v) {
		ViewSwitchUtils.in2TopIntent(context, SelfGenderSetDialog.class);
	}

	/**
	 * email�趨
	 * 
	 * @param v
	 */
	@OnClick(R.id.email_item)
	public void phoneClick(View v) {
//		ViewSwitchUtils.in2TopIntent_int(context, SelfUnitSetPage.class,
//				PARAMS.SELF_SET_EMAIL);
		ViewSwitchUtils.in2TopIntent(context, SelfUnitSetPage.class,
				Enum_SelfIfo.EMAIL.value(),Dao_Self.getInstance().getUser_email());
	}
	
	

	/**
	 * �����趨
	 * 
	 * @param v
	 */
	@OnClick(R.id.birthday_item)
	public void birthdayClick(View v) {
		ViewSwitchUtils.in2TopIntent(context, SelfBirthdaySetDialog.class);

	}

	/**
	 * ͷ���趨
	 * 
	 * @param v
	 */
	@OnClick(R.id.rank_item)
	public void randClick(View v) {
//		ViewSwitchUtils.in2TopIntent_int(context, SelfUnitSetPage.class,
//				PARAMS.SELF_SET_RANK);
		ViewSwitchUtils.in2TopIntent(context, SelfUnitSetPage.class,
				Enum_SelfIfo.RANK.value(),Dao_Self.getInstance().getUser_title1());
	}

	/**
	 * ��ǩ�趨
	 * 
	 * @param v
	 */
	@OnClick(R.id.label_item)
	public void labelClick(View v) {
//		ViewSwitchUtils.in2TopIntent_int(context, SelfUnitSetPage.class,
//				PARAMS.SELF_SET_LABEL);
		ViewSwitchUtils.in2TopIntent(context, SelfUnitSetPage.class,
				Enum_SelfIfo.LABEL.value(),Dao_Self.getInstance().getUser_lable());
	}

	@OnClick(R.id.about_item)
	public void aboutClick(View v) {

	}
	
	/**
	 * �༭����
	 * @param v
	 */
	@OnClick(R.id.fax_item)
	public void faxClick(View v){
		ViewSwitchUtils.in2TopIntent(context, SelfUnitSetPage.class,
				Enum_SelfIfo.FAX.value(),Dao_Self.getInstance().getUser_fax());
	}
	/**
	 * �༭��ַ
	 * @param v
	 */
	@OnClick(R.id.address_item)
	public void addressClick(View v){
		ViewSwitchUtils.in2TopIntent(context, SelfUnitSetPage.class,
				Enum_SelfIfo.ADDRESS.value(),Dao_Self.getInstance().getUser_address());
	}

	/**
	 * ��Ϊ�������
	 * 
	 * @param v
	 * @note ��ע��ҳ��ת��ֱ�ӽ��������ҳ
	 * @note �Ӹ���Ҳ��ת: �˳���¼
	 */
	@OnClick(R.id.bottom)
	public void bottomClick(View v) {
		switch (SOURCE_PAGE_NOTE) {
		// ��ע��ҳ��ת����
		case SOURCE_REGI_PAGE:
			ViewSwitchUtils.in2NormalIntent(context, TabBarActivity.class);
			break;
		// �Ӹ�������ҳ����ת����
		case SOURCE_SELF_PAGE:
			//ע�����˻�
			Dao_Self.delete();
			ViewSwitchUtils.in2NormalIntent(context, LoginHomePage.class);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case  PARAMS.PHOTO_REQUEST:
			System.out.println("���ս�ͼ���");
			if (PARAMS.REQUEST_CODE != PARAMS.PHOTO_CAPTURE_SUCCESS) {
//				toast.locatBottom().setText("ͷ���ļ�����,�ϴ�ʧ��");
				return;
			}
			String savePath = Environment.getExternalStorageDirectory() + "/"
					+ CONSTANT.IMG_CROP_FOLDER_NAME + "/"
					+ CONSTANT.IMG_HEAD_CROP_NAME;
			uplodBitmap = BitmapFactory.decodeFile(savePath);
			if (uplodBitmap == null) {
				toast.locatBottom().setText("ͷ���ļ�����,�ϴ�ʧ��");
				return;
			}

			
			// ��ͷ���ϴ��������
			httpImgSet(savePath);
			
			//ģ����Ҫ
//			selfIcon.setImageBitmap(uplodBitmap);
//			toast.setText("ģ����Ҫ,Ч������ʵ����");
			break;
//		case SOURCE_GENDER_PAGE:
//			SOURCE_PAGE = SOURCE_GENDER_PAGE;
//			String gender = data
//					.getStringExtra(SelfGenderSetDialog.INTENT_GENDER);
//			httpSet("sex", gender);
//			break;

		default:
			break;
		}
//		switch (resultCode) {
//		case value:
//			
//			break;
//
//		default:
//			break;
//		}
	}

	/**
	 * ����ͷ�����
	 * @param path
	 */
	public void httpImgSet(String path) {
		RequestParams params=new RequestParams();
		params.addBodyParameter("head", new File(path));
		params.addBodyParameter("uid",Dao_Self.getInstance().getUser_id());
		http.send(HttpMethod.POST, API.SELF_IMG_SET,params,new RequestCallBack<String>() {
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				super.onStart();
				topBar.setTitle("�ϴ�...").setProVisibility(true);
			}
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				toast.setText("�ϴ�ʧ��");
				topBar.setTitle("�ҵ���Ϣ").setProVisibility(false);
			}

			@Override
			public void onSuccess(ResponseInfo<String> params) {
				// TODO Auto-generated method stub
				new HttpResultService(params.result, new myHttpResultCallBack() {
					@Override
					public void onData(boolean validity, Object obj) {
						// TODO Auto-generated method stub
						super.onData(validity, obj);
						if(validity){
							Dao_Self.setParams(Enum_IfoType.user_head, (String)obj);
							imageLoader.displayImage(Dao_Self.getInstance().getUser_head(),
									headItem.getRightNoteIcon(),loadOptions);
							imageLoader.displayImage(Dao_Self.getInstance().getUser_head(),
									selfIcon,loadOptions);
							return;
						}
						toast.setText("�ϴ�ʧ��");
					}
					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onRequestFail() {
						// TODO Auto-generated method stub
						toast.setText("�ϴ�ʧ��");
					}
					
					@Override
					public void onFinally() {
						// TODO Auto-generated method stub
						topBar.setTitle("�ҵ���Ϣ").setProVisibility(false);
					}
				}, null, false);
			}
		});
	}
	public void httpSet(String paramName, final String paramValue) {
		
		
	}

	


	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		 ViewSwitchUtils.finishOut2Right(context);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		super.onBackPressed();
		switch (SOURCE_PAGE_NOTE) {
		// ��ע��ҳ��ת����
		case SOURCE_SELF_PAGE:
			finish();
			break;
		// �Ӹ�������ҳ����ת����
		case SOURCE_REGI_PAGE:
			new MyDialog(context).
			withTitle("��Ҫ��ʾ").
			withMessage("	���ƺ���Ϣ�����������ݵ���ʵ��,�������̻��Ĵ���Ŷ,ȷ����ɱ༭��?").
			withSwitchBtnLayout("������ҳ","�����༭").setRightBtnTextColor(CONSTANT.COLOR_NOTE).
			setOkBtnClickListener(new OnClickListener() {		
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ViewSwitchUtils.in2NormalIntent(context, TabBarActivity.class);
				}
			}).show();
			return;
		default:
			break;
		}
	}

	
}
