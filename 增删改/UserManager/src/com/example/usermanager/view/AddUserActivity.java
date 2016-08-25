package com.example.usermanager.view;

import java.util.UUID;

import com.example.usermanager.R;
import com.example.usermanager.R.layout;
import com.example.usermanager.R.menu;
import com.example.usermanager.entity.User;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddUserActivity extends Activity implements OnClickListener {

	private EditText et_username;
	private EditText et_userpwd;
	private EditText et_usernname;
	private Button btn_ok;
	private Button btn_cancel;
	private TextView tv_userid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_user);
		initView();
		setListener();
		User user=(User) getIntent().getSerializableExtra("user");
		if(user!=null){   //����û���itemʵ���޸�
			tv_userid.setText(user.getId());
			tv_userid.setVisibility(View.VISIBLE);
			et_username.setText(user.getUsername());
			et_userpwd.setText(user.getPassword());
			btn_ok.setText("�޸�");
		}
		
	}

	private void setListener() {  //���������Ϣ��ȷ�Ϻ�ȡ��������
		btn_ok.setOnClickListener(this);
		btn_cancel.setOnClickListener(this);
	}

	private void initView() {  //��ʼ�����ఴť
			tv_userid=(TextView)findViewById(R.id.tv_user_id);
			et_username=(EditText)findViewById(R.id.et_user_name);
			et_userpwd=(EditText)findViewById(R.id.et_user_pwd);
			et_usernname=(EditText)findViewById(R.id.et_user_nickname);
			btn_ok=(Button)findViewById(R.id.btn_add);
			btn_cancel=(Button)findViewById(R.id.btn_cancel);
	}

	@Override
	public void onClick(View v) {
			switch (v.getId()) {  //��õ����ǰҳ�水ť��id,
			case R.id.btn_add:
				if(btn_ok.getText().equals("�޸�")){
					String username=et_username.getText().toString().trim();
					String password=et_userpwd.getText().toString();
					User user=new User(tv_userid.getText().toString(), username, password);
					user.setNickname(et_usernname.getText().toString());
					user.setUser_icon(R.drawable.icon1);
					Intent intent=new Intent();
					intent.putExtra("user", user);
					setResult(3, intent);
					AddUserActivity.this.finish();
					/*����Intent���󣬲����ֱ�Ϊ�����ģ�Ҫ��ת��Activity��
                    Intent intent = new Intent(MyIntent.this, SecondActivity.class);
                                        ��Ҫ���ݵ�ֵ���ӵ�Intent����
                    intent.putExtra("et1", et1Str);
                    intent.putExtra("et2", et2Str);
                                        ������Intent����ʵ����ת
                    startActivity(intent);*/
				}else{
				String id="add"+UUID.randomUUID();
				//trim() ��ʾȥ���ַ������˿ո�
				String username=et_username.getText().toString().trim();
				String password=et_userpwd.getText().toString();
				User user=new User(id, username, password);
				user.setNickname(et_usernname.getText().toString());
				user.setUser_icon(R.drawable.icon1);
				Intent intent=new Intent();
				intent.putExtra("user", user);
				setResult(2, intent);
				AddUserActivity.this.finish();
				}
				break;
			case R.id.btn_cancel:
				AddUserActivity.this.finish();
				break;
			}
	}


}
