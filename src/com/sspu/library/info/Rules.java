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
	private String data[] = { "图书馆（网络中心）自助研讨室借用管理规定 ", "关于办理《校园临时教工卡》的规定 ",
			"关于餐饮系统POS机脱机使用数据上传的规定 ", "关于提供校园卡系统接口的规定 ", "校园卡设备维修办法 ",
			"上海第二工业大学网络中心机房服务器托管规定 ", "校园卡用户须知 ", "上海第二工业大学校园网故障处理（流程）办法 ",
			"用户入网安全责任书", "校园网络系统安全管理制度 ", "关于校园网信息安全事故责任追究的规定 ", "网站建设申请表 ",
			"上海第二工业大学域名申请表 ", "各级校务网站建设与管理基本规范 ", "校园网信息发布管理办法 ", "直属党支部工作制度 ",
			"直属党支部中心组学习制度 ", "政治学习制度 ", " [2010-12-21]党政联席会议制度 ",
			"图书馆公章使用和管理办法 ", "图书馆（网络中心）经费津贴使用办法 ", "设备管理办法 ", "图书馆（网络中心）会议制度 ",
			"上海第二工业大学图书馆文献资源采购管理办法 ", "受赠文献管理细则 ", "视听室读者须知 ", "公共查询电脑使用规定 ",
			"电子阅览室读者须知 ", "读者损坏、遗失书刊赔偿规定 ", "电子阅览室读者须知 ", "报刊阅览室读者须知 ",
			"图书借阅规定 ", "演播录音室使用管理规定 ", "图书馆教育技术部设备使用及服务办法 " };

	private ListView datalistlong = null; // 定义ListView组件
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>(); // 定义显示的内容包装
	private SimpleAdapter simpleAdapter = null; // 进行数据的转换操作
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
		title.setText("规章制度");
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
		this.datalistlong = (ListView) super.findViewById(R.id.datalistlong); // 取得组件
		for (int x = 0; x < this.data.length; x++) {
			Map<String, String> map = new HashMap<String, String>(); // 定义Map集合，保存每一行数据
			map.put("pic", String.valueOf(this.pic[x])); // 与data_list.xml中的TextView组加匹配
			map.put("title", this.data[x]); // 与data_list.xml中的TextView组加匹配
			this.list.add(map); // 保存了所有的数据行
		}
		this.simpleAdapter = new SimpleAdapter(this, this.list,
				R.layout.list_normal, new String[] { "pic", "title" } // Map中的key的名称
				, new int[] { R.id.pic, R.id.title }); // 是data_list.xml中定义的组件的资源ID
		this.datalistlong.setAdapter(this.simpleAdapter);
		datalistlong.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// arg2这个就是数据中的position的！~
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