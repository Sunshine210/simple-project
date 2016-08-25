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
		 * �б����ʾ��Ҫ����Ԫ�أ�
		 * 
		 * a��ListVeiw ����չʾ�б��View��
		 * 
		 * b�������� ����������ӳ�䵽ListView�ϵ��н顣
		 * 
		 * c������ ����Ľ���ӳ����ַ�����ͼƬ�����߻��������
		 */
		/*
		 * setAdapter������
		 * ����˼�壬����Ϊlistview�����������������Ҫ����listviewʱ��ʹ��ڴ�����notifyDataSetChanged
		 * ����û����setAdapter��
		 * ��ʵ����ܼ򵥣��ҵĸ��²��ǳ����Ķ�һ��������listview����ɾ�Ĳ�ĸ��²���������ÿ����Ҫȡ���µ�list�ĸ��£�ÿ�ζ���ȡ��
		 * ��list��Ҫÿ�ζ�setAdapterһ�£������Ը���listview��
		 */
	}

	private void setListener() {
		title_right.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// ��ͼ Intent
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
				startActivityForResult(intent, 11);//�����������Ǹ���ת��ҳ��һ����ʶ
				/*1��startActivity( ) 
				��������ת��Ŀ��ҳ�棬���������ص�ǰҳ�棬�������ʹ��һ��startActivity( )��
				2��startActivityForResult( ) 
				����һ��������������񣬵�����ִ�е���δ����ʱ��
				������T1Activity��ת����һ��Text2Activity���������Text2Activity������finish()�����Ժ�
				������Զ���ת��T1Activity��������ǰһ��T1Activity�е�onActivityResult( )������*/
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
			// �޸�User ����List �ҵ�id��ͬ�Ľ����޸�
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
			 * Adapter��notifyDataSetChanged������
			 * ����Android������˵����һЩ������Ҫ��Adapter�������򽻵�
			 * ����ȻAndroid�Դ���һЩ����ArrayAdapter���Ǵ�����������
			 * ������������Ҫ�����Ծ�Ҫ��BaseAdapter����һ�������������������Ҫ�� �������ǿ�����дgetView()��
			 * ͨ��LayoutInflater��inflate����ӳ��һ���Լ������Layout����xml���ػ��xxxView�д�������Щ��
			 * �ҿ��ܹ��������˵�����Ȼ�ܶ�Android�����߶���BaseAdapter��notifyDataSetChanged
			 * ()�������Ǻ���⣬notifyDataSetChanged��
			 * ��ͨ��һ���ⲿ�ķ���������������������ݸı�ʱ��Ҫǿ�Ƶ���getView��ˢ��ÿ��Item�����ݡ�
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
