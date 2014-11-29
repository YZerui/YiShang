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
 * �ֻ���ϵ����ش�����
 */
public class ConstactUtil {
	/**
	 * ��ȡ����ͨ��¼����
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
				// �����ϵ�˵�ID��
				String contactId = c.getString(c
						.getColumnIndex(ContactsContract.Contacts._ID));
				// �����ϵ������
				String name = c
						.getString(c
								.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
				// �鿴����ϵ���ж��ٸ��绰���롣���û���ⷵ��ֵΪ0
				int phoneCount = c
						.getInt(c
								.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
				String number=null;
				if (phoneCount > 0) {
					// �����ϵ�˵ĵ绰����
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
	 * ��ȡ����õ��ֻ�������Ϣ
	 */
	public static ArrayList<T_Contacts> getSortContactData() {
		//��ȡͨѶ¼��Ϣ
		Map<String,String> map=getAllCallRecords(AppContextApplication.getInstance());
		List<String> constact = new ArrayList<String>();
		//��ȡ��ϵ��Ϣ�е�����
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
			//��������
			contactSortBean.setName(date[i]);
			//�������
			contactSortBean.setPhone(map.get(date[i]));
			// ����ת����ƴ��
			String pinyin = characterParser.getSelling(date[i]);
			String sortString = pinyin.substring(0, 1).toUpperCase();

			// ������ʽ���ж�����ĸ�Ƿ���Ӣ����ĸ
			if (sortString.matches("[A-Z]")) {
				contactSortBean.setFirstLetter(sortString.toUpperCase());
			} else {
				contactSortBean.setFirstLetter("#");				
			}
			
			mSortList.add(contactSortBean);
		}
		//����ƴ����������
		PinyinComparator pinyinComparator=new PinyinComparator();
		Collections.sort(mSortList,pinyinComparator);
		
		return mSortList;

	}
}
