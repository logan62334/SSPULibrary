package com.sspu.library.readerservice;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
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

import com.sspu.library.BaseActivity;
import com.sspu.library.ExitApplication;
import com.sspu.library.R;
import com.sspu.library.booklistsearch.Searchresult_detail;
import com.sspu.library.data.Book;
import com.sspu.library.data.Events;
import com.sspu.library.data.VisitParameters;
import com.sspu.library.exhibitionlecture.UiActivity.MyThread;
import com.sspu.library.util.QueryServer;
import com.sspu.library.util.WorkType;

public class NewArrivals extends Activity {
	
	ImageView mback;
	private static int[] s1 = new int[] { R.drawable.back_default};

	QueryServer qs = new QueryServer();
	VisitParameters vp = new VisitParameters();
	ArrayList<Book> booklist = new ArrayList<Book>();
	ListView listView;
	public int lastItem = 0;
	private int per = 6;
	private int arrayListSize = 0;
	private final int nextsqllength = 20;
	private static boolean noDataFlag = false;
	LinearLayout loadingLayout;
	private listViewAdapter adapter= new listViewAdapter();
	private static Handler handler = new Handler();
	private ProgressDialog dialog;
	Object obj;
	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.list_recommendation);
        ExitApplication.getInstance().addActivity(this);
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
		.detectDiskReads().detectDiskWrites().detectNetwork() //

		// 就包括了磁盘读写和网络I/O
		.penaltyLog() // 打印logcat，当然也可以定位到dropbox，通过文件保存相应的log
		.build());
StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
		.detectLeakedSqlLiteObjects() // 探测SQLite数据库操作
		.penaltyLog() // 打印logcat
		.penaltyDeath().build());
        dialog = new ProgressDialog(this);
		dialog.setTitle("提示");
		dialog.setMessage("正在加载，请稍后...");
		dialog.setCancelable(false);
		mback = (ImageView) findViewById(R.id.toolbar_left_image);
		  mback.setOnClickListener(new View.OnClickListener() {
		   
		   @Override
		   public void onClick(View v) {
		    mback.setImageDrawable(getResources().getDrawable(s1[0]));
		    if(s1[0]==R.drawable.back_default){
		    	Intent myIntent = new Intent();
	            myIntent = new Intent(NewArrivals.this, BaseActivity.class);
	            myIntent.putExtra("num", 1);
				startActivity(myIntent);
	            NewArrivals.this.finish();
		    }
		   }

		  });
		listView = (ListView) super.findViewById(R.id.datalist11); // 取得组件
		vp.setStartRecordNum(0);
		vp.setEndRecordNum(20);
		new Thread(new MyThread()).start();
		dialog.show();
		
		/*
		 * 添加listview的setOnScrollListener
		 */
		listView.setOnItemClickListener(null);
		listView.setOnCreateContextMenuListener(null);
		listView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				lastItem = firstVisibleItem + visibleItemCount - 1;
				if (adapter.count < arrayListSize) {
					if (firstVisibleItem + visibleItemCount == totalItemCount) {
						adapter.count += per;
						if(adapter.count >= arrayListSize){
							LoadTask task = new LoadTask();
							task.execute();
							if(noDataFlag == false){
								SharedPreferences sharedata = getSharedPreferences("data", 0);
								String sspuuserid = sharedata.getString("sspuuserid", null);
								vp.setLoginID(sspuuserid);
								vp.setStartRecordNum(arrayListSize);
								vp.setEndRecordNum(arrayListSize + nextsqllength);
								QueryServer qs = new QueryServer();
								Object obj = (ArrayList<Book>) qs.doQuery(WorkType.req_New_BOOK_QUERY,vp);
								if(obj ==null){
									noDataFlag = true;
								}else{
									ArrayList<Book> al = (ArrayList<Book>)obj;
									if(al.size() == 0){
										noDataFlag = true;
									}else{
										for(int i =0;i<al.size();i++){
											booklist.add(al.get(i));
										}
										arrayListSize = booklist.size();
									}
								}
							}else{
								adapter.count = arrayListSize -1;
							}
						}
						adapter.notifyDataSetChanged();
						listView.smoothScrollToPosition(firstVisibleItem);
//						int currentPage = adapter.count / per;
					}
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
			booklist = (ArrayList<Book>) qs.doQuery(WorkType.req_New_BOOK_QUERY,vp);
			
			arrayListSize = booklist.size();
			handler.post(new Runnable() {
				@Override
				public void run() {

					listView.setAdapter(adapter);
				}
			});
			dialog.dismiss();
		}
	}
	
	private class LoadTask extends AsyncTask<Void, Integer, String> {
		private ProgressDialog progressDialog;

		@Override
		protected String doInBackground(Void... arg0) {
			int sum = 1;
			while (sum <= 10) {
				try {
					Thread.sleep(500);
					publishProgress(sum);
					sum++;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(NewArrivals.this,
					"loading", "loading");
		}
		protected void onPostExecute(String result){
			progressDialog.dismiss();
			
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
			final Book book;
//			if (v == null) {
			book = booklist.get(pos);
				view = LayoutInflater.from(NewArrivals.this).inflate(
						R.layout.list_item_recommendation, null);
				holder.titleView = (TextView) view.findViewById(R.id.title);
				holder.authorView = (TextView) view.findViewById(R.id.author);
				holder.gcwzView = (TextView) view.findViewById(R.id.gcwz);
				holder.titleView.setText(book.getTitle());
				holder.authorView.setText("作者：" + book.getAuthor());
				view.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					Intent vIntent = new Intent(NewArrivals.this,Searchresult_detail.class);
					Bundle vBundle = new Bundle();
					vBundle.putSerializable("book", book);
					vIntent.putExtras(vBundle);
					startActivity(vIntent);
				}
			});
//			} else {
//				view = (RelativeLayout) v;
//			}
			return view;
		}
	}

	public class ViewHolder {
		public TextView titleView;
		public TextView authorView;
		public TextView gcwzView;
		public TextView addressView;
	}
}