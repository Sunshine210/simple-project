package com.example.usermanager.view;
import com.example.usermanager.R;
import com.example.usermanager.R.id;
import com.example.usermanager.R.layout;
import com.example.usermanager.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
public class LoginActivity extends Activity  implements OnClickListener{
	public  static boolean is_debug=false;
	String username;
	String password;
	EditText  name;
	EditText  pwd;
	private ImageView iv_left;
	private ImageView iv_right;
	private TextView tv_title;
	private TextView tv_findPwdText;
	SharedPreferences  sp;
	SharedPreferences.Editor   spe;
	private Button btn_login;
	private Button btn_register;
	//主要控制我们手机屏幕的显示
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//加载对应布局
		setContentView(R.layout.activity_main);
		initView();
		setListener();
		name.setText("");
		pwd.setText("");
		sp=getSharedPreferences("note", MODE_PRIVATE);
		spe=sp.edit();
		//spe.putString("username", username);
		//spe.commit();
		String spname=sp.getString("username", "0");
		if(!spname.equals("0")){
			name.setText(spname);
		}
		iv_left.setVisibility(View.INVISIBLE);
		iv_right.setVisibility(View.INVISIBLE);
		if(is_debug=false){
			name.setText("");
			pwd.setText("");
		}
		//tv_title.setText("UserManager");
	}
	private void setListener() {
		btn_login.setOnClickListener(this);
		btn_register.setOnClickListener(this);
		tv_findPwdText.setOnClickListener(this);
	}
	private void initView() {
		name=(EditText)findViewById(R.id.editText1);
		pwd=(EditText) findViewById(R.id.editText2);
		iv_left=(ImageView) findViewById(R.id.imageView1);
		iv_right=(ImageView) findViewById(R.id.imageView2);
		tv_title=(TextView) findViewById(R.id.title);
		tv_findPwdText=(TextView) findViewById(R.id.tv_find_pwd);
		btn_login=(Button)findViewById(R.id.button1);
		btn_register=(Button)findViewById(R.id.button2);
	}
	//创建菜单
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void onClick(View v) {
			switch (v.getId()) {
			case R.id.button1:
				username=name.getText().toString();
				password=pwd.getText().toString();
				if(username.equals("admin")&&password.equals("123456")){
					Intent intent=new Intent(
							LoginActivity.this,
							WelcomeActivity.class);
					intent.putExtra("name", username);
					//启动意图
					startActivity(intent);
					spe.putString("username", username);
					spe.commit();
				}else{
					Toast.makeText(
							getApplicationContext(),
							"用户名或者密码输入错误", 1).show();	
				}
				break;
			case R.id.button2:
				Toast.makeText(getApplicationContext(), "注册按钮被点击了", 0).show();
				break;
			case R.id.tv_find_pwd:
				Toast.makeText(LoginActivity.this, "忘记密码TextVIew被点击", 0).show();
				break;
			}
	}

}
