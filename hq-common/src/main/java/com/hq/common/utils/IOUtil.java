package com.hq.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class IOUtil {
	
	// 关流
	public static void closeStream(Closeable... closeAble) {
		for (Closeable closeable2 : closeAble) {
			try {
				closeable2.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//复制流
	public static void copyTo(InputStream input, 
			OutputStream output, 
			boolean needCloseInput, boolean needCloseOutput) throws IOException {
		
		if (input == null || output == null) return;
		// 输入流缓冲区
		BufferedInputStream bis = new BufferedInputStream(input);
		// 输出流缓冲区
		BufferedOutputStream bos = new BufferedOutputStream(output);
		// 数组存储数据流
		byte[] buffer = new byte[1024];
		
		// 从输入流读取  直到最后返回-1时结束读取
		while (bis.read(buffer) != -1) {
			// 将读取的输入流  写入 输出流中
			bos.write(buffer);
		}
		
		// 由于使用了缓冲输出流。如果没有关闭该 流，则必须flush，如果关闭了这个流，在关闭时会自动flush
		bos.flush();
		
		if (needCloseInput) {
			closeStream(input);
		}
		
		if (needCloseOutput) {
			closeStream(output);
		}
	}
	
	// 读取文本文件
	public static String readTextFile(File file) throws Exception {
		if (file == null) return null;
		StringBuffer sb = new StringBuffer();
		
		FileReader fileReader = new FileReader(file);
		BufferedReader br=new BufferedReader(fileReader);
//		String readLine = br.readLine();
		String readLine =null;
		while((readLine=br.readLine())!=null) {
			sb.append(readLine);
		}
		return sb.toString();
	}
	
	// 按行读取文本文件
	public static List<String> readTextFileByLine(File file) throws Exception {
		if (file == null) return null;
		List<String> list=new ArrayList<String>();
		FileReader fileReader = new FileReader(file);
		BufferedReader br=new BufferedReader(fileReader);
		String readLine = br.readLine();
		while(readLine!=null) {
			list.add(readLine);
			readLine = br.readLine();
		}
		return list;
	}
	
	//写入文本文件
	public static void  inFileWrite(String content,File file) throws IOException {
		OutputStream os=new FileOutputStream(file);
		byte[] bytes = content.getBytes();
		os.write(bytes, 0, bytes.length);
	}
	
	//网络文件下载
	public static void downloadFile(InputStream is,HttpServletResponse response,boolean closeInputStream,
			boolean closeResponse) throws IOException {
		OutputStream os = response.getOutputStream();
		//输入流缓冲区
		BufferedInputStream bis = new BufferedInputStream(is);
		// 输出流 缓冲区
		BufferedOutputStream bos = new BufferedOutputStream(os);
        byte[] buffer = new byte[1024];
        int bytesRead = bis.read(buffer);
        while (bytesRead != -1){ //重点
           bos.write(buffer, 0, bytesRead);// 将文件发送到客户端
           bytesRead=bis.read(buffer);
        }
        if(closeInputStream) {
        	closeStream(bis,is);
        }
        if(closeResponse) {
        	closeStream(bos,os);
        }
	}
}
