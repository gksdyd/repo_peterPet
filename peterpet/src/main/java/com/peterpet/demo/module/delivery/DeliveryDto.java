package com.peterpet.demo.module.delivery;

import com.peterpet.demo.module.base.BaseDto;

public class DeliveryDto extends BaseDto {

	private String deliSeq;
	private String deliRoadAddr;
	private String deliDetailAddr;
	private Double deliLatitude;
	private Double deliLongtitude;
	private String deliRecvName;
	private String deliRecvPhone;
	private String deliText;
	private String deliRegDate;
	private String deliModDate;
	private Integer deliDelFlag;
	private Integer deliMain;
	
	public String getDeliSeq() {
		return deliSeq;
	}
	public void setDeliSeq(String deliSeq) {
		this.deliSeq = deliSeq;
	}
	public String getDeliRoadAddr() {
		return deliRoadAddr;
	}
	public void setDeliRoadAddr(String deliRoadAddr) {
		this.deliRoadAddr = deliRoadAddr;
	}
	public String getDeliDetailAddr() {
		return deliDetailAddr;
	}
	public void setDeliDetailAddr(String deliDetailAddr) {
		this.deliDetailAddr = deliDetailAddr;
	}
	public Double getDeliLatitude() {
		return deliLatitude;
	}
	public void setDeliLatitude(Double deliLatitude) {
		this.deliLatitude = deliLatitude;
	}
	public Double getDeliLongtitude() {
		return deliLongtitude;
	}
	public void setDeliLongtitude(Double deliLongtitude) {
		this.deliLongtitude = deliLongtitude;
	}
	public String getDeliRecvName() {
		return deliRecvName;
	}
	public void setDeliRecvName(String deliRecvName) {
		this.deliRecvName = deliRecvName;
	}
	public String getDeliRecvPhone() {
		return deliRecvPhone;
	}
	public void setDeliRecvPhone(String deliRecvPhone) {
		this.deliRecvPhone = deliRecvPhone;
	}
	public String getDeliText() {
		return deliText;
	}
	public void setDeliText(String deliText) {
		this.deliText = deliText;
	}
	public String getDeliRegDate() {
		return deliRegDate;
	}
	public void setDeliRegDate(String deliRegDate) {
		this.deliRegDate = deliRegDate;
	}
	public String getDeliModDate() {
		return deliModDate;
	}
	public void setDeliModDate(String deliModDate) {
		this.deliModDate = deliModDate;
	}
	public Integer getDeliDelFlag() {
		return deliDelFlag;
	}
	public void setDeliDelFlag(Integer deliDelFlag) {
		this.deliDelFlag = deliDelFlag;
	}
	public Integer getDeliMain() {
		return deliMain;
	}
	public void setDeliMain(Integer deliMain) {
		this.deliMain = deliMain;
	}
}
