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
 * 请求服务端（本地）数据并进行列表显示的Activity
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
	// 判断返回刷新的标志
	// protected boolean REFRESH_NOTE;
	/**
	 * 请求方式（刷新0/加载更多1）
	 */
	// protected int requestMode;
	protected int limit;// 每次请求的页数
	protected int start;// 每次请求的起始位置
	protected SimpleHeader header;
	protected  SimpleFooter footer; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	
		FRESH_FIRST = true;
		// 设定默认数值
		limit = CONSTANT.LIST_LOAD_LIMIT_DEFAULT;
		start = 0;

		//设定列表刷新头部样式
		header = new SimpleHeader(this);
		header.setTextColor(getResources().getColor(R.color.color_note));
		header.setCircleColor(getResources().getColor(R.color.color_note));
		
		// 设置加载更多的样式（可选）
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
