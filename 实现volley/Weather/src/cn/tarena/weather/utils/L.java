package cn.tarena.weather.utils;

import android.util.Log;

public class L {
	private static final String TAG="tag"; 
	public static void i(String text){
		Log.i(TAG,text);
	}
	public static void w(String text){
		Log.w(TAG,text);
	}
	public static void d(String text){
		Log.d(TAG,text);
	}
	public static void e(String text){
		Log.e(TAG,text);
	}
}
