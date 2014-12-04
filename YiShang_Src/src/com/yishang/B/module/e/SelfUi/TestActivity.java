package com.yishang.B.module.e.SelfUi;

import com.customview.view.CustomItemView;
import com.customview.view.CustomTopbarView;
import com.exception.utils.P;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.ruifeng.yishang.R;

import com.yishang.B.module.c.ResourceEntity.Recv_bookIfo;
import com.yishang.D.service.httpRequest.HttpReq_GetResIfo;
import com.yishang.D.service.httpRequest.HttpReq_GetResIfo.CallBack_Res;
import com.yishang.E.view.CustomToast;

import com.yishang.Z.utils.ViewShowUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class TestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_layout);
//	
		new HttpReq_GetResIfo("8", new CallBack_Res() {

			@Override
			public void onSuccess(Recv_bookIfo bean) {
				// TODO Auto-generated method stub
				P.v("³É¹¦");
			}

			@Override
			public void onFinally() {
				// TODO Auto-generated method stub
				P.v("½áÊø");
			}

			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				P.v("Ê§°Ü");
			}
		});

		// topbarView.setCallBack(callBack);
	}

	

}
