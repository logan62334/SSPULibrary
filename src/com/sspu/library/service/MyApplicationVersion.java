package com.sspu.library.service;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

/***
 * MyApplication
 * 
 * @author zhangjia
 * 
 */
public class MyApplicationVersion extends Application {

	public static int localVersion = 0;// 本地安装版本

	public static int serverVersion = 2;// 服务器版本

	public static String downloadDir = "jj/";// 安装目录

	@Override
	public void onCreate() {
		super.onCreate();
			try {
				PackageInfo packageInfo = getApplicationContext()
						.getPackageManager().getPackageInfo(getPackageName(), 0);
				localVersion = packageInfo.versionCode;
			} catch (NameNotFoundException e) {
				e.printStackTrace();
			}
	
			/***
			 * 在这里写一个方法用于请求获取服务器端的serverVersion.
			 */

	}

}
