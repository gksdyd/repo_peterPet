package com.peterpet.demo.module.product;

public class ProductDto {

	private String codeName;
	private String codeSeq;
	
	private String cogrSeq;
	private String cogrName;
	private int count;
	
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getCodeSeq() {
		return codeSeq;
	}
	public void setCodeSeq(String codeSeq) {
		this.codeSeq = codeSeq;
	}
	public String getCogrName() {
		return cogrName;
	}
	public void setCogrName(String cogrName) {
		this.cogrName = cogrName;
	}
	public String getCogrSeq() {
		return cogrSeq;
	}
	public void setCogrSeq(String cogrSeq) {
		this.cogrSeq = cogrSeq;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
