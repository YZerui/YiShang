package com.device;

import java.util.ArrayList;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.os.RemoteException;


public class PHONE {
	/**
	 * 添加一个电话号码到本地
	 * @param context
	 * @param phone
	 * @param name
	 * @return
	 */
	public static boolean addContact2Local(Context context,String phone,String name){
		 Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
	        ContentResolver resolver = context.getContentResolver();
	        ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();
	        ContentProviderOperation op1 = ContentProviderOperation.newInsert(uri)
	            .withValue("account_name", null)
	            .build();
	        operations.add(op1);
	         
	        uri = Uri.parse("content://com.android.contacts/data");
	        //添加姓名
	        ContentProviderOperation op2 = ContentProviderOperation.newInsert(uri)
	            .withValueBackReference("raw_contact_id", 0)
	            .withValue("mimetype", "vnd.android.cursor.item/name")
	            .withValue("data2", name)
	            .build();
	        operations.add(op2);
	        //添加电话号码
	        ContentProviderOperation op3 = ContentProviderOperation.newInsert(uri)
	            .withValueBackReference("raw_contact_id", 0)
	            .withValue("mimetype", "vnd.android.cursor.item/phone_v2")
	            .withValue("data1", phone)
	            .withValue("data2", "2")
	            .build();
	        operations.add(op3);
	         
	        try {
				resolver.applyBatch("com.android.contacts", operations);
				return true;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OperationApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return false;
	}
}
