package com.yishang.Z.utils;

import com.yishang.A.global.application.AppContextApplication;

import android.content.Context;
import android.widget.Toast;



/**
 * 关于视图显示的工具
 * @author MM_Zerui
 *
 */
public class ViewShowUtils {
	/**
	 * 普通的Toast
	 */
	public static void toastNormal(String str){
		Toast.makeText(AppContextApplication.getInstance(), str, Toast.LENGTH_LONG).show();
	}
	/**
	 * 普通的Toast
	 */
	public static void toastNormal(String str,Context context){
		Toast.makeText(context, str, Toast.LENGTH_LONG).show();
	}
}
