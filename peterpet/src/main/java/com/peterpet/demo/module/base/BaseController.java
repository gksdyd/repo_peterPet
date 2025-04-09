package com.peterpet.demo.module.base;

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
}
