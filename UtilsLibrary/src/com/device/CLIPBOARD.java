package com.device;

import android.content.ClipboardManager;
import android.content.Context;

public class CLIPBOARD {
	/**
	 * ʵ���ı����ƹ��� add by wangqianzhou
	 * 
	 * @param content
	 */
	public static void copy(String content, Context context) {
		// �õ������������
		ClipboardManager cmb = (ClipboardManager) context
				.getSystemService(Context.CLIPBOARD_SERVICE);
		cmb.setText(content.trim());
	}

	/**
	 * ʵ��ճ������ add by wangqianzhou
	 * 
	 * @param context
	 * @return
	 */
	public static String paste(Context context) {
		// �õ������������
		ClipboardManager cmb = (ClipboardManager) context
				.getSystemService(Context.CLIPBOARD_SERVICE);
		return cmb.getText().toString().trim();
	}
}
