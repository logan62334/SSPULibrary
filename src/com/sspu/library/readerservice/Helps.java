package com.sspu.library.readerservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.sspu.library.BaseActivity;
import com.sspu.library.ExitApplication;
import com.sspu.library.R;
import com.sspu.library.info.Detail;

public class Helps extends Activity {

	private int pic[] = new int[] { R.drawable.xsrgbz, R.drawable.xsrgbz,
			R.drawable.xsrgbz };
	private String data[] = { "У����ʷ���  ", "���Է��� ", "��ѯ���� " };

	private ListView datalistshort = null; // ����ListView���
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>(); // ������ʾ�����ݰ�װ
	private SimpleAdapter simpleAdapter = null; // �������ݵ�ת������
	ImageView mback;
	TextView title;
	private static int[] s1 = new int[] { R.drawable.back_default };

	// ��ʼ������
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// װ����View
		super.setContentView(R.layout.list_short);
		ExitApplication.getInstance().addActivity(this);
		// ���ImageView�Ķ���ʵ��
		mback = (ImageView) findViewById(R.id.toolbar_left_image);
		// ���TextView�Ķ���ʵ��
		title = (TextView) findViewById(R.id.title);
		// ����title��ֵ
		title.setText("������ݰ���");
		// ΪImageView��ӵ����¼�������
		mback.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mback.setImageDrawable(getResources().getDrawable(s1[0]));
				if (s1[0] == R.drawable.back_default) {
					Intent myIntent = new Intent();
					myIntent = new Intent(Helps.this, BaseActivity.class);
					myIntent.putExtra("num", 1);
					startActivity(myIntent);
					Helps.this.finish();
				}
			}
		});
		this.datalistshort = (ListView) super.findViewById(R.id.datalistshort); // ȡ�����
		for (int x = 0; x < this.data.length; x++) {
			Map<String, String> map = new HashMap<String, String>(); // ����Map���ϣ�����ÿһ������
			map.put("pic", String.valueOf(this.pic[x])); // ��data_list.xml�е�TextView���ƥ��
			map.put("title", this.data[x]); // ��data_list.xml�е�TextView���ƥ��
			this.list.add(map); // ���������е�������
		}
		this.simpleAdapter = new SimpleAdapter(this, this.list,
				R.layout.list_normal, new String[] { "pic", "title" } // Map�е�key������
				, new int[] { R.id.pic, R.id.title }); // ��data_list.xml�ж�����������ԴID
		this.datalistshort.setAdapter(this.simpleAdapter);
		// �����ť����һ�������¼�������ͨ����ť��id���ֵ������ĸ���ť
		datalistshort.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// arg2������������е�position�ģ�~
				Intent intent = new Intent();
				String arg;
				switch (arg2) {
				case 0:
					intent.setClass(getApplicationContext(), Detail.class);
					Helps.this.startActivity(intent);
					arg = "48";
					Editor sharedata47 = getSharedPreferences("dt", 0).edit();
					sharedata47.putString("arg2", arg);
					sharedata47.commit();
					break;
				case 1:
					intent.setClass(getApplicationContext(), Detail.class);
					Helps.this.startActivity(intent);
					arg = "49";
					Editor sharedata48 = getSharedPreferences("dt", 0).edit();
					sharedata48.putString("arg2", arg);
					sharedata48.commit();
					break;
				case 2:
					intent.setClass(getApplicationContext(), Detail.class);
					Helps.this.startActivity(intent);
					arg = "50";
					Editor sharedata49 = getSharedPreferences("dt", 0).edit();
					sharedata49.putString("arg2", arg);
					sharedata49.commit();
					break;
				}
			}
		});

	}
	//��׽�������µ��¼�
	public boolean OnKeyDown(int keyCode, KeyEvent event) {
		//������µ��ǡ����ؼ��������ظ�������0��ִ��dialog�������ҷ���true����ִ�лص�����
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

			Intent myIntent;

			myIntent = new Intent(Helps.this, BaseActivity.class);
			myIntent.putExtra("num", 1);
			startActivity(myIntent);

			this.finish();

		}

		return false;

	}
}
