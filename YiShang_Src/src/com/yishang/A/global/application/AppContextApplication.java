package com.yishang.A.global.application;

import java.io.File;
import java.util.ArrayList;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVGeoPoint;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.PushService;
import com.avos.avoscloud.SaveCallback;
import com.avos.avoscloud.PushDemo.PushDemo;
import com.exception.utils.P;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.yishang.A.global.Enum.Enum_Avos;
import com.yishang.A.global.constant.CONSTANT;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap.CompressFormat;

public class AppContextApplication extends Application {
	private static AppContextApplication instance;

	public static AppContextApplication getInstance() {
		return instance;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		instance = this;
		// ͼƬ�������ĳ�ʼ��
//		initImageLoader(getApplicationContext());
		initImageLoader();
		initAvos();
		mapBaidu();
		initBugCrash();
//		testQueryAvos();
	}

    private void initBugCrash() {
		// TODO Auto-generated method stub
    	// ���ñ�����־��¼
//		CrashHandler crashHandler = CrashHandler.getInstance();
//		crashHandler.init(this);
	}

	private void initImageLoader() {
        int memClass = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
        int cacheSize = 1024 * 1024 * memClass / 8;  //ϵͳ�����ڴ��1/8

        DisplayImageOptions defaultOptions = new DisplayImageOptions
                .Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
//                .displayer(new FadeInBitmapDisplayer(600))
                .delayBeforeLoading(800)
//              .showImageOnLoading(R.drawable.ic_default_pic)
                .build();

         File cacheDir = StorageUtils.getCacheDirectory(getApplicationContext());
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration
                .Builder(getApplicationContext())
                .threadPoolSize(3)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(cacheSize))
                .memoryCacheSize(cacheSize)
//              .diskCache(new UnlimitedDiscCache(new File(Config.getAppIm.agesPath()))) // default
                .discCache(new UnlimitedDiscCache(cacheDir))
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100)
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(configuration);
    }

//	public void testQueryAvos(){
//		AVGeoPoint point = new AVGeoPoint(45.0, -80.0);
//		AVQuery<T_UserPoint> query = AVObject.getQuery(T_UserPoint.class);
////		query.whereWithinKilometers("location", point,1.0);
//		query.whereNear("locatePoint", point);
//		try {
//			ArrayList<T_UserPoint> list=(ArrayList<T_UserPoint>) query.find();
//			for(T_UserPoint item:list){
//				System.out.println("name:"+item.getUser_name());
//			}
//		} catch (AVException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	private void mapBaidu() {
		// TODO Auto-generated method stub
	}

	/**
	 * Avos����س�ʼ��
	 */
	private void initAvos() {

		// TODO Auto-generated method stub
		AVObject.registerSubclass(T_UserPoint.class);
		// ��ʼ��Ӧ����Ϣ
		AVOSCloud.initialize(this,
				Enum_Avos.ID.value(),
				Enum_Avos.KEY.value());
		// ���ñ�������ͳ��
		AVAnalytics.enableCrashReport(this.getApplicationContext(), true);
	
		//һ��Ҫ�ӣ���Ȼ�����
		PushService.setDefaultPushCallback(this, PushDemo.class);
	}

	/**
	 * ͼƬ�������ĳ�ʼ��
	 * 
	 * @param context
	 */
	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.threadPoolSize(3)
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
//				.discCacheExtraOptions(45, 45, null)//����Ӳ�̻��棬Ĭ�ϸ�ʽjpeg��ѹ������70
				.writeDebugLogs() // Remove for release app
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}
}
