package com.example.usermanager.adapter;

import com.example.usermanager.R;
import com.example.usermanager.entity.User;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/*
 * 最终的所有布局的显示
 * 
 */
public class UserAdapter extends BaseAdapter {
	private User[] users;
	private Context context;
	private int layout;

	public UserAdapter(User[] users, Context context, int layout) {
		this.users = users;
		this.context = context;
		this.layout = layout;
	}

	@Override
	public int getCount() {
		return users.length;
	}

	@Override
	public Object getItem(int position) {
		return users[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) { // 当前位置，包裹当前item的view，
		// 显示布局 context
		User user = users[position];

		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = View.inflate(context, layout, null);
			viewHolder = new ViewHolder();
			viewHolder.imageview = (ImageView) convertView
					.findViewById(R.id.imageView1);
			viewHolder.tv1 = (TextView) convertView
					.findViewById(R.id.textView1);
			viewHolder.tv2 = (TextView) convertView
					.findViewById(R.id.textView2);
			// view=View.inflate(context, layout, null); //动态加载一个布局
			convertView.setTag(viewHolder); // 重新利用当前的view
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.imageview.setImageResource(user.getUser_icon());// 绘制图片
		viewHolder.tv1.setText(user.getUsername());
		viewHolder.tv2.setText(user.getPassword());
		return convertView;
	}

	class ViewHolder {
		ImageView imageview;

		TextView tv1;
		TextView tv2;
	}

}
