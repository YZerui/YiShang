package com.avos.avoscloud.PushDemo;

import org.json.JSONObject;

import com.avos.avoscloud.AVOSCloud;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class CustomReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
	  String jsonStr = intent.getExtras().getString(
				"com.avos.avoscloud.Data");
		System.out.println("收到易商推送消息Library222:"+jsonStr);
  }
}
