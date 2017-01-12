package com.common.okhttp.beans;

import java.io.Serializable;

import com.common.okhttp.enums.Enum;
import java.util.ArrayList;


public class HVMessageBean implements Serializable { 

	//消息标题
	private String title;

	//消息内容
	private String content;

	//消息时间
	private int createTime;

	//
	private int messageType;

	//
	private boolean isRead;


	public void setTitle(String value) { title  = value; } 
	public String getTitle() { return title ; } 

	public void setContent(String value) { content  = value; } 
	public String getContent() { return content ; } 

	public void setCreateTime(int value) { createTime  = value; } 
	public int getCreateTime() { return createTime ; } 

	public void setMessageType(int value) { messageType  = value; } 
	public Enum.EnumMessageType getMessageType() { return Enum.EnumMessageType.valueOf( messageType ); } 

	public void setIsRead(boolean value) { isRead  = value; } 
	public boolean getIsRead() { return isRead ; } 


}