package com.sspu.library.info;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sspu.library.BaseActivity;
import com.sspu.library.ExitApplication;
import com.sspu.library.R;

public class Detail extends Activity {

	ImageView mback;//定义返回按钮
	TextView detailcontent, detailtitle;//定义详细页的标题和内容
	private static int[] s1 = new int[] { R.drawable.back_default };//定义返回按钮的图片资源

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_detail);
		//讲Activity加入栈中
		ExitApplication.getInstance().addActivity(this);
		//从布局文件中获得各view
		mback = (ImageView) findViewById(R.id.toolbar_left_image);
		detailcontent = (TextView) findViewById(R.id.detailcontent);
		detailtitle = (TextView) findViewById(R.id.detailtitle);
		//从资源文件中获取对应的数组
		String[] rulestitle=getResources().getStringArray(R.array.rulestitle);
		String[] rulescontent=getResources().getStringArray(R.array.rulescontent);
		String[] commonproblemtitle=getResources().getStringArray(R.array.commonproblemtitle);
		String[] commonproblemcontent=getResources().getStringArray(R.array.commonproblemcontent);
		String[] servicetitle=getResources().getStringArray(R.array.servicetitle);
		String[] servicecontent=getResources().getStringArray(R.array.servicecontent);
		String[] helptitle=getResources().getStringArray(R.array.helptitle);
		String[] helpcontent=getResources().getStringArray(R.array.helpcontent);
		//用来标记哪条数据被选中
		SharedPreferences sharedata = getSharedPreferences("dt", 0);
		String dt = sharedata.getString("arg2", null);
		/*
		 * 规章制度内容
		 */
		if (dt == "0") {
			detailtitle.setText(rulestitle[0]);
			detailcontent.setText(rulescontent[0]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "1") {
			detailtitle.setText(rulestitle[1]);
			detailcontent.setText(rulescontent[1]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "2") {
			detailtitle.setText(rulestitle[2]);
			detailcontent.setText(rulescontent[2]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}

			});
		} else if (dt == "3") {
			detailtitle.setText(rulestitle[3]);
			detailcontent.setText(rulescontent[3]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}

			});
		} else if (dt == "4") {
			detailtitle.setText(rulestitle[4]);
			detailcontent.setText(rulescontent[4]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "5") {
			detailtitle.setText(rulestitle[5]);
			detailcontent.setText(rulescontent[5]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}

			});
		} else if (dt == "6") {
			detailtitle.setText(rulestitle[6]);
			detailcontent.setText(rulescontent[6]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "7") {
			detailtitle.setText(rulestitle[7]);
			detailcontent.setText(rulescontent[7]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "8") {
			detailtitle.setText(rulestitle[8]);
			detailcontent.setText(rulescontent[8]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "9") {
			detailtitle.setText(rulestitle[9]);
			detailcontent.setText(rulescontent[9]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "10") {
			detailtitle.setText(rulestitle[10]);
			detailcontent.setText(rulescontent[10]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}

			});
		} else if (dt == "11") {
			detailtitle.setText(rulestitle[11]);
			detailcontent.setText(rulescontent[11]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "12") {
			detailtitle.setText(rulestitle[12]);
			detailcontent.setText(rulescontent[12]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "13") {
			detailtitle.setText(rulestitle[13]);
			detailcontent.setText(rulescontent[13]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "14") {
			detailtitle.setText(rulestitle[14]);
			detailcontent.setText(rulescontent[14]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "15") {
			detailtitle.setText(rulestitle[15]);
			detailcontent.setText(rulescontent[15]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}

			});
		} else if (dt == "16") {
			detailtitle.setText(rulestitle[18]);
			detailcontent.setText(rulescontent[18]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "17") {
			detailtitle.setText(rulestitle[17]);
			detailcontent.setText(rulescontent[17]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "18") {
			detailtitle.setText(rulestitle[18]);
			detailcontent.setText(rulescontent[18]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "19") {
			detailtitle.setText(rulestitle[19]);
			detailcontent.setText(rulescontent[19]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "20") {
			detailtitle.setText(rulestitle[20]);
			detailcontent.setText(rulescontent[20]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "21") {
			detailtitle.setText(rulestitle[21]);
			detailcontent.setText(rulescontent[21]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "22") {
			detailtitle.setText(rulestitle[22]);
			detailcontent.setText(rulescontent[22]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "23") {
			detailtitle.setText(rulestitle[23]);
			detailcontent.setText(rulescontent[23]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "24") {
			detailtitle.setText(rulestitle[24]);
			detailcontent.setText(rulescontent[24]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "25") {
			detailtitle.setText(rulestitle[25]);
			detailcontent.setText(rulescontent[25]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "26") {
			detailtitle.setText(rulestitle[26]);
			detailcontent.setText(rulescontent[26]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "27") {
			detailtitle.setText(rulestitle[27]);
			detailcontent.setText(rulescontent[27]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "28") {
			detailtitle.setText(rulestitle[28]);
			detailcontent.setText(rulescontent[28]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "29") {
			detailtitle.setText(rulestitle[29]);
			detailcontent.setText(rulescontent[29]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "30") {
			detailtitle.setText(rulestitle[30]);
			detailcontent.setText(rulescontent[30]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "31") {
			detailtitle.setText(rulestitle[31]);
			detailcontent.setText(rulescontent[31]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "32") {
			detailtitle.setText(rulestitle[32]);
			detailcontent.setText(rulescontent[32]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "33") {
			detailtitle.setText(rulestitle[33]);
			detailcontent.setText(rulescontent[33]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 3);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		}
		/*
		 * 常见问题内容
		 */
		else if (dt == "34") {
			detailcontent.setText(commonproblemcontent[0]);
			detailtitle.setText(commonproblemtitle[0]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 1);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "35") {
			detailcontent.setText(commonproblemcontent[1]);
			detailtitle.setText(commonproblemtitle[1]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 1);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "36") {
			detailcontent.setText(commonproblemcontent[2]);
			detailtitle.setText(commonproblemtitle[2]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 1);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "37") {
			detailcontent.setText(commonproblemcontent[3]);
			detailtitle.setText(commonproblemtitle[3]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 1);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "38") {
			detailcontent.setText(commonproblemcontent[4]);
			detailtitle.setText(commonproblemtitle[4]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 1);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "39") {
			detailcontent.setText(commonproblemcontent[5]);
			detailtitle.setText(commonproblemtitle[5]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 1);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "40") {
			detailcontent.setText(commonproblemcontent[6]);
			detailtitle.setText(commonproblemtitle[6]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 1);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "41") {
			detailcontent.setText(commonproblemcontent[7]);
			detailtitle.setText(commonproblemtitle[7]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 1);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "42") {
			detailcontent.setText(commonproblemcontent[8]);
			detailtitle.setText(commonproblemtitle[8]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 1);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "43") {
			detailcontent.setText(commonproblemcontent[9]);
			detailtitle.setText(commonproblemtitle[9]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 1);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "44") {
			detailcontent.setText(commonproblemcontent[10]);
			detailtitle.setText(commonproblemtitle[10]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 1);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "45") {
			detailcontent.setText(commonproblemcontent[11]);
			detailtitle.setText(commonproblemtitle[11]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 1);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		}
		/*
		 * 服务指南内容
		 */
		else if (dt == "46") {
			detailtitle.setText(servicetitle[0]);
			detailcontent.setText(servicecontent[0]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 1);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "47") {
			detailtitle.setText(servicetitle[1]);
			detailcontent.setText(servicecontent[1]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 1);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		}
		/*
		 * 新生入馆帮助内容
		 */
		else if (dt == "48") {
			detailtitle.setText(helptitle[0]);
			detailcontent.setText(helpcontent[0]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 1);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "49") {
			detailtitle.setText(helptitle[1]);
			detailcontent.setText(helpcontent[1]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 1);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		} else if (dt == "50") {
			detailtitle.setText(helptitle[2]);
			detailcontent.setText(helpcontent[2]);
			mback.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					mback.setImageDrawable(getResources().getDrawable(
							s1[0]));
					if (s1[0] == R.drawable.back_default) {
						Intent myIntent = new Intent();
						myIntent = new Intent(Detail.this,
								BaseActivity.class);
						myIntent.putExtra("num", 1);
						startActivity(myIntent);
						Detail.this.finish();
					}
				}
			});
		}

	}


}