package com.yishang.D.service.httpRequest;

import java.util.ArrayList;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.format.utils.DataValidate;
import com.thread.RunnableService;
import com.thread.callBack.runCallBack;
import com.yishang.A.global.Enum.Enum_ListLimit;
import com.yishang.A.global.application.T_UserPoint;
import com.yishang.D.service.GetLocPointService;
import com.yishang.D.service.GetLocPointService.CallBack_Loc;

/**
 * 获取同城的用户
 * @author MM_Zerui
 *
 */
public class HttpReq_GetAtCity {
	private CallBack_City callBack;
	public HttpReq_GetAtCity(final CallBack_City callBack) {
		this.callBack=callBack;
		methodRun();
	}
	/**
	 * 获取信息的方法
	 */
	public void methodRun(){
		new GetLocPointService(new CallBack_Loc(){

			@Override
			public void getLocPoint(final double longitude, final double latitude,
					final String city) {
				// TODO Auto-generated method stub
				new RunnableService(new runCallBack() {
					
					@Override
					public void start() {
						// TODO Auto-generated method stub
//						AVGeoPoint point = new AVGeoPoint(latitude, longitude);
						AVQuery<T_UserPoint> query = AVObject.getQuery(T_UserPoint.class)
								.setLimit(Enum_ListLimit.NEARBY_USER.value());
						query.whereEqualTo("locateCity", city);
						try {
							ArrayList<T_UserPoint> list = (ArrayList<T_UserPoint>) query.find();
							if(DataValidate.checkDataValid(list)){
								callBack.onSuccess(list);
								return;
							}
							
						} catch (AVException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						callBack.onFail();
					}
					
					@Override
					public void end() {
						// TODO Auto-generated method stub
						
					}
				}, true);
				
			}

			@Override
			public void onFail() {
				// TODO Auto-generated method stub
				callBack.onFail();
			}
		
		});
		
	}
	public static abstract class CallBack_City{
		public abstract void onSuccess(ArrayList<T_UserPoint> list);
		public abstract void onFail();
		public abstract void onFinally();
	}
}
