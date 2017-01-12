package com.common.okhttp.beans;

import java.io.Serializable;

import java.util.ArrayList;


public class HVVenueServiceBean implements Serializable { 

	//场馆五福图标宽度
	private int serviceIconSize;

	//场馆服务图标地址
	private String serviceIconUrl;


	public void setServiceIconSize(int value) { serviceIconSize  = value; } 
	public int getServiceIconSize() { return serviceIconSize ; } 

	public void setServiceIconUrl(String value) { serviceIconUrl  = value; } 
	public String getServiceIconUrl() { return serviceIconUrl ; } 


}