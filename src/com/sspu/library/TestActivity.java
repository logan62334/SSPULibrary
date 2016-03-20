package com.sspu.library;

import cn.jpush.android.api.JPushInterface;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class TestActivity extends Activity {

	/*
	 * 定义了推送信息的展示页面
	 */
    @SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("用户自定义打开的Activity");
        tv.setTextSize(20);
        Intent intent = getIntent();
        if (null != intent) {
	        Bundle bundle = getIntent().getExtras();
//	        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
	        String content = bundle.getString(JPushInterface.EXTRA_ALERT);
	        tv.setText(content);
        }
        addContentView(tv, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
    }

}
