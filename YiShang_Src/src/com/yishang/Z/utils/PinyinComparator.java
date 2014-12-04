package com.yishang.Z.utils;

import java.util.Comparator;

import com.yishang.A.global.baseClass.BaseEntity;
import com.yishang.C.dao.daoModel.T_Contacts;
/**
 * 
 * @author xiaanming
 *
 */
public class PinyinComparator implements Comparator<BaseEntity> {

	public int compare(BaseEntity o1, BaseEntity o2) {
		if (o1.getFirstLetter().equals("@")
				|| o2.getFirstLetter().equals("#")) {
			return -1;
		} else if (o1.getFirstLetter().equals("#")
				|| o2.getFirstLetter().equals("@")) {
			return 1;
		} else {
			return o1.getFirstLetter().compareTo(o2.getFirstLetter());
		}
	}

}
