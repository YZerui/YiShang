package com.yishang.E.view;


import com.ruifeng.yishang.R;
import com.yishang.E.view.dialog.Effectstype;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 自定义的进度�?
 * 
 * @author MM_Zerui
 */
public class ProgressDialog extends Dialog {

	private Window window = null;
	private View view;
	private RelativeLayout bgLayout;
	private ProgressWheel progressWheel;
	private ProgressBar progressCircle,progressSector;
	private TextView progressNote;
	
	
	private static int mOrientation = 1;

	private Effectstype animatType = null;

	private int AnimatDuration = 300;

	public ProgressDialog(Context context) {
		super(context, R.style.dialog_untran);
		int ort = context.getResources().getConfiguration().orientation;
		if (mOrientation != ort) {
			mOrientation = ort;
		}
		initView(context);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		WindowManager.LayoutParams params = getWindow().getAttributes();
		params.height = ViewGroup.LayoutParams.MATCH_PARENT;
		params.width = ViewGroup.LayoutParams.MATCH_PARENT;
		getWindow().setAttributes(
				(android.view.WindowManager.LayoutParams) params);

	}

	private void initView(Context context) {
		// TODO Auto-generated method stub
		windowDeploy();
		view = View.inflate(context, R.layout.custom_dialog_progress, null);
		setContentView(view);
		
		progressWheel=(ProgressWheel)view.findViewById(R.id.bar_Wheel);
		progressCircle=(ProgressBar)view.findViewById(R.id.bar_circel);
		progressSector=(ProgressBar)view.findViewById(R.id.bar_Sector);
		progressNote=(TextView)view.findViewById(R.id.progressText);
		bgLayout=(RelativeLayout)view.findViewById(R.id.backLayout);
	}
	private void hideView(){
		progressCircle.setVisibility(View.INVISIBLE);
		progressWheel.setVisibility(View.INVISIBLE);
		progressSector.setVisibility(View.INVISIBLE);
		progressNote.setVisibility(View.INVISIBLE);
	}
	public ProgressDialog setBg(int color){
		bgLayout.setBackgroundColor(color);
		return this;
	}
	public ProgressDialog withWheel(){
//		hideView();
		progressWheel.setVisibility(View.VISIBLE);
		return this;
	}
	public ProgressDialog withCircel(){
//		hideView();
		progressCircle.setVisibility(View.VISIBLE);
		return this;
	}
	public ProgressDialog withSector(){
//		hideView();
		progressSector.setVisibility(View.VISIBLE);
		return this;
	}
	public ProgressDialog withNote(String note){
//		hideView();
		progressNote.setVisibility(View.VISIBLE);
		progressNote.setText(note);
		return this;
	}
	public ProgressDialog setNoteColor(int color){
		progressNote.setTextColor(color);
		return this;
	}

	public void windowDeploy() {
		window = getWindow(); // 得到对话�?
		window.requestFeature(Window.FEATURE_NO_TITLE);
		window.addFlags(WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW);
		setCanceledOnTouchOutside(false);

	}

	@Override
	public void show() {

		super.show();
	}

	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		hideView();
		super.dismiss();
	}

}
