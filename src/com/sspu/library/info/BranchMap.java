package com.sspu.library.info;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.sspu.library.BaseActivity;
import com.sspu.library.ExitApplication;
import com.sspu.library.R;

public class BranchMap extends Activity {

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.view_location_info);
		ExitApplication.getInstance().addActivity(this);
		WebView wView = (WebView)findViewById(R.id.wv1);     
        WebSettings wSet = wView.getSettings();     
        wSet.setJavaScriptEnabled(true);     
                     
        wView.loadUrl("file:///android_asset/map.html");   
	}

	public boolean OnKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

			Intent myIntent;

			myIntent = new Intent(BranchMap.this, BaseActivity.class);
			myIntent.putExtra("num", 3);
			startActivity(myIntent);

			this.finish();

		}

		return false;

	}
}
