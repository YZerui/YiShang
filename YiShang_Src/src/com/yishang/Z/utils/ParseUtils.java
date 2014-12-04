package com.yishang.Z.utils;

import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.exception.utils.P;

/**
 * �������ݵĹ�����
 * 
 * @author MM_Zerui
 * 
 */
public class ParseUtils {
	/**
	 * @param array
	 * @return �����������ȷ��
	 */
	public static boolean parseJsonState(JSONObject object) {
		try {
			// JSONObject object=(JSONObject) array.get(1);
			String state = object.getString("success");
			if (state != null && state.equals("true")) {
				return true;
			}
			return false;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @param object
	 * @return Object
	 */
	public static Object parseResultData(Object object) {
		if (object != null) {
			try {
				JSONObject jsonObject = new JSONObject();
				if (object instanceof String) {
					String jsonStr = (String) object;
					jsonObject = new JSONObject(jsonStr);
				} else if (object instanceof JSONObject) {
					jsonObject = (JSONObject) object;
				} else {
					return null;
				}
				return jsonObject.get("data");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return null;
	}

	/**
	 * @param object
	 * @return ����JsonObject
	 */
	public static <T> T parseResultJson(Object object, Class<T> beanClass) {
		if (object != null) {
			try {
				JSONObject jsonObject = new JSONObject();
				if (object instanceof String) {
					String jsonStr = (String) object;
					jsonObject = new JSONObject(jsonStr);
				} else if (object instanceof JSONObject) {
					jsonObject = (JSONObject) object;
				} else {
					return null;
				}
				JSONObject jsonData = jsonObject.getJSONObject("data");
				ObjectMapper objectMapper = new ObjectMapper();
				if (jsonData != null) {
					try {
						// objectMapper.configure(Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY,
						// true);
						T bean = (T) objectMapper.readValue(
								jsonData.toString(), beanClass);
					
						return bean;
					} catch (JsonParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JsonMappingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						P.v("����json�������Ͳ�ƥ��:"+e.getMessage());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * @param object
	 * @return ����JsonArray����Ϣ
	 */
	public static <T> ArrayList<T> parseLocalJsonArray(Object object,
			Class<T> beanCalss) {
		if (object != null) {
			try {
				JSONObject jsonObject = new JSONObject();
				if (object instanceof String) {
					String jsonStr = (String) object;
					jsonObject = new JSONObject(jsonStr);
				} else if (object instanceof JSONObject) {
					jsonObject = (JSONObject) object;
				} else {
					return null;
				}

				// String arrayStr=object.get("data").toString();
				JSONArray jsonArray = jsonObject.getJSONArray("data");

				if (jsonArray != null) {

					ObjectMapper objectMapper = new ObjectMapper();
					try {
						ArrayList<T> listBean = new ArrayList<T>();
						for (int i = 0; i < jsonArray.length(); i++) {
							JSONObject json = (JSONObject) jsonArray.get(i);
							T bean = (T) objectMapper.readValue(
									json.toString(), beanCalss);
							// listBean.getLocalUserItemBean().add(bean);
							listBean.add(bean);
						}
						return listBean;
					} catch (JsonParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("json��������");
					} catch (JsonMappingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
//						System.out.println("json�������Ͳ�ƥ��");
						P.v("����json���������Ͳ�ƥ��:"+e.getMessage());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("���������������");
					}

				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;

	}

}
