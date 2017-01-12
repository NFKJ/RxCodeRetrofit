package com.common.okhttp.RequestBeans;

import java.io.Serializable;

import java.util.ArrayList;


public class HVModifyVenueNoticeRequestBean implements Serializable { 

	//
	private String noticeContent;


	public void setNoticeContent(String value) { noticeContent  = value; } 
	public String getNoticeContent() { return noticeContent ; } 


}