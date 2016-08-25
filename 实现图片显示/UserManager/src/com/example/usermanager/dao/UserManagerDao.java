package com.example.usermanager.dao;

import java.util.UUID;

import com.example.usermanager.R;
import com.example.usermanager.entity.User;

public class UserManagerDao {
	int   usericon[]=new int[]{R.drawable.icon1,R.drawable.icon2,R.drawable.icon3,R.drawable.icon4,R.drawable.icon5,R.drawable.icon6};
	
		public   User[]  getData(User[] users){
			for(int i=0;i<users.length;i++){
				String id=UUID.randomUUID()+"";
				String username="user"+i;
				String password="123456";
				users[i]=new User(id, username, password);
				if(i<6){
					users[i].setUser_icon(usericon[i]);
				}else{
					users[i].setUser_icon(R.drawable.ic_launcher);
				}
			}
			return users;
		}
}
