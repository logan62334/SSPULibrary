package com.sspu.library.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpDownloader {
	private URL url = null;
	
	
	public String download(String urlStr){
		StringBuffer sb = new StringBuffer();
		String line = null;
		BufferedReader bfr = null;
		try {
			url = new URL(urlStr);
			HttpURLConnection urlConn =(HttpURLConnection)url.openConnection();
			bfr = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			while((line=bfr.readLine())!=null){
				sb.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				bfr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * @param urlStr
	 * @param path
	 * @param fileName
	 * @return 
	 * 		-1����ʾ�����ļ�����
	 * 		 0����ʾ�����ļ��ɹ�
	 * 		 1����ʾ�ļ��Ѿ�����
	 */
	public int download(String urlStr,String path,String fileName){
		InputStream input = null;
		FileUtil fileUtil = new FileUtil();
		if(fileUtil.isFileExist(path+fileName)){
			return 1;
		}else{
			try {
				url = new URL(urlStr);
				HttpURLConnection urlConn =(HttpURLConnection) url.openConnection();
				input = urlConn.getInputStream();
				File resultFile = fileUtil.writeToSDFromInput(path, fileName, input);
				if(resultFile==null){
					return -1;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}finally{
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return 0;
		}
	}
}
 