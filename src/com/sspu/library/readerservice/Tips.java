package com.sspu.library.readerservice;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sspu.library.BaseActivity;
import com.sspu.library.ExitApplication;
import com.sspu.library.R;
import com.sspu.library.data.Broadcast;
import com.sspu.library.data.VisitParameters;
import com.sspu.library.util.QueryServer;
import com.sspu.library.util.WorkType;

public class Tips extends Activity {

	ImageView mback;
	TextView mTextView;
	private static int[] s1 = new int[] { R.drawable.back_default };

	QueryServer qs = new QueryServer();
	VisitParameters vp = new VisitParameters();
	ArrayList<Broadcast> broadcastArrayList = new ArrayList<Broadcast>();
	ListView listView;
	public int lastItem = 0;
	private int per = 10;
	private int arrayListSize = 0;
	LinearLayout loadingLayout;
	private listViewAdapter adapter;
	private static Handler handler = new Handler();
	private ProgressDialog dialog;
	Object obj;
	private int scrollState;
	private View footerView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.list_news_bulletin);
		ExitApplication.getInstance().addActivity(this);
		dialog = new ProgressDialog(this);
		dialog.setTitle("提示");
		dialog.setMessage("正在加载，请稍后...");
		dialog.setCancelable(false);
		mback = (ImageView) findViewById(R.id.toolbar_left_image);
		mTextView = (TextView) findViewById(R.id.title);
		mTextView.setText("公告动态");
		mback.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mback
						.setImageDrawable(getResources().getDrawable(s1[0]));
				if (s1[0] == R.drawable.back_default) {
					Intent myIntent = new Intent();
					myIntent = new Intent(Tips.this, BaseActivity.class);
					myIntent.putExtra("num", 1);
					startActivity(myIntent);
					Tips.this.finish();
				}
			}
		});

		listView = (ListView) super.findViewById(R.id.datalistDynamic); // 取得组件
		
		new Thread(new MyThread()).start();
		dialog.show();
		footerView = LayoutInflater.from(this).inflate(R.layout.widget_loading, null);
		listView.addFooterView(footerView); // 在ListView底部添加正在加载视图
		/*
		 * 添加listview的setOnScrollListener
		 */
		listView.setOnItemClickListener(null);
		listView.setOnCreateContextMenuListener(null);
		listView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				Tips.this.scrollState = scrollState;
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				int lastVisibleItem = firstVisibleItem + visibleItemCount - 1; // 可视的最后一个列表项的索引
				/*
				 * 当列表正处于滑动状态且滑动到列表底部时，执行
				 */
				if (Tips.this.scrollState != AbsListView.OnScrollListener.SCROLL_STATE_IDLE
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
									if((arrayListSize-adapter.count)/per!=0)
									{
									adapter.count += per;
									adapter.notifyDataSetChanged();
									}
									else {
										adapter.count += arrayListSize-adapter.count;
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
					listView.removeFooterView(loadingLayout);
				}
			}
		});

	}
	public class MyThread implements Runnable {

		@SuppressWarnings("unchecked")
		@Override
		public void run() {
			broadcastArrayList = (ArrayList<Broadcast>) qs
					.doQuery(WorkType.req_NOTICE_QUERY);
			arrayListSize = broadcastArrayList.size();
			
		
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
			final Broadcast broadcast;
			if (v == null) {
				broadcast = broadcastArrayList.get(pos);
				view = LayoutInflater.from(Tips.this).inflate(
						R.layout.list_item_01, null);
				holder.titleView = (TextView) view.findViewById(R.id.title);
				holder.createdateView = (TextView) view.findViewById(R.id.author);
				holder.titleView.setText(broadcast.getTITLE());
				holder.createdateView.setText(broadcast.getCREATEDATE());
				view.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						Intent vIntent = new Intent(Tips.this,
								BroadcastDetail.class);
						Bundle vBundle = new Bundle();
						vBundle.putSerializable("broadcast", broadcast);
						vIntent.putExtras(vBundle);
						startActivity(vIntent);
					}
				});
			} else {
				view = (RelativeLayout) v;
			}
			return view;
		}
	}

	public class ViewHolder {
		public TextView titleView;
		public TextView createdateView;
	}

	public boolean OnKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

			Intent myIntent;

			myIntent = new Intent(Tips.this, BaseActivity.class);
			myIntent.putExtra("num", 1);
			startActivity(myIntent);

			this.finish();

		}

		return false;

	}
}