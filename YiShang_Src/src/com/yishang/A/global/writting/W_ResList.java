package com.yishang.A.global.writting;

import com.format.utils.DataValidate;

/**
 * 资源列表
 * @author MM_Zerui
 *
 */
public class W_ResList {
	public static String getComName(String info){
		if(!DataValidate.checkDataValid(info)){
			return "未知企业";
		}
		return info;
	}
	public static String getResName(String info){
		if(!DataValidate.checkDataValid(info)){
			return "未知文档";
		}
		return "《"+info+"》";
	}
	public static String getSenderName(String info){
		if(!DataValidate.checkDataValid(info)){
			return "未知转发";
		}
		return "来自 "+info;
	}
}
