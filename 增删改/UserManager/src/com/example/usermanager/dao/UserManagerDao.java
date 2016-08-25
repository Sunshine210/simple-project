package com.example.usermanager.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.usermanager.R;
import com.example.usermanager.entity.User;

public class UserManagerDao {
	int usericon[] = new int[] { R.drawable.icon1, R.drawable.icon2,
			R.drawable.icon3, R.drawable.icon4, R.drawable.icon5,
			R.drawable.icon6 };

	public User[] getData(User[] users) {
		for (int i = 0; i < users.length; i++) {
			String id = UUID.randomUUID() + "";
			String username = "user" + i;
			String password = "123456";
			users[i] = new User(id, username, password);
			if (i < 6) {
				users[i].setUser_icon(usericon[i]);
			} else {
				users[i].setUser_icon(R.drawable.ic_launcher);
			}
		}
		return users;
	}

	public List<User> getUserList() {
		ArrayList<User> alist = new ArrayList<User>();
		for (int i = 0; i < 2; i++) {
			String id = UUID.randomUUID() + "";
			String username = "user" + i;
			String password = "123456";
			User user = new User(id, username, password);
			
			if (i < 6) {
				user.setUser_icon(usericon[i]);
			} else {
				user.setUser_icon(R.drawable.icon5);
			}
			alist.add(user);
		}
		return alist;
	}
}
