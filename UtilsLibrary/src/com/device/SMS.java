package com.device;

import java.util.List;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

public class SMS {
	/**
	 * ���Ͷ���
	 * 
	 * @param context
	 * @param mobile
	 * 		�ֻ�����
	 * @param content
	 * 		��������
	 */
	public static void SEND(Context context, String mobile, String content) {
		SmsManager smsManager = SmsManager.getDefault();
		PendingIntent sentIntent = PendingIntent.getBroadcast(context, 0,
				new Intent(), 0);

		if (content.length() >= 70) {
			// ������������70���Զ�����
			List<String> ms = smsManager.divideMessage(content);

			for (String str : ms) {
				// ���ŷ���
				smsManager.sendTextMessage(mobile, null, str, sentIntent, null);
			}
		} else {
			smsManager.sendTextMessage(mobile, null, content, sentIntent, null);
		}
	}
}
