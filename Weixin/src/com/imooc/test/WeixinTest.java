package com.imooc.test;

import com.imooc.po.AccessToken;
import com.imooc.servlet.WeixinUtil;

public class WeixinTest{
    public static void main(String[] args) {
    	AccessToken token=WeixinUtil.getAccessToken();
    	System.out.println("Ʊ��:"+token.getToken());
    	System.out.println("��Чʱ��:"+token.getExpiresIn());

		 
	}

}