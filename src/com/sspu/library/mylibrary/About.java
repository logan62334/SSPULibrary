package com.sspu.library.mylibrary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
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
import com.sspu.library.util.UpdateManager;

public class About extends Activity {
	private int pic[] = new int[] { R.drawable.xsrgbz, R.drawable.xsrgbz,
			R.drawable.xsrgbz, R.drawable.xsrgbz, R.drawable.xsrgbz };
	private String data[] = { "ȥ���� ", "��������", "����΢�� ", "����°汾", "����Ӧ�� " };

	private ListView datalistshort = null; // ����ListView���
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>(); // ������ʾ�����ݰ�װ
	private SimpleAdapter simpleAdapter = null; // �������ݵ�ת������
	ImageView mback;
	TextView title, logoname;
	private static int[] s1 = new int[] { R.drawable.back_default };
	UpdateManager manager = new UpdateManager(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_about);
		ExitApplication.getInstance().addActivity(this);
		String versionName = getVersion(About.this.getApplicationContext());
		mback = (ImageView) findViewById(R.id.toolbar_left_image);
		title = (TextView) findViewById(R.id.title);
		logoname = (TextView) findViewById(R.id.logoname);
		logoname.setText("�ƶ�ͼ��� " + versionName);
		title.setText("��������");

		mback.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mback.setImageDrawable(getResources().getDrawable(s1[0]));
				if (s1[0] == R.drawable.back_default) {
					Intent myIntent = new Intent();
					myIntent = new Intent(About.this, BaseActivity.class);
					myIntent.putExtra("num", 4);
					startActivity(myIntent);
					About.this.finish();
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
		datalistshort.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// arg2������������е�position�ģ�~
				Intent intent = new Intent();
				switch (arg2) {
				case 0:
					Uri marketuri = Uri.parse("market://details?id="
							+ getPackageName());
					Intent marketintent = new Intent(Intent.ACTION_VIEW,
							marketuri);
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(marketintent);

					break;
				case 1:
					intent.setClass(getApplicationContext(), Reback.class);
					About.this.startActivity(intent);
					break;
				case 2:
					Uri uri = Uri.parse("http://weibo.com/sspulib");
					Intent it = new Intent(Intent.ACTION_VIEW, uri);
					startActivity(it);
					break;
				case 3:
					new Thread(new MyThread()).start();
					// ��ʾ�Ի���
					// mDialog.show();
					break;
				case 4:
					Intent shareintent = new Intent(Intent.ACTION_SEND);
					// �������������
					shareintent.setType("text/plain");
					// ���������
					shareintent.putExtra(Intent.EXTRA_SUBJECT, "���ѷ���");
					// ���������
					shareintent
							.putExtra(
									Intent.EXTRA_TEXT,
									"������ʹ�ö�����ͼ��ݣ���Ҳ����ɣ������ص�ַ��http://shouji.360tpcdn.com/141008/b2a1171b8ac6cfbf4796c21bc3264876/com.sspu.library_2.apk");
					// ���������µ�Activity
					shareintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					// Ŀ��Ӧ��Ѱ�ҶԻ���ı���
					startActivity(Intent.createChooser(shareintent, getTitle()));
					break;
				}
			}

		});

	}

	public class MyThread implements Runnable {

		@Override
		public void run() {
			try {
				manager.checkUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public static String getVersion(Context context)// ��ȡ�汾��
	{
		try {
			PackageInfo pi = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
			return pi.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return context.getString(R.string.version_unknown);
		}
	}

	public boolean OnKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

			Intent myIntent;

			myIntent = new Intent(About.this, BaseActivity.class);
			myIntent.putExtra("num", 4);
			startActivity(myIntent);

			this.finish();

		}

		return false;

	}
}
