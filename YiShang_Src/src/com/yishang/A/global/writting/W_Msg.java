package com.yishang.A.global.writting;

import com.format.utils.DataValidate;
import com.yishang.A.global.Enum.push.Enum_PushType;
import com.yishang.C.dao.daoModel.T_MsgSeq;

/**
 * 商机模块的文案格式
 * 
 * @author MM_Zerui
 * 
 */
public class W_Msg {
	/**
	 * 防止非空
	 * 
	 * @param str
	 * @return
	 */
	public static String Y(String str) {
		if (DataValidate.checkDataValid(str)) {
			return str;
		}
		return new String();
	}

	/**
	 * 消息首页的文本格式
	 * @param name 为企业名或用户名
	 * @param tEnum 消息类型
	 * @param success 有关通知的属性
	 * @return
	 */
	public static String getNote(boolean success,Enum_PushType tEnum) {
		String note="";
		switch (tEnum) {
		// 系统通知
		case SYS_INFORM:
			note="易商小秘书";
			break;
		// 企业奖赏
		case COM_AWARD:
			note="颁发积分";
			break;
		// 企业报备消息
		case COM_BAOBEI:
			if(success){
				note="报备成功";
			}else {
				note="报备失败";
			}
			
			break;
		// 企业关联审核
		case COM_CHECK:
//			if(success){
//				note="关联成功";
//			}else {
//				note="关联失败";
//			}
			note="关联通知";
			break;
		// 企业通知
		case COM_INFORM:
			note="企业通知";
			break;
		// 资源感兴趣
		case RES_INTEREST:
			note="感兴趣";
			break;
		// 收到资源文档
		case RES_RECEV:
			note="发来文档";
			break;
		// 未知消息
		case DEFAULT:
			note="未知消息";
			break;

		default:
			break;
		}
		return note;
	}
	
	public static String getContent(Enum_PushType tEnum,T_MsgSeq bean){
		String content=new String();
		switch (tEnum) {
		case COM_AWARD:
			
			break;
		case COM_BAOBEI:
			if(bean.getMsg_seq_success()==1){
				content="您对本公司的报备成功";
			}else {
				content="您对本公司的报备失败";
			}
			
			break;
		case COM_CHECK:
//			if(bean.getMsg_seq_success()==1){
//				content="您和本公司的关联成功";
//			}else {
//				content="您和本公司的关联失败";
//			}
			content= bean.getMsg_seq_content();
			break;
		case COM_INFORM:
			content=bean.getMsg_seq_content();
			break;
		case RES_INTEREST:
			content="文档 《"+bean.getMsg_seq_resName()+"》";
			break;
		case RES_RECEV:
			content="文档 《"+bean.getMsg_seq_resName()+"》";
			break;
		case SYS_INFORM:
			content=bean.getMsg_seq_content();
			break;
		case DEFAULT:
			content="未知消息";
			break;

		default:
			break;
		}
		return content;
	}

	// public static String msgDoc

}
