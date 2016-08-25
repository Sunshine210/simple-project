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
		 * ���������ж���ʾ�ڽ����ϵ��ǵڼ���
		 * ��Ϊ�ֻ���Ļ����ô������һ��չʾ���û������������ǹ̶��ģ������List����1000�����ݣ���Ӧ��new1000��converView
		 * �������ڴ�϶����㣬Ӧ��ѧ��ؼ����ã�������Ļ��converView���������½�����item������ʹ�ã�ֻ���޸�����չʾ��ֵ
		 * ViewGroup parent ��������Ǽ���xml��ͼʱʹ�á�inflateȷ�������ؼ������ٿ�ߵĲ���
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
			//����ʹ��setTag�Ѳ��ҵ�view������������������
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.imageview.setImageResource(user.getUser_icon());
		viewHolder.tv1.setText(user.getUsername());
		viewHolder.tv2.setText(user.getPassword());
		viewHolder.tv_remove.setOnClickListener(new OnClickListener() {// ���������
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
