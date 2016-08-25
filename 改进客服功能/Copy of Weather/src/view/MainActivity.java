package view;

import mooo.mainActivity;

import com.natasa.progressviews.CircleSegmentBar;

import cn.tarena.weather.R;
import cn.tarena.weather.R.id;
import cn.tarena.weather.R.layout;
import cn.tarena.weather.updatable.BMainActivity;
import adapter.HorizontalListView;
import android.R.integer;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends BMainActivity implements OnClickListener {
	// title中的控件
	private TextView tv_title;
	private Button tiaozhuan;//此处需要添加
	// weather_layout
	private TextView weather_tv_update_time, weather_xinqi, weather_content;

	private CircleSegmentBar weather_csb;

	// futureweather
	private View future_line;
	private HorizontalListView future_weather_hlv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		loadLayout(R.layout.activity_main);
		initView();
		setListener();//此处添加
		setCSB("32");
		showWeatherBack("晴");

	}
	private void setListener() { //该方法需要添加
		tiaozhuan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 意图 Intent
				Intent intent = new Intent(MainActivity.this,
						mainActivity.class);
				// startActivity
				startActivityForResult(intent, 1);
			}
		});
	}

	private void setCSB(final String temperature) {
		weather_csb.setCircleViewPadding(2);// 设置每一个小块之间的间距
		weather_csb.setWidthProgressBackground(25);//设置背景线宽度
		weather_csb.setWidthProgressBarLine(15);//设置进度条的宽度
		weather_csb.setLinearGradientProgress(true);
		//发送延迟消息
		final Handler handler = new Handler();
		handler.removeCallbacksAndMessages(null);// 移除回调的
		handler.postDelayed(new Runnable() {
			// 任务在500毫秒后执行
			int tem = Integer.parseInt(temperature);
			int progress = 0;
			@Override
			public void run() {
				progress += 2;
				if (tem * 100 / 50 >= progress) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							weather_csb.setProgress(progress);
							weather_csb.setText(progress * 50 / 100 + "");
						}
					});
				}
				//递归调用当前任务,在200毫秒以后
				handler.postDelayed(this, 200);
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
		weather_xinqi.setTextColor(getResources().getColor(R.color.black));
		weather_content.setTextColor(getResources().getColor(R.color.black));
		future_line.setBackgroundColor(getResources().getColor(R.color.black));
	}

	@Override
	public void wordWhite() {
		tv_title.setTextColor(getResources().getColor(R.color.white));
		weather_tv_update_time.setTextColor(getResources().getColor(
				R.color.white));
		weather_xinqi.setTextColor(getResources().getColor(R.color.white));
		weather_content.setTextColor(getResources().getColor(R.color.white));
		future_line.setBackgroundColor(getResources().getColor(R.color.white));
	}

	private void initView() {
		tv_title = (TextView) findViewById(R.id.title_tv);
		weather_tv_update_time = (TextView) findViewById(R.id.weather_tv_update_time);
		weather_xinqi = (TextView) findViewById(R.id.weather_xinqi);
		weather_content = (TextView) findViewById(R.id.weather_content);
		weather_csb = (CircleSegmentBar) findViewById(R.id.weather_csb);
		future_line = findViewById(R.id.future_line);
		future_weather_hlv = (HorizontalListView) findViewById(R.id.future_weather_hlv);
		tiaozhuan = (Button) findViewById(R.id.tiaozhuan);//此处需要添加
	}

}
