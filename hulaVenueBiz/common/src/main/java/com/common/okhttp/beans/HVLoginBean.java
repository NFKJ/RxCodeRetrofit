package com.common.okhttp.beans;

import java.io.Serializable;

import java.util.ArrayList;


public class HVLoginBean implements Serializable { 

	//
	private String token;

	//
	private HVVenueInfoBean venueInfo;


	public void setToken(String value) { token  = value; } 
	public String getToken() { return token ; } 

	public void setVenueInfo(HVVenueInfoBean value) { venueInfo  = value; } 
	public HVVenueInfoBean getVenueInfo() { return venueInfo ; } 


}