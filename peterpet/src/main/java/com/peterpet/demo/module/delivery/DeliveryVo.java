package com.peterpet.demo.module.delivery;

import com.peterpet.demo.module.base.BaseVo;

public class DeliveryVo extends BaseVo {

	private String userSeq;
	
	private String deliSeq;

	public String getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(String userSeq) {
		this.userSeq = userSeq;
	}

	public String getDeliSeq() {
		return deliSeq;
	}

	public void setDeliSeq(String deliSeq) {
		this.deliSeq = deliSeq;
	}
}
