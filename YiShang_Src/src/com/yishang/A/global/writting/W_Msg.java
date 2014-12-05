package com.yishang.A.global.writting;

import com.format.utils.DataValidate;
import com.yishang.A.global.Enum.push.Enum_PushType;
import com.yishang.C.dao.daoModel.T_Msg;
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
	/**
	 * 消息详情
	 * @param tEnum
	 * @param bean
	 * @return
	 */
	public static String getContentDetail(Enum_PushType tEnum,T_Msg bean){
		String content=new String();
		switch (tEnum) {
			//奖赏
		case COM_AWARD:
			
			break;
			//报备
		case COM_BAOBEI:
			if(bean.getMsg_success()==1){
				content="恭喜您，"+bean.getMsg_comName()+
							"通过了您的关联申请，现在您可以代表"+bean.getMsg_comName()+"开展营销了。";
			}else {
				content="很抱歉，"+bean.getMsg_comName()+"没有通过您的关联申请，如有疑问请联系相关人员。";
			}
			break;
			//关联审查
		case COM_CHECK:
			if(bean.getMsg_success()==1){
				content="恭喜您，您在"+bean.getMsg_comName()+"成功报备客户"+bean.getMsg_sendName()+
						"，在n个月内（n由企业设置）该用户由您负责跟进。";
			}else {
				content="很抱歉，您在"+bean.getMsg_comName()+"报备的客户"+bean.getMsg_sendName()+
						"没有获得批准，如有疑问请联系相关人员。";
			}
			break;
			//企业通知
		case COM_INFORM:
			content=bean.getMsg_content();
			break;
			//资源感兴趣
		case RES_INTEREST:
			content=bean.getMsg_sendName()+"对由您的文档《"+bean.getMsg_resName()+"》表示感兴趣，请确认该用户信息。";
			break;
			//收到文档
		case RES_RECEV:
			content="您收到"+bean.getMsg_sendName()+"转发给您的文档《"+bean.getMsg_resName()+"》，请查阅。";
			break;
			//系统通知
		case SYS_INFORM:
			content=bean.getMsg_content();
			break;
			//未知消息
		case DEFAULT:
			content="未知消息";
			break;
			
		default:
			break;
		}
		return content;
	}
	/**
	 * 小秘书默认提示
	 */
	public final static String SYSDefault="感谢您使用易商！易商是一款移动商务应用，" +
			"能够帮助您高效传播信息，快速增长人脉，最终帮助您提升业绩。更具体的介绍您可" +
			"以查看资源栏目中的《易商-让业绩飞》这份移动画册。关于易商的任何问题或建议，" +
			"您都可以在这里告诉我，或者发邮件到peter@wisdomeng.com，让我们和您一起进步！" +
			"易商祝您业绩长虹！"; 
	
	// public static String msgDoc

}
