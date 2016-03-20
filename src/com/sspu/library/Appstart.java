package com.sspu.library;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


public class Appstart extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN); // 全屏显示
		setContentView(R.layout.layout_appstart);

		ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		State wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.getState();// 取得WIFI状态
		if (wifi != State.CONNECTED && wifi != State.CONNECTING) {// 判断WIFI是否连接
			Toast.makeText(Appstart.this, "未检测到WIFI，请连接后使用", Toast.LENGTH_LONG)
					.show();
		}
		/**
		 * 让闪屏持续一秒钟
		 */
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// 判断是否第一次开启应用，如果是则显示欢迎页面
				SharedPreferences preferences = getSharedPreferences("count",
						MODE_PRIVATE);// 指定操作的文件名称
				int count = preferences.getInt("count", 0);
				if (count == 0) {
					Editor editor = preferences.edit();// 编辑文件
					editor.putInt("count", ++count);// 保存整型
					editor.commit();// 提交更新
					Intent intent = new Intent(Appstart.this, Welcome.class);
					startActivity(intent);
					Appstart.this.finish();

				} else {
					Intent intent = new Intent(Appstart.this, BaseActivity.class);
					startActivity(intent);
					Appstart.this.finish();
				}

			}
		}, 1000);
	}


}