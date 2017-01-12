package com.common.okhttp.RequestBeans;

import java.io.Serializable;

import com.common.okhttp.enums.Enum;
import java.util.ArrayList;


public class HVGetMessageListRequestBean implements Serializable { 

	//
	private int messageType;

	//
	private int pageIndex;

	//
	private int pageSize;


	public void setMessageType(int value) { messageType  = value; } 
	public Enum.EnumMessageType getMessageType() { return Enum.EnumMessageType.valueOf( messageType ); } 

	public void setPageIndex(int value) { pageIndex  = value; } 
	public int getPageIndex() { return pageIndex ; } 

	public void setPageSize(int value) { pageSize  = value; } 
	public int getPageSize() { return pageSize ; } 


}