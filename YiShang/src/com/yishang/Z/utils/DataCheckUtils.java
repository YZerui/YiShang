package com.yishang.Z.utils;
import android.provider.ContactsContract.CommonDataKinds.Phone;

/**
 * ����У��Ĺ���
 * @author MM_Zerui
 * 
 *
 */
public class DataCheckUtils {
	/**
	 * У���ֻ��������ȷ��
	 * @param phone
	 * @condition 11λ����
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
	 * @condition 6-15λ���ֻ���ĸ�����
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
