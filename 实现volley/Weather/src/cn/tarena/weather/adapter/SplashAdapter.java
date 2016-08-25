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
//BaseAdapter    ���ĸ�Ҫʵ�ֵķ���
public class SplashAdapter extends PagerAdapter{
	private Context  context;//�����Ķ���
	private OnClickListener listener;//������
	public SplashAdapter(Activity  activity,OnClickListener listener){
		context=activity;
		this.listener=listener;
	}//ͼƬ��Դid
	int[]  resImageId=new int[]{R.drawable.welcome_1
			,R.drawable.welcome_2,R.drawable.welcome_3};
	@Override
	public int getCount() {//ҳ������
		return 3;
	}
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0==arg1;
	}
	//������ListView�е�getView����
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
		container.addView(view);//���View��������
		return view;
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View)object);//�Ƴ�����
	}
}
