package com.sspu.library.info;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sspu.library.BaseActivity;
import com.sspu.library.ExitApplication;
import com.sspu.library.R;
import com.sspu.library.common.BackAction;

public class OpenTimeDetail extends Rules {

	ImageView mback;
	TextView l1, l2, l3, l4, l5, l6, l7, textView2;
	private static int[] s1 = new int[] { R.drawable.back_default };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opentime);
		ExitApplication.getInstance().addActivity(this);
		mback = (ImageView) findViewById(R.id.toolbar_left_image);
		SharedPreferences sharedata = getSharedPreferences("dt", 0);
		String dt = sharedata.getString("arg2", null);
		if (dt == "51") {
			l1 = (TextView) findViewById(R.id.l1);
			l2 = (TextView) findViewById(R.id.l2);
			l3 = (TextView) findViewById(R.id.l3);
			l4 = (TextView) findViewById(R.id.l4);
			l5 = (TextView) findViewById(R.id.l5);
			l6 = (TextView) findViewById(R.id.l6);
			l7 = (TextView) findViewById(R.id.l7);
			textView2 = (TextView) findViewById(R.id.textView2);
			l1.setText("��һ:8:00-22:00");
			l2.setText("�ܶ�:8:00-22:00");
			l3.setText("����:8:00-22:00");
			l4.setText("����:8:00-22:00");
			l5.setText("����:8:00-15:00");
			l6.setText("����:9:00-16:00");
			l7.setText("����:9:00-21:00");
			textView2.setText("���ı�����������18��¥102��");
		}else if(dt=="52"){
			l1 =(TextView) findViewById(R.id.l1);
	    	l2 =(TextView) findViewById(R.id.l2);
	    	l3 =(TextView) findViewById(R.id.l3);
	    	l4 =(TextView) findViewById(R.id.l4);
	    	l5 =(TextView) findViewById(R.id.l5);
	    	l6 =(TextView) findViewById(R.id.l6);
	    	l7 =(TextView) findViewById(R.id.l7);
	    	textView2 =(TextView) findViewById(R.id.textView2);
	    	l1.setText("��һ:8:00-22:00");
	    	l2.setText("�ܶ�:8:00-22:00");
	    	l3.setText("����:8:00-22:00");
	    	l4.setText("����:8:00-22:00");
	    	l5.setText("����:8:00-15:00");
	    	l6.setText("����:9:00-16:00");
	    	l7.setText("����:9:00-21:00");
	    	textView2.setText("��ͨ������18��¥232��");
		}else if(dt=="53"){
			l1 =(TextView) findViewById(R.id.l1);
	    	l2 =(TextView) findViewById(R.id.l2);
	    	l3 =(TextView) findViewById(R.id.l3);
	    	l4 =(TextView) findViewById(R.id.l4);
	    	l5 =(TextView) findViewById(R.id.l5);
	    	l6 =(TextView) findViewById(R.id.l6);
	    	l7 =(TextView) findViewById(R.id.l7);
	    	textView2 =(TextView) findViewById(R.id.textView2);
	    	l1.setText("��һ:8:00-22:00");
	    	l2.setText("�ܶ�:8:00-22:00");
	    	l3.setText("����:8:00-22:00");
	    	l4.setText("����:8:00-22:00");
	    	l5.setText("����:8:00-15:00");
	    	l6.setText("����:9:00-16:00");
	    	l7.setText("����:9:00-21:00");
	    	textView2.setText("����������18��¥231��");
		}else if(dt=="54"){
			l1 =(TextView) findViewById(R.id.l1);
	    	l2 =(TextView) findViewById(R.id.l2);
	    	l3 =(TextView) findViewById(R.id.l3);
	    	l4 =(TextView) findViewById(R.id.l4);
	    	l5 =(TextView) findViewById(R.id.l5);
	    	l6 =(TextView) findViewById(R.id.l6);
	    	l7 =(TextView) findViewById(R.id.l7);
	    	textView2 =(TextView) findViewById(R.id.textView2);
	    	l1.setText("��һ:8:00-22:00");
	    	l2.setText("�ܶ�:8:00-22:00");
	    	l3.setText("����:8:00-22:00");
	    	l4.setText("����:8:00-22:00");
	    	l5.setText("����:8:00-15:00");
	    	l6.setText("����:9:00-16:00");
	    	l7.setText("����:9:00-21:00");
	    	textView2.setText("����������18��¥331-332��");
		}else if(dt=="55"){
			l1 =(TextView) findViewById(R.id.l1);
	    	l2 =(TextView) findViewById(R.id.l2);
	    	l3 =(TextView) findViewById(R.id.l3);
	    	l4 =(TextView) findViewById(R.id.l4);
	    	l5 =(TextView) findViewById(R.id.l5);
	    	l6 =(TextView) findViewById(R.id.l6);
	    	l7 =(TextView) findViewById(R.id.l7);
	    	textView2 =(TextView) findViewById(R.id.textView2);
	    	l1.setText("��һ:8:00-22:00");
	    	l2.setText("�ܶ�:8:00-22:00");
	    	l3.setText("����:8:00-22:00");
	    	l4.setText("����:8:00-22:00");
	    	l5.setText("����:8:00-15:00");
	    	l6.setText("����:9:00-16:00");
	    	l7.setText("����:9:00-21:00");
	    	textView2.setText("����������18��¥431-432��");
		}else if(dt=="56"){
			l1 =(TextView) findViewById(R.id.l1);
	    	l2 =(TextView) findViewById(R.id.l2);
	    	l3 =(TextView) findViewById(R.id.l3);
	    	l4 =(TextView) findViewById(R.id.l4);
	    	l5 =(TextView) findViewById(R.id.l5);
	    	l6 =(TextView) findViewById(R.id.l6);
	    	l7 =(TextView) findViewById(R.id.l7);
	    	textView2 =(TextView) findViewById(R.id.textView2);
	    	l1.setText("��һ:8:30-11:30/12:30-15:30");
	    	l2.setText("�ܶ�:8:30-11:30/12:30-15:30");
	    	l3.setText("����:8:30-11:30/12:30-15:30");
	    	l4.setText("����:8:30-11:30/12:30-15:30");
	    	l5.setText("����:8:30-11:30");
	    	l6.setText("����:������");
	    	l7.setText("����:������");
	    	textView2.setText("У԰��������18��¥122��");
		}
		mback.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mback
						.setImageDrawable(getResources().getDrawable(s1[0]));
				if (s1[0] == R.drawable.back_default) {
					BackAction.Back(OpenTimeDetail.this, BaseActivity.class,3);
				}
			}
		});
	}

}