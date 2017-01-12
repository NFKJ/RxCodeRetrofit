package com.common.okhttp.beans;

import java.io.Serializable;

import com.common.okhttp.enums.Enum;
import java.util.ArrayList;


public class HVProductOrderBean implements Serializable {

	//产品子订单编号
	private String productOrderId;

	//产品名称
	private String productSubjectName;

	//产品规格
	private String productName;

	//数量
	private Integer total;

	//订单状态
	private int productOrderState;


	public void setProductOrderId(String value) { productOrderId  = value; }
	public String getProductOrderId() { return productOrderId ; }

	public void setProductSubjectName(String value) { productSubjectName  = value; }
	public String getProductSubjectName() { return productSubjectName ; }

	public void setProductName(String value) { productName  = value; }
	public String getProductName() { return productName ; }

	public void setTotal(Integer value) { total  = value; }
	public Integer getTotal() { return total ; }

	public void setProductOrderState(int value) { productOrderState  = value; }
	public Enum.EnumOrderState getProductOrderState() { return Enum.EnumOrderState.valueOf( productOrderState ); }


}