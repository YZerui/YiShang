package com.yishang.Z.utils;

import com.yishang.A.global.application.AppContextApplication;

import android.content.Context;
import android.widget.Toast;



/**
 * ������ͼ��ʾ�Ĺ���
 * @author MM_Zerui
 *
 */
public class ViewShowUtils {
	/**
	 * ��ͨ��Toast
	 */
	public static void toastNormal(String str){
		Toast.makeText(AppContextApplication.getInstance(), str, Toast.LENGTH_LONG).show();
	}
	/**
	 * ��ͨ��Toast
	 */
	public static void toastNormal(String str,Context context){
		Toast.makeText(context, str, Toast.LENGTH_LONG).show();
	}
}
