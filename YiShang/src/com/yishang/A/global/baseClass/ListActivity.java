package com.yishang.A.global.baseClass;

import zrc.widget.SimpleFooter;
import zrc.widget.SimpleHeader;

import com.ruifeng.yishang.R;
import com.yishang.A.global.constant.CONSTANT;
import com.yishang.A.global.constant.PARAMS;
import com.yishang.E.view.swipelistview.XListView;
import com.yishang.E.view.swipelistview.XListView.IXListViewListener;

import android.app.Dialog;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.AdapterView.OnItemClickListener;

/**
 * �������ˣ����أ����ݲ������б���ʾ��Activity
 * 
 * @author MM_Zerui
 */
public abstract class ListActivity extends SuperActivity {

	final protected static int REFRESH_LIST_MSG = 1000;
	final protected static int INIT_LIST_MSG = 1001;

	protected Boolean FRESH_FIRST;
	protected XListView xListView;

	protected ImageView defaultPage;
	protected ImageView defaultRefresh;
	protected ProgressBar defaultProgressBar;
	protected RelativeLayout defaultLayout;
//	protected Handler handler;
	// �жϷ���ˢ�µı�־
	// protected boolean REFRESH_NOTE;
	/**
	 * ����ʽ��ˢ��0/���ظ���1��
	 */
	// protected int requestMode;
	protected int limit;// ÿ�������ҳ��
	protected int start;// ÿ���������ʼλ��
	protected SimpleHeader header;
	protected  SimpleFooter footer; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	
		FRESH_FIRST = true;
		// �趨Ĭ����ֵ
		limit = CONSTANT.LIST_LOAD_LIMIT_DEFAULT;
		start = 0;

		//�趨�б�ˢ��ͷ����ʽ
		header = new SimpleHeader(this);
		header.setTextColor(getResources().getColor(R.color.color_note));
		header.setCircleColor(getResources().getColor(R.color.color_note));
		
		// ���ü��ظ������ʽ����ѡ��
        footer = new SimpleFooter(this);
        footer.setCircleColor(0xff33bbee);
//        handler=new Handler();
    	super.onCreate(savedInstanceState);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

	}

	protected abstract void outFinish();

}
