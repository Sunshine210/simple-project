package com.example.usermanager;


import java.util.ArrayList;
import java.util.Arrays;

import com.example.usermanager.adapter.UserAdapter;
import com.example.usermanager.biz.UserManagerBiz;
import com.example.usermanager.entity.User;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class WelcomeActivity extends Activity {
private ImageView title_left;
private TextView title_content;
private ImageView title_right;
private ListView listView;

private User[]  users=new User[50];
private ArrayList<User>userlist;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		initView();
		setData();
		Intent intent=getIntent();
		String username=intent.getStringExtra("name");
		title_content.setText(username);
		//adapter
		UserAdapter  adapter=new UserAdapter(userlist,WelcomeActivity.this, R.layout.user_manage_list_item);
		/*System.out.println(listView);
		Log.i("jmy", ""+listView);
		Log.i("jmy", ""+adapter);
		Log.i("jmy", ""+Arrays.toString(users));日志显示*/
		listView.setAdapter(adapter);
		
		
	}
	private void setData() {//MVC封装
		//Thread
		UserManagerBiz   umb=new UserManagerBiz();
		userlist=(ArrayList<User>) umb.getUserList();
		
	}
	private void initView(){
		title_left=(ImageView) findViewById(R.id.imageView1);
		title_right=(ImageView) findViewById(R.id.imageView2);
		title_content=(TextView) findViewById(R.id.textView1);
		listView=(ListView)findViewById(R.id.listView1);
	}
}
