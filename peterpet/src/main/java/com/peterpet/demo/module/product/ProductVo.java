package com.peterpet.demo.module.product;

import java.util.ArrayList;
import java.util.List;

import com.peterpet.demo.module.base.BaseVo;
import com.peterpet.demo.module.base.Constants;

public class ProductVo extends BaseVo{

	private static int currProdType = Constants.INIT_PRODUCT_TYPE;
	private String prodSeq;
	private int prodType;
	
	private int addOrRemoveFlag;	// 뱃지 추가 또는 제거 플래그
	private int prodFunction;	// 선택한 기능을 저장하기 위한 매개체
	
	private String infoSeq;
	
	private Integer shFeedSalaryAge;
	private Integer shFeedType;
	private Integer shFeedEtc;
	private Integer shFeedSize;
	private Integer shFeedBrand;
	private Integer shFeedIngredient;
	
	private Integer shSnackType;
	private Integer shSnackSalaryAge;
	private Integer shSnackMaterial;
	private Integer shSnackBrand;
	private Integer shSnackIngredient;

	public static List<String> prodFuncArr = new ArrayList<>();
	public static List<String> prodFuncNameArr = new ArrayList<>();
	
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

	public int getAddOrRemoveFlag() {
		return addOrRemoveFlag;
	}

	public void setAddOrRemoveFlag(int addOrRemoveFlag) {
		this.addOrRemoveFlag = addOrRemoveFlag;
	}
	
	public int getProdFunction() {
		return prodFunction;
	}

	public void setProdFunction(int prodFunction) {
		this.prodFunction = prodFunction;
	}

	public String getInfoSeq() {
		return infoSeq;
	}

	public void setInfoSeq(String infoSeq) {
		this.infoSeq = infoSeq;
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

	public Integer getShSnackType() {
		return shSnackType;
	}

	public void setShSnackType(Integer shSnackType) {
		this.shSnackType = shSnackType;
	}

	public Integer getShSnackSalaryAge() {
		return shSnackSalaryAge;
	}

	public void setShSnackSalaryAge(Integer shSnackSalaryAge) {
		this.shSnackSalaryAge = shSnackSalaryAge;
	}

	public Integer getShSnackMaterial() {
		return shSnackMaterial;
	}

	public void setShSnackMaterial(Integer shSnackMaterial) {
		this.shSnackMaterial = shSnackMaterial;
	}

	public Integer getShSnackBrand() {
		return shSnackBrand;
	}

	public void setShSnackBrand(Integer shSnackBrand) {
		this.shSnackBrand = shSnackBrand;
	}

	public Integer getShSnackIngredient() {
		return shSnackIngredient;
	}

	public void setShSnackIngredient(Integer shSnackIngredient) {
		this.shSnackIngredient = shSnackIngredient;
	}

	public void prodFuncArrClear() {
		prodFuncArr.clear();
		prodFuncNameArr.clear();
	}
	
	public void prodFuncSelect(String name) {		
		// 기능 추가
//		if (getAddOrRemoveFlag() == 1) {
//			prodFuncArr.add(getProdFunction());
//			prodFuncNameArr.add(name);
//		// 기능 제거
//		} else if (getAddOrRemoveFlag() == -1) {
//			for (int i = 0; i < prodFuncArr.size(); i++) {
//				if (prodFuncArr.get(i).equals(getProdFunction())) {
//					prodFuncArr.remove(i);
//					prodFuncNameArr.remove(i);
//					break;
//				}
//			}
//		}
	}
	
	// 기능 존재하는 플래그 서치 및 활성화
	public void prodFuncRegister(ProductDto productDto) {
		List<String> prodFuncFlagAddr = new ArrayList<>();
		
		for (int i = 0; i < prodFuncArr.size(); i++) {
			if (prodFuncArr.get(i).equals("20")) {
				productDto.setProdTeethFlag(20);
			} else if (prodFuncArr.get(i).equals("21")) {
				productDto.setProdTearsFlag(21);
			} else if (prodFuncArr.get(i).equals("22")) {
				productDto.setProdBrainFlag(22);
			} else if (prodFuncArr.get(i).equals("23")) {
				productDto.setProdImmunityFlag(23);
			} else if (prodFuncArr.get(i).equals("24")) {
				productDto.setProdBoneFlag(24);
			} else if (prodFuncArr.get(i).equals("25")) {
				productDto.setProdStressFlag(25);
			} else if (prodFuncArr.get(i).equals("26")) {
				productDto.setProdKidneyFlag(26);
			} else if (prodFuncArr.get(i).equals("27")) {
				productDto.setProdHeartFlag(27);
			} else if (prodFuncArr.get(i).equals("28")) {
				productDto.setProdAllergyFlag(28);
			} else if (prodFuncArr.get(i).equals("29")) {
				productDto.setProdPregnancyFlag(29);
			} else if (prodFuncArr.get(i).equals("30")) {
				productDto.setProdIntestineFlag(30);
			} else if (prodFuncArr.get(i).equals("31")) {
				productDto.setProdNeuteringFlag(31);
			} else if (prodFuncArr.get(i).equals("32")) {
				productDto.setProdWeightFlag(32);
			} else if (prodFuncArr.get(i).equals("33")) {
				productDto.setProdSkinFlag(33);
			}
		}
	}
	
	public void InitProdType() {
		if (getProdType() != currProdType) {
			prodFuncArrClear();
			if (getProdType() == 0) {
				setProdType(currProdType);
			} else {
				currProdType = getProdType();				
			}
		}
	}
}
