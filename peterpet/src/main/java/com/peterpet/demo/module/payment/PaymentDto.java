package com.peterpet.demo.module.payment;

public class PaymentDto {

	private String paySeq;
	private String paymentKey;
	private String orderId;
	private Integer amount;
	private String orderName;
	private String easyPay;
	private Integer vat;
	private String deliveryNum;
	private String deliveryCode;
	private Integer quantity;
	private Integer payStatus;
	private String userSeq;
	private String requestedAt;
	private String approvedAt;
	private String payRegDate;
	private String payModDate;
	
	public String getPaySeq() {
		return paySeq;
	}
	public void setPaySeq(String paySeq) {
		this.paySeq = paySeq;
	}
	public String getPaymentKey() {
		return paymentKey;
	}
	public void setPaymentKey(String paymentKey) {
		this.paymentKey = paymentKey;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public String getEasyPay() {
		return easyPay;
	}
	public void setEasyPay(String easyPay) {
		this.easyPay = easyPay;
	}
	public Integer getVat() {
		return vat;
	}
	public void setVat(Integer vat) {
		this.vat = vat;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(String userSeq) {
		this.userSeq = userSeq;
	}
	public String getRequestedAt() {
		return requestedAt;
	}
	public void setRequestedAt(String requestedAt) {
		this.requestedAt = requestedAt;
	}
	public String getApprovedAt() {
		return approvedAt;
	}
	public void setApprovedAt(String approvedAt) {
		this.approvedAt = approvedAt;
	}
	public Integer getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	public String getPayRegDate() {
		return payRegDate;
	}
	public void setPayRegDate(String payRegDate) {
		this.payRegDate = payRegDate;
	}
	public String getPayModDate() {
		return payModDate;
	}
	public void setPayModDate(String payModDate) {
		this.payModDate = payModDate;
	}
	public String getDeliveryNum() {
		return deliveryNum;
	}
	public void setDeliveryNum(String deliveryNum) {
		this.deliveryNum = deliveryNum;
	}
	public String getDeliveryCode() {
		return deliveryCode;
	}
	public void setDeliveryCode(String deliveryCode) {
		this.deliveryCode = deliveryCode;
	}
}
