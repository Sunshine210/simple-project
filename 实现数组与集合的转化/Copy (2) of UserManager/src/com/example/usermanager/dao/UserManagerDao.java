package com.example.usermanager.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.usermanager.R;
import com.example.usermanager.entity.USERS;
import com.example.usermanager.entity.User;

public class UserManagerDao {
	private List<USERS> User1 = new ArrayList<USERS>();
	int usericon[] = new int[] { R.drawable.icon1, R.drawable.icon2,
			R.drawable.icon3, R.drawable.icon4, R.drawable.icon5,
			R.drawable.icon6 };

	// 这里数组中保存的是一个流，流再转化为图片
	public User[] getData(User[] users) {

		for (int j = 0; j < 1000; j++) {
			String id = j + "";
			String username = "user" + j;
			String password = "123456";
			USERS user1 = new USERS(id, username, password);
			User1.add(user1);
			users[j] = new User(User1. get(j).getId(),User1.get(j).getUsername(),User1.get(j).getPassword());
		if (j< 6) {
			users[j].setUser_icon(usericon[j]);
		} else {
			users[j].setUser_icon(R.drawable.ic_launcher);
		}
		}
		return users;
	}
}
