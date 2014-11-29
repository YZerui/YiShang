package com.customview.model;

public class item {
	private int imgR;
	private String textStr;
	public item(String text,int img) {
		// TODO Auto-generated constructor stub
		this.imgR=img;
		this.textStr=text;
	}
	public int getImgR() {
		return imgR;
	}
	public void setImgR(int imgR) {
		this.imgR = imgR;
	}
	public String getTextStr() {
		return textStr;
	}
	public void setTextStr(String textStr) {
		this.textStr = textStr;
	}
}
