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
	private String data[] = { "去评分 ", "反馈我们", "新浪微博 ", "检查新版本", "分享应用 " };

	private ListView datalistshort = null; // 定义ListView组件
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>(); // 定义显示的内容包装
	private SimpleAdapter simpleAdapter = null; // 进行数据的转换操作
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
		logoname.setText("移动图书馆 " + versionName);
		title.setText("关于我们");

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
		this.datalistshort = (ListView) super.findViewById(R.id.datalistshort); // 取得组件
		for (int x = 0; x < this.data.length; x++) {
			Map<String, String> map = new HashMap<String, String>(); // 定义Map集合，保存每一行数据
			map.put("pic", String.valueOf(this.pic[x])); // 与data_list.xml中的TextView组加匹配
			map.put("title", this.data[x]); // 与data_list.xml中的TextView组加匹配
			this.list.add(map); // 保存了所有的数据行
		}
		this.simpleAdapter = new SimpleAdapter(this, this.list,
				R.layout.list_normal, new String[] { "pic", "title" } // Map中的key的名称
				, new int[] { R.id.pic, R.id.title }); // 是data_list.xml中定义的组件的资源ID
		this.datalistshort.setAdapter(this.simpleAdapter);
		datalistshort.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// arg2这个就是数据中的position的！~
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
					// 显示对话框
					// mDialog.show();
					break;
				case 4:
					Intent shareintent = new Intent(Intent.ACTION_SEND);
					// 分享的数据类型
					shareintent.setType("text/plain");
					// 分享的主题
					shareintent.putExtra(Intent.EXTRA_SUBJECT, "好友分享");
					// 分享的内容
					shareintent
							.putExtra(
									Intent.EXTRA_TEXT,
									"我正在使用二工大图书馆，你也加入吧！！下载地址：http://shouji.360tpcdn.com/141008/b2a1171b8ac6cfbf4796c21bc3264876/com.sspu.library_2.apk");
					// 允许启动新的Activity
					shareintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					// 目标应用寻找对话框的标题
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

	public static String getVersion(Context context)// 获取版本号
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
