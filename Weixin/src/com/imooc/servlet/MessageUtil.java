package com.imooc.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.imooc.po.News;
import com.imooc.po.NewsMessage;
import com.imooc.po.TextMessage;
import com.thoughtworks.xstream.XStream;


public class MessageUtil {
	
	public static final String MESSAGE_NEWS="news";
	public static final String MESSAGE_TEXT="text";
	public static final String MESSAGE_IMAGE="image";
	public static final String MESSAGE_VOICE="voice";
	public static final String MESSAGE_video="video";
	public static final String MESSAGE_LINK="link";
	public static final String MESSAGE_LOCATION="location";
	public static final String MESSAGE_EVENT="event";
	public static final String MESSAGE_SUBSCRIBE="subscribe";
	public static final String MESSAGE_UNSUBSCRIBE="unsubscribe";
	public static final String MESSAGE_CLICK="CLICK";
	public static final String MESSAGE_VIEW="VIEW";
	private static List<News> articles;

	
	//xml转为map集合
	public static Map<String,String>xmlToMap(HttpServletRequest request)throws IOException,DocumentException {
		Map<String,String> map =new HashMap<String,String>();
		SAXReader reader=new SAXReader();
		
		InputStream ins=request.getInputStream();
		
		Document doc=reader.read(ins);
		
		Element root=doc.getRootElement();
		List<Element>list=root.elements();
		
		for(Element e:list){
			map.put(e.getName(), e.getText());
		}
		ins.close();
		return map;
	}
	
	//将文本对象转化为xml
	public static String textMessageToXml(TextMessage textMessage){
		XStream xstream=new XStream();
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);	
	}
	//拼接文本消息
	public static String initText(String toUserName,String fromUserName,String content){
	TextMessage text=new TextMessage();
	text.setFromUserName(toUserName);
	text.setToUserName(fromUserName);
	text.setMsgType(MessageUtil.MESSAGE_TEXT);
	text.setCreateTime(new Date().getTime());
	text.setContent(content);
	 return textMessageToXml(text);
	}
	
	
	
	
	//主菜单
	public static String menuText(){
		StringBuffer sb=new StringBuffer();
		sb.append("欢迎关注由小何开发的公众号\n");
		sb.append("1.小何很帅\n");
		sb.append("2.小何非常帅\n");
		sb.append("回复？调出此菜单 。");
		return sb.toString();
		
	}
	public static String firstMenu(){
		StringBuffer sb=new StringBuffer();
		sb.append("欢迎关注由小何开发的公众号\n");
		return sb.toString();
		
	}
	
	public static String secondMenu(){
		StringBuffer sb=new StringBuffer();
		sb.append("欢迎关注由小何开发的公众号~\n");
		return sb.toString();	
	}
	
	/**
	 * 图文消息转化为xml
	 * @param newsMessage
	 * @return
	 */
	public static String newsMessageToXml(NewsMessage newsMessage){
		XStream xstream=new XStream();
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new News().getClass());
		return xstream.toXML(newsMessage);	
	}
	
	
	/**
	 *  图文消息的组装   
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String initNewsMessage(String toUserName,String fromUserName){
		String message=null;
		List<News>newsList=new ArrayList<News>();
		NewsMessage newsMessage=new NewsMessage();
		
		News news=new News();
		news.setTitle("小何的帅气");
		news.setDescription("帅的惊动党中央，帅的惊动联合国");
		news.setPicUrl("http://mytest.tunnel.qydev.com/Weixin/image/xiaohe.jpg");
		news.setUrl("www.imooc.com");
		
		newsList.add(news);
		
		 //消息各项内容
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MESSAGE_NEWS);
		newsMessage.setArticles(newsList);
		newsMessage.setArticleCount(newsList.size());
		
		//转成xml
		message=newsMessageToXml(newsMessage);
		return message;
			
	}
	
	
	
	}


