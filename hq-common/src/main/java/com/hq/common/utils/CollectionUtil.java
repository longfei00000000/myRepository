package com.hq.common.utils;

import java.util.Collection;

public class CollectionUtil {

	
	//  判断集合是否为空
	// 泛型方法   
	public static <E>boolean isEmpty(Collection<E> str) {
		//1: null  2str.size()==0
		return  str!=null && str.size()!=0;
	}
}
