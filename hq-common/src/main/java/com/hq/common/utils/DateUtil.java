package com.hq.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

// 日期工具类
public class DateUtil {
	//  有java.util.date（日期）  相互转换为java.util.Calendar（日历）
	//1:date 转  Calendar
//	Date date = new Date();
//	Calendar instance2 = Calendar.getInstance();
//	instance2.setTime(date);
	// 可以使用instance2 对象做为date的转换对象
	
	// 2:Calendar转换为 Date
//	 Calendar cal=Calendar.getInstance();  
//	 Date date=cal.getTime();  
	// 可以使用date 对象作为 cal 对象的转换对象
	
	// 根据日期算年龄
	public static int getAge(Calendar target) {
		
		// 1获取当前时间的年月 日
		int age = 0;
		// 通过调用方法完成实例化操作 (获取当前日期)
		Calendar instance = Calendar.getInstance();
		// 当我传入的日期 是当前日期之后的日期
		if (instance.before(target)) {
			throw new IllegalArgumentException("参数不正确");
		}
		// 获取日期的 年
		int day_year = instance.get(Calendar.YEAR);
		// 获取日期的月
		int day_Month = instance.get(Calendar.MONTH);
		// 获取日期中   每月的第几天
		int day_day = instance.get(Calendar.DAY_OF_MONTH);
		// 2获取 target 日期的年月 日
		int target_year = target.get(Calendar.YEAR);
		int target_Month = target.get(Calendar.MONTH);
		int target_day = target.get(Calendar.DAY_OF_MONTH);
		// 当前日期- 目标日期  （day 系统     target 目标）
		age = day_year - target_year;
		if (day_Month <= target_Month) {
			if (day_Month == target_Month) {
				if (day_day<target_day) {
					age--;
				}
			}
			age--;
		}
		return age + 1;
	}
	
	// 求未来日期离今天还剩的天数
	public static int getFutrueDays(Calendar target) {
		// 将未来日期与当前日期都转换为  毫秒计算   毫秒相减  转换为  日   /1000秒 /60分钟/60小时/24 天
		int day = 0;
		// 获取当前日期；  毫秒数
		long time = Calendar.getInstance().getTime().getTime();
		//  参数日期的毫秒数
		long time2 = target.getTime().getTime();
		if (time2 - time < 0) {
			throw new IllegalArgumentException("参数不合法异常");
		} else {
			day = (int) ((time2 - time) / 1000 / 60 / 60 / 24);
		}
		return day;
	}
	
	
	//求过去日期离今天还剩的天数
	public static int getPastDays(Calendar target) {
		int day = 0;
		// 获取当前日期；  毫秒数
		long time = Calendar.getInstance().getTime().getTime();
		//  参数日期的毫秒数
		long time2 = target.getTime().getTime();
		if (time - time2 < 0) {
			throw new IllegalArgumentException("参数不合法异常");
		} else {
			day = (int) ((time - time2) / 1000 / 60 / 60 / 24);
		}
		return day;
	}
	
	// 判断给定的日期是否为今天
	public static boolean isInThisDay(Calendar target) {
		boolean result = false;
		if (target == null)
			return false;
		// 获取当前日期的
		Calendar toDay = Calendar.getInstance();
		// 先比对年分
		if (toDay.get(Calendar.YEAR) == target.get(Calendar.YEAR)) {
			// 比对月份
			if (toDay.get(Calendar.MONTH) == target.get(Calendar.MONTH)) {
				// 比对日
				if (toDay.get(Calendar.DAY_OF_MONTH) == target.get(Calendar.DAY_OF_MONTH)) {
					return true;
				}
			}
		}
		return result;
	}
	
	// 判断选择的日期是否是本月
	public static boolean isInThisMonth(Calendar target) {
		boolean result = false;
		if (target == null) return false;
		Calendar toDay = Calendar.getInstance();
		if (toDay.get(Calendar.YEAR) == target.get(Calendar.YEAR)) {
			if (toDay.get(Calendar.MONTH)== target.get(Calendar.MONTH)) {
					return true;
			}
		}
		return result;
	}
	
	// 判断给定的日期是否在本周之内     先过周日
	public static boolean isInThisWeek(Calendar target) {
		boolean result = false;
		if (target == null)
			return false;
		// 获取当前日期的
		Calendar toDay = Calendar.getInstance();
		// week_of_year 比对  年中的周  
		if (toDay.get(Calendar.WEEK_OF_YEAR) == target.get(Calendar.WEEK_OF_YEAR)) {
			return true;
		}
		return result;
	}
	
	
//	给定时间对象，初始化到该月初的1月1日0时0分0秒0毫秒
	public static void initDateTime(Calendar target) {
		// 把target 对象的日  时  分  秒   毫秒  重置
		target.set(Calendar.DAY_OF_MONTH, 1);  //  日为1
		target.set(Calendar.HOUR, 0);  // 时 0
		target.set(Calendar.MINUTE, 0); // 分 0
		target.set(Calendar.SECOND, 0); // 秒 0
		target.set(Calendar.MILLISECOND, 0); // 毫秒 0
	}

	// 判断是否为闰年
	private static boolean isLeapYear(int year) {
		// 闰年规则   年数能被4整除并且不能为 100整除 为闰年
		// 400 的倍数为闰年
		return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
	}
	
	//时间比较    日期比较 compareTo
	public static int compareDate(Date date1,Date date2) {
		// compareTo 是日期的比较器    直接使用即可
		return date1.compareTo(date2);
		// 大于 1  小于-1 
	}
	
	//给定时间对象，设定到该月最后一天的23时59分59秒999毫秒
	public static void setToLastDateOfMonth(Calendar target) {
		// 月： 30    31    28/29
		int targetMonth = target.get(Calendar.MONTH+1);
		// 找到2月
		if(targetMonth == 2) {
			// 判断是否为闰月
			if (isLeapYear(target.get(Calendar.YEAR))) {
				target.set(Calendar.DATE, 29);
			} else {
				target.set(Calendar.DATE, 28);
			}
		// 大月份赋值
		} else {
			switch(targetMonth) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				target.set(Calendar.DATE, 31);
				break;
			default: // 小月份赋值
				target.set(Calendar.DATE, 30);
				break;
			}
		}
						// HOUR  12小时制   HOUR_OF_DAY 24小时制
		target.set(Calendar.HOUR_OF_DAY,23);  // 设置 23点
		target.set(Calendar.MINUTE,59); // 设置59分
		target.set(Calendar.SECOND,59); //是指59秒
		target.set(Calendar.MILLISECOND, 999); // 设置毫秒
	}
	
	
	public static Date getDate(String date,String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		Date parse = null;
			try {
				parse=simpleDateFormat.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return parse;
	}
	
	// 给定天数  计算随机的日期
	public static Date getRoundTime(int day) {
		// 得到当前日期然后将随机的天数+当前日期的天数  返回一个新的日期
		// 1：将参数的日期转换为毫秒
		int times=Math.abs(day*24*60*60*1000);
		Date date = new Date(); // 获取当前日期
//		System.out.println(date);
		long time = date.getTime();         // 获取当前日期的毫秒值
		long newTime=time+times; // 新的日期的毫秒值
		System.out.println(time+"=="+times+"=="+newTime);
		return new Date(newTime);
	}
	
}
