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
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��������
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN); // ȫ����ʾ
		setContentView(R.layout.layout_appstart);

		ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		State wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.getState();// ȡ��WIFI״̬
		if (wifi != State.CONNECTED && wifi != State.CONNECTING) {// �ж�WIFI�Ƿ�����
			Toast.makeText(Appstart.this, "δ��⵽WIFI�������Ӻ�ʹ��", Toast.LENGTH_LONG)
					.show();
		}
		/**
		 * ����������һ����
		 */
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// �ж��Ƿ��һ�ο���Ӧ�ã����������ʾ��ӭҳ��
				SharedPreferences preferences = getSharedPreferences("count",
						MODE_PRIVATE);// ָ���������ļ�����
				int count = preferences.getInt("count", 0);
				if (count == 0) {
					Editor editor = preferences.edit();// �༭�ļ�
					editor.putInt("count", ++count);// ��������
					editor.commit();// �ύ����
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