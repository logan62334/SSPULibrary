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

	public static int localVersion = 0;// ���ذ�װ�汾

	public static int serverVersion = 2;// �������汾

	public static String downloadDir = "jj/";// ��װĿ¼

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
			 * ������дһ���������������ȡ�������˵�serverVersion.
			 */

	}

}
