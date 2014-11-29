package com.http.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.lidroid.xutils.http.RequestParams;



public class myHttpUtils {
	public static Map<String, String> convertBeanToMap(Object bean)
			throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = bean.getClass().getDeclaredFields();
		HashMap<String, String> data = new HashMap<String, String>();
		for (Field field : fields) {
			field.setAccessible(true);
			data.put(field.getName(), (String) field.get(bean));
		}
		return data;
	}
	
	public static RequestParams convertMapToParams(Object bean){
		HashMap<String, String> map;
		try {
			map = (HashMap<String, String>) convertBeanToMap(bean);
			RequestParams params=new RequestParams();
			if (map!= null) {
				Set<String> keys = map.keySet();
				for (Iterator<String> i = keys.iterator(); i.hasNext();) {
					String key = (String) i.next();
					params.addBodyParameter(key, map.get(key));
				}
			}
			return params;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
