package cn.tarena.weather.adapter;

import java.util.ArrayList;

import cn.tarena.weather.R;
import cn.tarena.weather.entity.FWeather;
import cn.tarena.weather.entity.Info;
import cn.tarena.weather.utils.WIUtils;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FutureWeatherAdapter extends BaseAdapter {
private ArrayList<FWeather>   fWeatherList;
private Context   context;
public FutureWeatherAdapter(ArrayList<FWeather>   fWeatherList, Context   context){
	this.fWeatherList=fWeatherList;
	this.context=context;
}
	@Override
	public int getCount() {
		return fWeatherList.size();
	}
	@Override
	public Object getItem(int position) {
		return fWeatherList.get(position);
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		FWeather  fWeather=fWeatherList.get(position);
		if(convertView==null){
			convertView=View.inflate(context, R.layout.future_weather_item, null);
		}
		ImageView  iv=(ImageView) convertView.findViewById(R.id.iv_future_item);
		TextView     tv1=(TextView) convertView.findViewById(R.id.tv1_future_item);
		TextView     tv2=(TextView) convertView.findViewById(R.id.tv2_future_item);
		TextView     tv3=(TextView) convertView.findViewById(R.id.tv3_future_item);
		Info   info=fWeather.getInfo();
		String []day=fWeather.getInfo().getDay();
		String weatherinfo=fWeather.getInfo().getDay()[1];
		iv.setImageResource(WIUtils.STI(weatherinfo));
		tv1.setText(""+weatherinfo) ;
		String tem1=fWeather.getInfo().getDay()[2];
		String tem2=fWeather.getInfo().getNight()[2];
		tv2.setText(tem1+"/"+tem2);
		tv3.setText("ÐÇÆÚ"+fWeather.getWeek());
		return convertView;
	}
		
}
