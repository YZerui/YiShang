package com.yishang.A.global.baseClass;


import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class SuperAdapter extends ArrayAdapter<Object>{
	protected ImageLoader imageLoader;
	protected LayoutInflater inflater;
	protected DisplayImageOptions loadOptions;
	public SuperAdapter() {								
		imageLoader = ImageLoader.getInstance();
	}

}
