package com.peterpet.demo.module.member;

import com.peterpet.demo.module.base.BaseVo;

public class MemberVo extends BaseVo {

	private Integer shSecession;
	private String userSeq;
	private String reviSeq;

	public Integer getShSecession() {
		return shSecession;
	}

	public void setShSecession(Integer shSecession) {
		this.shSecession = shSecession;
	}
	
	public String getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(String userSeq) {
		this.userSeq = userSeq;
	}

	public String getReviSeq() {
		return reviSeq;
	}

	public void setReviSeq(String reviSeq) {
		this.reviSeq = reviSeq;
	}

	public String ranCertification() {
		String rt = "";
		for (int i = 0; i < 6; i++) {
			rt += (int)(Math.random() * 10);			
		}
		return rt;
	}
}
