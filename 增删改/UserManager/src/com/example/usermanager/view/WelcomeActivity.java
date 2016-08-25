package com.example.usermanager.view;

import java.util.ArrayList;
import java.util.Arrays;

import com.example.usermanager.R;
import com.example.usermanager.R.id;
import com.example.usermanager.R.layout;
import com.example.usermanager.adapter.UserAdapter;
import com.example.usermanager.biz.UserManagerBiz;
import com.example.usermanager.entity.User;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class WelcomeActivity extends Activity {
	private ImageView title_left;
	private TextView title_content;
	private ImageView title_right;
	private ListView listView;

	private User[] users = new User[50];
	private UserAdapter adapter;
	public ArrayList<User> userlist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		initView();
		setListener();

		setData();
		Intent intent = getIntent();
		String username = intent.getStringExtra("name");
		title_content.setText(username);
		// adapter
		//
		adapter = new UserAdapter(userlist, WelcomeActivity.this,
				R.layout.user_manage_list_item);
		listView.setAdapter(adapter);
		/*
		 * 列表的显示需要三个元素：
		 * 
		 * a．ListVeiw 用来展示列表的View。
		 * 
		 * b．适配器 用来把数据映射到ListView上的中介。
		 * 
		 * c．数据 具体的将被映射的字符串，图片，或者基本组件。
		 */
		/*
		 * setAdapter的作用
		 * 顾名思义，就是为listview放置适配器，这次需要更新listview时错就错在错用了notifyDataSetChanged
		 * ，而没有用setAdapter。
		 * 其实问题很简单，我的更新不是常见的对一个单独的listview的增删改查的更新操作，而是每次需要取到新的list的更新，每次都是取新
		 * 的list就要每次都setAdapter一下，最后得以更新listview。
		 */
	}

	private void setListener() {
		title_right.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 意图 Intent
				Intent intent = new Intent(WelcomeActivity.this,
						AddUserActivity.class);
				// startActivity
				startActivityForResult(intent, 1);
			}
		});
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(WelcomeActivity.this,
						AddUserActivity.class);
				intent.putExtra("user", userlist.get(position));
				startActivityForResult(intent, 11);//两个参数，是给跳转的页面一个标识
				/*1、startActivity( ) 
				仅仅是跳转到目标页面，若是想跳回当前页面，则必须再使用一次startActivity( )。
				2、startActivityForResult( ) 
				可以一次性完成这项任务，当程序执行到这段代码的时候，
				假若从T1Activity跳转到下一个Text2Activity，而当这个Text2Activity调用了finish()方法以后，
				程序会自动跳转回T1Activity，并调用前一个T1Activity中的onActivityResult( )方法。*/
			}
		});
		;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1 && resultCode == 2) {
			User user = (User) data.getSerializableExtra("user");
			userlist.add(user);
			adapter.notifyDataSetChanged();
		} else if (requestCode == 11 && resultCode == 3) {
			// 修改User 遍历List 找到id相同的进行修改
			User user = (User) data.getSerializableExtra("user");
			for (User u : userlist) {
				if (user.getId().equals(u.getId())) {
					u.setUsername(user.getUsername());
					u.setPassword(user.getPassword());
					break;
				}
			}
			adapter.notifyDataSetChanged();
			/*
			 * Adapter中notifyDataSetChanged的作用
			 * 对于Android开发来说处理一些界面需要和Adapter适配器打交道
			 * ，虽然Android自带了一些比如ArrayAdapter但是大多数情况下无
			 * 法满足我们需要，所以就要从BaseAdapter派生一个类满足我们特殊的需要。 首先我们可能重写getView()，
			 * 通过LayoutInflater的inflate方法映射一个自己定义的Layout布局xml加载或从xxxView中创建。这些大
			 * 家可能滚瓜烂熟了但是仍然很多Android开发者对于BaseAdapter中notifyDataSetChanged
			 * ()方法不是很理解，notifyDataSetChanged方
			 * 法通过一个外部的方法控制如果适配器的内容改变时需要强制调用getView来刷新每个Item的内容。
			 */
		}
	}

	private void setData() {// MVC
		// Thread
		UserManagerBiz umb = new UserManagerBiz();
		userlist = (ArrayList<User>) umb.getUserList();
	}

	private void initView() {
		title_left = (ImageView) findViewById(R.id.imageView1);
		title_right = (ImageView) findViewById(R.id.imageView2);
		title_content = (TextView) findViewById(R.id.textView1);
		listView = (ListView) findViewById(R.id.listView1);
	}
}
