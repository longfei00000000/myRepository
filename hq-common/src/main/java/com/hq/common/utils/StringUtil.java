package com.hq.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	private static final String CHAR_ARRAY="aqwertyuiopsdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
	private static final String NUM="1234567890";
	
	
	//判断源字符串是否有值，空引号也算没值
	// nul》false   ""》false
	public static boolean isEmpty(String str) {
		return str!=null && !"".equals(str);
	}
	
	//	判断源字符串是否有值，空引号和空格也算没值
	public static boolean isEmptyAndSpace(String str) {
		return str!=null && !"".equals(str.trim());
	}
	
	//判断是否为手机号码
	public static boolean isPhone(String phone) {
		//  正则  
		String reg_phone="^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
		// 验证正则
		//1:
//		Pattern pattern=Pattern.compile(reg_phone);
//		Matcher matcher = pattern.matcher(phone);
//		boolean matches = matcher.matches();
//		return matches;
		//2:
//		boolean matches2 = Pattern.matches(reg_phone, phone);
		//3:
		boolean matches = phone.matches(reg_phone);
		return matches;
	}
	
	//	判断是否为电子邮箱
	public static boolean isEmail(String email) {
		//  正则模式 种类：贪婪  独占   懒惰
//		String reg_email="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		String reg_email="^\\w+@\\w+(.com.cn|.com|.net)$";
		return email.matches(reg_email);
	}
	
	//	判断是否全部为字母
	public static boolean isCode(String str) {
		String zz="^[a-zA-Z]+$";
		if(isEmptyAndSpace(str)) {
			return str.matches(zz);
		}
		return false;
	}
	
	//获取n位随机英文字符串
	public static String getCode(int length) {
		// 随机数
		//   (int)(Math.random()*(最大值-最小值+1)+最小值)
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<length;i++) {
			int index=(int)(Math.random()*CHAR_ARRAY.length());
			char c = CHAR_ARRAY.charAt(index);
			sb.append(c);
		}
		return sb.toString();
	}
	
	//	获取n位随机英文和数字字符串
	public static String getCodeAndNum(int length) {
		// 随机数
		//   (int)(Math.random()*(最大值-最小值+1)+最小值)
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<length;i++) {
			int index=(int)(Math.random()*(CHAR_ARRAY+NUM).length());
			char c = (CHAR_ARRAY+NUM).charAt(index);
			sb.append(c);
		}
		return sb.toString();
	}
	
	//	获取n个随机中文字符串4e00--9fa5
	public static String getCn(int length) {
		// 随机数
		//   (int)(Math.random()*(最大值-最小值+1)+最小值)
		StringBuffer sb=new StringBuffer();
		int start = Integer.parseInt("4e00",16);
		int end = Integer.parseInt("9fa5",16);
		
		for(int i=0;i<length;i++) {
			int num=(int)((Math.random()*(end-start+1)+start));
			sb.append((char)num);
		}
		return sb.toString();
	}
	//	获取n个随机中文字符串4e00--9fa5
	public static boolean isHttpUrl(String length) {
		
		return true;
	}
}
