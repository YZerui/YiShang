package com.format.utils;

import java.util.ArrayList;
import java.util.List;

import com.format.callBack.callBack_dataVaildate;



/**
 * 检验数据的有效性
 * @author MM_Zerui
 *
 */
public class DataValidate {
	/**
	 * 检验手机号码的正确性
	 * 
	 * @param phone
	 * @param callBack
	 * @category 手机号码为11位
	 * @category 手机号码全为数字
	 */
	public static void checkPhone(String phone,callBack_dataVaildate callBack){
		if(phone==null||phone.isEmpty()){
			callBack.call_lengthNull();
			return;
		}
		if(phone.matches("\\d{11}")){
			callBack.call_valid();
			return;
		}
		if(phone.length()<11){
			callBack.call_lengthShort();
			return;
		}
		if(phone.length()>11){
			callBack.call_lengthLong();
			return;
		}
		callBack.call_lengthInvalid();
	}
	
	
	/**
	 * 检验密码的有效性
	 * @param password
	 * @param callBack
	 * @category 数据长度保证在3位以上
	 * @category 数据必须为数字或英文字符的组合
	 */
	public static void checkPassword(String password,callBack_dataVaildate callBack){
		if(password==null||password.isEmpty()){
			callBack.call_lengthNull();
			return;
		}
		if(password.length()<4){
			callBack.call_lengthShort();
			return;
		}
		if(password.length()>20){
			callBack.call_lengthLong();
			return;
		}
		if(password.matches("([a-z]|\\d)*")){
			callBack.call_valid();
			return;
		}
		callBack.call_lengthInvalid();
	}
	
	public static void checkPhoneAutho(String autho,callBack_dataVaildate callBack){
		if(autho==null||autho.isEmpty()){
			callBack.call_lengthNull();
			return;
		}
		if(autho.length()<4){
			callBack.call_lengthShort();
			return;
		}
		if(autho.length()>6){
			callBack.call_lengthLong();
			return;
		}
		if(autho.matches("\\d+")){
			callBack.call_valid();
			return;
		}
		callBack.call_lengthInvalid();
		
	}
	
	/**
	 * 检查数据是否为空是否有效
	 * @param obj
	 */
	public static boolean checkDataValid(Object obj){
		
		if(obj instanceof ArrayList<?>){
			ArrayList<?> list=(ArrayList<?>)obj;
			if(list!=null&&list.size()>0){
				return true;
			}
			return false;
		}
		if(obj instanceof String){
			String str=(String)obj;
			if(str!=null&&str.length()>0){
				return true;
			}
			return false;
		}
		if(obj instanceof List<?>){
			List<?> list=(List<?>)obj;
			if(list!=null&&list.size()>0){
				return true;
			}
			return false;
		}
		if(obj==null){
			return false;
		}else {
			return true;
		}
	}
}
