package com.format.utils;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.lidroid.xutils.http.RequestParams;

public class FormatUtils {
	public static int paseBool2Int(Boolean bool) {
		if (bool) {
			return 1;
		}
		return 0;
	}

	public static String paseBool2Str(Boolean bool) {
		if (bool) {
			return "1";
		}
		return "0";
	}

	public static String getDateTime() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
		return format.format(date);
	}
	/**
	 * 获得今天的日期（月——日）
	 * @return
	 */
	public static String getTodayDateTime_M_D(){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("MM-dd");
		return format.format(date);
	}
	/**
	 * @param timeValue
	 * @return 将时间戳转化为时间（月——日）
	 */
	public static String getDateTime_M_D(String timeValue) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
		String date = sdf.format(new Date(Long.valueOf(timeValue)));
		return date;
	}
	/**
	 * @param timeValue
	 * @return 将时间戳转化为时间（时——分）
	 */
	public static String getDateTime_H_M(String timeValue) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String date = sdf.format(new Date(Long.valueOf(timeValue)));
		return date;
	}
	/**
	 * @param timeValue
	 * @return 将时间戳转化为时间
	 */
	public static String getDateTime(String timeValue) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
		String date = sdf.format(new Date(Long.valueOf(timeValue)));
		return date;
	}
	/**
	 * @param timeValue
	 * @return 将时间戳转化为年份
	 */
	public static String getDateTime_YEAR(String timeValue) {
		if(timeValue==null||timeValue.equals("")){
			return "0";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String date = sdf.format(new Date(Long.valueOf(timeValue)));
		return date;
	}
	
	public static String getAge(String timeValue){
		if(timeValue==null||timeValue.equals("")){
			return "0";
		}
		int age=Integer.valueOf(getDateTime_YEAR(getCurrentDateValue()))-Integer.valueOf(getDateTime_YEAR(timeValue));
		return String.valueOf(age);
		
	}
	/**
	 * @param timeValue
	 * @return 将时间戳转化为生日
	 */
	public static String getDateTime_BIRTHDAY(String timeValue) {
		if(timeValue==null||timeValue.equals("")){
			return "0";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date(Long.valueOf(timeValue)));
		return date;
	}
	/**
	 * 将时间转化为时间戳（生日类型）
	 * 
	 * @param timeStr
	 * @return
	 */
	public static String getDateValueBirthday(String timeStr) {
		if(timeStr==null||timeStr.equals("")){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sdf.parse(timeStr);
			long value = date.getTime();
			return String.valueOf(value);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将时间转化为时间戳（目前时间）
	 * 
	 * @param timeStr
	 * @return
	 */
	public static String getCurrentDateValue() {
		Date date = new Date();
		long value = date.getTime();
		return String.valueOf(value);
	}

	/**
	 * 获得年份数组
	 * 
	 * @return
	 */
	public static String[] getYearArrays() {
		String[] array = new String[120];
		for (int i = 1900; i < 2020; i++) {
			array[i - 1900] = String.valueOf(i) + "年";
		}
		return array;
	}
	
	public static int getYearItem(int year){
		return (year-1900)<0?0:(year-1900);
	}
	public static int getMonthItem(int month){
		return (month-1)<0?0:(month-1);
	}
	public static int getDayItem(int day){
		return (day-1)<0?0:(day-1);
	}
	/**
	 * 获得月份数组
	 * 
	 * @return
	 */
	public static String[] getMonthArrays() {
		String[] array = new String[12];
		for (int i = 1; i <= 12; i++) {
			if (i < 10) {
				array[i - 1] = "0" + String.valueOf(i) + "月";
			} else {
				array[i - 1] = String.valueOf(i) + "月";
			}

		}
		return array;
	}

	/**
	 * 获得日数组
	 * 
	 * @return
	 */
	public static String[] getDayArrays() {
		String[] array = new String[31];
		for (int i = 1; i <= 31; i++) {
			if (i < 10) {
				array[i - 1] = "0" + String.valueOf(i) + "日";
			} else {
				array[i - 1] = String.valueOf(i) + "日";
			}

		}
		return array;
	}

	public static String normalFormat(String value,String defaultNote){
		if(value==null||value.isEmpty()){
			return defaultNote;
		}
		return value;
	}
	
	
	
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
	
	public static RequestParams convertBeanToParams(Object bean){
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
