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

public class Rules extends Activity {

	private int pic[] = new int[] { R.drawable.cjwt, R.drawable.cjwt,
			R.drawable.cjwt, R.drawable.cjwt, R.drawable.cjwt, R.drawable.cjwt,
			R.drawable.cjwt, R.drawable.cjwt, R.drawable.cjwt, R.drawable.cjwt,
			R.drawable.cjwt, R.drawable.cjwt, R.drawable.cjwt, R.drawable.cjwt,
			R.drawable.cjwt, R.drawable.cjwt, R.drawable.cjwt, R.drawable.cjwt,
			R.drawable.cjwt, R.drawable.cjwt, R.drawable.cjwt, R.drawable.cjwt,
			R.drawable.cjwt, R.drawable.cjwt, R.drawable.cjwt, R.drawable.cjwt,
			R.drawable.cjwt, R.drawable.cjwt, R.drawable.cjwt, R.drawable.cjwt,
			R.drawable.cjwt, R.drawable.cjwt, R.drawable.cjwt, R.drawable.cjwt };
	private String data[] = { "ͼ��ݣ��������ģ����������ҽ��ù���涨 ", "���ڰ���У԰��ʱ�̹������Ĺ涨 ",
			"���ڲ���ϵͳPOS���ѻ�ʹ�������ϴ��Ĺ涨 ", "�����ṩУ԰��ϵͳ�ӿڵĹ涨 ", "У԰���豸ά�ް취 ",
			"�Ϻ��ڶ���ҵ��ѧ�������Ļ����������йܹ涨 ", "У԰���û���֪ ", "�Ϻ��ڶ���ҵ��ѧУ԰�����ϴ������̣��취 ",
			"�û�������ȫ������", "У԰����ϵͳ��ȫ�����ƶ� ", "����У԰����Ϣ��ȫ�¹�����׷���Ĺ涨 ", "��վ��������� ",
			"�Ϻ��ڶ���ҵ��ѧ��������� ", "����У����վ�������������淶 ", "У԰����Ϣ��������취 ", "ֱ����֧�������ƶ� ",
			"ֱ����֧��������ѧϰ�ƶ� ", "����ѧϰ�ƶ� ", " [2010-12-21]������ϯ�����ƶ� ",
			"ͼ��ݹ���ʹ�ú͹���취 ", "ͼ��ݣ��������ģ����ѽ���ʹ�ð취 ", "�豸����취 ", "ͼ��ݣ��������ģ������ƶ� ",
			"�Ϻ��ڶ���ҵ��ѧͼ���������Դ�ɹ�����취 ", "�������׹���ϸ�� ", "�����Ҷ�����֪ ", "������ѯ����ʹ�ù涨 ",
			"���������Ҷ�����֪ ", "�����𻵡���ʧ�鿯�⳥�涨 ", "���������Ҷ�����֪ ", "���������Ҷ�����֪ ",
			"ͼ����Ĺ涨 ", "�ݲ�¼����ʹ�ù���涨 ", "ͼ��ݽ����������豸ʹ�ü�����취 " };

	private ListView datalistlong = null; // ����ListView���
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>(); // ������ʾ�����ݰ�װ
	private SimpleAdapter simpleAdapter = null; // �������ݵ�ת������
	ImageView mback;
	TextView title;
	private static int[] s1 = new int[] { R.drawable.back_default };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.list_long);
		ExitApplication.getInstance().addActivity(this);
		mback = (ImageView) findViewById(R.id.toolbar_left_image);
		title = (TextView) findViewById(R.id.title);
		title.setText("�����ƶ�");
		mback.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mback
						.setImageDrawable(getResources().getDrawable(s1[0]));
				if (s1[0] == R.drawable.back_default) {
					BackAction.Back(Rules.this, BaseActivity.class,3);
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
					Rules.this.startActivity(intent);
					arg = "0";
					Editor sharedata0 = getSharedPreferences("dt", 0).edit();
					sharedata0.putString("arg2", arg);
					sharedata0.commit();

					break;
				case 1:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "1";
					Editor sharedata1 = getSharedPreferences("dt", 0).edit();
					sharedata1.putString("arg2", arg);
					sharedata1.commit();
					break;
				case 2:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "2";
					Editor sharedata2 = getSharedPreferences("dt", 0).edit();
					sharedata2.putString("arg2", arg);
					sharedata2.commit();
					break;
				case 3:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "3";
					Editor sharedata3 = getSharedPreferences("dt", 0).edit();
					sharedata3.putString("arg2", arg);
					sharedata3.commit();
					break;
				case 4:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "4";
					Editor sharedata4 = getSharedPreferences("dt", 0).edit();
					sharedata4.putString("arg2", arg);
					sharedata4.commit();
					break;
				case 5:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "5";
					Editor sharedata5 = getSharedPreferences("dt", 0).edit();
					sharedata5.putString("arg2", arg);
					sharedata5.commit();
					break;
				case 6:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "6";
					Editor sharedata6 = getSharedPreferences("dt", 0).edit();
					sharedata6.putString("arg2", arg);
					sharedata6.commit();
					break;
				case 7:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "7";
					Editor sharedata7 = getSharedPreferences("dt", 0).edit();
					sharedata7.putString("arg2", arg);
					sharedata7.commit();
					break;
				case 8:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "8";
					Editor sharedata8 = getSharedPreferences("dt", 0).edit();
					sharedata8.putString("arg2", arg);
					sharedata8.commit();
					break;

				case 9:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "9";
					Editor sharedata9 = getSharedPreferences("dt", 0).edit();
					sharedata9.putString("arg2", arg);
					sharedata9.commit();
					break;
				case 10:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "10";
					Editor sharedata10 = getSharedPreferences("dt", 0).edit();
					sharedata10.putString("arg2", arg);
					sharedata10.commit();
					break;
				case 11:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "11";
					Editor sharedata11 = getSharedPreferences("dt", 0).edit();
					sharedata11.putString("arg2", arg);
					sharedata11.commit();
					break;
				case 12:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "12";
					Editor sharedata12 = getSharedPreferences("dt", 0).edit();
					sharedata12.putString("arg2", arg);
					sharedata12.commit();
					break;
				case 13:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "13";
					Editor sharedata13 = getSharedPreferences("dt", 0).edit();
					sharedata13.putString("arg2", arg);
					sharedata13.commit();
					break;
				case 14:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "14";
					Editor sharedata14 = getSharedPreferences("dt", 0).edit();
					sharedata14.putString("arg2", arg);
					sharedata14.commit();
					break;
				case 15:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "15";
					Editor sharedata15 = getSharedPreferences("dt", 0).edit();
					sharedata15.putString("arg2", arg);
					sharedata15.commit();
					break;
				case 16:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "16";
					Editor sharedata16 = getSharedPreferences("dt", 0).edit();
					sharedata16.putString("arg2", arg);
					sharedata16.commit();
					break;
				case 17:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "17";
					Editor sharedata17 = getSharedPreferences("dt", 0).edit();
					sharedata17.putString("arg2", arg);
					sharedata17.commit();
					break;
				case 18:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "18";
					Editor sharedata18 = getSharedPreferences("dt", 0).edit();
					sharedata18.putString("arg2", arg);
					sharedata18.commit();
					break;
				case 19:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "19";
					Editor sharedata19 = getSharedPreferences("dt", 0).edit();
					sharedata19.putString("arg2", arg);
					sharedata19.commit();
					break;
				case 20:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "20";
					Editor sharedata20 = getSharedPreferences("dt", 0).edit();
					sharedata20.putString("arg2", arg);
					sharedata20.commit();
					break;
				case 21:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "21";
					Editor sharedata21 = getSharedPreferences("dt", 0).edit();
					sharedata21.putString("arg2", arg);
					sharedata21.commit();
					break;
				case 22:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "22";
					Editor sharedata22 = getSharedPreferences("dt", 0).edit();
					sharedata22.putString("arg2", arg);
					sharedata22.commit();
					break;
				case 23:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "23";
					Editor sharedata23 = getSharedPreferences("dt", 0).edit();
					sharedata23.putString("arg2", arg);
					sharedata23.commit();
					break;
				case 24:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "24";
					Editor sharedata24 = getSharedPreferences("dt", 0).edit();
					sharedata24.putString("arg2", arg);
					sharedata24.commit();
					break;
				case 25:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "25";
					Editor sharedata25 = getSharedPreferences("dt", 0).edit();
					sharedata25.putString("arg2", arg);
					sharedata25.commit();
					break;
				case 26:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "26";
					Editor sharedata26 = getSharedPreferences("dt", 0).edit();
					sharedata26.putString("arg2", arg);
					sharedata26.commit();
					break;
				case 27:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "27";
					Editor sharedata27 = getSharedPreferences("dt", 0).edit();
					sharedata27.putString("arg2", arg);
					sharedata27.commit();
					break;
				case 28:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "28";
					Editor sharedata28 = getSharedPreferences("dt", 0).edit();
					sharedata28.putString("arg2", arg);
					sharedata28.commit();
					break;
				case 29:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "29";
					Editor sharedata29 = getSharedPreferences("dt", 0).edit();
					sharedata29.putString("arg2", arg);
					sharedata29.commit();
					break;
				case 30:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "30";
					Editor sharedata30 = getSharedPreferences("dt", 0).edit();
					sharedata30.putString("arg2", arg);
					sharedata30.commit();
					break;
				case 31:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "31";
					Editor sharedata31 = getSharedPreferences("dt", 0).edit();
					sharedata31.putString("arg2", arg);
					sharedata31.commit();
					break;
				case 32:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "32";
					Editor sharedata32 = getSharedPreferences("dt", 0).edit();
					sharedata32.putString("arg2", arg);
					sharedata32.commit();
					break;
				case 33:
					intent.setClass(getApplicationContext(), Detail.class);
					Rules.this.startActivity(intent);
					arg = "33";
					Editor sharedata33 = getSharedPreferences("dt", 0).edit();
					sharedata33.putString("arg2", arg);
					sharedata33.commit();
					break;
					
				}
				
			}
		});

	}

	public boolean OnKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			BackAction.Back(Rules.this, BaseActivity.class,3);

		}

		return false;

	}

}