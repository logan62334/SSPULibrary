package com.sspu.library.mylibrary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.baidu.mapapi.navi.BaiduMapAppNotSupportNaviException;
import com.baidu.mapapi.navi.BaiduMapNavigation;
import com.baidu.mapapi.navi.NaviPara;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.sspu.library.ExitApplication;
import com.sspu.library.R;
import com.sspu.library.service.Login;

public class UiPerson extends Activity {
	private int pic[] = new int[] { R.drawable.zxdt, R.drawable.bggk,
			R.drawable.fwzn, R.drawable.xstj ,R.drawable.zxdt};
	private String data[] = new String[] { "当前借阅", "历史借阅", "催还提醒", "导航服务", "关于我们" };

	private ListView datalist3 = null; // 定义ListView组件
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>(); // 定义显示的内容包装
	private SimpleAdapter simpleAdapter = null; // 进行数据的转换操作
	ImageView maccount;
	private static int[] s1 = new int[] { R.drawable.account_default };
	 private LocationManager locationManager; 
	 double mLat1;
	 double mLon1;
   	double mLat2 = 31.268792;   
   	double mLon2 = 121.65736;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_my_library);
		ExitApplication.getInstance().addActivity(this);
		maccount = (ImageView) findViewById(R.id.toolbar_right_image);
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);  
        // 从GPS获取最近的定位信息   
		
        Location location = locationManager  
                .getLastKnownLocation(LocationManager.GPS_PROVIDER); 
        if(location!=null)
        {
		 mLat1 = location.getLatitude(); 
	   	mLon1 = location.getLongitude(); 
        }
		maccount.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				maccount
						.setImageDrawable(getResources().getDrawable(s1[0]));
				if (s1[0] == R.drawable.account_default) {
					Intent myIntent = new Intent();
					myIntent = new Intent(UiPerson.this, Login.class);
					startActivity(myIntent);
					UiPerson.this.finish();
				}
			}
		});
		this.datalist3 = (ListView) super.findViewById(R.id.datalist3); // 取得组件
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
		this.datalist3.setAdapter(this.simpleAdapter);
		datalist3.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// arg2这个就是数据中的position的！~
				Intent intent = new Intent();
				SharedPreferences sharedata = getSharedPreferences("data", 0);
				String data = sharedata.getString("sspuuserid", null);

				switch (arg2) {
				case 0:
					if (data == null || "".equals(data)) {
						// 未登录，提示用户重新登录，并跳转至登陆页面
						Toast.makeText(UiPerson.this,
								"未登录，请登录后使用", Toast.LENGTH_SHORT)
								.show();
						intent.setClass(getApplicationContext(), Login.class);
						UiPerson.this.startActivity(intent);
//						MyActivity3.this.finish();
						break;
					} else {
						// 已登录，跳转至相应功能页面
						Toast.makeText(UiPerson.this, "已登录",
								Toast.LENGTH_SHORT).show();
						intent.setClass(getApplicationContext(),
								LendingInfo.class);
						UiPerson.this.startActivity(intent);
						break;
					}
				case 1:
					if (data == null || "".equals(data)) {
						// 未登录，提示用户重新登录，并跳转至登陆页面
						Toast.makeText(UiPerson.this,
								"未登录，请登录后使用", Toast.LENGTH_SHORT)
								.show();
						intent.setClass(getApplicationContext(), Login.class);
						UiPerson.this.startActivity(intent);
						break;
					} else {
						// 已登录，跳转至相应功能页面
						Toast.makeText(UiPerson.this, "已登录",
								Toast.LENGTH_SHORT).show();
						intent.setClass(getApplicationContext(),
								History.class);
						UiPerson.this.startActivity(intent);
						break;
					}
				case 2:
					if (data == null || "".equals(data)) {
						// 未登录，提示用户重新登录，并跳转至登陆页面
						Toast.makeText(UiPerson.this,
								"未登录，请登录后使用", Toast.LENGTH_SHORT)
								.show();
						intent.setClass(getApplicationContext(), Login.class);
						UiPerson.this.startActivity(intent);
						break;
					} else {
						// 已登录，跳转至相应功能页面
						Toast.makeText(UiPerson.this, "已登录",
								Toast.LENGTH_SHORT).show();
						intent.setClass(getApplicationContext(),
								Recall.class);
						UiPerson.this.startActivity(intent);
						break;
					}
				case 3:
					startNavi();
					break;
				case 4:
					intent.setClass(getApplicationContext(),
							About.class);
					UiPerson.this.startActivity(intent);

					break;
				}

			}
		});

	}

	   /**
	    * 开始导航		
	    * @param view
	    */
	   public void startNavi(){		
			int lat = (int) (mLat1 *1E6);
		   	int lon = (int) (mLon1 *1E6);   	
		   	GeoPoint pt1 = new GeoPoint(lat, lon);
			lat = (int) (mLat2 *1E6);
		   	lon = (int) (mLon2 *1E6);
		    GeoPoint pt2 = new GeoPoint(lat, lon);
		    // 构建 导航参数
	        NaviPara para = new NaviPara();
	        para.startPoint = pt1;
	        para.startName= "从这里开始";
	        para.endPoint  = pt2;
	        para.endName   = "到这里结束";
	        
	        try {
	        	
				 BaiduMapNavigation.openBaiduMapNavi(para, this);
				 
			} catch (BaiduMapAppNotSupportNaviException e) {
				e.printStackTrace();
				  AlertDialog.Builder builder = new AlertDialog.Builder(this);
				  builder.setMessage("您尚未安装百度地图app或app版本过低，点击确认安装？");
				  builder.setTitle("提示");
				  builder.setPositiveButton("确认", new OnClickListener() {
				   @Override
				   public void onClick(DialogInterface dialog, int which) {
					 dialog.dismiss();
					 BaiduMapNavigation.GetLatestBaiduMapApp(UiPerson.this);
				   }
				  });

				  builder.setNegativeButton("取消", new OnClickListener() {
				   @Override
				   public void onClick(DialogInterface dialog, int which) {
				    dialog.dismiss();
				   }
				  });

				  builder.create().show();
				 }
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
		System.out.println("MyActivityGroupProjectDemo3--dialog --7");
		AlertDialog.Builder builder = new Builder(UiPerson.this);
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