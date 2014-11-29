package com.yishang.A.global.baseClass;

import com.lidroid.xutils.db.annotation.Column;

public class BaseEntity {
	@Column(column="firstLetter")
	private String firstLetter;

	public String getFirstLetter() {
		return firstLetter;
	}

	public void setFirstLetter(String comLetter) {
		this.firstLetter = comLetter;
	}
	
}
