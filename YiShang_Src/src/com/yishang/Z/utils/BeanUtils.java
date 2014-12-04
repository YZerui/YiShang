package com.yishang.Z.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ��һ��bean�����ݿ�������һbean��ͬ�����ֶ�
 * @author MM_Zerui
 *
 */
public class BeanUtils {

	@SuppressWarnings("unchecked")
	public static void copyProperties(Object target, Object source)
			throws Exception {
		/*
		 * �ֱ���Դ�����Ŀ������Class���Ͷ���,Class����������������Ƶ�Դͷ����꣡
		 * Class������������ص�ʱ�����,���������������ԣ�����������������Ϣ
		 */
		Class sourceClz = source.getClass();
		Class targetClz = target.getClass();
		// �õ�Class�����������������������(����˽������)
		Field[] fields = sourceClz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			String fieldName = fields[i].getName();
			Field targetField = null;
			try {
				// �õ�targetClz�����������������ΪfieldName�����ԣ������ھͽ����´�ѭ��
				targetField = targetClz.getDeclaredField(fieldName);
			} catch (SecurityException e) {
				e.printStackTrace();
				break;
			} catch (NoSuchFieldException e) {
				continue;
			}
			// �ж�sourceClz�ֶ����ͺ�targetClzͬ���ֶ������Ƿ���ͬ
			if (fields[i].getType() == targetField.getType()) {
				// ���������ֵõ���Ӧget��set����������
				String getMethodName = "get"
						+ fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				String setMethodName = "set"
						+ fieldName.substring(0, 1).toUpperCase()
						+ fieldName.substring(1);
				// �ɷ��������ֵõ�get��set������Method����
				Method getMethod;
				try {
					getMethod = sourceClz.getDeclaredMethod(getMethodName,
							new Class[] {});
					Method setMethod = targetClz.getDeclaredMethod(
							setMethodName, fields[i].getType());
					// ����source�����getMethod����
					Object result = getMethod.invoke(source, new Object[] {});
					// ����target�����setMethod����
					setMethod.invoke(target, result);
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				throw new Exception("ͬ���������Ͳ�ƥ�䣡");
			}
		}
	}

}
