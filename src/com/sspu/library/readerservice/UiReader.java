package com.sspu.library.readerservice;

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

import com.sspu.library.ExitApplication;
import com.sspu.library.R;
import com.sspu.library.service.Login;

public class UiReader extends Activity {

	private int pic[] = new int[] { R.drawable.xwdt, R.drawable.ggdt,
			R.drawable.bggk, R.drawable.fwzn, R.drawable.xstj, R.drawable.cjwt,
			R.drawable.xsrgbz, R.drawable.sbbxtb };
	private String data[] = { "���Ŷ�̬", "���涯̬", "���ݸſ�", "����ָ��", "�����Ƽ�", "��������",
			"������ݰ���", "�豸����" };

	private ListView datalist1 = null; // ����ListView���
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>(); // ������ʾ�����ݰ�װ
	private SimpleAdapter simpleAdapter = null; // �������ݵ�ת������
	ImageView maccount;
	private static int[] s1 = new int[] { R.drawable.account_default };
    //��ʼ������
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//װ����View
		super.setContentView(R.layout.activity_reader_service);
		ExitApplication.getInstance().addActivity(this);
		//���ImageView�Ķ���ʵ��
		maccount = (ImageView) findViewById(R.id.toolbar_right_image);
		//ΪImageView��ӵ����¼�������
		maccount.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				maccount.setImageDrawable(getResources().getDrawable(s1[0]));
				if (s1[0] == R.drawable.account_default) {
					Intent myIntent = new Intent();
					myIntent = new Intent(UiReader.this, Login.class);
					startActivity(myIntent);
					UiReader.this.finish();
				}
			}

		});
		// ȡ�����
		this.datalist1 = (ListView) super.findViewById(R.id.datalist1); 
		for (int x = 0; x < this.data.length; x++) {
			// ����Map���ϣ�����ÿһ������
			Map<String, String> map = new HashMap<String, String>(); 
			// ��data_list.xml�е�TextView���ƥ��
			map.put("pic", String.valueOf(this.pic[x])); 
			// ��data_list.xml�е�TextView���ƥ��
			map.put("title", this.data[x]); 
			map.put("score", String.valueOf(R.drawable.arrow));
			// ���������е�������
			this.list.add(map); 
		}
		this.simpleAdapter = new SimpleAdapter(this, this.list,
				R.layout.list_normal, new String[] { "pic", "title",
						"score" } // Map�е�key������
				, new int[] { R.id.pic, R.id.title, R.id.score }); // ��data_list.xml�ж�����������ԴID
		this.datalist1.setAdapter(this.simpleAdapter);
		// �����ť����һ�������¼�������ͨ����ť��id���ֵ������ĸ���ť
		datalist1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// arg2������������е�position�ģ�~
				Intent intent = new Intent();
				switch (arg2) {
				case 0:
					intent.setClass(getApplicationContext(), News.class);
					UiReader.this.startActivity(intent);
					break;
				case 1:
					intent.setClass(getApplicationContext(), Tips.class);
					UiReader.this.startActivity(intent);
					break;
				case 2:
					intent.setClass(getApplicationContext(), Introduction.class);
					UiReader.this.startActivity(intent);
					break;
				case 3:
					intent.setClass(getApplicationContext(), Service.class);
					UiReader.this.startActivity(intent);
					break;
				case 4:
					intent.setClass(getApplicationContext(), NewArrivals.class);
					UiReader.this.startActivity(intent);
					break;
				case 5:
					intent.setClass(getApplicationContext(),
							CommonProblem.class);
					UiReader.this.startActivity(intent);
					break;
				case 6:
					intent.setClass(getApplicationContext(), Helps.class);
					UiReader.this.startActivity(intent);
					break;
				case 7:
					intent.setClass(getApplicationContext(), YellowPages.class);
					UiReader.this.startActivity(intent);
					break;
				}
			}

		});

	}
    //��׽�������µ��¼�
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		//������µ��ǡ����ؼ��������ظ�������0��ִ��dialog�������ҷ���true����ִ�лص�����
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			dialog();
			return true;
		}
		return true;
	}

	// ͨ�öԻ������ʾ
	protected void dialog() {
		AlertDialog.Builder builder = new Builder(UiReader.this);
		// ���öԻ�����ʾ����Ϣ
		builder.setMessage("ȷ��Ҫ�˳���?");
		// ���öԻ�����ʾ�ı���
		builder.setTitle("��ʾ");
		// ���öԻ���İ�ť
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
		//��ʾ�Ի���
		builder.create().show();
	}
}