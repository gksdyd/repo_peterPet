package com.peterpet.demo.module.code;

import java.util.ArrayList;
import java.util.List;

public class CodeDto {

	private String codeSeq;
	private String codeName;
	private String codeNameEng;
	private String codeNum;
	private Integer codeUseFlag;
	private Integer codeOrder;
	private String codeDisc;
	private String codeRegDate;
	private String codeModDate;
	private Integer codeDelFlag;
	private String codeGroup_cogrSeq;
	
	private String cogrSeq;
	private Integer cogrNum;
	private String cogrName;
	
	public String getCodeSeq() {
		return codeSeq;
	}
	public void setCodeSeq(String codeSeq) {
		this.codeSeq = codeSeq;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getCodeNameEng() {
		return codeNameEng;
	}
	public void setCodeNameEng(String codeNameEng) {
		this.codeNameEng = codeNameEng;
	}
	public String getCodeNum() {
		return codeNum;
	}
	public void setCodeNum(String codeNum) {
		this.codeNum = codeNum;
	}
	public Integer getCodeUseFlag() {
		return codeUseFlag;
	}
	public void setCodeUseFlag(Integer codeUseFlag) {
		this.codeUseFlag = codeUseFlag;
	}
	public Integer getCodeOrder() {
		return codeOrder;
	}
	public void setCodeOrder(Integer codeOrder) {
		this.codeOrder = codeOrder;
	}
	public String getCodeDisc() {
		return codeDisc;
	}
	public void setCodeDisc(String codeDisc) {
		this.codeDisc = codeDisc;
	}
	public String getCodeRegDate() {
		return codeRegDate;
	}
	public void setCodeRegDate(String codeRegDate) {
		this.codeRegDate = codeRegDate;
	}
	public String getCodeModDate() {
		return codeModDate;
	}
	public void setCodeModDate(String codeModDate) {
		this.codeModDate = codeModDate;
	}
	public Integer getCodeDelFlag() {
		return codeDelFlag;
	}
	public void setCodeDelFlag(Integer codeDelFlag) {
		this.codeDelFlag = codeDelFlag;
	}
	public String getCodeGroup_cogrSeq() {
		return codeGroup_cogrSeq;
	}
	public void setCodeGroup_cogrSeq(String codeGroup_cogrSeq) {
		this.codeGroup_cogrSeq = codeGroup_cogrSeq;
	}
	public Integer getCogrNum() {
		return cogrNum;
	}
	public void setCogrNum(Integer cogrNum) {
		this.cogrNum = cogrNum;
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
	
	//	for cache
	public static List<CodeDto> cachedCodeArrayList = new ArrayList<CodeDto>();
}
