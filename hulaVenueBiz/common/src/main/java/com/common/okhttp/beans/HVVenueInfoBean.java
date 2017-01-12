package com.common.okhttp.beans;

import java.io.Serializable;

import java.util.ArrayList;


public class HVVenueInfoBean implements Serializable { 

	//场馆id
	private String venueId;

	//场馆名称
	private String venueName;

	//缩略图
	private String thumbnailUrl;

	//地址
	private String address;

	//营业时间
	private String openTime;

	//电话
	private String phone;

	//公告
	private String noticeContent;

	//介绍
	private String introduce;

	//场馆服务
	private ArrayList<HVVenueServiceBean> viewVenueServiceList;

	//场馆项目
	private ArrayList<HVVenueCategoryBean> viewVenueCategoryList;


	public void setVenueId(String value) { venueId  = value; } 
	public String getVenueId() { return venueId ; } 

	public void setVenueName(String value) { venueName  = value; } 
	public String getVenueName() { return venueName ; } 

	public void setThumbnailUrl(String value) { thumbnailUrl  = value; } 
	public String getThumbnailUrl() { return thumbnailUrl ; } 

	public void setAddress(String value) { address  = value; } 
	public String getAddress() { return address ; } 

	public void setOpenTime(String value) { openTime  = value; } 
	public String getOpenTime() { return openTime ; } 

	public void setPhone(String value) { phone  = value; } 
	public String getPhone() { return phone ; } 

	public void setNoticeContent(String value) { noticeContent  = value; } 
	public String getNoticeContent() { return noticeContent ; } 

	public void setIntroduce(String value) { introduce  = value; } 
	public String getIntroduce() { return introduce ; } 

	public void setViewVenueServiceList(ArrayList<HVVenueServiceBean> value) { viewVenueServiceList  = value; } 
	public ArrayList<HVVenueServiceBean> getViewVenueServiceList() { return viewVenueServiceList ; } 

	public void setViewVenueCategoryList(ArrayList<HVVenueCategoryBean> value) { viewVenueCategoryList  = value; } 
	public ArrayList<HVVenueCategoryBean> getViewVenueCategoryList() { return viewVenueCategoryList ; } 


}