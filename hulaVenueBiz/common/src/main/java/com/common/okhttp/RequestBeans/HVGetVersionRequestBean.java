package com.common.okhttp.RequestBeans;

import java.io.Serializable;

import com.common.okhttp.enums.Enum;
import java.util.ArrayList;


public class HVGetVersionRequestBean implements Serializable { 

	//
	private String version;

	//
	private String channel;

	//
	private int platform;


	public void setVersion(String value) { version  = value; } 
	public String getVersion() { return version ; } 

	public void setChannel(String value) { channel  = value; } 
	public String getChannel() { return channel ; } 

	public void setPlatform(int value) { platform  = value; } 
	public Enum.EnumPlatform getPlatform() { return Enum.EnumPlatform.valueOf( platform ); } 


}