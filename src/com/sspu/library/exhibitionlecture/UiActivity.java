package com.sspu.library.exhibitionlecture;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
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
import android.widget.TextView;

import com.sspu.library.ExitApplication;
import com.sspu.library.R;
import com.sspu.library.data.Events;
import com.sspu.library.data.VisitParameters;
import com.sspu.library.service.Login;
import com.sspu.library.util.QueryServer;
import com.sspu.library.util.WorkType;

public class UiActivity extends Activity {

	ImageView maccount;
	private static int[] s1 = new int[] { R.drawable.account_default };
	QueryServer qs = new QueryServer();
	VisitParameters vp = new VisitParameters();
	ArrayList<Events> eventsArrayList = new ArrayList<Events>();
	ListView listView;
	public int lastItem = 0;
	private int per = 6;
	private int arrayListSize = 0;
	LinearLayout loadingLayout;
	private listViewAdapter adapter = new listViewAdapter();;
	private static Handler handler = new Handler();
	private ProgressDialog dialog;
	Object obj;
	private int scrollState;
	private View footerView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_exhibition_lecture);
		ExitApplication.getInstance().addActivity(this);
		dialog = new ProgressDialog(this);
		dialog.setTitle("提示");
		dialog.setMessage("正在加载，请稍后...");
		dialog.setCancelable(false);
		maccount = (ImageView) findViewById(R.id.toolbar_right_image);
		maccount.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				maccount.setImageDrawable(getResources().getDrawable(s1[0]));
				if (s1[0] == R.drawable.account_default) {
					Intent myIntent = new Intent();
					myIntent = new Intent(UiActivity.this, Login.class);
					startActivity(myIntent);
					UiActivity.this.finish();
				}
			}
		});

		listView = (ListView) super.findViewById(R.id.datalist); // 取得组件
		new Thread(new MyThread()).start();
		dialog.show();
		footerView = LayoutInflater.from(this).inflate(R.layout.widget_loading, null);
		listView.addFooterView(footerView); // 在ListView底部添加正在加载视图
		listView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				UiActivity.this.scrollState = scrollState;
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				int lastVisibleItem = firstVisibleItem + visibleItemCount - 1; // 可视的最后一个列表项的索引
				/*
				 * 当列表正处于滑动状态且滑动到列表底部时，执行
				 */
				if (UiActivity.this.scrollState != AbsListView.OnScrollListener.SCROLL_STATE_IDLE
						&& lastVisibleItem == totalItemCount - 1) {
					// dialog.show();
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
										// dialog.dismiss();
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
			eventsArrayList = (ArrayList<Events>) qs.doQuery(
					WorkType.req_SHOW_TRAIN_QUERY, vp);
			arrayListSize = eventsArrayList.size();
			handler.post(new Runnable() {
				@Override
				public void run() {

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
			final Events events;

			if (pos >= arrayListSize) {
				pos = arrayListSize - 1;
			}
			events = eventsArrayList.get(pos);
			int etype = Integer.parseInt(events.getETYPE());
			if (etype == 1) {
				view = LayoutInflater.from(UiActivity.this).inflate(
						R.layout.list_item_lecture, null);
			} else {
				view = LayoutInflater.from(UiActivity.this).inflate(
						R.layout.list_item_exhibition, null);
			}
			holder.titleView = (TextView) view.findViewById(R.id.title);
			holder.authorView = (TextView) view.findViewById(R.id.author);
			holder.titleView.setText(events.getTITLE());
			holder.authorView.setText(events.getEVENTDATE());
			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Intent vIntent = new Intent(UiActivity.this,
							EventsDetail.class);
					Bundle vBundle = new Bundle();
					vBundle.putSerializable("events", events);
					vIntent.putExtras(vBundle);
					startActivity(vIntent);
				}
			});

			return view;
		}
	}

	public class ViewHolder {
		public TextView titleView;
		public TextView authorView;
		public TextView addressView;
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			dialog.dismiss();
			dialog();

			return true;
		}
		return true;
	}

	protected void dialog() {

		AlertDialog.Builder builder = new Builder(UiActivity.this);
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