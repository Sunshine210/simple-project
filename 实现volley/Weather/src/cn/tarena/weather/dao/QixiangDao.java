package cn.tarena.weather.dao;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import cn.tarena.weather.entity.City;
import cn.tarena.weather.entity.Head;
import cn.tarena.weather.entity.XResult;

import android.util.Xml;

public class QixiangDao {
	public void processCity() throws XmlPullParserException, IOException {
		String xmlString = "<?xml version=\"1\" encoding=\"UTF\"?>"
				+ " <result> " + "<head>" + "<cmd>2070</cmd>" + "<msg>成功</msg>"
				+ "<code>0</code>" + "</head>" + "<city>" + "<name>北京</name>"
				+ "<lat>116.41667</lat>" + "<lon>39.91667</lon>" + "</city>"
				+ "<city>" + "<name>南京</name>" + "<lat>118.78333</lat>"
				+ "<lon>32.05000</lon>" + "</city>" + "</result>";
		ArrayList<City> citylist = new ArrayList<City>();
		XResult xResult = null;
		Head head = null;
		XmlPullParser xpp = Xml.newPullParser();//		XmlPullParser
		xpp.setInput(in)
		int evenType = xpp.getEventType();
		while (evenType != XmlPullParser.END_DOCUMENT) {
			City city = null;
			switch (evenType) {
			case XmlPullParser.START_TAG:
				if (xpp.getName().equals("result")) {
					xResult = new XResult();

				} else if (xpp.getName().equals("head")) {
					head = new Head();
				} else if (xpp.getName().equals("cmd")) {
					xpp.next();
					head.setCmd(xpp.getText());
				} else if (xpp.getName().equals("msg")) {
					xpp.next();
					head.setMsg(xpp.getText());
				} else if (xpp.getName().equals("code")) {
					xpp.next();
					head.setCode(xpp.getText());
				} else if (xpp.getName().equals("city")) {
					city = new City();
				} else if (xpp.getName().equals("name")) {
					xpp.next();
					city.setName(xpp.getText());
				} else if (xpp.getName().equals("lon")) {
					xpp.next();
					city.setLon(xpp.getText());
				} else if (xpp.getName().equals("lat")) {
					xpp.next();
					city.setLat(xpp.getText());
				}

			case XmlPullParser.END_TAG:
				if (xpp.getName().equals("result")) {

				} else if (xpp.getName().equals("head")) {

				} else if (xpp.getName().equals("city")) {
					citylist.add(city);
				}
				break;
			}
			xpp.next();
		}
	}
	public InputStream getStringSteam(String xmlString){
		if(xmlString == null||xmlString.equals("")){
		ByteArrayInputStream bais =new ByteArrayInputStream(xmlString.getBytes());
		
	}
		return bais;
	}
}
