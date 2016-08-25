package view;

import cn.tarena.weather.R;
import adapter.SplashAdapter;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

public class SplashActivity extends BaseActivity {
	private ViewPager splash_vp;//实现滑动
	private ImageView iv_splash1;
	private ImageView iv_splash2;
	private ImageView iv_splash3;
	private boolean isFirstOpen = true;
	private isFirstOpen data = new isFirstOpen(true);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);//消除题目
		super.onCreate(savedInstanceState);
	    SharedPreferences sp = getSharedPreferences("config", MODE_APPEND);
	    isFirstOpen = sp.getBoolean("IS_FIRST_OPEN", true);
	    if(isFirstOpen){
	    	//如果是第一次打开则不显示WEATHER
	    	findViewById(R.id.tv_splash_weather).setVisibility(View.GONE);
	    	firstOpen();
	    	//第一次打开已经执行结束，改变第一次打开的值
	    	SharedPreferences.Editor spe = sp.edit();
	    	spe.putBoolean("IS_FIRST_OPEN", false);
	    	spe.commit();
	    }else{
	    	//让WEATHER显示
	    	findViewById(R.id.splash_viewpager).setVisibility(View.VISIBLE);
	    	findViewById(R.id.iv_splash1).setVisibility(View.GONE);
	    	findViewById(R.id.iv_splash2).setVisibility(View.GONE);
	    	findViewById(R.id.iv_splash3).setVisibility(View.GONE);
	    	//启动工作线程
	    	new Thread(new Runnable() {
				
				@Override
				public void run() {
					//让欢迎界面停顿两秒
					SystemClock.sleep(2000);
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {  //主线程
							SplashActivity.this.startActivity(
									new Intent(SplashActivity.this,MainActivity.class));
						}
					});
				}
					
			}).start();
	    }
	}	
	   private void firstOpen(){
		   //设置页面指示器
		iv_splash3.setVisibility(View.VISIBLE);
		iv_splash1.setVisibility(View.VISIBLE);
		iv_splash2.setVisibility(View.VISIBLE);
		////第一次打开，为ViewPager设置Adapter
	  splash_vp.setAdapter(new SplashAdapter(this, new OnClickListener() {
			@Override
			public void onClick(View v) {
				//为第三个界面中的Button设置监听
				
			SplashActivity.this.startActivity(
					new Intent(SplashActivity.this,MainActivity.class));
			}
		}));
	  //为ViewPager设置监听
		splash_vp.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				switch (arg0) {
				case 0://控制页面指示器显示，防止用户回滑界面
					iv_splash3.setVisibility(View.VISIBLE);
					iv_splash1.setVisibility(View.VISIBLE);
					iv_splash2.setVisibility(View.VISIBLE);
					iv_splash1.setImageResource(R.drawable.point_checked);
					iv_splash2.setImageResource(R.drawable.point_nocheked);
					iv_splash3.setImageResource(R.drawable.point_nocheked);
					break;
				case 1:
					iv_splash3.setVisibility(View.VISIBLE);
					iv_splash1.setVisibility(View.VISIBLE);
					iv_splash2.setVisibility(View.VISIBLE);
					iv_splash2.setImageResource(R.drawable.point_checked);
					iv_splash1.setImageResource(R.drawable.point_nocheked);
					iv_splash3.setImageResource(R.drawable.point_nocheked);
					break;
				case 2:
					iv_splash3.setVisibility(View.GONE);
					iv_splash1.setVisibility(View.GONE);
					iv_splash2.setVisibility(View.GONE);
					break;
				}
				
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {//页面滚动的时候回掉
			}
			@Override
			public void onPageScrollStateChanged(int arg0) {//页面状态发生变化
			}
		});
	}

	@Override
	public void loadLayout() {
		setContentView(R.layout.activity_splash);
	}

	@Override
	public void initView() {
	splash_vp = (ViewPager) findViewById(R.id.splash_viewpager);
		iv_splash1 = (ImageView) findViewById(R.id.iv_splash1);
		iv_splash2 = (ImageView) findViewById(R.id.iv_splash2);
		iv_splash3 = (ImageView) findViewById(R.id.iv_splash3);
	}

}
