package com.peterpet.demo.module.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.peterpet.demo.module.base.BaseVo;
import com.peterpet.demo.module.base.Constants;

public class UtilDateTime {

	public static void initTime(BaseVo vo) {		
		if (vo.getShDateStart() != null && vo.getShDateStart() != "") {
			LocalTime time = LocalTime.MIN;
			DateTimeFormatter format = DateTimeFormatter.ofPattern(Constants.TIME_FORMAT_HHMMSS);
			vo.setShDateStart(vo.getShDateStart() + " " + time.format(format));
		} else {
			vo.setShDateStart(null);
		}
		if (vo.getShDateEnd() != null && vo.getShDateEnd() != "") {
			LocalTime time = LocalTime.MAX;
			DateTimeFormatter format = DateTimeFormatter.ofPattern(Constants.TIME_FORMAT_HHMMSS);
			vo.setShDateEnd(vo.getShDateEnd() + " " + time.format(format));
		} else {
			vo.setShDateEnd(null);
		}
	}
}
