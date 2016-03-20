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
	private String data[] = { "馆长致辞", "馆况介绍", "结构介绍", "开放时间", "馆舍导航", "规章制度",
			"分馆导航" };

	private ListView datalist2 = null; // 定义ListView组件
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>(); // 定义显示的内容包装
	private SimpleAdapter simpleAdapter = null; // 进行数据的转换操作
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

		this.datalist2 = (ListView) super.findViewById(R.id.datalist2); // 取得组件
		for (int x = 0; x < this.data.length; x++) {
			Map<String, String> map = new HashMap<String, String>(); // 定义Map集合，保存每一行数据
			map.put("pic", String.valueOf(this.pic[x])); // 与data_list.xml中的TextView组加匹配
			map.put("title", this.data[x]); // 与data_list.xml中的TextView组加匹配
			map.put("score", String.valueOf(R.drawable.arrow));
			this.list.add(map); // 保存了所有的数据行
		}
		this.simpleAdapter = new SimpleAdapter(this, this.list,
				R.layout.list_normal, new String[] { "pic", "title", "score" } // Map中的key的名称
				, new int[] { R.id.pic, R.id.title, R.id.score }); // 是data_list.xml中定义的组件的资源ID
		this.datalist2.setAdapter(this.simpleAdapter);

		datalist2.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// arg2这个就是数据中的position的！~
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
		builder.setMessage("确定要退出吗?");
		builder.setTitle("提示");
		builder.setPositiveButton("确认",
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
		builder.setNegativeButton("取消",
				new android.content.DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		builder.create().show();
	}

}