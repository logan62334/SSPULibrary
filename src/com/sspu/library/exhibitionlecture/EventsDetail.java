package com.sspu.library.exhibitionlecture;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sspu.library.BaseActivity;
import com.sspu.library.ExitApplication;
import com.sspu.library.R;
import com.sspu.library.common.BackAction;
import com.sspu.library.data.Events;
import com.sspu.library.info.StructureDetail;

public class EventsDetail extends Activity {
	ImageView mback;
	TextView titleTextView;
	TextView authorTextView;
	TextView addressTextView;
	TextView isbnTextView;
	TextView callnoTextView;
	TextView contentTextView;
	Events events;
	private static int[] s1 = new int[] { R.drawable.back_default};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_detail);
        ExitApplication.getInstance().addActivity(this);
		mback = (ImageView) findViewById(R.id.imageButton1);
		titleTextView = (TextView) findViewById(R.id.textView2);
		authorTextView = (TextView) findViewById(R.id.textView3);
		addressTextView = (TextView) findViewById(R.id.textView4);
		isbnTextView = (TextView) findViewById(R.id.textView7);
		callnoTextView = (TextView) findViewById(R.id.textView8);
		contentTextView = (TextView) findViewById(R.id.textView6);
		Intent mIntent = getIntent();
		Bundle bundle = mIntent.getExtras();
		events = (Events) bundle.get("events");
		titleTextView.setText(events.getTITLE());
		authorTextView.setText("活动时间："+events.getEVENTDATE());
		addressTextView.setText("活动地址："+events.getADDRESS());
		contentTextView.setText(events.getCONTENT());
		
		  mback.setOnClickListener(new View.OnClickListener() {
		   
		   @Override
		   public void onClick(View v) {
		    mback.setImageDrawable(getResources().getDrawable(s1[0]));
		    if(s1[0]==R.drawable.back_default){
		    	BackAction.Back(EventsDetail.this, BaseActivity.class,2);
		    }
		   }

		  });
	}

}
