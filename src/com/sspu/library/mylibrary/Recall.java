package com.sspu.library.mylibrary;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
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
import com.sspu.library.data.Myloanbook;
import com.sspu.library.data.VisitParameters;
import com.sspu.library.util.QueryServer;
import com.sspu.library.util.WorkType;

public class Recall extends Activity {

	ImageView mback;
	private static int[] s1 = new int[] { R.drawable.back_default };

	QueryServer qs = new QueryServer();
	VisitParameters vp = new VisitParameters();
	ArrayList<Myloanbook> myloanbooklist = new ArrayList<Myloanbook>();
	ListView listView;
	public int lastItem = 0;
	private int per = 6;
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
		super.setContentView(R.layout.list_book_overdue);
		ExitApplication.getInstance().addActivity(this);
		dialog = new ProgressDialog(this);
		dialog.setTitle("提示");
		dialog.setMessage("正在加载，请稍后...");
		dialog.setCancelable(false);
		mback = (ImageView) findViewById(R.id.toolbar_left_image);
		mback.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mback
						.setImageDrawable(getResources().getDrawable(s1[0]));
				if (s1[0] == R.drawable.back_default) {
					Intent myIntent = new Intent();
					myIntent = new Intent(Recall.this,
							BaseActivity.class);
					myIntent.putExtra("num", 4);
					startActivity(myIntent);
					Recall.this.finish();
				}
			}
		});
		listView = (ListView) super.findViewById(R.id.datalist11); // 取得组件
		SharedPreferences sharedata = getSharedPreferences("data", 0);
		String sspuuserid = sharedata.getString("sspuuserid", null);
		vp.setLoginID(sspuuserid);
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
				Recall.this.scrollState = scrollState;
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				int lastVisibleItem = firstVisibleItem + visibleItemCount - 1; // 可视的最后一个列表项的索引
				/*
				 * 当列表正处于滑动状态且滑动到列表底部时，执行
				 */
				if (Recall.this.scrollState != AbsListView.OnScrollListener.SCROLL_STATE_IDLE
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
//					listView.removeFooterView(loadingLayout);
				}
			}
		});
	}
	public class MyThread implements Runnable {

		@SuppressWarnings("unchecked")
		@Override
		public void run() {
			myloanbooklist = (ArrayList<Myloanbook>) qs.doQuery(
					WorkType.req_EXPIRE_BOOK, vp);

			arrayListSize = myloanbooklist.size();

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
			final Myloanbook myloanbook;
			if (v == null) {
				myloanbook = myloanbooklist.get(pos);
				view = LayoutInflater.from(Recall.this).inflate(
						R.layout.list_item_02, null);
				holder.titleView = (TextView) view.findViewById(R.id.title);
				holder.jytimeView = (TextView) view.findViewById(R.id.jysj);
				holder.authorView = (TextView) view.findViewById(R.id.author);
				holder.gcwzView = (TextView) view.findViewById(R.id.gcwz);
				holder.ghsjView = (TextView) view.findViewById(R.id.ghsj);
				holder.titleView.setText(myloanbook.getTitle());
				holder.authorView.setText("作者：" + myloanbook.getAuthor());
				holder.jytimeView.setText("借阅时间：" + myloanbook.getLoandate());
				holder.ghsjView.setText("归还时间：" + myloanbook.getReturndate());
				holder.gcwzView.setText("馆藏位置：" + myloanbook.getCallno());
			} else {
				view = (RelativeLayout) v;
			}
			return view;
		}
	}

	public class ViewHolder {
		public TextView titleView;
		public TextView authorView;
		public TextView jytimeView;
		public TextView gcwzView;
		public TextView ghsjView;
		public TextView addressView;
	}
}