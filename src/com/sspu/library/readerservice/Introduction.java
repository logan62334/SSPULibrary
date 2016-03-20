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

	// ��ʼ������
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// װ����View
		setContentView(R.layout.view_detail);
		ExitApplication.getInstance().addActivity(this);
		// ��ÿؼ��Ķ���ʵ��
		mback = (ImageView) findViewById(R.id.toolbar_left_image);
		detailcontent = (TextView) findViewById(R.id.detailcontent);
		detailtitle = (TextView) findViewById(R.id.detailtitle);
		title = (TextView) findViewById(R.id.title);
		String[] introduction = getResources().getStringArray(
				R.array.introduction);
		// ����title��ֵ
		detailtitle.setText(introduction[0]);
		title.setText("���ݸſ�");
		detailcontent.setText(introduction[1]);
		// ΪImageView��ӵ����¼�������
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
	//��׽�������µ��¼�
	public boolean OnKeyDown(int keyCode, KeyEvent event) {
		//������µ��ǡ����ؼ��������ظ�������0��ִ��dialog�������ҷ���true����ִ�лص�����
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