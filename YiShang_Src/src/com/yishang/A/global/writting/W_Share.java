package com.yishang.A.global.writting;

import com.yishang.C.dao.daoImpl.Dao_Self;

public class W_Share {
	/**
	 * 社会化分享
	 * @param url
	 * @return
	 */
	public static String shareSocial(String url){
		return "这个很不错，向大家推荐："+url+"，该链接由“易商”移动营销App生成，绝对安全，请放心查看。";
	}
	/**
	 * 邮件分享
	 * @param name
	 * @param url
	 * @return
	 */
	public static String shareEmail(String name,String url){
		return name+"您好！"+
		"我是"+Dao_Self.getInstance().getUser_name()+
			"，我认为这份资料对您会有帮助，请点击以下链接查看。该链接由“易商”移动营销App生成，绝对安全，请放心查看。"+url;
	}
	/**
	 * 短信分享
	 * @param name
	 * @return
	 */
	public static String shareMsg(String name,String url){
		return name+"您好，"+"我是"+Dao_Self.getInstance().getUser_name()+
					"！我认为这份资料对您会有帮助，请点击以下链接查看。该链接由“易商”移动营销App生成，绝对安全，请放心查看。"+url;
	}
}
