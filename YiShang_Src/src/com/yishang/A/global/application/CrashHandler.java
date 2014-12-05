package com.yishang.A.global.application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yishang.A.global.Enum.constant.Enum_Color;
import com.yishang.A.global.constant.API;
import com.yishang.E.view.CustomToast;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.text.style.LineHeightSpan.WithDensity;
import android.util.Log;
import android.widget.Toast;

/**
 * ClassName: CrashHandler Function:
 * UncaughtException������,��������Uncaught�쳣��ʱ��,�ɸ������ӹܳ���,����¼���ʹ��󱨸�.
 * 
 * @author Norris Norris.sly@gmail.com
 * @version
 * @since Ver 1.0 I used to be a programmer like you, then I took an arrow in
 *        the knee
 * @Date 2013 2013-3-24 ����12:27:10
 * @see 
 *      ��������������������������������������������������������������������������������������������������������������������������������������������������
 *      ����������������������������������������������������������
 * @Fields 
 *         ��������������������������������������������������������������������������������������������������������������������������������������������
 *         ����������������������������������������������������������������
 * @Methods 
 *          ������������������������������������������������������������������������������������������������������������������������������������������
 *          ������������������������������������������������������������������ 2013-3-24����12:27:10 Modified By
 *          Norris
 *          ����������������������������������������������������������������������������������������������������������������������������
 *          ��������������������������������������������������������������������������������
 */
public class CrashHandler implements UncaughtExceptionHandler {
	/**
	 * Log��־��tag String : TAG
	 * 
	 * @since 2013-3-21����8:44:28
	 */
	private static final String TAG = "NorrisInfo";
	/**
	 * ϵͳĬ�ϵ�UncaughtException������ Thread.UncaughtExceptionHandler :
	 * mDefaultHandler
	 * 
	 * @since 2013-3-21����8:44:43
	 */
	private Thread.UncaughtExceptionHandler mDefaultHandler;
	/**
	 * CrashHandlerʵ�� CrashHandler : mInstance
	 * 
	 * @since 2013-3-21����8:44:53
	 */
	private static CrashHandler mInstance = new CrashHandler();
	/**
	 * �����Context���� Context : mContext
	 * 
	 * @since 2013-3-21����8:45:02
	 */
	private Context mContext;
	/**
	 * �����洢�豸��Ϣ���쳣��Ϣ Map<String,String> : mLogInfo
	 * 
	 * @since 2013-3-21����8:46:15
	 */
	private Map<String, String> mLogInfo = new HashMap<String, String>();
	/**
	 * ���ڸ�ʽ������,��Ϊ��־�ļ�����һ����(FIXME ע����windows���ļ����޷�ʹ�ã��ȷ��ţ�) SimpleDateFormat :
	 * mSimpleDateFormat
	 * 
	 * @since 2013-3-21����8:46:39
	 */
	private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(
			"yyyyMMdd_HH-mm-ss");

	/**
	 * Creates a new instance of CrashHandler.
	 */
	private CrashHandler() {
	}

	/**
	 * getInstance:{��ȡCrashHandlerʵ�� ,����ģʽ } ��������������������������������������������������������������������
	 * 
	 * @return CrashHandler
	 * @throws
	 * @since I used to be a programmer like you, then I took an arrow in the
	 *        knee��Ver 1.0
	 *        ������������������������������������������������������������������������������������������������������������
	 *        ������������������������������������������������������������������������������������������������
	 *        2013-3-21����8:52:24 Modified By Norris
	 *        ����������������������������������������������������������
	 *        ����������������������������������������������������������������������������
	 *        ����������������������������������������������������������������������
	 */
	public static CrashHandler getInstance() {
		return mInstance;
	}

	/**
	 * init:{��ʼ��} ��������������������������������������������������������������������
	 * 
	 * @param paramContext
	 * @return void
	 * @throws
	 * @since I used to be a programmer like you, then I took an arrow in the
	 *        knee��Ver 1.0
	 *        ������������������������������������������������������������������������������������������������������������
	 *        ������������������������������������������������������������������������������������������������
	 *        2013-3-21����8:52:45 Modified By Norris
	 *        ����������������������������������������������������������
	 *        ����������������������������������������������������������������������������
	 *        ����������������������������������������������������������������������
	 */
	public void init(Context paramContext) {
		mContext = paramContext;
		// ��ȡϵͳĬ�ϵ�UncaughtException������
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		// ���ø�CrashHandlerΪ�����Ĭ�ϴ�����
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	/**
	 * ��UncaughtException����ʱ��ת�����д�ķ��������� (non-Javadoc)
	 * 
	 * @see java.lang.Thread.UncaughtExceptionHandler#uncaughtException(java.lang.Thread,
	 *      java.lang.Throwable)
	 */
	public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
		if (!handleException(paramThrowable) && mDefaultHandler != null) {
			// ����Զ����û�д�������ϵͳĬ�ϵ��쳣������������
			mDefaultHandler.uncaughtException(paramThread, paramThrowable);
		} else {
			try {
				// ��������ˣ��ó����������1�����˳�����֤�ļ����沢�ϴ���������
				paramThread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// �˳�����
			AppManager.getAppManager().finishAllActivity();
			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(1);
		}
	}

	/**
	 * handleException:{�Զ��������,�ռ�������Ϣ ���ʹ��󱨸�Ȳ������ڴ����.}
	 * ��������������������������������������������������������������������
	 * 
	 * @param paramThrowable
	 * @return true:��������˸��쳣��Ϣ;���򷵻�false.
	 * @throws
	 * @since I used to be a programmer like you, then I took an arrow in the
	 *        knee��Ver 1.0
	 *        ������������������������������������������������������������������������������������������������������������
	 *        ������������������������������������������������������������������������������������������������
	 *        2013-3-24����12:28:53 Modified By Norris
	 *        ��������������������������������������������������������
	 *        ������������������������������������������������������������������������������
	 *        ����������������������������������������������������������������������
	 */
	public boolean handleException(Throwable paramThrowable) {
		if (paramThrowable == null)
			return false;
		new Thread() {
			public void run() {
				Looper.prepare();
				// Toast.makeText(mContext , "�Բ����ˣ�Ӧ����Ƣ����Ŷ���������˳���" ,
				// Toast.LENGTH_LONG).show() ;
//				new CustomToast(mContext).locatCenter()
//						.setText("Ӧ�þ�Ȼ��Ƣ����Ŷ�����ǽ������ύ�����������޸���")
//						.withTextColor(COMMON.COLOR_WHITE).withShowLong().show();
				new CustomToast(mContext)
					.locatCenter().withShowLong()
					.setText("Ӧ�ó�״����,���ǽ��˴�����������ʨ����");
				
				Looper.loop();
			}
		}.start();
		// ��ȡ�豸������Ϣ
		getDeviceInfo(mContext);
		// ������־�ļ�
		saveCrashLogToFile(paramThrowable);
		return true;
	}

	/**
	 * getDeviceInfo:{��ȡ�豸������Ϣ} ��������������������������������������������������������������������
	 * 
	 * @param paramContext
	 * @throws
	 * @since I used to be a programmer like you, then I took an arrow in the
	 *        knee��Ver 1.0
	 *        ������������������������������������������������������������������������������������������������������������
	 *        ������������������������������������������������������������������������������������������������
	 *        2013-3-24����12:30:02 Modified By Norris
	 *        ��������������������������������������������������������
	 *        ������������������������������������������������������������������������������
	 *        ����������������������������������������������������������������������
	 */
	public void getDeviceInfo(Context paramContext) {
		try {
			// ��ð�������
			PackageManager mPackageManager = paramContext.getPackageManager();
			// �õ���Ӧ�õ���Ϣ������Activity
			PackageInfo mPackageInfo = mPackageManager.getPackageInfo(
					paramContext.getPackageName(),
					PackageManager.GET_ACTIVITIES);
			if (mPackageInfo != null) {
				String versionName = mPackageInfo.versionName == null ? "null"
						: mPackageInfo.versionName;
				String versionCode = mPackageInfo.versionCode + "";
				mLogInfo.put("versionName", versionName);
				mLogInfo.put("versionCode", versionCode);
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		// �������
		Field[] mFields = Build.class.getDeclaredFields();
		// ����Build���ֶ�key-value �˴�����Ϣ��Ҫ��Ϊ���ڷ��������ֻ����ְ汾�ֻ������ԭ��
		for (Field field : mFields) {
			try {
				field.setAccessible(true);
				mLogInfo.put(field.getName(), field.get("").toString());
				Log.d(TAG, field.getName() + ":" + field.get(""));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * saveCrashLogToFile:{��������Log���浽����} TODO ����չ����Log�ϴ���ָ��������·��
	 * ��������������������������������������������������������������������
	 * 
	 * @param paramThrowable
	 * @return FileName
	 * @throws
	 * @since I used to be a programmer like you, then I took an arrow in the
	 *        knee��Ver 1.0
	 *        ������������������������������������������������������������������������������������������������������������
	 *        ������������������������������������������������������������������������������������������������
	 *        2013-3-24����12:31:01 Modified By Norris
	 *        ��������������������������������������������������������
	 *        ������������������������������������������������������������������������������
	 *        ����������������������������������������������������������������������
	 */
	private String saveCrashLogToFile(Throwable paramThrowable) {
		StringBuffer mStringBuffer = new StringBuffer();
		for (Map.Entry<String, String> entry : mLogInfo.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			mStringBuffer.append(key + "=" + value + "\r\n");
		}
		Writer mWriter = new StringWriter();
		PrintWriter mPrintWriter = new PrintWriter(mWriter);
		paramThrowable.printStackTrace(mPrintWriter);
		Throwable mThrowable = paramThrowable.getCause();
		// ����ջ���а����е��쳣��Ϣд��writer��
		while (mThrowable != null) {
			mThrowable.printStackTrace(mPrintWriter);
			// ���� ÿ�����쳣ջ֮�任��
			mPrintWriter.append("\r\n");
			mThrowable = mThrowable.getCause();
		}
		// �ǵùر�
		mPrintWriter.close();
		String mResult = mWriter.toString();
		mStringBuffer.append(mResult);
		// �����ļ��������ļ���
		String mTime = mSimpleDateFormat.format(new Date());
		String mFileName = "CrashLog-" + mTime + ".log";
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			try {
				File mDirectory = new File(
						Environment.getExternalStorageDirectory()
								+ "/CrashInfos");
				Log.v(TAG, mDirectory.toString());
				if (!mDirectory.exists())
					mDirectory.mkdir();
				FileOutputStream mFileOutputStream = new FileOutputStream(
						mDirectory + "/" + mFileName);
				mFileOutputStream.write(mStringBuffer.toString().getBytes());
				mFileOutputStream.close();
				uploadBugFile(mDirectory + "/" + mFileName);
				return mFileName;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * �ϴ�bug�ļ��������
	 * 
	 * @param path
	 */
	public void uploadBugFile(String path) {
		System.out.println("�ύBug�ļ�·��:" + path);
		HttpUtils http = new HttpUtils();
		RequestParams params = new RequestParams();
		// params.addBodyParameter("",);
		params.addBodyParameter("systemid", "android");
		params.addBodyParameter("psw", "34kj3365h");
		params.addBodyParameter("bugFile", new File(path));
		http.send(HttpMethod.POST,API.BUG_UPLOAD, params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						// Toast.makeText(HttpTest.this,"�ϴ�ʧ��",
						// Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						// Toast.makeText(HttpTest.this,"�ϴ��ɹ�",
						// Toast.LENGTH_SHORT).show();
					}
				});
	}
}
