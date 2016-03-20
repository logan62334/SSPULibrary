package com.sspu.library.mylibrary;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sspu.library.BaseActivity;
import com.sspu.library.ExitApplication;
import com.sspu.library.R;

public class Reback extends Activity {
	ImageView msubmit;
	TextView title;
	EditText editText;
	private String ip = "202.121.241.133";
	private int port = 8097;
	private static int[] s1 = new int[] { R.drawable.action_done_up };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_feedback);
		ExitApplication.getInstance().addActivity(this);
		msubmit = (ImageView) findViewById(R.id.toolbar_right_image);
		title = (TextView) findViewById(R.id.title);
		editText = (EditText) findViewById(R.id.reback);
		title.setText("∑¥¿°–≈œ¢");
		msubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				msubmit
						.setImageDrawable(getResources().getDrawable(s1[0]));
				if (s1[0] == R.drawable.action_done_up) {
					try {

						if (!TextUtils.isEmpty(editText.getText()))
						SendMsg(ip, port, editText.getText().toString());
						Intent myIntent = new Intent();
						myIntent = new Intent(Reback.this, About.class);
						startActivity(myIntent);
						Reback.this.finish();

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

	public void SendMsg(String ip, int port, String msg)
			throws UnknownHostException, IOException {

		try {
			Socket socket = new Socket();
			SocketAddress socketAddress = new InetSocketAddress(ip, port);
			// socket = new Socket(ip, port);
			System.out.println("!!!");
			socket.connect(socketAddress, 8000);
			System.out.println("!!!!");
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream()));
			writer.write(msg);
			writer.flush();
			writer.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean OnKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

			Intent myIntent;

			myIntent = new Intent(Reback.this, BaseActivity.class);
			myIntent.putExtra("num", 4);
			startActivity(myIntent);

			this.finish();

		}

		return false;

	}
}
