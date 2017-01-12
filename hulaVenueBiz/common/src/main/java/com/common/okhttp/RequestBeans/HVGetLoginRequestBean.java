package com.common.okhttp.RequestBeans;

import java.io.Serializable;

import java.util.ArrayList;


public class HVGetLoginRequestBean implements Serializable { 

	//账号
	private String username;

	//密码
	private String password;


	public void setUsername(String value) { username  = value; } 
	public String getUsername() { return username ; } 

	public void setPassword(String value) { password  = value; } 
	public String getPassword() { return password ; } 


}