package com.sspu.library.service;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sspu.library.BaseActivity;
import com.sspu.library.ExitApplication;
import com.sspu.library.R;
import com.sspu.library.util.QueryServer;

public class Login extends Activity {
	ImageView mback;
	TextView txt1, txt2;
	private ProgressDialog dialog;
	private EditText userName, password;
	private ImageButton btn_login;
	private String userNameValue, passwordValue;
	private static int[] s1 = new int[] { R.drawable.back_default };
	private static Handler handler = new Handler();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_login);
		ExitApplication.getInstance().addActivity(this);
		SharedPreferences sharedata = getSharedPreferences("data", 0);
		String data = sharedata.getString("sspuuserid", null);
		userName = (EditText) findViewById(R.id.editText_cardnumber);
		password = (EditText) findViewById(R.id.editText_password);
		userName.setInputType(EditorInfo.TYPE_CLASS_PHONE);
		dialog = new ProgressDialog(this);
		dialog.setTitle("提示");
		dialog.setMessage("正在下载，请稍后...");
		dialog.setCancelable(false);
		txt1 = (TextView) findViewById(R.id.txt1);
		txt2 = (TextView) findViewById(R.id.txt2);
		if (data == null || "".equals(data)) {
			// 未登录，提示用户重新登录，并跳转至登陆页面
			txt1.setText("您尚未登录");
			txt1.setTextColor(Color.BLACK);
			txt2.setText("登录后享受更多的信息服务");
		} else {
			// 已登录，跳转至相应功能页面
			txt1.setText("当前账号：" + data);
			txt1.setTextColor(Color.BLUE);
			txt2.setText("您是否需要切换账号？");
		}
		btn_login = (ImageButton) findViewById(R.id.imageButton_login);
		mback = (ImageView) findViewById(R.id.toolbar_left_image);
		mback.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mback.setImageDrawable(getResources().getDrawable(s1[0]));
				if (s1[0] == R.drawable.back_default) {
					Intent myIntent = new Intent();
					myIntent = new Intent(Login.this, BaseActivity.class);
					startActivity(myIntent);
					Login.this.finish();
				}
			}
		});

		btn_login.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				new Thread(new MyThread()).start();
				// 显示对话框
				dialog.show();

			}
		});

	}

	public class MyThread implements Runnable {

		@Override
		public void run() {
			userNameValue = userName.getText().toString();
			passwordValue = password.getText().toString();
			try {
				QueryServer qs = new QueryServer();
				if (qs.doLogin(userNameValue, passwordValue) != null) {
					handler.post(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(Login.this, userNameValue + "-登录成功",
									Toast.LENGTH_SHORT).show();
						}
					});
					Editor sharedata = getSharedPreferences("data", 0).edit();
					sharedata.putString("sspuuserid", userNameValue);
					sharedata.commit();
					Intent intent = new Intent(Login.this, BaseActivity.class);
					intent.putExtra("num", 4);
					dialog.dismiss();
					Login.this.startActivity(intent);
					finish();
				} else {
					handler.post(new Runnable() {
						@Override
						public void run() {
							dialog.dismiss();
							Toast.makeText(Login.this, "登录失败，请确认输入的账号密码是否正确！",
									Toast.LENGTH_SHORT).show();
						}
					});
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
