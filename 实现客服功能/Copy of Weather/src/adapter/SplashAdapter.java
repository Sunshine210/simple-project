package adapter;

import view.MainActivity;
import cn.tarena.weather.R;
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

public class SplashAdapter extends PagerAdapter {
	private Context context;
	private OnClickListener listener;

	public SplashAdapter(Activity activity, OnClickListener listener) {

		context = activity;
		this.listener = listener;
	}

	int[] resImageId = new int[] { R.drawable.welcome_1, R.drawable.welcome_2,
			R.drawable.welcome_3 };// 放图片进数组

	@Override
	public int getCount() {
		return 3;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	// 类似listview中的GetView方法
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view = LayoutInflater.from(context).inflate(
				R.layout.splash_viewpager_item, null);
		// 还有一种写法为View view = View.inflate(context,
		// R.layout.splash_viewpager_item, null);
		ImageView iv = (ImageView) view.findViewById(R.id.iv_splash_item);
		Button btn = (Button) view.findViewById(R.id.bt_splash_start);
		iv.setImageResource(resImageId[position]);
		if (position == 2) { // 向右滑动，显示图片
			btn.setVisibility(View.VISIBLE);
			btn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					context.startActivity(new Intent(context,
							MainActivity.class));
				}
			});
		} else {
			btn.setVisibility(View.GONE);
		}
		container.addView(view);
		return view;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

}
