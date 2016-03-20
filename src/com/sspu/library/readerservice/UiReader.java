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
	private String data[] = { "新闻动态", "公告动态", "本馆概况", "服务指南", "新书推荐", "常见问题",
			"新生入馆帮助", "设备报修" };

	private ListView datalist1 = null; // 定义ListView组件
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>(); // 定义显示的内容包装
	private SimpleAdapter simpleAdapter = null; // 进行数据的转换操作
	ImageView maccount;
	private static int[] s1 = new int[] { R.drawable.account_default };
    //初始化窗口
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//装载了View
		super.setContentView(R.layout.activity_reader_service);
		ExitApplication.getInstance().addActivity(this);
		//获得ImageView的对象实例
		maccount = (ImageView) findViewById(R.id.toolbar_right_image);
		//为ImageView添加单击事件监听器
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
		// 取得组件
		this.datalist1 = (ListView) super.findViewById(R.id.datalist1); 
		for (int x = 0; x < this.data.length; x++) {
			// 定义Map集合，保存每一行数据
			Map<String, String> map = new HashMap<String, String>(); 
			// 与data_list.xml中的TextView组加匹配
			map.put("pic", String.valueOf(this.pic[x])); 
			// 与data_list.xml中的TextView组加匹配
			map.put("title", this.data[x]); 
			map.put("score", String.valueOf(R.drawable.arrow));
			// 保存了所有的数据行
			this.list.add(map); 
		}
		this.simpleAdapter = new SimpleAdapter(this, this.list,
				R.layout.list_normal, new String[] { "pic", "title",
						"score" } // Map中的key的名称
				, new int[] { R.id.pic, R.id.title, R.id.score }); // 是data_list.xml中定义的组件的资源ID
		this.datalist1.setAdapter(this.simpleAdapter);
		// 多个按钮共用一个单击事件方法，通过按钮的id区分单击了哪个按钮
		datalist1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// arg2这个就是数据中的position的！~
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
    //捕捉按键按下的事件
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		//如果按下的是“返回键”并且重复次数是0则执行dialog方法并且返回true不再执行回调方法
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			dialog();
			return true;
		}
		return true;
	}

	// 通用对话框的显示
	protected void dialog() {
		AlertDialog.Builder builder = new Builder(UiReader.this);
		// 设置对话框显示的信息
		builder.setMessage("确定要退出吗?");
		// 设置对话框显示的标题
		builder.setTitle("提示");
		// 设置对话框的按钮
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
		//显示对话框
		builder.create().show();
	}
}