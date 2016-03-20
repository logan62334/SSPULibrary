package com.sspu.library;

import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.sspu.library.R;
import com.sspu.library.R.drawable;
import com.sspu.library.booklistsearch.UiRetrieval;
import com.sspu.library.exhibitionlecture.UiActivity;
import com.sspu.library.info.UiInfo;
import com.sspu.library.mylibrary.UiPerson;
import com.sspu.library.readerservice.UiReader;

@SuppressWarnings("deprecation")
public class BaseActivity extends ActivityGroup { // �̳�ActivityGroup

	private GridView gridviewToolbar; // ����GridView������
	private int num=0;
	private MenuImageAdapter menu = null; // ͼƬ������
	private Intent intent = null; // Ҫ������Intent
	private LinearLayout content = null; // ��ʾ���ݵĲ��ֹ�����
	private int width = 0; // ������ÿ������Ŀ��
	private int height = 0; // �������ĸ߶�
	private int menu_img[] = new int[] { R.drawable.menu_main,
			R.drawable.menu_news, R.drawable.menu_sms, R.drawable.menu_more,
			R.drawable.menu_exit };
	private int menu_img1[] = new int[] { R.drawable.menu_main1,
			R.drawable.menu_news1, R.drawable.menu_sms1, R.drawable.menu_more1,
			R.drawable.menu_exit1 }; // ͼƬ��ʾ��ԴID

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE); // ����ʾ����
		super.setContentView(R.layout.activity_home); // ���ò��ֹ�����
		ExitApplication.getInstance().addActivity(this);
		this.gridviewToolbar = (GridView) super.findViewById(R.id.gridviewbar); // ȡ�����
		this.content = (LinearLayout) super.findViewById(R.id.content); // ȡ�����
		this.gridviewToolbar.setNumColumns(this.menu_img.length); // ����ÿ�е���ʾ����
		this.gridviewToolbar.setSelector(new ColorDrawable(Color.TRANSPARENT));// ѡ�е�ʱ��Ϊ͸��ɫ
		this.gridviewToolbar.setGravity(Gravity.CENTER); // ������ʾ
		this.gridviewToolbar.setVerticalSpacing(0); // ��ֱ���Ϊ0
		this.gridviewToolbar.setBackgroundColor(Color.LTGRAY); // ������ɫ����Ϊ��ɫ
		this.gridviewToolbar.setBackgroundResource(drawable.menu_label);
		this.width = super.getWindowManager().getDefaultDisplay().getWidth()
				/ this.menu_img.length; // ����ƽ�����
		this.height = super.getWindowManager().getDefaultDisplay().getHeight() / 8; // �߶�
		this.menu = new MenuImageAdapter(this, this.menu_img, width, height,
				this.menu_img1); // ʵ����������
		this.gridviewToolbar.setAdapter(menu); // ������ʾ����
		Intent intentnum=getIntent();
		if(intentnum!=null){
			int n=intentnum.getIntExtra("num", 0);
			num=n;
		}
		
		this.switchActivity(num); // Ĭ�ϴ򿪵�һ��
		this.gridviewToolbar
				.setOnItemClickListener(new OnItemClickListenerImpl()); // ѡ�м���

	}

	private class OnItemClickListenerImpl implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			BaseActivity.this.switchActivity(position); // �л�ѡ��
		}

	}

	/**
	 * ����ID��ָ����Activity
	 * 
	 * @param id
	 *            GridViewѡ��������
	 */
	private void switchActivity(int id) { // �л���ͼ
		this.menu.setFocus(id); // ѡ�����ø���
		this.content.removeAllViews(); // ���������������View
		switch (id) { // ���ݲ���ʵ����Intent
		case 0: // ָ��������Intent
			this.intent = new Intent(BaseActivity.this, UiRetrieval.class);
			break;
		case 1: // ָ��������Intent
			this.intent = new Intent(BaseActivity.this, UiReader.class);
			break;
		case 2: // ָ��������Intent
			this.intent = new Intent(BaseActivity.this, UiActivity.class);
			break;
		case 3: // ��������
			this.intent = new Intent(BaseActivity.this, UiInfo.class);
			break;
		case 4: // ָ��������Intent
			this.intent = new Intent(BaseActivity.this, UiPerson.class);
			break;
		}
		if (this.intent != null) {
			this.intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // ���ӱ��
			Window subActivity = this.getLocalActivityManager().startActivity(
					"subActivity", this.intent); // Activity תΪ View
			this.content.addView(subActivity.getDecorView(),
					LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT); // �������View
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
		AlertDialog.Builder builder = new Builder(BaseActivity.this);
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