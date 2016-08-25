package mooo.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.Date;

import mooo.bean.ChatMessage;
import mooo.bean.Result;
import mooo.bean.ChatMessage.Type;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
/*用户传入一个message，然后调用setParams拼接一个完整的URL+KEY+info，传回服务器，
 * 然后同过getInputStream返回的流，读操作转化为字符串返回
 * 
 * 完成消息实体的编写
*/
public class HttpUtils
{
	private static final String URL = "http://www.tuling123.com/openapi/api";
	private static final String API_KEY = "c3eaa6660cfc4ba09ae1ebb98ec6c49e";

	/**
	 * 发送一个消息，得到返回的消息
	 * @param msg
	 * @return
	 */
	public static ChatMessage sendMessage(String msg)
	{
		ChatMessage chatMessage = new ChatMessage();
		String jsonRes = doGet(msg);//服务器返回json字符串的结果
		Gson gson = new Gson();   //字符串转为对象，导入第三方jar--gson包
		Result result = null;
		try
		{
			result = gson.fromJson(jsonRes, Result.class);//使用result进行接收
			chatMessage.setMsg(result.getText());
		} catch (Exception e)
		{
			chatMessage.setMsg("服务器繁忙，稍后再试");
		}
		chatMessage.setDate(new Date());//返回时间
		chatMessage.setType(Type.INCOMING);//类型
		return chatMessage;
	}

	public static String doGet(String msg)
	{
		String result = "";
		String url = setParams(msg);
		ByteArrayOutputStream baos = null;
		InputStream is = null;
		try
		{
			java.net.URL urlNet = new java.net.URL(url);
			HttpURLConnection conn = (HttpURLConnection) urlNet
					.openConnection();
			conn.setReadTimeout(5 * 1000);
			conn.setConnectTimeout(5 * 1000);
			conn.setRequestMethod("GET");//请求方式
			is = conn.getInputStream();//拿到服务器返回的inputstream
			int len = -1;
			byte[] buf = new byte[128];
			baos = new ByteArrayOutputStream();//流转化为string

			while ((len = is.read(buf)) != -1)
			{
				baos.write(buf, 0, len);
			}
			baos.flush();//清除缓冲区
			result = new String(baos.toByteArray());
		} catch (MalformedURLException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally//释放所有的资源
		{
			try
			{
				if (baos != null)
					baos.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}

			try
			{
				if (is != null)
				{
					is.close();
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return result;
	}
//为URL返回消息
	private static String setParams(String msg)
	{
		String url = "";
		try
		{
			url = URL + "?key=" + API_KEY + "&info="
					+ URLEncoder.encode(msg, "UTF-8");
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return url;
	}

}
