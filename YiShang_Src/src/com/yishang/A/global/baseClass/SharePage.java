package com.yishang.A.global.baseClass;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.sina.weibo.SinaWeibo.ShareParams;
import cn.sharesdk.system.email.Email;
import cn.sharesdk.system.text.ShortMessage;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

import com.device.CLIPBOARD;
import com.exception.utils.P;
import com.format.utils.DataValidate;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.ruifeng.yishang.R;
import com.tencent.connect.share.QzoneShare;
import com.yishang.A.global.writting.W_Share;
import com.yishang.B.module.b.ContactsUI.ContactsSelectPage;
import com.yishang.C.dao.daoImpl.Dao_Resource;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.C.dao.daoModel.T_Resource;
import com.yishang.D.service.httpRequest.HttpReq_GetTransUrl;
import com.yishang.D.service.httpRequest.HttpReq_GetTransUrl.CallBack_TransUrl;
import com.yishang.Z.utils.DeviceUtils;
import com.yishang.Z.utils.FormatUtils;
import com.yishang.Z.utils.ViewSwitchUtils;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ��ữ����ҳ��
 * 
 * @���� �ĵ�ID
 * @���� ԭʼת����ID
 * @author MM_Zerui
 * 
 */
public class SharePage extends SuperDialogActivity implements
		PlatformActionListener {
	@ViewInject(R.id.yishang)
	private ImageView yishang;
	@ViewInject(R.id.email)
	private ImageView email;
	@ViewInject(R.id.wechat)
	private ImageView wechat;
	@ViewInject(R.id.group)
	private ImageView group;
	@ViewInject(R.id.weibo)
	private ImageView weibo;
	@ViewInject(R.id.qq)
	private ImageView qq;
	@ViewInject(R.id.sns)
	private ImageView sns;
	@ViewInject(R.id.link)
	private ImageView link;
	@ViewInject(R.id.cancel)
	private TextView cancel;

	private String resId, sourceId, transUrl, resName, shareTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.app_share_page);
		ViewUtils.inject(this);
		super.onCreate(savedInstanceState);
		getIntentValue();

	}

	private void getIntentValue() {
		// TODO Auto-generated method stub
		resId = getIntent().getStringExtra("DATA0");
		sourceId = getIntent().getStringExtra("DATA1");
		transUrl = getIntent().getStringExtra("DATA2");
		P.v("׼�������URL��" + transUrl);
		resName = getIntent().getStringExtra("DATA3");
		shareTitle = "�ĵ�����";
		if (!DataValidate.checkDataValid(resName)) {
			resName = "һ�������ĵ��������";
		}
		if (!DataValidate.checkDataValid(transUrl)) {
			T_Resource resBean;
			try {
				resBean = Dao_Resource.getResource(resId);

				new HttpReq_GetTransUrl(new CallBack_TransUrl() {

					@Override
					public void onSuccess(String url) {
						// TODO Auto-generated method stub
						transUrl = url;
						P.v("���ת��URL:" + url);
					}

					@Override
					public void onFinally() {
						// TODO Auto-generated method stub

					}

					@Override
					public void onFail() {
						// TODO Auto-generated method stub
						P.v("���ת��URLʧ��");
					}
				}).setIniId(resBean.getBook_creater_id()).setResId(resId)
						.setUserId(Dao_Self.getInstance().getUser_id())
						.httpMethod();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void setPageHeight() {
		// TODO Auto-generated method stub
		lp.height = DeviceUtils.dip2px(SharePage.this, 230);
	}

	@Override
	protected void obtainIntentValue() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initResource() {
		// TODO Auto-generated method stub
		ShareSDK.initSDK(SharePage.this);
	}

	@Override
	protected void onClickListener() {
		// TODO Auto-generated method stub

	}

	@OnClick(R.id.yishang)
	public void yishangShare(View v) {
		try {
			ViewSwitchUtils.in2TopIntent(context, ContactsSelectPage.class,
					resId, sourceId,transUrl);
		} catch (Exception e) {
			// TODO: handle exception
			toast.setText("�޷�����,���ݻ��������");
		}
	}

	@OnClick(R.id.email)
	public void emailShare(View v) {
		// ��������
		if (!DataValidate.checkDataValid(transUrl)) {
			toast.setText("�ĵ����ݳ���,��֧��ת��");
			return;
		}
		try {
			ShareParams sp = new ShareParams();
			// sp.setShareType(Pl)
			// sp.setAddress("465931543@qq.com");
			sp.setTitle("����һ���ĵ�");
			sp.setText(W_Share.shareEmail("",transUrl));
			Platform email = ShareSDK.getPlatform(context, Email.NAME);
			email.setPlatformActionListener(this);
			email.share(sp);
		} catch (Exception e) {
			// TODO: handle exception
			toast.setText("�������,�汾���ͻ��ް�װ�˿ͻ���");
		}
	}

	@OnClick(R.id.wechat)
	public void wechatShare(View v) {
		if (!DataValidate.checkDataValid(transUrl)) {
			toast.setText("�ĵ����ݳ���,��֧��ת��");
			return;
		}
		try {
			ShareParams sp = new ShareParams();
			sp.setShareType(Platform.SHARE_WEBPAGE);
			sp.setUrl(transUrl);
			sp.setTitle(shareTitle);
			sp.setText("����һ���ĵ�: " + "��" + resName + "��");
			Platform weChat = ShareSDK.getPlatform(context, Wechat.NAME);
			weChat.setPlatformActionListener(this); // ���÷����¼��ص�
			// ִ��ͼ�ķ���
			weChat.share(sp);
		} catch (Exception e) {
			// TODO: handle exception
			toast.setText("�������,�汾���ͻ��ް�װ�˿ͻ���");
		}
	}

	@OnClick(R.id.group)
	public void groupShare(View v) {
		if (!DataValidate.checkDataValid(transUrl)) {
			toast.setText("�ĵ����ݳ���,��֧��ת��");
			return;
		}
		try {
			ShareParams sp = new ShareParams();
			sp.setShareType(Platform.SHARE_WEBPAGE);
			sp.setUrl(transUrl);
			// sp.setTitle(shareTitle);
			// sp.setText(resName);
			sp.setTitle("����һ���ĵ�: " + "��" + resName + "��");
//			sp.setTitle("����һ���ĵ�: " + "��" + resName + "��");
			Platform weChat = ShareSDK.getPlatform(context, WechatMoments.NAME);
			weChat.setPlatformActionListener(this); // ���÷����¼��ص�
			// ִ��ͼ�ķ���
			weChat.share(sp);
		} catch (Exception e) {
			// TODO: handle exception
			toast.setText("�������,�汾���ͻ��ް�װ�˿ͻ���");
		}
	}

	@OnClick(R.id.weibo)
	public void weiboShare(View v) {
		if (!DataValidate.checkDataValid(transUrl)) {
			toast.setText("�ĵ����ݳ���,��֧��ת��");
			return;
		}
		try {
			ShareParams sp = new ShareParams();
//			sp.setText("����һ���ܺõ��ĵ����������: " + transUrl);
			sp.setText(W_Share.shareSocial(transUrl));
			// sp.setImageUrl("http://www.hotoos.com/guimi/?from=groupmessage&isappinstalled=0");
			Platform weibo = ShareSDK.getPlatform(context, SinaWeibo.NAME);
			weibo.setPlatformActionListener(this); // ���÷����¼��ص�
			// ִ��ͼ�ķ���
			weibo.share(sp);
		} catch (Exception e) {
			// TODO: handle exception
			toast.setText("�������,�汾���ͻ��ް�װ�˿ͻ���");
		}
	}

	@OnClick(R.id.qq)
	public void qqShare(View v) {
		if (!DataValidate.checkDataValid(transUrl)) {
			toast.setText("�ĵ����ݳ���,��֧��ת��");
			return;
		}
		try {
			ShareParams sp = new ShareParams();
			sp.setTitle(shareTitle);
			sp.setTitleUrl(transUrl);
			sp.setText(resName);
			Platform qq = ShareSDK.getPlatform(context, QQ.NAME);
			qq.setPlatformActionListener(this);
			qq.share(sp);
		} catch (Exception e) {
			// TODO: handle exception
			toast.setText("�������,�汾���ͻ��ް�װ�˿ͻ���");
		}
	}

	@OnClick(R.id.sns)
	public void qqZoneShare(View v) {
		if (!DataValidate.checkDataValid(transUrl)) {
			toast.setText("�ĵ����ݳ���,��֧��ת��");
			return;
		}
		try{
		ShareParams sp = new ShareParams();
		sp.setText(W_Share.shareMsg("",transUrl));
//		sp.setText("�ҷ���һ��������ĵ����㿴,�������ҪŶ:"+ transUrl);
		Platform sns = ShareSDK.getPlatform(context, ShortMessage.NAME);
		sns.setPlatformActionListener(this);
		sns.share(sp);}catch (Exception e) {
			// TODO: handle exception
			toast.setText("�ĵ����ݳ���,��֧�ַ���");
		}
		
		// if(!DataValidate.checkDataValid(transUrl)){
		// toast.setText("�ĵ����ݳ���,��֧��ת��");
		// return;
		// }
		// ShareParams sp=new ShareParams();
		// sp.setTitle(shareTitle);
		// sp.setTitleUrl(transUrl);
		// sp.setSite("����");
		// sp.setSiteUrl("www.baidu.com");
		// sp.setText(transUrl);
		// Platform qzone = ShareSDK.getPlatform(context,QZone.NAME);
		// qzone.setPlatformActionListener(this);
		// qzone.share(sp);
		//

		// ShareParams sp = new ShareParams();
		// sp.setTitle("����");
		// sp.setTitleUrl("http://sharesdk.cn"); // ����ĳ�����
		// sp.setText("���Է���");
		// sp.setImageUrl("http://mmapiss.meimeime.com:80/ES/file/head/1416286484641iAu0B3P.jpg");
		// sp.setSite("�����ĵ�");
		// sp.setSiteUrl("http://www.hotoos.com/guimi/?from=groupmessage&isappinstalled=0");
		//
		// Platform qzone = ShareSDK.getPlatform (QZone.NAME);
		// qzone.setPlatformActionListener(this);
		// qzone.share(sp);
	}

	@OnClick(R.id.link)
	public void linkShare(View v) {
		// �������ӵ����а�
		if (!DataValidate.checkDataValid(transUrl)) {
			toast.setText("�ĵ����ݳ���,��֧�ָ���");
			return;
		}
		CLIPBOARD.copy(transUrl, context);
		toast.setText("���Ӹ��Ƴɹ�");
	}

	@OnClick(R.id.cancel)
	public void cancelClick(View v) {
		finish();
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		ViewSwitchUtils.finishOut2Bottom(context);
	}

	@Override
	public void onCancel(Platform arg0, int arg1) {
		// TODO Auto-generated method stub
		toast.setText("ȡ���˷���");
	}

	@Override
	public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
		// TODO Auto-generated method stub
		toast.setText("����");
	}

	@Override
	public void onError(Platform arg0, int arg1, Throwable arg2) {
		// TODO Auto-generated method stub
		toast.setText("�������");
	}

}
