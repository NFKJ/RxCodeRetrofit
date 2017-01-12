package com.common.okhttp.beans;

import java.io.Serializable;

import java.util.ArrayList;


public class HVVenueCategoryBean implements Serializable { 

	//运动项目编号
	private String categoryId;

	//运动项目名称
	private String categoryName;


	public void setCategoryId(String value) { categoryId  = value; } 
	public String getCategoryId() { return categoryId ; } 

	public void setCategoryName(String value) { categoryName  = value; } 
	public String getCategoryName() { return categoryName ; } 


}