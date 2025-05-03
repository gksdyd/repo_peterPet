package com.peterpet.demo.module.base;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.peterpet.demo.module.util.UtilDateTime;

public class BaseController {

	public void initSearchTime(BaseVo vo) {		
		vo.setShDateStart(vo.getShDateStart() != null && vo.getShDateStart() != "" ? UtilDateTime.initMinTime(vo.getShDateStart()) : null);
		vo.setShDateEnd(vo.getShDateEnd() != null && vo.getShDateEnd() != "" ? UtilDateTime.initMaxTime(vo.getShDateEnd()) : null);
	}
	
	public String encodeBcrypt(String planeText, int strength) {
		  return new BCryptPasswordEncoder(strength).encode(planeText);
	}

			
	public boolean matchesBcrypt(String planeText, String hashValue, int strength) {
	  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(strength);
	  return passwordEncoder.matches(planeText, hashValue);
	}
	
	public void deliveryTimeCheck(BaseVo vo) throws Exception {
		boolean isHolliday = true;
		
		LocalDateTime startDateTime = LocalDateTime.now();
		LocalDateTime endDateTime = startDateTime;
		
		while (isHolliday) {
			if (UtilDateTime.openAPItest_v1(endDateTime.format(DateTimeFormatter.ofPattern("YYYYMMdd")))) {	// 공휴일
				endDateTime = endDateTime.plusDays(1);
			} else if (endDateTime.getDayOfWeek().getValue() > 5) {	// 주말
				endDateTime = endDateTime.plusDays(1);
			} else {	// 평일
				isHolliday = false;
			}
		}
		
		if (startDateTime == endDateTime) {	// 평일
			if (startDateTime.getHour() < 16) {
				endDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 0));
				vo.setDeliveryTime("오늘 오후 8시 ~ 밤 12시 사이");
			} else if (startDateTime.getHour() < 22) {
				if (startDateTime.getDayOfWeek().getValue() != 5) {
					endDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(22, 0));
					vo.setDeliveryTime("내일 새벽 7시 전");
				} else {
					endDateTime = LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(22, 0));
					vo.setDeliveryTime("내일 새벽 7시 전");
				}
			} else {
				if (startDateTime.getDayOfWeek().getValue() != 5) {
					endDateTime = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(16, 0));
					vo.setDeliveryTime("내일 오후 8시 ~ 밤 12시 사이");
				} else {
					endDateTime = LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(22, 0));
					vo.setDeliveryTime("월요일 새벽 7시 전");
				}
			}
		} else {
			long day = ChronoUnit.DAYS.between(startDateTime, endDateTime);
			if (day == 1) {
				vo.setDeliveryTime("내일 새벽 7시 전");
			} else if (day == 2) {
				vo.setDeliveryTime("모레 새벽 7시 전");
			} else {
				int dayOfWeek = endDateTime.getDayOfWeek().getValue();
				switch (dayOfWeek) {
					case 1:
						vo.setDeliveryTime("월요일 새벽 7시 전");
						break;
					case 2:
						vo.setDeliveryTime("화요일 새벽 7시 전");
						break;
					case 3:
						vo.setDeliveryTime("수요일 새벽 7시 전");
						break;
					case 4:
						vo.setDeliveryTime("목요일 새벽 7시 전");
						break;
					case 5:
						vo.setDeliveryTime("금요일 새벽 7시 전");
						break;
				}
			}
			endDateTime = LocalDateTime.of(endDateTime.minusDays(1).toLocalDate(), LocalTime.of(22, 0));
		}
		
		vo.setDiffDay(ChronoUnit.DAYS.between(startDateTime, endDateTime));
		vo.setDiffHour(ChronoUnit.HOURS.between(startDateTime, endDateTime) % 24);
		vo.setDiffMinute(ChronoUnit.MINUTES.between(startDateTime, endDateTime) % 60);
		vo.setDiffSecond(ChronoUnit.SECONDS.between(startDateTime, endDateTime) % 60);		
	}
}
