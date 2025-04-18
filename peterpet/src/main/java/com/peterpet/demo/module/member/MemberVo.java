package com.peterpet.demo.module.member;

import com.peterpet.demo.module.base.BaseVo;

public class MemberVo extends BaseVo {

	private Integer shSecession;

	public Integer getShSecession() {
		return shSecession;
	}

	public void setShSecession(Integer shSecession) {
		this.shSecession = shSecession;
	}
	
	public String ranCertification() {
		String rt = "";
		for (int i = 0; i < 6; i++) {
			rt += (int)(Math.random() * 10);			
		}
		return rt;
	}
}
