package com.example.usermanager.adapter;

import com.example.usermanager.R;
import com.example.usermanager.entity.User;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UserAdapter extends BaseAdapter{
	private  User[]  users;
	private   Context context;
	private int layout;
	public UserAdapter(User[] users,Context context,int layout){
			this.users=users;
			this.context=context;
			this.layout=layout;
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
	public View getView(int position, View convertView, ViewGroup parent) {
		//≤ºæ÷   context
		View view=View.inflate(context, layout, null);
		ImageView imageview=(ImageView) view.findViewById(R.id.imageView1);
		TextView tv1=(TextView) view.findViewById(R.id.textView1);
		TextView tv2=(TextView) view.findViewById(R.id.textView2);
		User  user=users[position];
		imageview.setImageResource(user.getUser_icon());//ªÊ÷∆Õº∆¨
		tv1.setText(user.getUsername());
		tv2.setText(user.getPassword());
		return view;
	}
		
}
