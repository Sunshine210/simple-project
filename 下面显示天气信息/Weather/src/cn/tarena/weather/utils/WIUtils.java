package cn.tarena.weather.utils;

import cn.tarena.weather.R;

public class WIUtils {
	public static int STI(String weather){
		if(weather.equals("��")||weather.equals("����")){
			return R.drawable.a323;
		}else if(weather.contains("��")){
			return R.drawable.a310;
		}else if(weather.contains("��")){
			return R.drawable.a319;
		}else if(weather.contains("��")){
			return R.drawable.a311;
		}else if(weather.contains("ѩ")){
			return R.drawable.a312;
		}else if(weather.contains("��")){
			return R.drawable.a305;
		}else if(weather.contains("��")){
			return R.drawable.a309;
		}
		return  R.drawable.a325;
	}
}
