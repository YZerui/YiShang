package com.yishang.A.global.writting;

import com.format.utils.DataValidate;
import com.yishang.A.global.Enum.Enum_Gender;
import com.yishang.Z.utils.FormatUtils;

/**
 * ������Ϣ����ʾ�İ�
 * @author MM_Zerui
 *
 */
public class W_UserIfo {
	public static String name(String name){
		if(DataValidate.checkDataValid(name)){
			return name;
		}
		return "����������Ϣ";
	}
	public static String gender(String name){
		if(name.equals(Enum_Gender.MAN.value())){
			return "��";
		}else if (name.equals(Enum_Gender.WOMEN.value())) {
			return "Ů";
		}
		return "δ֪�Ա�";
	}
	public static String email(String email){
		if(DataValidate.checkDataValid(email)){
			return email;
		}
		return "����������Ϣ";
	}
	public static String birthday(String birthday){
		if(DataValidate.checkDataValid(birthday)){
			return FormatUtils.getDateTime_BIRTHDAY(birthday);
		}
		return "����������Ϣ";
	}
	public static String label(String label){
		if(DataValidate.checkDataValid(label)){
			return label;
		}
		return "���޸��˱�ǩ";
	}
	public static String phone(String phone){
		if(DataValidate.checkDataValid(phone)){
			return phone;
		}
		return "�����ֻ���ϵ��ʽ";
	}
	public static String rank(String rank){
		if(DataValidate.checkDataValid(rank)){
			return rank;
		}
		return "���޸���ͷ��";
	}
	public static String intro(String intro){
		if(DataValidate.checkDataValid(intro)){
			return intro;
		}
		return "���޸��˼��";
	}
	public static String address(String addr){
		if(DataValidate.checkDataValid(addr)){
			return addr;
		}
		return "���޵�ַ��Ϣ";
	}
	public static String fax(String fax){
		if(DataValidate.checkDataValid(fax)){
			return fax;
		}
		return "���޴������";
	}
}
