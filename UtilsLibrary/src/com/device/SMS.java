package com.device;

import java.util.List;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

public class SMS {
	/**
	 * 发送短信
	 * 
	 * @param context
	 * @param mobile
	 * 		手机号码
	 * @param content
	 * 		短信内容
	 */
	public static void SEND(Context context, String mobile, String content) {
		SmsManager smsManager = SmsManager.getDefault();
		PendingIntent sentIntent = PendingIntent.getBroadcast(context, 0,
				new Intent(), 0);

		if (content.length() >= 70) {
			// 短信字数大于70，自动分条
			List<String> ms = smsManager.divideMessage(content);

			for (String str : ms) {
				// 短信发送
				smsManager.sendTextMessage(mobile, null, str, sentIntent, null);
			}
		} else {
			smsManager.sendTextMessage(mobile, null, content, sentIntent, null);
		}
	}
}
