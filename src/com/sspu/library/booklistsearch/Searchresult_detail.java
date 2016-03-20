package com.sspu.library.booklistsearch;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sspu.library.BaseActivity;
import com.sspu.library.ExitApplication;
import com.sspu.library.R;
import com.sspu.library.data.Book;
import com.sspu.library.data.VisitParameters;
import com.sspu.library.util.QueryServer;
import com.sspu.library.util.WorkType;

public class Searchresult_detail extends Activity {
	ImageView mback;
	TextView titleTextView;
	TextView authorTextView;
	TextView addressTextView;
	TextView isbnTextView;
	TextView callnoTextView;
	TextView contentTextView, pubdate, page, price, publish, language, subject,
			bookcontent, booksize;
	Book book;
	private static int[] s1 = new int[] { R.drawable.back_default };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchresult_detail);
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
		mback = (ImageView) findViewById(R.id.imageButton1);
		titleTextView = (TextView) findViewById(R.id.textView2);
		authorTextView = (TextView) findViewById(R.id.textView3);
		addressTextView = (TextView) findViewById(R.id.textView4);
		isbnTextView = (TextView) findViewById(R.id.textView7);
		callnoTextView = (TextView) findViewById(R.id.textView8);
		contentTextView = (TextView) findViewById(R.id.textView6);

		pubdate = (TextView) findViewById(R.id.textView9);

		page = (TextView) findViewById(R.id.textView10);

		price = (TextView) findViewById(R.id.textView11);

		publish = (TextView) findViewById(R.id.textView12);

		language = (TextView) findViewById(R.id.textView13);

		subject = (TextView) findViewById(R.id.textView14);


		booksize = (TextView) findViewById(R.id.textView15);
		Intent mIntent = getIntent();
		Bundle bundle = mIntent.getExtras();
		book = (Book) bundle.get("book");

		QueryServer qs = new QueryServer();
		VisitParameters vp = new VisitParameters();
		vp.setBookrecno(book.getBookrecno());
		try {
			book = (Book) qs.doQuery(WorkType.req_BOOK_DETAIL_QUERY, vp);
		} catch (Exception e) {

		}
		titleTextView.setText(book.getTitle());
		authorTextView.setText(book.getAuthor());
		addressTextView.setText("出版社：" + book.getAddress());
		isbnTextView.setText("ISBN：" + book.getIsbn());
		callnoTextView.setText("馆藏位置：" + book.getCallno());
		pubdate.setText("出版日期："+book.getPubdate());
		page.setText("页数："+book.getPage());
		price.setText("价格："+book.getPrice());
		publish.setText("出版社："+book.getPublisher());
		language.setText("语言："+book.getLanguage());
		subject.setText("类别："+book.getSubject());
		booksize.setText("尺寸："+book.getBooksize());
		contentTextView.setText("暂未发现书本简介，请 等待后续补充！");

		String url = book.getBookpicurl();
		Bitmap bitmap = getHttpBitmap(url);
		ImageView imageView = (ImageView) this.findViewById(R.id.pic);
		// 显示
		if(url != null && !"".equals(url))
		{
		imageView.setImageBitmap(bitmap);
		}
		else {
			imageView.setImageResource(R.drawable.pic_javaweb);
		}
		

		mback.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mback
						.setImageDrawable(getResources().getDrawable(s1[0]));
				if (s1[0] == R.drawable.back_default) {
					Intent myIntent = new Intent();
					myIntent = new Intent(Searchresult_detail.this,
							BaseActivity.class);
					startActivity(myIntent);
					Searchresult_detail.this.finish();
				}
			}
		});

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
