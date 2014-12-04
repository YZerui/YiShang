package com.yishang.Z.utils;
import android.provider.ContactsContract.CommonDataKinds.Phone;

/**
 * 数据校验的工具
 * @author MM_Zerui
 * 
 *
 */
public class DataCheckUtils {
	/**
	 * 校验手机号码的正确性
	 * @param phone
	 * @condition 11位数字
	 * @return
	 */
	public static boolean checkPhone(String phone){
		if(phone==null||phone.isEmpty()){
			return false;
		}
		if(phone.matches("\\d{11}")){
			return true;
		}
		return false;
	}
	
	/**
	 * @param password
	 * @condition 6-15位数字或字母的组合
	 * @return
	 */
	public static boolean checkPassword(String password){
		if(password==null||password.isEmpty()){
			return false;
		}
		if(password.matches("([a-z]|\\d){6,15}")){
			return true;	
		}
		return false;
	}
}
