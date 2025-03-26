package com.peterpet.demo.module.product;

import java.util.ArrayList;
import java.util.List;

import com.peterpet.demo.module.base.BaseVo;

public class ProductVo extends BaseVo{

	private String prodSeq;
	private int prodType;
	
	private int registerFlag;	// 등록 진행 여부 플래그
	private int addOrRemoveFlag;	// 뱃지 추가 또는 제거 플래그
	private String prodFunction;	// 선택한 기능을 저장하기 위한 매개체

	public static List<String> prodFuncArr = new ArrayList<>();
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
	
	public String getProdFunction() {
		return prodFunction;
	}

	public void setProdFunction(String prodFunction) {
		this.prodFunction = prodFunction;
	}

	public static List<String> getProdFuncArr() {
		return prodFuncArr;
	}

	public static void setProdFuncArr(List<String> prodFuncArr) {
		ProductVo.prodFuncArr = prodFuncArr;
	}

	public static List<FeedInfoVo> getFeedInfoArr() {
		return feedInfoArr;
	}

	public static void setFeedInfoArr(List<FeedInfoVo> feedInfoArr) {
		FeedInfoVo.feedInfoArr = feedInfoArr;
	}

	public void prodFuncSelect() {
		// 등록 중이 아니라면 클리어
		if (getRegisterFlag() == 0) {
			prodFuncArr.clear();
		}
		
		// 기능 추가
		if (getAddOrRemoveFlag() == 1) {
			prodFuncArr.add(getProdFunction());
		// 기능 제거
		} else if (getAddOrRemoveFlag() == -1) {
			for (int i = 0; i < prodFuncArr.size(); i++) {
				if (prodFuncArr.get(i).equals(getProdFunction())) {
					prodFuncArr.remove(i);
					break;
				}
			}
		}
	}
	
	public void prodFuncRegister(ProductDto productDto) {
		// 기능 존재하는 플래그 서치 및 활성화
		for (int i = 0; i < prodFuncArr.size(); i++) {
			if (prodFuncArr.get(i).equals("20")) {
				productDto.setFuncTeethFlag(20);
			} else if (prodFuncArr.get(i).equals("21")) {
				productDto.setFuncTearsFlag(21);
			} else if (prodFuncArr.get(i).equals("22")) {
				productDto.setFuncBrainFlag(22);
			} else if (prodFuncArr.get(i).equals("23")) {
				productDto.setFuncImmunityFlag(23);
			} else if (prodFuncArr.get(i).equals("24")) {
				productDto.setFuncBoneFlag(24);
			} else if (prodFuncArr.get(i).equals("25")) {
				productDto.setFuncStressFlag(25);
			} else if (prodFuncArr.get(i).equals("26")) {
				productDto.setFuncKidneyFlag(26);
			} else if (prodFuncArr.get(i).equals("27")) {
				productDto.setFuncHeartFlag(27);
			} else if (prodFuncArr.get(i).equals("28")) {
				productDto.setFuncAllergyFlag(28);
			} else if (prodFuncArr.get(i).equals("29")) {
				productDto.setFuncPregnancyFlag(29);
			} else if (prodFuncArr.get(i).equals("30")) {
				productDto.setFuncIntestineFlag(30);
			} else if (prodFuncArr.get(i).equals("31")) {
				productDto.setFuncNeuteringFlag(31);
			} else if (prodFuncArr.get(i).equals("32")) {
				productDto.setFuncWeightFlag(32);
			} else if (prodFuncArr.get(i).equals("33")) {
				productDto.setFuncSkinFlag(33);
			}
		}
	}
	
	public void InitProdType() {
		if (getProdType() == 0) {
			setProdType(1);
		}
	}
}
