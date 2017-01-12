package com.common.okhttp.beans;

import java.io.Serializable;

import com.common.okhttp.enums.Enum;
import java.util.ArrayList;


public class HVOrderInfoBean implements Serializable { 

	//订单号
	private String orderId;

	//场管编号
	private String venueId;

	//订单类型
	private int itemType;

	//场地子订单类别
	private ArrayList<HVItemOrderBean> itemOrderList;

	//场馆子订单列表
	private ArrayList<HVProductOrderBean> productOrderList;


	public void setOrderId(String value) { orderId  = value; } 
	public String getOrderId() { return orderId ; } 

	public void setVenueId(String value) { venueId  = value; } 
	public String getVenueId() { return venueId ; } 

	public void setItemType(int value) { itemType  = value; } 
	public Enum.EnumItemType getItemType() { return Enum.EnumItemType.valueOf( itemType ); } 

	public void setItemOrderList(ArrayList<HVItemOrderBean> value) { itemOrderList  = value; } 
	public ArrayList<HVItemOrderBean> getItemOrderList() { return itemOrderList ; } 

	public void setProductOrderList(ArrayList<HVProductOrderBean> value) { productOrderList  = value; } 
	public ArrayList<HVProductOrderBean> getProductOrderList() { return productOrderList ; } 


}