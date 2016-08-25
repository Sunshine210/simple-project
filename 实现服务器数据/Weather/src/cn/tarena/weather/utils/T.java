package cn.tarena.weather.utils;

import android.content.Context;
import android.widget.Toast;

public class T {
	  public static void sl(Context context,String text){
		  Toast.makeText(context, text, Toast.LENGTH_LONG).show();
	  }
	  public static void ss(Context context,String text){
		  Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	  }
}
