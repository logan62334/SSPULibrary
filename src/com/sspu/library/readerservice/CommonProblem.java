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

public class CommonProblem extends Activity {

	private int pic[] = new int[] { R.drawable.cjwt, R.drawable.cjwt,
			R.drawable.cjwt, R.drawable.cjwt, R.drawable.cjwt, R.drawable.cjwt,
			R.drawable.cjwt, R.drawable.cjwt, R.drawable.cjwt, R.drawable.cjwt,
			R.drawable.cjwt, R.drawable.cjwt };
	private String data[] = {
			"��վ��ͼ���Ѱ������ ",
			"��ν���ͼ��ݵ����������ң�",
			"���ϰ���С�߲�ѧ����Ԣ���������",
			"����������������� ",
			"������������صĵ�����ֻ�������ص����Ͽ��������ܲ����㣬����ÿ������ԭ�������صĵ��Ժܿ�������ͬѧ�����á���ʲô�취�ܷŵ��Լ������Ͽ��أ� ",
			"����Ϊʲô�е����Ĳ���ȫ�����أ�лл ", "Ϊʲôĳ�����ݿ�ʹ����һ��ʱ�䣬��ͼ�����վ�ĵ�����Դ�о�û�����Ӳ���ʹ���� ",
			"Ϊʲô��ʱ����ʹ��ĳ�����ݿ⣿", "����˽�ͼ��ݸ��ֵ�����Դ��ʹ�÷�������λ�ð�����", "ͼ�����ѵ��������������Щ��",
			"�ڼ������ͼ���������Դ�����ʸ����ʹ�� ", "�ҹݲ��õ�ͼ����෽����ʲô��" };

	private ListView datalistlong = null; // ����ListView���
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
		super.setContentView(R.layout.list_long);
		ExitApplication.getInstance().addActivity(this);
		// ���ImageView�Ķ���ʵ��
		mback = (ImageView) findViewById(R.id.toolbar_left_image);
		// ���TextView�Ķ���ʵ��
		title = (TextView) findViewById(R.id.title);
		// ����title��ֵ
		title.setText("��������");
		// ΪImageView��ӵ����¼�������
		mback.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mback.setImageDrawable(getResources().getDrawable(s1[0]));
				if (s1[0] == R.drawable.back_default) {
					Intent myIntent = new Intent();
					myIntent = new Intent(CommonProblem.this, BaseActivity.class);
					myIntent.putExtra("num", 1);
					startActivity(myIntent);
					CommonProblem.this.finish();
				}
			}

		});
		this.datalistlong = (ListView) super.findViewById(R.id.datalistlong); // ȡ�����
		for (int x = 0; x < this.data.length; x++) {
			Map<String, String> map = new HashMap<String, String>(); // ����Map���ϣ�����ÿһ������
			map.put("pic", String.valueOf(this.pic[x])); // ��data_list.xml�е�TextView���ƥ��
			map.put("title", this.data[x]); // ��data_list.xml�е�TextView���ƥ��
			this.list.add(map); // ���������е�������
		}
		this.simpleAdapter = new SimpleAdapter(this, this.list,
				R.layout.list_normal, new String[] { "pic", "title" } // Map�е�key������
				, new int[] { R.id.pic, R.id.title }); // ��data_list.xml�ж�����������ԴID
		this.datalistlong.setAdapter(this.simpleAdapter);
		// �����ť����һ�������¼�������ͨ����ť��id���ֵ������ĸ���ť
		datalistlong.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// arg2������������е�position�ģ�~
				Intent intent = new Intent();
				String arg;
				switch (arg2) {
				case 0:
					intent.setClass(getApplicationContext(), Detail.class);
					CommonProblem.this.startActivity(intent);
					arg = "34";
					Editor sharedata33 = getSharedPreferences("dt", 0).edit();
					sharedata33.putString("arg2", arg);
					sharedata33.commit();
					break;
				case 1:
					intent.setClass(getApplicationContext(), Detail.class);
					CommonProblem.this.startActivity(intent);
					arg = "35";
					Editor sharedata34 = getSharedPreferences("dt", 0).edit();
					sharedata34.putString("arg2", arg);
					sharedata34.commit();
					break;
				case 2:
					intent.setClass(getApplicationContext(), Detail.class);
					CommonProblem.this.startActivity(intent);
					arg = "36";
					Editor sharedata35 = getSharedPreferences("dt", 0).edit();
					sharedata35.putString("arg2", arg);
					sharedata35.commit();
					break;
				case 3:
					intent.setClass(getApplicationContext(), Detail.class);
					CommonProblem.this.startActivity(intent);
					arg = "37";
					Editor sharedata36 = getSharedPreferences("dt", 0).edit();
					sharedata36.putString("arg2", arg);
					sharedata36.commit();
					break;
				case 4:
					intent.setClass(getApplicationContext(), Detail.class);
					CommonProblem.this.startActivity(intent);
					arg = "38";
					Editor sharedata37 = getSharedPreferences("dt", 0).edit();
					sharedata37.putString("arg2", arg);
					sharedata37.commit();
					break;
				case 5:
					intent.setClass(getApplicationContext(), Detail.class);
					CommonProblem.this.startActivity(intent);
					arg = "39";
					Editor sharedata38 = getSharedPreferences("dt", 0).edit();
					sharedata38.putString("arg2", arg);
					sharedata38.commit();
					break;
				case 6:
					intent.setClass(getApplicationContext(), Detail.class);
					CommonProblem.this.startActivity(intent);
					arg = "40";
					Editor sharedata39 = getSharedPreferences("dt", 0).edit();
					sharedata39.putString("arg2", arg);
					sharedata39.commit();
					break;
				case 7:
					intent.setClass(getApplicationContext(), Detail.class);
					CommonProblem.this.startActivity(intent);
					arg = "41";
					Editor sharedata40 = getSharedPreferences("dt", 0).edit();
					sharedata40.putString("arg2", arg);
					sharedata40.commit();
					break;
				case 8:
					intent.setClass(getApplicationContext(), Detail.class);
					CommonProblem.this.startActivity(intent);
					arg = "42";
					Editor sharedata41 = getSharedPreferences("dt", 0).edit();
					sharedata41.putString("arg2", arg);
					sharedata41.commit();
					break;

				case 9:
					intent.setClass(getApplicationContext(), Detail.class);
					CommonProblem.this.startActivity(intent);
					arg = "43";
					Editor sharedata42 = getSharedPreferences("dt", 0).edit();
					sharedata42.putString("arg2", arg);
					sharedata42.commit();
					break;
				case 10:
					intent.setClass(getApplicationContext(), Detail.class);
					CommonProblem.this.startActivity(intent);
					arg = "44";
					Editor sharedata43 = getSharedPreferences("dt", 0).edit();
					sharedata43.putString("arg2", arg);
					sharedata43.commit();
					break;
				case 11:
					intent.setClass(getApplicationContext(), Detail.class);
					CommonProblem.this.startActivity(intent);
					arg = "45";
					Editor sharedata44 = getSharedPreferences("dt", 0).edit();
					sharedata44.putString("arg2", arg);
					sharedata44.commit();
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

			myIntent = new Intent(CommonProblem.this, BaseActivity.class);
			myIntent.putExtra("num", 1);
			startActivity(myIntent);

			this.finish();

		}

		return false;

	}
}
