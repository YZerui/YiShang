package com.yishang.A.global.callBack;

/**
 * 列表请求事件回调
 * @author MM_Zerui
 *
 */
public abstract class listHttpCallBack {
	/**
	 * 完成列表的初始化
	 */
	public abstract void initListView();
	/**
//	 * 对初始获取的数据进行列表的初始化工作
//	 */
//	public abstract void initResult();
	/**
	 * 列表下拉刷新的事件
	 */
	public abstract void onRefresh();
	/**
	 * 列表上拉加载的事件
	 */
	public abstract void onLoadMore();
//	public abstract void onRefreshResult(boolean condition,Object obj);
//	public abstract void onLoadResult(boolean condition,Object obj);
}
