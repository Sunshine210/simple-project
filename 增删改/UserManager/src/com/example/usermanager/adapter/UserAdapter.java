package com.example.usermanager.adapter;

import java.util.ArrayList;

import com.example.usermanager.R;
import com.example.usermanager.entity.User;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UserAdapter extends BaseAdapter {
	private ArrayList<User> userlist;
	private Context context;
	private int layout;

	public UserAdapter(ArrayList<User> userlist, Context context, int layout) {
		this.userlist = userlist;
		this.context = context;
		this.layout = layout;
	}

	@Override
	public int getCount() {
		return userlist.size();
	}

	@Override
	public Object getItem(int position) {
		return userlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		/*
		 * 该属性是判断显示在界面上的是第几个
		 * 因为手机屏幕就那么大，所以一次展示给用户看见的内容是固定的，如果你List中有1000条数据，不应该new1000个converView
		 * ，那样内存肯定不足，应该学会控件重用，滑出屏幕的converView就在下面新进来的item中重新使用，只是修改下他展示的值
		 * ViewGroup parent 这个属性是加载xml视图时使用。inflate确定他父控件，减少宽高的测算
		 */
		User user = userlist.get(position);
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = View.inflate(context, layout, null);
			viewHolder = new ViewHolder();
			viewHolder.imageview = (ImageView) convertView
					.findViewById(R.id.imageView1);
			viewHolder.tv1 = (TextView) convertView
					.findViewById(R.id.textView1);
			viewHolder.tv2 = (TextView) convertView
					.findViewById(R.id.textView2);
			viewHolder.tv_remove = (TextView) convertView
					.findViewById(R.id.tv_remove);
			//可以使用setTag把查找的view缓存起来方便多次重用
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.imageview.setImageResource(user.getUser_icon());
		viewHolder.tv1.setText(user.getUsername());
		viewHolder.tv2.setText(user.getPassword());
		viewHolder.tv_remove.setOnClickListener(new OnClickListener() {// 匿名类对象
					@Override
					public void onClick(View v) {
						userlist.remove(position);
						UserAdapter.this.notifyDataSetChanged();
					}
				});
		return convertView;
	}

	class ViewHolder {
		ImageView imageview;
		TextView tv1;
		TextView tv2;
		TextView tv_remove;
	}
}
