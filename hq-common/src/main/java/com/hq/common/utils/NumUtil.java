package com.hq.common.utils;

import java.math.BigDecimal;

public class NumUtil {
	
	
	// 获取百分数    小数取2位
	public static double getPercent(double num,double total) {
		/*
		 * BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
				return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
		 */
		BigDecimal bigDecimal = new BigDecimal(Double.toString(num/total*100));
		BigDecimal setScale = bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);
		return setScale.doubleValue();
	}
	
	// 是否为数字  （包括小数）
	public static boolean isNumber(String str) {
		String zz="^(\\-|\\+)?\\d+(\\.\\d+)?$";
		boolean matches = str.matches(zz);
		return matches;
		
	}
}
