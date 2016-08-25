package com.example.usermanager.biz;

//Log   
//¼¯ºÏ
import java.util.List;

import com.example.usermanager.dao.UserManagerDao;
import com.example.usermanager.entity.User;

public class UserManagerBiz {
	public UserManagerDao umd = new UserManagerDao();

	public User[] getUsers(User[] users) {

		return umd.getData(users);
	}

	public List<User> getUserList() {
		return umd.getUserList();
	}
}
