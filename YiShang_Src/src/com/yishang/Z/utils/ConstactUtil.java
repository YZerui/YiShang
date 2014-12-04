package com.yishang.Z.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yishang.A.global.application.AppContextApplication;
import com.yishang.C.dao.daoModel.T_Contacts;



import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

/**
 * @author MM_Zerui
 * 手机联系人相关处理工具
 */
public class ConstactUtil {
	/**
	 * 获取所有通信录数据
	 * 
	 * @return
	 */
	private static Map<String,String> getAllCallRecords(Context context) {
		Map<String,String> temp = new HashMap<String, String>();
		Cursor c = context.getContentResolver().query(
				ContactsContract.Contacts.CONTENT_URI,
				null,
				null,
				null,
				ContactsContract.Contacts.DISPLAY_NAME
						+ " COLLATE LOCALIZED ASC");
		if (c.moveToFirst()) {
			do {
				// 获得联系人的ID号
				String contactId = c.getString(c
						.getColumnIndex(ContactsContract.Contacts._ID));
				// 获得联系人姓名
				String name = c
						.getString(c
								.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
				// 查看该联系人有多少个电话号码。如果没有这返回值为0
				int phoneCount = c
						.getInt(c
								.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
				String number=null;
				if (phoneCount > 0) {
					// 获得联系人的电话号码
					Cursor phones = context.getContentResolver().query(
							ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
							null,
							ContactsContract.CommonDataKinds.Phone.CONTACT_ID
									+ " = " + contactId, null, null);
					if (phones.moveToFirst()) {
						number = phones
								.getString(phones
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
						}
					phones.close();
				}
				temp.put(name, number);
			} while (c.moveToNext());
		}
		c.close();
		return temp;
	}
	/**
	 * @param arrayList
	 * @param map
	 * @return
	 * 获取处理好的手机号码信息
	 */
	public static ArrayList<T_Contacts> getSortContactData() {
		//获取通讯录信息
		Map<String,String> map=getAllCallRecords(AppContextApplication.getInstance());
		List<String> constact = new ArrayList<String>();
		//提取联系信息中的名字
		for (Iterator<String> keys = map.keySet().iterator(); keys
				.hasNext();) {
			String key = keys.next();
			constact.add(key);
		}
		String[] date=new String[]{};
		date=constact.toArray(date);
		
		CharacterParser characterParser=CharacterParser.getInstance();
		ArrayList<T_Contacts> mSortList = new ArrayList<T_Contacts >();

		for (int i = 0; i < date.length; i++) {
			
			T_Contacts  contactSortBean = new T_Contacts();
			//存入姓名
			contactSortBean.setName(date[i]);
			//存入号码
			contactSortBean.setPhone(map.get(date[i]));
			// 汉字转换成拼音
			String pinyin = characterParser.getSelling(date[i]);
			String sortString = pinyin.substring(0, 1).toUpperCase();

			// 正则表达式，判断首字母是否是英文字母
			if (sortString.matches("[A-Z]")) {
				contactSortBean.setFirstLetter(sortString.toUpperCase());
			} else {
				contactSortBean.setFirstLetter("#");				
			}
			
			mSortList.add(contactSortBean);
		}
		//根据拼音进行排序
		PinyinComparator pinyinComparator=new PinyinComparator();
		Collections.sort(mSortList,pinyinComparator);
		
		return mSortList;

	}
}
