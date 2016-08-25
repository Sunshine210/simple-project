package com.example.usermanager.biz;
//Log   
//¼¯ºÏ
import com.example.usermanager.dao.UserManagerDao;
import com.example.usermanager.entity.User;

public class UserManagerBiz {
		public User[] getUsers(User[] users){
			UserManagerDao umd=new UserManagerDao();
			return umd.getData(users);
		}
}
