package com.peterpet.demo.module.product;

import java.util.ArrayList;
import java.util.List;

import com.peterpet.demo.module.base.BaseVo;

public class ProductVo extends BaseVo{

	private int prodType;
	private String feedFunction;	// 선택한 기능을 저장하기 위한 매개체
	private int registerFlag;	// 등록 진행 여부 플래그
	private int addOrRemoveFlag;	// 뱃지 추가 또는 제거 플래그

	public int getProdType() {
		return prodType;
	}

	public void setProdType(int prodType) {
		this.prodType = prodType;
	}
	
	public String getFeedFunction() {
		return feedFunction;
	}

	public void setFeedFunction(String feedFunction) {
		this.feedFunction = feedFunction;
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

	public static List<String> getFeedFuncArr() {
		return feedFuncArr;
	}

	public static void setFeedFuncArr(List<String> feedFuncArr) {
		ProductVo.feedFuncArr = feedFuncArr;
	}

	public static List<String> feedFuncArr = new ArrayList<>();
	
	public void InitProdType() {
		if (getProdType() == 0) {
			setProdType(1);
		}
	}
	
	public void feedFuncSelect() {
		// 등록 중이 아니라면 클리어
		if (getRegisterFlag() == 0) {
			feedFuncArr.clear();
		}
		
		// 기능 추가
		if (addOrRemoveFlag == 1) {
			feedFuncArr.add(getFeedFunction());
		// 기능 제거
		} else if (addOrRemoveFlag == -1) {
			for (int i = 0; i < feedFuncArr.size(); i++) {
				if (feedFuncArr.get(i).equals(getFeedFunction())) {
					feedFuncArr.remove(i);
					break;
				}
			}
		}
	}
}
