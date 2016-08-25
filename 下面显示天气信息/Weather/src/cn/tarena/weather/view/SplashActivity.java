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
	public void loadLayout() {//���ز���
			setContentView(R.layout.actvity_splash);
	}
	@Override
	public void initView() {//����View
		splash_vp=(ViewPager) findViewById(R.id.splash_viewpager);
		iv_splash1=(ImageView) findViewById(R.id.iv_splash1);
		iv_splash2=(ImageView) findViewById(R.id.iv_splash2);
		iv_splash3=(ImageView) findViewById(R.id.iv_splash3);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//ȥ��ActionBar
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//?????style Theme.Light.NoActionBar
		super.onCreate(savedInstanceState);
		//���ƫ������
		SharedPreferences sp=getSharedPreferences("config", MODE_PRIVATE);
		//�ж��Ƿ�Ϊ��һ�δ�
		isFirstOpen=sp.getBoolean("IS_FIRST_OPEN", true);
		if(isFirstOpen){
			//����ǵ�һ�δ�  ����ʾWEATHER
			findViewById(R.id.tv_splash_weather).setVisibility(View.GONE);
			firstOpen();
			//��һ�δ��Ѿ�ִ�н������ı��һ�δ򿪵�ֵ
			SharedPreferences.Editor  spe=sp.edit();
			spe.putBoolean("IS_FIRST_OPEN", false);
			spe.commit();
		}else{
			//��WEAHER��ʾ
			findViewById(R.id.tv_splash_weather).setVisibility(View.VISIBLE);
			//���������߳�
			new Thread(new Runnable() {
				@Override//�����߳���ִ�е�����
				public void run() {
					//�õ�ǰ�߳�˯������
						SystemClock.sleep(2000);
						//����UI�߳�ִ�з���
						runOnUiThread(new Runnable() {
							@Override
							public void run() {//UI�߳�ִ������
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
		//����ҳ��ָʾ��
		iv_splash3.setVisibility(View.VISIBLE);
		iv_splash1.setVisibility(View.VISIBLE);
		iv_splash2.setVisibility(View.VISIBLE);
		//��һ�δ򿪣�ΪViewPager����Adapter
		splash_vp.setAdapter(new SplashAdapter(this,new OnClickListener() {
			@Override
			public void onClick(View v) {//Ϊ�����������е�Button���ü���
				SplashActivity.this.startActivity(//��ת����
						new Intent(SplashActivity.this,MainActivity.class));
			}
		}));
		//ΪViewPager���ü���
		splash_vp.setOnPageChangeListener(new OnPageChangeListener() {
			@Override//��ĳһ��Page��ʾ��ʱ��
			public void onPageSelected(int arg0) {
				switch (arg0) {
				case 0://����ҳ��ָʾ����ʾ����ֹ�û��ػ�����
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
			@Override//ҳ�������ʱ��ص�
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			@Override//ҳ��״̬�����仯
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}
	
}
