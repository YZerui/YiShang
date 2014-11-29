package com.yishang.D.service.httpRequest;

import java.util.ArrayList;



import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVGeoPoint;
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
 * 获取附近的人(距离当前所在位置的前50位朋友)
 * 
 * @author MM_Zerui
 * 
 */
public class HttpReq_GetNearby {
	private CallBack_Nearby callBack;
	public HttpReq_GetNearby(CallBack_Nearby callBack) {
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
					String city) {
				new RunnableService(new runCallBack() {
					
					@Override
					public void start() {
						// TODO Auto-generated method stub
						AVGeoPoint point = new AVGeoPoint(latitude,longitude);
						AVQuery<T_UserPoint> query = AVObject.getQuery(T_UserPoint.class);
						query.whereNear("locatePoint", point);
						try {
							ArrayList<T_UserPoint> list=(ArrayList<T_UserPoint>) query.find();
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
	public static abstract class CallBack_Nearby{
		public abstract void onSuccess(ArrayList<T_UserPoint> list);
		public abstract void onFail();
		public abstract void onFinally();
	}
}
