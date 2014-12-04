package com.yishang.E.view;

import com.ruifeng.yishang.R;

import android.content.Context;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;



/**
 * 底部TabBar每个项的布局视图
 * @author MM_Zerui
 *
 */
public class TabbarItemView extends FrameLayout{
	ImageView imageView;
	TextView textView;
	RelativeLayout tabbarItem;
	public TabbarItemView(Context c, String text,int drawable, int drawableselec){
		super(c);
		View view=inflate(c, R.layout.tabbar_item_layout, null);
		
		imageView=(ImageView)view.findViewById(R.id.tabbar_icon);
		textView=(TextView)view.findViewById(R.id.tabbar_text);
		tabbarItem=(RelativeLayout)view.findViewById(R.id.tabbar_item);
		StateListDrawable listDrawable = new StateListDrawable();
		listDrawable.addState(SELECTED_STATE_SET, this.getResources()
				.getDrawable(drawableselec));
		listDrawable.addState(ENABLED_STATE_SET, this.getResources()
				.getDrawable(drawable));
		
		StateListDrawable listDrawable2 = new StateListDrawable();
		listDrawable2.addState(SELECTED_STATE_SET, this.getResources()
				.getDrawable(R.drawable.shape_tabbar_item_bg_press));
		listDrawable2.addState(ENABLED_STATE_SET, this.getResources()
				.getDrawable(R.drawable.shape_tabbar_item_bg_normal));
		imageView.setImageDrawable(listDrawable);
		tabbarItem.setBackgroundDrawable(listDrawable2);
		textView.setText(text);
		addView(view);
	}
}