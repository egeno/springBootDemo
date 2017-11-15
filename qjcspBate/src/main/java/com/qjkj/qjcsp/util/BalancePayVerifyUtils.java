package com.qjkj.qjcsp.util;
import java.util.regex.Pattern;
public class BalancePayVerifyUtils {
	
	public static Boolean balancePayVerifyUtils(String str){
		Pattern p=Pattern.compile("^\\d+(\\.\\d+)?$");
		
		
		return p.matcher(str).matches();
		
	} 
	public static void main(String[] args) {
		String str="0.011111";
		System.out.println(balancePayVerifyUtils(str));
	}

}
