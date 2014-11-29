package com.yishang.A.global.baseClass;

import java.util.List;

import com.customview.view.CustomTextView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.ruifeng.yishang.R;
import com.yishang.C.dao.daoModel.T_Relationships;
import com.yishang.E.view.ClearEditText;
import com.yishang.E.view.swipelistview.XListView;
import com.yishang.Z.utils.CharacterParser;
import com.yishang.Z.utils.PinyinComparator;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 搜索页面的基类
 * 
 * @author MM_Zerui
 * 
 */
public class SearchBoxActivity extends Activity {
	protected ClearEditText editText;
	protected Button cancelBtn;
	protected ListView localListView;
	protected XListView netListView;
	protected LinearLayout overallLayout, localLayout, netLayout;
	protected TextView localResultNote;
	protected CustomTextView customText;
	// 汉字转换成拼音的类
	protected CharacterParser characterParser;
	// 根据拼音来排列ListView里面的数据类
	protected PinyinComparator pinyinComparator;
	protected HttpUtils http;
	protected Context context;

	// 数据加载的分段标识
	protected int load_index = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_frame_layout);
		ViewUtils.inject(this);
		findViewById();
		initResource();
	}

	private void findViewById() {
		// TODO Auto-generated method stub
		editText = (ClearEditText) findViewById(R.id.editText);
		cancelBtn = (Button) findViewById(R.id.cancelBtn);
		localListView = (ListView) findViewById(R.id.localResultList);
		netListView = (XListView) findViewById(R.id.netResultList);
		localResultNote = (TextView) findViewById(R.id.localResultNote);
		overallLayout = (LinearLayout) findViewById(R.id.OverallLayout);
		localLayout = (LinearLayout) findViewById(R.id.localLayout);
		netLayout = (LinearLayout) findViewById(R.id.netLayout);
		customText = (CustomTextView) findViewById(R.id.customText);
	}

	private void initResource() {
		// TODO Auto-generated method stub
		context = this;
		characterParser = CharacterParser.getInstance();
		pinyinComparator = new PinyinComparator();
		http = new HttpUtils();
	}

	protected boolean ifMatch(String str, String filterStr) {
		if (str.indexOf(filterStr.toString()) != -1
				|| characterParser.getSelling(str).startsWith(
						filterStr)) {
			return true;
		}
		return false;
	}
}
