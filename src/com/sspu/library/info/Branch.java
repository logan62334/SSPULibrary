package com.sspu.library.info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
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

public class Branch extends Activity {

	private int pic[] = new int[] { R.drawable.xsrgbz, R.drawable.xsrgbz };
	private String data[] = { "曹路社区", "艺术图书馆" };

	private ListView datalistshort = null; // 定义ListView组件
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>(); // 定义显示的内容包装
	private SimpleAdapter simpleAdapter = null; // 进行数据的转换操作
	ImageView mback;
	TextView title;
	private static int[] s1 = new int[] { R.drawable.back_default };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.list_short);
		ExitApplication.getInstance().addActivity(this);
		mback = (ImageView) findViewById(R.id.toolbar_left_image);
		title = (TextView) findViewById(R.id.title);
		title.setText("分馆导航");
		mback.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mback
						.setImageDrawable(getResources().getDrawable(s1[0]));
				if (s1[0] == R.drawable.back_default) {
					Intent myIntent = new Intent();
					myIntent = new Intent(Branch.this, BaseActivity.class);
					myIntent.putExtra("num", 3);
					startActivity(myIntent);
					Branch.this.finish();
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
					intent.setClass(getApplicationContext(),
							BranchMap.class);
					Branch.this.startActivity(intent);
					Branch.this.finish();// Activityname就是您要结束的那个Activity类的名字
					break;
				case 1:
					intent.setClass(getApplicationContext(),
							BranchMap.class);
					Branch.this.startActivity(intent);
					Branch.this.finish();// Activityname就是您要结束的那个Activity类的名字
					break;
				}
			}
		});

	}

	public boolean OnKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

			Intent myIntent;

			myIntent = new Intent(Branch.this, BaseActivity.class);
			myIntent.putExtra("num", 3);
			startActivity(myIntent);

			this.finish();

		}

		return false;

	}
}
