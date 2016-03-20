package com.sspu.library.readerservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sspu.library.BaseActivity;
import com.sspu.library.ExitApplication;
import com.sspu.library.R;
import com.sspu.library.data.Broadcast;

public class BroadcastDetail extends Activity {
	ImageView mback;
	TextView titleTextView;
	TextView authorTextView;
	TextView addressTextView;
	TextView isbnTextView;
	TextView callnoTextView;
	TextView contentTextView;
	Broadcast broadcast;
	private static int[] s1 = new int[] { R.drawable.back_default };

	// 初始化窗口
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 装载了View
		setContentView(R.layout.activity_recommendation_detail);
		ExitApplication.getInstance().addActivity(this);
		// 获得ImageView的对象实例
		mback = (ImageView) findViewById(R.id.imageButton1);
		titleTextView = (TextView) findViewById(R.id.textView2);
		authorTextView = (TextView) findViewById(R.id.textView3);
		addressTextView = (TextView) findViewById(R.id.textView4);
		isbnTextView = (TextView) findViewById(R.id.textView7);
		callnoTextView = (TextView) findViewById(R.id.textView8);
		contentTextView = (TextView) findViewById(R.id.textView6);
		Intent mIntent = getIntent();
		Bundle bundle = mIntent.getExtras();
		broadcast = (Broadcast) bundle.get("broadcast");
		titleTextView.setText(broadcast.getTITLE());
		authorTextView.setText(broadcast.getCREATEDATE());
		contentTextView.setText(broadcast.getCONTEXT());
		// 为ImageView添加单击事件监听器
		mback.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mback.setImageDrawable(getResources().getDrawable(s1[0]));
				if (s1[0] == R.drawable.back_default) {
					Intent myIntent = new Intent();
					myIntent = new Intent(BroadcastDetail.this, BaseActivity.class);
					myIntent.putExtra("num", 1);
					startActivity(myIntent);
					BroadcastDetail.this.finish();
				}
			}

		});
	}

}
