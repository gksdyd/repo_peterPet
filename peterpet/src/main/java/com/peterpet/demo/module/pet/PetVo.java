package com.peterpet.demo.module.pet;

import com.peterpet.demo.module.base.BaseVo;

public class PetVo extends BaseVo {

	private String userSeq;
	private Integer shVaccineFlag;
	private Integer shNeuterFlag;
	private Integer shVarieties;
	private Integer shMinWeight = 0;
	private Integer shMaxWeight = 100;

	public String getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(String userSeq) {
		this.userSeq = userSeq;
	}

	public Integer getShVaccineFlag() {
		return shVaccineFlag;
	}

	public void setShVaccineFlag(Integer shVaccineFlag) {
		this.shVaccineFlag = shVaccineFlag;
	}

	public Integer getShNeuterFlag() {
		return shNeuterFlag;
	}

	public void setShNeuterFlag(Integer shNeuterFlag) {
		this.shNeuterFlag = shNeuterFlag;
	}

	public Integer getShVarieties() {
		return shVarieties;
	}

	public void setShVarieties(Integer shVarieties) {
		this.shVarieties = shVarieties;
	}

	public Integer getShMinWeight() {
		return shMinWeight;
	}

	public void setShMinWeight(Integer shMinWeight) {
		this.shMinWeight = shMinWeight;
	}

	public Integer getShMaxWeight() {
		return shMaxWeight;
	}

	public void setShMaxWeight(Integer shMaxWeight) {
		this.shMaxWeight = shMaxWeight;
	}
}
