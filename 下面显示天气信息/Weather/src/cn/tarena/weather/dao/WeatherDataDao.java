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
		String json="{\"reason\":\"successed!\",\"result\":{\"data\":{\"realtime\":{\"city_code\":\"101190101\",\"city_name\":\"南京\",\"date\":\"2016-08-23\",\"time\":\"07:00:00\",\"week\":2,\"moon\":\"七月廿一\",\"dataUptime\":1471907823,\"weather\":{\"temperature\":\"27\",\"humidity\":\"88\",\"info\":\"多云\",\"img\":\"1\"},\"wind\":{\"direct\":\"东风\",\"power\":\"2级\",\"offset\":null,\"windspeed\":null}},\"life\":{\"date\":\"2016-8-23\",\"info\":{\"chuanyi\":[\"炎热\",\"天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。\"],\"ganmao\":[\"少发\",\"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。\"],\"kongtiao\":[\"部分时间开启\",\"您将感到些燥热，建议您在适当的时候开启制冷空调来降低温度，以免中暑。\"],\"wuran\":[\"良\",\"气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。\"],\"xiche\":[\"较适宜\",\"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。\"],\"yundong\":[\"较适宜\",\"天气较好，但由于风力较大，推荐您在室内进行低强度运动，若在户外运动请注意避风。\"],\"ziwaixian\":[\"中等\",\"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。\"]}},\"weather\":[{\"date\":\"2016-08-23\",\"info\":{\"day\":[\"1\",\"多云\",\"34\",\"东风\",\"3-4 级\",\"05:34\"],\"night\":[\"1\",\"多云\",\"24\",\"东风\",\"微风\",\"18:40\"]},\"week\":\"二\",\"nongli\":\"七月廿一\"},{\"date\":\"2016-08-24\",\"info\":{\"dawn\":[\"1\",\"多云\",\"24\",\"东风\",\"微风\",\"18:40\"],\"day\":[\"1\",\"多云\",\"34\",\"东风\",\"3-4 级\",\"05:35\"],\"night\":[\"1\",\"多云\",\"25\",\"东风\",\"3-4 级\",\"18:39\"]},\"week\":\"三\",\"nongli\":\"七月廿二\"},{\"date\":\"2016-08-25\",\"info\":{\"dawn\":[\"1\",\"多云\",\"25\",\"东风\",\"3-4 级\",\"18:39\"],\"day\":[\"1\",\"多云\",\"34\",\"东风\",\"微风\",\"05:36\"],\"night\":[\"1\",\"多云\",\"26\",\"东北风\",\"3-4 级\",\"18:37\"]},\"week\":\"四\",\"nongli\":\"七月廿三\"},{\"date\":\"2016-08-26\",\"info\":{\"dawn\":[\"1\",\"多云\",\"26\",\"东北风\",\"3-4 级\",\"18:37\"],\"day\":[\"2\",\"阴\",\"31\",\"东北风\",\"4-5 级\",\"05:36\"],\"night\":[\"1\",\"多云\",\"24\",\"北风\",\"3-4 级\",\"18:36\"]},\"week\":\"五\",\"nongli\":\"七月廿四\"},{\"date\":\"2016-08-27\",\"info\":{\"dawn\":[\"1\",\"多云\",\"24\",\"北风\",\"3-4 级\",\"18:36\"],\"day\":[\"1\",\"多云\",\"30\",\"东北风\",\"3-4 级\",\"05:37\"],\"night\":[\"1\",\"多云\",\"20\",\"北风\",\"3-4 级\",\"18:35\"]},\"week\":\"六\",\"nongli\":\"七月廿五\"}],\"pm25\":{\"key\":\"Nanjing\",\"show_desc\":0,\"pm25\":{\"curPm\":\"48\",\"pm25\":\"26\",\"pm10\":\"47\",\"level\":1,\"quality\":\"优\",\"des\":\"可正常活动。\"},\"dateTime\":\"2016年08月23日06时\",\"cityName\":\"南京\"},\"jingqu\":\"\",\"date\":\"\",\"isForeign\":\"0\"}},\"error_code\":0}";
		//解析res
		JSONObject jobj_res=new JSONObject(json);//**
		Res res=new Res();
		res.setReason(jobj_res.getString("reason"));
		res.setError_code(jobj_res.getInt("error_code"));
		//解析result
		JSONObject jobj_result=jobj_res.getJSONObject("result");//**
		Result result=new Result();
		res.setResult(result);
		//解析Data
		JSONObject jobj_data=jobj_result.getJSONObject("data");//**
		Data  data=new Data();	
		result.setData(data);
		//解析Realtime
		JSONObject jobj_realtime=jobj_data.getJSONObject("realtime");//**
		Realtime realtime=new Realtime();
		realtime.setCity_code(jobj_realtime.getString("city_code"));
		realtime.setCity_name(jobj_realtime.getString("city_name"));
		realtime.setDate(jobj_realtime.getString("date"));
		realtime.setTime(jobj_realtime.getString("time"));
		realtime.setWeek(jobj_realtime.getInt("week"));
		realtime.setMoon(jobj_realtime.getString("moon"));
		realtime.setDataUptime(jobj_realtime.getString("dataUptime"));
		//解析weather
		JSONObject jobj_weather=jobj_realtime.getJSONObject("weather");
		Weather weather=new Weather();
		weather.setHumidity(jobj_weather.getString("humidity"));
		weather.setImg(jobj_weather.getString("img"));
		weather.setInfo(jobj_weather.getString("info"));
		weather.setTemperature(jobj_weather.getString("temperature"));
		realtime.setWeather(weather);
		//解析wind
		JSONObject jobj_wind=jobj_realtime.getJSONObject("wind");
		Wind wind=new Wind();
		wind.setDirect(jobj_wind.getString("direct"));
		wind.setPower(jobj_wind.getString("power"));
		realtime.setWind(wind);
		//解析FWeatherList
		JSONArray ja_fweather= jobj_data.getJSONArray("weather");
		ArrayList<FWeather>  fweatherlist=new ArrayList<FWeather>();
		for(int i=0;i<ja_fweather.length();i++){
			JSONObject jobj_fweather=ja_fweather.getJSONObject(i);
			FWeather fweather=new FWeather();
			fweather.setDate(jobj_fweather.getString("date"));
			fweather.setWeek(jobj_fweather.getString("week"));
			fweather.setNongli(jobj_fweather.getString("nongli"));
			//解析未来白天晚上天气状况
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
			fweather.setInfo(info);//把封装好的info添加到weather
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
