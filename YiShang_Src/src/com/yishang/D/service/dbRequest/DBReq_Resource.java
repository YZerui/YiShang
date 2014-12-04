package com.yishang.D.service.dbRequest;

import java.util.List;

import cn.sharesdk.framework.network.c;

import com.exception.utils.P;
import com.format.utils.DataValidate;
import com.lidroid.xutils.exception.DbException;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;
import com.yishang.A.global.Enum.Enum_ListLimit;
import com.yishang.A.global.Enum.db.Enum_ResSource;
import com.yishang.A.global.Enum.db.Enum_ResType;
import com.yishang.C.dao.daoImpl.Dao_Resource;
import com.yishang.C.dao.daoImpl.Dao_Self;
import com.yishang.C.dao.daoModel.T_Resource;

/**
 * ��Դ�ļ��ı��ػ�ȡ
 * 
 * @author MM_Zerui
 * @note ���ȼ��������˵Ĺ�ϵǿ�ȡ��յ�ʱ�䡢���� ����һ���ڵ����ݰ�����������ԭ���ö�
 *       ���г�����һ���������ݣ���ÿ��һ���ĵ�һ�Σ�����ָ��+1�����ڱ������������ϴ���������
 * 
 *       ��Դɸѡ��������ҵ���ͻ�����Ӧ�̡���ϵ�ˡ�������
 * 
 *       ����ɸѡ��Ĭ��(ȫ��)�����жȣ�ʱ�䣬����
 * 
 */
public class DBReq_Resource {
	private callBack_Res callDefault;
	private boolean ifType=true;
	private boolean ifInit=false;
	
	public boolean isIfInit() {
		return ifInit;
	}
	/**
	 * �趨Ĭ����Դ���еĲ����ص�
	 * @param call
	 */
	public DBReq_Resource setCallBack_Default(callBack_Res call) {
		this.callDefault = call;
		ifType=true;
		return this;
	}
	public DBReq_Resource getAll(){
		callDefault.index = Enum_ListLimit.DEFAULT.value();
		return this;
	}
	public void callInit(){
		ifInit=true;
		if(ifType){
			callInit_Type();
			return;
		}
		callInit_Source();
	}
	public void callLoad(){
		ifInit=false;
		if(ifType){
			callLoad_Type();
			return;
		}
		callLoad_Source();
	}
	/**
	 * ��ʾ������Դ��Ϣ
	 */
	private void callInit_Default() {

		new RunnableService(new runCallBack() {

			@Override
			public void start() {
				// TODO Auto-generated method stub
				try {
					List<T_Resource> list = Dao_Resource.getAll_WeekIn();
					if(DataValidate.checkDataValid(list)){
						List<T_Resource> listMore=Dao_Resource.getAll_WeekOut(callDefault.index++);
						if(DataValidate.checkDataValid(listMore)){
							list.addAll(listMore);
						}
						callDefault.onInit(list);
						return;
					}
					
				} catch (DbException e) {
					e.printStackTrace();
				}
				callDefault.onFail();
			}

			@Override
			public void end() {
				callDefault.onFinally();
			}
		}, true);
	}

	private void callLoad_Default(Boolean ifReset) {
		if (ifReset) {
			callDefault.index = 0;
		}
		new RunnableService(new runCallBack() {

			@Override
			public void start() {
				// TODO Auto-generated method stub
				try {
					List<T_Resource> list=Dao_Resource
							.getAll_WeekOut(callDefault.index++);
					if(DataValidate.checkDataValid(list)){
						callDefault.onLoadMore(list);
						return;
					}
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				callDefault.onFail();
			}

			@Override
			public void end() {
				// TODO Auto-generated method stub
				callDefault.onFinally();
			}
		}, true);
	}

	public static abstract class callBack_Res {
		public int index = 0;

		public abstract void onFail();
		public abstract void onFinally();
		public abstract void onInit(List<T_Resource> list);

		public abstract void onLoadMore(List<T_Resource> list);
	}

	/** *********************������Դ����********************* **/
	private Enum_ResType enumResType;
	public DBReq_Resource setEnum_ResType(Enum_ResType enumType){
		this.enumResType = enumType;
		ifType=true;
		return this;
	}

	private void callInit_Type() {
		callDefault.index = 0;
		ifInit=true;
		new RunnableService(new runCallBack() {

			@Override
			public void start() {
				try {
					switch (enumResType) {
					case DEFAULT:
						callInit_Default();
						return;
					case FREQUENCE:
						List<T_Resource> freqList=Dao_Resource
								.getAll_Freq(callDefault.index++);
						if(DataValidate.checkDataValid(freqList)){
							callDefault.onInit(freqList);
							return;
						}
						break;
					case RELA_STRENGTH:
						List<T_Resource> relateList=Dao_Resource
								.getAll_Relate(callDefault.index++);
						if(DataValidate.checkDataValid(relateList)){
							callDefault.onInit(relateList);
							return;
						}
						break;
					case TIME:
						List<T_Resource> timeList=Dao_Resource
								.getAll_Time(callDefault.index++);
						if(DataValidate.checkDataValid(timeList)){
							callDefault.onInit(timeList);
							return;
						}
						break;
					default:
						break;
					}
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					P.v("��Դ���ݿ����:"+e.getMessage());
				}
				callDefault.onFail();
			}

			@Override
			public void end() {
				callDefault.onFinally();
			}
		}, true);
	}

	private void callLoad_Type() {
		ifInit=false;
		new RunnableService(new runCallBack() {

			@Override
			public void start() {
				try {
					switch (enumResType) {
					case DEFAULT:
						callLoad_Default(false);
						return;
					case FREQUENCE:
						List<T_Resource> freqList=Dao_Resource
						.getAll_Freq(callDefault.index++);
						if(DataValidate.checkDataValid(freqList)){
							callDefault.onLoadMore(freqList);
							return;
						}
						
						break;
					case RELA_STRENGTH:
						List<T_Resource> relateList=Dao_Resource
						.getAll_Relate(callDefault.index++);
						if(DataValidate.checkDataValid(relateList)){
							callDefault.onLoadMore(relateList);
							return;
						}
						
						break;
					case TIME:
						List<T_Resource> timeList=Dao_Resource
						.getAll_Time(callDefault.index++);
						if(DataValidate.checkDataValid(timeList)){
							callDefault.onLoadMore(timeList);
							return;
						}
						break;
					default:
						break;
					}
					return;
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				callDefault.onFail();
			}

			@Override
			public void end() {
				callDefault.onFinally();
			}
		}, true);
	}

	/** *********************������Դ��Դ********************* **/
//	private callBack_Res callSource;
	private Enum_ResSource enumResSource;

//	/**
//	 * �����ĵ���Դ�趨��Դ���еĲ����ص�
//	 * 
//	 * @param call
//	 */
//	public DBReq_Resource setCallBack_Source(callBack_Res call,
//			Enum_ResSource enumResSource) {
//		this.callDefault = call;
//		this.enumResSource = enumResSource;
//		return this;
//	}
	public DBReq_Resource setEnum_Source(Enum_ResSource enumSource){
		this.enumResSource=enumSource;
		ifType=false;
		return this;
	}

	private void callInit_Source() {
		callDefault.index = 0;
		ifInit=true;
		new RunnableService(new runCallBack() {

			@Override
			public void start() {
				try {
					switch (enumResSource) {
					case SOURCE_CLIENT:
						List<T_Resource> scList=Dao_Resource.getAll_Source(
								Enum_ResSource.SOURCE_CLIENT, callDefault.index++);
						if(DataValidate.checkDataValid(scList)){
							callDefault.onInit(scList);
							return;
						}
						
						break;
					case SOURCE_CONTACT:
						List<T_Resource> contactList=Dao_Resource
								.getAll_Source(Enum_ResSource.SOURCE_CONTACT,
										callDefault.index++);
						if(DataValidate.checkDataValid(contactList)){
							callDefault.onInit(contactList);
							return;
						}
						
						break;
					case SOURCE_NEWFRIEND:
						List<T_Resource> newfriList=Dao_Resource.getAll_Source(
								Enum_ResSource.SOURCE_NEWFRIEND,
								callDefault.index++);
						if(DataValidate.checkDataValid(newfriList)){
							callDefault.onInit(newfriList);
							return;
						}
						break;
					case SOURCE_RELA:
						List<T_Resource> relaList=Dao_Resource
								.getAll_Source(Enum_ResSource.SOURCE_RELA,
										callDefault.index++);
						if(DataValidate.checkDataValid(relaList)){
							callDefault.onInit(relaList);
							return;
						}
						break;
					case SOURCE_SUPPLIER:
						List<T_Resource> suppList=Dao_Resource.
						getAll_Source(Enum_ResSource.SOURCE_SUPPLIER,
								callDefault.index++);
						if(DataValidate.checkDataValid(suppList)){
							callDefault.onInit(suppList);
							return;
						}
						break;
					default:
						break;
					}
//					return;
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				callDefault.onFail();
			}

			@Override
			public void end() {
				callDefault.onFinally();
			}
		}, true);
	}
	
	private void callLoad_Source(){
		ifInit=false;
		new RunnableService(new runCallBack() {

			@Override
			public void start() {
				try {
					switch (enumResSource) {
					case SOURCE_CLIENT:
						List<T_Resource> scList=Dao_Resource.getAll_Source(
								Enum_ResSource.SOURCE_CLIENT, callDefault.index++);
						if(DataValidate.checkDataValid(scList)){
							callDefault.onLoadMore(scList);
							return;
						}
						
						break;
					case SOURCE_CONTACT:
						List<T_Resource> contactList=Dao_Resource
								.getAll_Source(Enum_ResSource.SOURCE_CONTACT,
										callDefault.index++);
						if(DataValidate.checkDataValid(contactList)){
							callDefault.onLoadMore(contactList);
							return;
						}
						
						break;
					case SOURCE_NEWFRIEND:
						List<T_Resource> newfriList=Dao_Resource.getAll_Source(
								Enum_ResSource.SOURCE_NEWFRIEND,
								callDefault.index++);
						if(DataValidate.checkDataValid(newfriList)){
							callDefault.onLoadMore(newfriList);
							return;
						}
						break;
					case SOURCE_RELA:
						List<T_Resource> relaList=Dao_Resource
								.getAll_Source(Enum_ResSource.SOURCE_RELA,
										callDefault.index++);
						if(DataValidate.checkDataValid(relaList)){
							callDefault.onLoadMore(relaList);
							return;
						}
						break;
					case SOURCE_SUPPLIER:
						List<T_Resource> suppList=Dao_Resource.
						getAll_Source(Enum_ResSource.SOURCE_SUPPLIER,
								callDefault.index++);
						if(DataValidate.checkDataValid(suppList)){
							callDefault.onLoadMore(suppList);
							return;
						}
						break;
					default:
						break;
					}
//					return;
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				callDefault.onFail();
			}

			@Override
			public void end() {
				callDefault.onFinally();
			}
		}, true);
	}

}
