package com.imooc.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.imooc.po.AccessToken;

import net.sf.json.JSONObject;

public class WeixinUtil {
	private static final String APPID="wx7a359e3aa82cef18";
	private static final String APPSECRET="4e21f8c9ea7bfd43e3fa2df365412997";
	
	
	private static final String ACCESS_TOLKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	private static final CharSequence ACCESS_TOKEN = null;
/**
 * Get请求
 * @param url
 * @return
 */
	public static JSONObject doGetStr(String url){
		DefaultHttpClient httpClient =new DefaultHttpClient();
		HttpGet httpGet=new HttpGet(url);
		JSONObject jsonObject=null;
		try {
			HttpResponse response=httpClient.execute(httpGet);
			HttpEntity entity= response.getEntity();//从消息体里面拉取结果
			if(entity !=null){
				String result=EntityUtils.toString(entity,"UTF-8");//http转成字符串类型
			   jsonObject=JSONObject.fromObject(result);
			}
		} catch (ClientProtocolException e) {	
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return jsonObject;
	}
	/**
	 * Post请求
	 * @param url
	 * @param outStr
	 * @return
	 */
	public static JSONObject doPostStr(String url,String outStr){
		DefaultHttpClient httpClient=new DefaultHttpClient();
		HttpPost httpPost=new HttpPost(url);
		JSONObject jsonObject=null;
		try{
			httpPost.setEntity(new StringEntity(outStr,"UTF-8"));
			HttpResponse response=httpClient.execute(httpPost); 
			String result=EntityUtils.toString(response.getEntity(),"UTF-8");
			jsonObject=JSONObject.fromObject(result);
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonObject;	
	}
	/**
	 * 获取access_token
	 * @return
	 */
	public static AccessToken getAccessToken(){
		AccessToken token=new AccessToken();
		String url=ACCESS_TOLKEN_URL.replace("APPID",APPID).replace("APPSECRET",APPSECRET);
		JSONObject jsonObject=doGetStr(url);
		if(jsonObject!=null){
			token.setToken(jsonObject.getString("access_token"));
			token.setExpiresIn(jsonObject.getInt("expires_in"));
		}
		return token;
	}
	
	
}







