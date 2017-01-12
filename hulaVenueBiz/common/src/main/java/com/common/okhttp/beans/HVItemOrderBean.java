package com.common.okhttp.beans;

import java.io.Serializable;

import com.common.okhttp.enums.Enum;
import java.util.ArrayList;


public class HVItemOrderBean implements Serializable { 

	//子订单编号
	private String itemOrderId;

	//场地名称
	private String fieldName;

	//场次开始时间
	private Integer itemStartTime;

	//场次结束时间
	private Integer itemEndTime;

	//订单状态
	private int itemOrderState;


	public void setItemOrderId(String value) { itemOrderId  = value; } 
	public String getItemOrderId() { return itemOrderId ; } 

	public void setFieldName(String value) { fieldName  = value; } 
	public String getFieldName() { return fieldName ; } 

	public void setItemStartTime(Integer value) { itemStartTime  = value; } 
	public Integer getItemStartTime() { return itemStartTime ; } 

	public void setItemEndTime(Integer value) { itemEndTime  = value; } 
	public Integer getItemEndTime() { return itemEndTime ; } 

	public void setItemOrderState(int value) { itemOrderState  = value; } 
	public Enum.EnumOrderState getItemOrderState() { return Enum.EnumOrderState.valueOf( itemOrderState ); } 


}