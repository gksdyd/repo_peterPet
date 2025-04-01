package com.peterpet.demo.module.base;

import com.peterpet.demo.module.util.UtilDateTime;

public class BaseController {

	public void initSearchTime(BaseVo vo) {		
		vo.setShDateStart(vo.getShDateStart() != null && vo.getShDateStart() != "" ? UtilDateTime.initMinTime(vo.getShDateStart()) : null);
		vo.setShDateEnd(vo.getShDateEnd() != null && vo.getShDateEnd() != "" ? UtilDateTime.initMaxTime(vo.getShDateEnd()) : null);
	}
}
