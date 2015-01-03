package com.daxia.wy.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmsService {
	/**
	 * 
	 * @param 短信通客户接口测试
	 * @param sendsmsaddress
	 * @return
	 * 
	 */
	public String commandID="3";
	public String username="ceshi";
	public String password="147";
    public String serviceURL = "http://124.173.70.59:8081/SmsAndMms/mt"; 
    public static String connectURL(String commString,String sendsmsaddress) {
		String rec_string = "";
		URL url = null;
		HttpURLConnection urlConn = null;
		try {
			url = new URL(sendsmsaddress);  //根据数据的发送地址构建URL
			urlConn = (HttpURLConnection) url.openConnection(); //打开链接
			urlConn.setConnectTimeout(30000); //链接超时设置为30秒
			urlConn.setReadTimeout(30000);	//读取超时设置30秒
			urlConn.setRequestMethod("POST");	//链接相应方式为post
			urlConn.setDoOutput(true);
			urlConn.setDoInput(true);
			
			OutputStream out = urlConn.getOutputStream();
			out.write(commString.getBytes("UTF-8"));
			out.flush();
			out.close();
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));
			StringBuffer sb = new StringBuffer();
			int ch;
			while ((ch = rd.read()) > -1) {
				sb.append((char) ch);
			}
			
			rec_string = sb.toString().trim();
			rec_string = URLDecoder.decode(rec_string, "UTF-8");
			rd.close();
		} catch (Exception e) {
			rec_string = "-107";
		} finally {
			if (urlConn != null) {
				urlConn.disconnect();
			}
		}

		return rec_string;
	}

public String sendSms(String mobile, String content) {
		String res = "";
		try {
			String commString ="Sn="+username+"&Pwd="+password+"&mobile=" + mobile + "&content="+content;
			res = connectURL(commString,serviceURL);
		} catch (Exception e) {
			return "-10000";
		}
		//设置返回值  解析返回值
		String resultok = "";
//			//正则表达式
			Pattern pattern = Pattern.compile("<int xmlns=\"http://tempuri.org/\">(.*)</int>");
			Matcher matcher = pattern.matcher(res);
			while (matcher.find()) {
				resultok = matcher.group(1);
			}
		return resultok;
	}
public static void main(String[] args) {
	String mobile="13010203040";
	String content="您本次操作的效验码是1234【商讯】";
	SmsService tt=new SmsService();
	String res=tt.sendSms(mobile,content);
	//设置返回值
	System.out.println(res);
}
	
}
