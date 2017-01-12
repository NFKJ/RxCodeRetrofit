package com.common.okhttp.RequestBeans;

import java.io.Serializable;

import com.common.okhttp.enums.Enum;
import java.util.ArrayList;


public class HVCheckTicketRequestBean implements Serializable { 

	//订单Id
	private String orderId;

	//订单类型 1场地 2产品
	private int itemType;

	//场地子订单id
	private ArrayList<String> itemOrderIdList;


	public void setOrderId(String value) { orderId  = value; } 
	public String getOrderId() { return orderId ; } 

	public void setItemType(int value) { itemType  = value; } 
	public Enum.EnumItemType getItemType() { return Enum.EnumItemType.valueOf( itemType ); } 

	public void setItemOrderIdList(ArrayList<String> value) { itemOrderIdList  = value; } 
	public ArrayList<String> getItemOrderIdList() { return itemOrderIdList ; } 


}