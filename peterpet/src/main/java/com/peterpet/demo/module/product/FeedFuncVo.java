package com.peterpet.demo.module.product;

import java.util.ArrayList;
import java.util.List;

public class FeedFuncVo extends ProductVo {
	
	private String feedFunction;	// 선택한 기능을 저장하기 위한 매개체
	
	private Integer shFeedSalaryAge;
	private Integer shFeedType;
	private Integer shFeedEtc;
	private Integer shFeedSize;
	private Integer shFeedBrand;
	private Integer shFeedIngredient;
	
	public static List<String> feedFuncArr = new ArrayList<>();

	public String getFeedFunction() {
		return feedFunction;
	}
	public void setFeedFunction(String feedFunction) {
		this.feedFunction = feedFunction;
	}
	public static List<String> getFeedFuncArr() {
		return feedFuncArr;
	}
	public static void setFeedFuncArr(List<String> feedFuncArr) {
		FeedFuncVo.feedFuncArr = feedFuncArr;
	}	
	public Integer getShFeedSalaryAge() {
		return shFeedSalaryAge;
	}
	public void setShFeedSalaryAge(Integer shFeedSalaryAge) {
		this.shFeedSalaryAge = shFeedSalaryAge;
	}
	
	public Integer getShFeedType() {
		return shFeedType;
	}
	public void setShFeedType(Integer shFeedType) {
		this.shFeedType = shFeedType;
	}
	public Integer getShFeedEtc() {
		return shFeedEtc;
	}
	public void setShFeedEtc(Integer shFeedEtc) {
		this.shFeedEtc = shFeedEtc;
	}
	public Integer getShFeedSize() {
		return shFeedSize;
	}
	public void setShFeedSize(Integer shFeedSize) {
		this.shFeedSize = shFeedSize;
	}
	public Integer getShFeedBrand() {
		return shFeedBrand;
	}
	public void setShFeedBrand(Integer shFeedBrand) {
		this.shFeedBrand = shFeedBrand;
	}
	public Integer getShFeedIngredient() {
		return shFeedIngredient;
	}
	public void setShFeedIngredient(Integer shFeedIngredient) {
		this.shFeedIngredient = shFeedIngredient;
	}
	public void feedFuncSelect() {
		// 등록 중이 아니라면 클리어
		if (getRegisterFlag() == 0) {
			feedFuncArr.clear();
		}
		
		// 기능 추가
		if (getAddOrRemoveFlag() == 1) {
			feedFuncArr.add(getFeedFunction());
		// 기능 제거
		} else if (getAddOrRemoveFlag() == -1) {
			for (int i = 0; i < feedFuncArr.size(); i++) {
				if (feedFuncArr.get(i).equals(getFeedFunction())) {
					feedFuncArr.remove(i);
					break;
				}
			}
		}
	}
	
	public void feedFuncRegister(ProductDto productDto) {
		// 기능 존재하는 플래그 서치 및 활성화
		for (int i = 0; i < feedFuncArr.size(); i++) {
			if (feedFuncArr.get(i).equals("20")) {
				productDto.setFuncTeethFlag(20);
			} else if (feedFuncArr.get(i).equals("21")) {
				productDto.setFuncTearsFlag(21);
			} else if (feedFuncArr.get(i).equals("22")) {
				productDto.setFuncBrainFlag(22);
			} else if (feedFuncArr.get(i).equals("23")) {
				productDto.setFuncImmunityFlag(23);
			} else if (feedFuncArr.get(i).equals("24")) {
				productDto.setFuncBoneFlag(24);
			} else if (feedFuncArr.get(i).equals("25")) {
				productDto.setFuncStressFlag(25);
			} else if (feedFuncArr.get(i).equals("26")) {
				productDto.setFuncKidneyFlag(26);
			} else if (feedFuncArr.get(i).equals("27")) {
				productDto.setFuncHeartFlag(27);
			} else if (feedFuncArr.get(i).equals("28")) {
				productDto.setFuncAllergyFlag(28);
			} else if (feedFuncArr.get(i).equals("29")) {
				productDto.setFuncPregnancyFlag(29);
			} else if (feedFuncArr.get(i).equals("30")) {
				productDto.setFuncIntestineFlag(30);
			} else if (feedFuncArr.get(i).equals("31")) {
				productDto.setFuncNeuteringFlag(31);
			} else if (feedFuncArr.get(i).equals("32")) {
				productDto.setFuncWeightFlag(32);
			} else if (feedFuncArr.get(i).equals("33")) {
				productDto.setFuncSkinFlag(33);
			}
		}
	}
}
