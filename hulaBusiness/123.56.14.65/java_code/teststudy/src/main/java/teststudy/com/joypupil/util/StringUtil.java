package teststudy.com.joypupil.util;

import java.util.UUID;

public class StringUtil {
	
	public static boolean isEmpty(String str){
		return str == null || "".equals(str.trim());
	}
	
//	public static void main(String[] args){
//		System.out.println(UUID.randomUUID().toString().replace("-", ""));
//		System.out.println(System.currentTimeMillis());
//	}

}
