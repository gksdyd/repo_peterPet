package com.peterpet.demo.module.util;

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
}
