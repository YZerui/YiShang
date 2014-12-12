package com.yishang.C.dao.utils;

import com.yishang.A.global.Enum.db.Enum_RelaType;

/**
 * 判断人脉关系的工具类
 * 
 * @note 根据5位2进制转化为特定数字标识
 * @author MM_Zerui
 * 
 */
public class Utils_DBRNote {
	public static int trueNum(String param){
		if(param.indexOf("1")==-1){
			return Integer.valueOf(param);
		}
		return Integer.valueOf(param.substring(param.indexOf("1"),param.length()));
	}
	public static int parse(int param) {
		return handle(param);
	}
	
	public static int parse(String param){
		if(param.length()<5){
			return Enum_RelaType.UNKNOW.value();
		}
		String value=param.substring(param.indexOf("1"),param.length());
		return handle(Integer.parseInt(value));
	}
	public static int handle(int param){
		if(param==0){
			return Enum_RelaType.SYSTEM.value();
		}
		if(param==11111){
			return Enum_RelaType.SELF.value();
		}
		else if (param % 10 == 1) { //****1
			return Enum_RelaType.BLACKLIST.value();
		} else if (param / 1000 == 11) { // 11000
			return Enum_RelaType.CLIENT_SUPPLIER.value();
		} else if (param / 10000 == 1) {  //10000
			return Enum_RelaType.CLIENT.value();
		} else if (param / 1000 == 1) {   //01000
			return Enum_RelaType.SUPPLIER.value(); 
		} else if (param / 100 == 1) {  //00100
			return Enum_RelaType.CONTACTS.value();
		} else if (param / 10 == 1) {   //00010
			return Enum_RelaType.NEWFRIEND.value();
		}else {
			return Enum_RelaType.UNKNOW.value();
		}
	}
}
