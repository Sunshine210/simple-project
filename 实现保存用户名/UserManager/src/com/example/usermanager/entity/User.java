package com.example.usermanager.entity;

public class User {
		private String id;
		private String nickname;
		private String username;
		private String password;
		private int user_icon;
		//
		private int age;
		private String hobby;
		
		public String getId() {
			return id;
		}
		public User(String id, String username, String password) {
			super();
			this.id = id;
			this.username = username;
			this.password = password;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getNickname() {
			return nickname;
		}
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public int getUser_icon() {
			return user_icon;
		}
		public void setUser_icon(int user_icon) {
			this.user_icon = user_icon;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getHobby() {
			return hobby;
		}
		public void setHobby(String hobby) {
			this.hobby = hobby;
		}
		@Override
		public String toString() {
			return "User [nickname=" + nickname + ", username=" + username
					+ "]";
		}
}
