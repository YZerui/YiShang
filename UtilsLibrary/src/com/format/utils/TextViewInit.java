package com.format.utils;

import android.view.View;
import android.widget.TextView;

/**
 * TextView的初始化工作
 * @author MM_Zerui
 *
 */
public class TextViewInit {
	public static void textViewDefault(TextView textView,String valueStr,String defaultStr){
		if(DataValidate.checkDataValid(valueStr)){
			textView.setText(valueStr);
			return;
		}
		if(!DataValidate.checkDataValid(defaultStr)){
			textView.setVisibility(View.INVISIBLE);
			return;
		}
		textView.setText(defaultStr);
	}
}
