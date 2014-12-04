package com.yishang.E.view;


import com.ruifeng.yishang.R;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.StateListDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class TabView extends FrameLayout {
	ImageView imageView;

	public TabView(Context c, int drawable, int drawableselec) {
		super(c);
		
//		imageView = new ImageView(c);
		
//		imageView.setBackgroundColor(Color.TRANSPARENT);
//		imageView.setPadding(15,15,15,15);
//		imageView.setm
		View view=inflate(c, R.layout.tab_icon_view, null);
		imageView=(ImageView)view.findViewById(R.id.tab_icon);
		StateListDrawable listDrawable = new StateListDrawable();
		listDrawable.addState(SELECTED_STATE_SET, this.getResources()
				.getDrawable(drawableselec));
		listDrawable.addState(ENABLED_STATE_SET, this.getResources()
				.getDrawable(drawable));
		imageView.setImageDrawable(listDrawable);
		addView(view);
//		setGravity(Gravity.CENTER);
//		addView(imageView);
	}
}
