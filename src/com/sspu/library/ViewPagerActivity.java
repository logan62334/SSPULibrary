package com.sspu.library;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

/*
 * 视图滑动组件
 */
public class ViewPagerActivity extends Activity implements OnClickListener,
		OnPageChangeListener {

	private ViewPager vp;
	private ViewPagerAdapter vpAdapter;
	private List<View> views;

	private static final int[] pics = { R.drawable.gsdh1, R.drawable.gsdh2,
			R.drawable.gsdh3, R.drawable.gsdh4 };// 定义欢迎动画的资源

	private ImageView[] dots;// 导航点

	private int currentIndex;// 当前的索引值

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.widget_pic_show);
		ExitApplication.getInstance().addActivity(this);// 将Activity加入到栈中
		views = new ArrayList<View>();
		LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);// 定义图片的布局参数
		for (int i = 0; i < pics.length; i++) {
			ImageView iv = new ImageView(this);
			iv.setLayoutParams(mParams);
			iv.setImageResource(pics[i]);
			views.add(iv);
		}
		vp = (ViewPager) findViewById(R.id.viewpager);
		vpAdapter = new ViewPagerAdapter(views);
		vp.setAdapter(vpAdapter);
		vp.setOnPageChangeListener(this);

		initDots();

	}

	// 初始化导航点
	private void initDots() {
		LinearLayout ll = (LinearLayout) findViewById(R.id.ll);

		dots = new ImageView[pics.length];

		for (int i = 0; i < pics.length; i++) {
			dots[i] = (ImageView) ll.getChildAt(i);
			dots[i].setEnabled(true);
			dots[i].setOnClickListener(this);
			dots[i].setTag(i);
		}

		currentIndex = 0;
		dots[currentIndex].setEnabled(false);
	}

	// 设置当前视图
	private void setCurView(int position) {
		if (position < 0 || position >= pics.length) {
			return;
		}

		vp.setCurrentItem(position);
	}

	// 设置当前导航点
	private void setCurDot(int positon) {
		if (positon < 0 || positon > pics.length - 1 || currentIndex == positon) {
			return;
		}

		dots[positon].setEnabled(false);
		dots[currentIndex].setEnabled(true);

		currentIndex = positon;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int arg0) {
		setCurDot(arg0);
	}

	@Override
	public void onClick(View v) {
		int position = (Integer) v.getTag();
		setCurView(position);
		setCurDot(position);
	}
}