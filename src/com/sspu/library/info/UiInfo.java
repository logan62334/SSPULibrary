package com.sspu.library.info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
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

import com.sspu.library.ViewPagerActivity;
import com.sspu.library.ExitApplication;
import com.sspu.library.R;
import com.sspu.library.service.Login;

public class UiInfo extends Activity {

	private int pic[] = new int[] { R.drawable.zxdt, R.drawable.bggk,
			R.drawable.dzly, R.drawable.fwzn, R.drawable.xstj, R.drawable.cjwt,
			R.drawable.xsrgbz };
	private String data[] = { "�ݳ��´�", "�ݿ�����", "�ṹ����", "����ʱ��", "���ᵼ��", "�����ƶ�",
			"�ֹݵ���" };

	private ListView datalist2 = null; // ����ListView���
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>(); // ������ʾ�����ݰ�װ
	private SimpleAdapter simpleAdapter = null; // �������ݵ�ת������
	ImageView maccount;
	private static int[] s1 = new int[] { R.drawable.account_default };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_library_info);
		ExitApplication.getInstance().addActivity(this);
		maccount = (ImageView) findViewById(R.id.toolbar_right_image);
		maccount.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				maccount
						.setImageDrawable(getResources().getDrawable(s1[0]));
				if (s1[0] == R.drawable.account_default) {
					Intent myIntent = new Intent();
					myIntent = new Intent(UiInfo.this, Login.class);
					startActivity(myIntent);
					UiInfo.this.finish();
				}
			}

		});

		this.datalist2 = (ListView) super.findViewById(R.id.datalist2); // ȡ�����
		for (int x = 0; x < this.data.length; x++) {
			Map<String, String> map = new HashMap<String, String>(); // ����Map���ϣ�����ÿһ������
			map.put("pic", String.valueOf(this.pic[x])); // ��data_list.xml�е�TextView���ƥ��
			map.put("title", this.data[x]); // ��data_list.xml�е�TextView���ƥ��
			map.put("score", String.valueOf(R.drawable.arrow));
			this.list.add(map); // ���������е�������
		}
		this.simpleAdapter = new SimpleAdapter(this, this.list,
				R.layout.list_normal, new String[] { "pic", "title", "score" } // Map�е�key������
				, new int[] { R.id.pic, R.id.title, R.id.score }); // ��data_list.xml�ж�����������ԴID
		this.datalist2.setAdapter(this.simpleAdapter);

		datalist2.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// arg2������������е�position�ģ�~
				Intent intent = new Intent();
				switch (arg2) {
				case 0:
					intent.setClass(getApplicationContext(), Speech.class);
					UiInfo.this.startActivity(intent);
					break;
				case 1:
					intent.setClass(getApplicationContext(), MuseumCondition.class);
					UiInfo.this.startActivity(intent);
					break;
				case 2:
					intent.setClass(getApplicationContext(), StructureDetail.class);
					UiInfo.this.startActivity(intent);
					break;
				case 3:
					intent.setClass(getApplicationContext(), OpenTime.class);
					UiInfo.this.startActivity(intent);
					break;
				case 4:
					intent.setClass(getApplicationContext(),
							ViewPagerActivity.class);
					UiInfo.this.startActivity(intent);
					break;
				case 5:
					intent.setClass(getApplicationContext(), Rules.class);
					UiInfo.this.startActivity(intent);
					break;

				case 6:
					intent.setClass(getApplicationContext(), Branch.class);
					UiInfo.this.startActivity(intent);
					break;
				}
			}
		});

	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			dialog();

			return true;
		}
		return true;
	}

	protected void dialog() {
		AlertDialog.Builder builder = new Builder(UiInfo.this);
		builder.setMessage("ȷ��Ҫ�˳���?");
		builder.setTitle("��ʾ");
		builder.setPositiveButton("ȷ��",
				new android.content.DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Editor sharedata = getSharedPreferences("data", 0)
								.edit();
						sharedata.clear();
						sharedata.commit();

						ExitApplication.getInstance().exit();
					}
				});
		builder.setNegativeButton("ȡ��",
				new android.content.DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		builder.create().show();
	}

}