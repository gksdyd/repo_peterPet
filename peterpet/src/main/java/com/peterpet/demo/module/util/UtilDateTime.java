package com.peterpet.demo.module.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.peterpet.demo.module.base.Constants;

public class UtilDateTime {

	public static String initMinTime(String date) {		
		LocalTime time = LocalTime.MIN;
		DateTimeFormatter format = DateTimeFormatter.ofPattern(Constants.TIME_FORMAT_HHMMSS);
		return date + " " + time.format(format);
	}
	
	public static String initMaxTime(String date) {
		LocalTime time = LocalTime.MAX;
		DateTimeFormatter format = DateTimeFormatter.ofPattern(Constants.TIME_FORMAT_HHMMSS);
		return date + " " + time.format(format);
	}
	
	public static boolean openAPItest_v1(String date) throws Exception {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + "rcfaGbmRiUmN5q3Ab5%2BWHWF4mCklDyTg3cx%2Fg%2FlUZEF%2Fac9KHuoq%2BWUVRWJY2%2BtXwgY1IPMQZPcjfBgIf5IWOg%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*타입*/
        urlBuilder.append("&" + URLEncoder.encode("solYear","UTF-8") + "=" + URLEncoder.encode("2025", "UTF-8")); /*연*/
//        urlBuilder.append("&" + URLEncoder.encode("solMonth","UTF-8") + "=" + URLEncoder.encode("09", "UTF-8")); /*월*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("365", "UTF-8")); /*최대로 출력할 공휴일 수*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
//        System.out.println("Response code: " + conn.getResponseCode());
        
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
//        System.out.println(sb.toString());
//        System.out.println(sb.toString().contains("20250101"));
        return sb.toString().contains(date);
	}
	
	public static String nowString() {
		LocalDate date = LocalDate.now();
		return date.toString();
	}
}
