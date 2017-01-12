package com.common.retrofit.entity.resultImpl;

public class Enum
{
	public enum EnumPlatform 
	{
		None(0),
		Ios(1),
		Android(2);

		public int value;

		EnumPlatform(int var)
		{
			this.value = var;
		}

		public static EnumPlatform valueOf(int var)
		{
			switch(var)
			{
				case 1:
					return Ios;
				case 2:
					return Android;
				default:
					return None;
			}
		}
	}

	public enum EnumVersion 
	{
		Newest(0),	// 最新
		Force(1),	// 强制升级
		Prompt(2),	// 提示升级
		None(3);	// 还没上线的版本

		public int value;

		EnumVersion(int var)
		{
			this.value = var;
		}

		public static  EnumVersion valueOf(int var) 
		{
			switch(var)
			{
				case 1:
					return Force;
				case 2:
					return Prompt;
				case 3:
					return None;
				default:
					return Newest;
			}
		}
	}
}
