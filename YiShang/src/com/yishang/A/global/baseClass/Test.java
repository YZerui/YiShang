package com.yishang.A.global.baseClass;
//package com.yishang.A.baseClass;
//
//import java.util.ArrayList;
//
//import com.exception.utils.P;
//import com.lidroid.xutils.exception.DbException;
//import com.yishang.A.constant.DAO_CONSTANT;
//import com.yishang.E.daoImpl.BookDaoImpl;
//import com.yishang.E.daoImpl.CompanyDaoImpl;
//import com.yishang.E.daoImpl.Dao_MsgImpl;
//import com.yishang.E.daoImpl.RelationshipDaoImpl;
//import com.yishang.E.daoModel.T_Book;
//import com.yishang.E.daoModel.T_Company;
//import com.yishang.E.daoModel.T_Msg;
//import com.yishang.E.daoModel.T_Relationships;
//
//public class Test {
//	public static void initMsgData(){
//		ArrayList<T_Msg> list=new ArrayList<T_Msg>();
//		T_Msg bean;
//		/**系统通知**/
//		bean=new T_Msg();
//		bean.setMsg_content("欢迎你成为易商用户");
//		bean.setMsg_source(DAO_CONSTANT.DAO_MSG_SYSTEM);
//		bean.setMsg_type(DAO_CONSTANT.DAO_MSGTYPE_SYSTEM_INFORM);
//		list.add(bean);
//		
//		bean=new T_Msg();
//		bean.setMsg_content("您所关联的艾克科技公司因已破产被系统列入黑名单，其所有文档将不再允许转发。");
//		bean.setMsg_source(DAO_CONSTANT.DAO_MSG_SYSTEM);
//		bean.setMsg_type(DAO_CONSTANT.DAO_MSGTYPE_SYSTEM_INFORM);
//		list.add(bean);
//		
//		bean=new T_Msg();
//		bean.setMsg_content("你转发给通讯录好友的文档未能成功,请重新发送");
//		bean.setMsg_source(DAO_CONSTANT.DAO_MSG_SYSTEM);
//		bean.setMsg_type(DAO_CONSTANT.DAO_MSGTYPE_SYSTEM_INFORM);
//		list.add(bean);
//		
//		
//
//		bean=new T_Msg();
//		bean.setMsg_content("《草莓特卖》");
//		bean.setMsg_sender("颜小姐");
//		bean.setMsg_source(DAO_CONSTANT.DAO_MSG_RECEIVE);
//		bean.setMsg_type(DAO_CONSTANT.DAO_MSGTYPE_USER_RECEIVE);
//		list.add(bean);
//		/**企业通知**/
//		
//		bean=new T_Msg();
//		bean.setMsg_sender("星巴克");
//		bean.setMsg_content("我们已经通过您的关联申请");
//		bean.setMsg_source(DAO_CONSTANT.DAO_MSG_COMPANY);
//		bean.setMsg_type(DAO_CONSTANT.DAO_MSGTYPE_COM_VERIFY);
//		list.add(bean);
//		
//		bean=new T_Msg();
//		bean.setMsg_sender("星巴克");
//		bean.setMsg_content("很抱歉，您的本次关联申请未获通过");
//		bean.setMsg_source(DAO_CONSTANT.DAO_MSG_COMPANY);
//		bean.setMsg_type(DAO_CONSTANT.DAO_MSGTYPE_COM_VERIFY);
//		list.add(bean);
//
//		bean=new T_Msg();
//		bean.setMsg_content("《新品奶茶系列》");
//		bean.setMsg_sender("颜小姐");
//		bean.setMsg_source(DAO_CONSTANT.DAO_MSG_RECEIVE);
//		bean.setMsg_type(DAO_CONSTANT.DAO_MSGTYPE_USER_INTEREST);
//		list.add(bean);
//		
//		bean=new T_Msg();
//		bean.setMsg_content("游先生通过对你转发的文档《新品奶茶系列》关联了本公司，您因此获得了20积分。");
//		bean.setMsg_sender("星巴克");
//		bean.setMsg_source(DAO_CONSTANT.DAO_MSG_COMPANY);
//		bean.setMsg_type(DAO_CONSTANT.DAO_MSGTYPE_COM_CREDIT);
//		list.add(bean);
//		
//		bean=new T_Msg();
//		bean.setMsg_content("我们已安排游先生作为目前协助您的销售经理。");
//		bean.setMsg_sender("星巴克");
//		bean.setMsg_source(DAO_CONSTANT.DAO_MSG_COMPANY);
//		bean.setMsg_type(DAO_CONSTANT.DAO_MSGTYPE_COM_INFORM);
//		list.add(bean);
//		
//		bean=new T_Msg();
//		bean.setMsg_sender("星巴克");
//		bean.setMsg_content("很抱歉，我们已取消和您的关联");
//		bean.setMsg_source(DAO_CONSTANT.DAO_MSG_COMPANY);
//		bean.setMsg_type(DAO_CONSTANT.DAO_MSGTYPE_COM_VERIFY);
//		list.add(bean);
//		
//		/**收到用户文档**/
//		
//		bean=new T_Msg();
//		bean.setMsg_content("《野生猪肉》");
//		bean.setMsg_sender("颜小姐");
//		bean.setMsg_source(DAO_CONSTANT.DAO_MSG_RECEIVE);
//		bean.setMsg_type(DAO_CONSTANT.DAO_MSGTYPE_USER_INTEREST);
//		list.add(bean);
//		
//		
//		try {
//			Dao_MsgImpl.addRecord(list);
//		} catch (DbException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public static void initContactsData(){
//		ArrayList<T_Relationships> list=new ArrayList<T_Relationships>();
//		
//		T_Relationships bean=new T_Relationships();
//		bean.setRela_id("1234");
//		bean.setRela_name("游小瑞");
//		bean.setRela_register(DAO_CONSTANT.DAO_REGISTER_TRUE);
//		bean.setRela_type(DAO_CONSTANT.DAO_RELA_CLIENT);
//		
//		list.add(bean);
//		
//		T_Relationships bean1=new T_Relationships();
//		bean1.setRela_id("2234");
//		bean1.setRela_name("王先生");
//		bean1.setRela_register(DAO_CONSTANT.DAO_REGISTER_TRUE);
//		bean1.setRela_type(DAO_CONSTANT.DAO_RELA_CLIENT);
//		list.add(bean1);
//		
//		T_Relationships bean2=new T_Relationships();
//		bean2.setRela_id("3234");
//		bean2.setRela_name("林总");
//		bean2.setRela_register(DAO_CONSTANT.DAO_REGISTER_TRUE);
//		bean2.setRela_type(DAO_CONSTANT.DAO_RELA_SUPPLIER);
//		list.add(bean2);
//		
//		T_Relationships bean3=new T_Relationships();
//		bean3.setRela_id("4234");
//		bean3.setRela_name("方大同");
//		bean3.setRela_register(DAO_CONSTANT.DAO_REGISTER_TRUE);
//		bean3.setRela_type(DAO_CONSTANT.DAO_RELA_SUPPLIER);
//		list.add(bean3);
//		
//		T_Relationships bean4=new T_Relationships();
//		bean4.setRela_id("5234");
//		bean4.setRela_name("可欣");
//		bean4.setRela_register(DAO_CONSTANT.DAO_REGISTER_TRUE);
//		bean4.setRela_type(DAO_CONSTANT.DAO_RELA_CONTACT);
//		list.add(bean4);
//		
//		T_Relationships bean5=new T_Relationships();
//		bean5.setRela_id("6234");
//		bean5.setRela_name("优视企业");
//		bean5.setRela_register(DAO_CONSTANT.DAO_REGISTER_TRUE);
//		bean5.setRela_type(DAO_CONSTANT.DAO_RELA_FRIENDS);
//		list.add(bean5);
//		
//		T_Relationships bean6=new T_Relationships();
//		bean6.setRela_id("7234");
//		bean6.setRela_name("柯震某");
//		bean6.setRela_register(DAO_CONSTANT.DAO_REGISTER_TRUE);
//		bean6.setRela_type(DAO_CONSTANT.DAO_RELA_FRIENDS);
//		list.add(bean6);
//		
//		T_Relationships bean7=new T_Relationships();
//		bean7.setRela_id("8234");
//		bean7.setRela_name("琴小姐");
//		bean7.setRela_register(DAO_CONSTANT.DAO_REGISTER_TRUE);
//		bean.setRela_type(DAO_CONSTANT.DAO_RELA_FRIENDS);
//		list.add(bean7);
//		
//		try {
//			RelationshipDaoImpl.addList(list);
//		} catch (DbException e) {
//			// TODO Auto-generated catch block
//			P.v("数据操作出错");
//			e.printStackTrace();
//		}
//	}
//	public static void initResourceData(){
//		ArrayList<T_Book> list=new ArrayList<T_Book>();
//		
//		T_Book bean;
//		
//		bean=new T_Book();
//		bean.setBook_company("100");
//		bean.setBook_cTime("19:20");
//		bean.setBook_kw("阿里巴巴");
//		bean.setBook_name("资金的流转");
//		bean.setBook_type(DAO_CONSTANT.DAO_BOOK_CONTACT);
//		bean.setBook_download(DAO_CONSTANT.DAO_BOOKDOWN_FALSE);
//		bean.setBook_status(DAO_CONSTANT.DAO_BOOKSTATUS_RELEASE);
//		list.add(bean);
//		
//		bean=new T_Book();
//		bean.setBook_company("100");
//		bean.setBook_cTime("10:20");
//		bean.setBook_kw("万科地产");
//		bean.setBook_name("楼盘的艺术");
//		bean.setBook_type(DAO_CONSTANT.DAO_BOOK_FRIEND);
//		bean.setBook_download(DAO_CONSTANT.DAO_BOOKDOWN_TRUE);
//		bean.setBook_status(DAO_CONSTANT.DAO_BOOKSTATUS_DROP);
//		list.add(bean);
//		
//		bean=new T_Book();
//		bean.setBook_company("100");
//		bean.setBook_cTime("19:50");
//		bean.setBook_kw("沃尔玛");
//		bean.setBook_name("如何线上销售牛肉");
//		bean.setBook_type(DAO_CONSTANT.DAO_BOOK_INTEREST);
//		bean.setBook_download(DAO_CONSTANT.DAO_BOOKDOWN_FALSE);
//		bean.setBook_status(DAO_CONSTANT.DAO_BOOKSTATUS_DROP);
//		list.add(bean);
//		
//		bean=new T_Book();
//		bean.setBook_company("100");
//		bean.setBook_cTime("09::20");
//		bean.setBook_kw("瑞瑞科技 ");
//		bean.setBook_name("强大的智能排插");
//		bean.setBook_type(DAO_CONSTANT.DAO_BOOK_FRIEND);
//		bean.setBook_download(DAO_CONSTANT.DAO_BOOKDOWN_TRUE);
//		bean.setBook_status(DAO_CONSTANT.DAO_BOOKSTATUS_RELEASE);
//		list.add(bean);
//		
//		bean=new T_Book();
//		bean.setBook_company("100");
//		bean.setBook_cTime("18:20");
//		bean.setBook_kw("国美电器");
//		bean.setBook_name("空调大促销");
//		bean.setBook_type(DAO_CONSTANT.DAO_BOOK_INTEREST);
//		bean.setBook_download(DAO_CONSTANT.DAO_BOOKDOWN_FALSE);
//		bean.setBook_status(DAO_CONSTANT.DAO_BOOKSTATUS_RELEASE);
//		list.add(bean);
//		
//		bean=new T_Book();
//		bean.setBook_company("100");
//		bean.setBook_cTime("21:20");
//		bean.setBook_kw("东莞旅游局");
//		bean.setBook_name("东莞公园开发");
//		bean.setBook_type(DAO_CONSTANT.DAO_BOOK_CONTACT);
//		bean.setBook_download(DAO_CONSTANT.DAO_BOOKDOWN_TRUE);
//		bean.setBook_status(DAO_CONSTANT.DAO_BOOKSTATUS_UNRELEASE);
//		list.add(bean);
//		
//		bean=new T_Book();
//		bean.setBook_company("100");
//		bean.setBook_cTime("17:20");
//		bean.setBook_kw("圆通快递");
//		bean.setBook_name("松山湖物流优惠");
//		bean.setBook_type(DAO_CONSTANT.DAO_BOOK_FRIEND);
//		bean.setBook_download(DAO_CONSTANT.DAO_BOOKDOWN_TRUE);
//		bean.setBook_status(DAO_CONSTANT.DAO_BOOKSTATUS_DROP);
//		list.add(bean);
//		
//		bean=new T_Book();
//		bean.setBook_company("100");
//		bean.setBook_cTime("13:20");
//		bean.setBook_kw("皮皮网络公司");
//		bean.setBook_name("外包服务");
//		bean.setBook_type(DAO_CONSTANT.DAO_BOOK_INTEREST);
//		bean.setBook_download(DAO_CONSTANT.DAO_BOOKDOWN_TRUE);
//		bean.setBook_status(DAO_CONSTANT.DAO_BOOKSTATUS_DROP);
//		list.add(bean);
//	
//		bean=new T_Book();
//		bean.setBook_company("100");
//		bean.setBook_cTime("17:20");
//		bean.setBook_kw("360搜索");
//		bean.setBook_name("产品推广");
//		bean.setBook_type(DAO_CONSTANT.DAO_BOOK_CONTACT);
//		bean.setBook_download(DAO_CONSTANT.DAO_BOOKDOWN_TRUE);
//		bean.setBook_status(DAO_CONSTANT.DAO_BOOKSTATUS_DROP);
//		list.add(bean);
//		
//		bean=new T_Book();
//		bean.setBook_company("100");
//		bean.setBook_cTime("17:20");
//		bean.setBook_kw("耐克公司");
//		bean.setBook_name("健身器材团购");
//		bean.setBook_type(DAO_CONSTANT.DAO_BOOK_INTEREST);
//		bean.setBook_download(DAO_CONSTANT.DAO_BOOKDOWN_TRUE);
//		bean.setBook_status(DAO_CONSTANT.DAO_BOOKSTATUS_DROP);
//		list.add(bean);
//		
//		bean=new T_Book();
//		bean.setBook_company("100");
//		bean.setBook_cTime("09::20");
//		bean.setBook_kw("艾克智能家居");
//		bean.setBook_name("智能监控");
//		bean.setBook_type(DAO_CONSTANT.DAO_BOOK_INTEREST);
//		bean.setBook_download(DAO_CONSTANT.DAO_BOOKDOWN_TRUE);
//		bean.setBook_status(DAO_CONSTANT.DAO_BOOKSTATUS_DROP);
//		list.add(bean);
//		
//		bean=new T_Book();
//		bean.setBook_company("100");
//		bean.setBook_cTime("19::20");
//		bean.setBook_kw("伊利集团");
//		bean.setBook_name("经典牛奶");
//		bean.setBook_type(DAO_CONSTANT.DAO_BOOK_CONTACT);
//		bean.setBook_download(DAO_CONSTANT.DAO_BOOKDOWN_TRUE);
//		bean.setBook_status(DAO_CONSTANT.DAO_BOOKSTATUS_DROP);
//		list.add(bean);
//		
//		try {
//			BookDaoImpl.addBook(list);
//		} catch (DbException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public static void initCompanyData(){
//		ArrayList<T_Company> list=new ArrayList<T_Company>();
//		T_Company bean;
//		
//		bean=new T_Company();
//		bean.setCom_intro("苏宁电器");
//		bean.setCom_intro("");
//		bean.setCom_relate(DAO_CONSTANT.DAO_COMPANY_RELATE);
//		list.add(bean);
//		
//		bean=new T_Company();
//		bean.setCom_intro("安踏体育");
//		bean.setCom_relate(DAO_CONSTANT.DAO_COMPANY_CLIENT);
//		list.add(bean);
//		
//		bean=new T_Company();
//		bean.setCom_intro("步步高电子");
//		bean.setCom_relate(DAO_CONSTANT.DAO_COMPANY_RELATE);
//		list.add(bean);
//		
//		bean=new T_Company();
//		bean.setCom_intro("华盛水泥");
//		bean.setCom_relate(DAO_CONSTANT.DAO_COMPANY_SUPPLIER);
//		list.add(bean);
//		
//		bean=new T_Company();
//		bean.setCom_intro("美宜佳");
//		bean.setCom_relate(DAO_CONSTANT.DAO_COMPANY_RELATE);
//		list.add(bean);
//		
//		bean=new T_Company();
//		bean.setCom_intro("康泰保险");
//		bean.setCom_relate(DAO_CONSTANT.DAO_COMPANY_CLIENT);
//		list.add(bean);
//		
//		bean=new T_Company();
//		bean.setCom_intro("清远旅游公司");
//		bean.setCom_relate(DAO_CONSTANT.DAO_COMPANY_RELATE);
//		list.add(bean);
//	
//		try {
//			CompanyDaoImpl.addCompany(list);
//		} catch (DbException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
//
