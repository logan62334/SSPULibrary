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
			"网站的图书查寻在哪里 ",
			"如何借用图书馆的自助研讨室？",
			"河南岸和小高层学生公寓如何上网？",
			"无线网络如何上网？ ",
			"超星浏览器下载的电子书只能在下载电脑上看吗？这样很不方便，而且每次来，原用于下载的电脑很可能其他同学正在用。有什么办法能放到自己电脑上看呢？ ",
			"请问为什么有的论文不能全文下载，谢谢 ", "为什么某个数据库使用了一段时间，在图书馆网站的电子资源中就没有链接不能使用了 ",
			"为什么有时不能使用某个数据库？", "如何了解图书馆各种电子资源的使用方法，如何获得帮助？", "图书馆培训讲座的内容有哪些？",
			"在家想访问图书馆数字资源，请问该如何使用 ", "我馆采用的图书分类方法是什么？" };

	private ListView datalistlong = null; // 定义ListView组件
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>(); // 定义显示的内容包装
	private SimpleAdapter simpleAdapter = null; // 进行数据的转换操作
	ImageView mback;
	TextView title;
	private static int[] s1 = new int[] { R.drawable.back_default };

	// 初始化窗口
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 装载了View
		super.setContentView(R.layout.list_long);
		ExitApplication.getInstance().addActivity(this);
		// 获得ImageView的对象实例
		mback = (ImageView) findViewById(R.id.toolbar_left_image);
		// 获得TextView的对象实例
		title = (TextView) findViewById(R.id.title);
		// 设置title的值
		title.setText("常见问题");
		// 为ImageView添加单击事件监听器
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
		// 多个按钮共用一个单击事件方法，通过按钮的id区分单击了哪个按钮
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
	//捕捉按键按下的事件
	public boolean OnKeyDown(int keyCode, KeyEvent event) {
		//如果按下的是“返回键”并且重复次数是0则执行dialog方法并且返回true不再执行回调方法
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
