package com.qjkj.qjcsp.util;

import java.util.regex.Pattern;

public class ValidateInteger {

	  public static boolean isInteger(String str) {    
		    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");    
		    return pattern.matcher(str).matches();    
		  }  
}
