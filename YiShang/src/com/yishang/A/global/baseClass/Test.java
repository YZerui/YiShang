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
//		/**ϵͳ֪ͨ**/
//		bean=new T_Msg();
//		bean.setMsg_content("��ӭ���Ϊ�����û�");
//		bean.setMsg_source(DAO_CONSTANT.DAO_MSG_SYSTEM);
//		bean.setMsg_type(DAO_CONSTANT.DAO_MSGTYPE_SYSTEM_INFORM);
//		list.add(bean);
//		
//		bean=new T_Msg();
//		bean.setMsg_content("���������İ��˿Ƽ���˾�����Ʋ���ϵͳ������������������ĵ�����������ת����");
//		bean.setMsg_source(DAO_CONSTANT.DAO_MSG_SYSTEM);
//		bean.setMsg_type(DAO_CONSTANT.DAO_MSGTYPE_SYSTEM_INFORM);
//		list.add(bean);
//		
//		bean=new T_Msg();
//		bean.setMsg_content("��ת����ͨѶ¼���ѵ��ĵ�δ�ܳɹ�,�����·���");
//		bean.setMsg_source(DAO_CONSTANT.DAO_MSG_SYSTEM);
//		bean.setMsg_type(DAO_CONSTANT.DAO_MSGTYPE_SYSTEM_INFORM);
//		list.add(bean);
//		
//		
//
//		bean=new T_Msg();
//		bean.setMsg_content("����ݮ������");
//		bean.setMsg_sender("��С��");
//		bean.setMsg_source(DAO_CONSTANT.DAO_MSG_RECEIVE);
//		bean.setMsg_type(DAO_CONSTANT.DAO_MSGTYPE_USER_RECEIVE);
//		list.add(bean);
//		/**��ҵ֪ͨ**/
//		
//		bean=new T_Msg();
//		bean.setMsg_sender("�ǰͿ�");
//		bean.setMsg_content("�����Ѿ�ͨ�����Ĺ�������");
//		bean.setMsg_source(DAO_CONSTANT.DAO_MSG_COMPANY);
//		bean.setMsg_type(DAO_CONSTANT.DAO_MSGTYPE_COM_VERIFY);
//		list.add(bean);
//		
//		bean=new T_Msg();
//		bean.setMsg_sender("�ǰͿ�");
//		bean.setMsg_content("�ܱ�Ǹ�����ı��ι�������δ��ͨ��");
//		bean.setMsg_source(DAO_CONSTANT.DAO_MSG_COMPANY);
//		bean.setMsg_type(DAO_CONSTANT.DAO_MSGTYPE_COM_VERIFY);
//		list.add(bean);
//
//		bean=new T_Msg();
//		bean.setMsg_content("����Ʒ�̲�ϵ�С�");
//		bean.setMsg_sender("��С��");
//		bean.setMsg_source(DAO_CONSTANT.DAO_MSG_RECEIVE);
//		bean.setMsg_type(DAO_CONSTANT.DAO_MSGTYPE_USER_INTEREST);
//		list.add(bean);
//		
//		bean=new T_Msg();
//		bean.setMsg_content("������ͨ������ת�����ĵ�����Ʒ�̲�ϵ�С������˱���˾������˻����20���֡�");
//		bean.setMsg_sender("�ǰͿ�");
//		bean.setMsg_source(DAO_CONSTANT.DAO_MSG_COMPANY);
//		bean.setMsg_type(DAO_CONSTANT.DAO_MSGTYPE_COM_CREDIT);
//		list.add(bean);
//		
//		bean=new T_Msg();
//		bean.setMsg_content("�����Ѱ�����������ΪĿǰЭ���������۾���");
//		bean.setMsg_sender("�ǰͿ�");
//		bean.setMsg_source(DAO_CONSTANT.DAO_MSG_COMPANY);
//		bean.setMsg_type(DAO_CONSTANT.DAO_MSGTYPE_COM_INFORM);
//		list.add(bean);
//		
//		bean=new T_Msg();
//		bean.setMsg_sender("�ǰͿ�");
//		bean.setMsg_content("�ܱ�Ǹ��������ȡ�������Ĺ���");
//		bean.setMsg_source(DAO_CONSTANT.DAO_MSG_COMPANY);
//		bean.setMsg_type(DAO_CONSTANT.DAO_MSGTYPE_COM_VERIFY);
//		list.add(bean);
//		
//		/**�յ��û��ĵ�**/
//		
//		bean=new T_Msg();
//		bean.setMsg_content("��Ұ�����⡷");
//		bean.setMsg_sender("��С��");
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
//		bean.setRela_name("��С��");
//		bean.setRela_register(DAO_CONSTANT.DAO_REGISTER_TRUE);
//		bean.setRela_type(DAO_CONSTANT.DAO_RELA_CLIENT);
//		
//		list.add(bean);
//		
//		T_Relationships bean1=new T_Relationships();
//		bean1.setRela_id("2234");
//		bean1.setRela_name("������");
//		bean1.setRela_register(DAO_CONSTANT.DAO_REGISTER_TRUE);
//		bean1.setRela_type(DAO_CONSTANT.DAO_RELA_CLIENT);
//		list.add(bean1);
//		
//		T_Relationships bean2=new T_Relationships();
//		bean2.setRela_id("3234");
//		bean2.setRela_name("����");
//		bean2.setRela_register(DAO_CONSTANT.DAO_REGISTER_TRUE);
//		bean2.setRela_type(DAO_CONSTANT.DAO_RELA_SUPPLIER);
//		list.add(bean2);
//		
//		T_Relationships bean3=new T_Relationships();
//		bean3.setRela_id("4234");
//		bean3.setRela_name("����ͬ");
//		bean3.setRela_register(DAO_CONSTANT.DAO_REGISTER_TRUE);
//		bean3.setRela_type(DAO_CONSTANT.DAO_RELA_SUPPLIER);
//		list.add(bean3);
//		
//		T_Relationships bean4=new T_Relationships();
//		bean4.setRela_id("5234");
//		bean4.setRela_name("����");
//		bean4.setRela_register(DAO_CONSTANT.DAO_REGISTER_TRUE);
//		bean4.setRela_type(DAO_CONSTANT.DAO_RELA_CONTACT);
//		list.add(bean4);
//		
//		T_Relationships bean5=new T_Relationships();
//		bean5.setRela_id("6234");
//		bean5.setRela_name("������ҵ");
//		bean5.setRela_register(DAO_CONSTANT.DAO_REGISTER_TRUE);
//		bean5.setRela_type(DAO_CONSTANT.DAO_RELA_FRIENDS);
//		list.add(bean5);
//		
//		T_Relationships bean6=new T_Relationships();
//		bean6.setRela_id("7234");
//		bean6.setRela_name("����ĳ");
//		bean6.setRela_register(DAO_CONSTANT.DAO_REGISTER_TRUE);
//		bean6.setRela_type(DAO_CONSTANT.DAO_RELA_FRIENDS);
//		list.add(bean6);
//		
//		T_Relationships bean7=new T_Relationships();
//		bean7.setRela_id("8234");
//		bean7.setRela_name("��С��");
//		bean7.setRela_register(DAO_CONSTANT.DAO_REGISTER_TRUE);
//		bean.setRela_type(DAO_CONSTANT.DAO_RELA_FRIENDS);
//		list.add(bean7);
//		
//		try {
//			RelationshipDaoImpl.addList(list);
//		} catch (DbException e) {
//			// TODO Auto-generated catch block
//			P.v("���ݲ�������");
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
//		bean.setBook_kw("����Ͱ�");
//		bean.setBook_name("�ʽ����ת");
//		bean.setBook_type(DAO_CONSTANT.DAO_BOOK_CONTACT);
//		bean.setBook_download(DAO_CONSTANT.DAO_BOOKDOWN_FALSE);
//		bean.setBook_status(DAO_CONSTANT.DAO_BOOKSTATUS_RELEASE);
//		list.add(bean);
//		
//		bean=new T_Book();
//		bean.setBook_company("100");
//		bean.setBook_cTime("10:20");
//		bean.setBook_kw("��Ƶز�");
//		bean.setBook_name("¥�̵�����");
//		bean.setBook_type(DAO_CONSTANT.DAO_BOOK_FRIEND);
//		bean.setBook_download(DAO_CONSTANT.DAO_BOOKDOWN_TRUE);
//		bean.setBook_status(DAO_CONSTANT.DAO_BOOKSTATUS_DROP);
//		list.add(bean);
//		
//		bean=new T_Book();
//		bean.setBook_company("100");
//		bean.setBook_cTime("19:50");
//		bean.setBook_kw("�ֶ���");
//		bean.setBook_name("�����������ţ��");
//		bean.setBook_type(DAO_CONSTANT.DAO_BOOK_INTEREST);
//		bean.setBook_download(DAO_CONSTANT.DAO_BOOKDOWN_FALSE);
//		bean.setBook_status(DAO_CONSTANT.DAO_BOOKSTATUS_DROP);
//		list.add(bean);
//		
//		bean=new T_Book();
//		bean.setBook_company("100");
//		bean.setBook_cTime("09::20");
//		bean.setBook_kw("����Ƽ� ");
//		bean.setBook_name("ǿ��������Ų�");
//		bean.setBook_type(DAO_CONSTANT.DAO_BOOK_FRIEND);
//		bean.setBook_download(DAO_CONSTANT.DAO_BOOKDOWN_TRUE);
//		bean.setBook_status(DAO_CONSTANT.DAO_BOOKSTATUS_RELEASE);
//		list.add(bean);
//		
//		bean=new T_Book();
//		bean.setBook_company("100");
//		bean.setBook_cTime("18:20");
//		bean.setBook_kw("��������");
//		bean.setBook_name("�յ������");
//		bean.setBook_type(DAO_CONSTANT.DAO_BOOK_INTEREST);
//		bean.setBook_download(DAO_CONSTANT.DAO_BOOKDOWN_FALSE);
//		bean.setBook_status(DAO_CONSTANT.DAO_BOOKSTATUS_RELEASE);
//		list.add(bean);
//		
//		bean=new T_Book();
//		bean.setBook_company("100");
//		bean.setBook_cTime("21:20");
//		bean.setBook_kw("��ݸ���ξ�");
//		bean.setBook_name("��ݸ��԰����");
//		bean.setBook_type(DAO_CONSTANT.DAO_BOOK_CONTACT);
//		bean.setBook_download(DAO_CONSTANT.DAO_BOOKDOWN_TRUE);
//		bean.setBook_status(DAO_CONSTANT.DAO_BOOKSTATUS_UNRELEASE);
//		list.add(bean);
//		
//		bean=new T_Book();
//		bean.setBook_company("100");
//		bean.setBook_cTime("17:20");
//		bean.setBook_kw("Բͨ���");
//		bean.setBook_name("��ɽ�������Ż�");
//		bean.setBook_type(DAO_CONSTANT.DAO_BOOK_FRIEND);
//		bean.setBook_download(DAO_CONSTANT.DAO_BOOKDOWN_TRUE);
//		bean.setBook_status(DAO_CONSTANT.DAO_BOOKSTATUS_DROP);
//		list.add(bean);
//		
//		bean=new T_Book();
//		bean.setBook_company("100");
//		bean.setBook_cTime("13:20");
//		bean.setBook_kw("ƤƤ���繫˾");
//		bean.setBook_name("�������");
//		bean.setBook_type(DAO_CONSTANT.DAO_BOOK_INTEREST);
//		bean.setBook_download(DAO_CONSTANT.DAO_BOOKDOWN_TRUE);
//		bean.setBook_status(DAO_CONSTANT.DAO_BOOKSTATUS_DROP);
//		list.add(bean);
//	
//		bean=new T_Book();
//		bean.setBook_company("100");
//		bean.setBook_cTime("17:20");
//		bean.setBook_kw("360����");
//		bean.setBook_name("��Ʒ�ƹ�");
//		bean.setBook_type(DAO_CONSTANT.DAO_BOOK_CONTACT);
//		bean.setBook_download(DAO_CONSTANT.DAO_BOOKDOWN_TRUE);
//		bean.setBook_status(DAO_CONSTANT.DAO_BOOKSTATUS_DROP);
//		list.add(bean);
//		
//		bean=new T_Book();
//		bean.setBook_company("100");
//		bean.setBook_cTime("17:20");
//		bean.setBook_kw("�Ϳ˹�˾");
//		bean.setBook_name("���������Ź�");
//		bean.setBook_type(DAO_CONSTANT.DAO_BOOK_INTEREST);
//		bean.setBook_download(DAO_CONSTANT.DAO_BOOKDOWN_TRUE);
//		bean.setBook_status(DAO_CONSTANT.DAO_BOOKSTATUS_DROP);
//		list.add(bean);
//		
//		bean=new T_Book();
//		bean.setBook_company("100");
//		bean.setBook_cTime("09::20");
//		bean.setBook_kw("�������ܼҾ�");
//		bean.setBook_name("���ܼ��");
//		bean.setBook_type(DAO_CONSTANT.DAO_BOOK_INTEREST);
//		bean.setBook_download(DAO_CONSTANT.DAO_BOOKDOWN_TRUE);
//		bean.setBook_status(DAO_CONSTANT.DAO_BOOKSTATUS_DROP);
//		list.add(bean);
//		
//		bean=new T_Book();
//		bean.setBook_company("100");
//		bean.setBook_cTime("19::20");
//		bean.setBook_kw("��������");
//		bean.setBook_name("����ţ��");
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
//		bean.setCom_intro("��������");
//		bean.setCom_intro("");
//		bean.setCom_relate(DAO_CONSTANT.DAO_COMPANY_RELATE);
//		list.add(bean);
//		
//		bean=new T_Company();
//		bean.setCom_intro("��̤����");
//		bean.setCom_relate(DAO_CONSTANT.DAO_COMPANY_CLIENT);
//		list.add(bean);
//		
//		bean=new T_Company();
//		bean.setCom_intro("�����ߵ���");
//		bean.setCom_relate(DAO_CONSTANT.DAO_COMPANY_RELATE);
//		list.add(bean);
//		
//		bean=new T_Company();
//		bean.setCom_intro("��ʢˮ��");
//		bean.setCom_relate(DAO_CONSTANT.DAO_COMPANY_SUPPLIER);
//		list.add(bean);
//		
//		bean=new T_Company();
//		bean.setCom_intro("���˼�");
//		bean.setCom_relate(DAO_CONSTANT.DAO_COMPANY_RELATE);
//		list.add(bean);
//		
//		bean=new T_Company();
//		bean.setCom_intro("��̩����");
//		bean.setCom_relate(DAO_CONSTANT.DAO_COMPANY_CLIENT);
//		list.add(bean);
//		
//		bean=new T_Company();
//		bean.setCom_intro("��Զ���ι�˾");
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
