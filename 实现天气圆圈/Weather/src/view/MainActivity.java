package view;

import com.natasa.progressviews.CircleSegmentBar;

import cn.tarena.weather.R;
import cn.tarena.weather.R.id;
import cn.tarena.weather.R.layout;
import cn.tarena.weather.updatable.BMainActivity;
import adapter.HorizontalListView;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends BMainActivity implements OnClickListener {
//title中的控件
	private TextView tv_title;
	//weather_layout
	private TextView weather_tv_update_time,weather_xinqi,weather_content;

	private CircleSegmentBar weather_csb;
	
	//futureweather
	private View future_line;
	private HorizontalListView future_weather_hlv;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		loadLayout(R.layout.activity_main);
		initView();
		showWeatherBack("晴");
		
	}

	@Override
	public void onClick(View v) {
		
	}
	
	@Override
	public void wordBlack() {
		tv_title.setTextColor(getResources().getColor(R.color.black));
		weather_tv_update_time.setTextColor(getResources().getColor(R.color.black));
		weather_xinqi.setTextColor(getResources().getColor(R.color.black));
        weather_content.setTextColor(getResources().getColor(R.color.black));
		future_line.setBackgroundColor(getResources().getColor(R.color.black));
	}
	@Override
	public void wordWhite() {
		tv_title.setTextColor(getResources().getColor(R.color.white));
		weather_tv_update_time.setTextColor(getResources().getColor(R.color.white));
		weather_xinqi.setTextColor(getResources().getColor(R.color.white));
        weather_content.setTextColor(getResources().getColor(R.color.white));
		future_line.setBackgroundColor(getResources().getColor(R.color.white));
	}
	 private void initView(){
		 tv_title = (TextView) findViewById(R.id.title_tv);
		 weather_tv_update_time = (TextView) findViewById(R.id.weather_tv_update_time);
		 weather_xinqi = (TextView) findViewById(R.id.weather_xinqi);
		 weather_content = (TextView) findViewById(R.id.weather_content);
		 future_line = findViewById(R.id.future_line);
		 future_weather_hlv =(HorizontalListView) findViewById(R.id.future_weather_hlv);
		
	}

}
