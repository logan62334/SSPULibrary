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
		dialog.setTitle("��ʾ");
		dialog.setMessage("���ڼ��أ����Ժ�...");
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
		listView.addFooterView(footerView); // ��ListView�ײ�������ڼ�����ͼ

		/*
		 * ���listview��setOnScrollListener
		 */

		listView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				Searchresult_main.this.scrollState = scrollState;
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				int lastVisibleItem = firstVisibleItem + visibleItemCount - 1; // ���ӵ����һ���б��������
				/*
				 * ���б������ڻ���״̬�һ������б�ײ�ʱ��ִ��
				 */
				if (Searchresult_main.this.scrollState != AbsListView.OnScrollListener.SCROLL_STATE_IDLE
						&& lastVisibleItem == totalItemCount - 1) {
					/*
					 * ִ���̣߳�˯��5���Ӻ����10���б���
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

	// �����˵���Ӧ����
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
				// ��ʾ
				imageView.setImageBitmap(bitmap);
			} else {
				view = LayoutInflater.from(Searchresult_main.this).inflate(
						R.layout.list_item_searchresult, null);
			}
			// �õ����õ�ͼƬ

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
	 * �ӷ�����ȡͼƬ
	 * 
	 * @param url
	 * @return
	 */

	public static Bitmap getHttpBitmap(String url) {

		URL myFileURL;

		Bitmap bitmap = null;

		try {

			myFileURL = new URL(url);

			// �������

			HttpURLConnection conn = (HttpURLConnection) myFileURL
					.openConnection();

			// ���ó�ʱʱ��Ϊ6000���룬conn.setConnectionTiem(0);��ʾû��ʱ������

			conn.setConnectTimeout(6000);

			// �������û��������

			conn.setDoInput(true);

			// ��ʹ�û���

			conn.setUseCaches(false);

			// �����п��ޣ�û��Ӱ��

			// conn.connect();

			// �õ�������

			InputStream is = conn.getInputStream();

			// �����õ�ͼƬ

			bitmap = BitmapFactory.decodeStream(is);

			// �ر�������

			is.close();

		} catch (Exception e) {

			e.printStackTrace();

		}

		return bitmap;

	}
}
