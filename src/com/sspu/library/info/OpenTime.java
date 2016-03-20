package com.sspu.library.info;

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
import com.sspu.library.common.BackAction;

public class OpenTime extends Activity {

	private int pic[] = new int[] { R.drawable.dzly, R.drawable.dzly,
			R.drawable.dzly, R.drawable.dzly, R.drawable.dzly, R.drawable.dzly,
			R.drawable.dzly, R.drawable.dzly, R.drawable.dzly, R.drawable.dzly,
			R.drawable.dzly, R.drawable.dzly };
	private String data[] = { "���ı�����������18��¥102��", "��ͨ������18��¥232��",
			"����������18��¥231��", "����������18��¥331-332��", "����������18��¥431-432��",
			"У԰��������18��¥122��" };

	private ListView datalistshort = null; // ����ListView���
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>(); // ������ʾ�����ݰ�װ
	private SimpleAdapter simpleAdapter = null; // �������ݵ�ת������
	ImageView mback;
	TextView title;
	private static int[] s1 = new int[] { R.drawable.back_default };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.list_short);
		ExitApplication.getInstance().addActivity(this);
		mback = (ImageView) findViewById(R.id.toolbar_left_image);
		mback
		.setImageDrawable(getResources().getDrawable(s1[0]));
		title =(TextView) findViewById(R.id.title);
		title.setText("����ʱ��");
		mback.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				BackAction.Back(OpenTime.this, BaseActivity.class,3);
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
		datalistshort.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// arg2������������е�position�ģ�~
				Intent intent = new Intent();
				String arg;
				switch (arg2) {
				case 0:
					intent.setClass(getApplicationContext(), OpenTimeDetail.class);
					OpenTime.this.startActivity(intent);
					arg = "51";
					Editor sharedata49 = getSharedPreferences("dt", 0).edit();
					sharedata49.putString("arg2", arg);
					sharedata49.commit();
					break;
				case 1:
					intent.setClass(getApplicationContext(), OpenTimeDetail.class);
					OpenTime.this.startActivity(intent);
					arg = "52";
					Editor sharedata50 = getSharedPreferences("dt", 0).edit();
					sharedata50.putString("arg2", arg);
					sharedata50.commit();
					break;
				case 2:
					intent.setClass(getApplicationContext(), OpenTimeDetail.class);
					OpenTime.this.startActivity(intent);
					arg = "53";
					Editor sharedata51 = getSharedPreferences("dt", 0).edit();
					sharedata51.putString("arg2", arg);
					sharedata51.commit();
					break;
				case 3:
					intent.setClass(getApplicationContext(), OpenTimeDetail.class);
					OpenTime.this.startActivity(intent);
					arg = "54";
					Editor sharedata52 = getSharedPreferences("dt", 0).edit();
					sharedata52.putString("arg2", arg);
					sharedata52.commit();
					break;
				case 4:
					intent.setClass(getApplicationContext(), OpenTimeDetail.class);
					OpenTime.this.startActivity(intent);
					arg = "55";
					Editor sharedata53 = getSharedPreferences("dt", 0).edit();
					sharedata53.putString("arg2", arg);
					sharedata53.commit();
					break;
				case 5:
					intent.setClass(getApplicationContext(), OpenTimeDetail.class);
					OpenTime.this.startActivity(intent);
					arg = "56";
					Editor sharedata54 = getSharedPreferences("dt", 0).edit();
					sharedata54.putString("arg2", arg);
					sharedata54.commit();
					break;
				}
			}
		});

	}

	public boolean OnKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

			BackAction.Back(OpenTime.this, BaseActivity.class,3);

		}

		return false;

	}
}
