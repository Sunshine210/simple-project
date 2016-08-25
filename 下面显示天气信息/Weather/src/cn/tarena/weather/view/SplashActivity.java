package cn.tarena.weather.view;

import cn.tarena.weather.R;
import cn.tarena.weather.adapter.SplashAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class SplashActivity extends BaseActivity {
	private ViewPager  splash_vp;
	private  ImageView iv_splash1;
	private  ImageView iv_splash2;
	private  ImageView iv_splash3;
	private  boolean isFirstOpen=true;
	@Override
	public void loadLayout() {//加载布局
			setContentView(R.layout.actvity_splash);
	}
	@Override
	public void initView() {//加载View
		splash_vp=(ViewPager) findViewById(R.id.splash_viewpager);
		iv_splash1=(ImageView) findViewById(R.id.iv_splash1);
		iv_splash2=(ImageView) findViewById(R.id.iv_splash2);
		iv_splash3=(ImageView) findViewById(R.id.iv_splash3);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//去掉ActionBar
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//?????style Theme.Light.NoActionBar
		super.onCreate(savedInstanceState);
		//获得偏好设置
		SharedPreferences sp=getSharedPreferences("config", MODE_PRIVATE);
		//判断是否为第一次打开
		isFirstOpen=sp.getBoolean("IS_FIRST_OPEN", true);
		if(isFirstOpen){
			//如果是第一次打开  则不显示WEATHER
			findViewById(R.id.tv_splash_weather).setVisibility(View.GONE);
			firstOpen();
			//第一次打开已经执行结束，改变第一次打开的值
			SharedPreferences.Editor  spe=sp.edit();
			spe.putBoolean("IS_FIRST_OPEN", false);
			spe.commit();
		}else{
			//让WEAHER显示
			findViewById(R.id.tv_splash_weather).setVisibility(View.VISIBLE);
			//启动工作线程
			new Thread(new Runnable() {
				@Override//工作线程中执行的任务
				public void run() {
					//让当前线程睡眠两秒
						SystemClock.sleep(2000);
						//调用UI线程执行方法
						runOnUiThread(new Runnable() {
							@Override
							public void run() {//UI线程执行内容
								SplashActivity.this.startActivity(
										new Intent(SplashActivity.this,MainActivity.class));
							}
						});
				}
			}).start();
			System.out.println("asdsad");
			System.out.println("asdsad");
			System.out.println("asdsad");
		}
	}
	private void firstOpen() {
		//设置页面指示器
		iv_splash3.setVisibility(View.VISIBLE);
		iv_splash1.setVisibility(View.VISIBLE);
		iv_splash2.setVisibility(View.VISIBLE);
		//第一次打开，为ViewPager设置Adapter
		splash_vp.setAdapter(new SplashAdapter(this,new OnClickListener() {
			@Override
			public void onClick(View v) {//为第三个界面中的Button设置监听
				SplashActivity.this.startActivity(//跳转界面
						new Intent(SplashActivity.this,MainActivity.class));
			}
		}));
		//为ViewPager设置监听
		splash_vp.setOnPageChangeListener(new OnPageChangeListener() {
			@Override//当某一个Page显示的时候
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
			@Override//页面滚动的时候回掉
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			@Override//页面状态发生变化
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}
	
}
