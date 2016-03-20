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
public class BaseActivity extends ActivityGroup { // 继承ActivityGroup

	private GridView gridviewToolbar; // 定义GridView工具条
	private int num=0;
	private MenuImageAdapter menu = null; // 图片适配器
	private Intent intent = null; // 要操作的Intent
	private LinearLayout content = null; // 显示内容的布局管理器
	private int width = 0; // 工具栏每个组件的宽度
	private int height = 0; // 工具栏的高度
	private int menu_img[] = new int[] { R.drawable.menu_main,
			R.drawable.menu_news, R.drawable.menu_sms, R.drawable.menu_more,
			R.drawable.menu_exit };
	private int menu_img1[] = new int[] { R.drawable.menu_main1,
			R.drawable.menu_news1, R.drawable.menu_sms1, R.drawable.menu_more1,
			R.drawable.menu_exit1 }; // 图片显示资源ID

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.requestWindowFeature(Window.FEATURE_NO_TITLE); // 不显示标题
		super.setContentView(R.layout.activity_home); // 调用布局管理器
		ExitApplication.getInstance().addActivity(this);
		this.gridviewToolbar = (GridView) super.findViewById(R.id.gridviewbar); // 取得组件
		this.content = (LinearLayout) super.findViewById(R.id.content); // 取得组件
		this.gridviewToolbar.setNumColumns(this.menu_img.length); // 设置每行的显示列数
		this.gridviewToolbar.setSelector(new ColorDrawable(Color.TRANSPARENT));// 选中的时候为透明色
		this.gridviewToolbar.setGravity(Gravity.CENTER); // 居中显示
		this.gridviewToolbar.setVerticalSpacing(0); // 垂直间隔为0
		this.gridviewToolbar.setBackgroundColor(Color.LTGRAY); // 背景颜色设置为灰色
		this.gridviewToolbar.setBackgroundResource(drawable.menu_label);
		this.width = super.getWindowManager().getDefaultDisplay().getWidth()
				/ this.menu_img.length; // 计算平均宽度
		this.height = super.getWindowManager().getDefaultDisplay().getHeight() / 8; // 高度
		this.menu = new MenuImageAdapter(this, this.menu_img, width, height,
				this.menu_img1); // 实例化适配器
		this.gridviewToolbar.setAdapter(menu); // 设置显示数据
		Intent intentnum=getIntent();
		if(intentnum!=null){
			int n=intentnum.getIntExtra("num", 0);
			num=n;
		}
		
		this.switchActivity(num); // 默认打开第一个
		this.gridviewToolbar
				.setOnItemClickListener(new OnItemClickListenerImpl()); // 选中监听

	}

	private class OnItemClickListenerImpl implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			BaseActivity.this.switchActivity(position); // 切换选项
		}

	}

	/**
	 * 根据ID打开指定的Activity
	 * 
	 * @param id
	 *            GridView选中项的序号
	 */
	private void switchActivity(int id) { // 切换视图
		this.menu.setFocus(id); // 选中项获得高亮
		this.content.removeAllViews(); // 先清除容器中所有View
		switch (id) { // 根据操作实例化Intent
		case 0: // 指定操作的Intent
			this.intent = new Intent(BaseActivity.this, UiRetrieval.class);
			break;
		case 1: // 指定操作的Intent
			this.intent = new Intent(BaseActivity.this, UiReader.class);
			break;
		case 2: // 指定操作的Intent
			this.intent = new Intent(BaseActivity.this, UiActivity.class);
			break;
		case 3: // 弹出窗口
			this.intent = new Intent(BaseActivity.this, UiInfo.class);
			break;
		case 4: // 指定操作的Intent
			this.intent = new Intent(BaseActivity.this, UiPerson.class);
			break;
		}
		if (this.intent != null) {
			this.intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // 增加标记
			Window subActivity = this.getLocalActivityManager().startActivity(
					"subActivity", this.intent); // Activity 转为 View
			this.content.addView(subActivity.getDecorView(),
					LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT); // 容器添加View
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