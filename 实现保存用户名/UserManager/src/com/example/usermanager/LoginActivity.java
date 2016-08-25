package com.example.usermanager;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	public static boolean is_debug = false;

	String username;
	String password;
	EditText name;
	EditText pwd;
	private ImageView iv_left;
	private ImageView iv_right;
	private TextView tv_title;
	SharedPreferences sp;
	SharedPreferences.Editor spe;

	// ��Ҫ���������ֻ���Ļ����ʾ
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���ض�Ӧ����
		setContentView(R.layout.activity_main);
		initView();
		name.setText("");
		pwd.setText("");
		sp = getSharedPreferences("node", MODE_PRIVATE);// �����û���Ϣ
		spe = sp.edit();
		String spname = sp.getString("username", "0");
		if (!spname.equals("0")) {
			name.setText(spname);// ���ı���ֵ
		}

		iv_left.setVisibility(View.INVISIBLE);
		iv_right.setVisibility(View.INVISIBLE);
		if (is_debug = false) {
			name.setText("");
			pwd.setText("");
		}
		// tv_title.setText("UserManager");
	}

	private void initView() {
		name = (EditText) findViewById(R.id.editText1);
		pwd = (EditText) findViewById(R.id.editText2);
		iv_left = (ImageView) findViewById(R.id.imageView1);
		iv_right = (ImageView) findViewById(R.id.imageView2);
		tv_title = (TextView) findViewById(R.id.title);
	}

	// ctrl+shift+o
	public void doClick(View view) {
		username = name.getText().toString();
		password = pwd.getText().toString();
		if (username.equals("admin") && password.equals("123456")) {
			Intent intent = new Intent(LoginActivity.this,
					WelcomeActivity.class);
			intent.putExtra("name", username);
			// ������ͼ
			startActivity(intent);
			spe.putString("username", username);
			spe.commit(); //�ύ���ļ��ﱣ��
		} else {
			Toast.makeText(getApplicationContext(), "�û������������������", 1).show();
		}
	}

	// �����˵�
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
