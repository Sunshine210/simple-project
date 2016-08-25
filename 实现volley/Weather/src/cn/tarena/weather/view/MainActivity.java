package cn.tarena.weather.view;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.protocol.ResponseContent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.natasa.progressviews.CircleSegmentBar;
import com.natasa.progressviews.utils.ProgressStartPoint;

import cn.tarena.weather.R;
import cn.tarena.weather.R.id;
import cn.tarena.weather.R.layout;
import cn.tarena.weather.adapter.FutureWeatherAdapter;
import cn.tarena.weather.dao.WeatherDataDao;
import cn.tarena.weather.entity.Data;
import cn.tarena.weather.entity.FWeather;
import cn.tarena.weather.entity.Pm25;
import cn.tarena.weather.entity.Realtime;
import cn.tarena.weather.entity.Res;
import cn.tarena.weather.entity.Result;
import cn.tarena.weather.entity.Weather;
import cn.tarena.weather.entity.Wind;
import cn.tarena.weather.updatable.BMainActivity;
import cn.tarena.weather.updatable.HorizontalListView;
import cn.tarena.weather.utils.T;
import cn.tarena.weather.utils.Xingqi;
import android.app.DownloadManager.Request;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BMainActivity implements OnClickListener {
	// title中的控件
	private TextView tv_title;
	// weather_layout中的控件
	private TextView weather_tv_update_time, weather_xingqi, weather_content;
	private CircleSegmentBar weather_csb;
	// future weather
	private View future_line;
	private Res res;
	private HorizontalListView future_wether_hlv;
	private String jsonRes;
	private Handler handlerMain = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				T.ss(getApplicationContext(), "请求失败");
				break;
			case 1:
				try {
					setWeather();//解析数据并显示
					setFutureWeather();
					Weather weather =res.getResult().getData().getRealtime().getWeather();
					setCSB(weather.getTemperature());
					showWeatherBack("晴");
					
				} catch (Exception e) {
					T.ss(getApplicationContext(), "数据异常")	;
				}
				
				break;
			}

		}
	};



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		loadLayout(R.layout.activity_main);
		initView();
		try {
			setNet();
		} catch (UnsupportedEncodingException e) {
			T.ss(getApplicationContext(), "网络异常");
			e.printStackTrace();
		}
		
		/*
*/
	}

	private void setNet() throws UnsupportedEncodingException {
		RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
		String city = URLEncoder.encode("南京", "utf-8");
		StringRequest stringRequest = new StringRequest(
				"http://10.0.2.2:8080/wserver/weather.do?city=" + city,// 有网加个juweather
				new Response.Listener<String>() {
					@Override
					public void onResponse(String arg0) {
						handlerMain.sendEmptyMessage(1);// 网络请求结束后，发一个空的消息
						jsonRes =arg0;
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError arg0) {
						handlerMain.sendEmptyMessage(0);// 网络请求结束后，发一个空的消息
					}
				});
		requestQueue.add(stringRequest);
	}

	private void setFutureWeather() {
		FutureWeatherAdapter fwa = new FutureWeatherAdapter(res.getResult()
				.getData().getFweatherList(), this);
		future_wether_hlv.setAdapter(fwa);
	}

	private void setWeather() throws Exception {
		WeatherDataDao wdd = new WeatherDataDao();
		res = wdd.processRes(jsonRes);
		if (res == null) {
			T.ss(getApplicationContext(), "没有数据");
			return;
		}
		Realtime realtime = res.getResult().getData().getRealtime();
		tv_title.setText("" + realtime.getCity_name());
		Weather weather = realtime.getWeather();
		Pm25 pm25 = res.getResult().getData().getPm25();
		weather_content.setText(weather.getInfo() + "|空气质量:"
				+ pm25.getQuality());
		weather_tv_update_time.setText(realtime.getDate());
		weather_xingqi.setText("星期" + Xingqi.nx(realtime.getWeek()));
	}

	private void setCSB(final String temperautre) {
		// 设置每一个小块之间的间距
		weather_csb.setCircleViewPadding(2);
		// 设置背景线宽度
		weather_csb.setWidthProgressBackground(25);
		// 设置进度条线的宽度
		weather_csb.setWidthProgressBarLine(25);
		weather_csb.setStartPositionInDegrees(ProgressStartPoint.DEFAULT);
		// 设置线性梯度
		weather_csb.setLinearGradientProgress(true);
		// 发送延迟消息
		final Handler handler = new Handler();
		handler.removeCallbacksAndMessages(null);
		// 任务在500ms后执行
		handler.postDelayed(new Runnable() {
			int tem = Integer.parseInt(temperautre);
			int progress = 0;

			@Override
			public void run() {
				progress += 1;
				if (tem * 100 / 50 >= progress) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							weather_csb.setProgress(progress);
							weather_csb.setText(progress * 50 / 100 + "℃");
						}
					});
				}
				handler.postDelayed(this, 100);
			}
		}, 500);

	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public void wordBlack() {
		tv_title.setTextColor(getResources().getColor(R.color.black));
		weather_tv_update_time.setTextColor(getResources().getColor(
				R.color.black));
		weather_xingqi.setTextColor(getResources().getColor(R.color.black));
		weather_content.setTextColor(getResources().getColor(R.color.black));
		future_line.setBackgroundColor(getResources().getColor(R.color.black));
	}

	@Override
	public void wordWhite() {
		tv_title.setTextColor(getResources().getColor(R.color.white));
		weather_tv_update_time.setTextColor(getResources().getColor(
				R.color.white));
		weather_xingqi.setTextColor(getResources().getColor(R.color.white));
		weather_content.setTextColor(getResources().getColor(R.color.white));
		future_line.setBackgroundColor(getResources().getColor(R.color.white));
	}

	private void initView() {
		tv_title = (TextView) findViewById(R.id.title_tv);
		weather_tv_update_time = (TextView) findViewById(R.id.weather_tv_update_time);
		weather_xingqi = (TextView) findViewById(R.id.weather_xingqi);
		weather_content = (TextView) findViewById(R.id.weather_content);
		weather_csb = (CircleSegmentBar) findViewById(R.id.weather_csb);
		future_line = findViewById(R.id.future_line);
		future_wether_hlv = (HorizontalListView) findViewById(R.id.future_wether_hlv);
	}
}
