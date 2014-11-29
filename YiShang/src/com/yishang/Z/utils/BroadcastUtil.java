package com.yishang.Z.utils;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class BroadcastUtil {
	public static void sendBroadCast(Context context,String action) {
		// 发送刷新列表的广播
		Intent intent = new Intent();
		intent.setAction(action);
		context.sendBroadcast(intent);
	}
	public static void regiReceiver(Activity activity,BroadcastReceiver receiver,String action){
		
		IntentFilter filter_system = new IntentFilter();
		filter_system.addAction(action);
		activity.registerReceiver(receiver, filter_system);
	}
}
