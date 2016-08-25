package cn.tarena.weather.dao;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.tarena.weather.entity.Data;
import cn.tarena.weather.entity.FWeather;
import cn.tarena.weather.entity.Info;
import cn.tarena.weather.entity.Pm25;
import cn.tarena.weather.entity.Realtime;
import cn.tarena.weather.entity.Res;
import cn.tarena.weather.entity.Result;
import cn.tarena.weather.entity.Weather;
import cn.tarena.weather.entity.Wind;

public class WeatherDataDao {

	public  Res processRes(String json1) throws JSONException {
		String json="{\"reason\":\"successed!\",\"result\":{\"data\":{\"realtime\":{\"city_code\":\"101190101\",\"city_name\":\"�Ͼ�\",\"date\":\"2016-08-23\",\"time\":\"07:00:00\",\"week\":2,\"moon\":\"����إһ\",\"dataUptime\":1471907823,\"weather\":{\"temperature\":\"27\",\"humidity\":\"88\",\"info\":\"����\",\"img\":\"1\"},\"wind\":{\"direct\":\"����\",\"power\":\"2��\",\"offset\":null,\"windspeed\":null}},\"life\":{\"date\":\"2016-8-23\",\"info\":{\"chuanyi\":[\"����\",\"�������ȣ������Ŷ�������ȹ���̿㡢����T�����������ļ���װ��\"],\"ganmao\":[\"�ٷ�\",\"���������������ˣ�������ð���ʽϵ͡�������ⳤ�ڴ��ڿյ������У��Է���ð��\"],\"kongtiao\":[\"����ʱ�俪��\",\"�����е�Щ���ȣ����������ʵ���ʱ��������յ��������¶ȣ��������\"],\"wuran\":[\"��\",\"�������������ڿ�����Ⱦ��ϡ�͡���ɢ����������������������\"],\"xiche\":[\"������\",\"������ϴ����δ��һ�����꣬������С����ϴһ�µ����������ܱ���һ�졣\"],\"yundong\":[\"������\",\"�����Ϻã������ڷ����ϴ��Ƽ��������ڽ��е�ǿ���˶������ڻ����˶���ע��ܷ硣\"],\"ziwaixian\":[\"�е�\",\"���е�ǿ�������߷������������ʱ����Ϳ��SPF����15��PA+�ķ�ɹ����Ʒ����ñ�ӡ�̫������\"]}},\"weather\":[{\"date\":\"2016-08-23\",\"info\":{\"day\":[\"1\",\"����\",\"34\",\"����\",\"3-4 ��\",\"05:34\"],\"night\":[\"1\",\"����\",\"24\",\"����\",\"΢��\",\"18:40\"]},\"week\":\"��\",\"nongli\":\"����إһ\"},{\"date\":\"2016-08-24\",\"info\":{\"dawn\":[\"1\",\"����\",\"24\",\"����\",\"΢��\",\"18:40\"],\"day\":[\"1\",\"����\",\"34\",\"����\",\"3-4 ��\",\"05:35\"],\"night\":[\"1\",\"����\",\"25\",\"����\",\"3-4 ��\",\"18:39\"]},\"week\":\"��\",\"nongli\":\"����إ��\"},{\"date\":\"2016-08-25\",\"info\":{\"dawn\":[\"1\",\"����\",\"25\",\"����\",\"3-4 ��\",\"18:39\"],\"day\":[\"1\",\"����\",\"34\",\"����\",\"΢��\",\"05:36\"],\"night\":[\"1\",\"����\",\"26\",\"������\",\"3-4 ��\",\"18:37\"]},\"week\":\"��\",\"nongli\":\"����إ��\"},{\"date\":\"2016-08-26\",\"info\":{\"dawn\":[\"1\",\"����\",\"26\",\"������\",\"3-4 ��\",\"18:37\"],\"day\":[\"2\",\"��\",\"31\",\"������\",\"4-5 ��\",\"05:36\"],\"night\":[\"1\",\"����\",\"24\",\"����\",\"3-4 ��\",\"18:36\"]},\"week\":\"��\",\"nongli\":\"����إ��\"},{\"date\":\"2016-08-27\",\"info\":{\"dawn\":[\"1\",\"����\",\"24\",\"����\",\"3-4 ��\",\"18:36\"],\"day\":[\"1\",\"����\",\"30\",\"������\",\"3-4 ��\",\"05:37\"],\"night\":[\"1\",\"����\",\"20\",\"����\",\"3-4 ��\",\"18:35\"]},\"week\":\"��\",\"nongli\":\"����إ��\"}],\"pm25\":{\"key\":\"Nanjing\",\"show_desc\":0,\"pm25\":{\"curPm\":\"48\",\"pm25\":\"26\",\"pm10\":\"47\",\"level\":1,\"quality\":\"��\",\"des\":\"���������\"},\"dateTime\":\"2016��08��23��06ʱ\",\"cityName\":\"�Ͼ�\"},\"jingqu\":\"\",\"date\":\"\",\"isForeign\":\"0\"}},\"error_code\":0}";
		//����res
		JSONObject jobj_res=new JSONObject(json);//**
		Res res=new Res();
		res.setReason(jobj_res.getString("reason"));
		res.setError_code(jobj_res.getInt("error_code"));
		//����result
		JSONObject jobj_result=jobj_res.getJSONObject("result");//**
		Result result=new Result();
		res.setResult(result);
		//����Data
		JSONObject jobj_data=jobj_result.getJSONObject("data");//**
		Data  data=new Data();	
		result.setData(data);
		//����Realtime
		JSONObject jobj_realtime=jobj_data.getJSONObject("realtime");//**
		Realtime realtime=new Realtime();
		realtime.setCity_code(jobj_realtime.getString("city_code"));
		realtime.setCity_name(jobj_realtime.getString("city_name"));
		realtime.setDate(jobj_realtime.getString("date"));
		realtime.setTime(jobj_realtime.getString("time"));
		realtime.setWeek(jobj_realtime.getInt("week"));
		realtime.setMoon(jobj_realtime.getString("moon"));
		realtime.setDataUptime(jobj_realtime.getString("dataUptime"));
		//����weather
		JSONObject jobj_weather=jobj_realtime.getJSONObject("weather");
		Weather weather=new Weather();
		weather.setHumidity(jobj_weather.getString("humidity"));
		weather.setImg(jobj_weather.getString("img"));
		weather.setInfo(jobj_weather.getString("info"));
		weather.setTemperature(jobj_weather.getString("temperature"));
		realtime.setWeather(weather);
		//����wind
		JSONObject jobj_wind=jobj_realtime.getJSONObject("wind");
		Wind wind=new Wind();
		wind.setDirect(jobj_wind.getString("direct"));
		wind.setPower(jobj_wind.getString("power"));
		realtime.setWind(wind);
		//����FWeatherList
		JSONArray ja_fweather= jobj_data.getJSONArray("weather");
		ArrayList<FWeather>  fweatherlist=new ArrayList<FWeather>();
		for(int i=0;i<ja_fweather.length();i++){
			JSONObject jobj_fweather=ja_fweather.getJSONObject(i);
			FWeather fweather=new FWeather();
			fweather.setDate(jobj_fweather.getString("date"));
			fweather.setWeek(jobj_fweather.getString("week"));
			fweather.setNongli(jobj_fweather.getString("nongli"));
			//����δ��������������״��
			JSONObject jobj_info=jobj_fweather.getJSONObject("info");
			Info  info=new Info();
			JSONArray  ja_day=jobj_info.getJSONArray("day");
			JSONArray  ja_night=jobj_info.getJSONArray("night");
			String day[]=new String [6];
			String night[]=new String [6];
			for(int j=0;j<6;j++){
				day[j]=ja_day.getString(j);
				night[j]=ja_night.getString(j);
			}
			info.setDay(day);
			info.setNight(night);
			fweather.setInfo(info);//�ѷ�װ�õ�info��ӵ�weather
			fweatherlist.add(fweather);	
			data.setFweatherList(fweatherlist);
		}
		
		JSONObject jobj_pm25=jobj_data.getJSONObject("pm25");
		Pm25  pm25=new Pm25();
		String quality=jobj_pm25.getJSONObject("pm25").getString("quality");
		pm25.setQuality(quality);
		data.setPm25(pm25);
		return res;
	}
}
