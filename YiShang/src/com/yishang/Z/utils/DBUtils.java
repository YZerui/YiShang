package com.yishang.Z.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class DBUtils {
	public static Boolean getSharedPreferences(Context context, String str_note) {
		return context.getSharedPreferences("data", Activity.MODE_PRIVATE)
				.getBoolean(str_note, false);

	}

	public static void setSharedPreferences(Context context, String str_note,
			Boolean bool_note) {
		SharedPreferences.Editor localEditor = context.getSharedPreferences(
				"data", Activity.MODE_PRIVATE).edit();
		localEditor.putBoolean(str_note, bool_note.booleanValue());
		localEditor.commit();
	}

	public static void setSharedPreInt(Context context, String str_note, int m_num) {
		SharedPreferences.Editor localEditor = context.getSharedPreferences(
				"data", Activity.MODE_PRIVATE).edit();
		localEditor.putInt(str_note, m_num);
		localEditor.commit();
	}

	public static int getSharedPreInt(Context context, String str_note) {
		return context.getSharedPreferences("data", Activity.MODE_PRIVATE)
				.getInt(str_note, 0);

	}

	public static void setSharedPreStr(Context context, String str_note, String str) {
		SharedPreferences.Editor localEditor = context.getSharedPreferences(
				"data", Activity.MODE_PRIVATE).edit();
		localEditor.putString(str_note, str);
		localEditor.commit();
	}

	public static String getSharedPreStr(Context context, String str_note,String strDefault) {
		return context.getSharedPreferences("data", Activity.MODE_PRIVATE)
				.getString(str_note, strDefault);
	}

	public static void deleteSharePreFer(Context context) {
		SharedPreferences.Editor localEditor = context.getSharedPreferences(
				"data", Activity.MODE_PRIVATE).edit();
		localEditor.clear();
		localEditor.commit();
	}
}
