package com.yishang.C.dao.utils;

public class Utils_DB {
	public static String cOrderBy(Boolean desc,String... params){
		String value=new String();
		for(String str:params){
			if(desc){
				value+=str+" DESC,";
			}else {
				value+=str+" ASC,";
			}
		}
		return value.substring(0, value.lastIndexOf(" "));
	}
}
