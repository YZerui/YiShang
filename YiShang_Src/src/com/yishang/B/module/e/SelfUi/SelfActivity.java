package com.yishang.B.module.e.SelfUi;

import java.io.File;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
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
import com.yishang.A.global.Enum.Enum_Gender;
import com.yishang.A.global.Enum.Enum_IfoType;
import com.yishang.A.global.baseClass.SuperActivity;
import com.yishang.A.global.constant.API;
import com.yishang.A.global.constant.CONSTANT;
import com.yishang.A.global.constant.CONSTANT_SELF;
import com.yishang.A.global.constant.PARAMS;
import com.yishang.A.global.writting.W_UserIfo;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.D.service.HttpResultService;
import com.yishang.E.view.PullScrollView;
import com.yishang.Z.utils.ViewSwitchUtils;

public class SelfActivity extends SuperActivity {
	@ViewInject(R.id.scroll_view)
	private PullScrollView scrollView;
	@ViewInject(R.id.self_Icon)
	private ImageView headIcon;
	@ViewInject(R.id.topBar)
	private CustomTopbarView topBar;
	@ViewInject(R.id.rank_item)
	private CustomItemView rankItem;
	@ViewInject(R.id.phone_item)
	private CustomItemView phoneItem;
	@ViewInject(R.id.label_item)
	private CustomItemView labelItem;
	@ViewInject(R.id.self_Name)
	private TextView name;
	@ViewInject(R.id.selfGenderIcon)
	private ImageView selfGenderIcon;
	
	private Bitmap uplodBitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.self_page_layout);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		scrollView.setHeader(headIcon);
	}

	@Override
	protected void onClickListener() {
		// TODO Auto-generated method stub
		topBar.setCallBack(new topBarCallBack() {

		});
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		name.setText(W_UserIfo.name(Dao_Self.getInstance().getUser_name()));
		//刷新界面
		imageLoader.displayImage(Dao_Self.getInstance().getUser_head(),headIcon, loadOptions);
		rankItem.setTextContent(W_UserIfo.rank(Dao_Self.getInstance().getUser_title1()));
		phoneItem.setTextContent(W_UserIfo.phone(Dao_Self.getInstance().getUser_phone()));
		labelItem.setTextContent(W_UserIfo.label(Dao_Self.getInstance().getUser_lable()));
		if(Dao_Self.getInstance().getUser_sex().equals(Enum_Gender.MAN.value())){
			selfGenderIcon.setImageResource(R.drawable.gender_man);
		}else {
			selfGenderIcon.setImageResource(R.drawable.gender_women);
		}
	}

	@OnClick(R.id.note_item)
	public void toMoreClick(View v) {
		ViewSwitchUtils.tab_in2LeftIntent_int(context,
				SelfIfoSettingPage.class, SelfIfoSettingPage.SOURCE_SELF_PAGE);
	}

	@OnClick(R.id.img_set)
	public void imgSetClick(View v) {
		// ViewSwitchUtils.tab_in2TopIntent(context, SelfImgSetDialog.class);
		ViewSwitchUtils.tab_in2TopIntent_result(SelfActivity.this,
				SelfImgSetDialog.class, PARAMS.PHOTO_REQUEST);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case PARAMS.PHOTO_REQUEST:
			if (PARAMS.REQUEST_CODE != PARAMS.PHOTO_CAPTURE_SUCCESS) {
//				toast.locatBottom().setText("头像文件出错,上传失败");
				return;
			}
			String savePath = Environment.getExternalStorageDirectory() + "/"
					+ CONSTANT.IMG_CROP_FOLDER_NAME + "/"
					+ CONSTANT.IMG_HEAD_CROP_NAME;
			uplodBitmap = BitmapFactory.decodeFile(savePath);
			if (uplodBitmap == null) {
				toast.locatBottom().setText("头像文件出错,上传失败");
				return;
			}

			// 将头像上传到服务端
			httpImgSet(savePath);

			// 模拟需要
			// headIcon.setImageBitmap(uplodBitmap);
			break;

		default:
			break;
		}
	}

	/**
	 * 更改头像操作
	 * 
	 * @param path
	 */
	public void httpImgSet(String path) {
		RequestParams params = new RequestParams();
		params.addBodyParameter("head", new File(path));
		params.addBodyParameter("uid", Dao_Self.getInstance().getUser_id());
		http.send(HttpMethod.POST, API.SELF_IMG_SET, params,
				new RequestCallBack<String>() {
					@Override
					public void onStart() {
						// TODO Auto-generated method stub
						super.onStart();
						httpImgRun();
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						httpEnd();
						toast.setText("网络出状况，编辑头像失败");
					}

					@Override
					public void onSuccess(ResponseInfo<String> params) {
						// TODO Auto-generated method stub
						new HttpResultService(params.result,
								new myHttpResultCallBack() {
									@Override
									public void onData(boolean validity,
											Object obj) {
										// TODO Auto-generated method stub
										super.onData(validity, obj);
										if (validity) {
											String head = (String) obj;
											Dao_Self.setParams(
													Enum_IfoType.user_head,
													head);
											imageLoader.displayImage(head,
													headIcon, loadOptions);
											return;
										}
										toast.setText("数据出错,编辑头像失败");
									}

									@Override
									public void onSuccess() {
										// TODO Auto-generated method stub

									}

									@Override
									public void onRequestFail() {
										// TODO Auto-generated method stub
										toast.setText("头像格式出错,编辑头像失败");
									}

									@Override
									public void onFinally() {
										// TODO Auto-generated method stub
										httpEnd();
									}
								}, null, false);
					}
				});
	}

	public void httpImgRun() {
		topBar.setTitle("上传头像...").setProVisibility(true);
	}

	public void httpEnd() {
		topBar.setTitle("我").setRighTextVisibility(true);
	}
}
