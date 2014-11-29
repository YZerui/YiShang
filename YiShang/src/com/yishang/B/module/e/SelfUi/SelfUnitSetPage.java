package com.yishang.B.module.e.SelfUi;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomTopbarView;
import com.format.utils.DataValidate;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ruifeng.yishang.R;
import com.yishang.A.global.Enum.Enum_IfoType;
import com.yishang.A.global.Enum.Enum_SelfIfo;
import com.yishang.A.global.baseClass.SuperActivity;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.D.service.httpRequest.HttpReq_UpdateIfo;
import com.yishang.D.service.httpRequest.HttpReq_UpdateIfo.CallBack_UserIfo;
import com.yishang.E.view.ClearEditText;
import com.yishang.E.view.CustomToast;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * 个人信息项的设置页面
 * 
 * @author MM_Zerui
 * @intent_value 接收数据：个人信息的标识
 * @intent_value 文本信息
 * 
 */
public class SelfUnitSetPage extends SuperActivity {
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.editText)
	private ClearEditText editText;
	@ViewInject(R.id.note)
	private TextView note;
	
	private String title="未知项";
	
	private String result;
	
	private Enum_IfoType tEnum;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.self_unit_set_page);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);

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
				saveMethod();
			}
		});
	}
	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		SOURCE_PAGE_NOTE = Integer.valueOf(getIntent().getStringExtra("DATA0"));
		result=getIntent().getStringExtra("DATA1");
	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		editText.setText(result);
		switch (Enum_SelfIfo.getEnum(SOURCE_PAGE_NOTE)) {
		case NICKNAME:
			tEnum=Enum_IfoType.user_name;
			topBar.setTitle("编辑姓名");
			note.setText("请输入真实姓名,如 潘峰");
			break;
		case BIRTHDAY:
			tEnum=Enum_IfoType.user_bth;
			topBar.setTitle("编辑生日");
			break;
		case EMAIL:
			tEnum=Enum_IfoType.user_email;
			topBar.setTitle("编辑你的Email");
			note.setText("请输入Email,如465931543@qq.com");
			break;
		case FAX:
			tEnum=Enum_IfoType.user_fax;
			topBar.setTitle("编辑传真");
			note.setText("请输入传真,如0756-999999");
			break;
		case GENDER:
			tEnum=Enum_IfoType.user_sex;
			topBar.setTitle("编辑性别");
			break;
		case INTRO:
			tEnum=Enum_IfoType.user_intro;
			topBar.setTitle("编辑介绍信息");
			break;
		case LABEL:
			tEnum=Enum_IfoType.user_lable;
			topBar.setTitle("编辑你的标签");
			note.setText("请输入你的个人标签,如  APP推广   电商服务, 用空格隔开");
			break;
		case QQ:
			tEnum=Enum_IfoType.user_qq;
			topBar.setTitle("编辑QQ号码");
			break;
		case RANK:
			tEnum=Enum_IfoType.user_title1;
			topBar.setTitle("编辑你的头衔");
			note.setText("请输入你的头衔,如 易商公司CTO,多个标签用空格隔开");
			break;
		case WECHAT:
			tEnum=Enum_IfoType.user_wx;
			topBar.setTitle("编辑你的微信号");
			break;
		case ADDRESS:
			tEnum=Enum_IfoType.user_address;
			topBar.setTitle("编辑地址");
			note.setText("个人所在地,如 东莞市松山湖");
		default:
			break;
		}
//		topbarTitle.setText(title);
	}
	public void saveMethod(){
		result=editText.getText().toString();
		if(!DataValidate.checkDataValid(result)){
			new CustomToast(context).setText("请编辑好内容再提交").locatCenter().show();
			return;
		}
		new HttpReq_UpdateIfo(result,Enum_SelfIfo.getEnum(SOURCE_PAGE_NOTE), new CallBack_UserIfo() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				httpEnd();
				editSuccessMethod(result);
			}
			
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				httpRun();
			}
			
			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				httpEnd();
				toast.setText("出状况,修改失败了");
			}
		});
	}
	public void httpRun(){
		topBar.setTitle("提交...");
		topBar.setProVisibility(true);
	}
	public void httpEnd(){
		topBar.setTitle(title);
		topBar.setRighTextVisibility(true);
	}
	private void editSuccessMethod(String content) {
		// TODO Auto-generated method stub
		Dao_Self.setParams(tEnum,content);
		finish();
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		ViewSwitchUtils.finishOut2Bottom(context);
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}

}
