package com.peterpet.demo.module.product;

import java.util.ArrayList;
import java.util.List;

import com.peterpet.demo.module.base.BaseVo;
import com.peterpet.demo.module.base.Constants;

public class ProductVo extends BaseVo{

	private static int currProdType = Constants.INIT_PRODUCT_TYPE;
	public List<String> prodFuncArray = new ArrayList<>();
	public List<String> prodFuncNameArray = new ArrayList<>();
	public List<String> prodFuncIdArray = new ArrayList<>();
	
	private String prodSeq;
	private int prodType;
	private Integer prodTeethFlag;
	private Integer prodTearsFlag;
	private Integer prodBrainFlag;
	private Integer prodImmunityFlag;
	private Integer prodBoneFlag;
	private Integer prodStressFlag;
	private Integer prodKidneyFlag;
	private Integer prodHeartFlag;
	private Integer prodAllergyFlag;
	private Integer prodPregnancyFlag;
	private Integer prodIntestineFlag;
	private Integer prodNeuteringFlag;
	private Integer prodWeightFlag;
	private Integer prodSkinFlag;
	
	private Integer prodFunction;	// 선택한 기능을 저장하기 위한 매개체
	
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
	
	public List<String> getProdFuncArray() {
		return prodFuncArray;
	}

	public void setProdFuncArray(List<String> prodFuncArray) {
		this.prodFuncArray = prodFuncArray;
	}

	public List<String> getProdFuncIdArray() {
		return prodFuncIdArray;
	}

	public void setProdFuncIdArray(List<String> prodFuncIdArray) {
		this.prodFuncIdArray = prodFuncIdArray;
	}

	public List<String> getProdFuncNameArray() {
		return prodFuncNameArray;
	}

	public void setProdFuncNameArray(List<String> prodFuncNameArray) {
		this.prodFuncNameArray = prodFuncNameArray;
	}

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
	
	public static int getCurrProdType() {
		return currProdType;
	}

	public static void setCurrProdType(int currProdType) {
		ProductVo.currProdType = currProdType;
	}

	public Integer getProdTeethFlag() {
		return prodTeethFlag;
	}

	public void setProdTeethFlag(Integer prodTeethFlag) {
		this.prodTeethFlag = prodTeethFlag;
	}

	public Integer getProdTearsFlag() {
		return prodTearsFlag;
	}

	public void setProdTearsFlag(Integer prodTearsFlag) {
		this.prodTearsFlag = prodTearsFlag;
	}

	public Integer getProdBrainFlag() {
		return prodBrainFlag;
	}

	public void setProdBrainFlag(Integer prodBrainFlag) {
		this.prodBrainFlag = prodBrainFlag;
	}

	public Integer getProdImmunityFlag() {
		return prodImmunityFlag;
	}

	public void setProdImmunityFlag(Integer prodImmunityFlag) {
		this.prodImmunityFlag = prodImmunityFlag;
	}

	public Integer getProdBoneFlag() {
		return prodBoneFlag;
	}

	public void setProdBoneFlag(Integer prodBoneFlag) {
		this.prodBoneFlag = prodBoneFlag;
	}

	public Integer getProdStressFlag() {
		return prodStressFlag;
	}

	public void setProdStressFlag(Integer prodStressFlag) {
		this.prodStressFlag = prodStressFlag;
	}

	public Integer getProdKidneyFlag() {
		return prodKidneyFlag;
	}

	public void setProdKidneyFlag(Integer prodKidneyFlag) {
		this.prodKidneyFlag = prodKidneyFlag;
	}

	public Integer getProdHeartFlag() {
		return prodHeartFlag;
	}

	public void setProdHeartFlag(Integer prodHeartFlag) {
		this.prodHeartFlag = prodHeartFlag;
	}

	public Integer getProdAllergyFlag() {
		return prodAllergyFlag;
	}

	public void setProdAllergyFlag(Integer prodAllergyFlag) {
		this.prodAllergyFlag = prodAllergyFlag;
	}

	public Integer getProdPregnancyFlag() {
		return prodPregnancyFlag;
	}

	public void setProdPregnancyFlag(Integer prodPregnancyFlag) {
		this.prodPregnancyFlag = prodPregnancyFlag;
	}

	public Integer getProdIntestineFlag() {
		return prodIntestineFlag;
	}

	public void setProdIntestineFlag(Integer prodIntestineFlag) {
		this.prodIntestineFlag = prodIntestineFlag;
	}

	public Integer getProdNeuteringFlag() {
		return prodNeuteringFlag;
	}

	public void setProdNeuteringFlag(Integer prodNeuteringFlag) {
		this.prodNeuteringFlag = prodNeuteringFlag;
	}

	public Integer getProdWeightFlag() {
		return prodWeightFlag;
	}

	public void setProdWeightFlag(Integer prodWeightFlag) {
		this.prodWeightFlag = prodWeightFlag;
	}

	public Integer getProdSkinFlag() {
		return prodSkinFlag;
	}

	public void setProdSkinFlag(Integer prodSkinFlag) {
		this.prodSkinFlag = prodSkinFlag;
	}

	public Integer getProdFunction() {
		return prodFunction;
	}

	public void setProdFunction(Integer prodFunction) {
		this.prodFunction = prodFunction;
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
	
	public void InitProdType() {
		if (getProdType() != currProdType) {
			if (getProdType() == 0) {
				setProdType(currProdType);
			} else {
				currProdType = getProdType();				
			}
		}
	}
}
