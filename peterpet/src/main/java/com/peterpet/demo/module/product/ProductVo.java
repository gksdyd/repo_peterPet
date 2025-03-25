package com.peterpet.demo.module.product;

import java.util.ArrayList;
import java.util.List;

import com.peterpet.demo.module.base.BaseVo;

public class ProductVo extends BaseVo{

	private String prodSeq;
	private int prodType;
	
	private int registerFlag;	// 등록 진행 여부 플래그
	private int addOrRemoveFlag;	// 뱃지 추가 또는 제거 플래그

	public static List<FeedInfoVo> feedInfoArr = new ArrayList<>();
	
	public String getProdSeq() {
		return prodSeq;
	}

	public void setProdSeq(String prodSeq) {
		this.prodSeq = prodSeq;
	}

	public int getProdType() {
		return prodType;
	}

	public void setProdType(int prodType) {
		this.prodType = prodType;
	}
	
	public int getRegisterFlag() {
		return registerFlag;
	}

	public void setRegisterFlag(int registerFlag) {
		this.registerFlag = registerFlag;
	}

	public int getAddOrRemoveFlag() {
		return addOrRemoveFlag;
	}

	public void setAddOrRemoveFlag(int addOrRemoveFlag) {
		this.addOrRemoveFlag = addOrRemoveFlag;
	}
	
	public static List<FeedInfoVo> getFeedInfoArr() {
		return feedInfoArr;
	}

	public static void setFeedInfoArr(List<FeedInfoVo> feedInfoArr) {
		FeedInfoVo.feedInfoArr = feedInfoArr;
	}

	public void InitProdType() {
		if (getProdType() == 0) {
			setProdType(1);
		}
	}
}
