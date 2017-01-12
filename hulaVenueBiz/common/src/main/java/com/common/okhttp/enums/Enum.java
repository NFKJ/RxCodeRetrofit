package com.common.okhttp.enums;

public class Enum
{
	public enum EnumPlatform 
	{
		None(0),
		Ios(1),
		Android(2);

		public int value;

		private EnumPlatform(int var) 
		{
			this.value = var;
		}

		public static  EnumPlatform valueOf(int var) 
		{
			switch(var)
			{
				case 0:
					return None;
				case 1:
					return Ios;
				case 2:
					return Android;
				default:
					assert(false);
			}
			return null;
		}

	};

	public enum EnumVersion 
	{
		Newest(0),	//最新
		Force(1),	// 强制升级
		Prompt(2),	//提示升级/
		None(3);	//还没上线的版本

		public int value;

		private EnumVersion(int var) 
		{
			this.value = var;
		}

		public static  EnumVersion valueOf(int var) 
		{
			switch(var)
			{
				case 0:
					return Newest;
				case 1:
					return Force;
				case 2:
					return Prompt;
				case 3:
					return None;
				default:
					assert(false);
			}
			return null;
		}

	};

	public enum EnumMessageType 
	{
		System(1),	//系统
		Order(2);	//订单

		public int value;

		private EnumMessageType(int var) 
		{
			this.value = var;
		}

		public static  EnumMessageType valueOf(int var) 
		{
			switch(var)
			{
				case 1:
					return System;
				case 2:
					return Order;
				default:
					assert(false);
			}
			return null;
		}

	};

	public enum EnumItemType 
	{
		Field(1),	//场地
		Product(2);	//产品

		public int value;

		private EnumItemType(int var) 
		{
			this.value = var;
		}

		public static  EnumItemType valueOf(int var) 
		{
			switch(var)
			{
				case 1:
					return Field;
				case 2:
					return Product;
				default:
					assert(false);
			}
			return null;
		}

	};

	public enum EnumOrderState 
	{
		Nopay(1),	//待支付
		Paid(2),	// 已支付
		Used(3),	// 已使用
		Refunding(4),	// 退款中
		Refund(5),	// 已退款
		Cancel(6);	//已取消

		public int value;

		private EnumOrderState(int var) 
		{
			this.value = var;
		}

		public static  EnumOrderState valueOf(int var) 
		{
			switch(var)
			{
				case 1:
					return Nopay;
				case 2:
					return Paid;
				case 3:
					return Used;
				case 4:
					return Refunding;
				case 5:
					return Refund;
				case 6:
					return Cancel;
				default:
					assert(false);
			}
			return null;
		}

	};

}
