package com.sspu.library;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;


public class Welcome extends Activity {

	private ViewPager mViewPager;
	private ImageView mPage0;// 定义图片1
	private ImageView mPage1;// 定义图片2
	private ImageView mPage2;// 定义图片3
	private ImageView mPage3;// 定义图片4
	private ImageView mPage4;// 定义图片5

	public int currIndex = 0;// 当前索引

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN); // 全屏显示
		setContentView(R.layout.widget_splash_screen);
		// 获得视图view
		mViewPager = (ViewPager) findViewById(R.id.whatsnew_viewpager);
		mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
		mPage0 = (ImageView) findViewById(R.id.page0);
		mPage1 = (ImageView) findViewById(R.id.page1);
		mPage2 = (ImageView) findViewById(R.id.page2);
		mPage3 = (ImageView) findViewById(R.id.page3);
		mPage4 = (ImageView) findViewById(R.id.page4);

		// 将要分页显示的View装入数组中
		LayoutInflater mLi = LayoutInflater.from(this);
		View view1 = mLi.inflate(R.layout.widget_splash_screen_01, null);
		View view2 = mLi.inflate(R.layout.widget_splash_screen_02, null);
		View view3 = mLi.inflate(R.layout.widget_splash_screen_03, null);
		View view4 = mLi.inflate(R.layout.widget_splash_screen_04, null);
		View view5 = mLi.inflate(R.layout.widget_splash_screen_05, null);

		// 每个页面的view数据
		final ArrayList<View> views = new ArrayList<View>();
		views.add(view1);
		views.add(view2);
		views.add(view3);
		views.add(view4);
		views.add(view5);

		// 填充ViewPager的数据适配器
		PagerAdapter mPagerAdapter = new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return views.size();
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				((ViewPager) container).removeView(views.get(position));
			}

			@Override
			public Object instantiateItem(View container, int position) {
				((ViewPager) container).addView(views.get(position));
				return views.get(position);
			}
		};

		mViewPager.setAdapter(mPagerAdapter);
	}

	// 页面改变时的动作
	public class MyOnPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageSelected(int arg0) {
			switch (arg0) {
			case 0:
				mPage0.setImageDrawable(getResources().getDrawable(
						R.drawable.page_now));
				mPage1.setImageDrawable(getResources().getDrawable(
						R.drawable.page));
				break;
			case 1:
				mPage1.setImageDrawable(getResources().getDrawable(
						R.drawable.page_now));
				mPage0.setImageDrawable(getResources().getDrawable(
						R.drawable.page));
				mPage2.setImageDrawable(getResources().getDrawable(
						R.drawable.page));
				break;
			case 2:
				mPage2.setImageDrawable(getResources().getDrawable(
						R.drawable.page_now));
				mPage1.setImageDrawable(getResources().getDrawable(
						R.drawable.page));
				mPage3.setImageDrawable(getResources().getDrawable(
						R.drawable.page));
				break;
			case 3:
				mPage3.setImageDrawable(getResources().getDrawable(
						R.drawable.page_now));
				mPage4.setImageDrawable(getResources().getDrawable(
						R.drawable.page));
				mPage2.setImageDrawable(getResources().getDrawable(
						R.drawable.page));
				break;
			case 4:
				mPage4.setImageDrawable(getResources().getDrawable(
						R.drawable.page_now));
				mPage3.setImageDrawable(getResources().getDrawable(
						R.drawable.page));
				// mPage5.setImageDrawable(getResources().getDrawable(R.drawable.page));
				break;
			}
			currIndex = arg0;
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}

	// 开始按钮进入程序主界面
	public void startbutton(View v) {

		Intent intent = new Intent();
		intent.setClass(Welcome.this, BaseActivity.class);
		startActivity(intent);
		this.finish();
	}

}
