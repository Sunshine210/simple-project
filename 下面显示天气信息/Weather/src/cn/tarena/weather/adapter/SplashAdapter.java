package cn.tarena.weather.adapter;

import cn.tarena.weather.R;
import cn.tarena.weather.view.MainActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
//BaseAdapter    有四个要实现的方法
public class SplashAdapter extends PagerAdapter{
	private Context  context;//上下文对象
	private OnClickListener listener;//监听器
	public SplashAdapter(Activity  activity,OnClickListener listener){
		context=activity;
		this.listener=listener;
	}//图片资源id
	int[]  resImageId=new int[]{R.drawable.welcome_1
			,R.drawable.welcome_2,R.drawable.welcome_3};
	@Override
	public int getCount() {//页面数量
		return 3;
	}
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0==arg1;
	}
	//类似于ListView中的getView方法
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		//View view=View.inflate(context, R.layout.splash_viewpager_item, null);
		View view=
				LayoutInflater.from(context)
				    .inflate(R.layout.splash_viewpager_item, null);
		ImageView iv=(ImageView) view.findViewById(R.id.iv_splash_item);
		Button btn=(Button) view.findViewById(R.id.bt_splash_start);
		iv.setImageResource(resImageId[position]);
		if(position==2){
			btn.setVisibility(View.VISIBLE);
			btn.setOnClickListener(listener);
		}else{
			btn.setVisibility(View.GONE);
		}
		container.addView(view);//添加View到界面中
		return view;
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View)object);//移除界面
	}
}
