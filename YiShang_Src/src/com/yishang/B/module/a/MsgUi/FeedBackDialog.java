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
 * �����Ĵ���
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
		getWindow().setAttributes(lp);// �����ú����Ե�lpӦ�õ��Ի���
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
					toast.setText("��༭���������ύ");
					return;
				}
				new HttpReq_FeedBack(new CallBack_FeedBack() {
					
					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						toast.setText("�ύ�ɹ�,лл��ķ���");
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
						topBar.setProVisibility(false).setRightText("�ύ");
						
					}
					
					@Override
					public void onFail() {
						// TODO Auto-generated method stub
						toast.setText("�ύʧ��,ǰ���������ݸ��������绷��");
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
