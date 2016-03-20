package com.sspu.library.booklistsearch;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sspu.library.BaseActivity;
import com.sspu.library.ExitApplication;
import com.sspu.library.R;
import com.sspu.library.data.Book;
import com.sspu.library.data.VisitParameters;

public class Searchresult_main extends Activity {

	ListView listView;
	ImageButton mback;
	public int lastItem = 0;
	private int per = 6;
	private int arrayListSize = 0;
	private VisitParameters vp = new VisitParameters();
	LinearLayout loadingLayout;
	private listViewAdapter adapter;
	ArrayList<Book> bookArrayList;
	private static int[] s1 = new int[] { R.drawable.back_default };
	private static Handler handler = new Handler();
	private ProgressDialog dialog;
	Object obj;
	private int scrollState;
	private View footerView;
	Bitmap bitmap;
	String url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.list_searchresult);
		ExitApplication.getInstance().addActivity(this);
		dialog = new ProgressDialog(this);
		dialog.setTitle("提示");
		dialog.setMessage("正在加载，请稍后...");
		dialog.setCancelable(false);
		mback = (ImageButton) findViewById(R.id.imageButton1);
		mback.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mback.setImageDrawable(getResources().getDrawable(s1[0]));
				if (s1[0] == R.drawable.back_default) {
					Intent myIntent = new Intent();
					myIntent = new Intent(Searchresult_main.this, BaseActivity.class);
					startActivity(myIntent);
					Searchresult_main.this.finish();
				}
			}
		});

		listView = (ListView) findViewById(R.id.booksLV);
		new Thread(new MyThread()).start();
		dialog.show();
		footerView = LayoutInflater.from(this).inflate(R.layout.widget_loading, null);
		listView.addFooterView(footerView); // 在ListView底部添加正在加载视图

		/*
		 * 添加listview的setOnScrollListener
		 */

		listView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				Searchresult_main.this.scrollState = scrollState;
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				int lastVisibleItem = firstVisibleItem + visibleItemCount - 1; // 可视的最后一个列表项的索引
				/*
				 * 当列表正处于滑动状态且滑动到列表底部时，执行
				 */
				if (Searchresult_main.this.scrollState != AbsListView.OnScrollListener.SCROLL_STATE_IDLE
						&& lastVisibleItem == totalItemCount - 1) {
					/*
					 * 执行线程，睡眠5秒钟后添加10个列表项
					 */
					new Thread() {

						private Handler handler = new Handler() {

							@Override
							public void handleMessage(Message msg) {
								super.handleMessage(msg);
								if (adapter.count <= arrayListSize) {
									System.out.print(adapter.count);
									System.out.print(arrayListSize);
									if ((arrayListSize - adapter.count) / per != 0) {
										adapter.count += per;
										adapter.notifyDataSetChanged();
									} else {
										adapter.count += arrayListSize
												- adapter.count;
										adapter.notifyDataSetChanged();
										listView.removeFooterView(footerView);
									}
								}
							}

						};

						@Override
						public void run() {
							super.run();
							try {
								sleep(5000);
								handler.sendEmptyMessage(0);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}

					}.start();

				} else {

				}
			}
		});

	}

	public class MyThread implements Runnable {

		@SuppressWarnings("unchecked")
		@Override
		public void run() {
			Intent bookIntent = getIntent();
			Bundle bundle = bookIntent.getExtras();
			bookArrayList = (ArrayList<Book>) bundle
					.getSerializable("bookArrayList");
			arrayListSize = bookArrayList.size();

			ArrayList<VisitParameters> al = (ArrayList<VisitParameters>) bundle
					.getSerializable("bookQueryParam");
			vp = al.get(0);

			handler.post(new Runnable() {
				@Override
				public void run() {
					adapter = new listViewAdapter();
					listView.setAdapter(adapter);
				}
			});
			dialog.dismiss();
		}
	}

	// 长按菜单响应函数
	@Override
	public boolean onContextItemSelected(MenuItem item) {

		return super.onContextItemSelected(item);
	}

	class listViewAdapter extends BaseAdapter {

		int count = per;
		ViewHolder holder = new ViewHolder();

		@Override
		public int getCount() {
			if (arrayListSize <= per) {
				return arrayListSize;
			} else {
				return count;
			}
		}

		@Override
		public Object getItem(int pos) {
			return pos;
		}

		@Override
		public long getItemId(int pos) {
			return pos;
		}

		@Override
		public View getView(int pos, View v, ViewGroup p) {
			View view;
			final Book book;
			// if (v == null) {
			book = bookArrayList.get(pos);

			url = book.getBookpicurl();
			if (url != null && !"".equals(url)) {
				view = LayoutInflater.from(Searchresult_main.this).inflate(
						R.layout.list_item_searchresult_netpic, null);
				new Thread(new BitmapThread()).start();

				ImageView imageView = (ImageView) view.findViewById(R.id.pic);
				// 显示
				imageView.setImageBitmap(bitmap);
			} else {
				view = LayoutInflater.from(Searchresult_main.this).inflate(
						R.layout.list_item_searchresult, null);
			}
			// 得到可用的图片

			holder.titleView = (TextView) view.findViewById(R.id.titleTv);
			holder.titleView.setText(book.getTitle());

			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Intent vIntent = new Intent(Searchresult_main.this,
							Searchresult_detail.class);
					Bundle vBundle = new Bundle();
					vBundle.putSerializable("book", book);
					vIntent.putExtras(vBundle);
					startActivity(vIntent);
				}
			});
			return view;
		}
	}

	public class BitmapThread implements Runnable {

		@Override
		public void run() {
			bitmap = getHttpBitmap(url);
		}
	}

	public class ViewHolder {
		public TextView titleView;
		public TextView authorView;
		public TextView addressView;
	}

	/**
	 * 从服务器取图片
	 * 
	 * @param url
	 * @return
	 */

	public static Bitmap getHttpBitmap(String url) {

		URL myFileURL;

		Bitmap bitmap = null;

		try {

			myFileURL = new URL(url);

			// 获得连接

			HttpURLConnection conn = (HttpURLConnection) myFileURL
					.openConnection();

			// 设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制

			conn.setConnectTimeout(6000);

			// 连接设置获得数据流

			conn.setDoInput(true);

			// 不使用缓存

			conn.setUseCaches(false);

			// 这句可有可无，没有影响

			// conn.connect();

			// 得到数据流

			InputStream is = conn.getInputStream();

			// 解析得到图片

			bitmap = BitmapFactory.decodeStream(is);

			// 关闭数据流

			is.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return bitmap;

	}
}
