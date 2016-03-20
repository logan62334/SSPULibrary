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

		// �Ͱ����˴��̶�д������I/O
		.penaltyLog() // ��ӡlogcat����ȻҲ���Զ�λ��dropbox��ͨ���ļ�������Ӧ��log
		.build());
StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
		.detectLeakedSqlLiteObjects() // ̽��SQLite���ݿ����
		.penaltyLog() // ��ӡlogcat
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
		addressTextView.setText("�����磺" + book.getAddress());
		isbnTextView.setText("ISBN��" + book.getIsbn());
		callnoTextView.setText("�ݲ�λ�ã�" + book.getCallno());
		pubdate.setText("�������ڣ�"+book.getPubdate());
		page.setText("ҳ����"+book.getPage());
		price.setText("�۸�"+book.getPrice());
		publish.setText("�����磺"+book.getPublisher());
		language.setText("���ԣ�"+book.getLanguage());
		subject.setText("���"+book.getSubject());
		booksize.setText("�ߴ磺"+book.getBooksize());
		contentTextView.setText("��δ�����鱾��飬�� �ȴ��������䣡");

		String url = book.getBookpicurl();
		Bitmap bitmap = getHttpBitmap(url);
		ImageView imageView = (ImageView) this.findViewById(R.id.pic);
		// ��ʾ
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
