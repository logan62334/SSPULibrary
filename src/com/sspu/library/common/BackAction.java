package com.sspu.library.common;

import android.content.Context;
import android.content.Intent;

public class BackAction {
	
	public static void Back(Context context, Class<?> cls,int num) {
		Intent myIntent = new Intent();
		myIntent = new Intent(context, cls);
		myIntent.putExtra("num", num);
		context.startActivity(myIntent);
	}
}
