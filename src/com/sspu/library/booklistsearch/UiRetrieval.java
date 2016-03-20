package com.sspu.library.booklistsearch;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences.Editor;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.speech.RecognizerIntent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import cn.jpush.android.api.JPushInterface;

import com.sspu.library.ExampleUtil;
import com.sspu.library.ExitApplication;
import com.sspu.library.R;
import com.sspu.library.Welcome;
import com.sspu.library.data.Book;
import com.sspu.library.data.VisitParameters;
import com.sspu.library.service.Login;
import com.sspu.library.util.QueryServer;
import com.sspu.library.util.WorkType;

public class UiRetrieval extends Activity {
	ImageView maccount;
	ImageView mhelp;
	RadioGroup radioGroup;
	QueryServer qs = new QueryServer();
	VisitParameters vp = new VisitParameters();
	protected static final int RESULT_SPEECH = 1;
	private ImageButton btnSpeak, searchBtn;
	private TextView txtText;
	private ProgressDialog mDialog;
	private static final int REQUEST_TIMEOUT = 5 * 1000;
	private static final int SO_TIMEOUT = 10 * 1000;
	private static int[] s1 = new int[] { R.drawable.account_default,
			R.drawable.help_default, R.drawable.voice };
	private File PRO_DIR = new File(Environment.getExternalStorageDirectory()
			.toString() + "/test");
	public static boolean isForeground = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_book_retrieval);
		ExitApplication.getInstance().addActivity(this);
		JPushInterface.init(getApplicationContext());
		registerMessageReceiver();
		btnSpeak = (ImageButton) findViewById(R.id.imageButton_openspeechrecognition);
		maccount = (ImageView) findViewById(R.id.toolbar_right_image);
		mhelp = (ImageView) findViewById(R.id.toolbar_left_image);
		mDialog = new ProgressDialog(UiRetrieval.this);
		mDialog.setTitle("查询");
		mDialog.setMessage("正在查询，请稍后...");
		txtText = (TextView) findViewById(R.id.autoCompleteTextView);
		searchBtn = (ImageButton) findViewById(R.id.imageView_search);
		searchBtn.setOnClickListener(new searchBtnListener());
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup_bookretrive);
		radioGroup
				.setOnCheckedChangeListener(new radioGroupCheckedChangeListener());

		maccount.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				maccount
						.setImageDrawable(getResources().getDrawable(s1[0]));
				if (s1[0] == R.drawable.account_default) {
					Intent myIntent = new Intent();
					myIntent = new Intent(UiRetrieval.this, Login.class);
					startActivity(myIntent);
					UiRetrieval.this.finish();
				}
			}
		});
		mhelp.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mhelp
						.setImageDrawable(getResources().getDrawable(s1[1]));
				if (s1[1] == R.drawable.help_default) {
					Intent myIntent = new Intent();
					myIntent = new Intent(UiRetrieval.this, Welcome.class);
					startActivity(myIntent);
					UiRetrieval.this.finish();
				}
			}

		});
		btnSpeak.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(
						RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

				intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

				try {
					startActivityForResult(intent, RESULT_SPEECH);
					txtText.setText("");
				} catch (ActivityNotFoundException a) {
					Toast t = Toast.makeText(getApplicationContext(),
							"你没有安装google语音识别插件，请安装~", Toast.LENGTH_SHORT);
					t.show();
					installApk(getAssetFile());
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case RESULT_SPEECH: {
			if (resultCode == RESULT_OK && null != data) {

				ArrayList<String> text = data
						.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

				txtText.setText(text.get(0));
			}
			break;
		}

		}

	}

	public HttpClient getHttpClient() {
		BasicHttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, REQUEST_TIMEOUT);
		HttpConnectionParams.setSoTimeout(httpParams, SO_TIMEOUT);
		HttpClient client = new DefaultHttpClient(httpParams);
		return client;
	}

	class searchBtnListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {

			new Thread(new MyThread()).start();
			mDialog.show();
		}

	}

	public class MyThread implements Runnable {

		@Override
		public void run() {
			String keywords = "";
			vp.setBookQueryType("1");
			keywords = txtText.getText().toString().trim();
			if (vp.getBookQueryType() == "") {
				if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton1_title) {
					vp.setBookQueryType("1");
				}
				if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton2_author) {
					vp.setBookQueryType("0");
				}
			} else if (keywords == "") {
				Toast.makeText(UiRetrieval.this, "请输入有效检索值", Toast.LENGTH_SHORT)
						.show();
			} else {
				vp.setBookQueryKeywork(keywords);
				vp.setStartRecordNum(0);
				vp.setEndRecordNum(20);

				ArrayList<Book> o = new ArrayList<Book>();
				try {
					o = (ArrayList<Book>) qs.doQuery(WorkType.req_BOOK_QUERY,
							vp);
				} catch (Exception e) {
					Toast.makeText(UiRetrieval.this, "查找过程有误！",
							Toast.LENGTH_SHORT).show();
				}

				if (o.size() == 0) {
					Toast.makeText(UiRetrieval.this, "无法找到要查询的结果！",
							Toast.LENGTH_SHORT).show();
				} else {

					System.out.println(o.get(0).getTitle() + "booktitle");
					Intent bookIntent = new Intent(UiRetrieval.this,
							Searchresult_main.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable("bookArrayList", o);
					ArrayList<VisitParameters> al = new ArrayList<VisitParameters>();
					al.add(vp);
					bundle.putSerializable("bookQueryParam", al);
					bookIntent.putExtras(bundle);
					mDialog.cancel();
					startActivity(bookIntent);

				}
			}
		}

	}

	@Override
	protected void onResume() {
		isForeground = true;
		super.onResume();
	}

	@Override
	protected void onPause() {
		isForeground = false;
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		unregisterReceiver(mMessageReceiver);
		super.onDestroy();
	}

	@SuppressWarnings("unused")
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
			progressDialog = ProgressDialog.show(UiRetrieval.this, "loading",
					"loading");
		}

		protected void onPostExecute(String result) {
			progressDialog.dismiss();

		}

	}

	class radioGroupCheckedChangeListener implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(RadioGroup arg0, int arg1) {

			if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton1_title) {
				vp.setBookQueryType("1");
			}
			if (radioGroup.getCheckedRadioButtonId() == R.id.radioButton2_author) {
				vp.setBookQueryType("0");
			}
		}

	}

	private File getAssetFile() {

		AssetManager asset = UiRetrieval.this.getAssets();
		File f = null;
		try {
			PRO_DIR.mkdirs();
			InputStream is = asset.open("googlevoice.apk");
			f = new File(PRO_DIR, "googlevoice.apk");
			f.createNewFile();
			FileOutputStream fOut = new FileOutputStream(f);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				fOut.write(buffer, 0, len);
			}

			fOut.flush();
			is.close();
			fOut.close();
			return f;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void installApk(File file) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		intent.setClassName("com.android.packageinstaller",
				"com.android.packageinstaller.PackageInstallerActivity");
		String type = "android/vnd.android.package-archive";
		intent.setDataAndType(Uri.fromFile(file), type);
		startActivity(intent);

	}

	private MessageReceiver mMessageReceiver;
	public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
	public static final String KEY_TITLE = "title";
	public static final String KEY_MESSAGE = "message";
	public static final String KEY_EXTRAS = "extras";

	public void registerMessageReceiver() {
		mMessageReceiver = new MessageReceiver();
		IntentFilter filter = new IntentFilter();
		filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
		filter.addAction(MESSAGE_RECEIVED_ACTION);
		registerReceiver(mMessageReceiver, filter);
	}

	public class MessageReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
				String messge = intent.getStringExtra(KEY_MESSAGE);
				String extras = intent.getStringExtra(KEY_EXTRAS);
				StringBuilder showMsg = new StringBuilder();
				showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
				if (!ExampleUtil.isEmpty(extras)) {
					showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
				}
				// setCostomMsg(showMsg.toString());
			}
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
		System.out.println("MyActivityGroupProjectDemo3--dialog --7");
		AlertDialog.Builder builder = new Builder(UiRetrieval.this);
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