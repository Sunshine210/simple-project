package cn.tarena.weather.entity;

public class Realtime {
	private Weather weather;
	private Wind wind;
	private String city_code;
	private String city_name;
	private String  date;
	private String  time;
	private int week;
	private String moon;
	private String dataUptime;
	public String getCity_code() {
		return city_code;
	}
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public String getMoon() {
		return moon;
	}
	public void setMoon(String moon) {
		this.moon = moon;
	}
	public String getDataUptime() {
		return dataUptime;
	}
	public void setDataUptime(String dataUptime) {
		this.dataUptime = dataUptime;
	}
	public Weather getWeather() {
		return weather;
	}
	public void setWeather(Weather weather) {
		this.weather = weather;
	}
	@Override
	public String toString() {
		return "Realtime [weather=" + weather + ", city_code=" + city_code
				+ ", city_name=" + city_name + ", date=" + date + ", time="
				+ time + ", week=" + week + ", moon=" + moon + ", dataUptime="
				+ dataUptime + "]";
	}
	public Wind getWind() {
		return wind;
	}
	public void setWind(Wind wind) {
		this.wind = wind;
	}
}
