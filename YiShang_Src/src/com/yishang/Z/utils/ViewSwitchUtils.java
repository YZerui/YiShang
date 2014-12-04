package com.yishang.Z.utils;

import com.ruifeng.yishang.R;
import com.yishang.A.global.Enum.Enum_ReceiverAction;
import com.yishang.D.service.sync.SYNCLocalContactsService;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * ��ͼ�л��Ĺ���
 * 
 * @author MM_Zerui
 * 
 */
public class ViewSwitchUtils {
	/**
	 * anim�еĲ���ID
	 */
	public static int ANIM_IN = 0;
	/**
	 * anim�еĲ���ID
	 */
	public static int ANIM_OUT = 0;

	/**
	 * ͨ������xml�ļ���id������Ҫʹ�õĶ��������ļ�
	 * 
	 * @param layoutIn
	 * @param layoutOut
	 */
	public static void setLayout(int layoutIn, int layoutOut) {
		ANIM_IN = layoutIn;
		ANIM_OUT = layoutOut;
	}

	/**
	 * ����idΪ0
	 */
	public static void clear() {
		ANIM_IN = 0;
		ANIM_OUT = 0;
	}

	public static void nor_toIntent(Context context, Class toClass,
			String... params) {
		Intent intent = new Intent(context, toClass);
		if (params != null) {
			int i = 0;
			String note = "";
			for (String param : params) {
				note = "DATA" + i;
				intent.putExtra(note, param);
				i++;
			}
		}
		context.startActivity(intent);
	}


	
	/**
	 * @param context
	 * @param toClass
	 *            ��TabBar��������������ҳ��
	 */
	public static void tab_in2TopIntent(Context context, Class toClass,
			String... params) {
		Intent intent = new Intent(context, toClass);
		if (params != null) {
			int i = 0;
			String note = "";
			for (String param : params) {
				note = "DATA" + i;
				intent.putExtra(note, param);
				i++;
			}
		}
		context.startActivity(intent);
		ViewSwitchUtils.setLayout(R.anim.in_bottom_to_top_page,
				R.anim.out_top_to_bottom_page);
	}

	/**
	 * @param context
	 * @param toClass
	 *            ��TabBar��������������ҳ��
	 */
	public static void tab_in2TopIntent_result(Activity context, Class toClass,
			int requestCode, String... params) {
		Intent intent = new Intent(context, toClass);
		if (params != null) {
			int i = 0;
			String note = "";
			for (String param : params) {
				note = "DATA" + i;
				intent.putExtra(note, param);
				i++;
			}
		}
		context.startActivityForResult(intent, requestCode);
		ViewSwitchUtils.setLayout(R.anim.in_bottom_to_top_page,
				R.anim.out_top_to_bottom_page);
	}
	/**
	 * @param context
	 * @param toClass
	 *            ��TabBar��������������ҳ��
	 */
	public static void tab_in2LeftIntent_result(Activity context, Class toClass,
			int requestCode, String... params) {
		Intent intent = new Intent(context, toClass);
		if (params != null) {
			int i = 0;
			String note = "";
			for (String param : params) {
				note = "DATA" + i;
				intent.putExtra(note, param);
				i++;
			}
		}
		context.startActivityForResult(intent, requestCode);
		ViewSwitchUtils.setLayout(R.anim.in_right_to_left_page,
				R.anim.out_left_to_right_page);
	}

	/**
	 * @param context
	 * @param toClass
	 *            ��TabBar��������������ҳ��
	 */
	public static void tab_in2TopIntent_int(Context context, Class toClass,
			int... params) {
		Intent intent = new Intent(context, toClass);
		if (params != null) {
			int i = 0;
			String note = "";
			for (int param : params) {
				note = "DATA" + i;
				intent.putExtra(note, param);
				i++;
			}
		}
		context.startActivity(intent);
		ViewSwitchUtils.setLayout(R.anim.in_bottom_to_top_page,
				R.anim.out_top_to_bottom_page);
	}

	/**
	 * @param context
	 * @param toClass
	 *            ��TabBar��������������ҳ��
	 */
	public static void tab_in2LeftIntent(Context context, Class toClass,
			String... params) {
		Intent intent = new Intent(context, toClass);
		if (params != null) {
			int i = 0;
			String note = "";
			for (String param : params) {
				note = "DATA" + i;
				intent.putExtra(note, param);
				i++;
			}
		}
		context.startActivity(intent);
		ViewSwitchUtils.setLayout(R.anim.in_right_to_left_page,
				R.anim.out_right_to_left_page);
	}

	/**
	 * @param context
	 * @param toClass
	 *            ��TabBar��������������ҳ��
	 */
	public static void tab_in2LeftIntent_int(Context context, Class toClass,
			int... params) {
		Intent intent = new Intent(context, toClass);
		if (params != null) {
			int i = 0;
			String note = "";
			for (int param : params) {
				note = "DATA" + i;
				intent.putExtra(note, param);
				i++;
			}
		}
		context.startActivity(intent);
		ViewSwitchUtils.setLayout(R.anim.in_right_to_left_page,
				R.anim.out_left_to_right_page);
	}

	/**
	 * @param context
	 * @param toClass
	 *            ������������ҳ��
	 */
	public static void out2LeftIntent(Context context, Class toClass) {
		Intent intent = new Intent(context, toClass);
		context.startActivity(intent);
		((Activity) context).overridePendingTransition(0,
				R.anim.out_right_to_left_page);
	}

	/**
	 * @param context
	 * @param toClass
	 *            ������������ҳ��
	 */
	public static void out2RightIntent(Context context, Class toClass) {
		Intent intent = new Intent(context, toClass);
		context.startActivity(intent);
		((Activity) context).overridePendingTransition(0,
				R.anim.out_left_to_right_page);
	}

	/**
	 * @param context
	 * @param toClass
	 *            ������������ҳ��
	 */
	public static void in2TopIntent(Context context, Class toClass,
			String... params) {
		Intent intent = new Intent(context, toClass);
		if (params != null) {
			int i = 0;
			String note = "";
			for (String param : params) {
				note = "DATA" + i;
				intent.putExtra(note, param);
				i++;
			}
		}
		context.startActivity(intent);
		((Activity) context).overridePendingTransition(
				R.anim.in_bottom_to_top_page, 0);
	}
	/**
	 * @param context
	 * @param toClass
	 *            ������������ҳ��
	 */
	public static void in2TopIntent_result(Activity context, Class toClass,int requestCode,
			String... params) {
		Intent intent = new Intent(context, toClass);
		if (params != null) {
			int i = 0;
			String note = "";
			for (String param : params) {
				note = "DATA" + i;
				intent.putExtra(note, param);
				i++;
			}
		}
		context.startActivityForResult(intent, requestCode);
		context.overridePendingTransition(
				R.anim.in_bottom_to_top_page, 0);
	}

	/**
	 * @param context
	 * @param toClass
	 *            ������������ҳ��
	 */
	public static void in2TopIntent_int(Context context, Class toClass,
			int... params) {
		Intent intent = new Intent(context, toClass);
		if (params != null) {
			int i = 0;
			String note = "";
			for (int param : params) {
				note = "DATA" + i;
				intent.putExtra(note, param);
				i++;
			}
		}
		context.startActivity(intent);
		((Activity) context).overridePendingTransition(
				R.anim.in_bottom_to_top_page, 0);
	}

	/**
	 * @param context
	 * @param toClass
	 *            �������ʧ
	 */
	public static void in2BigIntent(Context context, Class toClass,
			String... params) {
		Intent intent = new Intent(context, toClass);
		if (params != null) {
			int i = 0;
			String note = "";
			for (String param : params) {
				note = "DATA" + i;
				intent.putExtra(note, param);
				i++;
			}
		}
		context.startActivity(intent);
		((Activity) context).overridePendingTransition(
				R.anim.in_little_to_normal_page, R.anim.out_normal_to_big_page);
	}

	/**
	 * @param context
	 * @param toClass
	 *            �������ʧ
	 */
	public static void in2BigIntent_int(Context context, Class toClass,
			int... params) {
		Intent intent = new Intent(context, toClass);
		if (params != null) {
			int i = 0;
			String note = "";
			for (int param : params) {
				note = "DATA" + i;
				intent.putExtra(note, param);
				i++;
			}
		}
		context.startActivity(intent);
		((Activity) context).overridePendingTransition(
				R.anim.in_little_to_normal_page, R.anim.out_normal_to_big_page);
	}

	/**
	 * @param context
	 * @param toClass
	 *            ������������ҳ��
	 */
	public static void out2BottomIntent(Context context, Class toClass,
			String... params) {
		Intent intent = new Intent(context, toClass);
		if (params != null) {
			int i = 0;
			String note = "";
			for (String param : params) {
				note = "DATA" + i;
				intent.putExtra(note, param);
				i++;
			}
		}
		context.startActivity(intent);
		((Activity) context).overridePendingTransition(0,
				R.anim.out_top_to_bottom_page);
	}

	/**
	 * @param context
	 * @param toClass
	 *            ������������ҳ��
	 */
	public static void in2RightIntent(Context context, Class toClass,
			String... params) {
		Intent intent = new Intent(context, toClass);
		if (params != null) {
			int i = 0;
			String note = "";
			for (String param : params) {
				note = "DATA" + i;
				intent.putExtra(note, param);
				i++;
			}
		}
		context.startActivity(intent);
		((Activity) context).overridePendingTransition(
				R.anim.out_left_to_right_page, 0);
	}

	/**
	 * @param context
	 * @param toClass
	 *            ������������ҳ��
	 */
	public static void in2RightIntent_int(Context context, Class toClass,
			int... params) {
		Intent intent = new Intent(context, toClass);
		if (params != null) {
			int i = 0;
			String note = "";
			for (int param : params) {
				note = "DATA" + i;
				intent.putExtra(note, param);
				i++;
			}
		}
		context.startActivity(intent);
		((Activity) context).overridePendingTransition(
				R.anim.out_left_to_right_page, 0);
	}

	/**
	 * @param context
	 * @param toClass
	 *            ������������ҳ��
	 */
	public static void in2NormalIntent(Context context, Class toClass,
			String... params) {
		Intent intent = new Intent(context, toClass);
		if (params != null) {
			int i = 0;
			String note = "";
			for (String param : params) {
				note = "DATA" + i;
				intent.putExtra(note, param);
				i++;
			}
		}
		context.startActivity(intent);
	}

	/**
	 * @param context
	 * @param toClass
	 *            ������������ҳ��
	 */
	public static void in2NormalIntent_int(Context context, Class toClass,
			int... params) {
		Intent intent = new Intent(context, toClass);
		if (params != null) {
			int i = 0;
			String note = "";
			for (int param : params) {
				note = "DATA" + i;
				intent.putExtra(note, param);
				i++;
			}
		}
		context.startActivity(intent);
	}

	/**
	 * @param context
	 * @param toClass
	 *            ������������ҳ��
	 */
	public static void in2LeftIntent(Context context, Class toClass,
			String... params) {
		Intent intent = new Intent(context, toClass);
		if (params != null) {
			int i = 0;
			String note = "";
			for (String param : params) {
				note = "DATA" + i;
				intent.putExtra(note, param);
				i++;
			}
		}
		context.startActivity(intent);
		((Activity) context).overridePendingTransition(
				R.anim.in_right_to_left_page, R.anim.out_left_to_right_page);
	}

	/**
	 * @param context
	 * @param toClass
	 *            ������������ҳ��
	 */
	public static void in2LeftIntent_int(Context context, Class toClass,
			int... params) {
		Intent intent = new Intent(context, toClass);
		if (params != null) {
			int i = 0;
			String note = "";
			for (int param : params) {
				note = "DATA" + i;
				intent.putExtra(note, param);
				i++;
			}
		}
		context.startActivity(intent);
		((Activity) context).overridePendingTransition(
				R.anim.in_right_to_left_page, R.anim.out_left_to_right_page);
	}

	/**
	 * @param context
	 * @param toClass
	 *            ���������ɴ�С����ҳ��
	 */
	public static void in2SmallIntent(Context context, Class toClass,
			String... params) {
		Intent intent = new Intent(context, toClass);
		if (params != null) {
			int i = 0;
			String note = "";
			for (String param : params) {
				note = "DATA" + i;
				intent.putExtra(note, param);
				i++;
			}
		}
		context.startActivity(intent);
		((Activity) context).overridePendingTransition(
				R.anim.in_big_to_small_page, 0);
	}

	/**
	 * @param context
	 * @param toClass
	 *            ���������ɴ�С����ҳ��
	 */
	public static void in2SmallIntent_int(Context context, Class toClass,
			int... params) {
		Intent intent = new Intent(context, toClass);
		if (params != null) {
			int i = 0;
			String note = "";
			for (int param : params) {
				note = "DATA" + i;
				intent.putExtra(note, param);
				i++;
			}
		}
		context.startActivity(intent);
		((Activity) context).overridePendingTransition(
				R.anim.in_big_to_small_page, 0);
	}

	/**
	 * �˳�ҳ�棬�����˳�
	 */
	public static void finishOut2Right(Context context) {
		((Activity) context).overridePendingTransition(
				R.anim.in_left_to_right_page, R.anim.out_left_to_right_page);
	}

	/**
	 * �˳�ҳ�棬�����˳�
	 */
	public static void finishOut2Bottom(Context context) {

		((Activity) context).overridePendingTransition(0,
				R.anim.out_top_to_bottom_page);
	}

	/**
	 * ����ĳ������
	 * 
	 * @param context
	 * @param toClass
	 */
	public static void startService(Context context, Class toClass) {
		Intent intent = new Intent(context, toClass);
		context.startService(intent);
	}
}
