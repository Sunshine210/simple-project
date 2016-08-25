package cn.tarena.weather.dao;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

import cn.tarena.weather.entity.City;
import cn.tarena.weather.entity.Head;
import cn.tarena.weather.entity.XResult;
import cn.tarena.weather.utils.L;


public class QixiangDao {
	public XResult processCity() throws Exception{
	String xmlString = ""+
"<result>"+
"<head>"+
"<cmd>2070</cmd>"+
"<msg>�ɹ�</msg>"+
"<code>0</code>"+
"</head>"+
"<city>"+
"<name>����</name>"+
"<lat>116.41667</lat>"+
"<lon>39.91667</lon>"+
"</city>"+
"<city>"+
"<name>�Ͼ�</name>"+
"<lat>118.78333</lat>"+
"<lon>32.05000</lon>"+
"</city>"+
"</result>";
	String xml="<?xml version=\"1.0\" encoding=\"utf-8\"?>";
	
	//xml
	//dom  pull  sax   dom4j
	//androidĬ�ϼ���pull��sax
	ArrayList<City>  citylist=new ArrayList<City>();
	XResult  xResult= null;
	Head   head = null;
	City     city= null;
	XmlPullParser   xpp=Xml.newPullParser();
	L.e(xmlString);
	InputStream  input=getStringSteam(xmlString);
	xpp.setInput(input, "UTF-8");
		int evenType= xpp.getEventType();
		while(evenType!=XmlPullParser.END_DOCUMENT){
			
 			switch (evenType) {
			case XmlPullParser.START_TAG:
				if(xpp.getName().equals("result")){
					xResult=new XResult();
				}else if(xpp.getName().equals("head")){
					head=new Head();
				}else if(xpp.getName().equals("cmd")){
					evenType=xpp.next();
					head.setCmd(xpp.getText());
				}else if(xpp.getName().equals("msg")){
					evenType=xpp.next();
					head.setMsg(xpp.getText());
				}else if(xpp.getName().equals("code")){
					evenType=xpp.next();
					head.setCode(xpp.getText());
				}else if(xpp.getName().equals("city")){
					city=new City();
				}else if(xpp.getName().equals("name")){
					evenType=xpp.next();
					city.setName(xpp.getText());
				}else if(xpp.getName().equals("lat")){
					evenType=xpp.next();
					city.setLat(xpp.getText());
				}else if(xpp.getName().equals("lon")){
					evenType=xpp.next();
					city.setLon(xpp.getText());
				}
				break;
			case XmlPullParser.END_TAG:
			    if(xpp.getName().equals("result")){
			    }else if(xpp.getName().equals("head")){
			    	System.out.println("dsadsa");
			    }else if(xpp.getName().equals("city")){
			    	citylist.add(city);
			    }
				break;
			}
			evenType=xpp.next();
		}
		xResult.setHead(head);
		xResult.setCitylist(citylist);
		return xResult;
	}	
	public InputStream  getStringSteam(String xmlString){
		if(xmlString!=null&&!xmlString.equals("")){
			ByteArrayInputStream bais=
					new ByteArrayInputStream(xmlString.getBytes());
			return bais;
		}
		return null;
		
	}
}
