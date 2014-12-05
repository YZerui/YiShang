package com.yishang.B.module.a.MsgUi;

import android.os.Bundle;
import android.view.WindowManager.LayoutParams;
import android.widget.EditText;

import com.customview.callBack.topBarCallBack;
import com.customview.view.CustomTopbarView;
import com.format.utils.DataValidate;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ruifeng.yishang.R;
import com.yishang.A.global.baseClass.SuperDialogActivity;
import com.yishang.D.service.httpRequest.HttpReq_FeedBack;
import com.yishang.D.service.httpRequest.HttpReq_FeedBack.CallBack_FeedBack;
import com.yishang.Z.utils.ViewSwitchUtils;

/**
 * 反馈的窗口
 * @author MM_Zerui
 *
 */
public class FeedBackDialog extends SuperDialogActivity{
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.editText)
	private EditText editText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.msg_feed_back_layout);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
	}
	@Override
	public void setPageHeight() {
		// TODO Auto-generated method stub
		lp.height = getWindowManager().getDefaultDisplay().getHeight() / 5 * 2;
		getWindow().setAttributes(lp);// 将设置好属性的lp应用到对话框
	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		topBar.setCallBack(new topBarCallBack() {
			@Override
			public void call_leftTextBtnListener() {
				// TODO Auto-generated method stub
				super.call_leftTextBtnListener();
				finish();
			}
			@Override
			public void call_rightTextBtnListener() {
				// TODO Auto-generated method stub
				super.call_rightTextBtnListener();
				String info=editText.getText().toString();
				if(!DataValidate.checkDataValid(info)){
					toast.setText("请编辑好内容再提交");
					return;
				}
				new HttpReq_FeedBack(new CallBack_FeedBack() {
					
					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						toast.setText("提交成功,谢谢你的反馈");
						finish();
					}
					
					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						topBar.setProVisibility(true);
					}
					
					@Override
					public void onFinally() {
						// TODO Auto-generated method stub
						topBar.setProVisibility(false).setRightText("提交");
						
					}
					
					@Override
					public void onFail() {
						// TODO Auto-generated method stub
						toast.setText("提交失败,前检查你的内容个数和网络环境");
					}
				}).setContent(info).httpAction();
			}
		});
	}

	@Override
	protected void onClickListener() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		ViewSwitchUtils.finishOut2Bottom(context);
	}

}
