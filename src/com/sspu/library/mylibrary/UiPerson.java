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
	private String data[] = new String[] { "��ǰ����", "��ʷ����", "�߻�����", "��������", "��������" };

	private ListView datalist3 = null; // ����ListView���
	private List<Map<String, String>> list = new ArrayList<Map<String, String>>(); // ������ʾ�����ݰ�װ
	private SimpleAdapter simpleAdapter = null; // �������ݵ�ת������
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
        // ��GPS��ȡ����Ķ�λ��Ϣ   
		
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
		this.datalist3 = (ListView) super.findViewById(R.id.datalist3); // ȡ�����
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
		this.datalist3.setAdapter(this.simpleAdapter);
		datalist3.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// arg2������������е�position�ģ�~
				Intent intent = new Intent();
				SharedPreferences sharedata = getSharedPreferences("data", 0);
				String data = sharedata.getString("sspuuserid", null);

				switch (arg2) {
				case 0:
					if (data == null || "".equals(data)) {
						// δ��¼����ʾ�û����µ�¼������ת����½ҳ��
						Toast.makeText(UiPerson.this,
								"δ��¼�����¼��ʹ��", Toast.LENGTH_SHORT)
								.show();
						intent.setClass(getApplicationContext(), Login.class);
						UiPerson.this.startActivity(intent);
//						MyActivity3.this.finish();
						break;
					} else {
						// �ѵ�¼����ת����Ӧ����ҳ��
						Toast.makeText(UiPerson.this, "�ѵ�¼",
								Toast.LENGTH_SHORT).show();
						intent.setClass(getApplicationContext(),
								LendingInfo.class);
						UiPerson.this.startActivity(intent);
						break;
					}
				case 1:
					if (data == null || "".equals(data)) {
						// δ��¼����ʾ�û����µ�¼������ת����½ҳ��
						Toast.makeText(UiPerson.this,
								"δ��¼�����¼��ʹ��", Toast.LENGTH_SHORT)
								.show();
						intent.setClass(getApplicationContext(), Login.class);
						UiPerson.this.startActivity(intent);
						break;
					} else {
						// �ѵ�¼����ת����Ӧ����ҳ��
						Toast.makeText(UiPerson.this, "�ѵ�¼",
								Toast.LENGTH_SHORT).show();
						intent.setClass(getApplicationContext(),
								History.class);
						UiPerson.this.startActivity(intent);
						break;
					}
				case 2:
					if (data == null || "".equals(data)) {
						// δ��¼����ʾ�û����µ�¼������ת����½ҳ��
						Toast.makeText(UiPerson.this,
								"δ��¼�����¼��ʹ��", Toast.LENGTH_SHORT)
								.show();
						intent.setClass(getApplicationContext(), Login.class);
						UiPerson.this.startActivity(intent);
						break;
					} else {
						// �ѵ�¼����ת����Ӧ����ҳ��
						Toast.makeText(UiPerson.this, "�ѵ�¼",
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
	    * ��ʼ����		
	    * @param view
	    */
	   public void startNavi(){		
			int lat = (int) (mLat1 *1E6);
		   	int lon = (int) (mLon1 *1E6);   	
		   	GeoPoint pt1 = new GeoPoint(lat, lon);
			lat = (int) (mLat2 *1E6);
		   	lon = (int) (mLon2 *1E6);
		    GeoPoint pt2 = new GeoPoint(lat, lon);
		    // ���� ��������
	        NaviPara para = new NaviPara();
	        para.startPoint = pt1;
	        para.startName= "�����￪ʼ";
	        para.endPoint  = pt2;
	        para.endName   = "���������";
	        
	        try {
	        	
				 BaiduMapNavigation.openBaiduMapNavi(para, this);
				 
			} catch (BaiduMapAppNotSupportNaviException e) {
				e.printStackTrace();
				  AlertDialog.Builder builder = new AlertDialog.Builder(this);
				  builder.setMessage("����δ��װ�ٶȵ�ͼapp��app�汾���ͣ����ȷ�ϰ�װ��");
				  builder.setTitle("��ʾ");
				  builder.setPositiveButton("ȷ��", new OnClickListener() {
				   @Override
				   public void onClick(DialogInterface dialog, int which) {
					 dialog.dismiss();
					 BaiduMapNavigation.GetLatestBaiduMapApp(UiPerson.this);
				   }
				  });

				  builder.setNegativeButton("ȡ��", new OnClickListener() {
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