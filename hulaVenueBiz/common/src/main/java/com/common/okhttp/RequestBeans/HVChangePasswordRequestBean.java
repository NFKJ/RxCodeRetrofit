package com.common.okhttp.RequestBeans;

import java.io.Serializable;

import java.util.ArrayList;


public class HVChangePasswordRequestBean implements Serializable { 

	//新密码
	private String oldPassword;

	//
	private String replacePassword;


	public void setOldPassword(String value) { oldPassword  = value; } 
	public String getOldPassword() { return oldPassword ; } 

	public void setReplacePassword(String value) { replacePassword  = value; } 
	public String getReplacePassword() { return replacePassword ; } 


}