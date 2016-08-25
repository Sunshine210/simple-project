package cn.tarena.weather.entity;

import java.util.ArrayList;

public class XResult {
		private Head  head;
		private ArrayList<City>  citylist;
		public Head getHead() {
			return head;
		}
		public void setHead(Head head) {
			this.head = head;
		}
		public ArrayList<City> getCitylist() {
			return citylist;
		}
		public void setCitylist(ArrayList<City> citylist) {
			this.citylist = citylist;
		}
}
