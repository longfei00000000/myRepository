package com.hq.common.utils;

import java.io.File;
import java.math.BigDecimal;

public class FileUtil {
	private FileUtil() {}
	
	// 思路： 获取文件 的名称   找到最后一个. 以此为截取位置 获取后缀
	//获取文件扩展名
	public static String getExtensions(String fileName) {
		//  从后向前第一次出现的位置  lastIndexOf
		int lastIndexOf = fileName.lastIndexOf(".");
		String substring = fileName.substring(lastIndexOf);
		return substring;
	}
	
	
	//删除文件，如果是目录，则下面的文件和所有子目录中的文件都要删除
	// java高级   查找文件  如果是文件夹（目录）  进入目录继续查找----》递归
	public static void deleteFile(File f){
		// 判断file 是否为文件  如果为文件删除
		if(f.isFile()) {
			f.delete();
		}else {
			// 如果不是文件那一定是 文件夹 （目录）
			File [] b = f.listFiles();//获取包含file对象对应的子目录或者文件
	        for(int i =0;i<b.length;i++){
	            if(b[i].isFile()){//判断是否为文件
	                b[i].delete();//如果是就删除
	            }else{
	            	deleteFile(b[i]);//否则重新递归到方法中
	            }
	        }
	        f.delete();//最后删除该目录中所有文件后就删除该目录
		}
    }  
	
	//获取操作系统用户目录   固定：user.home  ==》 当前用户的 目录
	public static File getUserHome() {
		String property = System.getProperty("user.home");
		// 将字符串类型的 名称转换为  file
		File file = new File(property);
		return file;
	}
	
	// 返回文件大小的(目录不适用)
	// BigInteger   BigDecimal
	//BigInteger在其底层中 用到了 BigDecimal 
	//此类是 Integer的高位计数 精度相对高一些   适用范围 高精度的需求（金融）
	public static String getFormatSize(File file) {
		
		if(file.isFile()) {
//			System.out.println(file.length());
			double kiloByte = file.length()/1024;
			if(kiloByte < 1) {
				return file.length() + "Byte(s)";
			}
			
			//  MB
			double megaByte = kiloByte/1024;
			if(megaByte < 1) {
				// 获取高精度的  浮点型数据
				
				BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
				return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
			}
			
			double gigaByte = megaByte/1024;
			if(gigaByte < 1) {
				BigDecimal result2  = new BigDecimal(Double.toString(megaByte));
				return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
								// 保留2位小数		四舍五入（平常用的数学运算）
			}
			
			double teraBytes = gigaByte/1024;
			if(teraBytes < 1) {
				BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
				return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
			}
			BigDecimal result4 = new BigDecimal(teraBytes);
				return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
			}
		else {
			return file+"为目录";
		}
	}
	
	
}
