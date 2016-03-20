package com.sspu.library.readerservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sspu.library.BaseActivity;
import com.sspu.library.ExitApplication;
import com.sspu.library.R;

public class Introduction extends Activity {

	ImageView mback;
	TextView detailcontent, detailtitle, title;
	private static int[] s1 = new int[] { R.drawable.back_default };

	// 初始化窗口
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 装载了View
		setContentView(R.layout.view_detail);
		ExitApplication.getInstance().addActivity(this);
		// 获得控件的对象实例
		mback = (ImageView) findViewById(R.id.toolbar_left_image);
		detailcontent = (TextView) findViewById(R.id.detailcontent);
		detailtitle = (TextView) findViewById(R.id.detailtitle);
		title = (TextView) findViewById(R.id.title);
		String[] introduction = getResources().getStringArray(
				R.array.introduction);
		// 设置title的值
		detailtitle.setText(introduction[0]);
		title.setText("本馆概况");
		detailcontent.setText(introduction[1]);
		// 为ImageView添加单击事件监听器
		mback.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mback.setImageDrawable(getResources().getDrawable(s1[0]));
				if (s1[0] == R.drawable.back_default) {
					Intent myIntent = new Intent();
					myIntent = new Intent(Introduction.this, BaseActivity.class);
					myIntent.putExtra("num", 1);
					startActivity(myIntent);
					Introduction.this.finish();
				}
			}
		});
	}
	//捕捉按键按下的事件
	public boolean OnKeyDown(int keyCode, KeyEvent event) {
		//如果按下的是“返回键”并且重复次数是0则执行dialog方法并且返回true不再执行回调方法
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

			Intent myIntent;

			myIntent = new Intent(Introduction.this, BaseActivity.class);
			myIntent.putExtra("num", 1);
			startActivity(myIntent);

			this.finish();

		}

		return false;

	}

}