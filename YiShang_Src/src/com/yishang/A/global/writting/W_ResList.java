package com.yishang.A.global.writting;

import com.format.utils.DataValidate;

/**
 * ��Դ�б�
 * @author MM_Zerui
 *
 */
public class W_ResList {
	public static String getComName(String info){
		if(!DataValidate.checkDataValid(info)){
			return "δ֪��ҵ";
		}
		return info;
	}
	public static String getResName(String info){
		if(!DataValidate.checkDataValid(info)){
			return "δ֪�ĵ�";
		}
		return "��"+info+"��";
	}
	public static String getSenderName(String info){
		if(!DataValidate.checkDataValid(info)){
			return "δ֪ת��";
		}
		return "���� "+info;
	}
}
