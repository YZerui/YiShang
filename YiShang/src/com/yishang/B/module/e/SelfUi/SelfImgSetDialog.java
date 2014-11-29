package com.yishang.B.module.e.SelfUi;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.ruifeng.yishang.R;
import com.yishang.A.global.baseClass.SuperDialogActivity;
import com.yishang.A.global.constant.CONSTANT;
import com.yishang.A.global.constant.PARAMS;
import com.yishang.Z.utils.DeviceUtils;
import com.yishang.Z.utils.FileUtils;
import com.yishang.Z.utils.ViewSwitchUtils;

public class SelfImgSetDialog extends SuperDialogActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.self_img_choose_dialog);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initResource() {

	}

	@OnClick(R.id.captureBtn)
	public void captureClick(View v) {
		photoCapture();
	}

	@OnClick(R.id.photoBtn)
	public void photoClick(View v) {
		photoSelect();
	}

	@OnClick(R.id.cancelBtn)
	public void exitClick(View v) {
		finish();
	}

	/**
	 * �������ѡ��ͼƬ
	 */
	private void photoSelect() {
		// TODO Auto-generated method stub
		Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
		PARAMS.REQUEST_CODE = PARAMS.PHOTO_CROP;
		openAlbumIntent.setDataAndType(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
		startActivityForResult(openAlbumIntent, PARAMS.REQUEST_CODE);
	}

	/**
	 * ����ȡͼ
	 */
	private void photoCapture() {
		Uri imageUri = null;
		Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// ɾ����һ�ν�ͼ����ʱ�ļ�
		SharedPreferences sharedPreferences = getSharedPreferences("temp",
				Context.MODE_WORLD_WRITEABLE);
		FileUtils.deletePhotoAtPathAndName(Environment
				.getExternalStorageDirectory().getAbsolutePath(),
				CONSTANT.IMG_HEAD_CROP_NAME);

		imageUri = Uri.fromFile(new File(Environment
				.getExternalStorageDirectory(), CONSTANT.IMG_HEAD_CROP_NAME));
		// ָ����Ƭ����·����SD������image.jpgΪһ����ʱ�ļ���ÿ�����պ����ͼƬ���ᱻ�滻
		openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		PARAMS.REQUEST_CODE = PARAMS.PHOTO_CROP;
		startActivityForResult(openCameraIntent, PARAMS.REQUEST_CODE);
	}

	// ��ȡͼƬ
	public void cropImage(Uri uri, int outputX, int outputY, int requestCode) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", outputX);
		intent.putExtra("outputY", outputY);
		intent.putExtra("outputFormat", "JPEG");
		intent.putExtra("noFaceDetection", true);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, requestCode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onActivityResult(int, int,
	 * android.content.Intent) ��ҳ�����ʱ����
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		// ����ͼ������ʱ,����Ӧ�öԽغ��ͼ���б��桢��ʾ�ȴ���
		case PARAMS.PHOTO_CAPTURE:
			Bitmap photo = null;
			if (data != null) {
				Uri photoUri = data.getData();
				System.out.println("URI:" + photoUri);
				if (photoUri != null) {
					photo = BitmapFactory.decodeFile(photoUri.getPath());
				}
				if (photo == null) {
					Bundle extra = data.getExtras();
					if (extra != null) {
						photo = (Bitmap) extra.get("data");
					}
				}
				if (photo == null) {
					return;
				}
				// �����ͼͼƬ������
				FileUtils
						.savePhotoToSDCard(photo, Environment
								.getExternalStorageDirectory()
								.getAbsolutePath()
								+ "/" + CONSTANT.IMG_CROP_FOLDER_NAME,
								CONSTANT.IMG_HEAD_CROP);
				PARAMS.REQUEST_CODE = PARAMS.PHOTO_CAPTURE_SUCCESS;
				
//				Intent intent = new Intent(this, SelfIfoSettingPage.class);
//				setResult(PARAMS.REQUEST_CODE, intent);
				finish();
			}

			break;
		// �������ս�ͼģʽʱ����
		case PARAMS.PHOTO_CROP:
			Uri uri = null;
			if (data != null) {
				uri = data.getData();
				System.out.println("Data");
			} else {
				System.out.println("File");
				uri = Uri.fromFile(new File(Environment
						.getExternalStorageDirectory(),
						CONSTANT.IMG_HEAD_CROP_NAME));
			}
			cropImage(uri, PARAMS.IMG_WALL_SIZE, PARAMS.IMG_WALL_SIZE,
					PARAMS.PHOTO_CAPTURE);
			break;
		default:
			break;
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		ViewSwitchUtils.finishOut2Bottom(context);
	}

	@Override
	protected void onClickListener() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPageHeight() {
		lp.height = DeviceUtils.dip2px(SelfImgSetDialog.this, 140);
		getWindow().setAttributes(lp);// �����ú����Ե�lpӦ�õ��Ի���
	}
}
