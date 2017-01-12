package com.common.okhttp.beans;

import java.io.Serializable;

import com.common.okhttp.enums.Enum;
import java.util.ArrayList;


public class HVVersionBean implements Serializable { 

	//版本类型枚举
	private int action;

	//跳转地址
	private String url;


	public void setAction(int value) { action  = value; } 
	public Enum.EnumVersion getAction() { return Enum.EnumVersion.valueOf( action ); } 

	public void setUrl(String value) { url  = value; } 
	public String getUrl() { return url ; } 


}