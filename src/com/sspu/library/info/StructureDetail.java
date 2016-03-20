package com.sspu.library.info;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sspu.library.BaseActivity;
import com.sspu.library.ExitApplication;
import com.sspu.library.R;
import com.sspu.library.common.BackAction;

public class StructureDetail extends Activity {

	ImageView mback;
	TextView detailcontent, detailtitle, title;
	private static int[] s1 = new int[] { R.drawable.back_default };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_detail);
		ExitApplication.getInstance().addActivity(this);
		mback = (ImageView) findViewById(R.id.toolbar_left_image);
		detailcontent = (TextView) findViewById(R.id.detailcontent);
		detailtitle = (TextView) findViewById(R.id.detailtitle);
		title = (TextView) findViewById(R.id.title);
		String[] structuredetail=getResources().getStringArray(R.array.structuredetail);
		title.setText("Ω·ππΩÈ…‹");
		detailtitle.setText(structuredetail[0]);
		detailcontent.setText(structuredetail[1]);
		mback.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mback
						.setImageDrawable(getResources().getDrawable(s1[0]));
				if (s1[0] == R.drawable.back_default) {
					BackAction.Back(StructureDetail.this, BaseActivity.class,3);
				}
			}
		});
	}

	public boolean OnKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			BackAction.Back(StructureDetail.this, BaseActivity.class,3);

		}

		return false;

	}

}