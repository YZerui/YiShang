package com.avos.avoscloud.PushDemo;

import android.app.Application;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.SaveCallback;

/**
 * Created with IntelliJ IDEA. User: tangxiaomin Date: 4/19/13 Time: 12:57 PM
 */
public class PushDemoApp extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    System.out.println("PushDemoApp application run...");
    // 初始化应用信息
    AVOSCloud.initialize(this, "bi59ml7oayentqx3a3i6njj611pffnkwv2yv1jqa0jw4pp4x",
        "p1dek02ab0h1gyyxcvmmzdrn78s2djky4lvw32iqj9c0va95");
    // 启用崩溃错误统计
    AVAnalytics.enableCrashReport(this.getApplicationContext(), true);
    AVInstallation.getCurrentInstallation().saveInBackground(
			new SaveCallback() {
				@Override
				public void done(AVException e) {
					AVInstallation.getCurrentInstallation()
							.saveInBackground();
				}
			});
  }
}
