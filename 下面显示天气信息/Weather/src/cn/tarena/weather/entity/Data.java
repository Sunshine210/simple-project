package cn.tarena.weather.entity;

import java.util.ArrayList;

public class Data {
	private String jingqu;
	private String date;
	private String ifForeign;
	private Realtime realtime;
	private Pm25 pm25;
	private ArrayList<FWeather> fweatherList;
	public String getJingqu() {
		return jingqu;
	}
	public void setJingqu(String jingqu) {
		this.jingqu = jingqu;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getIfForeign() {
		return ifForeign;
	}
	public void setIfForeign(String ifForeign) {
		this.ifForeign = ifForeign;
	}
	public Realtime getRealtime() {
		return realtime;
	}
	public void setRealtime(Realtime realtime) {
		this.realtime = realtime;
	}
	public ArrayList<FWeather> getFweatherList() {
		return fweatherList;
	}
	public void setFweatherList(ArrayList<FWeather> fweatherList) {
		this.fweatherList = fweatherList;
	}
	public Pm25 getPm25() {
		return pm25;
	}
	public void setPm25(Pm25 pm25) {
		this.pm25 = pm25;
	}
	
}
